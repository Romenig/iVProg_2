/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class FunctionReference extends DataObject {
	
	private String functionID;
	private Vector parameterTypes;
	private Vector arguments;
	
	public FunctionReference(){
		parameterTypes = new Vector();
		arguments = new Vector();
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap, usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		return ((DataObject)map.get(functionID)).evaluate(c, map, factory);
	}

	/**
	 * @return the functionID
	 */
    public String getFunctionID() {
	    return functionID;
    }

	/**
	 * @param functionID the functionID to set
	 */
    public void setFunctionID(String functionID) {
	    this.functionID = functionID;
    }
    
    public String setParameterType(int position, String parameterType){
    	String lastType = IVPValue.NULL;
    	if(parameterTypes.size() >= position){
    		lastType = (String) parameterTypes.get(position); 
    	}
    	parameterTypes.add(position, parameterType);
    	return lastType;
    }

    public String setParameter(int position, String parameter){
    	String lastParameter = IVPValue.NULL;
    	if(arguments.size() >= position){
    		lastParameter = (String) arguments.get(position); 
    	}
    	arguments.add(position, parameter);
    	return lastParameter;
    }
}
