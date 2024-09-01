package me.olios.plugins.simplecompressor.listeners

import me.olios.plugins.simplecompressor.CompressorHandler
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

        player.sendMessage(player.inventory.itemInMainHand.displayName())
        player.sendMessage("§6------------------------------------------")
        player.sendMessage(player.inventory.itemInMainHand.lore().toString())

        if (!CompressorHandler(plugin).hasAutoCompressor(player)) return

        player.sendMessage("HAS COMPRESSOR")
    }
}