package net.justsunnit.tpoaw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.justsunnit.tpoaw.backend.ActiveVote;
import net.justsunnit.tpoaw.commands.aurguments.YesNoArgument;
import net.justsunnit.tpoaw.commands.aurguments.YesNoEnum;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class VoteCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
		dispatcher.register(Commands.literal("vote")
				.then(Commands.argument("answer", YesNoArgument.arg()).executes(VoteCommand::run)));
	}

	private static int run(CommandContext<CommandSourceStack> context) {
		if (ActiveVote.isActive()) {
			int outcome = ActiveVote.vote(context.getArgument("answer", YesNoEnum.class).equals(YesNoEnum.YES),
					context.getSource().getPlayer());
			if (outcome == 2) {
				return 1;
			} else if (outcome == 1) {
				context.getSource().getPlayer().sendSystemMessage(Component.literal("Already Voted"));
				return 0;
			}
			else if(outcome == 0){
				return 0;
			}
			return 1;
		}
		else {
			return 0;
		}
	}
}
