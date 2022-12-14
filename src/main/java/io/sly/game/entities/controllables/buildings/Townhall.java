package io.sly.game.entities.controllables.buildings;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import io.sly.game.entities.controllables.generic.Worker;
import io.sly.game.player.Player;

public class Townhall extends Building {

	public Townhall(Player player, Point _pos) {
		super(player, _pos, player.getFaction().getSprite("townhall").copy());

		for(int i = -2; i < 1; i++) {
			// Spawn Workers
			new Worker(player, mapPos.getX() + i * 60, mapPos.getY() + (i + 3) * 60);
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public void step() {
		
	}

}
