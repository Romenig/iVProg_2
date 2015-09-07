package usp.ime.line.ivprog.interpreter.execution.expressions;

import java.util.HashMap;

import ilm.framework.assignment.model.DomainObject;
import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.model.utils.Services;

public abstract class Operation extends Expression {

	protected String expressionAID = "";
	protected String expressionBID = "";
	protected String operationResultID = "";

	public Operation(String name, String description) {
		super(name, description);
	}

	/**
	 * An expression is formed by two other expressions, A and B -> (A
	 * "operator" B). Observation: a variable is also an expression (a terminal
	 * symbol). This method returns the left side of an expression. In other
	 * words, it returns expression A.
	 * 
	 * @return expressionA
	 */
	public String getExpressionA() {
		return expressionAID;
	}

	/**
	 * An expression is formed by two other expressions, A and B -> (A
	 * "operator" B). Observation: a variable is also an expression (a terminal
	 * symbol). This method sets the left side of an expression. In other words,
	 * it sets expression A.
	 * 
	 * @param expA
	 */
	public void setExpressionA(String expA) {
		expressionAID = expA;
	}

	/**
	 * An expression is formed by two other expressions, A and B -> (A
	 * "operator" B). Observation: a variable is also an expression (a terminal
	 * symbol). This method returns the right side of an expression. In other
	 * words, it returns expression B.
	 * 
	 * @return expressionB
	 */
	public String getExpressionB() {
		return expressionBID;
	}

	/**
	 * An expression is formed by two other expressions, A and B -> (A
	 * "operator" B). Observation: a variable is also an expression (a terminal
	 * symbol). This method sets the right side of an expression. In other
	 * words, it sets expression B.
	 * 
	 * @param expressionA
	 */
	public void setExpressionB(String expB) {
		expressionBID = expB;
	}

	/**
	 * This method get the operation type. An operation might be arithmetical or
	 * logical.
	 * 
	 * @see {@literal} BuilderConstant for more information.
	 * @return expressionType
	 */
	public String getOperationType() {
		return getExpressionType();
	}

	public String toXML() {
		Expression expA = (Expression) Services.getService().getModelMapping().get(expressionAID);
		Expression expB = (Expression) Services.getService().getModelMapping().get(expressionBID);
		String str = "<dataobject class=\"operation\"><id>" + getUniqueID() + "</id>" + "<operationtype>" + expressionType
		        + "<operationtype>" + "<expressionA>" + expA.toXML() + "</expressionA>" + "<expressionB>" + expB.toXML() + "</expressionB>"
		        + "</dataobject>";
		return str;
	}

	public String toCString() {
		Expression expA = (Expression) Services.getService().getModelMapping().get(expressionAID);
		Expression expB = (Expression) Services.getService().getModelMapping().get(expressionBID);
		String str = "(";
		if (getExpressionType().equals(Operation.OPERATION_DIVISION)) {
			str += expA.toCString() + "*1.0 " + getOperationTypeAsString() + " ";
		} else {
			str += expA.toCString() + " " + getOperationTypeAsString() + " ";
		}
		str += expB.toCString() + " )";
		return str;
	}

	private String getOperationTypeAsString() {
		if (getOperationType().equals(Operation.OPERATION_ADDITION)) {
			return "+";
		} else if (getOperationType().equals(Operation.OPERATION_MULTIPLICATION)) {
			return "*";
		} else if (getOperationType().equals(Operation.OPERATION_SUBTRACTION)) {
			return "-";
		} else if (getOperationType().equals(Operation.OPERATION_DIVISION)) {
			return "/";
		} else if (getOperationType().equals(Operation.OPERATION_LES)) {
			return "<";
		} else if (getOperationType().equals(Operation.OPERATION_LEQ)) {
			return "<=";
		} else if (getOperationType().equals(Operation.OPERATION_EQU)) {
			return "==";
		} else if (getOperationType().equals(Operation.OPERATION_GEQ)) {
			return ">=";
		} else if (getOperationType().equals(Operation.OPERATION_GRE)) {
			return ">";
		} else if (getOperationType().equals(Operation.OPERATION_NEQ)) {
			return "!=";
		} else if (getOperationType().equals(Operation.OPERATION_AND)) {
			return "&&";
		} else if (getOperationType().equals(Operation.OPERATION_OR)) {
			return "||";
		} else if (getOperationType().equals(Operation.OPERATION_CONCAT)) {
			return "+";
		} else if (getOperationType().equals(Operation.OPERATION_INTDIV)) {
			return "%";
		}
		return "";
	}

	public boolean equals(DomainObject o) {
		return false;
	}

	public void removeExpression(String expression) {
		if (expressionAID != null && expressionAID.equals(expression) && "".equals(expressionAID)) {
			expressionAID = "";
		} else {
			expressionBID = "";
		}
	}

	public void updateParent(String lastExp, String newExp, String operationContext) {
		if (operationContext.equals("right")) {
			if (expressionBID.equals(lastExp) || expressionBID == "") {
				expressionBID = newExp;
			}
		} else {
			if (expressionAID.equals(lastExp) || expressionAID == "") {
				expressionAID = newExp;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog
	 * .interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	@Override
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		return null;
	}

	/**
	 * Each operation has it's result. A result maybe a IVPNumber, IVPBoolean or IVPString.
	 * This method returns the operation result object id.
	 * @return
	 */
	public String getOperationResultID() {
		return operationResultID;
	}

	 /**
	  * Each operation has it's result. A result maybe a IVPNumber, IVPBoolean or IVPString.
	  * This method sets the operation result ID.
	  * @param operationResultID
	  */
	public void setOperationResultID(String operationResultID) {
		this.operationResultID = operationResultID;
	}

}
