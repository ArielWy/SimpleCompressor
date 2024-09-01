package me.olios.plugins.simplecompressor

import me.olios.plugins.simplecompressor.commands.GiveCommand
import me.olios.plugins.simplecompressor.listeners.ItemPickupListener
import org.bukkit.plugin.java.JavaPlugin

class SimpleCompressor : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        saveDefaultConfig()

        registerListeners()
        registerCommands()
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(ItemPickupListener(this), this)
    }

    private fun registerCommands() {
        getCommand("simplecompressor")?.setExecutor(GiveCommand(this))
    }
}