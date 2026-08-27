package net.justsunnit.tpoaw.platform.fabric;

//? fabric {

import net.fabricmc.api.ModInitializer;
import net.justsunnit.tpoaw.ModTemplate;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		ModTemplate.onInitialize();
		FabricRegister.Register();
	}
}
//?}
