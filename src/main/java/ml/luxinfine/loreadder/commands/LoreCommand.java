package ml.luxinfine.loreadder.commands;

import ml.luxinfine.loreadder.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import java.io.FileWriter;
import java.io.IOException;

public class LoreCommand extends CommandBase {
	
	@Override
	public String getCommandName() {
		return "lore";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "/lore <info>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		ItemStack item = ((EntityPlayer) sender).inventory.getCurrentItem();
		String name = Item.itemRegistry.getNameForObject(item.getItem());
		int meta = item.getItemDamage();

		StringBuilder msg = new StringBuilder();
		for (String arg : args) msg.append(arg).append(" ");

		String lore = msg.toString().trim();

		String toFile;
		if(meta == 0) toFile = "\n<" + name + ">.addTooltip(\"" + lore + "\");";
		else toFile = "\n<" + name + ":" + meta + ">.addTooltip(\"" + lore + "\");";

		this.write(toFile);

		if(Config.reload) MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), Config.cmd);
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return p_71519_1_ instanceof EntityPlayer;
	}

	private void write(String to) {
		try(FileWriter writer = new FileWriter(Config.path, true)) {
			writer.write(to);
			writer.flush();
		} catch(IOException ex) { ex.printStackTrace(); }
	}
}
	
	
	