package net.justsunnit.tpoaw.platform.fabric;
//? fabric {

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.justsunnit.tpoaw.ModTemplate;
import net.justsunnit.tpoaw.backend.Ticker;
import net.justsunnit.tpoaw.commands.ProposeCommand;
import net.justsunnit.tpoaw.commands.VoteCommand;
import net.justsunnit.tpoaw.commands.aurguments.CommandInputArgument;
import net.justsunnit.tpoaw.commands.aurguments.YesNoArgument;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;

public class FabricRegister {
	public static void Register(){
		CommandRegistrationCallback.EVENT.register(VoteCommand::register);
		CommandRegistrationCallback.EVENT.register(ProposeCommand::register);

		ArgumentTypeRegistry.registerArgumentType(ModTemplate.id("yes_no"), YesNoArgument.class
				, SingletonArgumentInfo.contextFree(YesNoArgument::arg));

		ServerTickEvents.START_SERVER_TICK.register(server -> {
			Ticker.tick();
		});
	}
}
//? }
