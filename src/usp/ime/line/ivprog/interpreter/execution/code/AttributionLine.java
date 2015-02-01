/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPMatrixReference;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVectorReference;

public class AttributionLine extends DataObject {

	private String variableID;
	private String expressionID;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog
	 * .interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		if (map.get(variableID) instanceof IVPVariable) {
			IVPVariable variable = (IVPVariable) map.get(variableID);
			IVPValue value = (IVPValue) ((DataObject) map.get(expressionID)).evaluate(c, map, factory);
			IVPValue copyOfValue = createCopy(value, c, map, factory);
			variable.setValueID(copyOfValue.getUniqueID());
		} else if (map.get(variableID) instanceof IVPVectorReference) {
			IVPVectorReference ref = (IVPVectorReference) map.get(variableID);
			IVPValue value = (IVPValue) ((DataObject)map.get(expressionID)).evaluate(c, map, factory);
			IVPValue copyOfValue = createCopy(value, c, map, factory);
			ref.setElementIntoVector(copyOfValue.getUniqueID(), c, map, factory);
		} else if (map.get(variableID) instanceof IVPMatrixReference) {
			IVPMatrixReference ref = (IVPMatrixReference) map.get(variableID);
			IVPValue value = (IVPValue) ((DataObject)map.get(expressionID)).evaluate(c, map, factory);
			IVPValue copyOfValue = createCopy(value, c, map, factory);
			ref.setElementIntoMatrix(copyOfValue.getUniqueID(), c, map, factory);
		}
		return null;
	}

	/*
	 * The copy is needed because of the following: String a, b, c; a = "1"; b =
	 * "2"; c = "3";
	 * 
	 * a = b; b = c; System.out.println(a); // results 2, not 3.
	 */
	private IVPValue createCopy(IVPValue value, Context c, HashMap map, DataFactory factory) {
		IVPValue copy = null;
		if (value instanceof IVPNumber) {
			copy = factory.createIVPNumber();
			if(value.getValueType().equals(IVPValue.INTEGER_TYPE)){
				c.addInt(copy.getUniqueID(), c.getInt(value.getUniqueID()));
			}else{
				c.addDouble(copy.getUniqueID(), c.getDouble(value.getUniqueID()));
			}
		} else if (value instanceof IVPBoolean) {
			copy = factory.createIVPNumber();
			c.addBoolean(copy.getUniqueID(), c.getBoolean(value.getUniqueID()));
		} else {
			copy = factory.createIVPString();
			c.addString(copy.getUniqueID(), new String(c.getString(value.getUniqueID())));
		}
		copy.setValueType(value.getValueType());
		map.put(copy.getUniqueID(), copy);

		return copy;
	}

	/**
	 * Set the left member of this attribution line. attribution line:= variable
	 * = expression
	 * 
	 * @param uniqueID
	 */
	public void setVariable(String uniqueID) {
		variableID = uniqueID;
	}

	/**
	 * Set the right member of this attribution line. attribution line:=
	 * variable = expression
	 * 
	 * @param uniqueID
	 */
	public void setExpression(String uniqueID) {
		expressionID = uniqueID;
	}

}
