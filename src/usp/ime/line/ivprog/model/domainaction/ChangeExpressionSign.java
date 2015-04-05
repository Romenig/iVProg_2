package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class ChangeExpressionSign extends DomainAction {
	private IVPDomainModel model;
	private String expressionID = "";
	private String context = "";
	private String lastType;
	private String newType;

	public ChangeExpressionSign(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		lastType = model.changeOperationSign(expressionID, context, newType, _currentState);
	}

	protected void undoAction() {
		model.changeOperationSign(expressionID, context, lastType, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public String getExpressionID() {
		return expressionID;
	}

	public void setExpressionID(String expressionID) {
		this.expressionID = expressionID;
	}

	public String getLastType() {
		return lastType;
	}

	public void setLastType(String lastType) {
		this.lastType = lastType;
	}

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String toString() {
		String str = "";
		str += "<changeexpressionsign>\n" + "   <expressionid>" + expressionID + "</expressionid>\n" + "   <context>" + context
		        + "</context>\n" + "   <lasttype>" + lastType + "</lasttype>\n" + "   <newtype>" + newType + "</newtype>\n"
		        + "</changeexpressionsign>\n";
		return str;
	}
}
