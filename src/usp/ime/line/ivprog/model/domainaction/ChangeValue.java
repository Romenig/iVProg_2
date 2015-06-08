package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class ChangeValue extends DomainAction {
	private IVPDomainModel model;
	private String id = "";
	private String lastValue = "";
	private String newValue = "";
	private String context = "";
	private String holder = "";
	private String scopeID = "";

	public ChangeValue(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		lastValue = model.changeValue(scopeID, id, newValue, _currentState);
	}

	protected void undoAction() {
		model.changeValue(scopeID, id, lastValue, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public IVPDomainModel getModel() {
		return model;
	}

	public void setModel(IVPDomainModel model) {
		this.model = model;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastValue() {
		return lastValue;
	}

	public void setLastValue(String lastValue) {
		this.lastValue = lastValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String toString() {
		String str = "";
		str += "<changevalue>\n" + "   <id>" + id + "</id>\n" + "   <lastvalue>" + lastValue + "</lastvalue>\n" + "   <newvalue>"
		        + newValue + "</newvalue>\n" + "   <context>" + context + "</context>\n" + "   <holder>" + holder + "</holder>\n"
		        + "</changevalue>\n";
		return str;
	}

	/**
	 * @return the scopeID
	 */
    public String getScopeID() {
	    return scopeID;
    }

	/**
	 * @param scopeID the scopeID to set
	 */
    public void setScopeID(String scopeID) {
	    this.scopeID = scopeID;
    }
}
