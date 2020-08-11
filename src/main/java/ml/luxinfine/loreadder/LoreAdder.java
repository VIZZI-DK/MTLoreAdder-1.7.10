package ml.luxinfine.loreadder;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import ml.luxinfine.loreadder.commands.LoreCommand;

@Mod(name = "LoreAdder", modid = "loreadder", version = "1.0", acceptableRemoteVersions = "*")
public class LoreAdder {
    
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) { event.registerServerCommand(new LoreCommand()); }
}
