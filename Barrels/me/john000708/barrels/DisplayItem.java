/*    */ package me.john000708.barrels;
/*    */ 
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
/*    */ import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
/*    */ import me.mrCookieSlime.Slimefun.SlimefunStartup;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class DisplayItem
/*    */ {
/*    */   public static void updateDisplayItem(Block b, int capacity, boolean allow)
/*    */   {
/* 25 */     if (!allow) {
/* 26 */       removeDisplayItem(b);
/* 27 */       return;
/*    */     }
/*    */     
/* 30 */     ItemStack stack = new CustomItem(new ItemStack(Material.BARRIER), 1);
/* 31 */     String nametag = "§cEmpty";
/*    */     
/* 33 */     BlockMenu menu = BlockStorage.getInventory(b);
/* 34 */     if (BlockStorage.getBlockInfo(b, "storedItems") != null) {
/* 35 */       int storedItems = Integer.valueOf(BlockStorage.getBlockInfo(b, "storedItems")).intValue();
/* 36 */       stack = menu.getItemInSlot(22).clone();
/* 37 */       nametag = ChatColor.translateAlternateColorCodes('&', Barrels.config.getString("options.item-format"));
/* 38 */       nametag = nametag.replace("<storedAmount>", String.valueOf(storedItems));
/* 39 */       nametag = nametag.replace("<storedPercentage>", String.valueOf(Math.round(storedItems / capacity * 100.0F)));
/* 40 */       nametag = nametag.replace("<storedItem>", StringUtils.formatItemName(stack, false));
/*    */     }
/*    */     
/* 43 */     Item entity = getEntity(b);
/* 44 */     if (entity == null) {
/* 45 */       entity = b.getWorld().dropItem(new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 1.2D, b.getZ() + 0.5D), new CustomItem(stack, "§6§lB4R3L - §eITEM" + System.nanoTime()));
/* 46 */       entity.setVelocity(new Vector(0.0D, 0.1D, 0.0D));
/* 47 */       entity.setMetadata("no_pickup", new FixedMetadataValue(SlimefunStartup.instance, "barrel"));
/* 48 */       entity.setCustomNameVisible(true);
/*    */     }
/*    */     else {
/* 51 */       entity.setItemStack(new CustomItem(stack, "§6§lB4R3L - §eITEM" + System.nanoTime()));
/*    */     }
/*    */     
/* 54 */     entity.setCustomName(nametag);
/*    */   }
/*    */   
/*    */   public static void removeDisplayItem(Block b) {
/* 58 */     for (Entity n : b.getChunk().getEntities()) {
/* 59 */       if (((n instanceof Item)) && 
/* 60 */         (b.getLocation().add(0.5D, 1.2D, 0.5D).distanceSquared(n.getLocation()) < 1.0D) && (((Item)n).getItemStack().hasItemMeta()) && (((Item)n).getItemStack().getItemMeta().getDisplayName().startsWith("§6§lB4R3L - §eITEM"))) {
/* 61 */         n.remove();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   private static Item getEntity(Block b) {
/* 67 */     for (Entity n : b.getChunk().getEntities()) {
/* 68 */       if (((n instanceof Item)) && 
/* 69 */         (b.getLocation().add(0.5D, 1.2D, 0.5D).distanceSquared(n.getLocation()) < 1.0D) && (((Item)n).getItemStack().hasItemMeta()) && (((Item)n).getItemStack().getItemMeta().getDisplayName().startsWith("§6§lB4R3L - §eITEM"))) {
/* 70 */         return (Item)n;
/*    */       }
/*    */     }
/* 73 */     return null;
/*    */   }
/*    */ }