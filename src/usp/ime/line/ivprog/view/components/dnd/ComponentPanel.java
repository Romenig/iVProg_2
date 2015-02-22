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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.domaingui.utils.RoundedJPanel;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

/**
 * @author Romenig
 */
public class ComponentPanel extends RoundedJPanel {

	protected JPanel contentPanel;
	private JPanel gripArea;
	private JPanel trashCanPanel;
	private ComponentPanel instance;

	public ComponentPanel() {
		instance = this;
		setLayout(new BorderLayout());
		initContentPanel();
		setArcs(new Dimension(15, 15));
		initGripArea();
		initTrashCan();
		addMouseListener(Services.getService().getML());
		addMouseMotionListener((MouseMotionListener) Services.getService().getML());
	}

	private void initTrashCan() {
		trashCanPanel = new JPanel(new BorderLayout());
		Action action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				TargetPanel t = (TargetPanel) getParent();
				t.removeElement(instance);
			}
		};
		try {
			action.putValue(Action.SMALL_ICON,
			        new ImageIcon(ComponentPanel.class.getResource("/usp/ime/line/resources/icons/trash16x16.png")));
			action.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("removeComponent"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton btn = new JButton(action);
		btn.setUI(new IconButtonUI());
		trashCanPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		trashCanPanel.setOpaque(false);
		trashCanPanel.add(btn, BorderLayout.NORTH);
		add(trashCanPanel, BorderLayout.EAST);
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		add(contentPanel, BorderLayout.CENTER);
	}

	private void initGripArea() {
		GripArea grip = new GripArea("");
		gripArea = new JPanel();
		BorderLayout bl_gripArea = new BorderLayout();
		bl_gripArea.setHgap(2);
		gripArea.setLayout(bl_gripArea);
		gripArea.add(grip, BorderLayout.CENTER);
		gripArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		gripArea.add(grip, BorderLayout.CENTER);
		gripArea.setOpaque(false);
		add(gripArea, BorderLayout.WEST);
	}

}
