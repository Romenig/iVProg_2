/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPBoolean extends IVPValue {

	/**
	 * Updates the boolean value to the given value inside the given context.
	 * 
	 * @param context
	 * @param bool
	 */
	public void updateValue(Context context, boolean bool) {
		context.updateBoolean(getUniqueID(), bool);
	}

	/**
	 * @param b2
	 * @return
	 */
	public IVPBoolean and(IVPBoolean b2, Context context, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean res = new Boolean(context.getBoolean(getUniqueID()) && context.getBoolean(b2.getUniqueID()));
		context.addBoolean(result.getUniqueID(), res);
		return result;
	}

	/**
	 * @param b2
	 * @param c
	 * @param factory
	 * @return
	 */
	public IVPBoolean or(IVPBoolean b2, Context context, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean res = new Boolean(context.getBoolean(getUniqueID()) || context.getBoolean(b2.getUniqueID()));
		context.addBoolean(result.getUniqueID(), res);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue#
	 * ivpEqualTo
	 * (usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue,
	 * usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public IVPBoolean ivpEqualTo(IVPValue v, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean booleanResult = new Boolean(c.getBoolean(getUniqueID()) == c.getBoolean(v.getUniqueID()));
		c.addBoolean(result.getUniqueID(), booleanResult);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue#
	 * ivpNotEqualTo
	 * (usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue,
	 * usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public IVPBoolean ivpNotEqualTo(IVPValue v, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean booleanResult = new Boolean(c.getBoolean(getUniqueID()) != c.getBoolean(v.getUniqueID()));
		c.addBoolean(result.getUniqueID(), booleanResult);
		return result;
	}

}
