/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.model.utils.Services;

/**
 * @author Romenig
 * 
 */
public class IVPVariableReference extends Expression {

	private String referencedID = "";
	private String referencedType;
	private String referencedName;

	/**
	 * @param name
	 * @param description
	 */
	public IVPVariableReference() {
		super("IVPVariableReference", "IVPVariableReference object.");
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
		IVPVariable v = (IVPVariable) map.get(referencedID);
		return v.evaluate(c, map, factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment
	 * .model.DomainObject)
	 */
	public boolean equals(DomainObject o) {
		if (getUniqueID().equals(((DataObject) o).getUniqueID())) {
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
	 * @param referencedID
	 *            the referencedID to set
	 */
	public void setReferencedID(String referencedID) {
		this.referencedID = referencedID;
		if (referencedID != null && !"".equals(referencedID)) {
			IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(referencedID);
			setReferencedName(var.getVariableName());
			setReferencedType(var.getVariableType());
		} else {
			setReferencedName("");
			setReferencedType("-1");
		}
	}

	/**
	 * Set the variable type.
	 * 
	 * @param newType
	 */
	public void setReferencedType(String newType) {
		referencedType = newType;
	}

	/**
	 * Get the variable type.
	 * 
	 * @return
	 */
	public String getReferencedType() {
		return referencedType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toXML()
	 */
	@Override
	public String toXML() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toCString()
	 */
	@Override
	public String toCString() {
		return null;
	}

	/**
	 * @return the referencedName
	 */
	public String getReferencedName() {
		return referencedName;
	}

	/**
	 * @param referencedName
	 *            the referencedName to set
	 */
	public void setReferencedName(String referencedName) {
		this.referencedName = referencedName;
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }

}
