package net.justsunnit.tpoaw.commands.aurguments;

import net.justsunnit.tpoaw.ModTemplate;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public enum YesNoEnum implements StringRepresentable {
	YES(0, "yes"),
	NO(1, "no");

	private final int id;
	private final String name;
	private final Component translatedName;

	YesNoEnum(final int id, final String name){
		this.id = id;
		this.name = name;
		this.translatedName = Component.translatable(ModTemplate.MOD_ID + name);
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	public Component getGoverment(){
		return this.translatedName;
	}
}
