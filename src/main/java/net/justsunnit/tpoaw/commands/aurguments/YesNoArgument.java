package net.justsunnit.tpoaw.commands.aurguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class YesNoArgument implements ArgumentType<YesNoEnum> {

	@Override
	public YesNoEnum parse(StringReader reader) throws CommandSyntaxException {
		String s = reader.readUnquotedString();
		if (s.equalsIgnoreCase("yes")) {
			return YesNoEnum.YES;
		}
		else return YesNoEnum.NO;
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		return context.getSource() instanceof SharedSuggestionProvider
				? SharedSuggestionProvider.suggest(Arrays.stream(YesNoEnum.values()).map(YesNoEnum::getSerializedName), builder)
				: Suggestions.empty();
	}

	public static YesNoArgument arg() {
		return new YesNoArgument();
	}
}
