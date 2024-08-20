package org.hinoob.nonaturalmobs;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("NoNaturalMobs has been enabled!");

        if(getConfig().getBoolean("clear_on_startup.enabled")) {
            for(String world : getConfig().getStringList("clear_on_startup.worlds")) {
                int count = 0;
                World w = Bukkit.getWorld(world);

                if(w == null) {
                    getLogger().warning("World " + world + " does not exist!");
                    continue;
                }

                for(Entity e : w.getLivingEntities()) {
                    if((e instanceof Player) || (e instanceof Tameable && ((Tameable) e).isTamed())) {
                        continue;
                    }

                    e.remove();
                    count++;
                }

                if(count > 0)
                    getLogger().info("Removed " + count + " entities from world " + world);
            }
        }

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
