package net.justsunnit.tpoaw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.justsunnit.tpoaw.commands.aurguments.CommandInputArgument;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ProposeCommand {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext, Commands.CommandSelection commandSelection) {
		dispatcher.register(Commands.literal("propose")
				.then(Commands.argument("command",CommandInputArgument.arg()).executes(ProposeCommand::execute)));
	}

	private static int execute(CommandContext<CommandSourceStack> commandSourceStackCommandContext) {
		return 0;
	}
}
