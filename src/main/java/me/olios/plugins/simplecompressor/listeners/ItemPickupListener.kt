package me.olios.plugins.simplecompressor.listeners

import me.olios.plugins.simplecompressor.handler.CompressorsHandler
import me.olios.plugins.simplecompressor.SimpleCompressor
import me.olios.plugins.simplecompressor.handler.ItemsHandler
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent

class ItemPickupListener(private val plugin: SimpleCompressor): Listener {
    val config = plugin.config

    @EventHandler
    fun onItemPickup(event: EntityPickupItemEvent) {
        val player = event.entity
        val autoCompressor = "AutoCompressor"
        val superAutoCompressor = "SuperAutoCompressor"


        if (player !is Player) return

        if (CompressorsHandler(plugin).hasCompressor(player, autoCompressor)) {
            // the player has auto compressor
            player.sendMessage("§byou have auto compressor")
            ItemsHandler(plugin).checkInventory(player, autoCompressor)
        }

        if (CompressorsHandler(plugin).hasCompressor(player, superAutoCompressor)) {
            // the player has super auto compressor
            player.sendMessage("§9you have super auto compressor")
            ItemsHandler(plugin).checkInventory(player, superAutoCompressor)
        }
    }
}