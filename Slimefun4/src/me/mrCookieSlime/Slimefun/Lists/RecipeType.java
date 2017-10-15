package me.mrCookieSlime.Slimefun.Lists;

import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunGadget;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
import me.mrCookieSlime.Slimefun.api.SlimefunRecipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RecipeType {
	
	public static final RecipeType MULTIBLOCK = new RecipeType(new CustomItem(Material.BRICK, "&b건축식", 0, new String[] {"", "&a&o블럭을 건축하여 만듭니다"}));
	public static final RecipeType ARMOR_FORGE = new RecipeType(new CustomItem(Material.ANVIL, "&b화덕", 0, new String[] {"", "&a&o화덕에서 제작할 수 있습니다"}), "ARMOR_FORGE");
	public static final RecipeType GRIND_STONE = new RecipeType(new CustomItem(Material.DISPENSER, "&b숫돌", 0, new String[] {"", "&a&o숫돌에 갈아서 얻을 수 있습니다"}), "GRIND_STONE");
	public static final RecipeType MOB_DROP = new RecipeType(new CustomItem(Material.MONSTER_EGG, "&b전리품 드랍", 0, new String[] {"", "&a&o아 아이템을 얻을려면 지정된 몹을 죽이세요"}));
	public static final RecipeType SMELTERY = new RecipeType(new CustomItem(Material.FURNACE, "&6제련소", 0, new String[] {"", "&a&o제련소"}), "SMELTERY");
	public static final RecipeType ORE_CRUSHER = new RecipeType(new CustomItem(Material.DISPENSER, "&b광석 분쇄기", 0, new String[] {"", "&a&o광석 분쇄기로 분쇄하여 얻을 수 있습니다"}), "ORE_CRUSHER");
	public static final RecipeType GOLD_PAN = new RecipeType(new CustomItem(Material.BOWL, "&b골드 팬", 0, new String[] {"", "&a&o자갈에 골드 팬을 사용하여 얻을 수 있습니다"}));
	public static final RecipeType COMPRESSOR = new RecipeType(new CustomItem(Material.PISTON_BASE, "&b압축기", 0, new String[] {"", "&a&o압축기를 사용하여 압축해 얻을 수 있습니다"}), "COMPRESSOR");
	public static final RecipeType PRESSURE_CHAMBER = new RecipeType(new CustomItem(Material.GLASS, "&b압력 챔버", 0, new String[] {"", "&a&o압력 챔버를 사용하여 압축해 얻을 수 있습니다"}), "PRESSURE_CHAMBER");
	public static final RecipeType OVEN = new RecipeType(new CustomItem(Material.FURNACE, "&b화로", 0, new String[] {"", "&a&o화로에 구워 얻을 수 있습니다"}), "OVEN");
	public static final RecipeType MAGIC_WORKBENCH = new RecipeType(new CustomItem(Material.BOOKSHELF, "&6마법의 제작대", 0, new String[] {"", "&a&o마법의 제작대에 제작하여 얻을 수 있습니다"}), "MAGIC_WORKBENCH");
	public static final RecipeType ORE_WASHER = new RecipeType(new CustomItem(Material.CAULDRON_ITEM, "&6광석 세척기", 0, new String[] {"", "&a&o광석 세척기에 세척하여 얻을 수 있습니다"}), "ORE_WASHER");
	public static final RecipeType ENHANCED_CRAFTING_TABLE = new RecipeType(new CustomItem(Material.WORKBENCH, "&e강화된 제작대", 0, new String[] {"", "&a&o평범한 제작대로는 강력한 힘을 담기 어렵습니다", "&a&o강화된 제작대에서 제작하여 얻을 수 있습니다"}), "ENHANCED_CRAFTING_TABLE");
	public static final RecipeType JUICER = new RecipeType(new CustomItem(Material.GLASS_BOTTLE, "&e과즙기", 0, new String[] {"", "&a&o과즙기에서 만들 수 있습니다"}), "JUICER");
	public static final RecipeType ANCIENT_ALTAR = new RecipeType(new CustomItem(Material.ENCHANTMENT_TABLE, "&4고대의 제단", 0, new String[] {"", "&d고대의 제단에서 의식을 수행한 후 얻을 수 있습니다"}));
	public static final RecipeType HEATED_PRESSURE_CHAMBER = new RecipeType(new CustomItem(Material.STAINED_GLASS, "&c가열식 압력 챔버", 8, new String[] {"", "&a&o가열식 압력 챔버에서 가열하여 얻을 수 있습니다"}), "HEATED_PRESSURE_CHAMBER");
	
	public static final RecipeType SHAPED_RECIPE = new RecipeType(new CustomItem(Material.WORKBENCH, "&e유형의 레시피", 0, new String[] {"", "&a&o제작대의 표준 레시피 입니다"}));
	public static final RecipeType SHAPELESS_RECIPE = new RecipeType(new CustomItem(Material.WORKBENCH, "&e무형의 레시피", 0, new String[] {"", "&a&o제작대의 표준 레시피 입니다"}));
	public static final RecipeType FURNACE = new RecipeType(new CustomItem(Material.FURNACE, "&e화로 레시피", 0, new String[] {"", "&a&o화로의 표준 레시피 입니다"}));
	public static final RecipeType NULL = new RecipeType(null);
	
	ItemStack item;
	String machine;
	
	public RecipeType(ItemStack item) {
		this.item = item;
		this.machine = "";
	}
	
	public RecipeType(ItemStack item, String machine) {
		this.item = item;
		this.machine = machine;
	}
	
	public RecipeType(String machine, int seconds, ItemStack[] input, ItemStack[] output) {
		this.machine = machine;
		SlimefunItem item = getMachine();
		this.item = item.getItem();
		
		SlimefunRecipes.registerMachineRecipe(machine, seconds, input, output);
	}
	
	public ItemStack toItem() {
		return this.item;
	}
	
	public SlimefunItem getMachine() {
		return SlimefunItem.getByName(machine);
	}
	
	public static List<ItemStack> getRecipeInputs(SlimefunItem machine) {
		if (machine == null) return new ArrayList<ItemStack>();
		List<ItemStack[]> recipes = (machine instanceof SlimefunMachine ? ((SlimefunMachine) machine).getRecipes(): ((SlimefunGadget) machine).getRecipes());
		List<ItemStack> convertable = new ArrayList<ItemStack>();
		for (int i = 0; i < recipes.size(); i++) {
			if (i % 2 == 0) convertable.add(recipes.get(i)[0]);
		}
		return convertable;
	}
	
	public static List<ItemStack[]> getRecipeInputList(SlimefunItem machine) {
		if (machine == null) return new ArrayList<ItemStack[]>();
		List<ItemStack[]> recipes = (machine instanceof SlimefunMachine ? ((SlimefunMachine) machine).getRecipes(): ((SlimefunGadget) machine).getRecipes());
		List<ItemStack[]> convertable = new ArrayList<ItemStack[]>();
		for (int i = 0; i < recipes.size(); i++) {
			if (i % 2 == 0) convertable.add(recipes.get(i));
		}
		return convertable;
	}
	
	public static ItemStack getRecipeOutput(SlimefunItem machine, ItemStack input) {
		List<ItemStack[]> recipes = (machine instanceof SlimefunMachine ? ((SlimefunMachine) machine).getRecipes(): ((SlimefunGadget) machine).getRecipes());
		return recipes.get(((getRecipeInputs(machine).indexOf(input) * 2) + 1))[0];
	}
	
	public static ItemStack getRecipeOutputList(SlimefunItem machine, ItemStack[] input) {
		List<ItemStack[]> recipes = (machine instanceof SlimefunMachine ? ((SlimefunMachine) machine).getRecipes(): ((SlimefunGadget) machine).getRecipes());
		return recipes.get(((getRecipeInputList(machine).indexOf(input) * 2) + 1))[0];
	}
}
