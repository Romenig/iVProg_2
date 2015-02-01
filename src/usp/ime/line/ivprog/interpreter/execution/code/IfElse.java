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
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class IfElse extends CodeComposite {

	private String flowConditionID;
	private Vector elseChildren;

	public IfElse() {
		elseChildren = new Vector();
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
		IVPBoolean b = (IVPBoolean) ((DataObject)map.get(flowConditionID)).evaluate(c, map, factory);
		Function f = (Function) map.get(c.getFunctionID());
		if (c.getBoolean(b.getUniqueID())) {
			for (int i = 0; i < children.size(); i += 1) {
				DataObject component = (DataObject) map.get(children.get(i));
				if(component instanceof Return){
					DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
					f.setFunctionReturnedElementID(returnedElement.getUniqueID());
					f.setReturning(true);
					return returnedElement;
				}else if(f.isReturning()){
					return IVPValue.NULL;
				}
				component.evaluate(c, map, factory);
			}
		} else {
			for (int i = 0; i < elseChildren.size(); i += 1) {
				DataObject component = (DataObject) map.get(elseChildren.get(i));
				if(component instanceof Return){
					DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
					f.setFunctionReturnedElementID(returnedElement.getUniqueID());
					f.setReturning(true);
					return returnedElement;
				}else if(f.isReturning()){
					return IVPValue.NULL;
				}
				component.evaluate(c, map, factory);
			}
		}
		return null;
	}

	/**
	 * Sets the condition to this flow controller decide wich way to go.
	 * 
	 * @param uniqueID
	 */
	public void setFlowCondition(String uniqueID) {
		flowConditionID = uniqueID;
	}

	/**
	 * Append a child in the end of the 'if' children list.
	 * 
	 * @param uniqueID
	 */
	public void addIfChild(String uniqueID) {
		addChild(uniqueID);
	}

	/**
	 * Append a child in the end of the 'else' children list.
	 * 
	 * @param uniqueID
	 */
	public void addElseChild(String uniqueID) {
		elseChildren.add(uniqueID);
	}

	/**
	 * Add a child on the 'if' statement flow at the specified position.
	 * 
	 * @param uniqueID
	 */
	public String addIfChildAtIndex(BigDecimal index, String uniqueID) {
		return addChildAtIndex(index, uniqueID);
	}

	/**
	 * Append a child in the end of the list.
	 * 
	 * @param uniqueID
	 */
	public String addElseChildAtIndex(BigDecimal index, String uniqueID) {
		String lastChild = (String) children.remove(index.intValue());
		children.add(index.intValue(), uniqueID);
		return lastChild;
	}

	/**
	 * Remove an 'if' child at the specified position;
	 * 
	 * @param bigDecimal
	 */
	public String removeIfChildAtIndex(BigDecimal bigDecimal) {
		return removeChildAtIndex(bigDecimal);
	}

	/**
	 * Remove an 'else' child at the specified position;
	 * 
	 * @param bigDecimal
	 */
	public String removeElseChildAtIndex(BigDecimal index) {
		String lastChild = (String) elseChildren.remove(index.intValue());
		return lastChild;
	}

	/**
	 * Remove a given child of the 'if' statement flow.
	 * 
	 * @param bigDecimal
	 */
	public String removeIfChild(String childID) {
		return removeChild(childID);
	}

	/**
	 * Remove a given child of the 'else' statement flow.
	 * 
	 * @param bigDecimal
	 */
	public String removeElseChild(String childID) {
		String childRemoved = null;
		for (int i = 0; i < elseChildren.size(); i++) {
			if (childID.equals(elseChildren.get(i))) {
				childRemoved = childID;
				elseChildren.remove(i);
			}
		}
		return childRemoved;
	}

}
