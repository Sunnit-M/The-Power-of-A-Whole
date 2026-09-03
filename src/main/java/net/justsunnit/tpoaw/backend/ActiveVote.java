package net.justsunnit.tpoaw.backend;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.justsunnit.tpoaw.ModTemplate;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.UUID;

public class ActiveVote {
	private static String proposer;
	private static int peopleFor;
	private static int peopleAgainst;
	private static ArrayList<String> votersUUID;
	private static String command;
	private static int timeLeft;
	private static boolean active;
	private static MinecraftServer server;

	private static final ServerBossEvent bar = new ServerBossEvent(UUID.randomUUID(), Component.literal("No Current Poll")
			, BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.NOTCHED_6);

	public static void newPoll(ServerPlayer sender, String _command, MinecraftServer _server) {
		proposer = ModTemplate.debug() ? UUID.randomUUID().toString() : sender.getStringUUID();
		command = _command;
		peopleFor = 0;
		peopleAgainst = 0;
		timeLeft = 30;
		active = true;
		votersUUID = new ArrayList<>();
		votersUUID.add(proposer);
		server = _server;
		server.getPlayerList().getPlayers().forEach(bar::addPlayer);

		sendMessage("A Command has been proposed by:" + proposer);

		update();
	}

	private static void update() {
		if (!active){
			bar.removeAllPlayers();
			bar.setName(Component.empty());
			return;
		}
		if (timeLeft <= 0) {
			bar.removeAllPlayers();
			if (peopleFor > peopleAgainst) {
				try {
					execute();
				} catch (CommandSyntaxException e) {
					sendMessage(e.getMessage());
				}
			}
			active = false;
		}
		else {
			bar.setName(Component.literal(String.format("%ss - %s", timeLeft, command)));
			float percent = votersUUID.size() == 1 ? 0.5F : (float) peopleFor / ((float) votersUUID.size() - 1F);
			bar.setProgress(percent);
		}
	}

	public static void execute() throws CommandSyntaxException {
		if (!active) return;
		CommandDispatcher<CommandSourceStack> dis = server.getCommands().getDispatcher();
		dis.execute(command, server.createCommandSourceStack());
	}

	public static void lowerTime(int time) {
		if (timeLeft - time > 0) {
			timeLeft -= time;
		}
		else{
			timeLeft = 0;
		}
		update();
	}

	public static boolean isActive() {
		return active;
	}

	public static int vote(boolean vote, @Nullable ServerPlayer player){
		if (player == null) {
			return 0;
		}
		if (votersUUID.contains(player.getStringUUID())) {
			return 1;
		}

		if (vote) {
			peopleFor++;
		}
		else{
			peopleAgainst++;
		}

		votersUUID.add(ModTemplate.debug() ? UUID.randomUUID().toString() : player.getStringUUID());
		sendMessage(String.format("%s has voted %s", player.getPlainTextName(),vote ? "YES" : "NO"));
		update();
		return 2;
	}

	public static void sendMessage(String message){
		if (server == null) return;
		server.getPlayerList().getPlayers().forEach(p -> p.sendSystemMessage(Component.literal(message)));
	}
}
