package io.sly.game.entities.ai.pathfinding;

import static io.sly.GameConstants.TH_RENDER;
import static io.sly.GameConstants.TW_RENDER;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import io.sly.game.map.Map;
import io.sly.game.player.Camera;

public class NodeMap {

	public static final int RES = 2;

	public static final float NODE_WIDTH = TW_RENDER / RES;
	public static final float NODE_HEIGHT = TH_RENDER / RES;

	public static final float XOFFSET = (NODE_WIDTH / 2);
	public static final float YOFFSET = (NODE_HEIGHT / 2);

	private Map map;
	
	private Node[][] nodes;
	
	public NodeMap(Map m) {
		map = m;
	}


	public static NodeMap createNodeMap(Map m) {
		ArrayList<String> mapData = m.getMapData();

		int mapHeight = mapData.size();
		int mapWidth = mapData.get(0).split(" ").length;

		NodeMap nm = new NodeMap(m);

		String[][] mD = new String[mapHeight][mapWidth];

		Node[][] nodes = new Node[mapWidth * RES][mapHeight * RES];

		for (int i = 0; i < nodes[0].length; i++) {

			mD[i / RES] = mapData.get(i / RES).split(" ");
			for (int j = 0; j < nodes.length; j++) {
				int cost = 0;

				int tileVal = Integer.parseInt(mD[i / RES][j / RES]);

				if (tileVal == 0)
					cost = 0;
				else if (tileVal == 1 || tileVal == 2)
					cost = 100;
				nodes[i][j] = new Node(nm, j * NODE_WIDTH + XOFFSET, i * NODE_HEIGHT + YOFFSET, cost, i * mapHeight + j);
			}
		}
		nm.setNodes(nodes);
		System.out.print(m.getMapWidth());
		System.out.print(",");
		System.out.println(nodes.length);
		
		return nm;
	}

	public void render(Graphics g) {

		for (int i = 0; i < nodes.length; i++) {

			for (int j = 0; j < nodes[i].length; j++) {
				Node n = nodes[i][j];

				Camera c = map.getFocusedPlayer().getCamera();

				Point pos = new Point(n.getPos().getX() - NODE_WIDTH / 4, n.getPos().getY() - NODE_HEIGHT / 4);
				
				if (c.getRenderRect().contains(pos)) {

					float red = (n.getCost() / 100);
					float green = (1f - (n.getCost() / 100));
					float blue = 0;

					g.setColor(new Color(red, green, blue));
					g.fillOval(pos.getX(), pos.getY(), NODE_WIDTH / 2,
							NODE_HEIGHT / 2);

					g.setColor(Color.black);
					g.drawString(Integer.toString(n.getId()), pos.getX(), pos.getY());
				}

				g.setColor(Color.black);
				g.drawLine(0, j * NODE_HEIGHT + YOFFSET, map.getMapWidth() * TW_RENDER, j * NODE_HEIGHT + YOFFSET);
			}

			g.setColor(Color.black);
			g.drawLine(i * NODE_WIDTH + XOFFSET, 0, i * NODE_WIDTH + XOFFSET, map.getMapHeight() * TH_RENDER);
		}
	}
	
	// Getters and Setters
	public int getHeight() {
		return map.getMapHeight()*RES;
	}

	public int getWidth() {
		return map.getMapWidth()*RES;
	}

	public Node[][] getNodes() {
		return nodes;
	}

	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

}
