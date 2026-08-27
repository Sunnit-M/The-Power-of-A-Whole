package net.justsunnit.tpoaw.platform.neoforge;

//? neoforge {

/*import net.justsunnit.tpoaw.ModTemplate;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ModTemplate.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus iEventBus) {
		if (FMLLoader.getCurrent().getDist().isClient()) return;
		NeoforgeRegister.ARGUMENT_TYPES.register(iEventBus);
		NeoForge.EVENT_BUS.register(NeoforgeRegister.class);
		ModTemplate.onInitialize();
	}
}
*///?}
