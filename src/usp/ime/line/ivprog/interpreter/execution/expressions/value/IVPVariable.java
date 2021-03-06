/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;

public class IVPVariable extends Expression {

	private String valueID;
	private String variableType;
	private String variableName;
	private String scopeID; // It's a Function object id.
	private Vector referenceList;

	/**
	 * @param name
	 * @param description
	 */
	public IVPVariable() {
		super("IVPVariable", "IVPVariable object.");
		referenceList = new Vector();
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		DataObject dO = (DataObject) map.get(valueID);
		return dO.evaluate(c, map, factory);
	}

	/**
	 * @return the variable type
	 */
	public String getVariableType() {
		return variableType;
	}

	/**
	 * @param variableType
	 *            the variable type to set
	 */
	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	/**
	 * @return the valueID
	 */
	public String getValueID() {
		return valueID;
	}

	/**
	 * @param valueID
	 *            the valueID to set
	 */
	public void setValueID(String valueID) {
		this.valueID = valueID;
	}

	/**
	 * Set this variable name.
	 * 
	 * @param name
	 */
	public void setVariableName(String name) {
		variableName = name;
	}

	/**
	 * Get this variable name.
	 * 
	 * @return variableName
	 */
	public String getVariableName() {
		return variableName;
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
		if (valueID.equals(((DataObject) o).getUniqueID())) {
			return true;
		}
		return false;
	}

	/**
	 * Return the function ID if it's a local variable.
	 * 
	 * @return the scopeID
	 */
	public String getScopeID() {
		return scopeID;
	}

	/**
	 * Set the function's ID of which this is a local variable.
	 * 
	 * @param scopeID
	 *            the scopeID to set
	 */
	public void setScopeID(String scopeID) {
		this.scopeID = scopeID;
	}

	/**
	 * @return the referenceList
	 */
	public Vector getReferenceList() {
		return referenceList;
	}

	/**
	 * @param referenceList
	 *            the referenceList to set
	 */
	public void setReferenceList(Vector referenceList) {
		this.referenceList = referenceList;
	}

	/**
	 * Add a reference to the reference list.
	 * 
	 * @param referenceID
	 *            ;
	 */
	public void addReferenceToTheList(String referenceID) {
		referenceList.add(referenceID);
	}

	/**
	 * Remove the given reference from the reference list.
	 * 
	 * @param referenceID
	 *            ;
	 */
	public void removeReferenceFromTheList(String referenceID) {
		referenceList.remove(referenceID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toXML()
	 */
	@Override
	public String toXML() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toCString()
	 */
	@Override
	public String toCString() {
		return null;
	}

	/**
	 * @param newType
	 * @param modelMapping
	 * @param c
	 */
	public void updateVariableType(String newType, HashMap modelMapping, Context c) {
		variableType = newType;
		IVPValue value = (IVPValue) modelMapping.get(valueID);
		value.setValueType(newType);
		IVPError e = null;
		if (newType.equals(Expression.INTEGER_TYPE)) {
			e = c.addInt(valueID, new Integer(Expression.DEFAULT_INTEGER).intValue());
			if (e != null) {
				c.updateInt(valueID, new Integer(Expression.DEFAULT_INTEGER).intValue());
			}
		} else if (newType.equals(Expression.BOOLEAN_TYPE)) {
			e = c.addBoolean(valueID, new Boolean(Expression.DEFAULT_BOOLEAN).booleanValue());
			if (e != null) {
				c.updateBoolean(valueID, new Boolean(Expression.DEFAULT_BOOLEAN).booleanValue());
			}
		} else if (newType.equals(Expression.STRING_TYPE)) {
			e = c.addString(valueID, Expression.DEFAULT_STRING);
			if (e != null) {
				c.updateString(valueID, Expression.DEFAULT_STRING);
			}
		} else if (newType.equals(Expression.DOUBLE_TYPE)) {
			e = c.addDouble(valueID, new Double(Expression.DEFAULT_DOUBLE).doubleValue());
			if (e != null) {
				c.updateDouble(valueID, new Double(Expression.DEFAULT_DOUBLE).doubleValue());
			}
		}
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }

    public Object clone(){
    	IVPVariable v = new IVPVariable();
    	v.setExpressionType(getExpressionType());
    	v.setParentID(getParentID());
    	v.setReferenceList((Vector)getReferenceList().clone());
    	v.setScopeID(getScopeID());
    	v.setUniqueID(getUniqueID());
    	v.setValueID(getValueID());
    	v.setVariableName(getVariableName());
    	v.setVariableType(getVariableType());
    	return v;
    }
}
