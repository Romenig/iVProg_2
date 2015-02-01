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
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;

public abstract class IVPValue extends Expression {

	public static final String INTEGER_TYPE = "int";
	public static final String DOUBLE_TYPE = "double";
	public static final String STRING_TYPE = "String";
	public static final String BOOLEAN_TYPE = "boolean";
	public static final String NULL = "null";
	
	public static final String DEFAULT_INTEGER = "0";
	public static final String DEFAULT_DOUBLE = "0.0";
	public static final String DEFAULT_STRING = "abcd";
	public static final String DEFAULT_BOOLEAN = "true";
	
	private String valueType;

	
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		return this;
	}

	/**
	 * Get this value primitive type.
	 * 
	 * @see usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue
	 * @return
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * Set the value primitive type.
	 * 
	 * @param valueType
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	/**
	 * Verify if this object is equal to the given IVPValue v. This method
	 * returns an IVPBoolean with true or false.
	 * 
	 * @return
	 */
	public abstract IVPBoolean ivpEqualTo(IVPValue v, Context c, HashMap map, DataFactory factory);

	/**
	 * Verify if this object is not equal to the given IVPValue v. This method
	 * returns an IVPBoolean with true or false.
	 * 
	 * @return
	 */
	public abstract IVPBoolean ivpNotEqualTo(IVPValue v, Context c, HashMap map, DataFactory factory);

}