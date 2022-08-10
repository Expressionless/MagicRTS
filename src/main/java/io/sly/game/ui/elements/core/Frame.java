package io.sly.game.ui.elements.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import io.sly.engine.Engine;
import io.sly.game.ui.UI;
import io.sly.game.ui.UIElement;
import io.sly.graphics.res.Sprite;

public class Frame extends UIElement {

	private Sprite sprite;
	
	private int width;
	
	public Frame(UI ui, Point pos) {
		super(ui, pos, Engine.getWIDTH() ,64);
		
		sprite = player.getFaction().getSprite("ui_bottombar");
	}

	@Override
	public void draw(Graphics g) {

		int sW = sprite.getWidth();

		width = (int)Math.ceil((float)Engine.getWIDTH() / (float)sW);
		

		for (int x = 0; x < width; x++) {
			
				float xx = bounding.getX() + (x * sW);
				float yy = bounding.getY();
				
				// TODO: Refactor drawing for frame
				// Create a renderer?
				sprite.draw(xx, yy);
		}
	}

	@Override
	public void step() {

	}

}
