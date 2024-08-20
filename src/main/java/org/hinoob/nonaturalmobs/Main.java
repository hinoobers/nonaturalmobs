package org.hinoob.nonaturalmobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("NoNaturalMobs has been enabled!");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        String reason = event.getSpawnReason().toString().toLowerCase(); // version support
        if(reason.equals("natural") || reason.equals("chunk_gen")) {
            event.setCancelled(true);
        }
    }
}
