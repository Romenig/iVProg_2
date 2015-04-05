package usp.ime.line.ivprog.view.domaingui.workspace.codecomponents;

import javax.swing.JPanel;

import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.listeners.IExpressionListener;
import usp.ime.line.ivprog.listeners.IValueListener;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.FlatUIColors;
import usp.ime.line.ivprog.view.domaingui.IDomainObjectUI;
import usp.ime.line.ivprog.view.domaingui.editinplace.EditBoolean;
import usp.ime.line.ivprog.view.domaingui.editinplace.EditInPlace;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.FlowLayout;

public class ConstantUI extends JPanel implements IDomainObjectUI, IValueListener {
	private String currentModelID;
	private String parentModelID;
	private String scopeModelID;
	private String context;
	private String expressionType;
	private EditBoolean editBool;
	private EditInPlace editInPlace;
	private boolean isEditing = false;
	private JLabel valueLabel;

	public ConstantUI(String modelID) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(2);
		currentModelID = modelID;
		initBool();
		initEditInPlace();
		valueLabel = new JLabel();
		valueLabel.setForeground(Color.BLUE);
		add(valueLabel);
		add(editBool);
		add(editInPlace);
		setOpaque(false);
	}

	private void initEditInPlace() {
		editInPlace = new EditInPlace(FlatUIColors.CODE_BG);
		editInPlace.setValueListener(new IValueListener() {
			public void valueChanged(String value) {
				// Services.getService().getController().changeValue(currentModelID,
				// value);
			}
		});
	}

	private void initBool() {
		editBool = new EditBoolean(FlatUIColors.CODE_BG);
		editBool.setValueListener(new IValueListener() {
			public void valueChanged(String value) {
				// Services.getService().getController().changeValue(currentModelID,
				// value);
			}
		});
	}

	private void changeVariableType(String type) {
		if (type.equals(Expression.INTEGER_TYPE)) {
			editInPlace.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_INTEGER);
			editInPlace.setValue(Expression.DEFAULT_INTEGER);
		} else if (type.equals(Expression.DOUBLE_TYPE)) {
			editInPlace.setCurrentPattern(EditInPlace.PATTERN_VARIABLE_VALUE_DOUBLE);
			editInPlace.setValue(Expression.DEFAULT_DOUBLE);
		} else if (type.equals(Expression.STRING_TYPE)) {
			editInPlace.setValue(Expression.DEFAULT_STRING);
		} else if (type.equals(Expression.BOOLEAN_TYPE)) {
			editBool.setValue(Expression.DEFAULT_BOOLEAN);
		}
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

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
		changeVariableType(expressionType);
	}

	private void warningStateOn() {
	}

	private void warningStateOff() {
	}

	public void editStateOn() {
		isEditing = true;
		valueLabel.setVisible(false);
		if (!expressionType.equals(Expression.BOOLEAN_TYPE)) {
			editInPlace.setVisible(true);
			editBool.setVisible(false);
		} else {
			editBool.setVisible(true);
			editInPlace.setVisible(false);
		}
		if (getParent() instanceof ExpressionHolderUI)
			((ExpressionHolderUI) getParent()).editStateOn();
	}

	public void editStateOff(String string) {
		isEditing = false;
		valueLabel.setVisible(true);
		Context c = (Context) Services.getService().getContextMapping().get(scopeModelID);
		String text = getContextValue(this.expressionType, c);
		valueLabel.setText(text);
		editInPlace.setVisible(false);
		editBool.setVisible(false);
		if (getParent() instanceof ExpressionHolderUI)
			((ExpressionHolderUI) getParent()).editStateOff();
	}

	/**
	 * @param type
	 * @return
	 */
	private String getContextValue(String type, Context c) {
		if (type.equals(Expression.DOUBLE_TYPE)) {
			return c.getDouble(this.currentModelID) + "";
		} else if (type.equals(Expression.INTEGER_TYPE)) {
			return c.getInt(this.currentModelID) + "";
		} else if (type.equals(Expression.BOOLEAN_TYPE)) {
			return c.getBoolean(this.currentModelID) + "";
		} else
			return c.getString(this.currentModelID);
	}

	public boolean isEditState() {
		return isEditing;
	}

	public void valueChanged(String value) {
		if (expressionType == Expression.BOOLEAN_TYPE) {
			editBool.setValue(value);
			if (value.equals("true"))
				valueLabel.setText("Verdadeiro");
			else
				valueLabel.setText("Falso");
		} else {
			editInPlace.setValue(value);
			valueLabel.setText(value);
		}
	}

	public boolean isContentSet() {
		return true;
	}

	public void lockDownCode() {
		editStateOff("");
	}
}
