/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;

/**
 * @author Romenig
 *
 */
public class IVPVariableReference extends DataObject{

	private String referencedID;
	private String referencedType;
	
	/**
	 * @param name
	 * @param description
	 */
    public IVPVariableReference() {
	    super("IVPVariableReference", "IVPVariableReference object.");
    }

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap, usp.ime.line.ivprog.interpreter.DataFactory)
	 */
    public Object evaluate(Context c, HashMap map, DataFactory factory) {
    	IVPVariable v = (IVPVariable) map.get(referencedID);
	    return v.evaluate(c, map, factory);
    }

	/* (non-Javadoc)
	 * @see ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment.model.DomainObject)
	 */
    public boolean equals(DomainObject o) {
	     if(getUniqueID().equals(((DataObject)o).getUniqueID())){
	    	return true;
	     }
	     return false;
    }
    
    /**
	 * @return the referencedID
	 */
	public String getReferencedID() {
		return referencedID;
	}

	/**
	 * @param referencedID the referencedID to set
	 */
	public void setReferencedID(String referencedID) {
		this.referencedID = referencedID;
	}

	/**
	 * Set the variable type.
	 * @param newType
	 */
    public void setReferencedType(String newType) {
	    referencedType = newType;
    }
    
    /**
     * Get the variable type.
     * @return
     */
    public String getReferencedType(){
    	return referencedType;
    }
}
