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
import javax.swing.border.EmptyBorder;

import usp.ime.line.ivprog.interpreter.execution.code.CodeComposite;
import usp.ime.line.ivprog.model.utils.IVPConstants;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.IDomainObjectUI;
import usp.ime.line.ivprog.view.domaingui.workspace.IVPContextMenu;

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
	private String context;
	private String container;
	private String scopeID;
	private IVPContextMenu menu;

	/**
	 * Create the panel.
	 */
	public TargetPanel(boolean isInter, String compositeID, String scopeID, String context) {
		this.context = context;
		this.scopeID = scopeID;
		isInternal = isInter;
		setContainer(compositeID);
		initializeLayout();
		initializeVariables();
		addMouseListener(Services.getService().getML());
		addMouseMotionListener(Services.getService().getML());
		setBackground(FlatUIColors.MAIN_BG);
		menu = new IVPContextMenu(this, context, scopeID);
		elementList.add(menu);
		relayout();
	}

	private void initializeVariables() {
		elementList = new Vector();
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		if (isInternal)
			setPreferredSize(new Dimension(10, 42));
	}

	/**
	 * Add a component that would be a Leaf or a Composite Panel.
	 * 
	 * @param componentPanel
	 */
	public void addComponent(String childID) {
		JComponent child = Services.getService().getRenderer().paint(childID, scopeID);
		elementList.add(elementList.size() - 1, child);
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
		initCanvasHeight();
		elementList.remove(menu);
		elementList.add(menu);
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
	
	private void initCanvasHeight() {
		if (elementList.size() == 0) {
			setPreferredSize(new Dimension(10, 42));
		} else {
			setPreferredSize(null);
		}
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

	/**
	 * @return the container
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(String container) {
		this.container = container;
	}

	/**
	 * @return the scopeID
	 */
	public String getScopeID() {
		return scopeID;
	}

	/**
	 * @param scopeID
	 *            the scopeID to set
	 */
	public void setScopeID(String scopeID) {
		this.scopeID = scopeID;
	}

	/**
	 * @param childID
	 */
	public void childRemoved(String childID) {
		JComponent c = (JComponent) Services.getService().getViewMapping().getObject(childID);
		elementList.remove(c);
		relayout();
	}

	/**
	 * @param childID
	 * @param index
	 */
	public void restoreChild(String childID, int index) {
		JComponent child = (JComponent) Services.getService().getViewMapping().getObject(childID);
		if (elementList.contains(child)) {
			int ind = elementList.indexOf(child);
			if (index >= ind) {
				elementList.add(index, child);
				if (ind != -1)
					elementList.remove(ind);
			} else {
				elementList.remove(child);
				elementList.add(index, child);
			}
		} else {
			if (index != -1)
				elementList.add(index, child);
			else
				elementList.add(child);
		}
		relayout();
	}

	public boolean isContentSet() {
		boolean isCSet = true;
		for (int i = 0; i < elementList.size() - 1; i++) {
			if (!((IDomainObjectUI) elementList.get(i)).isContentSet()) {
				if (isCSet) {
					isCSet = false;
				}
			}
		}
		return isCSet;
	}

	public void lockCodeDown() {
		for (int i = 0; i < elementList.size() - 1; i++) {
			((IDomainObjectUI) elementList.get(i)).lockDownCode();
		}
	}
	
	public int getDropIndex(int dropY, JComponent c) {
		elementList.remove(menu);
		int index;
		for (index = 0; index < elementList.size(); index++) {
			if (dropY < ((JPanel) elementList.get(index)).getY()) {
				if (index > 0) {
					if (c.equals(elementList.get(index - 1))) {
						return index - 1;
					}
				}
				return index;
			}
		}
		if (index > 0) {
			if (c.equals(elementList.get(index - 1))) {
				return index - 1;
			}
		}
		elementList.add(menu);
		return index;
	}
	
	public void moveChild(String childID, int toIndex) {
		JComponent c = (JComponent) Services.getService().getViewMapping().getObject(childID);
		int lastIndex = elementList.indexOf(c);
		if (toIndex >= lastIndex) {
			elementList.add(toIndex, c);
			elementList.remove(lastIndex);
		} else {
			elementList.remove(c);
			elementList.add(toIndex, c);
		}
		relayout();
	}

	/**
	 * @return the context
	 */
    public String getContext() {
	    return context;
    }

	/**
	 * @param context the context to set
	 */
    public void setContext(String context) {
	    this.context = context;
    }

}
