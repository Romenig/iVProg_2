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

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class IVPMatrixReference extends DataObject {

	private String matrixID;
	private String linID;
	private String colID;

	/**
	 * @param name
	 * @param description
	 */
	public IVPMatrixReference() {
		super("IVPMatrixReference", "IVPMatrixReference object.");
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
		IVPValue value = (IVPValue) map.get(((IVPMatrix) map.get(matrixID)).getElement(linID, colID, c));
		return value;
	}

	/**
	 * @return the vectorID
	 */
	public String getMatrixID() {
		return matrixID;
	}

	/**
	 * @param vectorID
	 *            the vectorID to set
	 */
	public void setMatrixID(String vectorID) {
		this.matrixID = vectorID;
	}

	public IVPValue getElementFromMatrix(Context c, HashMap map, DataFactory factory) {
		IVPValue value = (IVPValue) map.get(((IVPMatrix) map.get(matrixID)).getElement(linID, colID, c));
		return value;
	}

	public Object setElementIntoMatrix(String valueID, Context c, HashMap map, DataFactory factory) {
		IVPMatrix m = (IVPMatrix) map.get(matrixID);
		return m.addElement(linID, colID, c, valueID);
	}

	/**
	 * @return the linID
	 */
	public String getLinID() {
		return linID;
	}

	/**
	 * @param linID
	 *            the linID to set
	 */
	public void setLinID(String linID) {
		this.linID = linID;
	}

	/**
	 * @return the colID
	 */
	public String getColID() {
		return colID;
	}

	/**
	 * @param colID
	 *            the colID to set
	 */
	public void setColID(String colID) {
		this.colID = colID;
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
