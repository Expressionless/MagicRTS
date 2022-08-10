package io.sly.game.entities;

import org.newdawn.slick.geom.Point;

import io.sly.game.map.Map;
import io.sly.game.player.Player;
import io.sly.graphics.res.Sprite;

public abstract class Projectile extends Renderable {
	
	// TODO: Consider vector2D
	protected float speed, direction;
	protected Point target;

	public Projectile(Player p, Map map, Point pos, Sprite sprite, Point target) {
		super(pos);
		this.target = target;
	}
	
}
