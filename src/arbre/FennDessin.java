package arbre;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FennDessin extends JPanel {

	private ArrayList<Line2D.Double> traits; //La liste des traits du dessin

	public final void addLine(final Line2D.Double trait) {//methode pour ajout traits
		traits.add(trait);
	}
	
	public final void init() {//Initialisation du Pannel de dessin
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		traits.clear();
		graphics2D.clearRect(0, 0, getWidth(), getHeight());
		graphics2D.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public final void dessine() {
		Graphics2D graphics2D = (Graphics2D) getGraphics();
		graphics2D.setColor(Color.decode("#6fa8dc"));
		for (Line2D.Double line : traits) {
			graphics2D.drawLine((int) Math.round(line.getX1()), (int) Math.round(line.getY1()), (int) Math.round(line.getX2()), (int) Math.round(line.getY2()));	
		}
	}

	@Override
	public final void paintComponent(final Graphics graphics) {//Surcharge paintComponent (swing)
		super.paintComponent(graphics);
		traits = new ArrayList<Line2D.Double>();
		dessine();
	}
}
