package net.justsunnit.tpoaw.backend;

public class Ticker {
	private static int ticks;

	public static void tick(){
		ticks++;
		if (ticks >= 20){
			ticks = 0;
			ActiveVote.lowerTime(1);
		}
	}
}
