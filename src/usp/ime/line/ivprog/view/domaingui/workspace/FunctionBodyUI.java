package usp.ime.line.ivprog.view.domaingui.workspace;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import usp.ime.line.ivprog.listeners.ICodeListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.components.dnd.TargetPanel;
import usp.ime.line.ivprog.view.domaingui.workspace.variable.IVPVariablePanel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.peer.ScrollbarPeer;

import javax.swing.ScrollPaneConstants;

public class FunctionBodyUI extends JPanel implements ICodeListener {
	private static final long serialVersionUID = -1559611466195605109L;
	private String name = "";
	private String type = "-1";
	private IVPVariablePanel variablesPanel;
	private TargetPanel container;
	private JScrollPane canvasHolder;
	private String functionID = "";

	public FunctionBodyUI(String functionID, boolean isMain) {
		this.functionID = functionID;
		setLayout(new BorderLayout(0, 0));
		variablesPanel = new IVPVariablePanel(functionID, isMain);
		add(variablesPanel, BorderLayout.NORTH);
		container = new TargetPanel(false);
		canvasHolder = new JScrollPane();
		canvasHolder.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		canvasHolder.setViewportView(container);
		add(canvasHolder, BorderLayout.CENTER);
		//Services.getService().getController().addComponentListener(this, functionID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String string) {
		this.type = string;
	}

	public String getDataFunction() {
		return functionID;
	}

	public void setDataFunction(String function) {
		this.functionID = function;
	}

	public void addChild(String childID, String context) {
		//container.addComponent(childID);
	}

	public void childRemoved(String childID, String context) {
		//container.childRemoved(childID);
	}

	public void restoreChild(String childID, int index, String context) {
		//container.restoreChild(childID, index);
	}

	public void moveChild(String childID, String context, int index) {
		//container.moveChild(childID, index);
	}

	public boolean checkContentSet() {
		//boolean isContentSet = container.isContentSet();
		//return isContentSet;
		return true;
	}

	public void lockCodeDown() {
		//container.lockCodeDown();
	}
}