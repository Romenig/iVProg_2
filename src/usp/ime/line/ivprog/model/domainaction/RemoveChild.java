package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class RemoveChild extends DomainAction {
	private IVPDomainModel model;
	private String containerID;
	private String childID;
	private int index;
	private String scopeID;
	private String context;

	public RemoveChild(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		index = model.removeChild(scopeID, containerID, childID, context, _currentState);
	}

	protected void undoAction() {
		model.restoreChild(scopeID, containerID, childID, index, context, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	public String getChildID() {
		return childID;
	}

	public void setChildID(String childID) {
		this.childID = childID;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		String str = "";
		str += "<removechild>\n" + "   <containerid>" + containerID + "</containerid>\n" + "   <childid>" + childID + "</childid>\n"
		        + "   <index>" + index + "</index>\n" + "   <context>" + context + "</context>\n" + "</removechild>\n";
		return str;
	}

	/**
	 * @return the scopeID
	 */
	public String getScopeID() {
		return scopeID;
	}

	/**
	 * @param scopeID
	 *            the scopeID to set
	 */
	public void setScopeID(String scopeID) {
		this.scopeID = scopeID;
	}
}
