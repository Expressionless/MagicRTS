package io.sly.input;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;

import io.sly.engine.Engine;
import io.sly.game.Game;
import io.sly.game.GameObject;
import io.sly.game.player.Camera;
import io.sly.game.states.GameState;

public class Mouse implements MouseListener {

	public static final int MOUSE_LEFT  = 0,
							MOUSE_RIGHT = 1,
							MOUSE_MIDDLE = 2;
	
	private Point pos, screenPos;
	private Input input;

	private final boolean[] button = new boolean[30];

	public Mouse(Input input) {
		this.input = input;
		input.addMouseListener(this);
		pos = new Point(input.getMouseX(), input.getMouseY());
		screenPos = new Point(input.getMouseX(), input.getMouseY());
	}

	public void update() {

		Point raw = new Point(input.getMouseX(), input.getMouseY());

		if (Engine.getCurrentState() instanceof GameState) {
			Camera c = ((GameState) Engine.getCurrentState()).getGame().getMap().getMainCamera();
			if (c != null) {
				Point targetPoint = Game.UIToObject(raw, c);

				pos.setX(targetPoint.getX());
				pos.setY(targetPoint.getY());
			}
		} else {
			pos.setX(raw.getX());
			pos.setY(raw.getY());
		}

		screenPos.setX(raw.getX());
		screenPos.setY(raw.getY());
	}

	public boolean overObject() {
		ArrayList<GameObject> objects = GameObject.getObjects();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getCollider().contains(pos))
				return true;
		}
		return false;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {

	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {

	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {

	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		this.button[button] = true;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.button[button] = false;
	}

	@Override
	public void mouseWheelMoved(int change) {

	}

	public boolean[] getButton() {
		return button;
	}

	public Point getPos() {
		return pos;
	}

	public Point getScreenPos() {
		return screenPos;
	}

}
