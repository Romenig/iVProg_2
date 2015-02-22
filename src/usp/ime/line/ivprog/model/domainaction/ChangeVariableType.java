package usp.ime.line.ivprog.model.domainaction;

import java.util.Vector;

import usp.ime.line.ivprog.model.IVPDomainModel;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class ChangeVariableType extends DomainAction {
	
	private IVPDomainModel model;
	private String lastType;
	private Vector returnedVector;
	private String newType;
	private String variableID;

	public ChangeVariableType(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		if (isRedo()) {
			model.changeVariableType(variableID, newType, _currentState);
		} else {
			returnedVector = model.changeVariableType(variableID, newType, _currentState);
		}
	}

	protected void undoAction() {
		model.restoreVariableType(variableID, returnedVector, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public String getVariableID() {
		return variableID;
	}

	public void setVariableID(String variableID) {
		this.variableID = variableID;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getLastType() {
		return lastType;
	}

	public void setLastType(String lastType) {
		this.lastType = lastType;
	}

	public String toString() {
		String str = "";
		str += "<changevariabletype>\n" + "   <variableid>" + variableID + "</variableid>\n" + "   <lasttype>" + lastType + "</lasttype>\n"
		        + "   <returnedvector>" + returnedVector.toString() + "</returnedvector>\n" + "   <newtype>" + newType + "</newtype>\n"
		        + "</changevariabletype>\n";
		return str;
	}

	public Vector getReturnedVector() {
		return returnedVector;
	}

	public void setReturnedVector(Vector returnedVector) {
		this.returnedVector = returnedVector;
	}
}
