package usp.ime.line.ivprog.model.utils;

import ilm.framework.assignment.model.AssignmentState;

import java.util.HashMap;

import javax.swing.JComponent;

import usp.ime.line.ivprog.components.dnd.DnDMouseAdapter;
import usp.ime.line.ivprog.controller.IVPController;
import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.model.IVPProgramData;
import usp.ime.line.ivprog.view.domaingui.IVPRenderer;

public class Services {

	private DnDMouseAdapter mL = null;
	private static Services instance;
	private IVPController controller;
	private AssignmentState currentState;
	private IVPDomainModel model;
	private IVPRenderer render;

	private Services() {
		mL = new DnDMouseAdapter();
		controller = new IVPController();
		render = new IVPRenderer();
	}

	public static Services getService() {
		if (instance != null) {
			return instance;
		} else {
			instance = new Services();
		}
		return instance;
	}

	/**
	 * @return the mL
	 */
	public DnDMouseAdapter getML() {
		return mL;
	}

	/**
	 * @param mL
	 *            the mL to set
	 */
	public void setML(DnDMouseAdapter mL) {
		this.mL = mL;
	}

	/**
	 * @return
	 */
    public IVPController getController() {
	    return controller;
    }

	/**
	 * @param controller the controller to set
	 */
    public void setController(IVPController controller) {
	    this.controller = controller;
    }
    
    /**
     * Return the current assignment state (the opened assignment tab).
     * @return
     */
	public AssignmentState getCurrentState() {
		return currentState;
	}

	/**
	 * Set the current assignment state (the opened assignement tab).
	 * @param current
	 */
	public void setCurrentState(AssignmentState current) {
		this.currentState = current;
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
     * Returns the data about relationships
     * @return
     */
    public IVPProgramData getProgramData(){
    	return (IVPProgramData) getCurrentState().get(0);
    }
    
    /**
     * Get the model hashmap.
     * @return
     */
    public HashMap getModelMapping(){
    	IVPProgramData data = (IVPProgramData) getCurrentState().get(0);
    	return data.getModelMapping();
    }
    
    /**
     * Get the view hashmap.
     * @return
     */
    public IVPMapping getViewMapping(){
    	IVPMapping data = (IVPMapping) getCurrentState().get(1);
    	return data;
    }

	/**
	 * 
	 * @return
	 */
    public IVPRenderer getRenderer() {
	    return render;
    }

	/**
	 * @return
	 */
    public HashMap getContextMapping() {
    	IVPProgramData data = (IVPProgramData) getCurrentState().get(0);
	    return (HashMap) data.getContextMap();
    }
    
    
}
