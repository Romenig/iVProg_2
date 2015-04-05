/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
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

public class StringLength extends Operation {

	private String string;

	/**
	 * @param name
	 * @param description
	 */
	public StringLength() {
		super("StringLength", "StringLength object.");
	}

	/**
	 * Set the string.
	 * 
	 * @param str
	 */
	public void setString(String str) {
		string = str;
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		IVPString str1 = (IVPString) ((DataObject) map.get(string)).evaluate(c, map, factory);
		IVPNumber result = str1.strlen(c, factory);
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
