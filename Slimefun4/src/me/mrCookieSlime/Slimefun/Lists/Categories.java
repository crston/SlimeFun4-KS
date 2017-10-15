package me.mrCookieSlime.Slimefun.Lists;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomArmor;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.MenuItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.Objects.SeasonCategory;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

/**
 * Built-in categories.
 * 
 * @author TheBusyBiscuit
 * @since 4.0
 * @see Category
 */
public class Categories {
	
	public static Category WEAPONS = new Category(new MenuItem(Material.GOLD_SWORD, "&7무기", 0, "Open"), 1);
	public static Category PORTABLE = null;
	public static Category FOOD = new Category(new MenuItem(Material.APPLE, "&7음식", 0, "Open"), 2);
	public static Category MACHINES_1 = null;
	public static LockedCategory ELECTRICITY = null;
	public static LockedCategory GPS = null;
	public static Category ARMOR = new Category(new MenuItem(Material.IRON_CHESTPLATE, "&7방어구", 0, "Open"), 2);
	public static Category LUMPS_AND_MAGIC = new Category(new MenuItem(Material.FIREBALL, "&7마법 아이템", 0, "Open"), 2);
	public static Category MAGIC = new Category(new MenuItem(Material.BLAZE_POWDER, "&7마법 재료", 0, "Open"), 3);
	public static Category MISC = null;
	public static Category TECH = new Category(new CustomArmor(new MenuItem(Material.LEATHER_CHESTPLATE, "&7기술 아이템", 0, "Open"), Color.SILVER), 3);
	public static Category RESOURCES = null;
	public static Category CARGO = null;
	public static Category TECH_MISC = new Category(new MenuItem(Material.REDSTONE_COMPARATOR, "&7기술 재료", 0, "Open"), 2);
	public static Category MAGIC_ARMOR = new Category(new MenuItem(Material.GOLD_CHESTPLATE, "&7마법 방어구", 0, "Open"), 2);
	public static Category TALISMANS_1 = new Category(new MenuItem(Material.EMERALD, "&7부적 &7- &eI", 0, "Open"), 2);
	public static LockedCategory TALISMANS_2 = new LockedCategory(new MenuItem(Material.EMERALD, "&7부적 &7- &e II", 0, "Open"), 3, TALISMANS_1);
	public static Category TOOLS = new Category(new MenuItem(Material.GOLD_PICKAXE, "&7도구", 0, "Open"), 1);
	public static SeasonCategory CHRISTMAS = new SeasonCategory(12, 1, new MenuItem(Material.NETHER_STAR, "&a크&c리&a스&c마&a스", 0, ChatColor.translateAlternateColorCodes('&', "&c도와줘요! &a산타")));
	public static SeasonCategory VALENTINES_DAY = new SeasonCategory(2, 2, new MenuItem(Material.RED_ROSE, "&d발렌타이 데이", 0, ChatColor.translateAlternateColorCodes('&', "&d사랑을 축하해")));
	public static SeasonCategory EASTER = new SeasonCategory(1, 2, new MenuItem(Material.EGG, "&6이스터 에그", 0, ChatColor.translateAlternateColorCodes('&', "&a계란 페인트")));
	public static SeasonCategory BIRTHDAY = new SeasonCategory(10, 1, new MenuItem(Material.FIREWORK, "&a&lmrCookieSlime 의 생일 &7(10월 26일)", 0, ChatColor.translateAlternateColorCodes('&', "&a나와 함께 축하해")));
	
	static {
		try {
			MISC = new Category(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRkYTk3ZjA4MGUzOTViODQyYzRjYzgyYTg0MDgyM2Q0ZGJkOGNhNjg4YTIwNjg1M2U1NzgzZTRiZmRjMDEyIn19fQ=="), "&7기타", "", "&a> 클릭하여 열기"), 2);
			PORTABLE = new Category(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&7아이템", "", "&a> 클릭하여 열기"), 1);
			MACHINES_1 = new Category(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&7일반 기계", "", "&a> 클릭하여 열기"), 1);
			ELECTRICITY = new LockedCategory(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU4NDQzMmFmNmYzODIxNjcxMjAyNThkMWVlZThjODdjNmU3NWQ5ZTQ3OWU3YjBkNGM3YjZhZDQ4Y2ZlZWYifX19"), "&b에너지 및 전기", "", "&a> 클릭하여 열기"), 4, MACHINES_1);
			GPS = new LockedCategory(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&bGPS 기계", "", "&a> 클릭하여 열기"), 4, MACHINES_1);
			RESOURCES = new Category(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2U4ZjVhZGIxNGQ2YzlmNmI4MTBkMDI3NTQzZjFhOGMxZjQxN2UyZmVkOTkzYzk3YmNkODljNzRmNWUyZTgifX19"), "&7자원", "", "&a> 클릭하여 열기"), 1);
			CARGO = new LockedCategory(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTUxMGJjODUzNjJhMTMwYTZmZjlkOTFmZjExZDZmYTQ2ZDdkMTkxMmEzNDMxZjc1MTU1OGVmM2M0ZDljMiJ9fX0="), "&c화물 관리", "", "&a> 클릭하여 열기"), 4, MACHINES_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
