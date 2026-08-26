package net.justsunnit.tpoaw.platform.neoforge;

//? neoforge {

/*import net.justsunnit.tpoaw.ModTemplate;
import net.justsunnit.tpoaw.commands.ProposeCommand;
import net.justsunnit.tpoaw.commands.VoteCommand;
import net.justsunnit.tpoaw.commands.aurguments.YesNoArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoforgeRegister {
	public static final DeferredRegister<ArgumentTypeInfo<?, ?>> ARGUMENT_TYPES =
			DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, ModTemplate.MOD_ID);

	public static final DeferredHolder<ArgumentTypeInfo<?, ?>, SingletonArgumentInfo<YesNoArgument>> SPELL = ARGUMENT_TYPES.register(
			"spell",
			() -> ArgumentTypeInfos.registerByClass(
					YesNoArgument.class,
					SingletonArgumentInfo.contextFree(YesNoArgument::arg)
			)
	);

	@SubscribeEvent
	public static void RegisterCommands(RegisterCommandsEvent event){
		VoteCommand.register(event.getDispatcher(),event.getBuildContext(),event.getCommandSelection());
		ProposeCommand.register(event.getDispatcher(),event.getBuildContext(),event.getCommandSelection());
	}
}
*///? }
