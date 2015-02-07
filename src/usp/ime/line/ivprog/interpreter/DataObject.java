/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.execution.Context;

public abstract class DataObject extends DomainObject{

	/**
	 * This method is inheritance from LPS. If you are going to use the interpreter separately, you can remove this method.
	 * @param name
	 * @param description
	 */
    public DataObject(String name, String description) {
	    super(name, description);
    }

	private String uniqueID;

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public abstract Object evaluate(Context c, HashMap map, DataFactory factory);

}
