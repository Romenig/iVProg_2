package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class ChangeVariableName extends DomainAction {
	private IVPDomainModel model;
	private String lastName;
	private String newName;
	private String variableID;

	public ChangeVariableName(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		lastName = model.changeVariableName(variableID, newName, _currentState);
	}

	protected void undoAction() {
		model.changeVariableName(variableID, lastName, _currentState);
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

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		String str = "";
		str += "<changevariablename>\n" + "   <lastname>" + lastName + "</lastname>\n" + "   <newname>" + newName + "</newname>\n"
		        + "   <variableid>" + variableID + "</variableid>\n" + "</changevariablename>\n";
		return str;
	}
}
