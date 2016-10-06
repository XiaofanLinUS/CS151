import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon {
	/**
	 * Construct a shape icon with a given shape, width and height
	 * @param shape the shape
	 * @param width the width
	 * @param height the height
	 */
	public ShapeIcon(MoveableShape shape, int width, int height) {
		this.shape = shape;
		this.width = width;
		this.height = height;
	}

	/**
	 * Get the width of this icon
	 * @return the width
	 */
	public int getIconWidth() {
		return width;
	}

	/**
	 * Get the height of this icon
	 * @return the height
	 */
	public int getIconHeight() {
		return height;
	}

	/**
	 * Paint the icon
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}

	private int width;
	private int height;
	private MoveableShape shape;
}
