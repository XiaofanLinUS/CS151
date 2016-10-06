import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.GeneralPath;
import java.time.Duration;
import java.time.Instant;
import javax.swing.Icon;


/**
 * A stopwatch with a movable pointer
 * @author linxiaofan
 */
public class Stopwatch implements MoveableShape {
	/**
	 * Constructs a stopwatch item.
	 * 
	 * @param x the left of the bounding circle
	 * @param y the top of the bounding circle
	 * @param radius the radius of the circle
	 */
	public Stopwatch(int x, int y, int radius) {
		running = false;
		frozen = false;
		totalDelay = 0;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.smallradius = (int) (radius / 2.7);
		this.dial1 = new Dial(radius, true, Color.BLACK);
		this.dial2 = new Dial(smallradius, false, Color.BLACK);
		this.degree = 0;
		this.smallDegree = 0;
	}

	/**
	 * Constructs a stopwatch item.
	 * @param radius the radius of the circle
	 */
	public Stopwatch(int radius) {
		totalDelay = 0;
		running = false;
		frozen = false;
		x = 0;
		y = 0;
		this.radius = radius;
		this.smallradius = (int) (radius / 2.7);
		this.dial1 = new Dial(radius, true, Color.BLACK);
		this.dial2 = new Dial(smallradius, false, Color.BLACK);
		this.degree = 0;
		this.smallDegree = 0;
	}

	/**
	 * Reset this watch
	 */
	public void reset() {
		totalDelay = 0;
		running = false;
		frozen = false;
		degree = 0;
		smallDegree = 0;
	}

	/**
	 * Move pointers of this watch
	 */
	public void move() {
		if (running) {
			Instant moment = Instant.now();
			Duration delay = Duration.between(thisMoment, moment);
			totalDelay += delay.getNano() / Math.pow(10, 9);
			thisMoment = moment;
			if (!frozen) {
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

	/**
	 * Draw a pointer for this watch
	 * @param g2 the graphics context
	 * @param startX the starting x value of the pointer
	 * @param startY the starting y value of the pointer
	 * @param length the length of the pointer
	 * @param degree the degree between the pointer and a vertical line
	 * @param aColor the color of the pointer
	 */
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

	/**
	 * Draw a stopwatch
	 * @param g2 the graphics context
	 */
	public void draw(Graphics2D g2) {
		int smallX = x + radius - smallradius;
		int smallY = (int) (y + radius / 2.5);
		double midX = x + radius; // Mid point of the big dial
		double midY = y + radius;
		double sMidX = smallX + smallradius;// Mid point of the small dial
		double sMidY = smallY + smallradius;

		Graphics g = (Graphics) g2;

		dial1.paintIcon(null, g, x, y);
		dial2.paintIcon(null, g, smallX, smallY);

		drawPointer(g2, midX, midY, 20 * radius / 21, degree, Color.RED);
		drawPointer(g2, sMidX, sMidY, 20 * smallradius / 21, smallDegree, Color.magenta);
	}

	/**
	 * Press the top button
	 */
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

	/**
	 * This code is modified on the basis of ArrowHead class in Violet program.
	 * Violet program: http://horstmann.com/violet/
	 * Dr. Horstmann, Cay S. Horstmann (http://horstmann.com), is the author of Violet
	 * Get a path of an arrow head
	 * @param startX the x value of the head
	 * @param startY the y value of the head
	 * @param endX the x value of the tail
	 * @param endY the y value of the tail
	 * @param arrowLength the length of axis of the arrow
	 * @return the path of the arrow
	 */
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

	/**
	 * Press the second button
	 */
	public void secondButtonPressed() {
		frozen = !frozen;
	}

	private double totalDelay;
	private boolean running;
	private boolean frozen;
	private int x;
	private int y;
	private int radius;
	private int smallradius;
	private double degree;
	private double smallDegree;
	private Icon dial1;
	private Icon dial2;
	private Instant thisMoment;
}
