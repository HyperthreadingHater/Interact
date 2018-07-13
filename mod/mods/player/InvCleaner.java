package us.interact.mod.mods.player;

import com.google.common.collect.Multimap;
import de.Hero.settings.Setting;
import de.Hero.settings.SettingsManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import us.interact.mod.Category;
import us.interact.mod.Mod;
import us.interact.mod.ModManager;
import us.interact.utils.ingame.TimeHelper;

public class InvCleaner extends Mod {
	public static TimeHelper timer;

	public InvCleaner() {
		super("InvCleaner", 0, true, Category.PLAYER);
		timer = new TimeHelper();
	}

	public void onUpdate() {
		try {

			if (mc.thePlayer.isUsingItem()) {
				return;
			}
			if ((getSetting("OpenInv")) && (!(mc.currentScreen instanceof GuiInventory))) {
				return;
			}
			CopyOnWriteArrayList<Integer> uselessItems = new CopyOnWriteArrayList();
			for (int o = 0; o < 45; o++) {
				if (mc.thePlayer.inventoryContainer.getSlot(o).getHasStack()) {
					ItemStack item = mc.thePlayer.inventoryContainer.getSlot(o).getStack();
					getItemDamage(item);
					if ((mc.thePlayer.inventory.armorItemInSlot(0) != item)
							&& (mc.thePlayer.inventory.armorItemInSlot(1) != item)
							&& (mc.thePlayer.inventory.armorItemInSlot(2) != item)
							&& (mc.thePlayer.inventory.armorItemInSlot(3) != item) && (item != null)
							&& (item.getItem() != null) && (Item.getIdFromItem(item.getItem()) != 0)
							&& (!stackIsUseful(o))) {
						uselessItems.add(Integer.valueOf(o));
					}
				}
			}
			ArrayList<Integer> torm = new ArrayList();
			for (Integer i : uselessItems) {
				if ((timer.isDelayCompleted((long) SettingsManager
						.getSettingByName("Delay", ModManager.getMod("InvCleaner")).getValDouble()))
						&& (!uselessItems.isEmpty())) {
					Minecraft.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, i.intValue(), 1, 4,
							mc.thePlayer);
					torm.add(i);
					timer.reset();
				}
			}
			for (Integer i : torm) {
				uselessItems.remove(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean stackIsUseful(int i) {
		ItemStack itemStack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();

		boolean hasAlreadyOrBetter = false;
		if (((itemStack.getItem() instanceof ItemSword)) || ((itemStack.getItem() instanceof ItemPickaxe))
				|| ((itemStack.getItem() instanceof ItemAxe))) {
			for (int o = 0; o < 45; o++) {
				if ((o != i) && (mc.thePlayer.inventoryContainer.getSlot(o).getHasStack())) {
					ItemStack item = mc.thePlayer.inventoryContainer.getSlot(o).getStack();
					if (item != null && item.getItem() instanceof ItemPickaxe
							&& itemStack.getItem() instanceof ItemPickaxe) {
						float damageFound = getItemDamage(itemStack);
						damageFound += EnchantmentHelper.func_152377_a(itemStack, EnumCreatureAttribute.UNDEFINED);

						float damageCurrent = getItemDamage(item);
						damageCurrent += EnchantmentHelper.func_152377_a(item, EnumCreatureAttribute.UNDEFINED);
						if (damageCurrent > damageFound) {
							hasAlreadyOrBetter = true;
							break;
						}
					} else if (item != null && item.getItem() instanceof ItemAxe
							&& itemStack.getItem() instanceof ItemAxe) {
						float damageFound = getItemDamage(itemStack);
						damageFound += EnchantmentHelper.func_152377_a(itemStack, EnumCreatureAttribute.UNDEFINED);

						float damageCurrent = getItemDamage(item);
						damageCurrent += EnchantmentHelper.func_152377_a(item, EnumCreatureAttribute.UNDEFINED);
						if (damageCurrent > damageFound) {
							hasAlreadyOrBetter = true;
							break;
						}
					} else if (item != null && item.getItem() instanceof ItemSword
							&& itemStack.getItem() instanceof ItemSword) {
						if (getItemAttackDamage(item) > getItemAttackDamage(itemStack)) {
							hasAlreadyOrBetter = true;
							break;
						}
					}
				}
			}
		} else if ((itemStack.getItem() instanceof ItemArmor)) {
			for (int o = 0; o < 45; o++) {
				if ((i != o) && (mc.thePlayer.inventoryContainer.getSlot(o).getHasStack())) {
					ItemStack item = mc.thePlayer.inventoryContainer.getSlot(o).getStack();
					if ((item != null) && ((item.getItem() instanceof ItemArmor))) {
						List<Integer> helmet = Arrays.asList(new Integer[] { Integer.valueOf(298), Integer.valueOf(314),
								Integer.valueOf(302), Integer.valueOf(306), Integer.valueOf(310) });
						List<Integer> chestplate = Arrays
								.asList(new Integer[] { Integer.valueOf(299), Integer.valueOf(315),
										Integer.valueOf(303), Integer.valueOf(307), Integer.valueOf(311) });
						List<Integer> leggings = Arrays
								.asList(new Integer[] { Integer.valueOf(300), Integer.valueOf(316),
										Integer.valueOf(304), Integer.valueOf(308), Integer.valueOf(312) });
						List<Integer> boots = Arrays.asList(new Integer[] { Integer.valueOf(301), Integer.valueOf(317),
								Integer.valueOf(305), Integer.valueOf(309), Integer.valueOf(313) });
						if ((helmet.contains(Integer.valueOf(Item.getIdFromItem(item.getItem()))))
								&& (helmet.contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))) {
							if (helmet.indexOf(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))) < helmet
									.indexOf(Integer.valueOf(Item.getIdFromItem(item.getItem())))) {
								hasAlreadyOrBetter = true;
								break;
							}
						} else {
							if (chestplate.contains(Integer.valueOf(Item.getIdFromItem(item.getItem())))) {
								if (chestplate.contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem())))) {
									if (chestplate.indexOf(
											Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))) >= chestplate
													.indexOf(Integer.valueOf(Item.getIdFromItem(item.getItem())))) {
										continue;
									}
									hasAlreadyOrBetter = true;
									break;
								}
							}
							if ((leggings.contains(Integer.valueOf(Item.getIdFromItem(item.getItem()))))
									&& (leggings.contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))) {
								if (leggings
										.indexOf(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))) < leggings
												.indexOf(Integer.valueOf(Item.getIdFromItem(item.getItem())))) {
									hasAlreadyOrBetter = true;
									break;
								}
							} else if ((boots.contains(Integer.valueOf(Item.getIdFromItem(item.getItem()))))
									&& (boots.contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))) {
								if (boots.indexOf(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))) < boots
										.indexOf(Integer.valueOf(Item.getIdFromItem(item.getItem())))) {
									hasAlreadyOrBetter = true;
									break;
								}
							}
						}
					}
				}
			}
		}

		if (hasAlreadyOrBetter) {
			return false;
		}

		if(!(itemStack.getItem() instanceof ItemSword)) {
			for (int o = 0; o < 45; o++) {
				if ((i != o) && (mc.thePlayer.inventoryContainer.getSlot(o).getHasStack())) {
					ItemStack item = mc.thePlayer.inventoryContainer.getSlot(o).getStack();
					if (((item != null) && (((item.getItem() instanceof ItemSword)) || ((item.getItem() instanceof ItemAxe))
							|| ((item.getItem() instanceof ItemBow)) || ((item.getItem() instanceof ItemFishingRod))
							|| ((item.getItem() instanceof ItemArmor)) || ((item.getItem() instanceof ItemAxe))
							|| ((item.getItem() instanceof ItemPickaxe)) || (Item.getIdFromItem(item.getItem()) == 346)))
							|| (Item.getIdFromItem(item.getItem()) == 259)) {
						Item found = item.getItem();
						if (Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(item.getItem())) {
							hasAlreadyOrBetter = true;
							break;
						}
					}
				}
			}

			if (hasAlreadyOrBetter) {
				return false;
			}
		}
		
		if ((itemStack.getItem() instanceof ItemArmor)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemAxe)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemBow)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemSword)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemPotion)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemFlintAndSteel)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemEnderPearl)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemBlock)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemFood)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemPickaxe)) {
			return true;
		}
		if ((itemStack.getItem() instanceof ItemTool)) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 367) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 30) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 259) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 262) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 264) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 265) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 346) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 384) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 345) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 296) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 336) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 266) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 280) {
			return true;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 6) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 32) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 31) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 37) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 38) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 39) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 40) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 50) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 54) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 65) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 81) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 84) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 85) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 101) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 102) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 106) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 111) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 113) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 120) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 145) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 146) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 160) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 161) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 165) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 171) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 175) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 188) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 189) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 190) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 191) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 192) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 321) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 323) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 355) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 389) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 390) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 397) {
			return false;
		}
		if (Item.getIdFromItem(itemStack.getItem()) == 416) {
			return false;
		}
		if (itemStack.hasDisplayName()) {
			return true;
		}
		return false;
	}

	private float getItemAttackDamage(ItemStack itemStack) {
		float attackDamage = 0F;

		for (Object o : itemStack.getTooltip(mc.thePlayer, false)) {
			String s = (String) o;
			if (s.contains("Damage")) {
				s = s.replace("§9+", "").replace(" Attack Damage", "").replace(",", ".");
				attackDamage = Float.parseFloat(s);

			}
		}

		return attackDamage;
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

	private boolean betterItem(ItemStack item, ItemStack item1) {

		if (item != null && item1 != null) {
			if (getItemDamage(item) > getItemDamage(item1))
				return true;
		}

		return false;
	}

	public void onEnable() {
		super.onEnable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
