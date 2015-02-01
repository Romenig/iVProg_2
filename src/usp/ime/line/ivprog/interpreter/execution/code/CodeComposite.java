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
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataObject;

public abstract class CodeComposite extends DataObject {

	protected Vector children;

	public CodeComposite() {
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
	 * @param index
	 * @param childID
	 * @return lastChild
	 */
	public String addChildAtIndex(BigDecimal index, String childID) {
		String lastChild = (String) children.remove(index.intValue());
		children.add(index.intValue(), childID);
		return lastChild;
	}

	/**
	 * Remove the given child of the children list.
	 * 
	 * @param childID
	 * @return
	 */
	public String removeChild(String childID) {
		String childRemoved = null;
		for (int i = 0; i < children.size(); i++) {
			if (childID.equals(children.get(i))) {
				childRemoved = childID;
				children.remove(i);
			}
		}
		return childRemoved;
	}

	/**
	 * Remove the child in the specified position of the children list.
	 * 
	 * @param index
	 * @return
	 */
	public String removeChildAtIndex(BigDecimal index) {
		String lastChild = (String) children.remove(index.intValue());
		return lastChild;
	}

}
