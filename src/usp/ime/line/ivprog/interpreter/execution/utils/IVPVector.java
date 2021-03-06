/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.utils;

import ilm.framework.assignment.model.DomainObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class IVPVector extends DataObject {

	private String sizeID;
	private String primitiveTypeID;
	private String[] vectorRepresentation;

	/**
	 * @param name
	 * @param description
	 */
	public IVPVector() {
		super("IVPVector", "IVPVector object.");
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
		return this;
	}

	/**
	 * Set the IVPVector size.
	 * 
	 * @param sizeID
	 * @param c
	 */
	public void setSize(String sizeID, Context c) {
		this.sizeID = sizeID;
		vectorRepresentation = new String[c.getInt(sizeID)];
		for (int i = 0; i < c.getInt(sizeID); i++) {
			vectorRepresentation[i] = IVPValue.NULL;
		}
	}

	/**
	 * Set the primitive type.
	 * 
	 * @param integerType
	 */
	public void setType(String t) {
		primitiveTypeID = t;
	}

	/**
	 * Get the primitive type.
	 * 
	 * @return
	 */
	public Object getType() {
		return primitiveTypeID;
	}

	/**
	 * Get the IVPVector sizeID.
	 * 
	 * @return
	 */
	public String getSize() {
		return sizeID;
	}

	/**
	 * If there is no element in the vector, it returns IVPBoolean false.
	 * Returns IVPBoolean true otherwise.
	 * 
	 * @return
	 */
	public String isEmpty(DataFactory factory, Context c, HashMap map) {
		IVPBoolean isEmpty = factory.createIVPBoolean();
		boolean test = true;
		for (int i = 0; i < vectorRepresentation.length; i += 1) {
			if (vectorRepresentation[i] != IVPValue.NULL) {
				test = false;
				break;
			}
		}
		c.addBoolean(isEmpty.getUniqueID(), new Boolean(test));
		return isEmpty.getUniqueID();
	}

	/**
	 * Add element to the specified position.
	 * 
	 * @param i
	 * @param uniqueID
	 */
	public Object add(int i, String uniqueID) {
		String lastElement = vectorRepresentation[i];
		vectorRepresentation[i] = uniqueID;
		return lastElement;
	}

	/**
	 * Get the element in the specified position.
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public Object get(String uniqueID, Context context) {
		int index = context.getInt(uniqueID);
		return vectorRepresentation[index];
	}

	/**
	 * @param uniqueID
	 * @param context
	 * @return
	 */
	public String remove(String uniqueID, Context context) {
		int index = context.getInt(uniqueID);
		String removed = vectorRepresentation[index];
		vectorRepresentation[index] = IVPValue.NULL;
		return removed;
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

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }

}
