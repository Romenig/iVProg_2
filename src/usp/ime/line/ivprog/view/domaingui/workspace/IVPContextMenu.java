package usp.ime.line.ivprog.view.domaingui.workspace;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import usp.ime.line.ivprog.controller.IVPController;
import usp.ime.line.ivprog.model.utils.IVPConstants;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.components.dnd.TargetPanel;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.domaingui.utils.RoundedJPanel;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

public class IVPContextMenu extends RoundedJPanel {

	private static final long serialVersionUID = 3814837809047109772L;
	private TargetPanel container = null;
	private JPanel btnPanel;
	private JPanel menuPanel;
	private JPanel buttonsContainer;
	private JButton plusBtn;
	private JPopupMenu menu;
	private String context;
	private String scopeID;

	public IVPContextMenu(TargetPanel c, String context, String scopeID) {
		this.context = context;
		this.scopeID = scopeID;
		container = c;
		initialization();
		initPanels();
		initPopupMenu();
		initPlusBtn();
	}

	private void initPopupMenu() {
		menu = new JPopupMenu();
		menu.setBackground(FlatUIColors.MAIN_BG);
		Action createWhile = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				 Services.getService().getController().addChild(scopeID, container.getContainer(),IVPConstants.MODEL_WHILE, context);
			}
		};
		createWhile.putValue(Action.SMALL_ICON,
		        new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/loop_while.png")));
		createWhile.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.while.tip"));
		createWhile.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.while.text"));
		Action createfOR = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// Services.getService().getController().addChild(container.getCodeComposite(),
				// IVPConstants.MODEL_FOR, context);
			}
		};
		createfOR.putValue(Action.SMALL_ICON, new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/loop-n.png")));
		createfOR.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.for.tip"));
		createfOR.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.for.text"));
		Action createifElse = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().addChild(scopeID, container.getContainer(),IVPConstants.MODEL_IFELSE, context);
			}
		};
		createifElse.putValue(Action.SMALL_ICON, new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/if.png")));
		createifElse.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.if.tip"));
		createifElse.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.if.text"));
		Action createRead = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// Services.getService().getController().addChild(container.getCodeComposite(),
				// IVPConstants.MODEL_READ, context);
			}
		};
		createRead.putValue(Action.SMALL_ICON,
		        new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/incoming.png")));
		createRead.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.read.tip"));
		createRead.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.read.text"));
		Action createPrint = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().addChild(scopeID, container.getContainer(), IVPConstants.MODEL_WRITE, context);
			}
		};
		createPrint.putValue(Action.SMALL_ICON,
		        new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/outcoming.png")));
		createPrint.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.escrita.tip"));
		createPrint.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.escrita.text"));
		Action createAtt = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().addChild(scopeID, container.getContainer(), IVPConstants.MODEL_ATTLINE, context);
			}
		};
		createAtt.putValue(Action.SMALL_ICON, new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/att.png")));
		createAtt.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("IVPContextMenu.for.tip"));
		createAtt.putValue(Action.NAME, ResourceBundleIVP.getString("IVPContextMenu.att.text"));
		menu.add(createAtt);
		menu.add(createWhile);
		menu.add(createfOR);
		menu.add(createifElse);
		menu.add(createRead);
		menu.add(createPrint);
	}

	private void initialization() {
		setBorder(new EmptyBorder(2, 2, 2, 2));
		setLayout(new BorderLayout(0, 0));
		setBackground(FlatUIColors.MAIN_BG);
	}

	private void initPanels() {
		btnPanel = new JPanel();
		btnPanel.setBackground(FlatUIColors.MAIN_BG);
		btnPanel.setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.WEST);
		menuPanel = new JPanel();
		menuPanel.setBackground(FlatUIColors.MAIN_BG);
		menuPanel.setLayout(new BorderLayout());
		add(menuPanel, BorderLayout.CENTER);
		buttonsContainer = new JPanel();
		buttonsContainer.setVisible(false);
		buttonsContainer.setBackground(FlatUIColors.MAIN_BG);
		buttonsContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.revalidate();
		menuPanel.repaint();
		menuPanel.add(buttonsContainer);
	}

	private void initPlusBtn() {
		plusBtn = new JButton();
		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.show(plusBtn, 0, plusBtn.getHeight());
			}
		});
		plusBtn.setIcon(new ImageIcon(IVPContextMenu.class.getResource("/usp/ime/line/resources/icons/plus_btn_icon.png")));
		plusBtn.setUI(new IconButtonUI());
		btnPanel.add(plusBtn);
	}

	private void hideMenu() {
		Runnable r = new Runnable() {
			public void run() {
				int delay = 1;
				((JComponent) getParent()).revalidate();
				((JComponent) getParent()).repaint();
				for (int i = 0; i > -menuPanel.getWidth(); i--) {
					try {
						Thread.sleep(delay);
						movePanel(new Point(i, 0));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buttonsContainer.setVisible(false);
				plusBtn.setEnabled(true);
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	private void movePanel(final Point p) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buttonsContainer.setLocation(p);
			}
		});
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
}
