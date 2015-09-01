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

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPString extends IVPValue {

	/**
	 * @param name
	 * @param description
	 */
	public IVPString() {
		super("IVPString", "IVPString object.");
	}

	/**
	 * Updates the strings' value to the given value inside the given context.
	 * 
	 * @param context
	 * @param string
	 */
	public void updateValue(Context context, String string) {
		context.updateString(getUniqueID(), string);
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
		IVPBoolean result = factory.createIVPBoolean();
		Boolean booleanResult = new Boolean(c.getString(getUniqueID()).equals(c.getString(v.getUniqueID())));
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

	public IVPBoolean ivpNotEqualTo(String resultID, IVPValue str, Context context, DataFactory factory,
			HashMap map) {
		IVPBoolean result = factory.createIVPBoolean();
		Boolean booleanResult = new Boolean(!context.getString(getUniqueID()).equals(context.getString(str.getUniqueID())));
		context.addBoolean(result.getUniqueID(), booleanResult);
		return result;
	}

	/**
	 * Concatenate this IVPString with another one, generating a new one and
	 * returning it.
	 * 
	 * @param str2
	 * @param c
	 * @param factory
	 * @return
	 */
	public IVPString concatenate(IVPString str2, Context c, DataFactory factory) {
		IVPString result = factory.createIVPString();
		c.addString(result.getUniqueID(), c.getString(getUniqueID()) + c.getString(str2.getUniqueID()));
		return result;
	}

	/**
	 * Get a substring for this IVPString.
	 * 
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public IVPString substring(String beginIndex, String endIndex, Context c, DataFactory factory) {
		IVPString result = factory.createIVPString();
		c.addString(result.getUniqueID(), c.getString(getUniqueID()).substring(Integer.parseInt(beginIndex), Integer.parseInt(endIndex)));
		return result;
	}

	/**
	 * Get the string length for this IVPString.
	 * 
	 * @param c
	 * @param factory
	 * @return
	 */
	public IVPNumber strlen(Context c, DataFactory factory) {
		IVPNumber result = factory.createIVPNumber();
		result.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(result.getUniqueID(), c.getString(getUniqueID()).length());
		return result;
	}

	/**
	 * Get the index for given substring on this IVPString. If it the returned
	 * value is -1, it means that the substrings was not found.
	 * 
	 * @param sub
	 * @param c
	 * @param factory
	 * @return
	 */
	public IVPNumber searchSubstring(IVPString sub, Context c, DataFactory factory) {
		IVPNumber result = factory.createIVPNumber();
		result.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(result.getUniqueID(), c.getString(getUniqueID()).indexOf(c.getString(sub.getUniqueID())));
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
