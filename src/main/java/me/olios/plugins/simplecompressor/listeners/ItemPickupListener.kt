package me.olios.plugins.simplecompressor.listeners

import me.olios.plugins.simplecompressor.CompressorsHandler
import me.olios.plugins.simplecompressor.SimpleCompressor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent

class ItemPickupListener(private val plugin: SimpleCompressor): Listener {
    val config = plugin.config

    @EventHandler
    fun onItemPickup(event: EntityPickupItemEvent) {
        val player = event.entity
        val item = event.item.itemStack

        if (player !is Player) return

        if (CompressorsHandler(plugin).hasCompressor(player, "AutoCompressor")) {
            // the player has auto compressor
            player.sendMessage("§byou have auto compressor")

        }

        if (CompressorsHandler(plugin).hasCompressor(player, "SuperAutoCompressor")) {
            // the player has super auto compressor
            player.sendMessage("§9you have super auto compressor")

        }
    }
}