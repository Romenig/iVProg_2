package usp.ime.line.ivprog.model.domainaction;

import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.model.IVPProgramData;
import usp.ime.line.ivprog.model.utils.Services;
import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainModel;

public class DeleteExpression extends DomainAction {
	private String holder;
	private String expression;
	private boolean isClean;
	private boolean isComparison;
	private IVPDomainModel model;
	private String context;
	private String scopeID;

	public DeleteExpression(String name, String description) {
		super(name, description);
	}

	public void setDomainModel(DomainModel m) {
		model = (IVPDomainModel) m;
	}

	protected void executeAction() {
		model.deleteExpression(scopeID, expression, holder, context, isClean, isComparison, _currentState);
	}

	protected void undoAction() {
		model.restoreExpression(scopeID, expression, holder, context, isClean, _currentState);
	}

	public boolean equals(DomainAction a) {
		return false;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void setContext(String ctx) {
		context = ctx;
	}

	public String getContext() {
		return context;
	}

	public boolean isClean() {
		return isClean;
	}

	public void setClean(boolean isClean) {
		this.isClean = isClean;
	}

	public boolean isComparison() {
		return isComparison;
	}

	public void setComparison(boolean isComparison) {
		this.isComparison = isComparison;
	}

	public String toString() {
		String str = "";
		str += "<deleteexpression>\n" + "   <holder>" + holder + "</holder>\n" + "   <expression>" + expression + "</expression>\n"
		        + "   <isclean>" + isClean + "</isclean>\n" + "   <iscomparison>" + isComparison + "</iscomparison>\n" + "   <context>"
		        + context + "</context>\n" + "</deleteexpression>\n";
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
