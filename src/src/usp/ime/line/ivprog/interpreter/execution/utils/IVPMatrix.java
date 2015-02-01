/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class IVPMatrix extends DataObject {

	private String nColID;
	private String nLinID;
	private String primitiveTypeID;
	private String[][] matrixRepresentation;

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
	public void setSize(String nCol, String nLin, Context c) {
		this.nColID = nCol;
		this.nLinID = nLin;
		int col = c.getInt(nCol);
		int lin = c.getInt(nLin);
		matrixRepresentation = new String[lin][col];
		for (int i = 0; i < lin; i++) {
			for (int j = 0; j < col; j++) {
				matrixRepresentation[i][j] = IVPValue.NULL;
			}
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
	 * If there is no element in the vector, it returns IVPBoolean false.
	 * Returns IVPBoolean true otherwise.
	 * 
	 * @return
	 */
	public String isEmpty(DataFactory factory, Context c, HashMap map) {
		IVPBoolean isEmpty = factory.createIVPBoolean();
		boolean test = true;
		int col = c.getInt(nColID);
		int lin = c.getInt(nLinID);
		for (int i = 0; i < lin; i++) {
			for (int j = 0; j < col; j++) {
				if (matrixRepresentation[i][j] != IVPValue.NULL) {
					test = false;
					break;
				}
			}
		}
		c.addBoolean(isEmpty.getUniqueID(), new Boolean(test));
		return isEmpty.getUniqueID();
	}

	/**
	 * Add element to the specified position.
	 * 
	 * @return the last element on that place
	 */
	public Object addElement(String lin, String col, Context context, String elementID) {
		int line = context.getInt(lin);
		int column = context.getInt(col);
		String lastElement = matrixRepresentation[line][column];
		matrixRepresentation[line][column] = elementID;
		return lastElement;
	}

	/**
	 * Get the element in the specified position.
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public Object getElement(String lin, String col, Context context) {
		int line = context.getInt(lin);
		int column = context.getInt(col);
		return matrixRepresentation[line][column];
	}

	/**
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public String removeElement(String lin, String col, Context context) {
		int line = context.getInt(lin);
		int column = context.getInt(col);
		String removed = matrixRepresentation[line][column];
		matrixRepresentation[line][column] = IVPValue.NULL;
		return removed;
	}

	/**
	 * Get the number of lines of this matrix.
	 * 
	 * @return the nColID
	 */
	public String getNColID() {
		return nColID;
	}

	/**
	 * Get the number of lines of this matrix.
	 * 
	 * @return the nLinID
	 */
	public String getNLinID() {
		return nLinID;
	}

}
