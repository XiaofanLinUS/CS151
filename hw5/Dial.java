import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.Icon;


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
		int largeTickers = 12;
		int mediumTicker = 5 * largeTickers;
		int smallTicker = 5 * mediumTicker;
		int length = radius/5;
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		drawTickers(true, largeTickers, length, x, y, g2);
		drawTickers(false, mediumTicker, length/2, x, y, g2);
		if(thirdTick) {
			drawTickers(false, smallTicker, length/4, x, y, g2);
		}
		
	}
	
	public void drawTickers(boolean number, int tickers, int length, int x, int y, Graphics2D g2) {
		int r = radius;
		int units = 60 / tickers;
		double[] startX = new double[tickers];
		double[] startY = new double[tickers];
		double[] endX = new double[tickers];
		double[] endY = new double[tickers];
		ArrayList<Line2D.Double> lines = new ArrayList<>();
		int m = x + radius;
		int n = y;
		double degree = 2 * Math.PI / tickers;
		for(int i = 0; i < tickers; i++) {
			startX[i] = m + Math.sin(i*degree)*r; 
			startY[i] = n + (1 - Math.cos(i*degree))*r;
		}
		n += length;
		r -= length;
		for(int i = 0; i < tickers; i++) {
			endX[i] = m + Math.sin(i*degree)*r; 
			endY[i] = n + (1 - Math.cos(i*degree))*r;
		}
		
		for(int i = 0; i < tickers; i++) {
			lines.add(new Line2D.Double(startX[i], startY[i], endX[i], endY[i]));
		}
		for(int i = 0; i < tickers; i++) {
			g2.draw(lines.get(i));
		}
		
		if(number) {
			n += length/2;
			r -= length/2;
			for(int i = 0; i < tickers; i++) {
				endX[i] = m + Math.sin(i*degree)*r; 
				endY[i] = n + (1 - Math.cos(i*degree))*r;
			}
			g2.setFont(new Font("TimesRoman", Font.BOLD, length/2));
			for(int i = 0; i < tickers; i++) {
				int theNumber = units * i;
				String text = Integer.toString(theNumber);
				Font font = g2.getFont();
				FontRenderContext context = g2.getFontRenderContext();
				Rectangle2D bounds = font.getStringBounds(text, context);
				double ascent = -bounds.getY();
				//double descent = bounds.getHeight() - ascent;
				double extent = bounds.getWidth();
				g2.drawString(text, (float) (endX[i] - 0.5 * extent), (float) (endY[i] + 0.5 * ascent));
			}
		}
	}
	@Override
	public int getIconWidth() {
		return 2 * radius;
	}

	@Override
	public int getIconHeight() {
		return 2 * radius;
	}

}
