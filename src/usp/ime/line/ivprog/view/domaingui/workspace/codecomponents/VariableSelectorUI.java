package usp.ime.line.ivprog.view.domaingui.workspace.codecomponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVariableReference;
import usp.ime.line.ivprog.listeners.IVariableListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.IDomainObjectUI;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

public class VariableSelectorUI extends JPanel implements IVariableListener, IDomainObjectUI {

	public static final Color borderColor = new Color(230, 126, 34);
	public static final Color hoverColor = FlatUIColors.HOVER_COLOR;
	private String currentModelID;
	private String parentModelID;
	private String scopeModelID;
	private String referencedID;
	private String context;
	private String lastRemoved = "";
	private JComboBox varList;
	private TreeMap indexMap;
	private JLabel nameLabel;
	private JLabel icon;
	private boolean isUpdate = true;
	private boolean warningState = false;
	private boolean isOnlyOneElement = false;
	private boolean isIsolated = false;
	private boolean drawBorder = true;
	private boolean editState = true;
	private String referencedType = "-1";

	public VariableSelectorUI(String parentID, String modelID, String scopeID) {
		this.parentModelID = parentID;
		setModelScope(scopeID);
		setModelID(modelID);
		setModelParent(parentID);
		initialization();
		initComponents();
		Services.getService().getProgramData().getVariableListeners().add(this);
	}

	// BEGIN: initialization methods
	private void initialization() {
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		flowLayout.setVgap(3);
		flowLayout.setHgap(3);
		setLayout(flowLayout);
		addMouseListener(new ExpressionMouseListener(this));
		setBackground(FlatUIColors.MAIN_BG);
	}

	private void initComponents() {
		initVector();
		initLabel();
		initConfigMenu();
	}

	private void initVector() {
		indexMap = new TreeMap();
	}

	private void initLabel() {
		nameLabel = new JLabel(ResourceBundleIVP.getString("variableSelectorInitialLabel"));
		nameLabel.setForeground(FlatUIColors.CHANGEABLE_ITEMS_COLOR);
		add(nameLabel);
	}

	private void initConfigMenu() {
		varList = new JComboBox();
		varList.setVisible(false);
		initValues();
		varList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!isUpdate) {
					JComboBox cb = (JComboBox) evt.getSource();
					Object item = cb.getSelectedItem();
					if (evt.getActionCommand().equals("comboBoxChanged")) {
						if (isIsolated) {
							editStateOff((String) item);
						}
						String newRefID = getNewVarID();
						if (!"".equals(newRefID)) {
							Services.getService().getController().updateVariableReference(currentModelID, newRefID);
						}
						if (warningState) {
							turnWaningStateOFF();
						}
					}
				}
			}
		});
		add(varList);
	}

	private String getNewVarID() {
		Function f = (Function) Services.getService().getModelMapping().get(scopeModelID);
		if(f == null){
			f = Services.getService().getProgramData().getMainFunction();
		}
		Vector variables = f.getLocalVariables();

		String item = (String) varList.getSelectedItem();
		item = (String) varList.getSelectedItem();

		for (int i = 0; i < variables.size(); i++) {
			IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(variables.get(i));
			String name = getVariableViewName(var);
			if (name.equals(item)) {
				return var.getUniqueID();
			}
		}

		return "";
	}

	private String getVariableViewName(IVPVariable var) {
		String name = var.getVariableName();
		if (name.contains("#@ivprog@#!")) {
			name = name.substring(name.indexOf("!") + 1);
		}
		return name;
	}

	private void initValues() {
		String parentID = parentModelID;
		if (parentID.contains("_"))
			parentID = parentModelID.substring(0, parentModelID.indexOf("_"));
		DataObject component = (DataObject) Services.getService().getModelMapping().get(parentID);

		Function f = (Function) Services.getService().getModelMapping().get(component.getScopeID());
		if(f == null){
			f = Services.getService().getProgramData().getMainFunction();
		}

		Vector variables = f.getLocalVariables();

		for (int i = 0; i < variables.size(); i++) {
			IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(variables.get(i));
			String name = getVariableViewName(var);
			indexMap.put(var.getUniqueID(), name);
		}
		isUpdate = true;
		updateVariableList("", "");
		isUpdate = false;
	}

	private void updateValuesFromVariableList() {
		if (!isIsolated && (!referencedType.equals("-1") && !referencedType.equals("0"))) {
			Function f = (Function) Services.getService().getModelMapping().get(getScopeID());
			if(f == null){
				f = Services.getService().getProgramData().getMainFunction();
			}
			Vector variables = f.getLocalVariables();
			indexMap = new TreeMap();
			for (int i = 0; i < variables.size(); i++) {
				IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(variables.get(i));
				String name = getVariableViewName(var);
				if (referencedType == var.getVariableType()) {
					indexMap.put(var.getUniqueID(), name);
				} else if (referencedType.equals(Expression.DOUBLE_TYPE) && var.getVariableType().equals(Expression.INTEGER_TYPE)) {
					indexMap.put(var.getUniqueID(), name);
				}
			}
			isUpdate = true;
			updateVariableList("", "");
			isUpdate = false;
		}
	}

	// END: initialization methods
	// BEGIN: Variable listener methods
	public void addedVariable(String id) {
		IVPVariable v = ((IVPVariable) Services.getService().getModelMapping().get(id));
		String name = "";
		if (isIsolated) {
			name = getVariableViewName(v);
			indexMap.put(id, name);
			isUpdate = true;
			updateVariableList("", "");
			isUpdate = false;
		} else {
			if (v.getVariableType() == referencedType) {
				name = getVariableViewName(v);
				indexMap.put(id, name);
				isUpdate = true;
				updateVariableList("", "");
				isUpdate = false;
			} else if (referencedType.equals("-1") || referencedType.equals("0")) {
				name = getVariableViewName(v);
				indexMap.put(id, name);
				isUpdate = true;
				updateVariableList("", "");
				isUpdate = false;
			}
		}
	}

	public void changeVariable(String id) {
	}

	public void removedVariable(String id) {
		String name = getVariableViewName(((IVPVariable) Services.getService().getModelMapping().get(id)));
		if (indexMap.containsKey(id)) {
			indexMap.put(id, null);
			if (isIsolated) {
				if (nameLabel.isVisible()) {
					if (nameLabel.getText().equals(name)) {
						lastRemoved = name;
						turnWaningStateON();
					}
				}
			} else {
				if (name.equals(varList.getSelectedItem())) {
					if (isEditState()) {
						lastRemoved = name;
						turnWaningStateON();
					} else {
						if (Services.getService().getModelMapping().get(parentModelID) instanceof ExpressionHolderUI) {
							((ExpressionHolderUI) Services.getService().getModelMapping().get(parentModelID)).warningStateOn();
						}
						lastRemoved = name;
						turnWaningStateON();
					}
				}
			}
			isUpdate = true;
			updateVariableList("", "");
			isUpdate = false;
		}
	}

	public void changeVariableName(String id, String name, String lastName) {
		if (indexMap.containsKey(id)) {
			isUpdate = true;
			indexMap.put(id, name);
			updateVariableList(name, lastName);
			isUpdate = false;
			if (nameLabel.isVisible() && nameLabel.getText().equals(lastName)) {
				nameLabel.setText(name);
				nameLabel.revalidate();
				nameLabel.repaint();
			}
		}
	}

	public void updateReference(String id) {

		if (id.equals(currentModelID)) {

			String name = ((IVPVariableReference) Services.getService().getModelMapping().get(id)).getReferencedName();
			isUpdate = true;
			varList.setSelectedItem(name);
			isUpdate = false;
			if (referencedType.equals("0") || referencedType.equals("-1")) {
				IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(getNewVarID());
				if (var != null) {
					referencedType = var.getVariableType();
				}
				updateValuesFromVariableList();
				isUpdate = true;
				updateVariableList("", "");
				isUpdate = false;
			}
			if (isIsolated) {
				editStateOff(name);
				if (Services.getService().getViewMapping().getObject(parentModelID) instanceof AttributionLineUI) {
					if ("".equals(name) || name == null) {
						((AttributionLineUI) Services.getService().getViewMapping().getObject(parentModelID)).setLeftVarSet(false);
						drawBorder = true;
						setBackground(FlatUIColors.MAIN_BG);
					} else {
						drawBorder = false;
						setBackground(FlatUIColors.CODE_BG);
						((AttributionLineUI) Services.getService().getViewMapping().getObject(parentModelID)).setLeftVarSet(true);
						referencedType = ((IVPVariable) Services.getService().getModelMapping().get(getNewVarID())).getVariableType();
					}
				}
			} else {

				if (nameLabel.isVisible() && !("".equals(name) || name == null)) {
					nameLabel.setText(name);
					nameLabel.revalidate();
					nameLabel.repaint();
					setBackground(FlatUIColors.MAIN_BG);
				} else {
					nameLabel.setText(ResourceBundleIVP.getString("variableSelectorInitialLabel"));
					nameLabel.revalidate();
					nameLabel.repaint();
					setBackground(FlatUIColors.CODE_BG);
				}
				if (Services.getService().getViewMapping().getObject(parentModelID) instanceof OperationUI) {
					if (getNewVarID() != null && !"".equals(getNewVarID())) {
						((OperationUI) Services.getService().getViewMapping().getObject(parentModelID))
						        .setExpressionType(((IVPVariable) Services.getService().getModelMapping().get(getNewVarID()))
						                .getVariableType());
					} else {
						if (Services.getService().getViewMapping().getObject(parentModelID) instanceof BooleanOperationUI) {
							if (!((BooleanOperationUI) Services.getService().getViewMapping().getObject(parentModelID)).isBothContentSet()) {
								((OperationUI) Services.getService().getViewMapping().getObject(parentModelID)).setExpressionType("-1");
								referencedType = "-1";
								initValues();
								isUpdate = true;
								updateVariableList("", "");
								isUpdate = false;
							}
						}
					}
				}
			}
		}
		revalidate();
		repaint();
	}

	public void changeVariableType(String id, String type) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		if (isIsolated) {
			if (getVariableViewName(v).equals(nameLabel.getText()) && nameLabel.isVisible()) {
				referencedType = type;
			}
		} else {
			if (indexMap.containsValue(v.getVariableName())) {
				if (getVariableViewName(v).equals(nameLabel.getText()) && nameLabel.isVisible() || !nameLabel.isVisible()
				        && getVariableViewName(v).equals(varList.getSelectedItem())) {
					lastRemoved = v.getVariableName();
					updateValuesFromVariableList();
					turnWaningStateON();
				} else {
					updateValuesFromVariableList();
				}
			} else {
				if (v.getVariableType() == referencedType) {
					indexMap.put(v.getUniqueID(), getVariableViewName(v));
					isUpdate = true;
					updateVariableList("", "");
					isUpdate = false;
					if (getVariableViewName(v).equals(lastRemoved)) {
						turnWaningStateOFF();
						isUpdate = true;
						varList.setSelectedItem(lastRemoved);
						isUpdate = false;
					}
				}
			}
		}
		revalidate();
		repaint();
	}

	public void variableRestored(String id) {
		String name = getVariableViewName(((IVPVariable) Services.getService().getModelMapping().get(id)));
		if (indexMap.containsKey(id)) {
			indexMap.put(id, name);
			isUpdate = true;
			updateVariableList("", "");
			isUpdate = false;
			if (isIsolated) {
				if (nameLabel.isVisible()) {
					if (lastRemoved.equals(name)) {
						nameLabel.setText(name);
						turnWaningStateOFF();
					}
				} else {
					if (lastRemoved.equals(name)) {
						turnWaningStateOFF();
						isUpdate = true;
						varList.setSelectedItem(lastRemoved);
						isUpdate = false;
					}
				}
			} else {
				if (lastRemoved.equals(name)) {
					turnWaningStateOFF();
					isUpdate = true;
					varList.setSelectedItem(lastRemoved);
					isUpdate = false;
				}
			}
			lastRemoved = "";
			revalidate();
			repaint();
		}
	}

	public void changeVariableValue(String id, String value) {
	}

	// END: Variable listener methods
	// BEGIN: support methods
	public void editStateOn() {
		varList.setVisible(true);
		nameLabel.setVisible(false);
		drawBorder = false;
		if (getParent() instanceof ExpressionHolderUI)
			((ExpressionHolderUI) getParent()).editStateOn();
		if (!isIsolated)
			editState = true;
		revalidate();
		repaint();
	}

	public void editStateOff(String item) {
		varList.setVisible(false);
		if (item != null && !"".equals(item))
			nameLabel.setText(item);
		else
			nameLabel.setText(ResourceBundleIVP.getString("variableSelectorInitialLabel"));
		nameLabel.setVisible(true);
		if (getParent() instanceof ExpressionHolderUI)
			((ExpressionHolderUI) getParent()).editStateOff();
		if (!isIsolated)
			editState = false;
		revalidate();
		repaint();
	}

	private void turnWaningStateON() {
		warningState = true;
		if (Services.getService().getViewMapping().getObject(parentModelID) instanceof ExpressionHolderUI) {
			((ExpressionHolderUI) Services.getService().getViewMapping().getObject(parentModelID)).warningStateOn();
		} else if (Services.getService().getViewMapping().getObject(parentModelID) instanceof OperationUI) {
			((OperationUI) Services.getService().getViewMapping().getObject(parentModelID)).enableEdition();
		}
		editStateOn();
		varList.setBorder(BorderFactory.createLineBorder(Color.red));
		revalidate();
		repaint();
	}

	private void turnWaningStateOFF() {
		warningState = false;
		if (Services.getService().getViewMapping().getObject(parentModelID) instanceof ExpressionHolderUI) {
			((ExpressionHolderUI) Services.getService().getViewMapping().getObject(parentModelID)).warningStateOFF();
		}
		varList.setBorder(null);
		revalidate();
		repaint();
	}

	private void updateVariableList(String newName, String lastName) {
		Object itemSelected = varList.getSelectedItem();
		varList.removeAllItems();
		Object[] keySetArray = indexMap.keySet().toArray();
		int count = 0;
		for (int i = 0; i < keySetArray.length; i++) {
			String variableName = (String) indexMap.get(keySetArray[i]);
			if (variableName != null && !variableName.equals("")) {
				count++;
			}
		}
		isOnlyOneElement = count == 1 ? true : false;
		for (int i = 0; i < keySetArray.length; i++) {
			String variableName = (String) indexMap.get(keySetArray[i]);
			if (variableName != null && !variableName.equals("")) {
				varList.addItem(variableName);
			}
		}
		if (lastName.equals(itemSelected)) {
			varList.setSelectedItem(newName);
		} else {
			varList.setSelectedItem(itemSelected);
		}
	}

	public String getScopeID() {
		return scopeModelID;
	}

	public void setScopeID(String scopeID) {
		this.scopeModelID = scopeID;
	}

	public String getCurrentModelID() {
		return currentModelID;
	}

	public void setCurrentModelID(String currentModelID) {
		this.currentModelID = currentModelID;
	}

	public String getModelID() {
		return currentModelID;
	}

	public String getModelParent() {
		return parentModelID;
	}

	public String getModelScope() {
		return scopeModelID;
	}

	public void setModelID(String id) {
		currentModelID = id;
	}

	public void setModelParent(String id) {
		parentModelID = id;
	}

	public void setModelScope(String id) {
		scopeModelID = id;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public boolean isEditState() {
		return editState;
	}

	public void setEditState(boolean editState) {
		this.editState = editState;
	}

	public String getVarListSelectedItem() {
		return (String) varList.getSelectedItem();
	}

	public void setIsolationMode(boolean isIso) {
		isIsolated = isIso;
		setBackground(FlatUIColors.MAIN_BG);
	}

	public boolean isIsolated() {
		return isIsolated;
	}

	public String referenceType() {
		return ((IVPVariable) Services.getService().getModelMapping().get(getNewVarID())).getVariableType();
	}

	public String getReferencedType() {
		return referencedType;
	}

	public void setReferencedType(String holdingType) {
		this.referencedType = holdingType;
		updateValuesFromVariableList();
		isUpdate = true;
		updateVariableList("", "");
		isUpdate = false;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (drawBorder) {
			FlowLayout layout = (FlowLayout) getLayout();
			layout.setVgap(3);
			layout.setHgap(3);
			revalidate();
			g.setColor(borderColor);
			java.awt.Rectangle bounds = getBounds();
			for (int i = 0; i < bounds.width; i += 6) {
				g.drawLine(i, 0, i + 3, 0);
				g.drawLine(i + 3, bounds.height - 1, i + 6, bounds.height - 1);
			}
			for (int i = 0; i < bounds.height; i += 6) {
				g.drawLine(0, i, 0, i + 3);
				g.drawLine(bounds.width - 1, i + 3, bounds.width - 1, i + 6);
			}
		} else {
			FlowLayout layout = (FlowLayout) getLayout();
			layout.setVgap(0);
			layout.setHgap(0);
		}
	}

	// BEGIN: Mouse listener
	private class ExpressionMouseListener implements MouseListener {
		private JPanel container;
		private int clickCounter = 0;

		public ExpressionMouseListener(JPanel c) {
			container = c;
		}

		public void mouseEntered(MouseEvent e) {
			if (editState || isIsolated) {
				setBackground(hoverColor);
				e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (editState || isIsolated) {
				if (drawBorder) {
					setBackground(FlatUIColors.MAIN_BG);
				} else {
					setBackground(FlatUIColors.CODE_BG);
				}
				e.getComponent().setCursor(Cursor.getDefaultCursor());
			}
		}

		public void mouseClicked(MouseEvent arg0) {
			if (editState) {
				setBorder(null);
				revalidate();
				repaint();
				editStateOn();
				varList.requestFocus();
			}
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	// END: Mouse listener
	public boolean isContentSet() {
		boolean isCSet = true;
		if (nameLabel.isVisible() && nameLabel.getText().equals(ResourceBundleIVP.getString("variableSelectorInitialLabel"))) {
			isCSet = false;
			turnWaningStateON();
		}
		if (!nameLabel.isVisible()) {
			if ("".equals(varList.getSelectedItem()) || varList.getSelectedItem() == null) {
				if (isCSet) {
					isCSet = false;
					turnWaningStateON();
				}
			}
		}
		return isCSet;
	}

	public void lockDownCode() {
		editStateOff(getVarListSelectedItem());
	}

}
