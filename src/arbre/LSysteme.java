package arbre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.awt.geom.Line2D;
import java.util.Stack;

public final class LSysteme extends Point {
	private Stack<Point> pile;
	private Point posActuel;
	private FennDessin pannelDe; //Instance fennetre de Dessin
	private int angle;
	protected String axiomIt;
	private int tailleLigne;
	private static final int ANGLE_V = 180;
	private static final int ANGLE_H = 90;
	private static final int DECALAGE = 50; 
	
	private Thread t;
	
	public LSysteme() {
		
	}
	
	public LSysteme(final FennDessin pannelDe) {
		this.pannelDe = pannelDe;

	}
	
	public final void setPosition(final String axiomIt, final int angle, final int tailleLigne, final int direcT, final boolean verticale) {
		double hauteurF = pannelDe.getSize().height - pannelDe.getInsets().bottom - pannelDe.getInsets().top;
		double largeurF = pannelDe.getSize().width - pannelDe.getInsets().left - pannelDe.getInsets().right;
		int angleT = verticale ? ANGLE_V : ANGLE_H;
		if (direcT == 1) // Direction de tracé milieu(Centre)
			this.posActuel = new Point(largeurF / 2, hauteurF / 2, angleT);
		else if (direcT == 2) // Coin
			this.posActuel = new Point(DECALAGE, hauteurF + pannelDe.getInsets().bottom - DECALAGE, angleT);
		else //Bas
			this.posActuel = new Point(largeurF / 2, hauteurF + pannelDe.getInsets().bottom - DECALAGE, angleT);
		this.pile = new Stack<Point>();
		this.axiomIt = axiomIt;
		this.angle = angle;
		this.tailleLigne = tailleLigne;
	}

	public final void draw() {//Dessiner les regles Lsyst (F,+,-,[,]) trait à trait
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				pannelDe.init();
				
				for (char item : axiomIt.toCharArray()) {
					switch(item) {
					case 'A':
					case 'B':
					case 'F':
					case 'G':
						double changeX = posActuel.x + (tailleLigne * Math.sin(Math.toRadians(posActuel.angle)));
						double changeY = posActuel.y + (tailleLigne * Math.cos(Math.toRadians(posActuel.angle)));
						pannelDe.addLine(new Line2D.Double(posActuel.x, posActuel.y, changeX, changeY));
						posActuel = new Point(changeX, changeY, posActuel.angle);
						break;
					case '+':
						int nextAngle = posActuel.angle + angle;
						posActuel = new Point(posActuel.x, posActuel.y, nextAngle);
						break;
					case '-':
						nextAngle = posActuel.angle - angle;
						posActuel = new Point(posActuel.x, posActuel.y, nextAngle);
						break;
					case '[':
						pile.push(posActuel);
						break;
					case ']':
						if (!pile.isEmpty())
							posActuel = pile.pop();
						break;
						}
					pannelDe.dessine();
					
				}
				
			}
			
		});
		
		t.start();
		
	}
	
	private static Map<Character, char[]> triRegles(final String[] regles) {
		Map<Character, char[]> trie = new HashMap<Character, char[]>();

		for (String role : regles) {
			char[] tabR = role.toCharArray();
			char constant = tabR[0]; //Recup le premier caract de chaque regle
			char[] production = Arrays.copyOfRange(tabR, 1, tabR.length);
			trie.put(constant, production);
		}

		return trie;
	}

	protected static String nbIter(final Map<Character, char[]> regles, final String axiom) {
		String ax = "";

		for (char elem : axiom.toCharArray()) {
			if (regles.get(elem) != null) {
				String p = new String(regles.get(elem));
				ax += p;
				} 
			else {
				ax += elem;
				}
		}

		return ax;
	}
	
	public void resoLSystem(final String axiom, final String[] regles, final int iterations, final int angle, final int tailleLigne, final int direcT, final boolean verticale) {
		String tempAx = axiom;
		Map<Character, char[]> rT = triRegles(regles);

		for (int i = 0; i < iterations; i++){
			tempAx = nbIter(rT, tempAx);
		}

		setPosition(tempAx, angle, tailleLigne, direcT, verticale);
		draw();
	}
}

