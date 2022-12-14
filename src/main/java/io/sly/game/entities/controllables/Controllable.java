package io.sly.game.entities.controllables;

import java.util.ArrayList;

import org.newdawn.slick.geom.Point;

import io.sly.game.entities.Renderable;
import io.sly.game.map.Map;
import io.sly.game.player.Player;
import io.sly.graphics.res.Sprite;

public abstract class Controllable extends Renderable {

	private static final ArrayList<Controllable> OBJECTS = new ArrayList<Controllable>();
	
	protected Player player;
	protected Map map;
	
	protected float[] stats;
	
	public Controllable(Point pos, Sprite sprite, Player player) {
		super(pos, sprite);
		this.player = player;
		stats = new float[UnitStat.values().length];
		OBJECTS.add(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMouseOver() {
		return false;
	}
	

	/**
	 * Get a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 */
	public float getStat(UnitStat stat) {
		int val = stat.val();
		return stats[val];
	}

	/**
	 * Add to a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 * @param num - float value to assign to the stat
	 */
	public void addToStat(UnitStat stat, float num) {
		int val = stat.val();
		stats[val] += num;
	}
	
	/**
	 * Set a stat from the unit
	 * @param stat - UnitStat (One of the UnitStat constants)
	 * @param num - float value to assign to the stat
	 */
	public void setStat(UnitStat stat, float num) {
		int val = stat.val();
		stats[val] = num;
	}	
	
	// Getters and Setters
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public static ArrayList<Controllable> getControllables() {
		return OBJECTS;
	}

}
