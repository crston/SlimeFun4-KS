package me.mrCookieSlime.ExoticGarden;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.ExoticGarden.CSCoreLibSetup.CSCoreLibLoader;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemInteractionHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class ExoticGarden extends JavaPlugin {
	
	static List<Berry> berries = new ArrayList<Berry>();
	static List<Tree> trees = new ArrayList<Tree>();
	static Map<String, ItemStack> items = new HashMap<String, ItemStack>();
	Category category_main, category_food, category_drinks, category_magic;
	Config cfg;
	
	private static boolean skullitems;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		CSCoreLibLoader loader = new CSCoreLibLoader(this);
		if (loader.load()) {
			if (!new File("plugins/ExoticGarden").exists()) new File("plugins/ExoticGarden").mkdirs();
			PluginUtils utils = new PluginUtils(this);
			utils.setupConfig();
			cfg = utils.getConfig();
			utils.setupMetrics();
			utils.setupUpdater(88425, getFile());
			
			skullitems = cfg.getBoolean("options.item-heads");
			
			category_main = new Category(new CustomItem(getSkull(Material.NETHER_STALK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVhNWM0YTBhMTZkYWJjOWIxZWM3MmZjODNlMjNhYzE1ZDAxOTdkZTYxYjEzOGJhYmNhN2M4YTI5YzgyMCJ9fX0="), "&a마법의 정원 - 식물 및 과일", "", "&a> Click to open"));
			category_food = new Category(new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&a마법의 정원 - 음식", "", "&a> Click to open"));
			category_drinks = new Category(new CustomItem(getSkull(new MaterialData(Material.POTION), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE4ZjFmNzBlODU4MjU2MDdkMjhlZGNlMWEyYWQ0NTA2ZTczMmI0YTUzNDVhNWVhNmU4MDdjNGIzMTNlODgifX19"), "&a마법의 정원 - 음료", "", "&a> Click to open"));
			category_magic = new Category(new CustomItem(new MaterialData(Material.BLAZE_POWDER), "&5마법의 정원 - 특수 식물", "", "&a> Click to open"));
			
			new SlimefunItem(Categories.MISC, new CustomItem(getSkull(Material.ICE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0MGJlZjJjMmMzM2QxMTNiYWM0ZTZhMWE4NGQ1ZmZjZWNiYmZhYjZiMzJmYTdhN2Y3NjE5NTQ0MmJkMWEyIn19fQ=="), "&b아이스 큐브"), "아이스_큐브", RecipeType.GRIND_STONE,
			new ItemStack[] {new ItemStack(Material.ICE), null, null, null, null, null, null, null, null}, new CustomItem(new CustomItem(getSkull(Material.ICE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0MGJlZjJjMmMzM2QxMTNiYWM0ZTZhMWE4NGQ1ZmZjZWNiYmZhYjZiMzJmYTdhN2Y3NjE5NTQ0MmJkMWEyIn19fQ=="), "&b아이스 큐브"), 4))
			.register();
			
			registerBerry("포도", "&c", 8201, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVlOTc2NDliZDk5OTk1NTQxM2ZjYmYwYjI2OWM5MWJlNDM0MmIxMGQwNzU1YmFkN2ExN2U5NWZjZWZkYWIwIn19fQ=="));
			registerBerry("블루베리", "&9", 8205, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVhNWM0YTBhMTZkYWJjOWIxZWM3MmZjODNlMjNhYzE1ZDAxOTdkZTYxYjEzOGJhYmNhN2M4YTI5YzgyMCJ9fX0="));
			registerBerry("엘더베리", "&c", 8193, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU0ODgzYTFlMjJjMzI0ZTc1MzE1MWUyYWM0MjRjNzRmMWNjNjQ2ZWVjOGVhMGRiMzQyMGYxZGQxZDhiIn19fQ=="));
			registerBerry("라스베리", "&d", 8193, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODI2MmM0NDViYzJkZDFjNWJiYzhiOTNmMjQ4MmY5ZmRiZWY0OGE3MjQ1ZTFiZGIzNjFkNGE1NjgxOTBkOWI1In19fQ=="));
			registerBerry("블랙베리", "&8", 8200, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjc2OWY4Yjc4YzQyZTI3MmE2NjlkNmU2ZDE5YmE4NjUxYjcxMGFiNzZmNmI0NmQ5MDlkNmEzZDQ4Mjc1NCJ9fX0="));
			registerBerry("크랜베리", "&c", 8193, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVmZTZjNzE4ZmJhNzE5ZmY2MjIyMzdlZDllYTY4MjdkMDkzZWZmYWI4MTRiZTIxOTJlOTY0M2UzZTNkNyJ9fX0="));
			registerBerry("월귤", "&c", 8193, PlantType.BUSH, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA0ZTU0YmYyNTVhYjBiMWM0OThjYTNhMGNlYWU1YzdjNDVmMTg2MjNhNWEwMmY3OGE3OTEyNzAxYTMyNDkifX19"));
			
			registerBerry("딸기", "&4", 8193, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JjODI2YWFhZmI4ZGJmNjc4ODFlNjg5NDQ0MTRmMTM5ODUwNjRhM2Y4ZjA0NGQ4ZWRmYjQ0NDNlNzZiYSJ9fX0="));
			registerPlant("토마토", "&4", Material.APPLE, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTkxNzIyMjZkMjc2MDcwZGMyMWI3NWJhMjVjYzJhYTU2NDlkYTVjYWM3NDViYTk3NzY5NWI1OWFlYmQifX19"));
			registerPlant("양상추", "&2", Material.LEAVES, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc3ZGQ4NDJjOTc1ZDhmYjAzYjFhZGQ2NmRiODM3N2ExOGJhOTg3MDUyMTYxZjIyNTkxZTZhNGVkZTdmNSJ9fX0="));
			registerPlant("찻 잎", "&a", Material.LEAVES, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTUxNGM4YjQ2MTI0N2FiMTdmZTM2MDZlNmUyZjRkMzYzZGNjYWU5ZWQ1YmVkZDAxMmI0OThkN2FlOGViMyJ9fX0="));
			registerPlant("양배추", "&2", Material.LEAVES, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNkNmQ2NzMyMGM5MTMxYmU4NWExNjRjZDdjNWZjZjI4OGYyOGMyODE2NTQ3ZGIzMGEzMTg3NDE2YmRjNDViIn19fQ=="));
			registerPlant("고구마", "&6", Material.LEAVES, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZmNDg1NzhiNjY4NGUxNzk5NDRhYjFiYzc1ZmVjNzVmOGZkNTkyZGZiNDU2ZjZkZWY3NjU3NzEwMWE2NiJ9fX0="));
			registerPlant("겨자 씨앗", "&e", Material.GOLD_NUGGET, PlantType.FRUIT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWQ1M2E0MjQ5NWZhMjdmYjkyNTY5OWJjM2U1ZjI5NTNjYzJkYzMxZDAyN2QxNGZjZjdiOGMyNGI0NjcxMjFmIn19fQ=="));
			
			registerPlant("옥수수", "&6", Material.GOLDEN_CARROT, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWJkMzgwMmU1ZmFjMDNhZmFiNzQyYjBmM2NjYTQxYmNkNDcyM2JlZTkxMWQyM2JlMjljZmZkNWI5NjVmMSJ9fX0="));
			registerPlant("파인애플", "&6", Material.GOLDEN_CARROT, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdlZGRkODJlNTc1ZGZkNWI3NTc5ZDg5ZGNkMjM1MGM5OTFmMDQ4M2E3NjQ3Y2ZmZDNkMmM1ODdmMjEifX19"));
			registerPlant("플라위", "&6", Material.LEAVES, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVmZGYyMmM5YmY0NGIzZWE1MzJjNGRkZTgzODk0YmI2MWExZjIwMTAxZDExNzRmOGRjYjMzOWI2ZmY0OSJ9fX0="));
			registerPlant("멜론", "&a", Material.LEAVES, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzNmRlZTgwNmJhNDdhMmM0MGU5NWI1N2ExMmYzN2RlNmMyZTY3N2YyMTYwMTMyYTA3ZTI0ZWVmZmE2In19fQ=="));
			registerPlant("호박", "&6", Material.LEAVES, PlantType.DOUBLE_PLANT, new PlantData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM5NzllOTYzOWExNGMzZDQ2Y2U0NmQ3MTRkNDIzY2IxNTkwOTE4MzlhNzYyY2Y5Mzk3MTZmMTUxMWZkMTUifX19"));
			
			registerTree("Apple Oak", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JiMzExZjNiYTFjMDdjM2QxMTQ3Y2QyMTBkODFmZTExZmQ4YWU5ZTNkYjIxMmEwZmE3NDg5NDZjMzYzMyJ9fX0=", "사과", "&c", 8201, "사과 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Coconut", new MaterialData(Material.INK_SACK, (byte) 3), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQyN2RlZDU3Yjk0Y2Y3MTViMDQ4ZWY1MTdhYjNmODViZWY1YTdiZTY5ZjE0YjE1NzNlMTRlN2U0MmUyZTgifX19", "야자", "&6", 8194, "야자 과즙", true, Material.SAND);
			registerTree("Cherry", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUyMDc2NmI4N2QyNDYzYzM0MTczZmZjZDU3OGIwZTY3ZDE2M2QzN2EyZDdjMmU3NzkxNWNkOTExNDRkNDBkMSJ9fX0=", "체리", "&c", 8193, "체리 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Pomegranate", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2JiMzExZjNiYTFjMDdjM2QxMTQ3Y2QyMTBkODFmZTExZmQ4YWU5ZTNkYjIxMmEwZmE3NDg5NDZjMzYzMyJ9fX0=", "석류", "&4", 8201, "석류 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Lemon", new MaterialData(Material.POTATO_ITEM), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU3ZmQ1NmNhMTU5Nzg3NzkzMjRkZjUxOTM1NGI2NjM5YThkOWJjMTE5MmM3YzNkZTkyNWEzMjliYWVmNmMifX19", "레몬", "&e", 8227, "레몬 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Plum", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjlkNjY0MzE5ZmYzODFiNGVlNjlhNjk3NzE1Yjc2NDJiMzJkNTRkNzI2Yzg3ZjY0NDBiZjAxN2E0YmNkNyJ9fX0=", "자두", "&5", 8201, "자두 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Lime", new MaterialData(Material.SLIME_BALL), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE1MTUzNDc5ZDlmMTQ2YTVlZTNjOWUyMThmNWU3ZTg0YzRmYTM3NWU0Zjg2ZDMxNzcyYmE3MWY2NDY4In19fQ==", "라임", "&a", 8203, "라임 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Orange", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjViMWRiNTQ3ZDFiNzk1NmQ0NTExYWNjYjE1MzNlMjE3NTZkN2NiYzM4ZWI2NDM1NWEyNjI2NDEyMjEyIn19fQ==", "오렌지", "&6", 8195, "오렌지 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Peach", new MaterialData(Material.APPLE), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDNiYTQxZmU4Mjc1Nzg3MWU4Y2JlYzlkZWQ5YWNiZmQxOTkzMGQ5MzM0MWNmODEzOWQxZGZiZmFhM2VjMmE1In19fQ==", "복숭아", "&5", 8201, "복숭아 주스", true, Material.DIRT, Material.GRASS);
			registerTree("Pear", new MaterialData(Material.SLIME_BALL), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmRlMjhkZjg0NDk2MWE4ZWNhOGVmYjc5ZWJiNGFlMTBiODM0YzY0YTY2ODE1ZThiNjQ1YWVmZjc1ODg5NjY0YiJ9fX0=", "배", "&a", 8203, "배 주스", true, Material.DIRT, Material.GRASS);
			registerDishes();
			
			registerMagicalPlant("석탄", new ItemStack(Material.COAL, 2), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc4OGY1ZGRhZjUyYzU4NDIyODdiOTQyN2E3NGRhYzhmMDkxOWViMmZkYjFiNTEzNjVhYjI1ZWIzOTJjNDcifX19",
			new ItemStack[] {null, new ItemStack(Material.COAL_ORE), null, new ItemStack(Material.COAL_ORE), new ItemStack(Material.SEEDS), new ItemStack(Material.COAL_ORE), null, new ItemStack(Material.COAL_ORE), null});
			
			registerMagicalPlant("철", new ItemStack(Material.IRON_INGOT), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI5N2JkZjkyYjYxOTI2ZTM5ZjVjZGRmMTJmOGY3MTMyOTI5ZGVlNTQxNzcxZTBiNTkyYzhiODJjOWFkNTJkIn19fQ==",
			new ItemStack[] {null, new ItemStack(Material.IRON_BLOCK), null, new ItemStack(Material.IRON_BLOCK), getItem("석탄_식물"), new ItemStack(Material.IRON_BLOCK), null, new ItemStack(Material.IRON_BLOCK), null});
			
			registerMagicalPlant("금", SlimefunItems.GOLD_4K, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRkZjg5MjI5M2E5MjM2ZjczZjQ4ZjllZmU5NzlmZTA3ZGJkOTFmN2I1ZDIzOWU0YWNmZDM5NGY2ZWNhIn19fQ==",
			new ItemStack[] {null, SlimefunItems.GOLD_16K, null, SlimefunItems.GOLD_16K, getItem("철_식물"), SlimefunItems.GOLD_16K, null, SlimefunItems.GOLD_16K, null});
			
			registerMagicalPlant("레드스톤", new ItemStack(Material.REDSTONE, 8), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThkZWVlNTg2NmFiMTk5ZWRhMWJkZDc3MDdiZGI5ZWRkNjkzNDQ0ZjFlM2JkMzM2YmQyYzc2NzE1MWNmMiJ9fX0=",
			new ItemStack[] {null, new ItemStack(Material.REDSTONE_BLOCK), null, new ItemStack(Material.REDSTONE_BLOCK), getItem("금_식물"), new ItemStack(Material.REDSTONE_BLOCK), null, new ItemStack(Material.REDSTONE_BLOCK), null});
			
			registerMagicalPlant("청금석", new ItemStack(Material.INK_SACK, (byte) 4), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFhMGQwZmVhMWFmYWVlMzM0Y2FiNGQyOWQ4Njk2NTJmNTU2M2M2MzUyNTNjMGNiZWQ3OTdlZDNjZjU3ZGUwIn19fQ==",
			new ItemStack[] {null, new ItemStack(Material.LAPIS_ORE), null, new ItemStack(Material.LAPIS_ORE), getItem("레드스톤_식물"), new ItemStack(Material.LAPIS_ORE), null, new ItemStack(Material.LAPIS_ORE), null});
			
			registerMagicalPlant("엔더", new ItemStack(Material.ENDER_PEARL, 4), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGUzNWFhZGU4MTI5MmU2ZmY0Y2QzM2RjMGVhNmExMzI2ZDA0NTk3YzBlNTI5ZGVmNDE4MmIxZDE1NDhjZmUxIn19fQ==",
			new ItemStack[] {null, new ItemStack(Material.ENDER_PEARL), null, new ItemStack(Material.ENDER_PEARL), getItem("청금석_식물"), new ItemStack(Material.ENDER_PEARL), null, new ItemStack(Material.ENDER_PEARL), null});
			
			registerMagicalPlant("석영", new ItemStack(Material.QUARTZ, 8), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZkZTU4ZDU4M2MxMDNjMWNkMzQ4MjQzODBjOGE0NzdlODk4ZmRlMmViOWE3NGU3MWYxYTk4NTA1M2I5NiJ9fX0=",
			new ItemStack[] {null, new ItemStack(Material.QUARTZ_ORE), null, new ItemStack(Material.QUARTZ_ORE), getItem("엔더_식물"), new ItemStack(Material.QUARTZ_ORE), null, new ItemStack(Material.QUARTZ_ORE), null});
			
			registerMagicalPlant("다이아몬드", new ItemStack(Material.DIAMOND), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg4Y2Q2ZGQ1MDM1OWM3ZDU4OThjN2M3ZTNlMjYwYmZjZDNkY2IxNDkzYTg5YjllODhlOWNiZWNiZmU0NTk0OSJ9fX0=",
			new ItemStack[] {null, new ItemStack(Material.DIAMOND), null, new ItemStack(Material.DIAMOND), getItem("석영_식물"), new ItemStack(Material.DIAMOND), null, new ItemStack(Material.DIAMOND), null});
			
			registerMagicalPlant("에메랄드", new ItemStack(Material.EMERALD), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZjNDk1ZDFlNmViNTRhMzg2MDY4YzZjYjEyMWM1ODc1ZTAzMWI3ZjYxZDcyMzZkNWYyNGI3N2RiN2RhN2YifX19",
			new ItemStack[] {null, new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD), getItem("다이아몬드_식물"), new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD), null});
			
			registerMagicalPlant("발광석", new ItemStack(Material.GLOWSTONE_DUST, 8), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVkN2JlZDhkZjcxNGNlYTA2M2U0NTdiYTVlODc5MzExNDFkZTI5M2RkMWQ5YjkxNDZiMGY1YWIzODM4NjYifX19",
			new ItemStack[] {null, new ItemStack(Material.GLOWSTONE), null, new ItemStack(Material.GLOWSTONE), getItem("레드스톤_식물"), new ItemStack(Material.GLOWSTONE), null, new ItemStack(Material.GLOWSTONE), null});
			
			registerMagicalPlant("흑요석", new ItemStack(Material.OBSIDIAN, 2), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MGI4N2Q1MjI3MWQyYTc1NWRlZGM4Mjg3N2UwZWQzZGY2N2RjYzQyZWE0NzllYzE0NjE3NmIwMjc3OWE1In19fQ==",
			new ItemStack[] {null, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), getItem("청금석_식물"), new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), null});
			
			registerMagicalPlant("슬라임", new ItemStack(Material.SLIME_BALL, 8), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBlNjVlNmU1MTEzYTUxODdkYWQ0NmRmYWQzZDNiZjg1ZThlZjgwN2Y4MmFhYzIyOGE1OWM0YTk1ZDZmNmEifX19",
			new ItemStack[] {null, new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), getItem("엔더_식물"), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), null});
			
			
			final ItemStack grass_seeds = new CustomItem(new MaterialData(Material.PUMPKIN_SEEDS), "&r잔디 씨앗", "", "&7흙에다가 심으면 잔디로 변합니다");
			
			final SlimefunItem crook = new SlimefunItem(Categories.TOOLS, new CustomItem(new MaterialData(Material.WOOD_HOE), "&r사금파리", "", "&7+ &b25% &7확률로 마법의 정원 작물을 얻습니다"), "사금파리", RecipeType.ENHANCED_CRAFTING_TABLE,
			new ItemStack[] {new ItemStack(Material.STICK), new ItemStack(Material.STICK), null, null, new ItemStack(Material.STICK), null, null, new ItemStack(Material.STICK), null});
			
			crook.register(false, new BlockBreakHandler() {
				
				@Override
				public boolean onBlockBreak(BlockBreakEvent arg0, ItemStack arg1, int arg2,List<ItemStack> arg3) {
					if (SlimefunManager.isItemSimiliar(arg1, crook.getItem(), true)) {
						PlayerInventory.damageItemInHand(arg0.getPlayer());
						if ((arg0.getBlock().getType() == Material.LEAVES || arg0.getBlock().getType() == Material.LEAVES_2) && CSCoreLib.randomizer().nextInt(100) < 25) {
							ItemStack sapling = new MaterialData(Material.SAPLING, (byte) ((arg0.getBlock().getData() % 4) + (arg0.getBlock().getType() == Material.LEAVES_2 ? 4: 0))).toItemStack(1);
							arg3.add(sapling);
						}
						return true;
					}
					return false;
				}
			});
			
			new SlimefunItem(category_main, grass_seeds, "잔디_씨앗", new RecipeType(new CustomItem(Material.LONG_GRASS, "&7잔디에서 얻습니다", 1)),
			new ItemStack[] {null, null, null, null, new CustomItem(Material.LONG_GRASS, 1), null, null, null, null})
			.register(false, new ItemInteractionHandler() {
				
				@Override
				public boolean onRightClick(ItemUseEvent arg0, Player arg1, ItemStack arg2) {
					if (SlimefunManager.isItemSimiliar(arg2, grass_seeds, true)) {
						if (arg0.getClickedBlock() != null && arg0.getClickedBlock().getType() == Material.DIRT) {
							PlayerInventory.consumeItemInHand(arg1);
							arg0.getClickedBlock().setType(Material.GRASS);
							arg0.getClickedBlock().getWorld().playEffect(arg0.getClickedBlock().getLocation(), Effect.STEP_SOUND, Material.GRASS);
						}
						return true;
					}
					else return false;
				}
			});
			
			new PlantsListener(this);
			new FoodListener(this);
			
			items.put("SEEDS", new ItemStack(Material.SEEDS));
			items.put("PUMPKIN_SEEDS", new ItemStack(Material.PUMPKIN_SEEDS));
			items.put("MELON_SEEDS", new ItemStack(Material.MELON_SEEDS));
			items.put("OAK_SAPLING", new ItemStack(Material.SAPLING));
			items.put("SPRUCE_SAPLING", new CustomItem(Material.SAPLING, 1));
			items.put("BIRCH_SAPLING", new CustomItem(Material.SAPLING, 2));
			items.put("JUNGLE_SAPLING", new CustomItem(Material.SAPLING, 3));
			items.put("ACACIA_SAPLING", new CustomItem(Material.SAPLING, 4));
			items.put("DARK_OAK_SAPLING", new CustomItem(Material.SAPLING, 5));
			items.put("GRASS_SEEDS", grass_seeds);
			
			Iterator<String> iterator = items.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				cfg.setDefaultValue("long-grass-drops." + key, true);
				if (!cfg.getBoolean("long-grass-drops." + key)) iterator.remove();
			}
			cfg.save();
		}
	}

	@SuppressWarnings("deprecation")
	private void registerDishes() {
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFkYWE5MzNmMTc5Njg4Mjk2YWViMDkwY2E5NDU1OTRlZDU4OTk0N2VhM2M3ZGJjMzJkMTNkNTM5YzkyODBhYSJ9fX0="), "&a라임 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "라임_스무디",
		new ItemStack[] {getItem("라임_주스"), getItem("아이스 큐브"), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkYzljMTlhOTgzOTk0NTljZmQyOTZlN2ExMjg4Mjk5NDI2NWMxNmFiNGNjMTRiMmI4MzIyYjIzZGYyNWEifX19"), "&4토마토 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "토마토_주스",
		new ItemStack[] {getItem("토마토"), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRhYTlhZWFiYWYyNTY4Yjk3YTJlOGNmYTlhNTNiYWNkNGM4ZDg5ZGFkNGJhMzg3ZjZjNGI3NjFhZTA0YTE4In19fQ=="), "&c와인", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "와인",
		new ItemStack[] {getItem("포도"), new ItemStack(Material.SUGAR), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "&e레몬 아이스 티", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "레몬_아이스_티",
		new ItemStack[] {getItem("레몬"), getItem("아이스_큐브"), getItem("찻_잎"), null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkYzljMTlhOTgzOTk0NTljZmQyOTZlN2ExMjg4Mjk5NDI2NWMxNmFiNGNjMTRiMmI4MzIyYjIzZGYyNWEifX19"), "&d라스베리 아이스 티", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "라스베리_아이스_티",
		new ItemStack[] {getItem("라스베리"), getItem("아이스_큐브"), getItem("찻_잎"), null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkYzljMTlhOTgzOTk0NTljZmQyOTZlN2ExMjg4Mjk5NDI2NWMxNmFiNGNjMTRiMmI4MzIyYjIzZGYyNWEifX19"), "&d복숭아 아이스 티", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "복숭아_아이스_티",
		new ItemStack[] {getItem("복숭아"), getItem("아이스_큐브"), getItem("찻_잎"), null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkYzljMTlhOTgzOTk0NTljZmQyOTZlN2ExMjg4Mjk5NDI2NWMxNmFiNGNjMTRiMmI4MzIyYjIzZGYyNWEifX19"), "&4딸기 아이스 티", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "딸기_아이스_티",
		new ItemStack[] {getItem("딸기"), getItem("아이스_큐브"), getItem("찻_잎"), null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNkYzljMTlhOTgzOTk0NTljZmQyOTZlN2ExMjg4Mjk5NDI2NWMxNmFiNGNjMTRiMmI4MzIyYjIzZGYyNWEifX19"), "&c체리 아이스 티", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "체리_아이스_티",
		new ItemStack[] {getItem("체리"), getItem("아이스_큐브"), getItem("찻_잎"), null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzg3YWIyMmNiYWFhYWZjMDQwYTczOWViYWFjNWJhNWRhYjc4MzA3NWU4YzFiY2M4M2QzNTRjZDU2NmRjNzNjIn19fQ=="), "&6차옌", "", "§7허기를 " + "7.0" + " §7채워줍니다"), "차옌",
		new ItemStack[] {getItem("찻_잎"), new ItemStack(Material.SUGAR), SlimefunItems.HEAVY_CREAM, getItem("야자_과즙"), null, null, null, null, null},
		14)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjM0ODdkNDU3ZjkwNjJkNzg3YTNlNmNlMWM0NjY0YmY3NDAyZWM2N2RkMTExMjU2ZjE5YjM4Y2U0ZjY3MCJ9fX0="), "&r호박 빵", "", "§7허기를 " + "4.0" + " §7채워줍니다"), "호박_빵",
		new ItemStack[] {getItem("호박"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, null, null, null, null, null, null},
		8)
		.register();
		
		new EGPlant(Categories.MISC, new CustomItem(getSkull(Material.MILK_BUCKET, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y4ZDUzNmM4YzJjMjU5NmJjYzE3MDk1OTBhOWQ3ZTMzMDYxYzU2ZTY1ODk3NGNkODFiYjgzMmVhNGQ4ODQyIn19fQ=="), "&r마요네즈"), "마요네즈", RecipeType.GRIND_STONE, false,
		new ItemStack[] {new ItemStack(Material.EGG), null, null, null, null, null, null, null, null})
		.register();
		
		new EGPlant(Categories.MISC, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWI5ZTk5NjIxYjk3NzNiMjllMzc1ZTYyYzY0OTVmZjFhYzg0N2Y4NWIyOTgxNmMyZWI3N2I1ODc4NzRiYTYyIn19fQ=="), "&e겨자"), "겨자", RecipeType.GRIND_STONE, false,
		new ItemStack[] {getItem("겨자_씨앗"), null, null, null, null, null, null, null, null})
		.register();
		
		new EGPlant(Categories.MISC, new CustomItem(getSkull(Material.MILK_BUCKET, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg2ZjE5YmYyM2QyNDhlNjYyYzljOGI3ZmExNWVmYjhhMWYxZDViZGFjZDNiODYyNWE5YjU5ZTkzYWM4YSJ9fX0="), "&cBBQ 소스"), "BBQ_소스", RecipeType.ENHANCED_CRAFTING_TABLE, false,
		new ItemStack[] {getItem("토마토"), getItem("겨자"), getItem("SALT"), new ItemStack(Material.SUGAR), null, null, null, null, null})
		.register();
		
		new SlimefunItem(Categories.MISC, new CustomItem(new MaterialData(Material.SUGAR), "&r옥수수 가루"), "옥수수_가루", RecipeType.GRIND_STONE,
		new ItemStack[] {getItem("옥수수"), null, null, null, null, null, null, null, null})
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(new MaterialData(Material.INK_SACK, (byte) 3), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE5Zjk0OGQxNzcxOGFkYWNlNWRkNmUwNTBjNTg2MjI5NjUzZmVmNjQ1ZDcxMTNhYjk0ZDE3YjYzOWNjNDY2In19fQ=="), "&r초콜릿 바", "", "§7허기를 " + "1.5" + " §7채워줍니다"), "초콜릿_바",
		new ItemStack[] {new MaterialData(Material.INK_SACK, (byte) 3).toItemStack(1), SlimefunItems.HEAVY_CREAM, null, null, null, null, null, null, null},
		3)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r감자 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "감자_샐러드",
		new ItemStack[] {new ItemStack(Material.BAKED_POTATO), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r치킨 샌드위치", "", "§7허기를 " + "5.5" + " §7채워줍니다"), "치킨_샌드위치",
		new ItemStack[] {new ItemStack(Material.COOKED_CHICKEN), getItem("마요네즈"), new ItemStack(Material.BREAD), null, null, null, null, null, null},
		11)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r생선 샌드위치", "", "§7허기를 " + "5.5" + " §7채워줍니다"), "생선_샌드위치",
		new ItemStack[] {new ItemStack(Material.COOKED_FISH), getItem("마요네즈"), new ItemStack(Material.BREAD), null, null, null, null, null, null},
		11)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r계란 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "계란_샐러드",
		new ItemStack[] {new ItemStack(Material.EGG), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTNhZWU2ZTlhYzVlNzEwYzk4OWYyN2MzY2MwNDA2Njk2OTIxNDI1MTUxNzZmZTRiZDZiYTllN2I5YmU3MzMwIn19fQ=="), "&4토마토 수프", "", "§7허기를 " + "5.5" + " §7채워줍니다"), "토마토_수프",
		new ItemStack[] {new ItemStack(Material.BOWL), getItem("토마토"), null, null, null, null, null, null, null},
		5)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&c딸기 샐러드", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "딸기_샐러드",
		new ItemStack[] {new ItemStack(Material.BOWL), getItem("딸기"), null, null, null, null, null, null, null},
		4)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&c포도 샐러드", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "포도_샐러드",
		new ItemStack[] {new ItemStack(Material.BOWL), getItem("포도"), null, null, null, null, null, null, null},
		4)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&r치즈 케이크", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "치즈_케이크",
		new ItemStack[] {new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null, null, null},
		16)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c체리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "체리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("체리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&9블루베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "블루베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("블루베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&6호박 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "호박_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("호박"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&6배 치즈 케이크", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "배_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), new ItemStack(Material.SUGAR), getItem("배"), null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.COOKIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjU5MmNmOWY0MmE1YThjOTk1OTY4NDkzZmRkMWIxMWUwYjY5YWFkNjQ3M2ZmNDUzODRhYmU1OGI3ZmM3YzcifX19"), "&6비스킷", "", "§7허기를 " + "2.0" + " §7채워줍니다"), "비스킷",
		new ItemStack[] {SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, null, null, null, null, null, null, null}, 
		2)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZjMzY1MjNjMmQxMWI4YzhlYTJlOTkyMjkxYzUyYTY1NDc2MGVjNzJkY2MzMmRhMmNiNjM2MTY0ODFlZSJ9fX0="), "&8블랙베리 코블러", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "블랙베리_코블러",
		new ItemStack[] {new ItemStack(Material.SUGAR), getItem("블랙베리"), SlimefunItems.WHEAT_FLOUR, null, null, null, null, null, null},
		4)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&r파블로바", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "파블로바",
		new ItemStack[] {getItem("레몬"), getItem("딸기"), new ItemStack(Material.SUGAR), new ItemStack(Material.EGG), SlimefunItems.HEAVY_CREAM, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(Material.GOLDEN_CARROT, "&6콘 옥수수", 0, new String[] {"", "§7허기를 " + "4.5" + " §7채워줍니다"}), "콘_옥수수",
		new ItemStack[] {SlimefunItems.BUTTER, getItem("옥수수"), SlimefunItems.CHEESE, null, null, null, null, null, null},
		3)
		.register();
		
		new CustomFood(category_food, new CustomItem(Material.MUSHROOM_SOUP, "&r옥수수 크림", 0, new String[] {"", "§7허기를 " + "4.0" + " §7채워줍니다"}), "옥수수_크림",
		new ItemStack[] {SlimefunItems.HEAVY_CREAM, getItem("옥수수"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		2)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.GRILLED_PORK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdiYTIyZDVkZjIxZTgyMWE2ZGU0YjhjOWQzNzNhM2FhMTg3ZDhhZTc0ZjI4OGE4MmQyYjYxZjI3MmU1In19fQ=="), "&r베이컨", "", "§7허기를 " + "1.5" + " §7채워줍니다"), "베이컨",
		new ItemStack[] {new ItemStack(Material.GRILLED_PORK), null, null, null, null, null, null, null, null},
		3)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r샌드위치", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "샌드위치",
		new ItemStack[] {new ItemStack(Material.BREAD), getItem("마요네즈"), new ItemStack(Material.COOKED_BEEF), getItem("토마토"), getItem("양상추"), null, null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&rBLT", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "BLT",
		new ItemStack[] {new ItemStack(Material.BREAD), new ItemStack(Material.GRILLED_PORK), getItem("토마토"), getItem("양상추"), null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r양상추 치킨 샌드위치", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "양상추_치킨_샌드위치",
		new ItemStack[] {getItem("치킨_샌드위치"), getItem("양상추"), null, null, null, null, null, null, null},
		1)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r양상추 생선 샌드위치", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "양상추_생선_샌드위치",
		new ItemStack[] {getItem("생선_샌드위치"), getItem("양상추"), null, null, null, null, null, null, null},
		11)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r햄버거", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "햄버거",
		new ItemStack[] {new ItemStack(Material.BREAD), new ItemStack(Material.COOKED_BEEF), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r치즈 버거", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "치즈_버거",
		new ItemStack[] {getItem("햄버거"), SlimefunItems.CHEESE, null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r베이컨 치즈 버거", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "베이컨_치즈_버거",
		new ItemStack[] {getItem("치즈_버거"), getItem("베이컨"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r듀렉스 치즈 버거", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "듀렉스_치즈_버거",
		new ItemStack[] {getItem("치즈_버거"), getItem("양상추"), getItem("토마토"), null, null, null, null, null, null},
		16)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjkxMzY1MTRmMzQyZTdjNTIwOGExNDIyNTA2YTg2NjE1OGVmODRkMmIyNDkyMjAxMzllOGJmNjAzMmUxOTMifX19"), "&r당근 케이크", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "당근_케이크",
		new ItemStack[] {new ItemStack(Material.CARROT_ITEM), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.SUGAR), new ItemStack(Material.EGG), null, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r치킨 버거", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "치킨_버거",
		new ItemStack[] {new ItemStack(Material.BREAD), new ItemStack(Material.COOKED_CHICKEN), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r치킨 치즈 버거", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "치킨_치즈_버거",
		new ItemStack[] {getItem("치킨_버거"), SlimefunItems.CHEESE, null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhZGYxNzQ0NDMzZTFjNzlkMWQ1OWQyNzc3ZDkzOWRlMTU5YTI0Y2Y1N2U4YTYxYzgyYmM0ZmUzNzc3NTUzYyJ9fX0="), "&r베이컨 버거", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "베이컨_버거",
		new ItemStack[] {new ItemStack(Material.BREAD), getItem("베이컨"), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r베이컨 샌드위치", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "베이컨_샌드위치",
		new ItemStack[] {new ItemStack(Material.BREAD), getItem("베이컨"), getItem("마요네즈"), getItem("토마토"), getItem("양상추"), null, null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThjZWQ3NGEyMjAyMWE1MzVmNmJjZTIxYzhjNjMyYjI3M2RjMmQ5NTUyYjcxYTM4ZDU3MjY5YjM1MzhjZiJ9fX0="), "&r타코", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "타코",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.COOKED_BEEF), getItem("양상추"), getItem("토마토"), getItem("CHEESE"), null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThjZWQ3NGEyMjAyMWE1MzVmNmJjZTIxYzhjNjMyYjI3M2RjMmQ5NTUyYjcxYTM4ZDU3MjY5YjM1MzhjZiJ9fX0="), "&r생선 타코", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "생선_타코",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.COOKED_FISH), getItem("양상추"), getItem("토마토"), getItem("CHEESE"), null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(Material.COOKIE, "&c잼 비스킷", 0, new String[] {"", "§7허기를 " + "5.0" + " §7채워줍니다"}), "잼_비스킷",
		new ItemStack[] {null, getItem("비스킷"), null, null, getItem("라스베리_주스"), null, null, getItem("비스킷"), null}, 
		8)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r팬케이크", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "팬케이크",
		new ItemStack[] {getItem("WHEAT_FLOUR"), new ItemStack(Material.SUGAR), getItem("BUTTER"), new ItemStack(Material.EGG), new ItemStack(Material.EGG), null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r블루베리 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "블루베리_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("블루베리"), null, null, null, null, null, null, null},
		13)
		.register();

		new CustomFood(category_food, new CustomItem(getSkull(Material.POTATO_ITEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTYzYjhhZWFmMWRmMTE0ODhlZmM5YmQzMDNjMjMzYTg3Y2NiYTNiMzNmN2ZiYTljMmZlY2FlZTk1NjdmMDUzIn19fQ=="), "&r감자 튀김", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "감자_튀김",
		new ItemStack[] {new ItemStack(Material.POTATO_ITEM), getItem("SALT"), null, null, null, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.POTATO_ITEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQ5N2IxNDdjZmFlNTIyMDU1OTdmNzJlM2M0ZWY1MjUxMmU5Njc3MDIwZTRiNGZhNzUxMmMzYzZhY2RkOGMxIn19fQ=="), "&r팝콘", "", "§7허기를 " + "4.0" + " §7채워줍니다"), "팝콘",
		new ItemStack[] {getItem("옥수수"), getItem("BUTTER"), null, null, null, null, null, null, null},
		8)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.POTATO_ITEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQ5N2IxNDdjZmFlNTIyMDU1OTdmNzJlM2M0ZWY1MjUxMmU5Njc3MDIwZTRiNGZhNzUxMmMzYzZhY2RkOGMxIn19fQ=="), "&r달콤한 팝콘", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "달콤한_팝콘",
		new ItemStack[] {getItem("옥수수"), getItem("BUTTER"), new ItemStack(Material.SUGAR), null, null, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.POTATO_ITEM, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQ5N2IxNDdjZmFlNTIyMDU1OTdmNzJlM2M0ZWY1MjUxMmU5Njc3MDIwZTRiNGZhNzUxMmMzYzZhY2RkOGMxIn19fQ=="), "&r짭짤한 팝콘", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "짭짤한_팝콘",
		new ItemStack[] {getItem("옥수수"), getItem("BUTTER"), getItem("SALT"), null, null, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), "&r셰퍼드 파이", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "셰퍼드_파이",
		new ItemStack[] {getItem("양배추"), new ItemStack(Material.CARROT_ITEM), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.COOKED_BEEF), getItem("토마토"), null, null, null, null},
		16)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), "&r코티지 파이", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "코티지_파이",
		new ItemStack[] {getItem("양배추"), new ItemStack(Material.CARROT_ITEM), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.COOKED_MUTTON), getItem("토마토"), null, null, null, null},
		16)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), "&r치킨 팟 파이", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "치킨_팟_파이",
		new ItemStack[] {new ItemStack(Material.COOKED_CHICKEN), new ItemStack(Material.CARROT_ITEM), SlimefunItems.WHEAT_FLOUR, new ItemStack(Material.POTATO_ITEM), null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTExOWZjYTRmMjhhNzU1ZDM3ZmJlNWRjZjZkOGMzZWY1MGZlMzk0YzFhNzg1MGJjN2UyYjcxZWU3ODMwM2M0YyJ9fX0="), "&r초콜릿 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "초콜릿_케이크",
		new ItemStack[] {getItem("초콜릿_바"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, new ItemStack(Material.EGG), null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.COOKIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZkNzFlMjBmYzUwYWJmMGRlMmVmN2RlY2ZjMDFjZTI3YWQ1MTk1NTc1OWUwNzJjZWFhYjk2MzU1ZjU5NGYwIn19fQ=="), "&r쿠키 앤 크림", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "쿠키_앤_크림",
		new ItemStack[] {getItem("초콜릿_바"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r블루베리 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "블루베리_머핀",
		new ItemStack[] {getItem("블루베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r호박 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "호박_머핀",
		new ItemStack[] {getItem("호박"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r초콜릿 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "초콜릿_머핀",
		new ItemStack[] {getItem("초콜릿_바"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZkNzFlMjBmYzUwYWJmMGRlMmVmN2RlY2ZjMDFjZTI3YWQ1MTk1NTc1OWUwNzJjZWFhYjk2MzU1ZjU5NGYwIn19fQ=="), "&r보스턴 크림 파이", "", "§7허기를 " + "4.5" + " §7채워줍니다"), "보스턴_크림_파이",
		new ItemStack[] {null, getItem("초콜릿_바"), null, null, SlimefunItems.HEAVY_CREAM, null, null, getItem("비스킷"), null},
		9)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmMmQ3ZDdhOGIxYjk2OTE0Mjg4MWViNWE4N2U3MzdiNWY3NWZiODA4YjlhMTU3YWRkZGIyYzZhZWMzODIifX19"), "&r핫도그", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "핫도그",
		new ItemStack[] {null, null, null, null, new ItemStack(Material.GRILLED_PORK), null, null, new ItemStack(Material.BREAD), null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmMmQ3ZDdhOGIxYjk2OTE0Mjg4MWViNWE4N2U3MzdiNWY3NWZiODA4YjlhMTU3YWRkZGIyYzZhZWMzODIifX19"), "&r베이컨 치즈 핫도그", "&7&o\"치즈!\" - @crston", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "베이컨_치즈_핫도그",
		new ItemStack[] {getItem("베이컨"), getItem("핫도그"), getItem("베이컨"), null, getItem("CHEESE"), null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmMmQ3ZDdhOGIxYjk2OTE0Mjg4MWViNWE4N2U3MzdiNWY3NWZiODA4YjlhMTU3YWRkZGIyYzZhZWMzODIifX19"), "&rBBQ 베이컨 핫도그", "&7&o\"안녕 난 밥이야\" - @TreeTreeLeaf", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "BBQ_베이컨_핫도그",
		new ItemStack[] {getItem("베이컨"), getItem("핫도그"), getItem("베이컨"), null, getItem("BBQ_SAUCE"), null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNmMmQ3ZDdhOGIxYjk2OTE0Mjg4MWViNWE4N2U3MzdiNWY3NWZiODA4YjlhMTU3YWRkZGIyYzZhZWMzODIifX19"), "&r스페셜 핫도그", "&7&o\"이건 스페셜 하군요?\" - @Rirbe", "", "§7허기를 " + "10.0" + " §7채워줍니다"), "스페셜_핫도그",
		new ItemStack[] {getItem("베이컨"), getItem("BBQ_소스"), getItem("베이컨"), getItem("베이컨"), new ItemStack(Material.GRILLED_PORK), getItem("베이컨"), getItem("옥수수_가루"), getItem("CHEESE"), getItem("옥수수_가루")},
		20)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhlOTRkZGQ3NjlhNWJlYTc0ODM3NmI0ZWM3MzgzZmQzNmQyNjc4OTRkN2MzYmVlMDExZThlNGY1ZmNkNyJ9fX0="), "&a홍차", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "홍차",
		new ItemStack[] {getItem("찻_잎"), new ItemStack(Material.SUGAR), null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDExNTExYmRkNTViY2I4MjgwM2M4MDM5ZjFjMTU1ZmQ0MzA2MjYzNmUyM2Q0ZDQ2YzRkNzYxYzA0ZDIyYzIifX19"), "&6핫초코", "", "§7허기를 " + "4.0" + " §7채워줍니다"), "핫초코",
		new ItemStack[] {getItem("초콜릿_바"), SlimefunItems.HEAVY_CREAM, null, null, null, null, null, null, null},
		8)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE4ZjFmNzBlODU4MjU2MDdkMjhlZGNlMWEyYWQ0NTA2ZTczMmI0YTUzNDVhNWVhNmU4MDdjNGIzMTNlODgifX19"), "&6피냐 콜라다", "", "§7허기를 " + "7.0" + " §7채워줍니다"), "피냐_콜라다",
		new ItemStack[] {getItem("파인애플"), getItem("아이스_큐브"), getItem("야자_과즙"), null, null, null, null, null, null},
		14)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.NETHER_STALK, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ0ZWQ3YzczYWMyODUzZGZjYWE5Y2E3ODlmYjE4ZGExZDQ3YjE3YWQ2OGIyZGE3NDhkYmQxMWRlMWE0OWVmIn19fQ=="), "&c딸기 초콜릿", "", "§7허기를 " + "2.5" + " §7채워줍니다"), "딸기_초콜릿",
		new ItemStack[] {getItem("초콜릿_바"), getItem("딸기"), null, null, null, null, null, null, null},
		5)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBhY2RlZWQ2MDcyNWQ5NWI2OTExNDM3MmQ3MDI0ZjlkNjY4ZjlmZTc0NjkzN2UwNTkzMjhiYmZiZmY2In19fQ=="), "&e레모네이드", "", "§7허기를 " + "4.0" + " §7채워줍니다"), "레모네이드",
		new ItemStack[] {getItem("레몬_주스"), new ItemStack(Material.SUGAR), null, null, null, null, null, null, null},
		8)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), "&r고구마 파이", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "고구마_파이",
		new ItemStack[] {getItem("고구마"), new ItemStack(Material.EGG), SlimefunItems.HEAVY_CREAM, SlimefunItems.WHEAT_FLOUR, null, null, null, null, null},
		13);
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTExOWZjYTRmMjhhNzU1ZDM3ZmJlNWRjZjZkOGMzZWY1MGZlMzk0YzFhNzg1MGJjN2UyYjcxZWU3ODMwM2M0YyJ9fX0="), "&r레밍턴", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "레밍턴",
		new ItemStack[] {getItem("초콜릿_바"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("야자"), null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r와플", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "와플",
		new ItemStack[] {getItem("WHEAT_FLOUR"), new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), getItem("BUTTER"), null, null, null, null, null},
		12)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE0MjE2ZDEwNzE0MDgyYmJlM2Y0MTI0MjNlNmIxOTIzMjM1MmY0ZDY0ZjlhY2EzOTEzY2I0NjMxOGQzZWQifX19"), "&r클럽 샌드위치", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "클럽_샌드위치",
		new ItemStack[] {new ItemStack(Material.BREAD), getItem("마요네즈"), getItem("베이컨"), getItem("토마토"), getItem("양상추"), getItem("겨자"), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4N2E2MjFlMjY2MTg2ZTYwNjgzMzkyZWIyNzRlYmIyMjViMDQ4NjhhYjk1OTE3N2Q5ZGMxODFkOGYyODYifX19"), "&r브리또", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "브리또",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.COOKED_BEEF), getItem("양상추"), getItem("토마토"), getItem("HEAVY_CREAM"), getItem("CHEESE"), null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4N2E2MjFlMjY2MTg2ZTYwNjgzMzkyZWIyNzRlYmIyMjViMDQ4NjhhYjk1OTE3N2Q5ZGMxODFkOGYyODYifX19"), "&r치킨 브리또", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "치킨_브리또",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.COOKED_CHICKEN), getItem("양상추"), getItem("토마토"), getItem("HEAVY_CREAM"), getItem("CHEESE"), null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFlZTg0ZDE5Yzg1YWZmNzk2Yzg4YWJkYTIxZWM0YzkyYzY1NWUyZDY3YjcyZTVlNzdiNWFhNWU5OWVkIn19fQ=="), "&r구운 샌드위치", "", "§7허기를 " + "5.5" + " §7채워줍니다"), "구운_샌드위치",
		new ItemStack[] {new ItemStack(Material.BREAD), new ItemStack(Material.GRILLED_PORK), getItem("CHEESE"), null, null, null, null, null, null},
		11)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDNhMzU3NGE4NDhmMzZhZTM3MTIxZTkwNThhYTYxYzEyYTI2MWVlNWEzNzE2ZjZkODI2OWUxMWUxOWUzNyJ9fX0="), "&r라자냐", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "라자냐",
		new ItemStack[] {getItem("토마토"), getItem("CHEESE"), SlimefunItems.WHEAT_FLOUR, getItem("토마토"), getItem("CHEESE"), new ItemStack(Material.COOKED_BEEF), null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTUzNjZjYTE3OTc0ODkyZTRmZDRjN2I5YjE4ZmViMTFmMDViYTJlYzQ3YWE1MDM1YzgxYTk1MzNiMjgifX19"), "&r아이스크림", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "아이스크림",
		new ItemStack[] {getItem("HEAVY_CREAM"), getItem("아이스_큐브"), new ItemStack(Material.SUGAR), new MaterialData(Material.INK_SACK, (byte) 3).toItemStack(1), getItem("딸기"), null, null, null, null},
		16)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§6파인애플 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "파인애플_주스",
		new ItemStack[] {getItem("파인애플"), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§6파인애플 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "파인애플_스무디",
		new ItemStack[] {getItem("파인애플_주스"), getItem("아이스_큐브"), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r티라미수", "", "§7허기를 " + "8.0" + " §7채워줍니다"), "티라미수",
		new ItemStack[] {getItem("HEAVY_CREAM"), new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), new MaterialData(Material.INK_SACK, (byte) 3).toItemStack(1), new ItemStack(Material.EGG), null, null, null, null},
		16)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r딸기 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "딸기_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("딸기"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r라스베리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "라스베리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("라스베리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r블랙베리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "블랙베리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("블랙베리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTExOWZjYTRmMjhhNzU1ZDM3ZmJlNWRjZjZkOGMzZWY1MGZlMzk0YzFhNzg1MGJjN2UyYjcxZWU3ODMwM2M0YyJ9fX0="), "&r초콜릿 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "초콜릿_배_케이크",
		new ItemStack[] {getItem("초콜릿_바"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&c사과 배 케이크", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "사과_배_케이크",
		new ItemStack[] {getItem("사과"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		18)
		.register();
	
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFlN2ViMmU0NjFlOTZlNjMxY2JhMGMwY2RhYTU0NDg4MDYzMDJlZGFlOTFiNjFkYWZjMjgxYWU1ODRkOCJ9fX0="), "&c붉은색 플라위 주스", "", "§7허기를 " + "10.0" + " §7채워줍니다"), "붉은_플라위_주스",
		new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), getItem("플라위"), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR)},
		20)
		.register();

		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTYyOWEzZDNmNTE5ZGNkMzg1YjU5OTJlZjM0YmZhOGE2OGMzNzQ0NDc1MGI5NGVkZTVjMmY1MjFlMTczNDIifX19"), "&5보라색 플라위 주스", "", "§7허기를 " + "10.0" + " §7채워줍니다"), "붉은_플라위_주스",
		new ItemStack[] {SlimefunItems.BUTTER, SlimefunItems.BUTTER, SlimefunItems.BUTTER, SlimefunItems.BUTTER, getItem("플라위"), SlimefunItems.BUTTER, SlimefunItems.BUTTER, SlimefunItems.BUTTER, SlimefunItems.BUTTER},
		20)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI2M2IyZGM2YmM1MWNmYzIzODNlZTQ0ZTMzNjFhZmRlNTRlZmVjN2RhNDIzODVlNmZiZTk1MjA4MWFmOTk0MSJ9fX0="), "&d장미색 플라위 주스", "", "§7허기를 " + "10.0" + " §7채워줍니다"), "살구색_플라위_주스",
		new ItemStack[] {getItem("SALT"), getItem("SALT"), getItem("SALT"), getItem("SALT"), getItem("플라위"), getItem("SALT"), getItem("SALT"), getItem("SALT"), getItem("SALT")},
		20)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTMxMDRmMTlhOTQ1YzYyZTEwMzJkZTZlNmM2MzQyMDY2NDdkOTRlZDljMGE1ODRlNmQ2YjZkM2E0NzVmNTIifX19"), "&b하늘색 플라위 주스", "", "§7허기를 " + "10.0" + " §7채워줍니다"), "하늘색_플라위_주스",
		new ItemStack[] {getItem("CHEESE"), getItem("CHEESE"), getItem("CHEESE"), getItem("CHEESE"), getItem("플라위"), getItem("CHEESE"), getItem("CHEESE"), getItem("CHEESE"), getItem("CHEESE")},
		20)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c사과 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "사과_주스",
		new ItemStack[] {getItem("사과"), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c사과 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "사과_스무디",
		new ItemStack[] {getItem("사과"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§6야자 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "야자_스무디",
		new ItemStack[] {getItem("야자"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c체리 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "체리_스무디",
		new ItemStack[] {getItem("체리"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c석류 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "석류_스무디",
		new ItemStack[] {getItem("석류"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§e레몬 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "레몬_스무디",
		new ItemStack[] {getItem("레몬"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§5자두 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "자두_스무디",
		new ItemStack[] {getItem("자두"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§5복숭아 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "복숭아_스무디",
		new ItemStack[] {getItem("복숭아"), null, null, null, null, null, null, null, null},
		10)
		.register();
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§a배 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "배_스무디",
		new ItemStack[] {getItem("배"), null, null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r블루베리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "블루베리_샐러드",
		new ItemStack[] {getItem("블루베리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r엘더베리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "엘더베리_샐러드",
		new ItemStack[] {getItem("엘더베리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r라스베리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "라스베리_샐러드",
		new ItemStack[] {getItem("라스베리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r블랙베리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "블랙베리_샐러드",
		new ItemStack[] {getItem("블랙베리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r크랜베리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "크랜베리_샐러드",
		new ItemStack[] {getItem("크랜베리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r월귤 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "월귤_샐러드",
		new ItemStack[] {getItem("월귤"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r토마토 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "토마토_샐러드",
		new ItemStack[] {getItem("토마토"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r고구마 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "고구마_샐러드",
		new ItemStack[] {getItem("고구마"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r옥수수 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "옥수수_샐러드",
		new ItemStack[] {getItem("옥수수"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r파인애플 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "파인애플_샐러드",
		new ItemStack[] {getItem("파인애플"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r사과 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "사과_샐러드",
		new ItemStack[] {getItem("사과"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r야자 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "야자_샐러드",
		new ItemStack[] {getItem("야자"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r체리 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "체리_샐러드",
		new ItemStack[] {getItem("체리"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r석류 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "석류_샐러드",
		new ItemStack[] {getItem("석류"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r레몬 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "레몬_샐러드",
		new ItemStack[] {getItem("레몬"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r자두 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "자두_샐러드",
		new ItemStack[] {getItem("자두"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r라임 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "라임_샐러드",
		new ItemStack[] {getItem("라임"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r오렌지 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "오렌지_샐러드",
		new ItemStack[] {getItem("오렌지"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r복숭아 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "복숭아_샐러드",
		new ItemStack[] {getItem("복숭아"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r배 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "배_샐러드",
		new ItemStack[] {getItem("배"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.MUSHROOM_SOUP, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlOTJlMTFhNjdiNTY5MzU0NDZhMjE0Y2FhMzcyM2QyOWU2ZGI1NmM1NWZhOGQ0MzE3OWE4YTMxNzZjNmMxIn19fQ=="), "&r멜론 샐러드", "", "§7허기를 " + "6.0" + " §7채워줍니다"), "멜론_샐러드",
		new ItemStack[] {getItem("멜론"), getItem("마요네즈"), new ItemStack(Material.BOWL), null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c포도 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "포도_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("포도"), null, null, null, null, null, null, null},
		17)
		.register();

		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c블루베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "블루베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("블루베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c엘더베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "엘더베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("엘더베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c라스베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "라스베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("라스베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c블랙베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "블랙베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("블랙베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c크랜베리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "크랜베리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("크랜베리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c월귤 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "월귤_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("월귤"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c딸기 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "딸기_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("딸기"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c토마토 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "토마토_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("토마토"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c고구마 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "고구마_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("고구마"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c옥수수 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "옥수수_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("옥수수_가루"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c멜론 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "멜론_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("멜론"), null, null, null, null, null, null, null},
		17)
		.register();

		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c파인애플 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "파인애플_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("파인애플"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c사과 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "사과_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("사과"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c야자 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "야자_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("야자"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c체리 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "체리_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("체리"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c석류 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "석류_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("석류"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c레몬 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "레몬_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("레몬"), null, null, null, null, null, null, null},
		17)
		.register();
	
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c자두 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "자두_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("자두"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c자두 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "자두_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("라임"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c오렌지 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "오렌지_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("오렌지"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2NWI2MWU3OWZjYjkxM2JjODYwZjRlYzYzNWQ0YTZhYjFiNzRiZmFiNjJmYjZlYTZkODlhMTZhYTg0MSJ9fX0="), "&c복숭아 치즈 케이크", "", "§7허기를 " + "8.5" + " §7채워줍니다"), "복숭아_치즈_케이크",
		new ItemStack[] {getItem("치즈_케이크"), getItem("복숭아"), null, null, null, null, null, null, null},
		17)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r포도 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "포도_배_케이크",
		new ItemStack[] {getItem("포도"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();

		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r블루베리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "블루베리_배_케이크",
		new ItemStack[] {getItem("블루베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r엘더베리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "엘더베리_배_케이크",
		new ItemStack[] {getItem("엘더베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r라스베리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "라스베리_배_케이크",
		new ItemStack[] {getItem("라스베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r블랙베리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "블랙베리_배_케이크",
		new ItemStack[] {getItem("블랙베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r크랜베리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "크랜베리_배_케이크",
		new ItemStack[] {getItem("크랜베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r월귤 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "월귤_배_케이크",
		new ItemStack[] {getItem("월귤"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r딸기 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "딸기_배_케이크",
		new ItemStack[] {getItem("딸기"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r토마토 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "토마토_배_케이크",
		new ItemStack[] {getItem("토마토"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r고구마 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "고구마_배_케이크",
		new ItemStack[] {getItem("고구마"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r옥수수 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "옥수수_배_케이크",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r파인애플 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "파인애플_배_케이크",
		new ItemStack[] {getItem("파인애플"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r야자 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "야자_배_케이크",
		new ItemStack[] {getItem("야자"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r체리 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "체리_배_케이크",
		new ItemStack[] {getItem("체리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r석류 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "석류_배_케이크",
		new ItemStack[] {getItem("석류"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r레몬 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "레몬_배_케이크",
		new ItemStack[] {getItem("레몬"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r자두 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "자두_배_케이크",
		new ItemStack[] {getItem("자두"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r라임 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "라임_배_케이크",
		new ItemStack[] {getItem("라임"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r오렌지 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "오렌지_배_케이크",
		new ItemStack[] {getItem("오렌지"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r복숭아 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "복숭아_배_케이크",
		new ItemStack[] {getItem("복숭아"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r멜론 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "멜론_배_케이크",
		new ItemStack[] {getItem("멜론"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZjZkMjY3ZTNhYjNjMGE5ZjUyNjUwM2E0MjFlNmNhMmE2M2RiZmE5YzZhZGEzYmM5ZjhhOWI1NTYzNyJ9fX0="), "&r호박 배 케이크", "", "§7허기를 " + "9.5" + " §7채워줍니다"), "호박_배_케이크",
		new ItemStack[] {getItem("호박"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, getItem("배"), new ItemStack(Material.EGG), null, null, null},
		19)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r포도 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "포도_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("포도"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r엘더베리 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "엘더베리_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("엘더베리"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r라스베리 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "라스베리_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("라스베리"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r크랜베리 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "크랜베리_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("크랜베리"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r월귤 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "월귤_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("월귤"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r딸기 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "딸기_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("딸기"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r토마토 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "토마토_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("토마토"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r고구마 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "고구마_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("고구마"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r옥수수 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "옥수수_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("옥수수_가루"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r파인애플 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "파인애플_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("파인애플"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r사과 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "사과_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("사과"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r야자 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "야자_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("야자"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r체리 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "체리_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("체리"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r석류 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "석류_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("석류"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r레몬 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "레몬_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("레몬"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r자두 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "자두_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("자두"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r라임 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "라임_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("라임"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r오렌지 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "오렌지_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("오렌지"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r복숭아 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "복숭아_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("복숭아"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r배 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "배_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("배"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r멜론 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "멜론_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("멜론"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ3ZjRmNWE3NGM2NjkxMjgwY2Q4MGU3MTQ4YjQ5YjJjZTE3ZGNmNjRmZDU1MzY4NjI3ZjVkOTJhOTc2YTZhOCJ9fX0="), "&r호박 팬케이크", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "호박_팬케이크",
		new ItemStack[] {getItem("팬케이크"), getItem("호박"), null, null, null, null, null, null, null},
		13)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c멜론 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "멜론_주스",
		new ItemStack[] {getItem("멜론"), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFkYWE5MzNmMTc5Njg4Mjk2YWViMDkwY2E5NDU1OTRlZDU4OTk0N2VhM2M3ZGJjMzJkMTNkNTM5YzkyODBhYSJ9fX0="), "&a멜론 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "멜론_스무디",
		new ItemStack[] {getItem("멜론_주스"), getItem("아이스 큐브"), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), "§c호박 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), "호박_주스",
		new ItemStack[] {getItem("호박"), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFkYWE5MzNmMTc5Njg4Mjk2YWViMDkwY2E5NDU1OTRlZDU4OTk0N2VhM2M3ZGJjMzJkMTNkNTM5YzkyODBhYSJ9fX0="), "&a호박 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), "호박_스무디",
		new ItemStack[] {getItem("호박_주스"), getItem("아이스 큐브"), null, null, null, null, null, null, null},
		10)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r포도 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "포도_머핀",
		new ItemStack[] {getItem("포도"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r엘더베리 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "엘더베리_머핀",
		new ItemStack[] {getItem("엘더베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r라스베리 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "라스베리_머핀",
		new ItemStack[] {getItem("라스베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r크랜베리 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "크랜베리_머핀",
		new ItemStack[] {getItem("크랜베리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r월귤 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "월귤_머핀",
		new ItemStack[] {getItem("월귤"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r딸기 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "딸기_머핀",
		new ItemStack[] {getItem("딸기"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r토마토 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "토마토_머핀",
		new ItemStack[] {getItem("토마토"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r고구마 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "고구마_머핀",
		new ItemStack[] {getItem("고구마"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r옥수수 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "옥수수_머핀",
		new ItemStack[] {getItem("옥수수_가루"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r파인애플 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "파인애플_머핀",
		new ItemStack[] {getItem("파인애플"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r사과 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "사과_머핀",
		new ItemStack[] {getItem("사과"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r야자 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "야자_머핀",
		new ItemStack[] {getItem("야자"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r체리 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "체리_머핀",
		new ItemStack[] {getItem("체리"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r석류 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "석류_머핀",
		new ItemStack[] {getItem("석류"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r레몬 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "레몬_머핀",
		new ItemStack[] {getItem("레몬"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r자두 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "자두_머핀",
		new ItemStack[] {getItem("자두"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r라임 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "라임_머핀",
		new ItemStack[] {getItem("라임"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r오렌지 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "오렌지_머핀",
		new ItemStack[] {getItem("오렌지"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r복숭아 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "복숭아_머핀",
		new ItemStack[] {getItem("복숭아"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r배 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "배_머핀",
		new ItemStack[] {getItem("배"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM3OTRjNzM2ZmM3NmU0NTcwNjgzMDMyNWI5NTk2OTQ2NmQ4NmY4ZDdiMjhmY2U4ZWRiMmM3NWUyYWIyNWMifX19"), "&r멜론 머핀", "", "§7허기를 " + "6.5" + " §7채워줍니다"), "멜론_머핀",
		new ItemStack[] {getItem("멜론"), new ItemStack(Material.SUGAR), SlimefunItems.WHEAT_FLOUR, SlimefunItems.BUTTER, SlimefunItems.HEAVY_CREAM, new ItemStack(Material.EGG), null, null, null},
		13)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r포도 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "포도_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("포도"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r블루베리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "블루베리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("블루베리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r엘더베리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "엘더베리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("엘더베리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r크랜베리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "크랜베리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("크랜베리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r월귤 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "월귤_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("월귤"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r토마토 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "토마토_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("토마토"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r고구마 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "고구마_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("고구마"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r옥수수 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "옥수수_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("옥수수_가루"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r파인애플 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "파인애플_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("파인애플"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r사과 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "사과_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("사과"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r야자 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "야자_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("야자"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r체리 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "체리_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("체리"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r석류 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "석류_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("석류"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r레몬 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "레몬_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("레몬"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r자두 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "자두_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("자두"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r라임 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "라임_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("라임"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r오렌지 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "오렌지_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("오렌지"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r복숭아 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "복숭아_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("복숭아"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r배 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "배_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("배"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r멜론 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "멜론_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("멜론"), null, null, null, null, null, null, null},
		18)
		.register();
		
		new CustomFood(category_food, new CustomItem(getSkull(Material.SNOW_BALL, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY5MDkxZDI4ODAyMmM3YjBlYjZkM2UzZjQ0YjBmZWE3ZjJjMDY5ZjQ5NzQ5MWExZGNhYjU4N2ViMWQ1NmQ0In19fQ=="), "&r호박 티라미수", "", "§7허기를 " + "9.0" + " §7채워줍니다"), "호박_티라미수",
		new ItemStack[] {getItem("티라미수"), getItem("호박"), null, null, null, null, null, null, null},
		18)
		.register();
	}

		// TODO Auto-generated method stub

	@Override
	public void onDisable() {
		berries = null;
		trees = null;
		items = null;
	}
	
	public void registerTree(String name, MaterialData material, String texture, String fruitName, String color, int potion, String juice, boolean pie, Material... soil) {
		String id = name.toUpperCase().replace(" ", "_");
		Tree tree = new Tree(id, fruitName, texture, soil);
		trees.add(tree);
		
		items.put(fruitName + "_묘묙", new CustomItem(Material.SAPLING, color + fruitName + " 묘묙", 0));
		
		new SlimefunItem(category_main, new CustomItem(Material.SAPLING, color + fruitName + " 묘묙", 0), fruitName + "_묘묙", new RecipeType(new CustomItem(Material.LONG_GRASS, "&7잔디에서 얻습니다", 1)),
		new ItemStack[] {null, null, null, null, new CustomItem(Material.LONG_GRASS, 1), null, null, null, null})
		.register();
		
		try {
			new EGPlant(category_main, new CustomItem(getSkull(material, texture), color + StringUtils.format(fruitName)), fruitName, new RecipeType(new CustomItem(Material.LEAVES, "&7해당 묘묙에서 수확하여 얻습니다", 0)), true,
			new ItemStack[] {null, null, null, null, getItem(fruitName + "_묘묙"), null, null, null, null})
			.register();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (potion > 0) {
			new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFkYWE5MzNmMTc5Njg4Mjk2YWViMDkwY2E5NDU1OTRlZDU4OTk0N2VhM2M3ZGJjMzJkMTNkNTM5YzkyODBhYSJ9fX0="), color + juice, "", "§7허기를 " + "3.0" + " §7채워줍니다"), juice.toUpperCase().replace(" ", "_"),
			new ItemStack[] {getItem(fruitName), null, null, null, null, null, null, null, null},
			6)
			.register();
		}
		if (pie) {
			try {
				new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), color + fruitName + " 파이", "", "§7허기를 " + "6.5" + " §7채워줍니다"), fruitName.toUpperCase() + "_파이",
				new ItemStack[] {getItem(fruitName.toUpperCase()), new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), new ItemStack(Material.MILK_BUCKET), SlimefunItems.WHEAT_FLOUR, null, null, null, null},
				13)
				.register();
				new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM4YTkzOTA5M2FiMWNkZTY2NzdmYWY3NDgxZjMxMWU1ZjE3ZjYzZDU4ODI1ZjBlMGMxNzQ2MzFmYjA0MzkifX19"), color + fruitName + " 잼 샌드위치", "", "§7허기를 " + "8.0" + " §7채워줍니다"), fruitName.toUpperCase() + "_잼_샌드위치",
						new ItemStack[] {null, new ItemStack(Material.BREAD), null, null, getItem(fruitName.toUpperCase() + "_주스"), null, null, new ItemStack(Material.BREAD), null},
						16)
						.register();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		InputStream stream = Tree.class.getResourceAsStream("schematics/" + id + "_TREE.schematic");
	    OutputStream out = null;
	    int read;
	    byte[] buffer = new byte[4096];
	    try {
	        out = new FileOutputStream(new File("plugins/ExoticGarden/" + id + "_TREE.schematic"));
	        while ((read = stream.read(buffer)) > 0) {
	            out.write(buffer, 0, read);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
				stream.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void registerBerry(String name, String color, int potion, PlantType type, PlantData data) {
		Berry berry = new Berry(name.toUpperCase(), type, data);
		berries.add(berry);
		
		items.put(name.toUpperCase() + "_덤불", new CustomItem(Material.SAPLING, color + name + " 덤불", 0));
		
		new SlimefunItem(category_main, new CustomItem(Material.SAPLING, color + name + " 덤불", 0), name.toUpperCase() + "_덤불", new RecipeType(new CustomItem(Material.LONG_GRASS, "&7잔디에서 얻습니다", 1)),
		new ItemStack[] {null, null, null, null, new CustomItem(Material.LONG_GRASS, 1), null, null, null, null})
		.register();
		
		new EGPlant(category_main, new CustomItem(getSkull(Material.NETHER_STALK, data.getTexture()), color + name), name.toUpperCase(), new RecipeType(new CustomItem(Material.LEAVES, "&7특정 덤불에서 수확하여 얻습니다", 0)), true,
		new ItemStack[] {null, null, null, null, getItem(name.toUpperCase() + "_덤불"), null, null, null, null})
		.register();

		new CustomJuice(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDRhYTlhZWFiYWYyNTY4Yjk3YTJlOGNmYTlhNTNiYWNkNGM4ZDg5ZGFkNGJhMzg3ZjZjNGI3NjFhZTA0YTE4In19fQ=="), color + name + " 주스", "", "§7허기를 " + "3.0" + " §7채워줍니다"), name.toUpperCase() + "_주스",
		new ItemStack[] {getItem(name.toUpperCase()), null, null, null, null, null, null, null, null},
		6)
		.register();
		
		new CustomFood(category_drinks, new CustomItem(getSkull(Material.POTION, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjliZjg4NWY1MTM3YTliZDhjZTQzYTkxYzVkMGI1ZGU5YjMyNGEzN2YxNGUxNWVlY2IzYmJjZmIxNjJhOWViIn19fQ=="), color + name + " 스무디", "", "§7허기를 " + "5.0" + " §7채워줍니다"), name.toUpperCase() + "_스무디",
		new ItemStack[] {getItem(name.toUpperCase() + "_주스"), getItem("아이스_큐브"), null, null, null, null, null, null, null},
		10)
		.register();
		
		try {
			new CustomFood(category_food, new CustomItem(getSkull(Material.BREAD, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM4YTkzOTA5M2FiMWNkZTY2NzdmYWY3NDgxZjMxMWU1ZjE3ZjYzZDU4ODI1ZjBlMGMxNzQ2MzFmYjA0MzkifX19"), color + name + " 잼 샌드위치", "", "§7허기를 " + "8.0" + " §7채워줍니다"), name.toUpperCase() + "_잼_샌드위치",
			new ItemStack[] {null, new ItemStack(Material.BREAD), null, null, getItem(name.toUpperCase() + "_주스"), null, null, new ItemStack(Material.BREAD), null},
			16)
			.register();
			
			new CustomFood(category_food, new CustomItem(getSkull(Material.PUMPKIN_PIE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQxOGM2YjBhMjlmYzFmZTc5MWM4OTc3NGQ4MjhmZjYzZDJhOWZhNmM4MzM3M2VmM2FhNDdiZjNlYjc5In19fQ=="), color + name + " 파이", "", "§7허기를 " + "6.5" + " §7채워줍니다"), name.toUpperCase() + "_파이",
			new ItemStack[] {getItem(name.toUpperCase()), new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), new ItemStack(Material.MILK_BUCKET), SlimefunItems.WHEAT_FLOUR, null, null, null, null},
			13)
			.register();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static ItemStack getItem(String id) {
		SlimefunItem item = SlimefunItem.getByName(id);
		return item != null ? item.getItem(): null;
	}
	
	private ItemStack getSkull(Material material, String texture) {
		return getSkull(new MaterialData(material), texture);
	}
	
	public static ItemStack getSkull(MaterialData material, String texture) {
		try {
			if (texture.equals("NO_SKULL_SPECIFIED")) return material.toItemStack(1);
			return skullitems ? CustomSkull.getItem(texture): material.toItemStack(1);
		} catch (Exception e) {
			e.printStackTrace();
			return material.toItemStack(1);
		}
	}

	public void registerPlant(String name, String color, Material material, PlantType type, PlantData data) {
		Berry berry = new Berry(name.toUpperCase().replace(" ", "_"), type, data);
		berries.add(berry);
		
		items.put(name.toUpperCase() + "_덤불", new CustomItem(Material.SAPLING, color + name + " 식물", 0));
		
		new SlimefunItem(category_main, new CustomItem(Material.SAPLING, color + name + " 식물", 0), name.toUpperCase().replace(" ", "_") + "_덤불", new RecipeType(new CustomItem(Material.LONG_GRASS, "&7잔디에서 얻습니다", 1)),
		new ItemStack[] {null, null, null, null, new CustomItem(Material.LONG_GRASS, 1), null, null, null, null})
		.register();

		new EGPlant(category_main, new CustomItem(getSkull(material, data.getTexture()), color + name), name.toUpperCase().replace(" ", "_"), new RecipeType(new CustomItem(Material.LEAVES, "&7특정 덤불에서 수확하여 얻습니다", 0)), true,
		new ItemStack[] {null, null, null, null, getItem(name.toUpperCase().replace(" ", "_") + "_덤불"), null, null, null, null})
		.register();
	}

	public void registerMagicalPlant(String name, ItemStack item, String skull, ItemStack[] recipe) {
		ItemStack essence = new CustomItem(new MaterialData(Material.BLAZE_POWDER), "&r마법의 정수", "", "§7" + name);
		
		Berry berry = new Berry(essence, name.toUpperCase() + "_정수", PlantType.ORE_PLANT, new PlantData(skull));
		berries.add(berry);
		
		new SlimefunItem(category_magic, new CustomItem(Material.SAPLING, "&r" + name + " 식물", 0), name.toUpperCase().replace(" ", "_") + "_식물", RecipeType.ENHANCED_CRAFTING_TABLE,
		recipe)
		.register();
		
		HandledBlock plant = new HandledBlock(category_magic, essence, name.toUpperCase().replace(" ", "_") + "_정수", RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {essence, essence, essence, essence, null, essence, essence, essence, essence});
		
		plant.setRecipeOutput(item.clone());
		plant.register();
	}
	
	public static Berry getBerry(Block block) {
		SlimefunItem item = BlockStorage.check(block);
		if (item != null && item instanceof HandledBlock) {
			for (Berry berry: ExoticGarden.berries) {
				if (item.getName().equalsIgnoreCase(berry.getName())) return berry;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack harvestPlant(Block block) {
		ItemStack itemstack = null;
		SlimefunItem item = BlockStorage.check(block);
		if (item != null) {
			for (Berry berry: berries) {
				if (item.getName().equalsIgnoreCase(berry.getName())) {
					switch (berry.getType()) {
					case ORE_PLANT:
					case DOUBLE_PLANT: {
						Block plant;
						if (BlockStorage.check(block.getRelative(BlockFace.DOWN)) == null) {
							plant = block;
							BlockStorage.retrieve(block.getRelative(BlockFace.UP));
							block.getWorld().playEffect(block.getRelative(BlockFace.UP).getLocation(), Effect.STEP_SOUND, Material.LEAVES);
							block.getRelative(BlockFace.UP).setType(Material.AIR);;
						}
						else {
							plant = block.getRelative(BlockFace.DOWN);
							BlockStorage.retrieve(block);
							block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, Material.LEAVES);
							block.setType(Material.AIR);;
						}
						plant.setType(Material.SAPLING);
						plant.setData((byte) 0);
						itemstack = berry.getItem();
						BlockStorage.store(plant, getItem(berry.toBush()));
						break;
					}
					default: {
						block.setType(Material.SAPLING);
						block.setData((byte) 0);
						itemstack = berry.getItem();
						BlockStorage.store(block, getItem(berry.toBush()));
						break;
					}
					}
				}
			}
		}
		return itemstack;
	}

}
