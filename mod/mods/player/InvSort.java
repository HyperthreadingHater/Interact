package us.interact.mod.mods.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Multimap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.utils.ingame.TimeHelper;

public class InvSort extends Mod {

	public InvSort() {
		super("InvSorter", 0, true, Category.PLAYER);
	}

	public static int sword = 1, bow = 2, pickaxe = 3;

	@Override
	public void onUpdate() {
		if (getSetting("OpenInv") && !(mc.currentScreen instanceof GuiInventory))
			return;
		ArrayList<Integer> pickaxes = new ArrayList<>();
		ArrayList<Integer> swords = new ArrayList<>();
		ArrayList<Integer> bows = new ArrayList<>();
		for (int i = 0; i < 45; i++) {
			ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
			if (item != null) {
				if (item.getItem() instanceof ItemSword) {
					swords.add(i);
				} else if (item.getItem() instanceof ItemBow) {
					bows.add(i);
				} else if (item.getItem() instanceof ItemPickaxe) {
					pickaxes.add(i);
				}
			}
		}
		int oldPickaxe = getBestItem(pickaxes);
		int oldSword = getBestItem(swords);
		int oldBow = getBestItem(bows);

		if (oldSword != -1 && oldSword != 35 + sword) {
			Minecraft.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, oldSword, sword - 1, 2,
					mc.thePlayer);
		}
		if (oldBow != -1 && oldBow != 35 + bow) {
			Minecraft.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, oldBow, bow - 1, 2,
					mc.thePlayer);
		}
		if (oldPickaxe != -1 && oldPickaxe != 35 + pickaxe) {
			Minecraft.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, oldPickaxe, pickaxe - 1, 2,
					mc.thePlayer);
		}
	}

	private int getBestItem(ArrayList<Integer> items) {
		ItemStack best = null;
		int slot = -1;

		for (int i : items) {
			ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
			if (best != null) {
				if (getItemDamage(item) > getItemDamage(best)) {
					best = item;
					slot = i;
				}
			} else {
				best = item;
				slot = i;
			}
		}

		return slot;
	}

	private float getItemDamage(ItemStack itemStack) {
		Multimap multimap = itemStack.getAttributeModifiers();
		if (!multimap.isEmpty()) {
			Iterator iterator = multimap.entries().iterator();
			if (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				AttributeModifier attributeModifier = (AttributeModifier) entry.getValue();
				double damage;

				if ((attributeModifier.getOperation() != 1) && (attributeModifier.getOperation() != 2)) {
					damage = attributeModifier.getAmount();
				} else {
					damage = attributeModifier.getAmount() * 100.0D;
				}
				if (attributeModifier.getAmount() > 1.0D) {
					return 1.0F + (float) damage;
				}
				return 1.0F;
			}
		}
		return 1.0F;
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
