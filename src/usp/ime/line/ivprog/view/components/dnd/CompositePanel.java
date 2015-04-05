/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.components.dnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;

/**
 * @author Romenig
 * 
 */
public abstract class CompositePanel extends ComponentPanel {

	private JPanel header;
	private JPanel content;
	private JButton expandBtnUp;
	private JButton expandBtnDown;
	private Icon up;
	private Icon down;

	/**
	 * Create the panel.
	 */
	public CompositePanel() {
		super();
		initHeader();
		initContent();
		initIcons();
		initButtons();
	}

	private void initIcons() {
		try {
			up = new javax.swing.ImageIcon(getClass().getResource("/usp/ime/line/resources/icons/expand_up.png"));
			down = new javax.swing.ImageIcon(getClass().getResource("/usp/ime/line/resources/icons/expand_down.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initButtons() {
		initExpandBtnUp();
		initExpandBtnDown();
	}

	private void initExpandBtnUp() {
		expandBtnUp = new JButton();
		expandBtnUp.setIcon(up);
		expandBtnUp.setUI(new IconButtonUI());
		expandBtnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expandedActions();
			}
		});
		expandBtnUp.setVisible(false);
		header.add(expandBtnUp);
	}

	private void initExpandBtnDown() {
		expandBtnDown = new JButton();
		expandBtnDown.setIcon(down);
		expandBtnDown.setUI(new IconButtonUI());
		expandBtnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notExpandedAction();
			}
		});
		header.add(expandBtnDown);
	}

	protected void notExpandedAction() {
		content.setVisible(true);
		expandBtnUp.setVisible(true);
		expandBtnDown.setVisible(false);
		revalidate();
		repaint();
	}

	protected void expandedActions() {
		content.setVisible(false);
		expandBtnUp.setVisible(false);
		expandBtnDown.setVisible(true);
		revalidate();
		repaint();
	}

	private void initHeader() {
		header = new JPanel();
		header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setOpaque(false);
		contentPanel.add(BorderLayout.NORTH, header);
	}

	private void initContent() {
		content = new JPanel();
		content.setVisible(false);
		content.setOpaque(false);
		content.setLayout(new BorderLayout());
		content.setPreferredSize(new Dimension(10, 30));
		contentPanel.add(BorderLayout.CENTER, content);
	}

}
