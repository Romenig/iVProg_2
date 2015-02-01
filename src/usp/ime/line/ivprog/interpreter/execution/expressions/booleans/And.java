/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.booleans;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;

public class And extends Expression {

	private String expA;
	private String expB;

	/**
	 * Set the left expression of and. And := expressionA && expressionB
	 * 
	 * @param expressionA
	 */
	public void setExpressionA(String expressionA) {
		expA = expressionA;
	}

	/**
	 * Set the right expression of and. And := expressionA && expressionB
	 * 
	 * @param expressionB
	 */
	public void setExpressionB(String expressionB) {
		expB = expressionB;
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		IVPBoolean b1 = (IVPBoolean) ((DataObject)map.get(expA)).evaluate(c, map, factory);
		IVPBoolean b2 = (IVPBoolean) ((DataObject)map.get(expB)).evaluate(c, map, factory);
		IVPBoolean result = b1.and(b2, c, factory);
		return result;
	}

}
