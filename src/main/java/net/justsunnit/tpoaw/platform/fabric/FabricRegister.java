package net.justsunnit.tpoaw.platform.fabric;
//? fabric {

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.justsunnit.tpoaw.commands.VoteCommand;

public class FabricRegister {
	public static void Register(){
		CommandRegistrationCallback.EVENT.register(VoteCommand::register);
	}
}
//? }
