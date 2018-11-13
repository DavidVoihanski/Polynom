package myMath;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 1000);
        // Insert rest of the code here
    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        Polynom p=new Polynom("0.2x^4-1.5x^3+3.0x^2-1x^1-5x^0");
    	DataTable data = new DataTable(Double.class, Double.class);
    	for (double x = -2.0; x <= 6.0; x+=0.10) {
    	    double y = p.f(x);
    	    data.add(x, y);
    	}
    	
    	XYPlot plot = new XYPlot(data);
    	frame.getContentPane().add(new InteractivePanel(plot));
    	LineRenderer lines = new DefaultLineRenderer2D();
    	plot.setLineRenderers(data, lines);
    	Color color = new Color(0.0f, 0.3f, 1.0f);
    	DataTable min=new DataTable(Double.class,Double.class);
    	color=color.red;
    	for (double x = -5.0; x <= 5.0; x+=0.10) {
    		min.add(0.0,0.0);
    	}
    	plot.add(min);
    	plot.getPointRenderers(min).get(0).setColor(color);
    	//plot.getLineRenderers(min).get(0).setColor(color);
    	frame.setVisible(true);
   

    }
}