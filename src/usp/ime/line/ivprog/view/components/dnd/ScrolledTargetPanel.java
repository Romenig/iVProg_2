/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.components.dnd;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrolledTargetPanel extends JScrollPane {

	TargetPanel target;
	JPanel contentPanel;

	/**
	 * Create the panel.
	 */
	public ScrolledTargetPanel() {
		target = new TargetPanel(false);
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(BorderLayout.CENTER, target);
		this.setViewportView(contentPanel);
	}

	public void addComponent(ComponentPanel p) {
		target.addComponent(p);
	}
}
