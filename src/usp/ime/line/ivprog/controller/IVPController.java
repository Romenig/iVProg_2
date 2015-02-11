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
	 * @param currentDomainGUI the currentDomainGUI to set
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
	 * @param model the model to set
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
     * Delete the variable. This action comes from the delete button on the variable.
     * @param scopeID
     * @param variableID
     */
	public void deleteVariable(String scopeID, String variableID) {
		currentDomainGUI.deleteVariable(scopeID, variableID);
	}
}
