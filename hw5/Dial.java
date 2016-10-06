import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;

import javax.swing.Icon;

/**
 * A dial icon
 * @author linxiaofan
 *
 */
public class Dial implements Icon {
	
	/**
	 * Construct a dial icon
	 * @param aRadius the radius of this icon
	 * @param aThirdTick true if the icon needs to have the third ticker
	 * @param aColor the color of this dial
	 */
	public Dial(int aRadius, boolean aThirdTick, Color aColor) {
		radius = aRadius;
		thirdTick = aThirdTick;
		color = aColor;
	}

	/**
	 * Paint the dial
	 * @param c the component
	 * @param g the graphics context
	 * @param x the left of the bounding circle
	 * @param y the top of the bounding circle
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		int largeTickers = 12;
		int mediumTicker = 5 * largeTickers;
		int smallTicker = 5 * mediumTicker;
		int length = radius / 5;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		drawTickers(true, largeTickers, length, x, y, g2);
		drawTickers(false, mediumTicker, length/2, x, y, g2);
		if (thirdTick) drawTickers(false, smallTicker, length/4, x, y, g2);
	}
	
	/**
	 * Draw tickers for this dial
	 * @param number true if the dial needs to have a number label besides a ticker
	 * @param tickers the number of tickers
	 * @param length the length of tickers
	 * @param x the left of the bounding circle
	 * @param y the top of the bounding circle
	 * @param g2 the graphics context
	 */
	public void drawTickers(boolean number, int tickers, int length, int x, int y, Graphics2D g2) {
		int r1 = radius;
		int r2 = r1 - length;
		int units = 60 / tickers;
		double[] startX = new double[tickers];
		double[] startY = new double[tickers];
		double[] endX = new double[tickers];
		double[] endY = new double[tickers];
		int m = x + radius;
		int n = y;
		int p = m;
		int q = n + length;
		double degree = 2 * Math.PI / tickers;
		for(int i = 0; i < tickers; i++) {
			startX[i] = m + Math.sin(i*degree)*r1; 
			startY[i] = n + (1 - Math.cos(i*degree))*r1;
			endX[i] = p + Math.sin(i*degree)*r2; 
			endY[i] = q + (1 - Math.cos(i*degree))*r2;
		}
		
		for(int i = 0; i < tickers; i++) {
			g2.draw(new Line2D.Double(startX[i], startY[i], endX[i], endY[i]));
		}
		
		if(number) {
			q += length/2;
			r2 -= length/2;
			for(int i = 0; i < tickers; i++) {
				endX[i] = p + Math.sin(i*degree)*r2; 
				endY[i] = q + (1 - Math.cos(i*degree))*r2;
			}
			g2.setFont(new Font("sans serif", Font.BOLD, (int) (length/1.6)));
			for(int i = 0; i < tickers; i++) {
				int theNumber = units * i;
				String text = Integer.toString(theNumber);
				/*
				 * The following code is cited from a book, Object-Oriented Design and Patterns
				 * Dr. Horstmann, Cay S. Horstmann (http://horstmann.com), is the author of the book
				 */
				Font font = g2.getFont();	
				FontRenderContext context = g2.getFontRenderContext();
				Rectangle2D bounds = font.getStringBounds(text, context);
				double ascent = -bounds.getY();
				double extent = bounds.getWidth();
				/*
				 * End of citation
				 */
				g2.drawString(text, (float) (endX[i] - 0.5 * extent), (float) (endY[i] + 0.5 * ascent));
			}
		}
		
	}
	
	/**
	 * Get the width of this dial
	 * @return the width
	 */
	public int getIconWidth() {
		return 2 * radius;
	}


	/**
	 * Get the height of this dial
	 * @return the height
	 */
	public int getIconHeight() {
		return 2 * radius;
	}


	private int radius;
	private boolean thirdTick;
	private Color color;
}
