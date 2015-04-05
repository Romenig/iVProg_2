/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.strings;

import ilm.framework.assignment.model.DomainObject;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.Operation;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class Contains extends Operation {

	private String string;
	private String subString;

	/**
	 * @param name
	 * @param description
	 */
	public Contains() {
		super("Contains", "Contains object.");
	}

	/**
	 * Set the string.
	 * 
	 * @param str
	 */
	public void setString(String str) {
		string = str;
	}

	/**
	 * Set the substring you wish to search for.
	 * 
	 * @param sub
	 */
	public void setSubString(String sub) {
		subString = sub;
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		IVPString str1 = (IVPString) ((DataObject) map.get(string)).evaluate(c, map, factory);
		IVPString sub = (IVPString) ((DataObject) map.get(subString)).evaluate(c, map, factory);
		IVPNumber result = str1.searchSubstring(sub, c, factory);
		return result;
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

}
