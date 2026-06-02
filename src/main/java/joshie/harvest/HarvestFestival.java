package joshie.harvest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import joshie.harvest.core.HFApiLoader;
import joshie.harvest.core.HFTrackers;
import joshie.harvest.core.commands.CommandManager;
import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.core.proxy.HFCommonProxy;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
		modid = HFModInfo.MODID,
		name = HFModInfo.MODNAME,
		version = HFModInfo.VERSION,
		dependencies = HFModInfo.DEPENDENCIES,
		guiFactory = HFModInfo.GUI_FACTORY)
public class HarvestFestival {
	@SidedProxy(clientSide = HFModInfo.JAVAPATH + "core.proxy.HFClientProxy", serverSide = HFModInfo.JAVAPATH + "core.proxy.HFCommonProxy")
	public static HFCommonProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(HFModInfo.MODNAME);

	@Instance(HFModInfo.MODID)
	public static HarvestFestival instance;

	public static ResourceLocation id(String path) {
		if (path.contains(":")) {
			return new ResourceLocation(path);
		} else {
			return new ResourceLocation(HFModInfo.MODID, path);
		}
	}

	@EventHandler
	public void onConstruction(FMLConstructionEvent event) {
		HFApiLoader.init(event.getASMHarvestedData()); //Load in the api once construction is done
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.setup(event.getAsmData()); //Load all the classes
		proxy.setupConfig(event.getSuggestedConfigurationFile());
		proxy.configure();
		proxy.load("preInit");
		proxy.loadAPI(event.getAsmData());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.load("init");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.load("postInit");
	}

	@EventHandler
	public void onLoadComplete(FMLLoadCompleteEvent event) {
		proxy.load("complete");
	}

	@EventHandler
	public void onMapping(FMLModIdMappingEvent event) {
		proxy.load("remap");
	}

	@EventHandler
	public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
		HFTrackers.resetServer();
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		ICommandManager manager = event.getServer().getCommandManager();
		if (manager instanceof ServerCommandManager) {
			((ServerCommandManager) manager).registerCommand(CommandManager.INSTANCE);
		}

		proxy.load("onServerStarting");
	}
}