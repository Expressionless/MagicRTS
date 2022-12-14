package io.sly.game.player;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import io.sly.engine.Engine;
import io.sly.game.Game;
import io.sly.game.entities.controllables.Controllable;
import io.sly.game.entities.controllables.Unit;
import io.sly.input.Mouse;

public class Selector implements MouseListener {

	private Point startPoint = null, endPoint = null;

	private Player player;
	private Camera camera;

	private boolean selectedSomething = false;

	private Rectangle selectBox;

	private boolean selecting = false;

	public Selector(Player p, Input input) {
		input.addMouseListener(this);
		player = p;

		camera = player.getCamera();
		selectBox = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		if (startPoint != null) {
			if (endPoint != null) {
				Point p = Game.UIToObject(startPoint, camera);

				selectBox.setX(p.getX());
				selectBox.setY(p.getY());

				float width = (endPoint.getX() - startPoint.getX()) / camera.getZoom();
				float height = (endPoint.getY() - startPoint.getY()) / camera.getZoom();

				selectBox.setWidth(width);
				selectBox.setHeight(height);
			}
		}
	}

	/**
	 * Get the nearest Controllable to the mouse on the map
	 * 
	 * @return returns nearest Controllable
	 */
	public Controllable getNearestControllable() {
		ArrayList<Controllable> controllables = Controllable.getControllables();
		Controllable current = controllables.get(0);

		Mouse m = Engine.getMouse();

		float curDist = current.getDistanceTo(m.getPos()), nextDist;

		for (int i = 0; i < controllables.size(); i++) {
			nextDist = controllables.get(i).getDistanceTo(m.getPos());
			if (nextDist < curDist) {
				current = controllables.get(i);
			}
		}

		return current;
	}

	public float getDist(Point p1, Point p2) {
		float distX = (float) Math.pow(p1.getX() - p2.getX(), 2);
		float distY = (float) Math.pow(p1.getY() - p2.getY(), 2);
		return (float) Math.pow(distX + distY, 0.5);

	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public boolean isAcceptingInput() {
		if (player.getUI() != null) {
			if (!player.getUI().contains(new Point(Engine.getInput().getMouseX(), Engine.getInput().getMouseY()))
					|| selecting) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if (startPoint != null) {
			if (camera != null) {
				endPoint = new Point(newx, newy);
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (camera != null) {
			switch (button) {
			case Mouse.MOUSE_LEFT:
				startPoint = new Point(x, y);

				selecting = true;

				// Clear the selected units
				if (!selectedSomething)
					player.getSelected().clear();

				// Handle single selection
				Controllable nearest = getNearestControllable();

				if (nearest.isMouseOver()) {
					player.getSelected().add(nearest);
					selectedSomething = true;
				}
				break;

			case Mouse.MOUSE_RIGHT:
				if (player.getSelected().size() > 0) {
					for (Controllable controllable : player.getSelected()) {
						if (controllable instanceof Unit) {
							if (controllable.getPlayer() == player) {
								Unit unit = (Unit) controllable;
								Point p = Game.UIToObject(new Point(x, y), camera);
								unit.setPath(null);
								unit.getDes().setX(p.getX());
								unit.getDes().setY(p.getY());
							}
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == Mouse.MOUSE_LEFT) {
			ArrayList<Controllable> controllables = Controllable.getControllables();
			ArrayList<Controllable> selected = new ArrayList<Controllable>();

			selecting = false;
			if (startPoint != null && endPoint != null) {
				for (Controllable controllable : controllables) {
					if (controllable.getPlayer() == player) {
						Point p = Game.UIToObject(startPoint, camera);

						float width = (endPoint.getX() - startPoint.getX()) / camera.getZoom();
						float height = (endPoint.getY() - startPoint.getY()) / camera.getZoom();

						float centX = p.getX() + width / 2;
						float centY = p.getY() + height / 2;

						boolean safeX = Math.abs(controllable.getPos().getX() - centX) < Math.abs(width) / 2;
						boolean safeY = Math.abs(controllable.getPos().getY() - centY) < Math.abs(height) / 2;

						if (safeX && safeY) {
							if (!selected.contains(controllable)) {
								selected.add(controllable);
							}
						}
					}
				}
			} else if (startPoint != null) {
				if (camera != null) {
					Controllable controllable = getNearestControllable();
					float centX = controllable.getCollider().getWidth() / 2 + controllable.getCollider().getX();
					float centY = controllable.getCollider().getHeight() / 2 + controllable.getCollider().getY();

					Point toGame = Game.UIToObject(new Point(x, y), camera);

					boolean safeX = Math.abs(toGame.getX() - centX) < controllable.getCollider().getWidth() / 2;
					boolean safeY = Math.abs(toGame.getY() - centY) < controllable.getCollider().getHeight() / 2;
					if (safeX && safeY)
						selected.add(controllable);
				}
			}

			player.setSelected(selected);

			endPoint = null;
			startPoint = null;
			selectedSomething = false;
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
	}

	public Point getStartDrag() {
		return startPoint;
	}

	public Point getEndDrag() {
		return endPoint;
	}

	public Rectangle getSelectBox() {
		return selectBox;
	}

}
