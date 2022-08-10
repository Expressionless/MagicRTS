package io.sly.game.entities.resources;

import org.newdawn.slick.geom.Point;

import io.sly.game.map.Map;

public class ManaNode extends Resource {

	public ManaNode(Map map, Point pos) {
		super(map, pos, RES.getSprite("node_mana").copy());
	}
	
	public void step() {}
}
