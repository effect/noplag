package org.amse.fedotov.barchart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BarChartPanel extends JPanel {

	private final IBarChartModel myBarChartModel;

	public BarChartPanel(IBarChartModel barChartModel) {
		myBarChartModel = barChartModel;
		addMouseListener(new ChartPanelMouseListener());
	}

	@Override
	public void paintComponent(Graphics g) {
		List<? extends IValue> values = myBarChartModel.getValues();
		if (values == null) {
			return;
		}
		super.paintComponent(g);
		int n = values.size();
		if (n == 0) {
			return;
		}
		int w = this.getWidth() / n;
		int h = this.getHeight();
		int curW = 0;
		double max = maxValue();
		int width = w / 2;
		int fontSize = countFontSize(values, width);
		for (IValue value : values) {
			double v = value.getValue();
			int height = (int) (h * v / max);
			drawRect(g, curW + w / 2 - width / 2, h - height, width, height);

			g.setFont(new Font("Monospaced", Font.BOLD, fontSize));
			g.setColor(Color.BLACK);
			g.drawString(String.format("%.1f", v), curW + w / 2 - width / 2, h / 2);

			curW += w;
		}
	}

	private void drawRect(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		Graphics2D g2d = (Graphics2D)g;
        Color startColor = Color.WHITE;
        Color endColor = Color.BLUE;
        GradientPaint gradient = new GradientPaint(x, y, startColor, x + width, y + height, endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(x, y, width, height);
	}

	private int countFontSize(List<? extends IValue> values, int width) {
		int maxLen = -1;
		for (IValue v : values) {
			if (maxLen < String.format("%.1f", v.getValue()).length()) {
				maxLen = String.format("%.1f", v.getValue()).length();
			}
		}
		return 7 * width / maxLen / 4;
	}

	private double maxValue() {
		return myBarChartModel.getMaxValue();
	}

	private class ChartPanelMouseListener extends MouseAdapter {

		/**
		 * Delegates action to bar chart model with defined index.
		 */
		public void mouseClicked(MouseEvent e) {
			List<? extends IValue> values = myBarChartModel.getValues();
			if (values == null) {
				return;
			}
			int x = e.getX();
			int n = values.size();
			if (n == 0) {
				return;
			}
			int w = BarChartPanel.this.getWidth() / n;
			int num = x / w;
			if (myBarChartModel != null) {
				myBarChartModel.click(num);
			}
		}
	}

}
