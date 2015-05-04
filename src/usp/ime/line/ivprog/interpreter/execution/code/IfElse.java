/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import ilm.framework.assignment.model.DomainObject;

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
		super("IfElse", "IfElse object.");
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
		IVPBoolean b = (IVPBoolean) ((DataObject) map.get(flowConditionID)).evaluate(c, map, factory);
		Function f = (Function) map.get(c.getFunctionID());
		if (c.getBoolean(b.getUniqueID())) {
			for (int i = 0; i < children.size(); i += 1) {
				DataObject component = (DataObject) map.get(children.get(i));
				if (component instanceof Return) {
					DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
					f.setFunctionReturnedElementID(returnedElement.getUniqueID());
					f.setReturning(true);
					return returnedElement;
				} else if (f.isReturning()) {
					return IVPValue.NULL;
				}
				component.evaluate(c, map, factory);
			}
		} else {
			for (int i = 0; i < elseChildren.size(); i += 1) {
				DataObject component = (DataObject) map.get(elseChildren.get(i));
				if (component instanceof Return) {
					DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
					f.setFunctionReturnedElementID(returnedElement.getUniqueID());
					f.setReturning(true);
					return returnedElement;
				} else if (f.isReturning()) {
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
	public int addIfChildAtIndex(int index, String uniqueID) {
		return addChildAtIndex(uniqueID, index);
	}

	/**
	 * Append a child in the end of the list.
	 * 
	 * @param index
	 */
	public String addElseChildAtIndex(String childID, int index) {
		String lastChild = (String) children.remove(index);
		children.add(index, childID);
		return lastChild;
	}

	/**
	 * Remove an 'if' child at the specified position;
	 * 
	 * @param index
	 */
	public String removeIfChildAtIndex(int index) {
		return removeChildFromIndex(index);
	}

	/**
	 * Remove an 'else' child at the specified position;
	 * 
	 * @param bigDecimal
	 */
	public String removeElseChildAtIndex(int index) {
		String lastChild = (String) elseChildren.remove(index);
		return lastChild;
	}

	/**
	 * Remove a given child of the 'if' statement flow.
	 * 
	 * @param childID
	 */
	public int removeIfChild(String childID) {
		return removeChild(childID);
	}
	
	public int moveElseChild(String child, int index) {
		int lastIndex = elseChildren.indexOf(child);
		if (index >= lastIndex) {
			elseChildren.add(index, child);
			elseChildren.remove(lastIndex);
		} else {
			elseChildren.remove(child);
			elseChildren.add(index, child);
		}
		return lastIndex;
	}

	/**
	 * Remove a given child of the 'else' statement flow.
	 * 
	 * @param bigDecimal
	 */
	public int removeElseChild(String childID) {
		String childRemoved = null;
		int index = 0;
		for (int i = 0; i < elseChildren.size(); i++) {
			if (childID.equals(elseChildren.get(i))) {
				childRemoved = childID;
				elseChildren.remove(i);
				index = i;
			}
		}
		return index;
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
		// TODO Auto-generated method stub
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

	public void updateParent(String lastExp, String newExp, String operationContext) {
		if (flowConditionID.equals(lastExp)) {
			flowConditionID = newExp;
		}
	}

	/**
	 * @param childID
	 * @return
	 */
	public String getChildContext(String childID) {
		if (elseChildren.contains(childID)) {
			return "else";
		} else if (children.contains(childID)) {
			return "if";
		} else
			return "";
	}

	/**
	 * @param childID
	 * @param index
	 */
	public void addElseChildToIndex(String childID, int index) {
		elseChildren.add(index, childID);
	}
}
