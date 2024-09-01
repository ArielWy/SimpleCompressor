package me.olios.plugins.simplecompressor

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*


class CompressorsHandler(private val plugin: SimpleCompressor) {
    private val config = plugin.config

    fun hasCompressor(player: Player, path: String): Boolean {
        val inv = player.inventory
        val material = config.getString("$path.material")?.let { Material.getMaterial(it) } ?: return false
        val itemName = config.getString("$path.name")?.let { MiniMessage.miniMessage().deserialize(it) } ?: return false
        val lore = getLore("$path.lore")

        return inv.any { itemStack ->
            itemStack != null &&
            itemStack.type == material &&
                    itemStack.itemMeta.displayName() == itemName &&
                    itemStack.itemMeta.lore() == lore
        }
    }

    fun getCompressorItemStack(path: String): ItemStack? {
        val material = config.getString("$path.material")?.let { Material.getMaterial(it) } ?: return null
        val itemName = config.getString("$path.name")?.let { MiniMessage.miniMessage().deserialize(it) } ?: return null
        val lore = getLore("$path.lore")

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