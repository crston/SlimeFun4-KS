/*    */ package me.john000708.barrels.listeners;
/*    */ 
/*    */ import me.john000708.barrels.Barrels;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryPickupItemEvent;
/*    */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class DisplayListener implements org.bukkit.event.Listener
/*    */ {
/*    */   public DisplayListener()
/*    */   {
/* 16 */     org.bukkit.Bukkit.getPluginManager().registerEvents(this, Barrels.plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPickpup(PlayerPickupItemEvent e) {
/* 21 */     if ((!e.getItem().hasMetadata("no_pickup")) && (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta().hasDisplayName()) && (e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith("§6§lB4R3L - §eITEM"))) {
/* 22 */       e.setCancelled(true);
/* 23 */       e.getItem().remove();
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onInventoryPickup(InventoryPickupItemEvent e) {
/* 29 */     if ((!e.getItem().hasMetadata("no_pickup")) && (e.getItem().getItemStack().hasItemMeta()) && (e.getItem().getItemStack().getItemMeta().hasDisplayName()) && (e.getItem().getItemStack().getItemMeta().getDisplayName().startsWith("§6§lB4R3L - §eITEM"))) {
/* 30 */       e.setCancelled(true);
/* 31 */       e.getItem().remove();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\bobas\Downloads\Barrels_v1.11.jar!\me\john000708\barrels\listeners\DisplayListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */