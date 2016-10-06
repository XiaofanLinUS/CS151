import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.GeneralPath;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import javax.swing.Icon;

import com.sun.prism.paint.Stop;

/**
 * A car that can be moved around.
 */
public class Stopwatch implements MoveableShape {
	/**
	 * Constructs a car item.
	 * 
	 * @param x
	 *            the left of the bounding rectangle
	 * @param y
	 *            the top of the bounding rectangle
	 * @param width
	 *            the width of the bounding rectangle
	 */
	public Stopwatch(int x, int y, int width) {
		running = false;
		frozen = false;
		totalDelay = 0;
		this.x = x;
		this.y = y;
		this.width = width;
		this.smallWidth = (int) (width / 2.7);
		this.dial1 = new Dial(width, true, Color.BLACK);
		this.dial2 = new Dial(smallWidth, false, Color.BLACK);
		this.degree = 0;
		this.smallDegree = 0;
	}

	public Stopwatch(int width) {
		totalDelay = 0;
		running = false;
		frozen = false;
		x = 0;
		y = 0;
		this.width = width;
		this.smallWidth = (int) (width / 2.7);
		this.dial1 = new Dial(width, true, Color.BLACK);
		this.dial2 = new Dial(smallWidth, false, Color.BLACK);
		this.degree = 0;
		this.smallDegree = 0;
	}

	public void reset() {
		totalDelay = 0;
		running = false;
		frozen = false;
		degree = 0;
		smallDegree = 0;
	}

	public void move() {
		if (running) {
			Instant moment = Instant.now();
			Duration delay = Duration.between(thisMoment, moment);
			totalDelay += delay.getNano() / Math.pow(10, 9);
			if (!frozen) {
				thisMoment = moment;
				degree = ((totalDelay / 60)) * (2 * Math.PI);
				smallDegree = (totalDelay / (60 * 60)) * (2 * Math.PI);
				degree %= 2 * Math.PI;
				smallDegree %= 2 * Math.PI;
			}
		} else {
			if (frozen) {
				reset();
			}
		}
	}

	public void drawPointer(Graphics2D g2, double startX, double startY, double length, double degree, Color aColor) {
		double m = startX;
		double n = startY - length;
		double endX = m + Math.sin(degree) * length;
		double endY = n + (1 - Math.cos(degree)) * length;
		double arrowLength = length / 35;
		
		n += arrowLength;
		double lengthToAxisEnd = length - arrowLength;
		
		double startAxisX = m + Math.sin(degree) * lengthToAxisEnd;
		double startAxisY = n + (1 - Math.cos(degree)) * lengthToAxisEnd;
		GeneralPath path = getPath(startAxisX, startAxisY, endX, endY, arrowLength);
		Line2D.Double aLine = new Line2D.Double(startX, startY, endX, endY);
		g2.setColor(aColor);
		g2.draw(aLine);
	    g2.fill(path);
	}

	public void draw(Graphics2D g2) {
		int smallX = x + width - smallWidth;
		int smallY = (int) (y + width / 2.5);
		double midX = x + width; // Mid point of the big dial
		double midY = y + width;
		double sMidX = smallX + smallWidth;// Mid point of the small dial
		double sMidY = smallY + smallWidth;

		Graphics g = (Graphics) g2;

		dial1.paintIcon(null, g, x, y);
		dial2.paintIcon(null, g, smallX, smallY);

		drawPointer(g2, midX, midY, 20 * width / 21, degree, Color.RED);
		drawPointer(g2, sMidX, sMidY, 20 * smallWidth / 21, smallDegree, Color.magenta);
	}

	public void topButtonPressed() {
		if (!running) {
			thisMoment = Instant.now();
			running = true;
		} else if (frozen) {
			frozen = !frozen;
		} else {
			running = false;
		}
	}

	public GeneralPath getPath(double startX, double startY, double endX, double endY, double arrowLength) {
		GeneralPath path = new GeneralPath();
		final double ARROW_ANGLE = Math.PI / 3.1;

		double dx = endX - startX;
		double dy = endY - startY;
		double angle = Math.atan2(dy, dx);
		double x1 = startX - arrowLength * Math.cos(angle + ARROW_ANGLE);
		double y1 = startY - arrowLength * Math.sin(angle + ARROW_ANGLE);
		double x2 = startX - arrowLength * Math.cos(angle - ARROW_ANGLE);
		double y2 = startY - arrowLength * Math.sin(angle - ARROW_ANGLE);
		path.moveTo((float) endX, (float) endY);
		path.lineTo((float) x1, (float) y1);

		path.lineTo((float) x2, (float) y2);
		path.closePath();
		return path;
	}

	public void secondButtonPressed() {
		frozen = !frozen;
	}

	private double totalDelay;
	private boolean running;
	private boolean frozen;
	private int x;
	private int y;
	private int width;
	private int smallWidth;
	private double degree;
	private double smallDegree;
	private Icon dial1;
	private Icon dial2;
	private Instant thisMoment;
}
