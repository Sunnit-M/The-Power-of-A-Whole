package net.justsunnit.tpoaw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.justsunnit.tpoaw.backend.ActiveVote;
import net.justsunnit.tpoaw.commands.aurguments.CommandInputArgument;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ProposeCommand {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext, Commands.CommandSelection commandSelection) {
		dispatcher.register(Commands.literal("propose").requires(CommandSourceStack::isPlayer)
				.then(Commands.argument("command",CommandInputArgument.arg()).executes(ProposeCommand::execute)));
	}

	private static int execute(CommandContext<CommandSourceStack> context) {
		if (ActiveVote.isActive()) {
			context.getSource().sendFailure(Component.literal("ERROR : active vote"));
			return 0;
		}
		else {
			ServerPlayer player = context.getSource().getPlayer();
			ActiveVote.newPoll(player, context.getArgument("command", String.class)
					, context.getSource().getServer());
			context.getSource().getServer().getPlayerList().getPlayers().forEach(p ->
					p.sendSystemMessage(Component.literal("A Command has been proposed by:" + context.getSource().getPlayer().getPlainTextName())));
			return 1;
		}
	}
}
