/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

public abstract class IVPValue extends Expression {

	/**
	 * @param name
	 * @param description
	 */
	public IVPValue(String name, String description) {
		super(name, description);
	}

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
	public abstract IVPBoolean ivpEqualTo(String resultID, IVPValue expressionB, Context context, DataFactory factory, HashMap map);

	/**
	 * Verify if this object is not equal to the given IVPValue v. This method
	 * returns an IVPBoolean with true or false.
	 * 
	 * @return
	 */
	public abstract IVPBoolean ivpNotEqualTo(String resultID, IVPValue number, Context context, DataFactory factory, HashMap map);

}