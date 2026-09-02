package net.justsunnit.tpoaw.commands.aurguments;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
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

@Deprecated
public class CommandInputArgument implements ArgumentType<String> {

	@Override
	public String parse(StringReader reader) throws CommandSyntaxException {
		String r = reader.getRemaining();
		reader.setCursor(reader.getTotalLength());
		return r;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> ctx, SuggestionsBuilder builder) {
		if (ctx.getSource() instanceof CommandSourceStack source){
			CommandDispatcher<CommandSourceStack> dis = source.dispatcher();
			String remaining = builder.getRemaining();
			ParseResults<CommandSourceStack> parse =
					dis.parse(remaining, source);
			return dis.getCompletionSuggestions(parse, builder.getStart());
		}
		return Suggestions.empty();
	}

	public static CommandInputArgument arg(){
		return new CommandInputArgument();
	}
}
