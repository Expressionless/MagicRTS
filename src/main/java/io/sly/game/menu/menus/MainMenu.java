package io.sly.game.menu.menus;

import org.newdawn.slick.geom.Point;

import io.sly.engine.Engine;
import io.sly.game.menu.Menu;
import io.sly.game.states.MenuState;

public class MainMenu extends Menu {

	public MainMenu(MenuState state) {
		super(state);
	}

	@Override
	protected void init_buttons() {

		final int MENU_Y = 400;
		final int BUTTON_HEIGHT = 55;

		addButton(new Point(0, MENU_Y), "Campaign", null);
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT), "Skirmish", () -> menuState.setCurrentMenu("map_select"));
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT * 2), "Map Editor", () -> menuState.setCurrentMenu("editor"));
		addButton(new Point(0, MENU_Y + BUTTON_HEIGHT * 3), "Options", () -> Engine.setCurrentState("test"));
		
		// Quit Button
		// TODO: REMOVE MAGIC NUMBER
		addButton(new Point(Engine.getWIDTH() - 160, MENU_Y + BUTTON_HEIGHT * 4), "Quit", () -> System.exit(0));
	}
}
