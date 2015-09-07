/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPBoolean extends IVPValue {

	/**
	 * @param name
	 * @param description
	 */
	public IVPBoolean() {
		super("IVPBoolean", "IVPBoolean object.");
	}

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
	 * @param operationResultID 
	 * @param b2
	 * @return
	 */
	public IVPBoolean and(String operationResultID, IVPBoolean b2, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(operationResultID);
		Boolean res = new Boolean(context.getBoolean(getUniqueID()) && context.getBoolean(b2.getUniqueID()));
		if(context.addBoolean(result.getUniqueID(), res) != null){
			context.updateBoolean(result.getUniqueID(), res);
		}
		return result;
	}

	/**
	 * @param operationResultID 
	 * @param b2
	 * @param c
	 * @param factory
	 * @param map 
	 * @return
	 */
	public IVPBoolean or(String operationResultID, IVPBoolean b2, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(operationResultID);
		Boolean res = new Boolean(context.getBoolean(getUniqueID()) || context.getBoolean(b2.getUniqueID()));
		if(context.addBoolean(result.getUniqueID(), res) != null){
			context.updateBoolean(result.getUniqueID(), res);
		}
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
	public IVPBoolean ivpEqualTo(String resultID, IVPValue v, Context c, DataFactory factory,
			HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		Boolean booleanResult = new Boolean(c.getBoolean(getUniqueID()) == c.getBoolean(v.getUniqueID()));
		if(c.addBoolean(result.getUniqueID(), booleanResult) != null){
			c.updateBoolean(result.getUniqueID(), booleanResult);
		}
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
	public IVPBoolean ivpNotEqualTo(String resultID, IVPValue v, Context c, DataFactory factory,
			HashMap map) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean booleanResult = new Boolean(c.getBoolean(getUniqueID()) != c.getBoolean(v.getUniqueID()));
		c.addBoolean(result.getUniqueID(), booleanResult);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toXML()
	 */
	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toCString()
	 */
	@Override
	public String toCString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }

}
