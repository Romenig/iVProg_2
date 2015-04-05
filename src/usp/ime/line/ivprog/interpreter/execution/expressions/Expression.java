/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions;

import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

public abstract class Expression extends DataObject {

	public static final String VARIABLE = "0";
	public static final String OPERATION_ADDITION = "1";
	public static final String OPERATION_SUBTRACTION = "2";
	public static final String OPERATION_DIVISION = "3";
	public static final String OPERATION_MULTIPLICATION = "4";
	public static final String OPERATION_AND = "5";
	public static final String OPERATION_OR = "6";
	public static final String OPERATION_LEQ = "7";
	public static final String OPERATION_LES = "8";
	public static final String OPERATION_EQU = "9";
	public static final String OPERATION_NEQ = "10";
	public static final String OPERATION_GEQ = "11";
	public static final String OPERATION_GRE = "12";
	public static final String INTEGER_TYPE = "int";
	public static final String DOUBLE_TYPE = "double";
	public static final String STRING_TYPE = "String";
	public static final String BOOLEAN_TYPE = "boolean";
	public static final String NULL = "null";
	public static final String OPERATION_CONCAT = "17";
	public static final String OPERATION_INTDIV = "18";

	public static final String DEFAULT_INTEGER = "1";
	public static final String DEFAULT_DOUBLE = "1.0";
	public static final String DEFAULT_STRING = ResourceBundleIVP.getString("helloWorld.text");
	public static final String DEFAULT_BOOLEAN = "true";

	protected String expressionType = "-1";

	/**
	 * @param name
	 * @param description
	 */
	public Expression(String name, String description) {
		super(name, description);
	}

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String type) {
		expressionType = type;
	}

}
