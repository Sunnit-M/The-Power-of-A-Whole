package net.justsunnit.tpoaw.platform.fabric;

//? fabric {

import net.fabricmc.api.DedicatedServerModInitializer;
import net.justsunnit.tpoaw.ModTemplate;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;

@Entrypoint("main")
public class FabricEntrypoint implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		ModTemplate.onInitialize();
		FabricRegister.Register();
	}
}
//?}
