import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.ArrayList;

import javax.swing.Icon;

import com.sun.prism.paint.Color;

public class Dial implements Icon {
	private int radius;
	private boolean thirdTick;
	private java.awt.Color color;
	public Dial(int aRadius, boolean aThirdTick, java.awt.Color green) {
		radius = aRadius;
		thirdTick = aThirdTick;
		color = green;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		int r = radius;
		double[] startX = new double[12];
		double[] startY = new double[12];
		double[] endX = new double[12];
		double[] endY = new double[12];
		/**
		ArrayList<Point2D.Double> starts = new ArrayList<>();
		ArrayList<Point2D.Double> ends = new ArrayList<>();**/
		ArrayList<Line2D.Double> lines = new ArrayList<>();
		int m = x + radius;
		int n = y;

		double degree = 2 * Math.PI / 12;
		for(int i = 0; i < 12; i++) {
			startX[i] = m + Math.sin(i*degree)*r; 
			startY[i] = n + (1 - Math.cos(i*degree))*r;
		}
		n += 20;
		r -= 20;
		for(int i = 0; i < 12; i++) {
			endX[i] = m + Math.sin(i*degree)*r; 
			endY[i] = n + (1 - Math.cos(i*degree))*r;
		}
		
		for(int i = 0; i < 12; i++) {
			lines.add(new Line2D.Double(startX[i], startY[i], endX[i], endY[i]));
		}
		for(int i = 0; i < 12; i++) {
			g2.draw(lines.get(i));
		}
		
		for(int i = 0; i < 12; i++) {
			System.out.println("|" + startX[i] + " " + startY[i] + " " + endX[i] + " " + endY[i] + "|");
			System.out.println(i);
		}
	}

	@Override
	public int getIconWidth() {
		return 2 * radius;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 2 * radius;
	}

}
