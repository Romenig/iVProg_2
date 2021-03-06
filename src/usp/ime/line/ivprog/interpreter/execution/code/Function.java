/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import ilm.framework.assignment.model.DomainObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class Function extends CodeComposite {

	private String functionName;
	private String functionReturnType;
	private String functionReturnedElementID;
	private boolean isReturning = false;
	private Vector argumentList;
	private Vector localVariables;

	public Function() {
		super("Function", "Function object.");
		argumentList = new Vector();
		localVariables = new Vector();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog
	 * .interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		for (int i = 0; i < children.size(); i += 1) {
			DataObject component = (DataObject) map.get(children.get(i));
			if (component instanceof Return) {
				DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
				this.setFunctionReturnedElementID(returnedElement.getUniqueID());
				this.setReturning(true);
				return returnedElement;
			}
			component.evaluate(c, map, factory);
			if (this.isReturning()) {
				if (functionReturnedElementID != null) {
					return map.get(functionReturnedElementID);
				}
				return IVPValue.NULL;
			}

		}
		return null;
	}

	/**
	 * Add a new constant to this function context.
	 * 
	 * @param constant
	 * @param constantValue
	 * @param context
	 * @param map
	 */
	public void addConstant(IVPValue constant, String constantValue, Context context, HashMap map) {
		if (constant.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			context.addInt(constant.getUniqueID(), new Integer(constantValue).intValue());
		} else if (constant.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
			context.addDouble(constant.getUniqueID(), new Double(constantValue).doubleValue());
		} else if (constant.getValueType().equals(IVPValue.STRING_TYPE)) {
			context.addString(constant.getUniqueID(), constantValue);
		} else if (constant.getValueType().equals(IVPValue.BOOLEAN_TYPE)) {
			context.addBoolean(constant.getUniqueID(), new Boolean(constantValue));
		}
		map.put(constant.getUniqueID(), constant);
	}

	/**
	 * Add a new variable to this function.
	 * 
	 * @param variable
	 * @param value
	 * @param context
	 * @param map
	 * @param factory
	 */
	public void addVariable(IVPVariable variable, String value, Context context, HashMap map, DataFactory factory) {
		IVPValue varValue = null;
		if (variable.getVariableType().equals(IVPValue.INTEGER_TYPE)) {
			varValue = factory.createIVPNumber();
			context.addInt(varValue.getUniqueID(), new Integer(value).intValue());
		} else if (variable.getVariableType().equals(IVPValue.DOUBLE_TYPE)) {
			varValue = factory.createIVPNumber();
			context.addDouble(varValue.getUniqueID(), new Double(value).doubleValue());
		} else if (variable.getVariableType().equals(IVPValue.STRING_TYPE)) {
			varValue = factory.createIVPString();
			context.addString(varValue.getUniqueID(), value);
		} else if (variable.getVariableType().equals(IVPValue.BOOLEAN_TYPE)) {
			varValue = factory.createIVPBoolean();
			context.addBoolean(varValue.getUniqueID(), new Boolean(value));
		}
		varValue.setValueType(variable.getVariableType());
		variable.setValueID(varValue.getUniqueID());
		map.put(variable.getUniqueID(), variable);
		map.put(varValue.getUniqueID(), varValue);
		localVariables.add(variable.getUniqueID());
	}

	/**
	 * Get the function name.
	 * 
	 * @return the functionName
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * Set the function name.
	 * 
	 * @param functionName
	 *            the functionName to set
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * Get the function returning type.
	 * 
	 * @return the functionReturnType
	 */
	public String getFunctionReturnType() {
		return functionReturnType;
	}

	/**
	 * Set the function return type.
	 * 
	 * @param functionReturnType
	 *            the functionReturnType to set
	 */
	public void setFunctionReturnType(String functionReturnType) {
		this.functionReturnType = functionReturnType;
	}

	/**
	 * Add a parameter to this function. It automatically put a value in the
	 * memory.
	 * 
	 * @param integerType
	 */
	public void addArgument(String type, Context c, HashMap map, DataFactory factory) {
		IVPValue value = null;
		if (type.equals(IVPValue.INTEGER_TYPE)) {
			value = factory.createIVPNumber();
			c.addInt(value.getUniqueID(), new Integer(IVPValue.DEFAULT_INTEGER).intValue());
		} else if (type.equals(IVPValue.DOUBLE_TYPE)) {
			value = factory.createIVPNumber();
			c.addDouble(value.getUniqueID(), new Double(IVPValue.DEFAULT_DOUBLE).doubleValue());
		} else if (type.equals(IVPValue.STRING_TYPE)) {
			value = factory.createIVPString();
			c.addString(value.getUniqueID(), IVPValue.DEFAULT_STRING);
		} else {
			value = factory.createIVPBoolean();
			c.addBoolean(value.getUniqueID(), new Boolean(IVPValue.DEFAULT_BOOLEAN));
		}
		value.setValueType(type);
		map.put(value.getUniqueID(), value);
		argumentList.add(value.getUniqueID());
	}

	/**
	 * Get the argument in the given position.
	 * 
	 * @param position
	 * @return
	 */
	public String getArgument(int position) {
		String argumentID = (String) argumentList.get(position);
		return argumentID;
	}

	/**
	 * @return the functionReturnedElementID
	 */
	public String getFunctionReturnedElementID() {
		return functionReturnedElementID;
	}

	/**
	 * @param functionReturnedElementID
	 *            the functionReturnedElementID to set
	 */
	public void setFunctionReturnedElementID(String functionReturnedElementID) {
		this.functionReturnedElementID = functionReturnedElementID;
	}

	/**
	 * @return the isReturning
	 */
	public boolean isReturning() {
		return isReturning;
	}

	/**
	 * @param isReturning
	 *            the isReturning to set
	 */
	public void setReturning(boolean isReturning) {
		this.isReturning = isReturning;
	}

	/**
	 * Get the local variables ID's.
	 * 
	 * @return the localVariables
	 */
	public Vector getLocalVariables() {
		return localVariables;
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
		if (this.getUniqueID().equals(((DataObject) o).getUniqueID())) {
			return true;
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.code.CodeComposite#updateParent
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateParent(String lastExp, String newExp, String operationContext) {
		// TODO Auto-generated method stub

	}
	
	public Object clone(){
		Function newF = new Function();
		newF.setFunctionName(functionName);
		newF.setFunctionReturnType(functionReturnType);
		newF.setParentID(getParentID());
		newF.setScopeID(getScopeID());
		newF.setUniqueID(getUniqueID());
		newF.localVariables = (Vector) localVariables.clone();
		newF.argumentList = (Vector) argumentList.clone();
		newF.children = (Vector) children.clone();
		return newF;
	}

}
