package io.sly.game.entities.controllables.abilities;

import org.newdawn.slick.geom.Point;

import io.sly.GameConstants;
import io.sly.game.entities.ai.pathfinding.Path;
import io.sly.game.entities.controllables.Controllable;
import io.sly.game.entities.controllables.Unit;
import io.sly.game.entities.controllables.UnitStat;

public interface BasicCommandable {

	/*
	 * An interface for a basic list of commands
	 */
	
	// Patrol a set of points
	public default void patrol(Unit unit, Point[] points) {
		// Find a path to the next point
		
		if(unit.getPath() == null) {
			int len = points.length;
			if(unit.getCurPoint() + 1 < len)
				unit.setCurPoint(unit.getCurPoint() + 1);
			else {
				unit.setCurPoint(0);
			}
			Path p = unit.findPath(points[unit.getCurPoint()]);
			unit.setPath(p);
		} else {
			unit.moveAlongPath(unit.getPath());
		}
	}
	
	public default void attack(Unit unit, Controllable target) {
		if(unit.getDistanceTo(target.getPos()) > unit.getStat(UnitStat.ATTACK_RANGE) * GameConstants.TW_RENDER) {
			unit.setState(GameConstants.STATE_MOVING);
		} else {
			float attack_dmg = unit.getStat(UnitStat.ATTACK);
			target.addToStat(UnitStat.HP, -attack_dmg);
			// TODO: Make an attack anim
		}
	}
}
