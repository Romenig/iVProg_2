/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataObject;

public abstract class CodeComposite extends DataObject {

	protected Vector children;

	public CodeComposite(String name, String description) {
		super(name, description);
		children = new Vector();
	}

	/**
	 * Append a child at the end of the children list.
	 * 
	 * @param childID
	 */
	public void addChild(String childID) {
		children.add(childID);
	}

	/**
	 * Append a child at the at the specified position in the children list.
	 * 
	 * @param childID
	 * @param index
	 * @return lastChild
	 */
	public int addChildAtIndex(String childID, int index) {
		int lastIndex = -1;
		if (children.contains(childID)) {
			lastIndex = children.indexOf(childID);
			if (index >= lastIndex) {
				children.add(index, childID);
				children.remove(lastIndex);
			} else {
				children.remove(childID);
				children.add(index, childID);
			}
			return lastIndex;
		} else {
			children.add(index, childID);
			return lastIndex;
		}
	}

	/**
	 * Remove the given child of the children list.
	 * 
	 * @param childID
	 * @return
	 */
	public int removeChild(String childID) {
		String childRemoved = null;
		int index = 0;
		for (int i = 0; i < children.size(); i++) {
			if (childID.equals(children.get(i))) {
				childRemoved = childID;
				children.remove(i);
				index = i;
			}
		}
		return index;
	}

	/**
	 * Remove the child in the specified position of the children list.
	 * 
	 * @param index
	 * @return
	 */
	public String removeChildFromIndex(int index) {
		String lastChild = (String) children.remove(index);
		return lastChild;
	}
	
	public int moveChild(String child, int index) {
		int lastIndex = children.indexOf(child);
		if (index >= lastIndex) {
			children.add(index, child);
			if (lastIndex != -1)
				children.remove(lastIndex);
		} else {
			children.remove(child);
			children.add(index, child);
		}
		return lastIndex;
	}

	public abstract void updateParent(String lastExp, String newExp, String operationContext);

}
