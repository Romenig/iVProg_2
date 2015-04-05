/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.utils;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.model.utils.Services;

/**
 * @author Romenig
 * 
 */
public class IVPPrinter extends DataObject {

	private String printableID;

	/**
	 * @return the printableID
	 */
	public String getPrintableID() {
		return printableID;
	}

	/**
	 * @param printableID
	 *            the printableID to set
	 */
	public void setPrintableID(String printableID) {
		this.printableID = printableID;
	}

	/**
	 * @param name
	 * @param description
	 */
	public IVPPrinter() {
		super("IVPPrinter", "IVPPrinter class.");
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
		// por enquanto vou imprimir apenas um valor de variável... depois
		// coloco a impressão de matrizes e vetores
		IVPValue v = (IVPValue) ((DataObject) map.get(printableID)).evaluate(c, map, factory);
		String printableString = getString(v, c, map);
		try {
			Services.getService().getController().printMessage(printableString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param string
	 * @param c
	 * @return
	 */
	private String getString(IVPValue v, Context c, HashMap map) {
		if (v.getValueType().equals(Expression.INTEGER_TYPE)) {
			return c.getInt(v.getUniqueID()) + "";
		} else if (v.getValueType().equals(Expression.DOUBLE_TYPE)) {
			return c.getDouble(v.getUniqueID()) + "";
		} else if (v.getValueType().equals(Expression.STRING_TYPE)) {
			return c.getString(v.getUniqueID());
		} else if (v.getValueType().equals(Expression.INTEGER_TYPE)) {
			return c.getBoolean(v.getUniqueID()) + "";
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toXML()
	 */
	public String toXML() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toCString()
	 */
	public String toCString() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment
	 * .model.DomainObject)
	 */
	public boolean equals(DomainObject o) {
		return false;
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }

}
