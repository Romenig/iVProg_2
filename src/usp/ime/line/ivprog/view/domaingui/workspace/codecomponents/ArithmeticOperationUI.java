package usp.ime.line.ivprog.view.domaingui.workspace.codecomponents;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.Operation;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ArithmeticOperationUI extends OperationUI {
	
	public ArithmeticOperationUI(String parent, String scope, String id) {
		super(parent, scope, id);
	}

	public void initOperationSignMenu() {
		operationSignMenu = new JPopupMenu();
		Action changeToAddition = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().changeExpressionSign(currentModelID, Expression.OPERATION_ADDITION, context);
			}
		};
		changeToAddition.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("ArithmeticOperationUI.changeSignPanel.tip"));
		changeToAddition.putValue(Action.NAME, "\u002B");
		Action changeToDivision = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().changeExpressionSign(currentModelID, Expression.OPERATION_DIVISION, context);
			}
		};
		changeToDivision.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("ArithmeticOperationUI.changeSignPanel.tip"));
		changeToDivision.putValue(Action.NAME, "/");
		Action changeToMultiplication = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().changeExpressionSign(currentModelID, Expression.OPERATION_MULTIPLICATION, context);
			}
		};
		changeToMultiplication.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("ArithmeticOperationUI.changeSignPanel.tip"));
		changeToMultiplication.putValue(Action.NAME, "\u00D7");
		Action changeToSubtraction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().changeExpressionSign(currentModelID, Expression.OPERATION_SUBTRACTION, context);
			}
		};
		changeToSubtraction.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("ArithmeticOperationUI.changeSignPanel.tip"));
		changeToSubtraction.putValue(Action.NAME, "\u002D");
		Action changeToIntDiv = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				Services.getService().getController().changeExpressionSign(currentModelID, Expression.OPERATION_INTDIV, context);
			}
		};
		changeToIntDiv.putValue(Action.SHORT_DESCRIPTION, ResourceBundleIVP.getString("ArithmeticOperationUI.changeSignPanel.tip"));
		changeToIntDiv.putValue(Action.NAME, "\u0025");
		operationSignMenu.add(changeToAddition);
		operationSignMenu.add(changeToDivision);
		operationSignMenu.add(changeToMultiplication);
		operationSignMenu.add(changeToSubtraction);
		operationSignMenu.addSeparator();
		operationSignMenu.add(changeToIntDiv);
	}

	public void initSignal() {
		String sign = "";
		Operation op = (Operation) Services.getService().getModelMapping().get(currentModelID);
		String type = op.getOperationType();
		if (type.equals(Expression.OPERATION_ADDITION)) {
			sign = "\u002B";
		} else if (type.equals(Expression.OPERATION_DIVISION)) {
			sign = "/";
		} else if (type.equals(Expression.OPERATION_MULTIPLICATION)) {
			sign = "\u00D7";
		} else if (type.equals(Expression.OPERATION_SUBTRACTION)) {
			sign = "\u002D";
		} else if (type.equals(Expression.OPERATION_INTDIV)) {
			sign = "\u0025";
		}
		expSign.setText(sign);
	}

	public void operationTypeChanged(String id, String context) {
		if (currentModelID.equals(id) && this.context.equals(context)) {
			setModelID(id);
		}
	}

	public boolean isContentSet() {
		boolean isCSet = true;
		if (!expressionBaseUI_1.isCSet()) {
			isCSet = false;
		}
		if (!expressionBaseUI_2.isCSet()) {
			if (isCSet) {
				isCSet = false;
			}
		}
		return isCSet;
	}

	public void lockDownCode() {
		disableEdition();
	}
}
