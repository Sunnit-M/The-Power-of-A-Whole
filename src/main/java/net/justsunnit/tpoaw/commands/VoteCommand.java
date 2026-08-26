package net.justsunnit.tpoaw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.justsunnit.tpoaw.commands.aurguments.YesNoArgument;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;


public class VoteCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
		dispatcher.register(Commands.literal("vote")
				.then(Commands.argument("answer", YesNoArgument.arg()).executes(VoteCommand::run)));
	}

	private static int run(CommandContext<CommandSourceStack> context) {
		return 0;
	}
}
