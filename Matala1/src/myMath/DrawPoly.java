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
	/**
	 * default constructor 
	 */
	public DrawPoly() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
	}
	/**
	 * Draws a given polynom's graph local extremum are drawn in red, global are drawn in green.
	 * @param x0 the starting point to draw from
	 * @param x1 the end point
	 * @param p	 Polynom type param you wish to draw
	 */
	public static void draw(double x0, double x1, Polynom p) {
		DrawPoly frame = new DrawPoly();
		DataTable data = new DataTable(Double.class, Double.class);  //data table to hold our function's points
		while(x0+0.10<=x1) {			 //finds points on the function with a difference of 0.10 in between
			double y = p.f(x0);  
			data.add(x0, y);
			x0=x0+0.10;
		}
		if(x0<x1)data.add(x1,p.f(x1));   //if we didn't get to the x1 border adds this point
		XYPlot plot = new XYPlot(data);
		frame.getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
	    Color minMax = new Color(0.0f, 0.3f, 1.0f);
	    minMax=Color.RED;
	    Color GlobalMinMax = new Color(0.0f, 0.3f, 1.0f);
	    GlobalMinMax=Color.GREEN;
	    DataTable extremum = new DataTable(Double.class, Double.class);     //datatable to hold our extremum points
	    DataTable globalExtremum = new DataTable(Double.class, Double.class); //data table to hold our global extremum
	    ArrayList<Point>temp=p.findMinMax(-2, 6);							//finds the extremums
	    Iterator<Point>it=temp.iterator();
	    while(it.hasNext()) {												//adds them to their corresponding tables
	    	Point currP=it.next();
	    	if(currP.isGlobalMax()||currP.isGlobalMin()) globalExtremum.add(currP.getX(),currP.getY());
	    	else extremum.add(currP.getX(),currP.getY());
	    }
	    plot.add(extremum);												//adds the extremum to our graph
	    plot.add(globalExtremum);										//adds the global extremum to our graph
	    plot.getPointRenderers(extremum).get(0).setColor(minMax);		//colors extremum in red
	    plot.getPointRenderers(globalExtremum).get(0).setColor(GlobalMinMax);	//colors the global in green
        frame.setVisible(true);													//shows the graph
	}

	public static void main(String[] args) {

		Polynom p=new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		draw(-2,6,p);

	}
}