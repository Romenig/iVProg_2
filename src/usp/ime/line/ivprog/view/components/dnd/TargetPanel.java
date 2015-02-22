/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.components.dnd;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;

import usp.ime.line.ivprog.model.utils.IVPConstants;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

/**
 * @author Romenig
 */
public class TargetPanel extends JPanel {

	private Vector elementList;
	private boolean isInternal;

	/**
	 * Create the panel.
	 */
	public TargetPanel(boolean isInter) {
		isInternal = isInter;
		initializeLayout();
		initializeVariables();
		addMouseListener(Services.getService().getML());
		addMouseMotionListener(Services.getService().getML());
		setBackground(FlatUIColors.MAIN_BG);
	}

	private void initializeVariables() {
		elementList = new Vector();
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		if (isInternal)
			setPreferredSize(new Dimension(10, 30));
	}

	/**
	 * Add a component that would be a Leaf or a Composite Panel.
	 * 
	 * @param componentPanel
	 */
	public void addComponent(ComponentPanel componentPanel) {
		elementList.add(componentPanel);
		relayout();
	}

	/**
	 * Repaint the target panel with the updated element list.
	 */
	private void relayout() {
		int row = 0;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		removeAll();
		gbc.gridy = row++;
		gbc.insets = new Insets(4, 3, 2, 5);
		for (int i = 0; i < elementList.size(); i++) {
			gbc.gridy = row++;
			JComponent c = (JComponent) elementList.get(i);
			add(c, gbc);
		}
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = row++;
		Component strut = Box.createVerticalStrut(1);
		add(strut, gbc);
		revalidate();
		repaint();
	}

	/**
	 * Returns if this targetPanel is inside a CompositePanel.
	 * 
	 * @return the isInternal
	 */
	public boolean isInternal() {
		return isInternal;
	}

	/**
	 * Set if this TargetPanel is inside a CompositePanel or not.
	 * 
	 * @param isInternal
	 *            the isInternal to set
	 */
	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
	}

	/**
	 * Removes this element from the containing panel.
	 * 
	 * @param abstractAction
	 */
	public void removeElement(ComponentPanel element) {
		elementList.remove(element);
		relayout();
	}

}
