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

public class IVPVariable extends Expression {

	private String valueID;
	private String variableType;
	private String variableName;

	
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

}
