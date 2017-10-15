package me.mrCookieSlime.Slimefun.Lists;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomArmor;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomPotion;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.Christmas;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class SlimefunItems {
	
	/*		 Items 		*/
	public static ItemStack PORTABLE_CRAFTER = new CustomItem(Material.BOOK, "&6휴대용 제작대", 0, new String[] {"&a&o간편한 제작대", "", "&e우 클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack PORTABLE_DUSTBIN = null;
	public static ItemStack ENDER_BACKPACK = null;
	public static ItemStack MAGIC_EYE_OF_ENDER = new CustomItem(Material.EYE_OF_ENDER, "&6&l마법의 엔더의 눈", 0, new String[] {"&4&l엔더 세트를 입은 상태에서 사용할 수 있습니다", "", "&e우클릭 &7으로 엔더 진주를 발사합니다"});
	public static ItemStack BROKEN_SPAWNER = new CustomItem(new MaterialData(Material.MOB_SPAWNER), "&c깨진 스포너", "&7Type: &b<Type>", "", "&c고대의 제단에서 수선하여 사용이 가능합니다");
	public static ItemStack REPAIRED_SPAWNER = new CustomItem(Material.MOB_SPAWNER, "&b강화된 스포너", 0, new String[] {"&7Type: &b<Type>"});
	public static ItemStack INFERNAL_BONEMEAL = new CustomItem(new MaterialData(Material.INK_SACK, (byte) 15), "&4네더 뼛가루", "", "&c성장 속도를 촉진시킬 수 있습니다", "&c네더 사마귀에만 해당됩니다");
	
	/*		 Gadgets 		*/
	public static ItemStack GOLD_PAN = new CustomItem(Material.BOWL, "&6골드 팬", 0, new String[] {"&a&o다양한 종류의 재료를 얻을 수 있습니다", "", "&e우클릭 &7으로 자갈에서 다양한 재료를 팬할 수 있습니다"});
	public static ItemStack PARACHUTE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&r&l낙하산", 0, new String[] {"", "&e쉬프트&7 를 누르고 있으면 사용할 수 있습니다"}), Color.WHITE);
	public static ItemStack GRAPPLING_HOOK = new CustomItem(Material.LEASH, "&6갈고리 훅", 0, new String[] {"", "&e우클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack SOLAR_HELMET = new CustomItem(Material.IRON_HELMET, "&b태양열 모자", 0, new String[] {"", "&a&o들고있는 아이템과 입고있는 것을 충전할 수 있습니다"});
	public static ItemStack CLOTH = new CustomItem(Material.PAPER, "&b천", 0);
	public static ItemStack CAN = null;
	public static ItemStack NIGHT_VISION_GOGGLES = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&a야간 투시경", 0, new String[] {"", "&9+ 야간 투시"}), Color.BLACK);
	public static ItemStack FARMER_SHOES = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&e농부의 신발", 0, new String[] {"", "&6&o작물을 짓밟아지는 것을 보호합니다"}), Color.YELLOW);
	public static ItemStack INFUSED_MAGNET = null;
	public static ItemStack FLASK_OF_KNOWLEDGE = new CustomItem(Material.GLASS_BOTTLE, "&c지식의 영약", 0, new String[] {"", "&r자신의 경험치 일부를", "&r&r경험치 병으로 변환합니다", "&7비용: &a1 레벨"});
	public static ItemStack RAG = new CustomItem(Material.PAPER, "&c낡은 붕대", 0, new String[] {"", "&a의료 용품 &7- &eI", "", "&r하트 2칸을 채워줍니다", "&r화상 치료", "", "&e우클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack BANDAGE = new CustomItem(Material.PAPER, "&c붕대", 0, new String[] {"", "&a의료 용품 &7- &eII", "", "&r하트 4칸을 채워줍니다", "&r화상 치료", "", "&e우클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack SPLINT = new CustomItem(Material.STICK, "&c부목", 0, new String[] {"", "&a의료 용품 &7- &eI", "", "&r하트 2칸을 채워줍니다", "", "&e우클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack VITAMINS = new CustomItem(Material.NETHER_STALK, "&c비타민", 0, new String[] {"", "&a의료 용품 &7- &eIII", "", "&r하트 4칸을 채워줍니다", "&r화상 치료", "&r독 / 부패 / 방사선을 치료할 수 있습니다", "", "&e우클릭 &7으로 사용할 수 있습니다"});
	public static ItemStack MEDICINE = new CustomItem(Material.POTION, "&c한약", 8229, new String[] {"", "&a의료 용품 &7- &eIII", "", "&r하트 4칸을 채워줍니다", "&r화상 치료", "&r독 / 부패 / 방사선을 치료할 수 있습니다", "", "&e우클릭 &7으로 마실 수 있습니다"});
	
	/*		Backpacks		*/
	public static ItemStack BACKPACK_SMALL = null;
	public static ItemStack BACKPACK_MEDIUM = null;
	public static ItemStack BACKPACK_LARGE = null;
	public static ItemStack WOVEN_BACKPACK = null;
	public static ItemStack GILDED_BACKPACK = null;
	public static ItemStack BOUND_BACKPACK = null;
	public static ItemStack COOLER = null;
	
	public static ItemStack VOIDBAG_SMALL = null;
	public static ItemStack VOIDBAG_MEDIUM = null;
	public static ItemStack VOIDBAG_BIG = null;
	public static ItemStack VOIDBAG_LARGE = null;
	public static ItemStack BOUND_VOIDBAG = null;

	/*		 Jetpacks		*/
	public static ItemStack DURALUMIN_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eI", "", "&8\u21E8 &7재질: &b두랄루민", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "&8\u21E8 &7추력: &c0.35", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack SOLDER_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eII", "", "&8\u21E8 &7재질: &b땜납", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "&8\u21E8 &7추력: &c0.4", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack BILLON_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eIII", "", "&8\u21E8 &7재질: &b금동", "&c&o&8\u21E8 &e\u26A1 &70 / 45 J", "&8\u21E8 &7추력: &c0.45", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack STEEL_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eIV", "", "&8\u21E8 &7재질: &b강철", "&c&o&8\u21E8 &e\u26A1 &70 / 60 J", "&8\u21E8 &7추력: &c0.5", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack DAMASCUS_STEEL_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eV", "", "&8\u21E8 &7재질: &b다마스커스 강철", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "&8\u21E8 &7추력: &c0.55", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack REINFORCED_ALLOY_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eVI", "", "&8\u21E8 &7재질: &b강화 합금", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "&8\u21E8 &7추력: &c0.6", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack CARBONADO_JETPACK = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_CHESTPLATE), "&9전기 제트 팩 &7- &eVII", "", "&8\u21E8 &7재질: &b카르보나두", "&c&o&8\u21E8 &e\u26A1 &70 / 150 J", "&8\u21E8 &7추력: &c0.7", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.BLACK);
	public static ItemStack ARMORED_JETPACK = new CustomItem(new MaterialData(Material.IRON_CHESTPLATE), "&9기갑 된 제트 팩", "&8\u21E8 &7재질: &b강철", "", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7추력: &c0.45", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다");
	
	/*		 Jetboots		*/
	public static ItemStack DURALUMIN_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eI", "", "&8\u21E8 &7재질: &b두랄루민", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "&8\u21E8 &7속도: &a0.35", "&8\u21E8 &7정확성: &c50%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack SOLDER_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eII", "", "&8\u21E8 &7재질: &b땜납", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "&8\u21E8 &7속도: &a0.4", "&8\u21E8 &7정확성: &660%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack BILLON_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eIII", "", "&8\u21E8 &7재질: &b금동", "&c&o&8\u21E8 &e\u26A1 &70 / 40 J", "&8\u21E8 &7속도: &a0.45", "&8\u21E8 &7정확성: &665%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack STEEL_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eIV", "", "&8\u21E8 &7재질: &b강철", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7속도: &a0.5", "&8\u21E8 &7정확성: &e70%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack DAMASCUS_STEEL_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eV", "", "&8\u21E8 &7재질: &b다마스커스 강철", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "&8\u21E8 &7속도: &a0.55", "&8\u21E8 &7정확성: &a75%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack REINFORCED_ALLOY_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eVI", "", "&8\u21E8 &7재질: &b강화 합금", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "&8\u21E8 &7속도: &a0.6", "&8\u21E8 &7정확성: &c80%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.SILVER);
	public static ItemStack CARBONADO_JETBOOTS = new CustomArmor(new CustomItem(new MaterialData(Material.LEATHER_BOOTS), "&9제트 부츠 &7- &eVII", "", "&8\u21E8 &7재질: &b카르보나두", "&c&o&8\u21E8 &e\u26A1 &70 / 125 J", "&8\u21E8 &7속도: &a0.7", "&8\u21E8 &7정확성: &c99.9%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다"), Color.BLACK);
	public static ItemStack ARMORED_JETBOOTS = new CustomItem(new MaterialData(Material.IRON_BOOTS), "&9기갑 된 제트 부츠", "", "&8\u21E8 &7재질: &b강철", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "&8\u21E8 &7속도: &a0.45", "&8\u21E8 &7정확성: &e70%", "", "&e쉬프트 &7를 누르고 있으면 사용할 수 있습니다");
	
	/*		 Multi Tools		*/
	public static ItemStack DURALUMIN_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eI", "", "&8\u21E8 &7재질: &b두랄루민", "&c&o&8\u21E8 &e\u26A1 &70 / 20 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack SOLDER_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eII", "", "&8\u21E8 &7재질: &b땜납", "&c&o&8\u21E8 &e\u26A1 &70 / 30 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack BILLON_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eIII", "", "&8\u21E8 &7재질: &b금동", "&c&o&8\u21E8 &e\u26A1 &70 / 40 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack STEEL_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eIV", "", "&8\u21E8 &7재질: &b강철", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack DAMASCUS_STEEL_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eV", "", "&8\u21E8 &7재질: &b다마스커스 강철", "&c&o&8\u21E8 &e\u26A1 &70 / 60 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack REINFORCED_ALLOY_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eVI", "", "&8\u21E8 &7재질: &b강화 합금", "&c&o&8\u21E8 &e\u26A1 &70 / 75 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	public static ItemStack CARBONADO_MULTI_TOOL = new CustomItem(new MaterialData(Material.SHEARS), "&9멀티 툴 &7- &eVII", "", "&8\u21E8 &7재질: &b카르보나두", "&c&o&8\u21E8 &e\u26A1 &70 / 100 J", "", "&e우클릭 &7으로 사용할 수 있습니다", "&7쉬프트 + 우클릭 &7을 누르고 있으면 모드를 변경합니다");
	
	/*		 Food 		*/
	public static ItemStack FORTUNE_COOKIE = new CustomItem(Material.COOKIE, "&6포춘 쿠키", 0, new String[] {"", "&a&o미래에 대해 조언을 해드립니다 :o"});
	public static ItemStack BEEF_JERKY = new CustomItem(Material.COOKED_BEEF, "&6육포", 0, new String[] {"", "&a&o포화"});
	public static ItemStack MAGIC_SUGAR = new CustomItem(Material.SUGAR, "&6마법의 설탕", 0, new String[] {"", "&a&o헤르메스의 힘을 느껴보세요!"});
	public static ItemStack MONSTER_JERKY = new CustomItem(Material.ROTTEN_FLESH, "&6괴물 육포", 0, new String[] {"", "&a&o더 이상 배고프지 않아도 됩니다"});
	public static ItemStack APPLE_JUICE = new CustomPotion("&c사과 주스", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
	public static ItemStack MELON_JUICE = new CustomPotion("&c멜론 주스", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
	public static ItemStack CARROT_JUICE = new CustomPotion("&6당근 주스", 8195, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
	public static ItemStack PUMPKIN_JUICE = new CustomPotion("&6호박 주스", 8195, new String[0], new PotionEffect(PotionEffectType.SATURATION, 10, 0));
	public static ItemStack GOLDE_APPLE_JUICE = new CustomPotion("&b황금 사과 주스", 8195, new String[0], new PotionEffect(PotionEffectType.ABSORPTION, 20 * 20, 0));
	
	/*		Christmas		*/
	public static ItemStack MILK = new CustomPotion("&6우유 한 잔", 8194, new String[0], new PotionEffect(PotionEffectType.SATURATION, 5, 0));
	public static ItemStack CHOCOLATE_MILK = new CustomPotion("&6초콜릿 우유", 8201, new String[0], new PotionEffect(PotionEffectType.SATURATION, 12, 0));
	public static ItemStack EGG_NOG = new CustomPotion("&a에그노그", 8194, new String[0], new PotionEffect(PotionEffectType.SATURATION, 7, 0));
	public static ItemStack APPLE_CIDER = new CustomPotion("&c사과 사이다", 8197, new String[0], new PotionEffect(PotionEffectType.SATURATION, 14, 0));
	public static ItemStack CHRISTMAS_COOKIE = new CustomItem(Material.COOKIE, Christmas.color("크리스마스 쿠키"), 0);
	public static ItemStack FRUIT_CAKE = new CustomItem(Material.PUMPKIN_PIE, Christmas.color("과일 케이크"), 0);
	public static ItemStack APPLE_PIE = new CustomItem(Material.PUMPKIN_PIE, "&rApple Pie", 0);
	public static ItemStack HOT_CHOCOLATE = new CustomPotion("&6Hot Chocolate", 8201, new String[0], new PotionEffect(PotionEffectType.SATURATION, 14, 0));
	public static ItemStack CHRISTMAS_CAKE = new CustomItem(Material.PUMPKIN_PIE, Christmas.color("크리스마스 케이크"), 0);
	public static ItemStack CARAMEL = new CustomItem(Material.CLAY_BRICK, "&6캐러멜", 0);
	public static ItemStack CARAMEL_APPLE = new CustomItem(Material.APPLE, "&6캐러멜 사과", 0);
	public static ItemStack CHOCOLATE_APPLE = new CustomItem(Material.APPLE, "&6초콜릿 사과", 0);
	public static ItemStack PRESENT = new CustomItem(Material.CHEST, Christmas.color("크리스마스 선물"), 0, new String[] {"&7보내는 이: &emrCookieSlime", "&7받는 이: &e당신", "", "&e우 클릭 &7으로 열 수 있습니다"});
	
	/*		Easter			*/
	public static ItemStack EASTER_EGG = new CustomItem(Material.EGG, "&r이스터 에그", 0, new String[] {"&b서프라이즈! 서프라이즈!"});
	public static ItemStack CARROT_PIE = new CustomItem(Material.PUMPKIN_PIE, "&6당근 파이", 0);
	
	/*		 Weapons 		*/
	public static ItemStack GRANDMAS_WALKING_STICK = new CustomItem(Material.STICK, "&7할머니 지팡이", 0, new String[0], new String[] {"KNOCKBACK-2"});
	public static ItemStack GRANDPAS_WALKING_STICK = new CustomItem(Material.STICK, "&7할아버지 지팡이", 0, new String[0], new String[] {"KNOCKBACK-5"});
	public static ItemStack SWORD_OF_BEHEADING = new CustomItem(Material.IRON_SWORD, "&6참수의 검", 0, new String[] {"&7참수 II", "", "&r목을 베어 머리를 얻을 수 있습니다", "&r(위더 스켈레톤에게 더 높은 확률로 얻을 수 있습니다)"});
	public static ItemStack BLADE_OF_VAMPIRES = new CustomItem(Material.GOLD_SWORD, "&c흡혈의 검", 0, new String[] {"&7흡혈 I", "", "&r생명체를 때릴 때마다", "&r45% 의 확률로", "&r하트 2칸을 채울 수 있습니다"}, new String[] {"FIRE_ASPECT-2", "DURABILITY-4", "DAMAGE_ALL-2"});
	public static ItemStack SEISMIC_AXE = new CustomItem(Material.IRON_AXE, "&a지진 도끼", 0, new String[] {"", "&7&o지진을 일으킬 수 있습니다", "", "&e우클릭 &7으로 사용할 수 있습니다"});
	
	/*		Bows		*/
	public static ItemStack EXPLOSIVE_BOW = new CustomItem(Material.BOW, "&c폭발적인 활", 0, new String[] {"&r이 활을 발사하여 적에게", "&r명중하면 폭발을 일으킵니다"});
	public static ItemStack ICY_BOW = new CustomItem(Material.BOW, "&b얼음 활", 0, new String[] {"&r이 활을 발사하여 적에게", "&r명중하면 적이 2초 동안 움직이지 못합니다"});
	
	/*		 Tools		*/
	public static ItemStack AUTO_SMELT_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, "&6제련소의 곡괭이", 0, new String[] {"&c&l자동 제련 기능이 있습니다", "", "&9행운이 적용됩니다"});
	public static ItemStack LUMBER_AXE = new CustomItem(Material.DIAMOND_AXE, "&6목재 도끼", 0, new String[] {"&a&o찹트리가 가능한 도끼"});
	public static ItemStack PICKAXE_OF_CONTAINMENT = new CustomItem(Material.IRON_PICKAXE, "&c봉쇄의 곡괭이", 0, new String[] {"", "&9스포너를 캘 수 있습니다"});
	public static ItemStack HERCULES_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "&9헤라클레스 곡괭이", 0, new String[] {"", "&r강력한 힘으로 광석을 내려칩니다", "&r광석이 아주 가루가 됩니다"}, new String[] {"DURABILITY-2", "DIG_SPEED-4"});
	public static ItemStack EXPLOSIVE_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, "&e폭발적인 곡괭이", 0, new String[] {"", "&r폭발적인 힘으로 폭발적인 채굴을 할 수 있습니다", "", "&9행운이 적용됩니다"});
	public static ItemStack PICKAXE_OF_THE_SEEKER = new CustomItem(Material.DIAMOND_PICKAXE, "&a시커의 곡괭이", 0, new String[] {"&r가장 가까운 광석을 가리킵니다", "&r광석을 가르킬 때 마다 내구도가 소비됩니다", "", "&7&e우 클릭&7 으로 가까운 광석을 찾습니다"});
	public static ItemStack COBALT_PICKAXE = new CustomItem(Material.IRON_PICKAXE, "&9코발트 곡괭이", 0, new String[] {}, new String[] {"DURABILITY-3", "DIG_SPEED-6"});
	public static ItemStack PICKAXE_OF_VEIN_MINING = new CustomItem(Material.DIAMOND_PICKAXE, "&e정맥 채취의 곡괭이", 0, new String[] {"", "&r이 곡괭이는 광물의 전체", "&r정맥을 발굴할 것 입니다"});
	
	/*		 Armor 		*/
	public static ItemStack GLOWSTONE_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&e&l발광석 모자", 0, new String[] {"", "&a&o태양처럼 반짝!", "", "&9+ 야간 투시"}), Color.YELLOW);
	public static ItemStack GLOWSTONE_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&e&l발광석 조끼", 0, new String[] {"", "&a&o태양처럼 반짝!", "", "&9+ 야간 투시"}), Color.YELLOW);
	public static ItemStack GLOWSTONE_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&e&l발광석 바지", 0, new String[] {"", "&a&o태양처럼 반짝!", "", "&9+ 야간 투시"}), Color.YELLOW);
	public static ItemStack GLOWSTONE_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&e&l발광석 부츠", 0, new String[] {"", "&a&o태양처럼 반짝!", "", "&9+ 야간 투시"}), Color.YELLOW);
	public static ItemStack ENDER_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&5&l엔더 모자", 0, new String[] {"", "&a&o어디에나 있고 어디에도 없다"}), Color.fromRGB(28, 25, 112));
	public static ItemStack ENDER_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&5&l엔더 조끼", 0, new String[] {"", "&a&o어디에나 있고 어디에도 없다"}), Color.fromRGB(28, 25, 112));
	public static ItemStack ENDER_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&5&l엔더 바지", 0, new String[] {"", "&a&o어디에나 있고 어디에도 없다"}), Color.fromRGB(28, 25, 112));
	public static ItemStack ENDER_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&5&l엔더 부츠", 0, new String[] {"", "&a&o어디에나 있고 어디에도 없다", "" , "&9+ 엔더 진주 피해 없음"}), Color.fromRGB(28, 25, 112));
	public static ItemStack SLIME_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&a&l슬라임 모자", 0, new String[] {"", "&a&o탄력있는 느낌"}), Color.LIME);
	public static ItemStack SLIME_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&a&l슬라임 조끼", 0, new String[] {"", "&a&o탄력있는 느낌"}), Color.LIME);
	public static ItemStack SLIME_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&a&l슬라임 바지", 0, new String[] {"", "&a&o탄력있는 느낌", "", "&9+ 속도"}), Color.LIME);
	public static ItemStack SLIME_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&a&l슬라임 부츠", 0, new String[] {"", "&a&o탄력있는 느낌", "", "&9+ 점프 강화", "&9+ 낙하 피해 없음"}), Color.LIME);
	public static ItemStack CACTUS_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&2선인장 모자", 0, new String[0], new String[] {"THORNS-3", "DURABILITY-5"}), Color.GREEN);
	public static ItemStack CACTUS_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&2선인장 조끼", 0, new String[0], new String[] {"THORNS-3", "DURABILITY-5"}), Color.GREEN);
	public static ItemStack CACTUS_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&2선인장 바지", 0, new String[0], new String[] {"THORNS-3", "DURABILITY-5"}), Color.GREEN);
	public static ItemStack CACTUS_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&2선인장 부츠", 0, new String[0], new String[] {"THORNS-3", "DURABILITY-5"}), Color.GREEN);
	public static ItemStack DAMASCUS_STEEL_HELMET = new CustomItem(Material.IRON_HELMET, "&7다마스커스 강철 투구", new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4"}, 0);
	public static ItemStack DAMASCUS_STEEL_CHESTPLATE = new CustomItem(Material.IRON_CHESTPLATE, "&7다마스커스 강철 흉갑", new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4"}, 0);
	public static ItemStack DAMASCUS_STEEL_LEGGINGS = new CustomItem(Material.IRON_LEGGINGS, "&7다마스커스 강철 각반", new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4"}, 0);
	public static ItemStack DAMASCUS_STEEL_BOOTS = new CustomItem(Material.IRON_BOOTS, "&7다마스커스 강철 부츠", new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-4"}, 0);
	public static ItemStack REINFORCED_ALLOY_HELMET = new CustomItem(Material.IRON_HELMET, "&b강화된 투구", new String[] {"DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9"}, 0);
	public static ItemStack REINFORCED_ALLOY_CHESTPLATE = new CustomItem(Material.IRON_CHESTPLATE, "&b강화된 흉갑", new String[] {"DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9"}, 0);
	public static ItemStack REINFORCED_ALLOY_LEGGINGS = new CustomItem(Material.IRON_LEGGINGS, "&b강화된 각반", new String[] {"DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9"}, 0);
	public static ItemStack REINFORCED_ALLOY_BOOTS = new CustomItem(Material.IRON_BOOTS, "&b강화된 부츠", new String[] {"DURABILITY-9", "PROTECTION_ENVIRONMENTAL-9"}, 0);
	public static ItemStack SCUBA_HELMET = new CustomArmor(new CustomItem(Material.LEATHER_HELMET, "&c스쿠버 헬멧", 0, new String[] {"", "&b수중 호흡이 가능합니다", "&4&o방호복 슈트의 일부"}), Color.ORANGE);
	public static ItemStack HAZMATSUIT_CHESTPLATE = new CustomArmor(new CustomItem(Material.LEATHER_CHESTPLATE, "&c방화복 상의", 0, new String[] {"", "&b불 위를 걸을 수 있습니다", "&4&o방호복 슈트의 일부"}), Color.ORANGE);
	public static ItemStack HAZMATSUIT_LEGGINGS = new CustomArmor(new CustomItem(Material.LEATHER_LEGGINGS, "&c방화복 하의", 0, new String[] {"", "&4&o방호복 슈트의 일부"}), Color.ORANGE);
	public static ItemStack RUBBER_BOOTS = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&c고무 장화", 0, new String[] {"", "&4&o방호복 슈트의 일부"}), Color.BLACK);
	public static ItemStack GILDED_IRON_HELMET = new CustomItem(Material.GOLD_HELMET, "&6도금된 철 투구", new String[] {"DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8"}, 0);
	public static ItemStack GILDED_IRON_CHESTPLATE = new CustomItem(Material.GOLD_CHESTPLATE, "&6도금된 철 흉갑", new String[] {"DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8"}, 0);
	public static ItemStack GILDED_IRON_LEGGINGS = new CustomItem(Material.GOLD_LEGGINGS, "&6도금된 철 각반", new String[] {"DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8"}, 0);
	public static ItemStack GILDED_IRON_BOOTS = new CustomItem(Material.GOLD_BOOTS, "&6도금된 철 부츠", new String[] {"DURABILITY-6", "PROTECTION_ENVIRONMENTAL-8"}, 0);
	public static ItemStack GOLD_HELMET = new CustomItem(Material.GOLD_HELMET, "&6금 투구", 0, new String[] {"&912-캐럿"}, new String[] {"DURABILITY-10"});
	public static ItemStack GOLD_CHESTPLATE = new CustomItem(Material.GOLD_CHESTPLATE, "&6금 흉갑", 0, new String[] {"&912-캐럿"}, new String[] {"DURABILITY-10"});
	public static ItemStack GOLD_LEGGINGS = new CustomItem(Material.GOLD_LEGGINGS, "&6금 각반", 0, new String[] {"&912-캐럿"}, new String[] {"DURABILITY-10"});
	public static ItemStack GOLD_BOOTS = new CustomItem(Material.GOLD_BOOTS, "&6금 부츠", 0, new String[] {"&912-캐럿"}, new String[] {"DURABILITY-10"});
	public static ItemStack SLIME_HELMET_STEEL = new CustomItem(Material.IRON_HELMET, "&a&l슬라임 투구", 0, new String[] {"&7&o강화됨", "", "&a&o탄력있는 느낌"}, new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2"});
	public static ItemStack SLIME_CHESTPLATE_STEEL = new CustomItem(Material.IRON_CHESTPLATE, "&a&l슬라임 흉갑", 0, new String[] {"&7&o강화됨", "", "&a&o탄력있는 느낌"}, new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2"});
	public static ItemStack SLIME_LEGGINGS_STEEL = new CustomItem(Material.IRON_LEGGINGS, "&a&l슬라임 각반", 0, new String[] {"&7&o강화됨", "", "&a&o탄력있는 느낌", "", "&9+ 속도"}, new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2"});
	public static ItemStack SLIME_BOOTS_STEEL = new CustomItem(Material.IRON_BOOTS, "&a&l슬라임 부츠", 0, new String[] {"&7&o강화됨", "", "&a&o탄력있는 느낌", "", "&9+ 점프 강화", "&9+ 낙하 피해 없음"}, new String[] {"DURABILITY-4", "PROTECTION_ENVIRONMENTAL-2"});
	public static ItemStack BOOTS_OF_THE_STOMPER = new CustomArmor(new CustomItem(Material.LEATHER_BOOTS, "&b폭풍의 장화", 0, new String[] {"", "&9낙하 피해를 받지 않습니다", "&9몹 / 플레이어 에게 광역 피해를 입힙니다", "", "&9+ 낙하 피해 없음"}), Color.AQUA);
	public static ItemStack HEAVY_METAL_HELMET = new CustomItem(Material.IRON_HELMET, "&c중무장 투구", 0, new String[] {"", "&9+ 힘", "&9+ 구속"}, new String[] {"DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10"});
	public static ItemStack HEAVY_METAL_CHESTPLATE = new CustomItem(Material.IRON_CHESTPLATE, "&c중무장 흉갑", 0, new String[] {"", "&9+ 힘", "&9+ 구속"}, new String[] {"DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10"});
	public static ItemStack HEAVY_METAL_LEGGINGS = new CustomItem(Material.IRON_LEGGINGS, "&c중무장 각반", 0, new String[] {"", "&9+ 힘", "&9+ 구속"}, new String[] {"DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10"});
	public static ItemStack HEAVY_METAL_BOOTS = new CustomItem(Material.IRON_BOOTS, "&c중무장 부츠", 0, new String[] {"", "&9+ 힘", "&9+ 구속"}, new String[] {"DURABILITY-10", "PROTECTION_ENVIRONMENTAL-10"});
	
	/*		 Misc 		*/
	public static ItemStack MAGIC_LUMP_1 = new CustomItem(Material.GOLD_NUGGET, "&6마법의 덩어리 &7- &eI", 0, new String[] {"", "&c&o등급: I"});
	public static ItemStack MAGIC_LUMP_2 = new CustomItem(Material.GOLD_NUGGET, "&6마법의 덩어리 &7- &eII", 0, new String[] {"", "&c&o등급: II"});
	public static ItemStack MAGIC_LUMP_3 = new CustomItem(Material.GOLD_NUGGET, "&6마법의 덩어리 &7- &eIII", 0, new String[] {"", "&c&o등급: III"});
	public static ItemStack ENDER_LUMP_1 = new CustomItem(Material.GOLD_NUGGET, "&5엔더 덩어리 &7- &eI", 0, new String[] {"", "&c&o등급: I"});
	public static ItemStack ENDER_LUMP_2 = new CustomItem(Material.GOLD_NUGGET, "&5엔더 덩어리 &7- &eII", 0, new String[] {"", "&c&o등급: II"});
	public static ItemStack ENDER_LUMP_3 = new CustomItem(Material.GOLD_NUGGET, "&5엔더 덩어리 &7- &eIII", 0, new String[] {"", "&c&o등급: III"});
	public static ItemStack MAGICAL_BOOK_COVER = new CustomItem(Material.PAPER, "&6마법의 책 표지", 0, new String[] {"", "&a&o다양한 마법의 책에 사용됩니다"});
	public static ItemStack BASIC_CIRCUIT_BOARD = new CustomItem(Material.ACTIVATOR_RAIL, "&b기본 회로 기판", 0);
	public static ItemStack ADVANCED_CIRCUIT_BOARD = new CustomItem(Material.POWERED_RAIL, "&b고급 회로 기판", 0);
	public static ItemStack WHEAT_FLOUR = new CustomItem(Material.SUGAR, "&r밀가루", 0);
	public static ItemStack STEEL_PLATE = new CustomItem(Material.PAPER, "&7&l강판", 0);
	public static ItemStack COMPRESSED_CARBON = null;
	public static ItemStack BATTERY = null;
	public static ItemStack CARBON_CHUNK = null;
	public static ItemStack STEEL_THRUSTER = new CustomItem(Material.BUCKET, "&7&l강철 추진기", 0);
	public static ItemStack POWER_CRYSTAL = null;
	public static ItemStack CHAIN = new CustomItem(Material.STRING, "&b체인", 0);
	public static ItemStack HOOK = new CustomItem(Material.FLINT, "&b갈고리", 0);
	public static ItemStack SIFTED_ORE = new CustomItem(Material.SULPHUR, "&6선별 된 광석", 0);
	public static ItemStack STONE_CHUNK = null;
	public static ItemStack LAVA_CRYSTAL = null;
	public static ItemStack SALT = new CustomItem(Material.SUGAR, "&r소금", 0);
	public static ItemStack BUTTER = null;
	public static ItemStack CHEESE = null;
	public static ItemStack HEAVY_CREAM = new CustomItem(Material.SNOW_BALL, "&r헤비 크림", 0);
	public static ItemStack CRUSHED_ORE = new CustomItem(Material.SULPHUR, "&6분쇄 된 광석 &7- &eI", 0);
	public static ItemStack PULVERIZED_ORE = new CustomItem(Material.SULPHUR, "&6분쇄 된 광석 &7- &eII", 0);
	public static ItemStack PURE_ORE_CLUSTER = new CustomItem(Material.SULPHUR, "&6순수한 광석 클러스터", 0);
	public static ItemStack TINY_URANIUM = null;
	public static ItemStack SMALL_URANIUM = null;
	public static ItemStack MAGNET = null;
	public static ItemStack NECROTIC_SKULL = new CustomItem(new MaterialData(Material.SKULL_ITEM, (byte) 1).toItemStack(1), "&c괴사된 머리");
	public static ItemStack ESSENCE_OF_AFTERLIFE = new CustomItem(Material.SULPHUR, "&4사후의 정수", 0);
	public static ItemStack ELECTRO_MAGNET = null;
	public static ItemStack HEATING_COIL = null;
	public static ItemStack COOLING_UNIT = null;
	public static ItemStack ELECTRIC_MOTOR = null;
	public static ItemStack CARGO_MOTOR = null;
	public static ItemStack SCROLL_OF_DIMENSIONAL_TELEPOSITION = new CustomItem(Material.PAPER, "&6차원의 두루마리", 0, new String[] {"", "&c이 두루마리로", "&c블랙홀을 만들 수 있습니다", "&c근처에 있는 생명체들은", "&c헤어나오지 못할 것 입니다"});
	public static ItemStack TOME_OF_KNOWLEDGE_SHARING = new CustomItem(Material.BOOK, "&6연구서", 0, new String[] {"&7주인: &b없음", "", "&e우 클릭 &7으로 연구서를 기록할 수 있습니다", "", "", "&e우 클릭 &7으로 연구 기록을 볼 수 있습니다", "&7주인이 연구한 것 까지만 알 수 있습니다"});
	public static ItemStack HARDENED_GLASS = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 8), "&7강화 유리", "", "&r폭발에 견딜 수 있습니다");
	public static ItemStack WITHER_PROOF_OBSIDIAN = new CustomItem(Material.OBSIDIAN, "&5시들지 않는 흑요석", 0, new String[] {"", "&r폭발에 견딜 수 있습니다", "&r위더에게 견딜 수 있습니다"});
	public static ItemStack WITHER_PROOF_GLASS = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 15), "&5시들지 않는 유리", "", "&r폭발에 견딜 수 있습니다", "&r위더에게 견딜 수 있습니다");
	public static ItemStack REINFORCED_PLATE = new CustomItem(Material.PAPER, "&7강화 플레이트", 0);
	public static ItemStack ANCIENT_PEDESTAL = new CustomItem(Material.DISPENSER, "&d고대의 받침대", 0, new String[] {"", "&5고대의 제단 일부"});
    public static ItemStack ANCIENT_ALTAR = new CustomItem(Material.ENCHANTMENT_TABLE, "&d고대의 제단", 0, new String[] {"", "&5건축식 입니다", "&5마법의 제작을 위해 필요한 제단"});
	public static ItemStack DUCT_TAPE = null;
	
    public static ItemStack RAINBOW_WOOL = new CustomItem(new MaterialData(Material.WOOL), "&5무지개 양털", "", "&d무지개의 모든 색상을 따라 순환합니다!");
    public static ItemStack RAINBOW_GLASS = new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5무지개 유리", "", "&d무지개의 모든 색상을 따라 순환합니다!");
    public static ItemStack RAINBOW_CLAY = new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5무지개 점토", "", "&d무지개의 모든 색상을 따라 순환합니다!");
    public static ItemStack RAINBOW_GLASS_PANE = new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5무지개 유리판", "", "&d무지개의 모든 색상을 따라 순환합니다!");
    
    public static ItemStack RAINBOW_WOOL_XMAS = new CustomItem(new MaterialData(Material.WOOL), "&5무지개 양털 &7(크리스마스)", "", Christmas.color("< Christmas Edition >"));
    public static ItemStack RAINBOW_GLASS_XMAS = new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5무지개 유리 &7(크리스마스)", "", Christmas.color("< Christmas Edition >"));
    public static ItemStack RAINBOW_CLAY_XMAS = new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5무지개 점토 &7(크리스마스)", "", Christmas.color("< Christmas Edition >"));
    public static ItemStack RAINBOW_GLASS_PANE_XMAS = new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5무지개 유리판 &7(크리스마스)", "", Christmas.color("< Christmas Edition >"));
    
    public static ItemStack RAINBOW_WOOL_VALENTINE = new CustomItem(new MaterialData(Material.WOOL), "&5무지개 양털 &7(발렌타인 데이)", "", "&d< Valentine's Day Edition >");
    public static ItemStack RAINBOW_GLASS_VALENTINE = new CustomItem(new MaterialData(Material.STAINED_GLASS), "&5무지개 유리 &7(발렌타인 데이)", "", "&d< Valentine's Day Edition >");
    public static ItemStack RAINBOW_CLAY_VALENTINE = new CustomItem(new MaterialData(Material.STAINED_CLAY), "&5무지개 점토 &7(발렌타인 데이)", "", "&d< Valentine's Day Edition >");
    public static ItemStack RAINBOW_GLASS_PANE_VALENTINE = new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE), "&5무지개 유리판 &7(발렌타인 데이)", "", "&d< Valentine's Day Edition >");
    
    /*		 Ingots 		*/
	public static ItemStack COPPER_INGOT = new CustomItem(Material.CLAY_BRICK, "&b구리", 0, new String[0]);
	public static ItemStack TIN_INGOT = new CustomItem(Material.IRON_INGOT, "&b주석", 0, new String[0]);
	public static ItemStack SILVER_INGOT = new CustomItem(Material.IRON_INGOT, "&b은", 0, new String[0]);
	public static ItemStack ALUMINUM_INGOT = new CustomItem(Material.IRON_INGOT, "&b알루미늄", 0, new String[0]);
	public static ItemStack LEAD_INGOT = new CustomItem(Material.IRON_INGOT, "&b납", 0, new String[0]);
	public static ItemStack ZINC_INGOT = new CustomItem(Material.IRON_INGOT, "&b아연", 0, new String[0]);
	public static ItemStack MAGNESIUM_INGOT = new CustomItem(Material.IRON_INGOT, "&b마그네슘", 0, new String[0]);
	
	/*		Alloy (Carbon + Iron)	*/
	public static ItemStack STEEL_INGOT = new CustomItem(Material.IRON_INGOT, "&b강철", 0, new String[0]);
	/*		Alloy (Copper + Tin)	*/
	public static ItemStack BRONZE_INGOT = new CustomItem(Material.CLAY_BRICK, "&b청동", 0, new String[0]);
	/*		Alloy (Copper + Aluminum)	*/
	public static ItemStack DURALUMIN_INGOT = new CustomItem(Material.IRON_INGOT, "&b두랄루민", 0, new String[0]);
	/*		Alloy (Copper + Silver)	*/
	public static ItemStack BILLON_INGOT = new CustomItem(Material.IRON_INGOT, "&b은동", 0, new String[0]);
	/*		Alloy (Copper + Zinc)	*/
	public static ItemStack BRASS_INGOT = new CustomItem(Material.GOLD_INGOT, "&b황동", 0, new String[0]);
	/*		Alloy (Aluminum + Brass)	*/
	public static ItemStack ALUMINUM_BRASS_INGOT = new CustomItem(Material.GOLD_INGOT, "&b알루미늄 황동", 0, new String[0]);
	/*		Alloy (Aluminum + Bronze)	*/
	public static ItemStack ALUMINUM_BRONZE_INGOT = new CustomItem(Material.GOLD_INGOT, "&b알루미늄 청동", 0, new String[0]);
	/*		Alloy (Gold + Silver + Copper)	*/
	public static ItemStack CORINTHIAN_BRONZE_INGOT = new CustomItem(Material.GOLD_INGOT, "&b코린트식 청동", 0, new String[0]);
	/*		Alloy (Lead + Tin)	*/
	public static ItemStack SOLDER_INGOT = new CustomItem(Material.IRON_INGOT, "&b땜납", 0, new String[0]);
	/*		Alloy (Steel + Iron + Carbon)	*/
	public static ItemStack DAMASCUS_STEEL_INGOT = new CustomItem(Material.IRON_INGOT, "&b다마스커스 강철", 0, new String[0]);
	/*		Alloy (Damascus Steel + Duralumin + Compressed Carbon + Aluminium Bronze)	*/
	public static ItemStack HARDENED_METAL_INGOT = new CustomItem(Material.IRON_INGOT, "&b&l경화 금속", 0);
	/*		Alloy (Hardened Metal + Corinthian Bronze + Solder + Billon + Damascus Steel)	*/
	public static ItemStack REINFORCED_ALLOY_INGOT = new CustomItem(Material.IRON_INGOT, "&b&l강화 합금", 0);
	/*		Alloy (Iron + Silicon)		*/
	public static ItemStack FERROSILICON = new CustomItem(Material.IRON_INGOT, "&b페로실리콘" , 0);
	/*		Alloy (Iron + Gold)			*/
	public static ItemStack GILDED_IRON = new CustomItem(Material.GOLD_INGOT, "&6&l도금한 철", 0);	
	/*		Alloy (Redston + Ferrosilicon)	*/
	public static ItemStack REDSTONE_ALLOY = new CustomItem(Material.CLAY_BRICK, "&c레드스톤 합금", 0);
	/*		Alloy (Iron + Copper)		*/
	public static ItemStack NICKEL_INGOT = new CustomItem(Material.IRON_INGOT, "&b니켈" , 0);
	/*		Alloy (Nickel + Iron + Copper)		*/
	public static ItemStack COBALT_INGOT = new CustomItem(Material.IRON_INGOT, "&9코발트" , 0);
	
	/*		Gold		*/
	public static ItemStack GOLD_4K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(4-캐럿)", 0);
	public static ItemStack GOLD_6K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(6-캐럿)", 0);
	public static ItemStack GOLD_8K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(8-캐럿)", 0);
	public static ItemStack GOLD_10K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(10-캐럿)", 0);
	public static ItemStack GOLD_12K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(12-캐럿)", 0);
	public static ItemStack GOLD_14K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(14-캐럿)", 0);
	public static ItemStack GOLD_16K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(16-캐럿)", 0);
	public static ItemStack GOLD_18K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(18-캐럿)", 0);
	public static ItemStack GOLD_20K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(20-캐럿)", 0);
	public static ItemStack GOLD_22K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(22-캐럿)", 0);
	public static ItemStack GOLD_24K = new CustomItem(Material.GOLD_INGOT, "&r금괴 &7(24-캐럿)", 0);
	
	/*		 Dusts 		*/
	public static ItemStack IRON_DUST = new CustomItem(Material.SULPHUR, "&6철 가루", 0);
	public static ItemStack GOLD_DUST = new CustomItem(Material.GLOWSTONE_DUST, "&6금 가루", 0);
	public static ItemStack TIN_DUST = new CustomItem(Material.SUGAR, "&6주석 가루", 0);
	public static ItemStack COPPER_DUST = new CustomItem(Material.GLOWSTONE_DUST, "&6구리 가루", 0);
	public static ItemStack SILVER_DUST = new CustomItem(Material.SUGAR, "&6은 가루", 0);
	public static ItemStack ALUMINUM_DUST = new CustomItem(Material.SUGAR, "&6알루미늄 가루", 0);
	public static ItemStack LEAD_DUST = new CustomItem(Material.SULPHUR, "&6납 가루", 0);
	public static ItemStack SULFATE = new CustomItem(Material.GLOWSTONE_DUST, "&6황산염", 0);
	public static ItemStack ZINC_DUST = new CustomItem(Material.SUGAR, "&6아연 가루", 0);
	public static ItemStack MAGNESIUM_DUST = new CustomItem(Material.SUGAR, "&6마그네슘 가루", 0);
	public static ItemStack CARBON = null;
	public static ItemStack SILICON = new CustomItem(Material.FIREWORK_CHARGE, "&6실리콘", 0);
	public static ItemStack GOLD_24K_BLOCK = new CustomItem(Material.GOLD_BLOCK, "&r금 블럭 &7(24-캐럿)", 0);
	
	/*		 Gems 		*/
	public static ItemStack SYNTHETIC_DIAMOND = new CustomItem(Material.DIAMOND, "&b합성 다이아몬드", 0);
	public static ItemStack SYNTHETIC_SAPPHIRE = new CustomItem(new MaterialData(Material.INK_SACK, (byte) 4), "&b합성 사파이어");
	public static ItemStack SYNTHETIC_EMERALD = new CustomItem(Material.EMERALD, "&b합성 에메랄드", 0);
	public static ItemStack CARBONADO = null;
	public static ItemStack RAW_CARBONADO = null;
	public static ItemStack URANIUM = null;
	public static ItemStack NEPTUNIUM = null;
	public static ItemStack PLUTONIUM = null;
	public static ItemStack BOOSTED_URANIUM = null;
	
	/*		Talisman		*/
	public static ItemStack TALISMAN = new CustomItem(Material.EMERALD, "&6평범한 부적", 0);
	public static ItemStack TALISMAN_ANVIL = new CustomItem(Material.EMERALD, "&a모루의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r한개의 도구가 깨지기 전에 내구도를 채워줍니다", "", "&4&l주의:", "&4너무 복잡한 도구는", "&4이 부적이 작동하지 않습니다", "&4아이템이 수리될 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_MINER = new CustomItem(Material.EMERALD, "&a광부의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r20% 확률로 2 배의 광물을 얻을 수 있습니다"});
	public static ItemStack TALISMAN_HUNTER = new CustomItem(Material.EMERALD, "&a사냥꾼의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&ra 20% 확률로 2 배의 전리품을 얻을 수 잇습니다"});
	public static ItemStack TALISMAN_LAVA = new CustomItem(Material.EMERALD, "&a용암의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r용암에 닿을 경우 화염 저항을 얻습니다", "화염 저항을 얻을 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_WATER = new CustomItem(Material.EMERALD, "&a수중 호흡의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r물에서 숨을 쉬지 못할 경우", "&r수중 호흡을 얻습니다", "&r수중 호흡을 얻을 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_ANGEL = new CustomItem(Material.EMERALD, "&a천사의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r75% 확률로 낙하 피해를 입지 않습니다"});
	public static ItemStack TALISMAN_FIRE = new CustomItem(Material.EMERALD, "&a소방관의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r화상을 입을 경우 화염 저항을 얻습니다", "&r화염 저항을 얻을 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_MAGICIAN = new CustomItem(Material.EMERALD, "&a마술사의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r80% 확률로 행운 보너스 마법 부여가 적용됩니다"});
	public static ItemStack TALISMAN_TRAVELLER = new CustomItem(Material.EMERALD, "&a여행자의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r60% 확률로 달릴 때 속도 증가를 얻습니다"});
	public static ItemStack TALISMAN_WARRIOR = new CustomItem(Material.EMERALD, "&a전사의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r피해를 입을 경우 힘 III 을 얻습니다", "&r힘을 얻을 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_KNIGHT = new CustomItem(Material.EMERALD, "&a나이트의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r30% 확률로 5초 동안 재생을 얻습니다", "&r재생을 얻을 때 이 부적은 소모됩니다"});
	public static ItemStack TALISMAN_WHIRLWIND = new CustomItem(Material.EMERALD, "&a회오리 바람의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r60% 확률로 발사체를 튕겨냅니다"});
	public static ItemStack TALISMAN_WIZARD = new CustomItem(Material.EMERALD, "&a마법사의 부적", 0, new String[] {"", "&r이 부적의 효과는 다음과 같습니다", "&r인벤토리에 소지시", "&r행운 마법 부여의 4 또는 5 를 얻을 수 있지만", "&r다른 마법 부여를 감소시킬 수도 있습니다"});
	
	/*		Staves		*/
	public static ItemStack STAFF_ELEMENTAL = new CustomItem(Material.STICK, "&6정령의 지팡이", 0);
	public static ItemStack STAFF_WIND = new CustomItem(Material.STICK, "&6정령의 지팡이 &7- &b&o바람", 0, new String[] {"", "&7원소: &b&o바람", "", "&e우 클릭 &7으로 자신을 바람에 실을 수 있습니다"}, new String[] {"LUCK-1"});
	public static ItemStack STAFF_FIRE = new CustomItem(Material.STICK, "&6정령의 지팡이 &7- &c&o불", 0, new String[] {"", "&7원소: &c&o불"}, new String[] {"FIRE_ASPECT-5"});
	public static ItemStack STAFF_WATER = new CustomItem(Material.STICK, "&6정령의 지팡이 &7- &1&o물", 0, new String[] {"", "&7원소: &1&o물", "", "&7&e우 클릭 &7으로 자신을 화재에서 벗어나게 할 수 잇습니다"}, new String[] {"WATER_WORKER-1"});
	
	/*		 Machines 		*/
	public static ItemStack GRIND_STONE = new CustomItem(Material.DISPENSER, "&b숫돌", 0, new String[] {"", "&a&o아이템을 효율적으로 갈을 수 있습니다"});
	public static ItemStack ARMOR_FORGE = new CustomItem(Material.ANVIL, "&6화덕", 0, new String[] {"", "&a&o강력한 방어구를 만드는데에 사용합니다"});
	public static ItemStack SMELTERY = new CustomItem(Material.FURNACE, "&6제련소", 0, new String[] {"", "&a&o용광로로 사용하며 녹일 수 있습니다"});
	public static ItemStack ORE_CRUSHER = new CustomItem(Material.DISPENSER, "&b광석 분쇄기", 0, new String[] {"", "&a&o광석을 잘게 부십니다"});
	public static ItemStack COMPRESSOR = new CustomItem(Material.PISTON_BASE, "&b압축기", 0, new String[] {"", "&a&o아이템을 압축 시킬 수 있습니다"});
	public static ItemStack PRESSURE_CHAMBER = new CustomItem(Material.GLASS, "&b압력 챔버", 0, new String[] {"", "&a&o아이템을 더 압축 시킬 수 있습니다"});
	public static ItemStack MAGIC_WORKBENCH = new CustomItem(Material.WORKBENCH, "&6마법의 제작대", 0, new String[] {"", "&a&o신비한 힘이 흐르고 있는 제작대"});
	public static ItemStack ORE_WASHER = new CustomItem(Material.CAULDRON_ITEM, "&6광물 세척기", 0, new String[] {"", "&a&o광물을 세척할 수 있습니다", "&a&o작은 돌 덩어리를 얻을 수 있습니다"});
	public static ItemStack SAW_MILL = new CustomItem(Material.IRON_FENCE, "&6제재소", 0, new String[] {"", "&a&o나무 1개로 8개의 목재로 만들 수 있습니다"});
	public static ItemStack COMPOSTER = new CustomItem(Material.CAULDRON_ITEM, "&a발효기", 0, new String[] {"", "&a&o시간이 지남에 따라 다양한 재료로 변환할 수 있습니다"});
	public static ItemStack ENHANCED_CRAFTING_TABLE = new CustomItem(Material.WORKBENCH, "&e강화된 제작대", 0, new String[] {"", "&a&o평범한 제작대로는 강력한 힘을 담기 어렵습니다", "&a&o강력한 힘을 담기 위해 강화된 조합대가 있습니다"});
	public static ItemStack CRUCIBLE = new CustomItem(Material.CAULDRON_ITEM, "&c도가니", 0, new String[] {"", "&a&o아이템을 액체로 옮기기 위해 사용됩니다"});
	public static ItemStack JUICER = new CustomItem(Material.GLASS_BOTTLE, "&a과즙기", 0, new String[] {"", "&a&o음료를 만들 수 있습니다"});
	
	public static ItemStack SOLAR_PANEL = new CustomItem(Material.DAYLIGHT_DETECTOR, "&b태양 전기 패널", 0, new String[] {"", "&a&o햇빛을 에너지로 변환합니다"});
	public static ItemStack SOLAR_ARRAY = new CustomItem(Material.DAYLIGHT_DETECTOR, "&b태양 전지판", 0, new String[] {"", "&a&o햇빛을 에너지로 변환합니다"});
	public static ItemStack DIGITAL_MINER = new CustomItem(Material.IRON_PICKAXE, "&b채굴기", 0, new String[] {"", "&a&o모든 것들을 파낼 수 있습니다"});
	public static ItemStack ADVANCED_DIGITAL_MINER = new CustomItem(Material.DIAMOND_PICKAXE, "&6고급 채굴기", 0, new String[] {"", "&a&o모든 것을 파낼 수 있습니다", "&a&o자동으로 광물을 분쇄합니다"});
	public static ItemStack AUTOMATED_PANNING_MACHINE = new CustomItem(Material.BOWL, "&a자동화 패닝 머신", 0, new String[] {"", "&a&o골드 팬의 건축식 버전입니다"});

	public static ItemStack HOLOGRAM_PROJECTOR = new CustomItem(new MaterialData(Material.STEP, (byte) 7), "&b홀로그램 프로젝터", "", "&r편집 가능한 홀로그램을 투사합니다");
	
	/*		 Enhanced Furnaces 		*/
	public static ItemStack ENHANCED_FURNACE = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eI", 0, new String[] {"", "&7처리 속도: &e1x", "&7연료 효율: &e1x", "&7행운 배율: &e1x"});
	public static ItemStack ENHANCED_FURNACE_2 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eII", 0, new String[] {"", "&7처리 속도: &e2x", "&7연료 효율: &e1x", "&7행운 배율: &e1x"});
	public static ItemStack ENHANCED_FURNACE_3 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eIII", 0, new String[] {"", "&7처리 속도: &e2x", "&7연료 효율: &e2x", "&7행운 배율: &e1x"});
	public static ItemStack ENHANCED_FURNACE_4 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eIV", 0, new String[] {"", "&7처리 속도: &e3x", "&7연료 효율: &e2x", "&7행운 배율: &e1x"});
	public static ItemStack ENHANCED_FURNACE_5 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eV", 0, new String[] {"", "&7처리 속도: &e3x", "&7연료 효율: &e2x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_6 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eVI", 0, new String[] {"", "&7처리 속도: &e3x", "&7연료 효율: &e3x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_7 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eVII", 0, new String[] {"", "&7처리 속도: &e4x", "&7연료 효율: &e3x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_8 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eVIII", 0, new String[] {"", "&7처리 속도: &e4x", "&7연료 효율: &e4x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_9 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eIX", 0, new String[] {"", "&7처리 속도: &e5x", "&7연료 효율: &e4x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_10 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eX", 0, new String[] {"", "&7처리 속도: &e5x", "&7연료 효율: &e5x", "&7행운 배율: &e2x"});
	public static ItemStack ENHANCED_FURNACE_11 = new CustomItem(Material.FURNACE, "&7강화된 화로 - &eXI", 0, new String[] {"", "&7처리 속도: &e5x", "&7연료 효율: &e5x", "&7행운 배율: &e3x"});
	public static ItemStack REINFORCED_FURNACE = new CustomItem(Material.FURNACE, "&7강화된 화로 - XII", 0, new String[] {"", "&7처리 속도: &e10x", "&7연료 효율: &e10x", "&7행운 배율: &e3x"});
	public static ItemStack CARBONADO_EDGED_FURNACE = new CustomItem(Material.FURNACE, "&7카르보나두 화로", 0, new String[] {"", "&7처리 속도: &e20x", "&7연료 효율: &e10x", "&7행운 배율: &e3x"});
	
	public static ItemStack BLOCK_PLACER = new CustomItem(Material.DISPENSER, "&a블럭 배치자", 0, new String[] {"", "&r발사기에 있는 모든 블럭을", "&r자동으로 블럭을 배치합니다"});
	
	/*		Soulbound Items		*/
	public static ItemStack SOULBOUND_SWORD = new CustomItem(Material.DIAMOND_SWORD, "&c귀속된 검", 0);
	public static ItemStack SOULBOUND_BOW = new CustomItem(Material.BOW, "&c귀속된 활", 0);
	public static ItemStack SOULBOUND_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, "&c귀속된 곡괭이", 0);
	public static ItemStack SOULBOUND_AXE = new CustomItem(Material.DIAMOND_AXE, "&c귀속된 도끼", 0);
	public static ItemStack SOULBOUND_SHOVEL = new CustomItem(Material.DIAMOND_SPADE, "&c귀속된 삽", 0);
	public static ItemStack SOULBOUND_HOE = new CustomItem(Material.DIAMOND_HOE, "&c귀속된 괭이", 0);
	
	public static ItemStack SOULBOUND_HELMET = new CustomItem(Material.DIAMOND_HELMET, "&c귀속된 헬멧", 0);
	public static ItemStack SOULBOUND_CHESTPLATE = new CustomItem(Material.DIAMOND_CHESTPLATE, "&c귀속된 흉갑", 0);
	public static ItemStack SOULBOUND_LEGGINGS = new CustomItem(Material.DIAMOND_LEGGINGS, "&c귀속된 각반", 0);
	public static ItemStack SOULBOUND_BOOTS = new CustomItem(Material.DIAMOND_BOOTS, "&c귀속된 부츠", 0);
	
	/*		Runes				*/
	public static ItemStack BLANK_RUNE = null;
	public static ItemStack RUNE_AIR = null;
	public static ItemStack RUNE_WATER = null;
	public static ItemStack RUNE_FIRE = null;
	public static ItemStack RUNE_EARTH = null;
	public static ItemStack RUNE_ENDER = null;
	public static ItemStack RUNE_RAINBOW = null;
	
	static {
		ItemStack itemB = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imB = (FireworkEffectMeta) itemB.getItemMeta();
		imB.setEffect(FireworkEffect.builder().with(Type.BURST).with(Type.BURST).withColor(Color.BLACK).build());
		imB.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8비어있는 룬"));
		itemB.setItemMeta(imB);
		BLANK_RUNE = itemB;
		
		ItemStack itemA = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imA = (FireworkEffectMeta) itemA.getItemMeta();
		imA.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.AQUA).build());
		imA.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&b&l공기&8&l]"));
		itemA.setItemMeta(imA);
		RUNE_AIR = itemA;
		
		ItemStack itemW = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imW = (FireworkEffectMeta) itemW.getItemMeta();
		imW.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.BLUE).build());
		imW.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&1&l물&8&l]"));
		itemW.setItemMeta(imW);
		RUNE_WATER = itemW;
		
		ItemStack itemF = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imF = (FireworkEffectMeta) itemF.getItemMeta();
		imF.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.RED).build());
		imF.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&4&l불&8&l]"));
		itemF.setItemMeta(imF);
		RUNE_FIRE = itemF;
		
		ItemStack itemE = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imE = (FireworkEffectMeta) itemE.getItemMeta();
		imE.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.ORANGE).build());
		imE.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&c&l땅&8&l]"));
		itemE.setItemMeta(imE);
		RUNE_EARTH = itemE;
		
		ItemStack itemN = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imN = (FireworkEffectMeta) itemN.getItemMeta();
		imN.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.PURPLE).build());
		imN.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&5&l엔더&8&l]"));
		itemN.setItemMeta(imN);
		RUNE_ENDER = itemN;
		
		ItemStack itemR = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta imR = (FireworkEffectMeta) itemR.getItemMeta();
		imR.setEffect(FireworkEffect.builder().with(Type.BURST).withColor(Color.PURPLE).build());
		imR.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7고대의 룬 &8&l[&d&l무지개&8&l]"));
		itemR.setItemMeta(imR);
		RUNE_RAINBOW = itemR;
	}
	
	/*		Electricity			*/
	public static ItemStack SOLAR_GENERATOR = new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&b태양열 발전기", "", "&e기본 발전기", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &74 J/s");
	public static ItemStack SOLAR_GENERATOR_2 = new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&c고급 태양열 발전기", "", "&a중간 발전기", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &716 J/s");
	public static ItemStack SOLAR_GENERATOR_3 = new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&4카르보나두 태양열 발전기", "", "&4최종 발전기", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &764 J/s");
	public static ItemStack SOLAR_GENERATOR_4 = new CustomItem(new ItemStack(Material.DAYLIGHT_DETECTOR), "&e활성화 태양열 발전기", "", "&9밤에도 작동합니다", "", "&4최종 발전기", "&8\u21E8 &e\u26A1 &70 J Buffer", "&8\u21E8 &e\u26A1 &7256 J/s (아침)", "&8\u21E8 &e\u26A1 &7128 J/s (밤)");
	
	public static ItemStack COAL_GENERATOR = null;
	public static ItemStack LAVA_GENERATOR = null;
	
	public static ItemStack ELECTRIC_FURNACE = new CustomItem(new ItemStack(Material.FURNACE), "&c전기로 &7- &eI", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &74 J/s");
	public static ItemStack ELECTRIC_FURNACE_2 = new CustomItem(new ItemStack(Material.FURNACE), "&c전기로 &7- &eII", "", "&a중간 기계", "&8\u21E8 &7처리 속도: 2x", "&8\u21E8 &e\u26A1 &76 J/s");
	public static ItemStack ELECTRIC_FURNACE_3 = new CustomItem(new ItemStack(Material.FURNACE), "&c전기로 &7- &eIII", "", "&a중간 기계", "&8\u21E8 &7처리 속도: 4x", "&8\u21E8 &e\u26A1 &710 J/s");
	
	public static ItemStack ELECTRIC_ORE_GRINDER = new CustomItem(new ItemStack(Material.FURNACE), "&c전기 광석 분쇄기 &7- &eI", "","&r광석 분쇄기와 숫돌 역활을 합니다", "", "&6고급 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &712 J/s");
	public static ItemStack ELECTRIC_ORE_GRINDER_2 = new CustomItem(new ItemStack(Material.FURNACE), "&c전기 광석 분쇄기 &7- &eII", "","&r광석 분쇄기와 숫돌 역활을 합니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 4x", "&8\u21E8 &e\u26A1 &730 J/s");
	public static ItemStack ELECTRIC_INGOT_PULVERIZER = new CustomItem(new ItemStack(Material.FURNACE), "&c전기 주괴 분쇄기", "", "&r주괴를 분쇄하여 가루로 만듭니다", "", "&a중간 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &714 J/s");
	public static ItemStack AUTO_ENCHANTER = new CustomItem(new ItemStack(Material.ENCHANTMENT_TABLE), "&5자동 마법 부여대", "", "&a중간 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &718 J/s");
	public static ItemStack AUTO_DISENCHANTER = new CustomItem(new ItemStack(Material.ENCHANTMENT_TABLE), "&5자동 마법 회수기", "", "&a중간 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &718 J/s");
	public static ItemStack AUTO_ANVIL = new CustomItem(new ItemStack(Material.IRON_BLOCK), "&7자동 모루 &7- &eI", "", "&6고급 기계", "&8\u21E8 &7수리율: 10%", "&8\u21E8 &e\u26A1 &724 J/s");
	public static ItemStack AUTO_ANVIL_2 = new CustomItem(new ItemStack(Material.IRON_BLOCK), "&7자동 모루 &7- &eII", "", "&4최종 기계", "&8\u21E8 &7수리율: 25%", "&8\u21E8 &e\u26A1 &732 J/s");
	
	public static ItemStack BIO_REACTOR = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 5), "&2바이오 반응기", "", "&6평균 발전기", "&8\u21E8 &e\u26A1 &7128 J Buffer", "&8\u21E8 &e\u26A1 &78 J/s");
	public static ItemStack MULTIMETER = new CustomItem(new MaterialData(Material.WATCH), "&e멀티 미터", "", "&r저장된 양을 측정합니다", "&r블럭에 저장된 전기만 해당됩니다");
	public static ItemStack SMALL_CAPACITOR = null, MEDIUM_CAPACITOR = null, BIG_CAPACITOR = null, LARGE_CAPACITOR = null, CARBONADO_EDGED_CAPACITOR = null;
	
	/*		Robots				*/
	public static ItemStack PROGRAMMABLE_ANDROID = null;
	public static ItemStack PROGRAMMABLE_ANDROID_MINER = null;
	public static ItemStack PROGRAMMABLE_ANDROID_BUTCHER = null;
	public static ItemStack PROGRAMMABLE_ANDROID_FARMER = null;
	public static ItemStack PROGRAMMABLE_ANDROID_WOODCUTTER = null;
	public static ItemStack PROGRAMMABLE_ANDROID_FISHERMAN = null;

	public static ItemStack PROGRAMMABLE_ANDROID_2 = null;
	public static ItemStack PROGRAMMABLE_ANDROID_2_FISHERMAN = null;
	public static ItemStack PROGRAMMABLE_ANDROID_2_FARMER = null;
	public static ItemStack PROGRAMMABLE_ANDROID_2_BUTCHER = null;

	public static ItemStack PROGRAMMABLE_ANDROID_3 = null;
	public static ItemStack PROGRAMMABLE_ANDROID_3_FISHERMAN = null;
	public static ItemStack PROGRAMMABLE_ANDROID_3_BUTCHER = null;
	
	/*		GPS					*/
	public static ItemStack GPS_TRANSMITTER = null;
	public static ItemStack GPS_TRANSMITTER_2 = null;
	public static ItemStack GPS_TRANSMITTER_3 = null;
	public static ItemStack GPS_TRANSMITTER_4 = null;
	
	public static ItemStack GPS_CONTROL_PANEL = null;
	public static ItemStack GPS_MARKER_TOOL = new CustomItem(new MaterialData(Material.REDSTONE_TORCH_ON), "&bGPS 마커 도구", "", "&r웨이포인트를 설정할 수 있습니다", "&r현재 있는 위치를 중심으로 지정합니다");
	public static ItemStack GPS_EMERGENCY_TRANSMITTER = null;
	public static ItemStack GPS_GEO_SCANNER = null;
	
	public static ItemStack ANDROID_INTERFACE_FUEL = new CustomItem(new ItemStack(Material.DISPENSER), "&7안드로이드 인터페이스 &c(연료)", "", "&r인터페이스에 저장된 아이템을", "&r안드로이드의 연료 공간에 삽입됩니다", "&r스크립트가 작동할 경우에만 해당됩니다");
	public static ItemStack ANDROID_INTERFACE_ITEMS = new CustomItem(new ItemStack(Material.DISPENSER), "&7안드로이드 인터페이스 &9(아이템)", "", "&r안드로이드의 인벤토리에서", "&r인터페이스에 아이템을 저장합니다", "&r스크립트가 작동할 경우에만 해당됩니다");

	public static ItemStack BUCKET_OF_OIL = null;
	public static ItemStack BUCKET_OF_FUEL = null;
	public static ItemStack OIL_PUMP = null;

	public static ItemStack REFINERY = new CustomItem(new ItemStack(Material.PISTON_BASE), "&c정제소", "", "&r석유를 정제하여 연료를 생성합니다");
	public static ItemStack COMBUSTION_REACTOR = null;
	public static ItemStack ANDROID_MEMORY_CORE = null;
	
	public static ItemStack GPS_TELEPORTER_PYLON = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 10), "&5GPS 텔레포터 철탑", "", "&7텔레포터 구성 요소");
	public static ItemStack GPS_TELEPORTATION_MATRIX = new CustomItem(new MaterialData(Material.IRON_BLOCK), "&bGPS 텔레포터 매트릭스", "", "&r이것은 텔레포터의 주요 구성 요소입니다", "&r이 매트릭스는 플레이어가 지정한 웨이포인트로", "&r이동하게 해주는 장치입니다");
	public static ItemStack GPS_ACTIVATION_DEVICE_SHARED = new CustomItem(new MaterialData(Material.STONE_PLATE), "&rGPS 활성화 장치 &3(공유)", "", "&r텔레포트 매트릭스를 설치한 후", "&r그 위에 올려 활성화를 합니다", "&rGPS 제어판으로 이동할 위치를 설정하세요");
	public static ItemStack GPS_ACTIVATION_DEVICE_PERSONAL = new CustomItem(new MaterialData(Material.STONE_PLATE), "&rGPS 활성화 장치 &a(개인)", "", "&r텔레포트 매트릭스를 설치한 후", "&r그 위에 올려 활성화를 합니다", "&rGPS 제어판으로 이동할 위치를 설정하세요", "", "&r이 장치는 설치한 개인만 사용이 가능합니다");

	public static ItemStack ELEVATOR = new CustomItem(new MaterialData(Material.STONE_PLATE), "&b엘레베이터 플레이트", "", "&r모든 층에 엘레베이터 플레이트를 배치하고 나면", "&r설치한 곳에서 엘레베이터를 사용할 수 있습니다", "", "&e우 클릭 &7으로 층 이름을 변경할 수 있습니다");
	
	public static ItemStack INFUSED_HOPPER = new CustomItem(new MaterialData(Material.HOPPER), "&5Infused Hopper", "", "&rAutomatically picks up nearby Items in a 7x7x7", "&rRadius when placed.");

	public static ItemStack PLASTIC_SHEET = new CustomItem(new MaterialData(Material.PAPER), "&r플라스틱 시트");
	public static ItemStack HEATED_PRESSURE_CHAMBER = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 8), "&c가열식 압력 챔버 &7- &eI", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &710 J/s");
	public static ItemStack HEATED_PRESSURE_CHAMBER_2 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 8), "&c가열식 압력 챔버 &7- &eII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 5x", "&8\u21E8 &e\u26A1 &744 J/s");
	
	public static ItemStack ELECTRIC_SMELTERY = new CustomItem(new MaterialData(Material.FURNACE), "&c전기 제련소 &7- &eI", "", "&4합금 전용, 주괴를 가루로 녹이지 않습니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &720 J/s");
	public static ItemStack ELECTRIC_SMELTERY_2 = new CustomItem(new MaterialData(Material.FURNACE), "&c전기 제련소 &7- &eII", "", "&4합금 전용, 주괴를 가루로 녹이지 않습니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 3x", "&8\u21E8 &e\u26A1 &740 J/s");
	
	public static ItemStack ELECTRIFIED_CRUCIBLE = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 도가니 &7- &eI", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &748 J/s");
	public static ItemStack ELECTRIFIED_CRUCIBLE_2 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 도가니 &7- &eII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 2x", "&8\u21E8 &e\u26A1 &780 J/s");
	public static ItemStack ELECTRIFIED_CRUCIBLE_3 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 도가니 &7- &eIII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 4x", "&8\u21E8 &e\u26A1 &7120 J/s");

	public static ItemStack CARBON_PRESS = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 15), "&c탄소 압축기 &7- &eI", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &720 J/s");
	public static ItemStack CARBON_PRESS_2 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 15), "&c탄소 압축기 &7- &eII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 3x", "&8\u21E8 &e\u26A1 &750 J/s");
	public static ItemStack CARBON_PRESS_3 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 15), "&c탄소 압축기 &7- &eIII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 15x", "&8\u21E8 &e\u26A1 &7180 J/s");
	
	public static ItemStack BLISTERING_INGOT = new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6블리스터 &7(33%)", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
	public static ItemStack BLISTERING_INGOT_2 = new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6블리스터 &7(66%)", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
	public static ItemStack BLISTERING_INGOT_3 = new CustomItem(new MaterialData(Material.GOLD_INGOT), "&6블리스터", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
	
	public static ItemStack ENERGY_REGULATOR = null;
	public static ItemStack DEBUG_FISH = new CustomItem(new MaterialData(Material.RAW_FISH), "&3생선 얼마에요?", "", "&e블럭 데이터를 볼려면 블럭을 클릭하세요", "&e왼 클릭 &r으로 블럭을 부실 수 있습니다", "&e쉬프트 + 왼 클릭 &r으로 블럭 데이터를 지울 수 있습니다", "&e쉬프트 + 우 클릭 &r으로 PlaceHolderAPI 블럭을 설치할 수 있습니다");


	public static ItemStack NETHER_ICE = null;
	public static ItemStack ENRICHED_NETHER_ICE = null;
	public static ItemStack NETHER_ICE_COOLANT_CELL = null;
	public static ItemStack NETHER_DRILL = new CustomItem(new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&4네더 드릴", "", "&r네더 얼음을 채광할 수 있게 해줍니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &7102 J/s", "", "&c&l! &c네더에서만 사용할 수 있습니다!", "&c&l! &c지리 스캐너로 청크를 먼저 스캔하세요"));
	
	// Cargo
	public static ItemStack CARGO_MANAGER = null;
	public static ItemStack CARGO_NODE = null;
	public static ItemStack CARGO_INPUT = null;
	public static ItemStack CARGO_OUTPUT = null;
	public static ItemStack CARGO_OUTPUT_ADVANCED = null;

	public static ItemStack AUTO_BREEDER = null;
	
	public static ItemStack ORGANIC_FOOD = null;
	public static ItemStack ORGANIC_FOOD2 = null;
	public static ItemStack ORGANIC_FOOD3 = null;
	public static ItemStack ORGANIC_FOOD4 = null;
	public static ItemStack ORGANIC_FOOD5 = null;
	public static ItemStack ORGANIC_FOOD6 = null;
	public static ItemStack ORGANIC_FOOD7 = null;
	public static ItemStack ORGANIC_FOOD8 = null;
	
	public static ItemStack FERTILIZER = null;
	public static ItemStack FERTILIZER2 = null;
	public static ItemStack FERTILIZER3 = null;
	public static ItemStack FERTILIZER4 = null;
	public static ItemStack FERTILIZER5 = null;
	public static ItemStack FERTILIZER6 = null;
	public static ItemStack FERTILIZER7 = null;
	public static ItemStack FERTILIZER8 = null;
	
	public static ItemStack ANIMAL_GROWTH_ACCELERATOR = null;
	public static ItemStack CROP_GROWTH_ACCELERATOR = null;
	public static ItemStack CROP_GROWTH_ACCELERATOR_2 = null;

	public static ItemStack FOOD_FABRICATOR = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 13), "&c식품 제조기 &7- &eI", "", "&a유기농 식품 &r을 생산합니다", "", "&6고급 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &714 J/s");
	public static ItemStack FOOD_FABRICATOR_2 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 13), "&c식품 제조기 &7- &eII", "", "&a유기농 식품 &r을 생산합니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 6x", "&8\u21E8 &e\u26A1 &7512 J Buffer", "&8\u21E8 &e\u26A1 &748 J/s");
	
	public static ItemStack FOOD_COMPOSTER = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 13), "&c식품 발효기 &7- &eI", "", "&a유기질 비료 &r를 생산합니다", "", "&6고급 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &716 J/s");
	public static ItemStack FOOD_COMPOSTER_2 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 13), "&c식품 발효기 &7- &eII", "", "&a유기질 비료 &r를 생산합니다", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 10x", "&8\u21E8 &e\u26A1 &7512 J Buffer", "&8\u21E8 &e\u26A1 &752 J/s");

	public static ItemStack XP_COLLECTOR = null;
	public static ItemStack REACTOR_COOLANT_CELL = null;

	public static ItemStack NUCLEAR_REACTOR = null;
	public static ItemStack NETHERSTAR_REACTOR = null;
	public static ItemStack REACTOR_ACCESS_PORT = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 9), "&2원자로 액세스 포트", "", "&r원자로와 상호 작용할 수 있습니다", "&r화물 노드를 통해 사용할 수도 있습니다", "", "&8\u21E8 &e원자로 &e위에 두어야 합니다");
	
	public static ItemStack FREEZER = null;
	public static ItemStack FREEZER_2 = null;
	
	public static ItemStack ELECTRIC_GOLD_PAN = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 12), "&6전기 골드 팬 &7- &eI", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &72 J/s");
	public static ItemStack ELECTRIC_GOLD_PAN_2 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 12), "&6전기 골드 팬 &7- &eII", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 3x", "&8\u21E8 &e\u26A1 &74 J/s");
	public static ItemStack ELECTRIC_GOLD_PAN_3 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 12), "&6전기 골드 팬 &7- &eIII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 10x", "&8\u21E8 &e\u26A1 &714 J/s");

	public static ItemStack ELECTRIC_DUST_WASHER = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 11), "&3전기 가루 세척기 &7- &eI", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &76 J/s");
	public static ItemStack ELECTRIC_DUST_WASHER_2 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 11), "&3전기 가루 세척기 &7- &eII", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 2x", "&8\u21E8 &e\u26A1 &710 J/s");
	public static ItemStack ELECTRIC_DUST_WASHER_3 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 11), "&3전기 가루 세척기 &7- &eIII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 10x", "&8\u21E8 &e\u26A1 &730 J/s");

	public static ItemStack ELECTRIC_INGOT_FACTORY = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 주괴 제조기 &7- &eI", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &78 J/s");
	public static ItemStack ELECTRIC_INGOT_FACTORY_2 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 주괴 제조기 &7- &eII", "", "&e기본 기계", "&8\u21E8 &7처리 속도: 2x", "&8\u21E8 &e\u26A1 &714 J/s");
	public static ItemStack ELECTRIC_INGOT_FACTORY_3 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 14), "&c전기 주괴 제조기 &7- &eIII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 8x", "&8\u21E8 &e\u26A1 &740 J/s");

	public static ItemStack AUTOMATED_CRAFTING_CHAMBER = new CustomItem(new MaterialData(Material.WORKBENCH), "&6자동 제작 챔버", "", "&6고급 기계", "&8\u21E8 &e\u26A1 &710 J/Item");
	public static ItemStack FLUID_PUMP = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 11), "&9유체 펌프", "", "&6고급 기계", "&8\u21E8 &e\u26A1 &732 J/Block");
	public static ItemStack CHARGING_BENCH = new CustomItem(new MaterialData(Material.WORKBENCH), "&6충전대", "", "&r충전이 필요한 아이템을 충전할 수 있습니다", "", "&e기본 기계", "&8\u21E8 &e\u26A1 &7128 J Buffer", "&8\u21E8 &e\u26A1 &7손실: &c50%");

	public static ItemStack WITHER_ASSEMBLER = new CustomItem(new MaterialData(Material.OBSIDIAN), "&5위더 어셈블러", "", "&4최종 기계", "&8\u21E8 &7쿨타임: &b30 초", "&8\u21E8 &e\u26A1 &74096 J Buffer", "&8\u21E8 &e\u26A1 &74096 J/Wither");
	
	public static ItemStack TRASH_CAN = null;
	
	public static ItemStack ELYTRA = new ItemStack(Material.ELYTRA);
	public static ItemStack ELYTRA_SCALE = new CustomItem(new ItemStack(Material.FEATHER), "&b겉날개 조각");
	public static ItemStack INFUSED_ELYTRA = new CustomItem(new CustomItem(ELYTRA, "&5주입된 겉날개"), new String[] {"MENDING-1"});
	public static ItemStack SOULBOUND_ELYTRA = new CustomItem(ELYTRA, "&c귀속된 겉날개");
	
	// ChestTerminal Addon
	
	public static ItemStack CHEST_TERMINAL = null;
	public static ItemStack CT_IMPORT_BUS = null;
	public static ItemStack CT_EXPORT_BUS = null;
	
	static {
		try {
			PORTABLE_DUSTBIN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJkNDEwNDJjZTk5MTQ3Y2MzOGNhYzllNDY3NDE1NzZlN2VlNzkxMjgzZTZmYWM4ZDMyOTJjYWUyOTM1ZjFmIn19fQ=="), "&6휴대용 쓰레기통", "&r간편하게 쓰레기를 버릴 수 있습니다", "", "&e우 클릭&7 으로 사용합니다");
			TRASH_CAN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJkNDEwNDJjZTk5MTQ3Y2MzOGNhYzllNDY3NDE1NzZlN2VlNzkxMjgzZTZmYWM4ZDMyOTJjYWUyOTM1ZjFmIn19fQ=="), "&3쓰레기통", "", "&r쓰레기를 버릴 수 있습니다");
			CAN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRkYTk3ZjA4MGUzOTViODQyYzRjYzgyYTg0MDgyM2Q0ZGJkOGNhNjg4YTIwNjg1M2U1NzgzZTRiZmRjMDEyIn19fQ=="), "&r깡통");
			
			STONE_CHUNK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2U4ZjVhZGIxNGQ2YzlmNmI4MTBkMDI3NTQzZjFhOGMxZjQxN2UyZmVkOTkzYzk3YmNkODljNzRmNWUyZTgifX19"), "&6돌 덩어리");
			
			INFUSED_MAGNET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&a주입된 자석" , "", "&r마법 주입식 자석", "&r가까운 아이템을", "&r끌어 당깁니다", "", "&e쉬프트 &7를 누르고 있으면 아이템을 빨아들일 수 있습니다");
			MAGNET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&c자석");
			ELECTRO_MAGNET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&c전자석");
			ELECTRIC_MOTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiY2EwMTJmNjdlNTRkZTlhZWU3MmZmNDI0ZTA1NmMyYWU1OGRlNWVhY2M5NDlhYjJiY2Q5NjgzY2VjIn19fQ=="), "&c전기 모터");
			CARGO_MOTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiY2EwMTJmNjdlNTRkZTlhZWU3MmZmNDI0ZTA1NmMyYWU1OGRlNWVhY2M5NDlhYjJiY2Q5NjgzY2VjIn19fQ=="), "&3화물 모터");
			
			BACKPACK_SMALL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e매우 작은 가방", new String[] {"", "&7크기: &e9", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			BACKPACK_MEDIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e작은 가방", new String[] {"", "&7크기: &e18", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			BACKPACK_LARGE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e가방", new String[] {"", "&7크기: &e27", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			WOVEN_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e큰 가방", new String[] {"", "&7크기: &e36", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			GILDED_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBjYjFlNjdiNTEyYWIyZDRiZjNkN2FjZTBlYWFmNjFjMzJjZDQ2ODFkZGMzOTg3Y2ViMzI2NzA2YTMzZmEifX19"), "&e매우 큰 가방", new String[] {"", "&7크기: &e45", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			BOUND_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&c귀속된 가방", new String[] {"", "&7크기: &e36", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			COOLER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRjMTU3MjU4NGViNWRlMjI5ZGU5ZjVhNGY3NzlkMGFhY2JhZmZkMzNiY2IzM2ViNDUzNmE2YTJiYzZhMSJ9fX0="), "&b냉장고", new String[] {"&r주스 / 스무디를 보관할 수 있습니다", "&r배가 고플 때 마다 자동으로 소비합니다", "", "&7크기: &e27", "&7ID: <ID>", "", "&e우 클릭 &7으로 열 수 있습니다"});
			ENDER_BACKPACK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&6휴대용 엔더 가방", new String[] {"&a&o휴대용 엔더 상자", "", "&e우 클릭 &7으로 열 수 있습니다"});

			VOIDBAG_SMALL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4작은 보이드 백", new String[] {"", "&7크기: &e9", "&7ID: <ID>", "", "&e왼 클릭 &7으로 주변 아이템을 빨아들일 수 있습니다", "&e우 클릭 &7으로 열 수 있습니다"});
			VOIDBAG_MEDIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4보이드 백", new String[] {"", "&7크기: &e18", "&7ID: <ID>", "", "&e왼 클릭 &7으로 주변 아이템을 빨아들일 수 있습니다", "&e우 클릭 &7으로 열 수 있습니다"});
			VOIDBAG_BIG = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4큰 보이드 백", new String[] {"", "&7크기: &e27", "&7ID: <ID>", "", "&e왼 클릭 &7으로 주변 아이템을 빨아들일 수 있습니다", "&e우 클릭 &7으로 열 수 있습니다"});
			VOIDBAG_LARGE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4매우 큰 보이드 백", new String[] {"", "&7크기: &e36", "&7ID: <ID>", "", "&e왼 클릭 &7으로 주변 아이템을 빨아들일 수 있습니다", "&e우 클릭 &7으로 열 수 있습니다"});
			BOUND_VOIDBAG = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjM0ODYyYjlhZmI2M2NmOGQ1Nzc5OTY2ZDNmYmE3MGFmODJiMDRlODNmM2VhZjY0NDlhZWJhIn19fQ=="), "&4귀속된 보이드 백", new String[] {"", "&7크기: &e36", "&7ID: <ID>", "", "&e왼 클릭 &7으로 주변 아이템을 빨아들일 수 있습니다", "&e우 클릭 &7으로 열 수 있습니다"});
			
			COAL_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c석탄 발전기", "", "&6평균 발전기", "&8\u21E8 &e\u26A1 &764 J Buffer", "&8\u21E8 &e\u26A1 &716 J/s");
			LAVA_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&4용암 발전기", "", "&6평균 발전기", "&8\u21E8 &e\u26A1 &7512 J Buffer", "&8\u21E8 &e\u26A1 &720 J/s");
			COMBUSTION_REACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c연소로", "", "&6고급 발전기", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &724 J/s");

			NUCLEAR_REACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&2원자로", "", "&r냉각이 필요합니다!", "&8\u21E8 &b물에 둘러싸여 야합니다", "&8\u21E8 &b원자로 냉각제 셀과 함께 공급되어야 합니다", "", "&4최종 발전기", "&8\u21E8 &e\u26A1 &716384 J Buffer", "&8\u21E8 &e\u26A1 &7500 J/s");
			NETHERSTAR_REACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&f네더의 별 원자로", "", "&f네더의 별이 필요합니다", "&8\u21E8 &b물에 둘러싸여 야합니다", "&8\u21E8 &b네더 냉각제 셀과 함께 공급되어 야합니다", "", "&4최종 발전기", "&8\u21E8 &e\u26A1 &732768 J Buffer", "&8\u21E8 &e\u26A1 &71024 J/s", "&8\u21E8 &4주변 생명체에게 시들음을 유발합니다");

			SMALL_CAPACITOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a소량 축전기", "", "&e기본 축전기", "&8\u21E8 &e\u26A1 &7128 J Capacity");
			MEDIUM_CAPACITOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a축전기", "", "&6소형 축전기", "&8\u21E8 &e\u26A1 &7512 J Capacity");
			BIG_CAPACITOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a중량 축전기", "", "&a중형 축전기", "&8\u21E8 &e\u26A1 &71024 J Capacity");
			LARGE_CAPACITOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a대용량 축전기", "", "&2대형 축전기", "&8\u21E8 &e\u26A1 &78192 J Capacity");
			CARBONADO_EDGED_CAPACITOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTEzNjFlNTc2YjQ5M2NiZmRmYWUzMjg2NjFjZWRkMWFkZDU1ZmFiNGU1ZWI0MThiOTJjZWJmNjI3NWY4YmI0In19fQ=="), "&a카르보나두 축전기", "", "&4최종 축전기", "&8\u21E8 &e\u26A1 &765536 J Capacity");
			CHEESE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRmZWJiYzE1ZDFkNGNjNjJiZWRjNWQ3YTJiNmYwZjQ2Y2Q1YjA2OTZhODg0ZGU3NWUyODllMzVjYmI1M2EwIn19fQ=="), "&r치즈");
			BUTTER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY2YjE5ZjdkNjM1ZDAzNDczODkxZGYzMzAxN2M1NDkzNjMyMDlhOGY2MzI4YTg1NDJjMjEzZDA4NTI1ZSJ9fX0="), "&r버터");
			DUCT_TAPE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJmYWFjZWFiNjM4NGZmZjVlZDI0YmI0NGE0YWYyZjU4NGViMTM4MjcyOWVjZDkzYTUzNjlhY2ZkNjY1NCJ9fX0="), "&8덕트 테이프", "", "&r자동 모루에 수리할 때 사용됩니다");
		
			URANIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&4우라늄", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
			SMALL_URANIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&c작은 우라늄 덩어리", "", "&e방사선 레벨: 보통", "&4&o방호복 슈트가 필요합니다");
			TINY_URANIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMjlhZmE2ZDZkYzkyM2UyZTEzMjRiZjgxOTI3NTBmN2JkYmRkYzY4OTYzMmEyYjZjMThkOWZlN2E1ZSJ9fX0="), "&c우라늄의 작은 더미", "", "&c방사선 레벨: 낮음", "&4&o방호복 슈트가 필요하지 않습니다");

			NEPTUNIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVkZWE2YmZkMzdlNDlkZTQzZjE1NGZlNmZjYTYxN2Q0MTI5ZTYxYjk1NzU5YTNkNDlhMTU5MzVhMWMyZGNmMCJ9fX0="), "&a넵투늄", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
			PLUTONIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjVjZjkxYjczODg2NjVhNmQ3YzFiNjAyNmJkYjIzMjJjNmQyNzg5OTdhNDQ0Nzg2NzdjYmNjMTVmNzYxMjRmIn19fQ=="), "&7플루토늄", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
			BOOSTED_URANIUM = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgzN2NhMTJmMjIyZjQ3ODcxOTZhMTdiOGFiNjU2OTg1Zjg0MDRjNTA3NjdhZGJjYjZlN2YxNDI1NGZlZSJ9fX0="), "&2활성화 우라늄", "", "&2방사선 레벨: 높음", "&4&o방호복 슈트가 필요합니다");
			
			PROGRAMMABLE_ANDROID = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&c안드로이드 &7(일반)", "", "&8\u21E8 &7기능: 없음", "&8\u21E8 &7연료 효율: 1.0x");
            PROGRAMMABLE_ANDROID_FARMER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlkMzMzNTdlODQxODgyM2JmNzgzZGU5MmRlODAyOTFiNGViZDM5MmFlYzg3MDY2OThlMDY4OTZkNDk4ZjYifX19"), "&c안드로이드 &7(농부)", "", "&8\u21E8 &7기능: 농사", "&8\u21E8 &7연료 효율: 1.0x");
			PROGRAMMABLE_ANDROID_MINER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTYzOGEyODU0MWFiM2FlMGE3MjNkNTU3ODczOGUwODc1ODM4OGVjNGMzMzI0N2JkNGNhMTM0ODJhZWYzMzQifX19"), "&c안드로이드 &7(광부)", "", "&8\u21E8 &7기능: 채광", "&8\u21E8 &7연료 효율: 1.0x");
			PROGRAMMABLE_ANDROID_WOODCUTTER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDMyYTgxNDUxMDE0MjIwNTE2OWExYWQzMmYwYTc0NWYxOGU5Y2I2YzY2ZWU2NGVjYTJlNjViYWJkZWY5ZmYifX19"), "&c안드로이드 &7(나무꾼)", "", "&8\u21E8 &7기능: 벌목", "&8\u21E8 &7연료 효율: 1.0x");
			PROGRAMMABLE_ANDROID_BUTCHER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&c안드로이드 &7(도살자)", "", "&8\u21E8 &7기능: 도살", "&8\u21E8 &7피해: 4", "&8\u21E8 &7연료 효율: 1.0x");
			PROGRAMMABLE_ANDROID_FISHERMAN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&c안드로이드 &7(낚시꾼)", "", "&8\u21E8 &7기능: 낚시", "&8\u21E8 &7성공 확률: 10%", "&8\u21E8 &7연료 효율: 1.0x");
			
			PROGRAMMABLE_ANDROID_2 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&c고급 안드로이드 &7(일반)", "", "&8\u21E8 &7기능: 없음", "&8\u21E8 &7연료 효율: 1.5x");
			PROGRAMMABLE_ANDROID_2_FISHERMAN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&c고급 안드로이드 &7(낚시꾼)", "", "&8\u21E8 &7기능: 낚시", "&8\u21E8 &7성공 확률: 20%", "&8\u21E8 &7연료 효율: 1.5x");
			PROGRAMMABLE_ANDROID_2_FARMER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjlkMzMzNTdlODQxODgyM2JmNzgzZGU5MmRlODAyOTFiNGViZDM5MmFlYzg3MDY2OThlMDY4OTZkNDk4ZjYifX19"), "&c고급 안드로이드 &7(농부)", "", "&8\u21E8 &7기능: 농사", "&8\u21E8 &7연료 효율: 1.5x", "&8\u21E8 &7ExoticGarden 플러그인의 자원을 지원합니다");
			PROGRAMMABLE_ANDROID_2_BUTCHER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&c고급 안드로이드 &7(도살자)", "", "&8\u21E8 &7기능: 도살", "&8\u21E8 &7피해: 8", "&8\u21E8 &7연료 효율: 1.5x");

			PROGRAMMABLE_ANDROID_3 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&e최고급 안드로이드 &7(일반)", "", "&8\u21E8 &7기능: 없음", "&8\u21E8 &7연료 효율: 3.0x");
			PROGRAMMABLE_ANDROID_3_FISHERMAN = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ1ZTg3MzNhNzMxMTQzMzNiOThiMzYwMTc1MTI0MTcyMmY0NzEzZTFhMWE1ZDM2ZmJiMTMyNDkzZjFjNyJ9fX0="), "&e최고급 안드로이드 &7(낚시꾼)", "", "&8\u21E8 &7기능: 낚시", "&8\u21E8 &7성공 확률: 30%", "&8\u21E8 &7연료 효율: 8.0x");
			PROGRAMMABLE_ANDROID_3_BUTCHER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I0NzJkZjBhZDlhM2JlODhmMmU1ZDVkNDIyZDAyYjExNmQ2NGQ4ZGYxNDc1ZWQzMmU1NDZhZmM4NGIzMSJ9fX0="), "&e최고급 안드로이드 &7(도살자)", "", "&8\u21E8 &7기능: 도살", "&8\u21E8 &7피해: 20", "&8\u21E8 &7연료 효율: 8.0x");
			
			GPS_TRANSMITTER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&bGPS 송신기", "", "&8\u21E8 &e\u26A1 &716 J Buffer", "&8\u21E8 &e\u26A1 &72 J/s");
			GPS_TRANSMITTER_2 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&c고급 GPS 송신기", "", "&8\u21E8 &e\u26A1 &764 J Buffer", "&8\u21E8 &e\u26A1 &76 J/s");
			GPS_TRANSMITTER_3 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&4카르보나두 GPS 송신기", "", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &722 J/s");
			GPS_TRANSMITTER_4 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&e활성화 GPS 송신기", "", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &792 J/s");
			
			GPS_CONTROL_PANEL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZmJhNThmYWYxZjY0ODQ3ODg0MTExODIyYjY0YWZhMjFkN2ZjNjJkNDQ4MWYxNGYzZjNiY2I2MzMwIn19fQ=="), "&bGPS 제어판", "", "&r위성을 표시할 수 있습니다", "&r웨이포인트를 관리할 수 있습니다");
			GPS_EMERGENCY_TRANSMITTER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBjOWMxYTAyMmY0MGI3M2YxNGI0Y2JhMzdjNzE4YzZhNTMzZjNhMjg2NGI2NTM2ZDVmNDU2OTM0Y2MxZiJ9fX0="), "&cGPS 비상 송신기", "", "&r인벤토리에 소지시 사용됩니다", "&r죽을 경우 자동으로 저장됩니다");
			
			GPS_GEO_SCANNER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFkOGNmZWIzODdhNTZlM2U1YmNmODUzNDVkNmE0MTdiMjQyMjkzODg3ZGIzY2UzYmE5MWZhNDA5YjI1NGI4NiJ9fX0="), "&bGPS 지리 스캐너", "", "&r지리에서 천연 자원을 검색합니다");
			OIL_PUMP = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWZlMWEwNDBhNDI1ZTMxYTQ2ZDRmOWE5Yjk4MDZmYTJmMGM0N2VlODQ3MTFjYzE5MzJmZDhhYjMyYjJkMDM4In19fQ=="), "&r석유 펌프", "", "&7양동이에 석유를 채울 수 있습니다", "", "&c&l! &c먼저 지리 스캐너로 조사해야 합니다");
			BUCKET_OF_OIL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNlMDRiNDFkMTllYzc5MjdmOTgyYTYzYTk0YTNkNzlmNzhlY2VjMzMzNjMwNTFmZGUwODMxYmZhYmRiZCJ9fX0="), "&r석유 양동이");
			BUCKET_OF_FUEL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTg0ZGRjYTc2NjcyNWI4Yjk3NDEzZjI1OWMzZjc2NjgwNzBmNmFlNTU0ODNhOTBjOGU1NTI1Mzk0ZjljMDk5In19fQ=="), "&r연료 양동이");

			NETHER_ICE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NlMmRhZDliYWY3ZWFiYTdlODBkNGQwZjlmYWMwYWFiMDFhNzZiMTJmYjcxYzNkMmFmMmExNmZkZDRjNzM4MyJ9fX0="), "&e네더 얼음", "", "&e방사선 레벨: 중간", "&4&o방호복 슈트가 필요합니다");
			ENRICHED_NETHER_ICE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M4MThhYTEzYWFiYzcyOTQ4MzhkMjFjYWFjMDU3ZTk3YmQ4Yzg5NjQxYTBjMGY4YTU1NDQyZmY0ZTI3In19fQ=="), "&e강화된 네더 얼음", "", "&2방사선 레벨: 아주 높음", "&4&o방호복 슈트가 필요합니다");

			LAVA_CRYSTAL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNhZDhlZTg0OWVkZjA0ZWQ5YTI2Y2EzMzQxZjYwMzNiZDc2ZGNjNDIzMWVkMWVhNjNiNzU2NTc1MWIyN2FjIn19fQ=="), "&4용암 크리스탈");
			ANDROID_MEMORY_CORE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&b안드로이드 메모리 코어");

			CARBON = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIzYTA5NWI2YjgxZTZiOTg1M2ExOTMyNGVlZGYwYmI5MzQ5NDE3MjU4ZGQxNzNiOGVmZjg3YTA4N2FhIn19fQ=="), "&e탄소");
			COMPRESSED_CARBON = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIxZDQ5NTE2NTc0OGQzMTE2Zjk5ZDZiNWJkNWQ0MmViOGJhNTkyYmNkZmFkMzdmZDk1ZjliNmMwNGEzYiJ9fX0="), "&c압축 탄소");
			CARBON_CHUNK = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzIxZDQ5NTE2NTc0OGQzMTE2Zjk5ZDZiNWJkNWQ0MmViOGJhNTkyYmNkZmFkMzdmZDk1ZjliNmMwNGEzYiJ9fX0="), "&4탄소 덩어리");
			
			CARBONADO = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTJmNGIxNTc3ZjUxNjBjNjg5MzE3MjU3MWM0YTcxZDhiMzIxY2RjZWFhMDMyYzZlMGUzYjYwZTBiMzI4ZmEifX19"), "&b&l카르보나두", "", "&7&o\"블랙 다이아몬드\"");
			RAW_CARBONADO = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWI0OWU2ZWMxMDc3MWU4OTkyMjVhZWE3M2NkOGNmMDM2ODRmNDExZDE0MTVjNzMyM2M5M2NiOTQ3NjIzMCJ9fX0="), "&b원시 카르보나두");
			
			ENERGY_REGULATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDc4ZjJiN2U1ZTc1NjM5ZWE3ZmI3OTZjMzVkMzY0YzRkZjI4YjQyNDNlNjZiNzYyNzdhYWRjZDYyNjEzMzcifX19"), "&6에너지 조정기", "", "&r에너지 네트워크의 핵심 구성 요소");
			
			CARGO_MANAGER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTUxMGJjODUzNjJhMTMwYTZmZjlkOTFmZjExZDZmYTQ2ZDdkMTkxMmEzNDMxZjc1MTU1OGVmM2M0ZDljMiJ9fX0="), "&6화물 관리자", "", "&r아이템 전송 네트워크의 핵심 구성 요소");
			CARGO_NODE = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDdiN2VmNmZkNzg2NDg2NWMzMWMxZGM4N2JlZDI0YWI1OTczNTc5ZjVjNjYzOGZlY2I4ZGVkZWI0NDNmZjAifX19"), "&7화물 노드 &c(커넥터)", "", "&r화물 커넥터 파이프");
			CARGO_INPUT = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZkMWMxYTY5YTNkZTlmZWM5NjJhNzdiZjNiMmUzNzZkZDI1Yzg3M2EzZDhmMTRmMWRkMzQ1ZGFlNGM0In19fQ=="), "&7화물 노드 &c(입력)", "", "&r화물 입력 파이프");
			CARGO_OUTPUT = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTViMjFmZDQ4MGMxYzQzYmYzYjlmODQyYzg2OWJkYzNiYzVhY2MyNTk5YmYyZWI2YjhhMWM5NWRjZTk3OGYifX19"), "&7화물 노드 &c(출력)", "", "&r화물 출력 파이프");
			CARGO_OUTPUT_ADVANCED = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTViMjFmZDQ4MGMxYzQzYmYzYjlmODQyYzg2OWJkYzNiYzVhY2MyNTk5YmYyZWI2YjhhMWM5NWRjZTk3OGYifX19"), "&6고급 화물 노드 &c(산출)", "", "&r화물 산출 파이프");

			AUTO_BREEDER = new CustomItem(new MaterialData(Material.HAY_BLOCK), "&e자동 증식기", "", "&a유기농 식품 &r을 사용합니다", "", "&4최종 기계", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &760 J/Animal");
			ANIMAL_GROWTH_ACCELERATOR = new CustomItem(new MaterialData(Material.HAY_BLOCK), "&b동물 성장 가속기", "", "&a유기농 식품 &r을 사용합니다", "", "&4최종 기계", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &728 J/s");
			CROP_GROWTH_ACCELERATOR = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 5), "&a작물 성장 가속기 &7- &eI", "", "&r유기질 비료 &r를 사용합니다", "", "&4최종 기계", "&8\u21E8 &7범위: 7x7", "&8\u21E8 &7처리 속도: &a3/time", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &750 J/s");
			CROP_GROWTH_ACCELERATOR_2 = new CustomItem(new MaterialData(Material.STAINED_CLAY, (byte) 5), "&a작물 성장 가속기 &7- &eII", "", "&r유기질 비료 &r를 사용합니다", "", "&4최종 기계", "&8\u21E8 &7범위: 9x9", "&8\u21E8 &7처리 속도: &a4/time", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &760 J/s");
			XP_COLLECTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2MmExNWIwNDY5MmEyZTRiM2ZiMzY2M2JkNGI3ODQzNGRjZTE3MzJiOGViMWM3YTlmN2MwZmJmNmYifX19"), "&aEXP 수집기", "", "&r근처에 있는 경험치를 수집합니다", "", "&4최종 단계", "&8\u21E8 &e\u26A1 &71024 J Buffer", "&8\u21E8 &e\u26A1 &720 J/s");
			
			ORGANIC_FOOD = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물 : &9X");
			ORGANIC_FOOD2 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9밀");
			ORGANIC_FOOD3 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9당근");
			ORGANIC_FOOD4 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9감자");
			ORGANIC_FOOD5 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9씨앗");
			ORGANIC_FOOD6 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9사탕무");
			ORGANIC_FOOD7 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9멜론");
			ORGANIC_FOOD8 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기농 식품", "&7내용물: &9사과");

			FERTILIZER = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9X");
			FERTILIZER2 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9밀");
			FERTILIZER3 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9당근");
			FERTILIZER4 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9감자");
			FERTILIZER5 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9씨앗");
			FERTILIZER6 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9사탕무");
			FERTILIZER7 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9멜론");
			FERTILIZER8 = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzOWUzZjVhY2JlZTliZTRjNDI1OTI4OWQ2ZDlmMzVjNjM1ZmZhNjYxMTE0Njg3YjNlYTZkZGE4Yzc5In19fQ=="), "&a유기질 비료", "&7내용물: &9사과");

			NETHER_ICE_COOLANT_CELL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQzY2Q0MTI1NTVmODk3MDE2MjEzZTVkNmM3NDMxYjQ0OGI5ZTU2NDRlMWIxOWVjNTFiNTMxNmYzNTg0MGUwIn19fQ=="), "&6네더 얼음 냉각제 셀");
			REACTOR_COOLANT_CELL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGU0MDczYmU0MGNiM2RlYjMxMGEwYmU5NTliNGNhYzY4ZTgyNTM3MjcyOGZhZmI2YzI5NzNlNGU3YzMzIn19fQ=="), "&b원자로 냉각제 셀");

			CHEST_TERMINAL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E0NGZmM2E1ZjQ5YzY5Y2FiNjc2YmFkOGQ5OGEwNjNmYTc4Y2ZhNjE5MTZmZGVmM2UyNjc1NTdmZWMxODI4MyJ9fX0="), "&3CT Access Terminal", "&7If this Block is connected to a", "&7Cargo Network, it will allow you to remotely", "&7interact with any Items supplied by", "&7Nodes tuned into the ChestTerminal Channel");
			CT_IMPORT_BUS = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTEzZGIyZTdlNzJlYTQ0MzJlZWZiZDZlNThhODVlYWEyNDIzZjgzZTY0MmNhNDFhYmM2YTkzMTc3NTdiODg5In19fQ=="), "&3CT 입력 버스", "&7해당 장치가", "&e화물 네트워크 &7에 연결되어 있다면", "&7부착하여 네트워크 채널을 입력하세요", "&7into the CT Network Channel");
			CT_EXPORT_BUS = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTEzZGIyZTdlNzJlYTQ0MzJlZWZiZDZlNThhODVlYWEyNDIzZjgzZTY0MmNhNDFhYmM2YTkzMTc3NTdiODg5In19fQ=="), "&3CT 출력 버스", "&7해당 장치가", "&e화물 네트워크 &7에 연결되어 있다면", "&7부착하여 네트워크 채널을 열고 이것을 배치하세요", "&7into the Inventory it is attached to");
			
			FREEZER = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 3), "&b냉동고 &7- &eI", "", "&6고급 기계", "&8\u21E8 &7처리 속도: 1x", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &718 J/s");
			FREEZER_2 = new CustomItem(new MaterialData(Material.STAINED_GLASS, (byte) 3), "&b냉동고 &7- &eII", "", "&4최종 기계", "&8\u21E8 &7처리 속도: 2x", "&8\u21E8 &e\u26A1 &7256 J Buffer", "&8\u21E8 &e\u26A1 &730 J/s");
			
			BATTERY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUyZGRhNmVmNjE4NWQ0ZGQ2ZWE4Njg0ZTk3ZDM5YmE4YWIwMzdlMjVmNzVjZGVhNmJkMjlkZjhlYjM0ZWUifX19"), "&6배터리");
			
			HEATING_COIL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzYmM0ODkzYmE0MWEzZjczZWUyODE3NGNkZjRmZWY2YjE0NWU0MWZlNmM4MmNiN2JlOGQ4ZTk3NzFhNSJ9fX0="), "&c가열 코일");
			COOLING_UNIT = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU0YmFkODZjOTlkZjc4MGM4ODlhMTA5OGY3NzY0OGVhZDczODVjYzFkZGIwOTNkYTVhN2Q4YzRjMmFlNTRkIn19fQ=="), "&b냉각 장치");
			POWER_CRYSTAL = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNjMWIwMzZiNmUwMzUxN2IyODVhODExYmQ4NWU3M2Y1YWJmZGFjYzFkZGY5MGRmZjk2MmUxODA5MzRlMyJ9fX0="), "&c&l파워 크리스탈");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
