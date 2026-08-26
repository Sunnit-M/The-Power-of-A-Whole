package net.justsunnit.tpoaw.commands.aurguments;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CommandInputArgument implements ArgumentType<String> {

	@Override
	public String parse(StringReader reader) throws CommandSyntaxException {
		return reader.readQuotedString();
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		if (context.getSource() instanceof CommandSourceStack source){
			CommandDispatcher<CommandSourceStack> dis = source.getServer().getCommands().getDispatcher();
			Stream<String> commands = dis.getRoot().getChildren().stream().map(CommandNode::getName);
			return SharedSuggestionProvider.suggest(commands, builder);
		}
		return Suggestions.empty();
	}
}
