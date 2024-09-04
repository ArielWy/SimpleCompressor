package me.olios.plugins.simplecompressor.handler

import me.olios.plugins.simplecompressor.SimpleCompressor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class ItemsHandler(private val plugin: SimpleCompressor) {
    private val config = plugin.config

    fun checkInventory(player: Player, path: String) {
        val inventory = player.inventory

        inventory.forEach { itemStack ->
            if (itemStack == null) return@forEach

            val displayName = itemStack.itemMeta.displayName()
            if (displayName != null) {
                player.sendMessage(displayName)
            }
            val compressedName = getDisplayNameValue(player, displayName, path) ?: return@forEach

            val stackCount = countStacks(inventory, compressedName)

            player.sendMessage(compressedName)
            player.sendMessage(MiniMessage.miniMessage().deserialize("<green>You have $stackCount stacks of $compressedName</green>"))
        }
    }

    private fun countStacks(inventory: Inventory, compressedName: Component): Int {
        var count = 0
        inventory.forEach { itemStack ->
            if (itemStack.itemMeta.displayName() == compressedName) {
                count += itemStack.amount
            }
        }
        return count / 64
    }

    private fun getDisplayNameValue(player: Player, itemName: Component?, path: String): Component? {
        val names = config.getMapList(path)
        for (name in names) {
            val configItemName = MiniMessage.miniMessage().deserialize(name["item-name"] as String)
            val compressedName = MiniMessage.miniMessage().deserialize(name["compressed-item-name"] as String)
            player.sendMessage(compressedName)
            if (itemName == configItemName)
                return compressedName
        }
        return null  // return null if the name doesn't exist
    }
}