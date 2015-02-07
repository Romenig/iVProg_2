/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.domaingui;

import java.util.HashMap;
import java.util.Observable;
import java.util.Vector;

import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainGUI;
import ilm.framework.domain.DomainModel;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import usp.ime.line.ivprog.view.domaingui.console.IVPConsole;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.domaingui.utils.RoundedJPanel;

/**
 * @author Romenig
 * 
 */
public class IVPDomainGUI extends DomainGUI {

	private JPanel functionPanel;
	private JPanel consolePanel;
	private JPanel iconPanel;
	private IVPConsole console;
	private RoundedJPanel consoleContainer;
	private JPanel playAndConsolePanel;
	private JLabel consoleLabel;
	private JButton btnPlay;
	private JButton btnErase;
	private Component verticalStrut;
	private Component verticalStrut_1;

	public IVPDomainGUI() {
		initBasePanels();
		initPlayPanel();
	}

	private void initPlayPanel() {
		playAndConsolePanel = new JPanel();
		playAndConsolePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		consolePanel.add(playAndConsolePanel, BorderLayout.WEST);
		playAndConsolePanel.setLayout(new BoxLayout(playAndConsolePanel, BoxLayout.Y_AXIS));
		Action playAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

			}
		};
		playAction.putValue(Action.SMALL_ICON, new ImageIcon(IVPDomainGUI.class.getResource("/usp/ime/line/resources/icons/play.png")));
		playAction.putValue(Action.SHORT_DESCRIPTION, "Executa a função principal.");
		consoleLabel = new JLabel();
		consoleLabel.setIcon(new ImageIcon(IVPDomainGUI.class.getResource("/usp/ime/line/resources/icons/console.png")));
		playAndConsolePanel.add(consoleLabel);
		verticalStrut = Box.createVerticalStrut(10);
		playAndConsolePanel.add(verticalStrut);
		btnPlay = new JButton(playAction);
		btnPlay.setUI(new IconButtonUI());
		playAndConsolePanel.add(btnPlay);
		verticalStrut_1 = Box.createVerticalStrut(10);
		playAndConsolePanel.add(verticalStrut_1);
		Action cleanAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				console.clean();
			}
		};
		cleanAction.putValue(Action.SMALL_ICON, new ImageIcon(IVPDomainGUI.class.getResource("/usp/ime/line/resources/icons/erase.png")));
		cleanAction.putValue(Action.SHORT_DESCRIPTION, "Executa a função principal.");
		btnErase = new JButton(cleanAction);
		btnErase.setUI(new IconButtonUI());
		playAndConsolePanel.add(btnErase);
	}

	private void initBasePanels() {
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);

		functionPanel = new JPanel();
		splitPane.setLeftComponent(functionPanel);

		consolePanel = new JPanel();
		splitPane.setRightComponent(consolePanel);
		consolePanel.setLayout(new BorderLayout(0, 0));

		iconPanel = new JPanel();
		consolePanel.add(iconPanel, BorderLayout.WEST);

		consoleContainer = new RoundedJPanel();
		consoleContainer.setBackgroundColor(FlatUIColors.CONSOLE_COLOR);
		consoleContainer.setArcs(new Dimension(10, 10));
		consoleContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		consolePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		consoleContainer.setLayout(new BorderLayout(0, 0));
		console = new IVPConsole();
		consoleContainer.add(console, BorderLayout.CENTER);
		consolePanel.add(consoleContainer, BorderLayout.CENTER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.domain.DomainGUI#initDomainGUI()
	 */
	protected void initDomainGUI() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.domain.DomainGUI#initDomainActionList(ilm.framework.domain
	 * .DomainModel)
	 */
	public void initDomainActionList(DomainModel model) {
		_actionList = new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.domain.DomainGUI#getSelectedObjects()
	 */
	public Vector getSelectedObjects() {
		return null;
	}

}
