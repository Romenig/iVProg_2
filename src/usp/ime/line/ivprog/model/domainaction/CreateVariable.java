package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.model.utils.Services;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class CreateVariable extends DomainAction {

	private IVPDomainModel model;
	private String scopeID;
	private String varID;
	private String initValue;

	public CreateVariable(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		if (isRedo()) {
			model.restoreVariable(scopeID, varID, _currentState);
		} else {
			varID = model.createVariable(scopeID, initValue, _currentState);
		}
	}

	protected void undoAction() {
		model.removeVariable(scopeID, varID, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public String getScopeID() {
		return scopeID;
	}

	public void setScopeID(String scopeID) {
		this.scopeID = scopeID;
	}

	public String getVarID() {
		return varID;
	}

	public void setVarID(String varID) {
		this.varID = varID;
	}

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public String toString() {
		String str = "";
		str += "<createvariable>\n" + "   <scopeid>" + scopeID + "</scopeid>\n" + "   <varid>" + varID + "</varid>\n" + "   <initvalue>"
		        + initValue + "</initvalue>\n" + "</createvariable>\n";
		return str;
	}
}
