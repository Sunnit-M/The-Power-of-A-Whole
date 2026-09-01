package net.justsunnit.tpoaw.backend;

import net.justsunnit.tpoaw.ModTemplate;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;

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
		_server.getPlayerList().getPlayers().forEach(bar::addPlayer);

		update();
	}

	private static void update(){
		if (timeLeft <= 0) {
			bar.removeAllPlayers();

		}
		else {
			bar.setName(Component.literal(String.format("%ss - %s", timeLeft, command)));
			float percent = votersUUID.size() == 1 ? 0.5F : (float) peopleFor / ((float) votersUUID.size() - 1F);
			bar.setProgress(percent);
		}
	}

	public static void lowerTime(int time) {
		ActiveVote.timeLeft -= time;
		update();
	}

	public static boolean isActive() {
		return active;
	}

	public static void vote(boolean vote, UUID uuid){
		if (vote) {
			peopleFor++;
		}
		else{
			peopleAgainst++;
		}

		votersUUID.add(ModTemplate.debug() ? UUID.randomUUID().toString() : uuid.toString());
		update();
	}
}
