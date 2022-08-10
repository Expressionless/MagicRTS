package io.sly.game.entities.resources;

import org.newdawn.slick.geom.Point;

import io.sly.game.entities.Renderable;
import io.sly.game.map.Map;
import io.sly.graphics.res.Sprite;

public abstract class Resource extends Renderable {

	public Resource(Map map, Point pos, Sprite anims) {
		super(pos);
	}
}
