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

	private IVPNumber additionResult;
	
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
		v1.add(additionResult, v2, c, factory, map);
		/*
		if (v1.getValueType() == IVPValue.DOUBLE_TYPE || v2.getValueType() == IVPValue.DOUBLE_TYPE) {
			additionResult.setValueType(IVPValue.DOUBLE_TYPE);
		} else {
			additionResult.setValueType(IVPValue.INTEGER_TYPE);
		}*/
		return additionResult;
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
    public IVPNumber getAdditionResult() {
	    return additionResult;
    }

	/**
	 * @param additionResult the additionResult to set
	 */
    public void setAdditionResult(IVPNumber additionResult) {
	    this.additionResult = additionResult;
    }

}
