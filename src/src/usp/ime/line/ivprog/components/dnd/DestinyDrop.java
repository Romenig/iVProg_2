/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.components.dnd;

import javax.swing.JPanel;

import usp.ime.line.ivprog.view.utils.RoundedJPanel;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * @author Romenig
 */
public class DestinyDrop extends RoundedJPanel {

	/**
	 * Create the panel.
	 */
	public DestinyDrop() {
		JLabel dropLabel = new JLabel("Solte aqui.");
		setBackgroundColor(new Color(230, 250, 230));
		setBorderColor(new Color(150,170,150));
		repaint();
		add(dropLabel);
	}

}
