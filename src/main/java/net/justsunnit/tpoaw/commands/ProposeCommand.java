package net.justsunnit.tpoaw.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.justsunnit.tpoaw.backend.ActiveVote;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ProposeCommand {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext, Commands.CommandSelection commandSelection) {
		dispatcher.register(Commands.literal("propose").requires(CommandSourceStack::isPlayer)
				.then(Commands.argument("command", StringArgumentType.greedyString())
						.suggests((ctx, builder) -> {
							StringReader reader = new StringReader(builder.getInput());
							reader.setCursor(builder.getStart());
							ParseResults<CommandSourceStack> parse = dispatcher.parse(reader, ctx.getSource());
							return dispatcher.getCompletionSuggestions(parse, builder.getStart() + builder.getRemaining().length());
						}).executes(ProposeCommand::execute)));
	}

	private static int execute(CommandContext<CommandSourceStack> context) {
		if (ActiveVote.isActive()) {
			context.getSource().sendFailure(Component.literal("ERROR : active vote"));
			return 0;
		}
		else {
			ServerPlayer player = context.getSource().getPlayer();
			ActiveVote.newPoll(player, context.getArgument("command", String.class), context.getSource().getServer());
			return 1;
		}
	}
}
