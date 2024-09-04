package me.olios.plugins.simplecompressor.commands

import me.olios.plugins.simplecompressor.handler.CompressorsHandler
import me.olios.plugins.simplecompressor.SimpleCompressor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class GiveCommand(private val plugin: SimpleCompressor): CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        val command = p3?.get(0)?.lowercase() ?: return false
        if (sender !is Player) return false

        when (command) {
            "give" -> {
                when (p3[1].lowercase()) {
                    "autocompressor" -> sender.inventory.setItemInMainHand(CompressorsHandler(plugin).getCompressorItemStack("AutoCompressor"))
                    "superautocompressor" -> sender.inventory.setItemInMainHand(CompressorsHandler(plugin).getCompressorItemStack("SuperAutoCompressor"))
                    else -> sender.sendMessage("Invalid compressor type. Available: AutoCompressor, SuperAutoCompressor")
                }
                return true
            }
            "reload" -> {
                plugin.saveDefaultConfig()
                plugin.reloadConfig()
                sender.sendMessage("§aThe configuration file has been reloaded.")
                return true
            }
            else -> {}
        }
        return false
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>?
    ): MutableList<String>? {
        if (p1.name.equals("simplecompressor", ignoreCase = true)) {
            if (p3!!.size == 1)
                return mutableListOf("give", "reload")
            if (p3.size == 2)
                return mutableListOf("AutoCompressor", "SuperAutoCompressor")
        }
        return null
    }
}