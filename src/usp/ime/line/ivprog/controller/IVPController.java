package usp.ime.line.ivprog.controller;

import java.awt.Cursor;

import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.listeners.ICodeListener;
import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.IVPDomainGUI;
import usp.ime.line.ivprog.view.domaingui.workspace.codecomponents.FunctionBodyUI;

public class IVPController {

	private IVPDomainGUI currentDomainGUI;
	private IVPDomainModel model;

	/**
	 * @return the currentDomainGUI
	 */
	public IVPDomainGUI getCurrentDomainGUI() {
		return currentDomainGUI;
	}

	/**
	 * @param currentDomainGUI
	 *            the currentDomainGUI to set
	 */
	public void setCurrentDomainGUI(IVPDomainGUI currentDomainGUI) {
		this.currentDomainGUI = currentDomainGUI;
	}

	/**
	 * @return the model
	 */
	public IVPDomainModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(IVPDomainModel model) {
		this.model = model;
	}

	/**
	 * Initializes the domain model with the main function.
	 */
	public void initializeModel() {
		model.initializeModel();
	}

	/**
	 * @param scopeID
	 * @param string
	 */
	public void addVariable(String scopeID, String value) {
		currentDomainGUI.createVariable(scopeID, value);
	}

	/**
	 * Delete the variable. This action comes from the delete button on the
	 * variable.
	 * 
	 * @param scopeID
	 * @param variableID
	 */
	public void deleteVariable(String scopeID, String variableID) {
		currentDomainGUI.deleteVariable(scopeID, variableID);
	}

	/**
	 * Change the variable Type. The parameter variable type must be one of the
	 * IVPValues constants (e.g. IVPValue.INTEGER_TYPE).
	 * 
	 * @param id
	 * @param variableType
	 */
	public void changeVariableType(String scopeID, String id, String variableType) {
		currentDomainGUI.changeVariableType(scopeID, id, variableType);
	}

	/**
	 * Change the variable initial value to the given one.
	 * 
	 * @param variableID
	 * @param newValueAsString
	 */
	public void changeVariableInitialValue(String variableID, String newValueAsString) {
		currentDomainGUI.changeVariableInitialValue(variableID, newValueAsString);
	}
	
	/**
	 * Change the ConstantUI value.
	 * @param id
	 * @param newValue
	 */
	public void changeValue(String scopeID, String id, String newValue) {
		currentDomainGUI.changeValue(scopeID, id, newValue);
	}

	/**
	 * Change the variable name to the given one.
	 * 
	 * @param id
	 * @param name
	 */
	public void changeVariableName(String id, String name) {
		currentDomainGUI.changeVariableName(id, name);
	}

	/**
	 * This method is used to verify if there is another variable with the given
	 * name in the same scope (function).
	 * 
	 * @param scopeID
	 * @param name
	 * @return
	 */
	public boolean validateVariableName(String scopeID, String name) {
		return model.validateVariableName(scopeID, name);
	}

	/**
	 * Prints an error message into the console.
	 * 
	 * @param errorMessage
	 */
	public void printError(String errorMessage) {
		currentDomainGUI.printError(errorMessage);
	}

	/**
	 * Prints an error message into the console.
	 * 
	 * @param errorMessage
	 */
	public void printMessage(String message) {
		currentDomainGUI.printMessage(message);
	}

	public void playCode() {
		model.playCode();
	}

	public void createExpression(String scopeID, String leftExpID, String holder, String expressionType, String holdingType, String context) {
		currentDomainGUI.createExpression(scopeID, leftExpID, holder, expressionType, holdingType, context);
	}

	public void deleteExpression(String id, String holder, String context, boolean isClean, boolean isComparison) {
		currentDomainGUI.deleteExpression(id, holder, context, isClean, isComparison);
	}

	public void changeExpressionSign(String id, String expressionType, String context) {
		currentDomainGUI.changeExpressionSign(id, expressionType, context);
	}

	/**
	 * @param currentModelID
	 * @param newRefID
	 */
	public void updateVariableReference(String currentModelID, String newRefID) {
		currentDomainGUI.updateVariableReference(currentModelID, newRefID);
	}

	/**
	 * @param parentModelID
	 * @param string
	 * @param id
	 * @param operationContext
	 */
	public void updateParent(String parentModelID, String currentModelID, String newExpID, String context) {
		model.updateParent(parentModelID, currentModelID, newExpID, context);
	}

	/**
	 * @param parentModelID
	 * @param thisModelID
	 * @param string
	 */
	public void removeChild(String scopeID, String parentModelID, String thisModelID, String string) {
		currentDomainGUI.removeChild(scopeID, parentModelID, thisModelID, string);
	}

	/**
	 * 
	 * @param containerID
	 * @param childType
	 * @param context
	 */
	public void addChild(String scopeID, String containerID, String childType, String context) {
		currentDomainGUI.addChild(scopeID, containerID, childType, context);
	}

	public void addCodeListener(ICodeListener listener, String id) {
		model.addComponentListener(listener, id);
	}
	
	public void moveChild(String child, String origin, String destiny, String originContext, String destinyContext, int dropIndex) {
		currentDomainGUI.moveChild(child, origin, destiny, originContext, destinyContext, dropIndex);
	}
	
	public void changeCursor(int cursor) {
		currentDomainGUI.setCursor(Cursor.getPredefinedCursor(cursor));
	}

	/**
	 * @return
	 */
	public boolean isContentSet() {
		// TODO: aqui dá pra criar uma mensagem pra olhar quais funções deverão
		// ter os campos verificados.
		boolean isCSet = true;
		Object[] functions = Services.getService().getProgramData().getFunctionMap().values().toArray();
		Function func = Services.getService().getProgramData().getMainFunction();
		FunctionBodyUI f_main = (FunctionBodyUI) Services.getService().getViewMapping().getObject(func.getUniqueID());
		if (!f_main.checkContentSet()) {
			if (isCSet) {
				isCSet = false;
			}
		}
		for (int i = 0; i < functions.length; i++) {
			FunctionBodyUI f = (FunctionBodyUI) Services.getService().getViewMapping().getObject(((Function) functions[i]).getUniqueID());
			if (!f.checkContentSet()) {
				if (isCSet) {
					isCSet = false;
				}
			}
		}
		return isCSet;
	}

	/**
	 * 
	 */
	public void lockCodeDown() {
		Object[] functions = Services.getService().getProgramData().getFunctionMap().values().toArray();
		for (int i = 0; i < functions.length; i++) {
			FunctionBodyUI f = (FunctionBodyUI) Services.getService().getViewMapping().getObject(((Function) functions[i]).getUniqueID());
			f.lockCodeDown();
		}
	}

}
