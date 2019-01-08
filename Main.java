package me.Petrosaurus.Permisse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener, CommandExecutor{
	public void onEnable(){
		getCommand("ahoj").setExecutor(this);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	//nazevpluginu.prikaz
	//ahojplugin.ahoj
	//ahojplugin.break
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("ahoj")){
			if (sender instanceof Player){
				Player p = (Player) sender;
				
				if (p.hasPermission("ahojplugin.ahoj")){
					p.sendMessage(ChatColor.GREEN + "Ahoj, vitej na serveru!");
					return true;
				}
				else {
					p.sendMessage(ChatColor.RED + "Na tento prikaz nemas povoleni!");
					return false;
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.DIAMOND_ORE){
			if (!p.hasPermission("ahojplugin.break")){
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Nemuzes kopat diamanty!");
			}
			else {
				p.sendMessage(ChatColor.GREEN + "Vykopal jsi diamanty!");
			}
		}
	}
	

}
