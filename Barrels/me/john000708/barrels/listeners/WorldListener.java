/*    */ package me.john000708.barrels.listeners;
/*    */ 
/*    */ import me.john000708.barrels.Barrels;
/*    */ import me.mrCookieSlime.Slimefun.api.BlockStorage;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBurnEvent;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ 
/*    */ public class WorldListener
/*    */   implements Listener
/*    */ {
/*    */   public WorldListener()
/*    */   {
/* 17 */     Bukkit.getPluginManager().registerEvents(this, Barrels.plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onFireSpread(BlockBurnEvent e) {
/* 22 */     if (e.getBlock() == null) { return;
/*    */     }
/* 24 */     String id = BlockStorage.checkID(e.getBlock());
/* 25 */     if ((id != null) && (id.startsWith("BARREL_"))) e.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\bobas\Downloads\Barrels_v1.11.jar!\me\john000708\barrels\listeners\WorldListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */