/*     */ package me.john000708.barrels;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
/*     */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*     */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*     */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
/*     */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
/*     */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
/*     */ import me.mrCookieSlime.Slimefun.api.inventory.ItemManipulationEvent;
/*     */ import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public class Barrel extends SlimefunItem
/*     */ {
/*  38 */   private int[] border1 = { 0, 1, 2, 9, 11, 18, 19, 20 };
/*  39 */   private int[] border2 = { 3, 5, 12, 13, 14, 21, 23 };
/*  40 */   private int[] border3 = { 6, 7, 8, 15, 17, 24, 25, 26 };
/*     */   private int capacity;
/*     */   private boolean allowDisplayItem;
/*     */   
/*     */   public Barrel(Category category, ItemStack item, String name, RecipeType recipeType, ItemStack[] recipe, int capacity)
/*     */   {
/*  46 */     super(category, item, name, recipeType, recipe);
/*     */     
/*  48 */     this.capacity = capacity;
/*     */     
/*  50 */     new BlockMenuPreset(name, getInventoryTitle())
/*     */     {
/*     */       public void init()
/*     */       {
/*  54 */         Barrel.this.constructMenu(this);
/*     */       }
/*     */       
/*     */ 
/*     */       public void newInstance(BlockMenu menu, final Block b)
/*     */       {
/*  60 */         registerEvent(new ItemManipulationEvent()
/*     */         {
/*     */           public ItemStack onEvent(int i, ItemStack itemStack, ItemStack itemStack1)
/*     */           {
/*  64 */             Barrel.this.updateBarrel(b);
/*  65 */             return itemStack1;
/*     */           }
/*     */         });
/*     */         
/*     */ 
/*  70 */         if (BlockStorage.getBlockInfo(b, "storedItems") == null) {
/*  71 */           menu.replaceExistingItem(4, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
/*  72 */           menu.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
/*     */         }
/*     */         
/*  75 */         if (Barrels.displayItem) {
/*  76 */           Barrel.this.allowDisplayItem = (b.getRelative(BlockFace.UP).getType() == Material.AIR);
/*     */           
/*  78 */           DisplayItem.updateDisplayItem(b, Barrel.this.getCapacity(b), Barrel.this.allowDisplayItem);
/*     */         }
/*     */       }
/*     */       
/*     */       public boolean canOpen(Block b, Player p)
/*     */       {
/*  84 */         boolean protect = (BlockStorage.getBlockInfo(b, "protected") == null) || (BlockStorage.getBlockInfo(b, "owner").equals(p.getUniqueId().toString())) || ((BlockStorage.getBlockInfo(b, "whitelist") != null) && (BlockStorage.getBlockInfo(b, "whitelist").contains(p.getUniqueId().toString())));
/*     */         
/*  86 */         return (p.hasPermission("slimefun.inventory.bypass")) || (protect);
/*     */       }
/*     */       
/*     */       public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow)
/*     */       {
/*  91 */         return new int[0];
/*     */       }
/*     */       
/*     */       public int[] getSlotsAccessedByItemTransport(BlockMenu menu, ItemTransportFlow flow, ItemStack item)
/*     */       {
/*  96 */         if (flow == ItemTransportFlow.INSERT) {
/*  97 */           if (BlockStorage.getBlockInfo(menu.getBlock(), "storedItems") != null)
/*  98 */             return Barrel.this.isSimiliar(item, menu.getItemInSlot(22)) ? Barrel.this.getInputSlots() : new int[0];
/*  99 */           return Barrel.this.getInputSlots(); }
/* 100 */         return Barrel.this.getOutputSlots();
/*     */       }
/*     */       
/* 103 */     };
/* 104 */     registerBlockHandler(name, new SlimefunBlockHandler()
/*     */     {
/*     */       public void onPlace(Player player, Block block, SlimefunItem slimefunItem) {
/* 107 */         BlockStorage.addBlockInfo(block, "owner", player.getUniqueId().toString());
/* 108 */         BlockStorage.addBlockInfo(block, "whitelist", " ");
/*     */       }
/*     */       
/*     */ 
/*     */       public boolean onBreak(Player player, Block block, SlimefunItem slimefunItem, UnregisterReason unregisterReason)
/*     */       {
/* 114 */         if (unregisterReason.equals(UnregisterReason.EXPLODE)) {
/* 115 */           if (BlockStorage.getBlockInfo(block, "explosion") != null) return false;
/* 116 */         } else if ((unregisterReason.equals(UnregisterReason.PLAYER_BREAK)) && 
/* 117 */           (!BlockStorage.getBlockInfo(block, "owner").equals(player.getUniqueId().toString()))) {
/* 118 */           return false;
/*     */         }
/*     */         
/* 121 */         DisplayItem.removeDisplayItem(block);
/*     */         
/* 123 */         BlockMenu inv = BlockStorage.getInventory(block);
/*     */         
/* 125 */         if (BlockStorage.getBlockInfo(block, "explosion") != null)
/* 126 */           block.getWorld().dropItem(block.getLocation(), SlimefunItem.getByName("EXPLOSION_MODULE").getItem());
/* 127 */         if (BlockStorage.getBlockInfo(block, "STRUCT_1") != null)
/* 128 */           block.getWorld().dropItem(block.getLocation(), SlimefunItem.getByName("STRUCT_UPGRADE_1").getItem());
/* 129 */         if (BlockStorage.getBlockInfo(block, "STRUCT_2") != null)
/* 130 */           block.getWorld().dropItem(block.getLocation(), SlimefunItem.getByName("STRUCT_UPGRADE_2").getItem());
/* 131 */         if (BlockStorage.getBlockInfo(block, "STRUCT_3") != null)
/* 132 */           block.getWorld().dropItem(block.getLocation(), SlimefunItem.getByName("STRUCT_UPGRADE_3").getItem());
/* 133 */         if (BlockStorage.getBlockInfo(block, "protected") != null) {
/* 134 */           block.getWorld().dropItem(block.getLocation(), SlimefunItem.getByName("BIO_PROTECTION").getItem());
/*     */         }
/* 136 */         if (BlockStorage.getBlockInfo(block, "storedItems") == null) return true;
/* 137 */         int storedAmount = Integer.valueOf(BlockStorage.getBlockInfo(block, "storedItems")).intValue();
/*     */         
/* 139 */         ItemStack item = inv.getItemInSlot(22);
/* 140 */         ItemMeta meta = item.getItemMeta();
/*     */         
/* 142 */         List<String> lore = meta.getLore();
/* 143 */         for (int i = 0; i <= lore.size() - 1; i++) {
/* 144 */           if (((String)lore.get(i)).equals("§b§a§r§r§e§l")) {
/* 145 */             lore.remove(i);
/* 146 */             meta.setLore(lore);
/* 147 */             item.setItemMeta(meta);
/* 148 */             break;
/*     */           }
/*     */         }
/*     */         
/* 152 */         while (storedAmount > 0) {
/* 153 */           int amount = item.getMaxStackSize();
/*     */           
/* 155 */           if (storedAmount > amount) {
/* 156 */             storedAmount -= amount;
/*     */           } else {
/* 158 */             amount = storedAmount;
/* 159 */             storedAmount = 0;
/*     */           }
/*     */           
/* 162 */           block.getWorld().dropItem(block.getLocation(), new CustomItem(item, amount));
/*     */         }
/*     */         
/* 165 */         if (inv.getItemInSlot(Barrel.this.getInputSlots()[0]) != null) {
/* 166 */           block.getWorld().dropItem(block.getLocation(), inv.getItemInSlot(Barrel.this.getInputSlots()[0]));
/*     */         }
/* 168 */         if (inv.getItemInSlot(Barrel.this.getOutputSlots()[0]) != null) {
/* 169 */           block.getWorld().dropItem(block.getLocation(), inv.getItemInSlot(Barrel.this.getOutputSlots()[0]));
/*     */         }
/* 171 */         return true;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void register(boolean slimefun)
/*     */   {
/* 178 */     addItemHandler(new ItemHandler[] { new BlockTicker()
/*     */     {
/*     */       public boolean isSynchronized()
/*     */       {
/* 182 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       public void uniqueTick() {}
/*     */       
/*     */ 
/*     */       public void tick(Block block, SlimefunItem slimefunItem, Config config)
/*     */       {
/* 192 */         Barrel.this.updateBarrel(block);
/*     */         
/*     */ 
/* 195 */         if (Barrels.displayItem) {
/* 196 */           Barrel.this.allowDisplayItem = (block.getRelative(BlockFace.UP).getType() == Material.AIR);
/* 197 */           DisplayItem.updateDisplayItem(block, Barrel.this.getCapacity(block), Barrel.this.allowDisplayItem);
/*     */         }
/*     */         
/*     */       }
/* 201 */     } });
/* 202 */     super.register(false);
/*     */   }
/*     */   
/*     */   public String getInventoryTitle() {
/* 206 */     return "&6Barrel";
/*     */   }
/*     */   
/*     */   public int getCapacity(Block b) {
/* 210 */     if (BlockStorage.getBlockInfo(b, "capacity") == null) {
/* 211 */       BlockStorage.addBlockInfo(b, "capacity", String.valueOf(this.capacity));
/*     */     }
/*     */     
/* 214 */     return Integer.valueOf(BlockStorage.getBlockInfo(b, "capacity")).intValue();
/*     */   }
/*     */   
/*     */   public int[] getInputSlots() {
/* 218 */     return new int[] { 10 };
/*     */   }
/*     */   
/*     */   public int[] getOutputSlots() {
/* 222 */     return new int[] { 16 };
/*     */   }
/*     */   
/*     */   private ItemStack getCapacityItem(Block b) {
/* 226 */     StringBuilder bar = new StringBuilder();
/*     */     
/* 228 */     int storedItems = Integer.valueOf(BlockStorage.getBlockInfo(b, "storedItems")).intValue();
/*     */     
/* 230 */     float percentage = Math.round(storedItems / getCapacity(b) * 100.0F);
/*     */     
/* 232 */     bar.append("&8[");
/*     */     
/* 234 */     if (percentage < 25.0F) {
/* 235 */       bar.append("&2");
/* 236 */     } else if (percentage < 50.0F) {
/* 237 */       bar.append("&a");
/* 238 */     } else if (percentage < 75.0F) {
/* 239 */       bar.append("&e");
/*     */     } else {
/* 241 */       bar.append("&c");
/*     */     }
/*     */     
/* 244 */     int lines = 20;
/*     */     
/* 246 */     for (int i = (int)percentage; i >= 5; i -= 5) {
/* 247 */       bar.append(":");
/* 248 */       lines--;
/*     */     }
/*     */     
/* 251 */     bar.append("&7");
/*     */     
/* 253 */     for (int i = 0; i < lines; i++) {
/* 254 */       bar.append(":");
/*     */     }
/*     */     
/* 257 */     bar.append("&8] &7- " + percentage + "%");
/*     */     
/* 259 */     return new CustomItem(new ItemStack(Material.CAULDRON_ITEM), "&7" + BlockStorage.getBlockInfo(b, "storedItems") + "/" + getCapacity(b), new String[] { ChatColor.translateAlternateColorCodes('&', bar.toString()) });
/*     */   }
/*     */   
/*     */   private void updateBarrel(Block b) {
/* 263 */     BlockMenu inventory = BlockStorage.getInventory(b);
/*     */     
/* 265 */     if (inventory == null) { return;
/*     */     }
/* 267 */     for (int slot : getInputSlots()) {
/* 268 */       if (inventory.getItemInSlot(slot) != null) {
/* 269 */         ItemStack input = inventory.getItemInSlot(slot);
/*     */         
/* 271 */         if (isSimiliar(input, inventory.getItemInSlot(22))) {
/* 272 */           if (BlockStorage.getBlockInfo(b, "storedItems") == null) {
/* 273 */             BlockStorage.addBlockInfo(b, "storedItems", "1");
/*     */           }
/* 275 */           int storedAmount = Integer.valueOf(BlockStorage.getBlockInfo(b, "storedItems")).intValue();
/*     */           
/* 277 */           if (storedAmount < getCapacity(b)) {
/* 278 */             if (storedAmount + input.getAmount() > getCapacity(b)) {
/* 279 */               BlockStorage.addBlockInfo(b, "storedItems", String.valueOf(getCapacity(b)));
/* 280 */               inventory.replaceExistingItem(slot, InvUtils.decreaseItem(inventory.getItemInSlot(slot), getCapacity(b) - storedAmount), false);
/* 281 */               inventory.replaceExistingItem(4, getCapacityItem(b), false);
/*     */             } else {
/* 283 */               BlockStorage.addBlockInfo(b, "storedItems", String.valueOf(storedAmount + input.getAmount()));
/* 284 */               inventory.replaceExistingItem(slot, new ItemStack(Material.AIR), false);
/* 285 */               inventory.replaceExistingItem(4, getCapacityItem(b), false);
/*     */             }
/*     */           }
/* 288 */         } else if (inventory.getItemInSlot(22).getType() == Material.BARRIER) {
/* 289 */           ItemStack stack = input.clone();
/* 290 */           List<String> lore = (stack.hasItemMeta()) && (stack.getItemMeta().hasLore()) ? stack.getItemMeta().getLore() : new ArrayList();
/* 291 */           lore.add("§b§a§r§r§e§l");
/* 292 */           ItemMeta meta = stack.getItemMeta();
/* 293 */           meta.setLore(lore);
/* 294 */           stack.setItemMeta(meta);
/* 295 */           BlockStorage.addBlockInfo(b, "storedItems", String.valueOf(input.getAmount()));
/*     */           
/* 297 */           inventory.replaceExistingItem(22, new CustomItem(stack, 1), false);
/* 298 */           inventory.replaceExistingItem(slot, new ItemStack(Material.AIR), false);
/* 299 */           inventory.replaceExistingItem(4, getCapacityItem(b), false);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 304 */     if (BlockStorage.getBlockInfo(b, "storedItems") == null) { return;
/*     */     }
/* 306 */     int stored = Integer.valueOf(BlockStorage.getBlockInfo(b, "storedItems")).intValue();
/* 307 */     ItemStack output = inventory.getItemInSlot(22).clone();
/*     */     
/* 309 */     if (inventory.getItemInSlot(getOutputSlots()[0]) != null) {
/* 310 */       if (!isSimiliar(inventory.getItemInSlot(getOutputSlots()[0]), output)) {
/* 311 */         return;
/*     */       }
/*     */       
/* 314 */       int requested = output.getMaxStackSize() - inventory.getItemInSlot(getOutputSlots()[0]).getAmount();
/*     */       
/* 316 */       if (stored >= requested) {
/* 317 */         output.setAmount(requested);
/*     */       } else {
/* 319 */         output.setAmount(stored);
/*     */       }
/*     */     }
/* 322 */     else if (stored > output.getMaxStackSize()) {
/* 323 */       output.setAmount(output.getMaxStackSize());
/*     */     } else {
/* 325 */       output.setAmount(stored);
/*     */     }
/*     */     
/*     */ 
/* 329 */     ItemMeta meta = output.getItemMeta();
/* 330 */     if (meta == null) { return;
/*     */     }
/* 332 */     List<String> lore = meta.getLore();
/*     */     
/* 334 */     for (int i = 0; i <= lore.size() - 1; i++) {
/* 335 */       if (((String)lore.get(i)).equals("§b§a§r§r§e§l")) {
/* 336 */         lore.remove(i);
/* 337 */         break;
/*     */       }
/*     */     }
/*     */     
/* 341 */     meta.setLore(lore);
/* 342 */     output.setItemMeta(meta);
/*     */     
/* 344 */     if (!fits(b, new ItemStack[] { output })) { return;
/*     */     }
/* 346 */     BlockStorage.addBlockInfo(b, "storedItems", String.valueOf(stored - output.getAmount()));
/*     */     
/* 348 */     pushItems(b, new ItemStack[] { output });
/*     */     
/* 350 */     if (stored - output.getAmount() <= 0) {
/* 351 */       BlockStorage.addBlockInfo(b, "storedItems", null);
/* 352 */       inventory.replaceExistingItem(4, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
/* 353 */       inventory.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BARRIER), "&7Empty"), false);
/* 354 */       return;
/*     */     }
/*     */     
/* 357 */     inventory.replaceExistingItem(4, getCapacityItem(b), false);
/*     */   }
/*     */   
/*     */   private void constructMenu(BlockMenuPreset preset)
/*     */   {
/* 362 */     for (int i : this.border1) {
/* 363 */       preset.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)9), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */       {
/*     */         public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 366 */           return false;
/*     */         }
/*     */       });
/*     */     }
/*     */     
/* 371 */     for (int i : this.border2) {
/* 372 */       preset.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)15), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */       {
/*     */         public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 375 */           return false;
/*     */         }
/*     */       });
/*     */     }
/*     */     
/* 380 */     for (int i : this.border3) {
/* 381 */       preset.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte)1), " ", new String[0]), new ChestMenu.MenuClickHandler()
/*     */       {
/*     */         public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 384 */           return false;
/*     */         }
/*     */       });
/*     */     }
/*     */     
/* 389 */     preset.addMenuClickHandler(4, new ChestMenu.MenuClickHandler()
/*     */     {
/*     */       public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 392 */         return false;
/*     */       }
/*     */       
/* 395 */     });
/* 396 */     preset.addMenuClickHandler(22, new ChestMenu.MenuClickHandler()
/*     */     {
/*     */       public boolean onClick(Player player, int i, ItemStack itemStack, ClickAction clickAction) {
/* 399 */         return false;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private boolean isSimiliar(ItemStack i1, ItemStack i2) {
/* 405 */     if (i1 == null) return false;
/* 406 */     if (i2 == null) { return false;
/*     */     }
/* 408 */     ItemStack itemStack1 = i1.clone();
/* 409 */     itemStack1.setAmount(1);
/* 410 */     ItemStack itemStack2 = i2.clone();
/* 411 */     itemStack2.setAmount(1);
/*     */     
/* 413 */     if (!itemStack2.hasItemMeta()) { return false;
/*     */     }
/* 415 */     if (!itemStack2.getItemMeta().hasLore()) { return false;
/*     */     }
/* 417 */     ItemMeta meta = itemStack2.getItemMeta();
/*     */     
/* 419 */     List<String> lore = meta.getLore();
/* 420 */     for (int i = 0; i <= lore.size() - 1; i++) {
/* 421 */       if (((String)lore.get(i)).equals("§b§a§r§r§e§l")) {
/* 422 */         lore.remove(i);
/* 423 */         meta.setLore(lore);
/* 424 */         itemStack2.setItemMeta(meta);
/* 425 */         break;
/*     */       }
/*     */     }
/*     */     
/* 429 */     return itemStack1.isSimilar(itemStack2);
/*     */   }
/*     */   
/*     */   private Inventory inject(Block b) {
/* 433 */     int size = BlockStorage.getInventory(b).toInventory().getSize();
/* 434 */     Inventory inv = Bukkit.createInventory(null, size);
/* 435 */     for (int i = 0; i < size; i++) {
/* 436 */       inv.setItem(i, new CustomItem(Material.COMMAND, " §4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
/*     */     }
/* 438 */     for (int slot : getOutputSlots()) {
/* 439 */       inv.setItem(slot, BlockStorage.getInventory(b).getItemInSlot(slot));
/*     */     }
/* 441 */     return inv;
/*     */   }
/*     */   
/*     */   protected boolean fits(Block b, ItemStack[] items) {
/* 445 */     return inject(b).addItem(items).isEmpty();
/*     */   }
/*     */   
/*     */   protected void pushItems(Block b, ItemStack[] items) {
/* 449 */     Inventory inv = inject(b);
/* 450 */     inv.addItem(items);
/*     */     
/* 452 */     for (int slot : getOutputSlots()) {
/* 453 */       BlockStorage.getInventory(b).replaceExistingItem(slot, inv.getItem(slot));
/*     */     }
/*     */   }
/*     */ }
