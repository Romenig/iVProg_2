package usp.ime.line.ivprog.controller;

import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.view.domaingui.IVPDomainGUI;

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
	public void changeVariableType(String id, String variableType) {
		currentDomainGUI.changeVariableType(id, variableType);
	}
	
	/**
	 * Change the variable initial value to the given one.
	 * @param variableID 
	 * @param newValueAsString 
	 */
	public void changeVariableInitialValue(String variableID, String newValueAsString) {
		currentDomainGUI.changeVariableInitialValue(variableID, newValueAsString);
	}
	
	/**
	 * Change the variable name to the given one.
	 * @param id
	 * @param name
	 */
	public void changeVariableName(String id, String name) {
		currentDomainGUI.changeVariableName(id, name);
	}
	
	/**
	 * This method is used to verify if there is another variable with the given name in the same scope (function).
	 * @param scopeID
	 * @param name
	 * @return
	 */
	public boolean validateVariableName(String scopeID, String name) {
		return model.validateVariableName(scopeID, name);
	}
	
	/**
	 * Prints an error message into the console.
	 * @param errorMessage
	 */
	public void printError(String errorMessage) {
		currentDomainGUI.printError(errorMessage);
	}
	
	
}
