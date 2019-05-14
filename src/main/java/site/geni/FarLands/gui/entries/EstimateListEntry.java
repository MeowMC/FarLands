package site.geni.FarLands.gui.entries;

import me.shedaniel.cloth.gui.entries.StringListEntry;

import static org.lwjgl.glfw.GLFW.*;

public class EstimateListEntry extends StringListEntry {

	public EstimateListEntry(String fieldName, String value) {
		super(fieldName, value, null);
	}

	private static boolean handlePress(int charCode) {
		switch (charCode) {
			case GLFW_KEY_RIGHT:
			case GLFW_KEY_LEFT:
			case GLFW_KEY_UP:
			case GLFW_KEY_DOWN:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean charTyped(char character, int charCode) {
		return EstimateListEntry.handlePress(charCode) || super.charTyped(character, charCode);
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		return EstimateListEntry.handlePress(charCode) || super.keyPressed(charCode, int_1, int_2);
	}
}