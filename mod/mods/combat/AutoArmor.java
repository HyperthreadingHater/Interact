package us.interact.mod.mods.combat;

import org.lwjgl.input.Keyboard;

import de.Hero.settings.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.TimeHelper;

public class AutoArmor extends Mod {

	public AutoArmor() {
		super("AutoArmor", Keyboard.KEY_NONE, true, Category.COMBAT);
	}

	int ticks = 0;
	TimeHelper th = new TimeHelper();

	public void onTick() {
		try {
			if(getSetting("OpenInv"))
				if (!(mc.currentScreen instanceof GuiInventory))
					return;

			if (th.isDelayCompleted((long) SettingsManager.getSettingByName("Delay", this).getValDouble())) {
				if (this.ticks == 0) {
					checkArmor("boot");
				}
				if (this.ticks == 1) {
					checkArmor("leggings");
				}
				if (this.ticks == 2) {
					checkArmor("chestplate");
				}
				if (this.ticks == 3) {
					checkArmor("helmet");
				}
				th.reset();
			}
			this.ticks += 1;
			if (this.ticks > 4) {
				this.ticks = 0;
			}
		} catch (Exception e) {}
	}

	public void checkArmor(String armortype) {
		try {
			ItemStack armor = null;
			try {
				armor = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(this.ticks);
			} catch (Exception localException) {
			}
			int[] usedArmor = getBestArmor(armortype);
			if ((armor != null) && ((armor.getItem() instanceof ItemArmor))) {
				ItemArmor armorOn = (ItemArmor) armor.getItem();
				int currentDurabillity = armorOn.getMaxDamage();
				if (armorOn.getUnlocalizedName().toLowerCase().contains("iron")) {
					currentDurabillity++;
				}
				if (currentDurabillity < usedArmor[1]) {
					Minecraft.playerController.windowClick(0, 8 - this.ticks, 0, 4, Minecraft.getMinecraft().thePlayer);
					Minecraft.playerController.updateController();
				}
			} else {
				setArmor(usedArmor[0]);
			}
		} catch (Exception e) {}
	}

	public void setArmor(int slot) {
		try {
			if (slot != 0) {
				Minecraft.playerController.windowClick(0, slot, 0, 1, Minecraft.getMinecraft().thePlayer);
			}
		} catch (Exception e) {}
	}

	public int[] getBestArmor(String armortype) {
		try {
			int lastDurabillity = 0;
			int slotID = 0;
			for (int i = 9; i < 45; i++) {
				ItemStack stack = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();
				if ((stack != null) && ((stack.getItem() instanceof ItemArmor))) {
					ItemArmor armorOn = (ItemArmor) stack.getItem();
					int currrentDurabillity = armorOn.getMaxDamage();
					if (armorOn.getUnlocalizedName().toLowerCase().contains("iron")) {
						currrentDurabillity++;
					}
					if ((currrentDurabillity >= lastDurabillity)
							&& (armorOn.getUnlocalizedName().toLowerCase().contains(armortype))) {
						slotID = i;
						lastDurabillity = currrentDurabillity;
					}
				}
			}
			return new int[] { slotID, lastDurabillity };
		} catch (Exception e) {
			return new int[] {};
		}
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
