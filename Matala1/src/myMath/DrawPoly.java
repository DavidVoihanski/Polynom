
package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class DrawPoly extends JFrame {
	public DrawPoly() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 1000);
	}

	public static void main(String[] args) {

		Polynom p = new Polynom("x^2");
		PlotTest frame = new PlotTest();
		DataTable data = new DataTable(Double.class, Double.class);
		for (double x = -2.0; x <= 6.0; x += 0.25) {
			double y = p.f(x);
			data.add(x, y);
		}

		XYPlot plot = new XYPlot(data);
		frame.getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		Color minMax = new Color(0.0f, 0.3f, 1.0f);
		minMax = Color.RED;
		Color GlobalMinMax = new Color(0.0f, 0.3f, 1.0f);
		GlobalMinMax = Color.pink;
		DataTable extremum = new DataTable(Double.class, Double.class);
		DataTable globalExtremum = new DataTable(Double.class, Double.class);
		ArrayList<Point> temp = p.findMinMax(-2, 6);
		Iterator<Point> it = temp.iterator();
		while (it.hasNext()) {
			Point currP = it.next();
			if (currP.isGlobalMax() || currP.isGlobalMin())
				globalExtremum.add(currP.getX(), currP.getY());
			else
				extremum.add(currP.getX(), currP.getY());
		}
		plot.add(extremum);
		plot.add(globalExtremum);
		plot.getPointRenderers(extremum).get(0).setColor(minMax);
		plot.getPointRenderers(globalExtremum).get(0).setColor(GlobalMinMax);
		frame.setVisible(true);
	}
}