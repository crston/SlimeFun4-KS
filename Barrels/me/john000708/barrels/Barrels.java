/*     */ package me.john000708.barrels;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.john000708.barrels.listeners.DisplayListener;
/*     */ import me.john000708.barrels.listeners.WorldListener;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.events.ItemUseEvent;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemInteractionHandler;
/*     */ import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Barrels extends JavaPlugin
/*     */ {
/*     */   public static boolean displayItem;
/*     */   public static JavaPlugin plugin;
/*     */   public static Config config;
/*     */   boolean plastic;
/*     */   
/*     */   public void onEnable()
/*     */   {
/*  41 */     plugin = this;
/*     */     
/*  43 */     PluginUtils utils = new PluginUtils(this);
/*  44 */     utils.setupMetrics();
/*  45 */     utils.setupUpdater(99947, getFile());
/*     */     
/*  47 */     utils.setupConfig();
/*  48 */     config = utils.getConfig();
/*     */     
/*  50 */     new DisplayListener();
/*  51 */     new WorldListener();
/*     */     
/*  53 */     displayItem = config.getBoolean("options.displayItem");
/*  54 */     this.plastic = config.getBoolean("options.plastic-recipe");
/*     */     
/*  56 */     setup();
/*  57 */     getLogger().info("Barrels v" + getDescription().getVersion() + " has been enabled!");
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  61 */     plugin = null;
/*     */   }
/*     */   
/*     */   private void setup()
/*     */   {
/*  66 */     Category barrelCat = new Category(new CustomItem(new ItemStack(Material.LOG), "&a배럴", new String[] { "", "&a> 클릭하여 열기" }), 2);
/*     */     
/*  68 */     ItemStack SMALL_BARREL = new CustomItem(new ItemStack(Material.LOG), "&9배럴 &7- &eI", new String[] { "", "&8⇨ &7Capacity: 64 Stacks" });
/*  69 */     ItemStack MEDIUM_BARREL = new CustomItem(new MaterialData(Material.LOG, (byte) 1), "&9배럴 &7- &eII", new String[] { "", "&8⇨ &7Capacity: 128 Stacks" });
/*  70 */     ItemStack BIG_BARREL = new CustomItem(new MaterialData(Material.LOG_2, (byte) 1), "&9배럴 &7- &eIII", new String[] { "", "&8⇨ &7Capacity: 256 Stacks" });
/*  71 */     ItemStack LARGE_BARREL = new CustomItem(new ItemStack(Material.LOG_2), "&9배럴 &7- &eIV", new String[] { "", "&8⇨ &7Capacity: 512 Stacks" });
/*  72 */     ItemStack DSU = new CustomItem(new ItemStack(Material.DIAMOND_BLOCK), "&3대용량 저장 장치", new String[] { "", "&4최종 저장소", "", "&8⇨ &7Capacity: 1048576 Stacks" });
/*     */     
/*     */ 
/*  75 */     final ItemStack EXPLOSION_MODULE = new CustomItem(new ItemStack(Material.ITEM_FRAME), "&9폭발 방지", new String[] { "", "&f배럴이 폭발로부터 파괴되는 것을 방지합니다" });
/*  76 */     final ItemStack BIOMETRIC_PROTECTION = new CustomItem(new ItemStack(Material.ITEM_FRAME), "&9생체 인식 보호", new String[] { "", "&f다른 플레이어가 배럴에 접근하는 것을 차단합니다" });
/*  77 */     final ItemStack ID_CARD = new CustomItem(new ItemStack(Material.PAPER), "&fID 카드", new String[] { "", "&e우 클릭 &7으로 사용합니다" });
/*  78 */     final ItemStack STRUCT_UPGRADE_1 = new CustomItem(new ItemStack(Material.ITEM_FRAME), "&9배럴 업그레이드 장치 &7- &eI", new String[] { "&b배럴 &7- &eI &8⇨ &b배럴 &7- &eII" });
/*  79 */     final ItemStack STRUCT_UPGRADE_2 = new CustomItem(new ItemStack(Material.ITEM_FRAME), "&9배럴 업그레이드 장치 &7- &eII", new String[] { "&b배럴 &7- &eII &8⇨ &b배럴 &7- &eIII" });
/*  80 */     final ItemStack STRUCT_UPGRADE_3 = new CustomItem(new ItemStack(Material.ITEM_FRAME), "&9배럴 업그레이드 장치 &7- &eIII", new String[] { "&b배럴 &7- &eIII &8⇨ &b배럴 &7- &eIV" });
/*     */     
/*  82 */     new Barrel(barrelCat, SMALL_BARREL, "BARREL_SMALL", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOD_STEP), this.plastic ? SlimefunItems.PLASTIC_SHEET : new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.CHEST), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), SlimefunItems.GILDED_IRON, new ItemStack(Material.WOOD_STEP) }, 4096)
/*     */     {
/*     */       public String getInventoryTitle()
/*     */       {
/*  86 */         return "&9배럴 &7- &eI";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */     }.register();new Barrel(barrelCat, MEDIUM_BARREL, "BARREL_MEDIUM", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOD_STEP), this.plastic ? SlimefunItems.PLASTIC_SHEET : new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), SMALL_BARREL, new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), SlimefunItems.GILDED_IRON, new ItemStack(Material.WOOD_STEP) }, 8192)
/*     */     {
/*     */       public String getInventoryTitle()
/*     */       {
/*  95 */         return "&9배럴 &7- &eII";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */     }.register();new Barrel(barrelCat, BIG_BARREL, "BARREL_BIG", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOD_STEP), this.plastic ? SlimefunItems.PLASTIC_SHEET : new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), MEDIUM_BARREL, new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), SlimefunItems.GILDED_IRON, new ItemStack(Material.WOOD_STEP) }, 16384)
/*     */     {
/*     */       public String getInventoryTitle()
/*     */       {
/* 104 */         return "&9배럴 &7- &eIII";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 115 */     }.register();new Barrel(barrelCat, LARGE_BARREL, "BARREL_LARGE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.WOOD_STEP), this.plastic ? SlimefunItems.PLASTIC_SHEET : new ItemStack(Material.CAULDRON_ITEM), new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), BIG_BARREL, new ItemStack(Material.WOOD_STEP), new ItemStack(Material.WOOD_STEP), SlimefunItems.GILDED_IRON, new ItemStack(Material.WOOD_STEP) }, 32768)
/*     */     {
/*     */       public String getInventoryTitle()
/*     */       {
/* 113 */         return "&9배럴 &7- &eIV";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 124 */     }.register();new Barrel(barrelCat, DSU, "BARREL_GIGANTIC", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.ENDER_CHEST), SlimefunItems.REINFORCED_PLATE, SlimefunItems.PLASTIC_SHEET, LARGE_BARREL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.REINFORCED_PLATE, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_PLATE }, 1048576)
/*     */     {
/*     */       public String getInventoryTitle()
/*     */       {
/* 122 */         return "&3대용량 저장 장치";
/*     */       }
/*     */       
/*     */ 
/* 126 */     }.register();
/* 127 */     new SlimefunItem(barrelCat, EXPLOSION_MODULE, "EXPLOSION_MODULE", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.TNT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.TNT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.TNT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.TNT) }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack)
/*     */       {
/* 131 */         if (!SlimefunManager.isItemSimiliar(itemStack, EXPLOSION_MODULE, true)) return false;
/* 132 */         if ((itemUseEvent.getClickedBlock() != null) && (BlockStorage.hasBlockInfo(itemUseEvent.getClickedBlock())) && (BlockStorage.checkID(itemUseEvent.getClickedBlock()).startsWith("BARREL_"))) {
/* 133 */           Block clickedBlock = itemUseEvent.getClickedBlock();
/* 134 */           if (BlockStorage.getBlockInfo(clickedBlock, "explosion") == null) {
/* 135 */             BlockStorage.addBlockInfo(clickedBlock, "explosion", "true");
/* 136 */             player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 137 */             player.sendMessage(ChatColor.GREEN + "모듈이 성공적으로 적용되었습니다!");
/*     */           }
/*     */         }
/* 140 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 144 */     } });
/* 145 */     new SlimefunItem(barrelCat, STRUCT_UPGRADE_1, "STRUCT_UPGRADE_1", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, MEDIUM_BARREL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack)
/*     */       {
/* 149 */         if (!SlimefunManager.isItemSimiliar(itemStack, STRUCT_UPGRADE_1, true)) return false;
/* 150 */         if ((itemUseEvent.getClickedBlock() != null) && (BlockStorage.hasBlockInfo(itemUseEvent.getClickedBlock())) && (BlockStorage.checkID(itemUseEvent.getClickedBlock()).startsWith("BARREL_")) && (BlockStorage.getBlockInfo(itemUseEvent.getClickedBlock(), "STRUCT_1") == null)) {
/* 151 */           Block clickedBlock = itemUseEvent.getClickedBlock();
/*     */           
/* 153 */           BlockStorage.addBlockInfo(clickedBlock, "STRUCT_1", "true");
/* 154 */           BlockStorage.addBlockInfo(clickedBlock, "capacity", String.valueOf(Integer.valueOf(BlockStorage.getBlockInfo(clickedBlock, "capacity")).intValue() + 8192));
/* 155 */           player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 156 */           player.sendMessage(ChatColor.GREEN + "모듈이 성공적으로 적용되었습니다!");
/*     */         }
/* 158 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 162 */     } });
/* 163 */     new SlimefunItem(barrelCat, STRUCT_UPGRADE_2, "STRUCT_UPGRADE_2", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, BIG_BARREL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack)
/*     */       {
/* 167 */         if (!SlimefunManager.isItemSimiliar(itemStack, STRUCT_UPGRADE_2, true)) return false;
/* 168 */         if ((itemUseEvent.getClickedBlock() != null) && (BlockStorage.hasBlockInfo(itemUseEvent.getClickedBlock())) && (BlockStorage.checkID(itemUseEvent.getClickedBlock()).startsWith("BARREL_")) && (BlockStorage.getBlockInfo(itemUseEvent.getClickedBlock(), "STRUCT_2") == null)) {
/* 169 */           Block clickedBlock = itemUseEvent.getClickedBlock();
/*     */           
/* 171 */           BlockStorage.addBlockInfo(clickedBlock, "STRUCT_2", "true");
/* 172 */           BlockStorage.addBlockInfo(clickedBlock, "capacity", String.valueOf(Integer.valueOf(BlockStorage.getBlockInfo(clickedBlock, "capacity")).intValue() + 16384));
/* 173 */           player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 174 */           player.sendMessage(ChatColor.GREEN + "모듈이 성공적으로 적용되었습니다!");
/*     */         }
/* 176 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 180 */     } });
/* 181 */     new SlimefunItem(barrelCat, STRUCT_UPGRADE_3, "STRUCT_UPGRADE_3", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, LARGE_BARREL, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.LEAD_INGOT }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack)
/*     */       {
/* 185 */         if (!SlimefunManager.isItemSimiliar(itemStack, STRUCT_UPGRADE_3, true)) return false;
/* 186 */         if ((itemUseEvent.getClickedBlock() != null) && (BlockStorage.hasBlockInfo(itemUseEvent.getClickedBlock())) && (BlockStorage.checkID(itemUseEvent.getClickedBlock()).startsWith("BARREL_")) && (BlockStorage.getBlockInfo(itemUseEvent.getClickedBlock(), "STRUCT_3") == null)) {
/* 187 */           Block clickedBlock = itemUseEvent.getClickedBlock();
/*     */           
/* 189 */           BlockStorage.addBlockInfo(clickedBlock, "STRUCT_3", "true");
/* 190 */           BlockStorage.addBlockInfo(clickedBlock, "capacity", String.valueOf(Integer.valueOf(BlockStorage.getBlockInfo(clickedBlock, "capacity")).intValue() + 32768));
/* 191 */           player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 192 */           player.sendMessage(ChatColor.GREEN + "모듈이 성공적으로 적용되었습니다!");
/*     */         }
/* 194 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 198 */     } });
/* 199 */     new SlimefunItem(barrelCat, BIOMETRIC_PROTECTION, "BIO_PROTECTION", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.REDSTONE), new ItemStack(Material.DIAMOND), new ItemStack(Material.REDSTONE), new ItemStack(Material.DIAMOND), new ItemStack(Material.PAPER), new ItemStack(Material.DIAMOND), new ItemStack(Material.REDSTONE), new ItemStack(Material.DIAMOND), new ItemStack(Material.REDSTONE) }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack) {
/* 202 */         if (!SlimefunManager.isItemSimiliar(itemStack, BIOMETRIC_PROTECTION, true)) return false;
/* 203 */         if ((itemUseEvent.getClickedBlock() != null) && (BlockStorage.hasBlockInfo(itemUseEvent.getClickedBlock())) && (BlockStorage.checkID(itemUseEvent.getClickedBlock()).startsWith("BARREL_")) && (BlockStorage.getBlockInfo(itemUseEvent.getClickedBlock(), "BIO_PROT") == null)) {
/* 204 */           Block clickedBlock = itemUseEvent.getClickedBlock();
/*     */           
/* 206 */           BlockStorage.addBlockInfo(clickedBlock, "protected", "true");
/* 207 */           player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 208 */           player.sendMessage(ChatColor.GREEN + "모듈이 성공적으로 적용되었습니다!");
/*     */         }
/* 210 */         return false;
/*     */       }
/*     */       
/* 213 */     } });
/* 214 */     new SlimefunItem(barrelCat, ID_CARD, "BARREL_ID_CARD", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.REDSTONE), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.REDSTONE), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.PAPER), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.REDSTONE), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.REDSTONE) }).register(false, new ItemHandler[] { new ItemInteractionHandler()
/*     */     {
/*     */       public boolean onRightClick(ItemUseEvent itemUseEvent, Player player, ItemStack itemStack) {
/* 217 */         if (!SlimefunManager.isItemSimiliar(itemStack, ID_CARD, false)) return false;
/* 218 */         Block clickedBlock = itemUseEvent.getClickedBlock();
/* 219 */         ItemStack idCard = itemStack;
/* 220 */         ItemMeta meta = idCard.getItemMeta();
/* 221 */         List<String> lore = idCard.getItemMeta().getLore();
/*     */         
/* 223 */         if (((String)lore.get(0)).equals("")) {
/* 224 */           lore.set(0, ChatColor.translateAlternateColorCodes('&', "&0" + player.getUniqueId().toString()));
/* 225 */           lore.set(1, ChatColor.translateAlternateColorCodes('&', "&f소유자: " + player.getName()));
/* 226 */           meta.setLore(lore);
/* 227 */           idCard.setItemMeta(meta);
/* 228 */           player.sendMessage(ChatColor.GREEN + "ID 카드를 소유하였습니다");
/* 229 */         } else if ((clickedBlock != null) && (BlockStorage.hasBlockInfo(clickedBlock)) && (BlockStorage.checkID(clickedBlock).startsWith("BARREL_")) && (BlockStorage.getBlockInfo(clickedBlock, "whitelist") != null) && (BlockStorage.getBlockInfo(clickedBlock, "owner").equals(player.getUniqueId().toString()))) {
/* 230 */           String whitelistedPlayers = BlockStorage.getBlockInfo(clickedBlock, "whitelist");
/* 231 */           if (!whitelistedPlayers.contains(ChatColor.stripColor((String)lore.get(0)))) {
/* 232 */             BlockStorage.addBlockInfo(clickedBlock, "whitelist", whitelistedPlayers + ChatColor.stripColor((String)lore.get(0)) + ";");
/* 233 */             player.getInventory().setItem(player.getInventory().getHeldItemSlot(), InvUtils.decreaseItem(itemStack, 1));
/* 234 */             player.sendMessage(ChatColor.GREEN + "플레이어가 화이트 리스트에 주가되었습니다!");
/*     */           } else {
/* 236 */             player.sendMessage(ChatColor.RED + "플레이어는 이미 화이트 리스트에 추가되어 있습니다");
/*     */           }
/*     */         }
/*     */         
/* 240 */         return false;
/*     */       }
/*     */     } });
/*     */   }
/*     */ }
