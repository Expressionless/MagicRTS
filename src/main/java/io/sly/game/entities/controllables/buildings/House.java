package io.sly.game.entities.controllables.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import io.sly.game.player.Player;

public class House extends Building {

	public House(Player player, Point pos) {
		super(player, pos, player.getFaction().getSprite("house").copy());
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {
		
	}

}
