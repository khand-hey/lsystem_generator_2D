package arbre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Fennetre extends JFrame implements ActionListener {

	private FennDessin pannelD;
	public JTextField zoneAxiom;
	public JTextArea zoneRule;
	public JTextField zoneAngle;
	public JTextField zoneIteration;
	public JTextField zoneLongueur;
	public JRadioButton buttonCoin;
	public JRadioButton buttonCentre;
	public JRadioButton buttonBas;
	public JCheckBox pos;
	static final int RES_LONGUEUR = 800;
	static final int RES_LARGEUR = 600;
	
	public Fennetre() {//Constructeur
		this.setTitle("LINDENMAYER c khand - hey §§§");
		this.genInterface(); //fx init
		this.completer();
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(RES_LONGUEUR, RES_LARGEUR);//Taille de la fenetre
		this.getContentPane().setBackground(Color.decode("#6fa8dc"));//couleur de fond
		this.setLocationRelativeTo(null);//Centrer la fenetre au lancement
		}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Fennetre prog = new Fennetre() ;
	}
	
	public final void genInterface() {//Config de la fenetre principale
		Font font = new Font("Century Gothic", Font.BOLD, 14);
		Font font1 = new Font("Century Gothic", Font.PLAIN, 14);
		Font font2 = new Font("Arial", Font.PLAIN, 14);
		JPanel monPannel = new JPanel() {
		    @Override
		    public Dimension getPreferredSize() {
		        return new Dimension(RES_LONGUEUR, 100);
		    };
		};
		monPannel.setBackground(Color.decode("#76a5a9"));
		monPannel.setLayout(null);
		
		pannelD = new FennDessin();
		pannelD.setBackground(Color.black);
		pannelD.setBounds(100, 100, 600, 500);
		
		JButton buttn = new JButton("Dessiner");
		buttn.setBounds(640, 20, 140, 20);
		buttn.addActionListener(this);
		buttn.setActionCommand("dessiner");
		buttn.setFont(font);
		
		JButton buttn2 = new JButton("Effacer");
		buttn2.setBounds(640, 60, 140, 20);
		buttn2.addActionListener(this);
		buttn2.setActionCommand("effacer");
		buttn2.setFont(font);
		
		JLabel txtAxiom = new JLabel();
		txtAxiom.setText("Axiom de départ");
		txtAxiom.setBounds(10, 0, 140, 20);
		txtAxiom.setFont(font1);
		
		zoneAxiom = new JTextField();
		zoneAxiom.setBounds(8, 20, 120, 20);
		zoneAxiom.setBackground(Color.decode("#d0e0e3"));
		zoneAxiom.setFont(font2);
		
		JLabel txtRule = new JLabel();
		txtRule.setText("Règle de Reécriture");
		txtRule.setBounds(10, 40, 140, 20);
		txtRule.setFont(font1);
		
		zoneRule = new JTextArea(2, 2);
		zoneRule.setBackground(Color.decode("#d0e0e3"));
		zoneRule.setFont(font2);
		
		JScrollPane scroll = new JScrollPane (zoneRule, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(8, 60, 140, 38);
		
		JLabel txtIteration = new JLabel();
		txtIteration.setText("Nombre d'Iterations");
		txtIteration.setBounds(170, 0, 140, 20);
		txtIteration.setFont(font1);
		
		zoneIteration = new JTextField();
		zoneIteration.setBounds(170, 20, 140, 20);
		zoneIteration.setBackground(Color.decode("#d0e0e3"));
		zoneIteration.setFont(font2);		
		
		JLabel txtAngle = new JLabel();
		txtAngle.setText("Angle de rotation");
		txtAngle.setBounds(170, 50, 140, 20);
		txtAngle.setFont(font1);
		
		zoneAngle = new JTextField();
		zoneAngle.setBounds(170, 70, 140, 20);
		zoneAngle.setBackground(Color.decode("#d0e0e3"));
		zoneAngle.setFont(font2);
		
		JLabel txtLongueur = new JLabel();
		txtLongueur.setText("Longueur de traits");
		txtLongueur.setBounds(330, 0, 140, 20);
		txtLongueur.setFont(font1);
		
		zoneLongueur = new JTextField();
		zoneLongueur.setBounds(330, 20, 140, 20);
		zoneLongueur.setBackground(Color.decode("#d0e0e3"));
		zoneLongueur.setFont(font2);
		
		JLabel txtOrigine = new JLabel();
		txtOrigine.setText("Direction de tracé");
		txtOrigine.setBounds(330, 50, 140, 20);
		txtOrigine.setFont(font1);
		
		buttonCoin = new JRadioButton("Coin");
		buttonCoin.setBounds(330, 70, 60, 20);
		buttonCoin.setBackground(Color.decode("#d0e0e3"));
		buttonCoin.setFont(font1);
		
		buttonCentre = new JRadioButton("Centre");
		buttonCentre.setBounds(400, 70, 80, 20);
		buttonCentre.setBackground(Color.decode("#d0e0e3"));
		buttonCentre.setFont(font1);
		
		buttonBas = new JRadioButton("Bas");
		buttonBas.setBounds(490, 70, 50, 20);
		buttonBas.setBackground(Color.decode("#d0e0e3"));
		buttonBas.setFont(font1);
		
		ButtonGroup buttonGroupOrigine = new ButtonGroup();//Grouper pour qu'un seul soit selectionnable à la fois
		
		buttonGroupOrigine.add(buttonCoin);
		buttonGroupOrigine.add(buttonCentre);
		buttonGroupOrigine.add(buttonBas);
		
		/*//Gestion des couleurs
		JLabel txtCouleur = new JLabel();
		txtCouleur.setText("Couleur");
		txtCouleur.setBounds(490, 0, 140, 20);
		txtCouleur.setFont(font1);
		*/
		pos = new JCheckBox("V");
		pos.setBounds(550, 70, 40, 20);
		pos.setBackground(Color.decode("#d0e0e3"));
		pos.setFont(font1);
		
		monPannel.add(buttn);
		monPannel.add(buttn2);
		monPannel.add(txtAxiom);
		monPannel.add(zoneAxiom);
		monPannel.add(txtRule);
		monPannel.add(scroll);
		monPannel.add(txtIteration);
		monPannel.add(zoneIteration);
		monPannel.add(txtAngle);
		monPannel.add(zoneAngle);
		monPannel.add(txtLongueur);
		monPannel.add(zoneLongueur);
		monPannel.add(txtOrigine);
		monPannel.add(buttonCoin);
		monPannel.add(buttonCentre);
		monPannel.add(buttonBas);
		//monPannel.add(txtCouleur);
		monPannel.add(pos);
		
		add(pannelD);
		add(monPannel);
		
	}

	public final FennDessin getPannelD() {
		return pannelD;
	}
	
	public void completer() {
			zoneAxiom.setText("F");
			zoneRule.setText("F=F[+FF][-FF]F[-F][+F]F\n");
			zoneIteration.setText("3");
			zoneAngle.setText("36");
			zoneLongueur.setText("8");
			buttonBas.setSelected(true);
			pos.setSelected(true);
	}
	
	@Override
	public final void actionPerformed(ActionEvent e) {
		LSysteme exe = new LSysteme(pannelD);
		if (e.getActionCommand() == "dessiner") {
			try {
				String axiom = zoneAxiom.getText();
				String[] rules = zoneRule.getText().split("\n");
				int iterations = Integer.parseInt(zoneIteration.getText());
				int angle = Integer.parseInt(zoneAngle.getText());
				int traitL = Integer.parseInt(zoneLongueur.getText());
				int dir = 1;
				if (!buttonCentre.isSelected()) {
					dir = buttonCoin.isSelected() ? 2 : 3;
					}

				exe.resoLSystem(axiom, rules, iterations, angle, traitL, dir, pos.isSelected());

			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(pannelD, "Verifier les saisies", "Attention !!!!!!!", JOptionPane.WARNING_MESSAGE);
				}
			} 
		else if (e.getActionCommand()=="effacer") {
			pannelD.init();
			}
		}
}	