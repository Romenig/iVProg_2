package usp.ime.line.ivprog.view.domaingui.workspace.codecomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usp.ime.line.ivprog.interpreter.execution.code.While;
import usp.ime.line.ivprog.listeners.ICodeListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.components.dnd.TargetPanel;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JToolBar;

public class WhileUI extends CodeBaseUI implements ICodeListener {
	
	private JPanel contentPanel;
	private JPanel header;
	private TargetPanel container;
	private ExpressionFieldUI expressionField;
	private JLabel codeBlockName;
	private JButton expandBtnUP;
	private JButton expandBtnDOWN;
	private Icon up;
	private Icon down;
	private String context;
	private BooleanOperationUI booleanOperationUI;

	public WhileUI(String modelID, String scopeModelID, String parentModelID) {
		super(modelID);
		setModelID(modelID);
		setModelScope(scopeModelID);
		setModelParent(parentModelID);
		initContentPanel();
		initExpandButtonIcon();
		initHeader();
		initExpressionHolder();
		initContainer();
		addContentPanel(contentPanel);
		setBackground(FlatUIColors.MAIN_BG);
		Services.getService().getController().addCodeListener(this, modelID);
	}

	private void initContainer() {
		container = new TargetPanel(true, getModelID(), getModelScope(), "");
		container.setVisible(false);
		contentPanel.add(container, BorderLayout.CENTER);
	}

	private void initExpandButtonIcon() {
		up = new javax.swing.ImageIcon(getClass().getResource("/usp/ime/line/resources/icons/expand_up.png"));
		down = new javax.swing.ImageIcon(getClass().getResource("/usp/ime/line/resources/icons/expand_down.png"));
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false);
	}

	private void initHeader() {
		header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setOpaque(false);
		contentPanel.add(header, BorderLayout.NORTH);
		initExpandBtnUP();
		initExpandBtnDOWN();
		initCodeBlockLabel();
		initExpression();
	}

	private void initExpressionHolder() {
	}

	private void initExpression() {
		String condition = ((While)Services.getService().getModelMapping().get(getModelID())).getLoopConditionID();
				
		booleanOperationUI = (BooleanOperationUI) Services.getService().getRenderer().paint(condition, getModelScope());
		expressionField = new ExpressionFieldUI(this.getModelID(), this.getModelScope());
		expressionField.setHolderContent(booleanOperationUI);
		expressionField.setComparison(true);
		expressionField.setBlocked(false);
		header.add(expressionField);
	}

	private void initExpandBtnUP() {
		expandBtnUP = new JButton();
		expandBtnUP.setIcon(up);
		expandBtnUP.setUI(new IconButtonUI());
		expandBtnUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				expandedActions();
			}
		});
		expandBtnUP.setVisible(false);
		header.add(expandBtnUP);
	}

	private void initExpandBtnDOWN() {
		expandBtnDOWN = new JButton();
		expandBtnDOWN.setIcon(down);
		expandBtnDOWN.setUI(new IconButtonUI());
		expandBtnDOWN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notExpandedAction();
			}
		});
		header.add(expandBtnDOWN);
	}

	protected void notExpandedAction() {
		container.setVisible(true);
		expandBtnUP.setVisible(true);
		expandBtnDOWN.setVisible(false);
		revalidate();
		repaint();
	}

	protected void expandedActions() {
		container.setVisible(false);
		expandBtnUP.setVisible(false);
		expandBtnDOWN.setVisible(true);
		revalidate();
		repaint();
	}

	private void initCodeBlockLabel() {
		codeBlockName = new JLabel(ResourceBundleIVP.getString("WhileUI.text"));
		header.add(codeBlockName);
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void addChild(String childID, String context) {
		container.addComponent(childID);
	}

	public void childRemoved(String childID, String context) {
		container.childRemoved(childID);
	}

	public void restoreChild(String childID, int index, String context) {
		container.restoreChild(childID, index);
	}

	public void moveChild(String childID, String context, int index) {
		container.moveChild(childID, index);
	}

	public boolean isContentSet() {
		boolean isCSet = true;
		if (!expressionField.isContentSet()) {
			isCSet = false;
		}
		if (!container.isContentSet()) {
			if (isCSet) {
				isCSet = false;
			}
		}
		return isCSet;
	}

	public void lockDownCode() {
		expressionField.setEdition(false);
		container.lockCodeDown();
	}
}
