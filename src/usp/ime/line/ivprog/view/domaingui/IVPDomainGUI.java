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

import usp.ime.line.ivprog.listeners.IFunctionListener;
import usp.ime.line.ivprog.model.domainaction.ChangeExpressionSign;
import usp.ime.line.ivprog.model.domainaction.ChangeVariableInitValue;
import usp.ime.line.ivprog.model.domainaction.ChangeVariableName;
import usp.ime.line.ivprog.model.domainaction.ChangeVariableType;
import usp.ime.line.ivprog.model.domainaction.CreateChild;
import usp.ime.line.ivprog.model.domainaction.CreateExpression;
import usp.ime.line.ivprog.model.domainaction.CreateVariable;
import usp.ime.line.ivprog.model.domainaction.DeleteExpression;
import usp.ime.line.ivprog.model.domainaction.DeleteVariable;
import usp.ime.line.ivprog.model.domainaction.MoveComponent;
import usp.ime.line.ivprog.model.domainaction.RemoveChild;
import usp.ime.line.ivprog.model.domainaction.UpdateReferencedVariable;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.console.IVPConsole;
import usp.ime.line.ivprog.view.domaingui.utils.IconButtonUI;
import usp.ime.line.ivprog.view.domaingui.utils.RoundedJPanel;
import usp.ime.line.ivprog.view.domaingui.workspace.codecomponents.FunctionBodyUI;

import javax.swing.JTabbedPane;

/**
 * @author Romenig
 * 
 */
public class IVPDomainGUI extends DomainGUI implements IFunctionListener {

	private JPanel workspacePanel;
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
	private JTabbedPane tabbedPane;

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
				Services.getService().getController().playCode();
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
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(3);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);

		workspacePanel = new JPanel();
		workspacePanel.setPreferredSize(new Dimension(800, 600));
		splitPane.setLeftComponent(workspacePanel);
		workspacePanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		workspacePanel.add(tabbedPane, BorderLayout.CENTER);

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
		CreateVariable createVariable = new CreateVariable("createVariable", "createVariable");
		createVariable.setDomainModel(model);
		_actionList.put("createVariable", createVariable);
		DeleteVariable delVar = new DeleteVariable("deleteVariable", "deleteVariable");
		delVar.setDomainModel(model);
		_actionList.put("delvar", delVar);
		ChangeVariableName changeVarName = new ChangeVariableName("changeVariableName", "changeVariableName");
		changeVarName.setDomainModel(model);
		_actionList.put("changeVarName", changeVarName);
		ChangeVariableType changeVarType = new ChangeVariableType("changeVariableType", "changeVariableType");
		changeVarType.setDomainModel(model);
		_actionList.put("changeVarType", changeVarType);
		ChangeVariableInitValue change = new ChangeVariableInitValue("changeVariableInitValue", "changeVariableInitValue");
		change.setDomainModel(model);
		_actionList.put("changevariableinitvalue", change);
		CreateChild createChild = new CreateChild("createChild", "createChild");
		createChild.setDomainModel(model);
		_actionList.put("createChild", createChild);
		UpdateReferencedVariable updateRefVar = new UpdateReferencedVariable("updateReferenceVariable", "updateReferenceVariable");
		updateRefVar.setDomainModel(model);
		_actionList.put("updateReferenceVariable", updateRefVar);
		RemoveChild removeChild = new RemoveChild("removeChild", "removeChild");
		removeChild.setDomainModel(model);
		_actionList.put("removeChild", removeChild);
		CreateExpression createExpression = new CreateExpression("createExpression", "createExpression");
		createExpression.setDomainModel(model);
		_actionList.put("createExpression", createExpression);
		MoveComponent mChild = new MoveComponent("moveChild", "moveChild");
		mChild.setDomainModel(model);
		_actionList.put("moveComponent", mChild);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.domain.DomainGUI#getSelectedObjects()
	 */
	public Vector getSelectedObjects() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.listeners.IFunctionListener#functionCreated(java.
	 * lang.String)
	 */
	public void functionCreated(String id) {
		updateFunction((FunctionBodyUI) Services.getService().getRenderer().paint(id, ""));
	}

	public void updateFunction(FunctionBodyUI function) {
		if (tabbedPane.getTabCount() == 0) {
			tabbedPane.add(function.getName(), function);
			return;
		}
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(function.getName())) {
				tabbedPane.remove(i);
				tabbedPane.add(function, i);
			}
		}
	}

	// DOMAIN ACTION LIST

	/**
	 * @param scopeID
	 * @param value
	 */
	public void createVariable(String scopeID, String value) {
		CreateVariable newVar = (CreateVariable) _actionList.get("createVariable");
		newVar.setScopeID(scopeID);
		newVar.setInitValue(value);
		newVar.execute();
	}

	/**
	 * @param scopeID
	 * @param variableID
	 */
	public void deleteVariable(String scopeID, String id) {
		DeleteVariable delVar = (DeleteVariable) _actionList.get("delvar");
		delVar.setScopeID(scopeID);
		delVar.setVariableID(id);
		delVar.execute();
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public void changeVariableName(String id, String name) {
		ChangeVariableName changeVarName = (ChangeVariableName) _actionList.get("changeVarName");
		changeVarName.setVariableID(id);
		changeVarName.setNewName(name);
		changeVarName.execute();
	}

	/**
	 * 
	 * @param id
	 * @param expressionInteger
	 */
	public void changeVariableType(String scopeID, String id, String expressionType) {
		ChangeVariableType changeVarType = (ChangeVariableType) _actionList.get("changeVarType");
		changeVarType.setVariableID(id);
		changeVarType.setScopeID(scopeID);
		changeVarType.setNewType(expressionType);
		changeVarType.execute();
	}

	/**
	 * 
	 * @param id
	 * @param value
	 */
	public void changeVariableInitialValue(String id, String value) {
		ChangeVariableInitValue change = (ChangeVariableInitValue) _actionList.get("changevariableinitvalue");
		change.setNewValue(value);
		change.setVariableID(id);
		change.execute();
	}

	/**
	 * @param errorMessage
	 */
	public void printError(String errorMessage) {
		console.printError(errorMessage);
	}

	public void printMessage(String message) {
		console.println(message);
	}

	/**
	 * @param leftExpID
	 * @param holder
	 * @param expressionType
	 * @param primitiveType
	 * @param context
	 * @param context2 
	 */
	public void createExpression(String scopeID, String leftExpID, String holder, String expressionType, String primitiveType, String context) {
		CreateExpression createExpression = (CreateExpression) _actionList.get("createExpression");
		createExpression.setExp1(leftExpID);
		createExpression.setHolder(holder);
		createExpression.setScopeID(scopeID);
		createExpression.setExpressionType(expressionType);
		createExpression.setContext(context);
		createExpression.setPrimitiveType(primitiveType);
		createExpression.execute();
	}

	/**
	 * 
	 * @param id
	 * @param holder
	 * @param context
	 * @param isClean
	 * @param isComparison
	 */
	public void deleteExpression(String id, String holder, String context, boolean isClean, boolean isComparison) {
		DeleteExpression deleteExpression = (DeleteExpression) _actionList.get("deleteexpression");
		deleteExpression.setExpression(id);
		deleteExpression.setHolder(holder);
		deleteExpression.setContext(context);
		deleteExpression.setClean(isClean);
		deleteExpression.setComparison(isComparison);
		deleteExpression.execute();
	}

	/**
	 * 
	 * @param id
	 * @param expressionType
	 * @param context
	 */
	public void changeExpressionSign(String id, String expressionType, String context) {
		ChangeExpressionSign changeExpression = (ChangeExpressionSign) _actionList.get("changeexpressionsign");
		changeExpression.setExpressionID(id);
		changeExpression.setContext(context);
		changeExpression.setNewType(expressionType);
		changeExpression.execute();
	}

	/**
	 * @param currentModelID
	 * @param newRefID
	 */
	public void updateVariableReference(String currentModelID, String newRefID) {
		UpdateReferencedVariable upVar = (UpdateReferencedVariable) _actionList.get("updateReferenceVariable");
		upVar.setReferenceID(currentModelID);
		upVar.setNewVarID(newRefID);
		upVar.execute();
	}

	/**
	 * @param parentModelID
	 * @param childID
	 * @param string
	 */
	public void removeChild(String scopeID, String parentModelID, String childID, String context) {
		RemoveChild removeChild = (RemoveChild) _actionList.get("removeChild");
		removeChild.setChildID(childID);
		removeChild.setContext(context);
		removeChild.setScopeID(scopeID);
		removeChild.setContainerID(parentModelID);
		removeChild.execute();
	}

	/**
	 * @param containerID
	 * @param childType
	 * @param context
	 */
	public void addChild(String scopeID, String containerID, String childType, String context) {
		CreateChild newChild = (CreateChild) _actionList.get("createChild");
		newChild.setClassID(childType);
		newChild.setContainerID(containerID);
		newChild.setScopeID(scopeID);
		newChild.setContext(context);
		newChild.execute();
	}

	/**
	 * @param child
	 * @param origin
	 * @param destiny
	 * @param originContext
	 * @param destinyContext
	 * @param dropIndex
	 */
    public void moveChild(String child, String origin, String destiny, String originContext, String destinyContext, int dropIndex) {
    	MoveComponent mv = (MoveComponent) _actionList.get("moveComponent");
		mv.setComponent(child);
		mv.setOrigin(origin);
		mv.setOriginContext(originContext);
		mv.setDestinyContext(destinyContext);
		mv.setDestiny(destiny);
		mv.setDropY(dropIndex);
		mv.execute();
    }

}
