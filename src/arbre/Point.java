package arbre;

public class Point {//Classe pour la position de chaque trait à tracer
	public double x;
	public double y;
	public int angle;
	
	Point(){//Construteur vide
		
	}

	protected Point(final double x, final double y, final int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
}
