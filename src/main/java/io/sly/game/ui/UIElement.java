package io.sly.game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import io.sly.engine.Engine;
import io.sly.game.player.Player;
import io.sly.graphics.res.Sprite;
import io.sly.util.ResourceLoader;

public abstract class UIElement {
	protected static final ResourceLoader RES = Engine.RES;
	
	protected UI ui;
	protected Player player;

	protected Point uiPos;
	protected Rectangle bounding;
	
	protected Sprite sprite;
	
	public UIElement(UI ui, Point pos, float width, float height) {
		this.ui = ui;

		this.player = ui.getPlayer();

		bounding = new Rectangle(pos.getX(), pos.getY(), width, height);
		
		this.uiPos = new Point(bounding.getX() / Engine.getWIDTH(), bounding.getY() / Engine.getHEIGHT());
		ui.getElements().add(this);
	}

	public void tick() {
		if(uiPos != null) {
			//TODO: Give ui visibility to the camera and make only one ui instance instead
			//of one per player
			bounding.setX(uiPos.getX() * Engine.getWIDTH());
			bounding.setY(uiPos.getY() * Engine.getHEIGHT());
		}
		step();
	}

	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, ui.getAlpha()));
		// Comment this out if you don't want it to show please
		//g.fill(bounding);
		draw(g);
	}

	public abstract void draw(Graphics g);

	public abstract void step();

	public UI getUI() {
		return ui;
	}

	public Rectangle getBounding() {
		return bounding;
	}
}
