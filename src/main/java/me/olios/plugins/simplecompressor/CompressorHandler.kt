package me.olios.plugins.simplecompressor

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


class CompressorHandler(private val plugin: SimpleCompressor) {
    private val config = plugin.config

    fun hasAutoCompressor(player: Player): Boolean {
        val inv = player.inventory
        val material = config.getString("AutoCompressor.material")?.let { Material.getMaterial(it) } ?: return false
        val itemName = config.getString("AutoCompressor.name")?.let { MiniMessage.miniMessage().deserialize(it) } ?: return false
        val lore = getLore("AutoCompressor.lore")

        player.sendMessage("material: $material")
        player.sendMessage("§6------------------------------------------")
        player.sendMessage(itemName)
        player.sendMessage("§6------------------------------------------")
        player.sendMessage(lore.toString())

        return inv.any { itemStack ->
            itemStack != null &&
            itemStack.type == material &&
                    itemStack.displayName() == itemName &&
                    itemStack.lore() == lore
        }
    }

    fun AutoCompressorItemStack(): ItemStack? {
        val material = config.getString("AutoCompressor.material")?.let { Material.getMaterial(it) } ?: return null
        val itemName = config.getString("AutoCompressor.name")?.let { MiniMessage.miniMessage().deserialize(it) } ?: return null
        val lore = getLore("AutoCompressor.lore")

        val item = ItemStack(material)
        val itemMeta = item.itemMeta
        itemMeta.displayName(itemName)
        itemMeta.lore(lore)
        item.itemMeta = itemMeta

        return item
    }

    private fun getLore(lorePath: String): MutableList<Component> {
        val stringList: List<String> = config.getStringList(lorePath)

        val deserializedLore: MutableList<Component> = ArrayList<Component>()
        for (loreLine in stringList)
            deserializedLore.add(MiniMessage.miniMessage().deserialize(loreLine))
        return deserializedLore
    }
}