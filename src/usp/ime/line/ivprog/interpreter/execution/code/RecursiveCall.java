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
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class RecursiveCall extends DataObject {
	
	private String functionID;
	private Vector argumentsTypes;
	private Vector arguments;
	
	public RecursiveCall(){
		argumentsTypes = new Vector();
		arguments = new Vector();
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap, usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		Context updated = (Context) c.clone();
		updated.setFunctionID(c.getFunctionID());
		Function function = (Function) map.get(functionID);
		for(int i = 0; i < arguments.size(); i++){
			IVPValue value = (IVPValue) ((DataObject)map.get(arguments.get(i))).evaluate(updated, map, factory);
			IVPValue argument = (IVPValue) map.get(function.getArgument(i));
			if(argument.getValueType().equals(IVPValue.INTEGER_TYPE) ){
				((IVPNumber)argument).updateIntegerValue(updated, updated.getInt(value.getUniqueID()));
			}else if(argument.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				((IVPNumber)argument).updateDoubleValue(updated, updated.getDouble(value.getUniqueID()));
			}
			map.put(value.getUniqueID(), value);
		}
		IVPValue returningValue = (IVPValue) ((DataObject)map.get(functionID)).evaluate(updated, map, factory);
		if(returningValue.getValueType().equals(IVPValue.INTEGER_TYPE)){
			c.addInt(returningValue.getUniqueID(), updated.getInt(returningValue.getUniqueID()));
		}else if(returningValue.equals(IVPValue.DOUBLE_TYPE)){
			c.addDouble(returningValue.getUniqueID(), updated.getDouble(returningValue.getUniqueID()));
		}
		return returningValue;
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

    public String addParameter(int position, String parameter){
    	String lastParameter = IVPValue.NULL;
    	if(arguments.size() >= position && position != 0){
    		lastParameter = (String) arguments.get(position); 
    	}
    	if(arguments.size() <= position && position != 0){
    		arguments.add(position, parameter);	
    	}else if(position == 0){
    		arguments.add(parameter);
    	}
    	return lastParameter;
    }
}
