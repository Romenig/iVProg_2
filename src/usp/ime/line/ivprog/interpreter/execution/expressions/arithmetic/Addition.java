/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic;

import ilm.framework.assignment.model.DomainObject;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.Operation;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class Addition extends Operation {

	private String additionResultID;
	
	/**
	 * @param name
	 * @param description
	 */
	public Addition() {
		super("Addition", "Addition object.");
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		IVPNumber v1 = (IVPNumber) ((DataObject) map.get(expressionAID)).evaluate(c, map, factory);
		IVPNumber v2 = (IVPNumber) ((DataObject) map.get(expressionBID)).evaluate(c, map, factory);
		return v1.add(additionResultID, v2, c, factory, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment
	 * .model.DomainObject)
	 */
	@Override
	public boolean equals(DomainObject o) {
		return false;
	}

	/**
	 * @return the additionResult
	 */
    public String getAdditionResult() {
	    return additionResultID;
    }

	/**
	 * @param additionResult the additionResult to set
	 */
    public void setAdditionResult(String additionResult) {
	    this.additionResultID = additionResult;
    }

    public Object clone(){
    	Addition a = new Addition();
    	a.setAdditionResult(additionResultID);
    	a.setExpressionA(getExpressionA());
    	a.setExpressionB(getExpressionB());
    	a.setExpressionType(getExpressionType());
    	a.setOperationResultID(getOperationResultID());
    	a.setParentID(getParentID());
    	a.setScopeID(getScopeID());
    	a.setUniqueID(getUniqueID());
    	return a;
    }
}
