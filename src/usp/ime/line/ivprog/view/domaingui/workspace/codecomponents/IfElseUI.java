package usp.ime.line.ivprog.view.domaingui.workspace.codecomponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usp.ime.line.ivprog.interpreter.execution.code.IfElse;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.listeners.ICodeListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.components.dnd.TargetPanel;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

import java.awt.Dimension;

public class IfElseUI extends CodeBaseUI implements ICodeListener {
	private JPanel contentPanel;
	private JPanel header;
	private TargetPanel ifContainer;
	private TargetPanel elseContainer;
	private ExpressionFieldUI expressionField;
	private JLabel codeBlockName;
	private JButton expandBtnUP;
	private JButton expandBtnDOWN;
	private Icon up;
	private Icon down;
	private String context;
	private BooleanOperationUI booleanOperationUI;
	private JPanel contentPanelHolder;
	private JPanel elseHeader;
	private JLabel lblSeno;

	public IfElseUI(String modelID, String scopeModelID, String parentModelID) {
		super(modelID);
		setModelID(modelID);
		setModelParent(parentModelID);
		setModelScope(scopeModelID);
		initContentPanel();
		initExpandButtonIcon();
		initHeader();
		initExpressionHolder();
		initContainer();
		addContentPanel(contentPanel);
		initElseHeader();
		initContentPanelHolder();
		setBackground(FlatUIColors.MAIN_BG);
		Services.getService().getController().addCodeListener(this, modelID);
	}

	private void initElseHeader() {
		elseHeader = new JPanel();
		FlowLayout flowLayout = (FlowLayout) elseHeader.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		lblSeno = new JLabel(ResourceBundleIVP.getString("IfElse.lblSeno.text"));
		elseHeader.add(lblSeno);
	}

	private void initContentPanelHolder() {
		contentPanelHolder = new JPanel();
		contentPanelHolder.setVisible(false);
		contentPanel.add(contentPanelHolder, BorderLayout.CENTER);
		contentPanelHolder.setLayout(new BorderLayout(0, 0));
		contentPanelHolder.add(ifContainer, BorderLayout.NORTH);
		contentPanelHolder.add(elseHeader, BorderLayout.CENTER);
		contentPanelHolder.add(elseContainer, BorderLayout.SOUTH);
	}

	private void initContainer() {
		ifContainer = new TargetPanel(true, getModelID(), getModelScope(), "if");
		//ifContainer.setContainerBackground(FlatUIColors.MAIN_BG);
		elseContainer = new TargetPanel(true, getModelID(), getModelScope(), "else");
		//elseContainer.setContainerBackground(FlatUIColors.MAIN_BG);
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
		String condition = ((IfElse) Services.getService().getModelMapping().get(getModelID())).getFlowConditionID();
		booleanOperationUI = (BooleanOperationUI) Services.getService().getRenderer().paint(condition, this.getModelScope());
		expressionField = new ExpressionFieldUI(this.getModelID(), this.getModelScope());
		//expressionField.setHoldingType(booleanOperationUI.getExpressionType());
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
		contentPanelHolder.setVisible(true);
		expandBtnUP.setVisible(true);
		expandBtnDOWN.setVisible(false);
		revalidate();
		repaint();
	}

	protected void expandedActions() {
		contentPanelHolder.setVisible(false);
		expandBtnUP.setVisible(false);
		expandBtnDOWN.setVisible(true);
		revalidate();
		repaint();
	}

	private void initCodeBlockLabel() {
		codeBlockName = new JLabel(ResourceBundleIVP.getString("IfElseUI.if.text"));
		header.add(codeBlockName);
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void addChild(String childID, String context) {
		if (context.equals("if")) {
			ifContainer.addComponent(childID);
		} else if (context.equals("else")) {
			elseContainer.addComponent(childID);
		}
	}

	public void childRemoved(String childID, String context) {
		if (context.equals("if")) {
			ifContainer.childRemoved(childID);
		} else if (context.equals("else")) {
			elseContainer.childRemoved(childID);
		}
	}

	public void restoreChild(String childID, int index, String context) {
		if (context.equals("if")) {
			ifContainer.restoreChild(childID, index);
		} else if (context.equals("else")) {
			elseContainer.restoreChild(childID, index);
		}
	}

	public void moveChild(String childID, String context, int index) {
		if (context.equals("if")) {
			ifContainer.moveChild(childID, index);
		} else {
			elseContainer.moveChild(childID, index);
		}
	}

	public boolean isContentSet() {
		boolean isCSet = true;
		if (!expressionField.isContentSet()) {
			isCSet = false;
		}
		if (!ifContainer.isContentSet()) {
			if (isCSet) {
				isCSet = false;
			}
		}
		if (!elseContainer.isContentSet()) {
			if (isCSet) {
				isCSet = false;
			}
		}
		return isCSet;
	}

	public void lockDownCode() {
		expressionField.setEdition(false);
		ifContainer.lockCodeDown();
		elseContainer.lockCodeDown();
	}
}
