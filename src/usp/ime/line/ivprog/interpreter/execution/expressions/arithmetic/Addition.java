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
		IVPNumber result = v1.add(v2, operationResultID, c, factory, map);
		if (v1.getValueType() == IVPValue.DOUBLE_TYPE || v2.getValueType() == IVPValue.DOUBLE_TYPE) {
			result.setValueType(IVPValue.DOUBLE_TYPE);
		} else {
			result.setValueType(IVPValue.INTEGER_TYPE);
		}
		return result;
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
		// TODO Auto-generated method stub
		return false;
	}

}
