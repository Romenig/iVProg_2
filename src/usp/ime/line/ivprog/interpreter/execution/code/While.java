/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class While extends CodeComposite {

	private String loopConditionID;

	/**
	 * @param name
	 * @param description
	 */
	public While() {
		super("While", "While object.");
	}

	/**
	 * @param uniqueID
	 */
	public void setLoopCondition(String uniqueID) {
		loopConditionID = uniqueID;
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
		IVPBoolean b = (IVPBoolean) ((DataObject) map.get(loopConditionID)).evaluate(c, map, factory);
		Function f = (Function) map.get(c.getFunctionID());
		
		System.out.println(((DataObject) map.get(loopConditionID)).evaluate(c, map, factory));
		
		while (c.getBoolean(b.getUniqueID())) {
			for (int i = 0; i < children.size(); i += 1) {
				DataObject component = (DataObject) map.get(children.get(i));
				if (component instanceof Return) {
					DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
					f.setFunctionReturnedElementID(returnedElement.getUniqueID());
					f.setReturning(true);
					return returnedElement;
				} else if (f.isReturning()) {
					return IVPValue.NULL;
				}
				component.evaluate(c, map, factory);
			}
			b = (IVPBoolean) ((DataObject) map.get(loopConditionID)).evaluate(c, map, factory);
		}
		return null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.code.CodeComposite#updateParent
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateParent(String lastExp, String newExp, String operationContext) {
		if(loopConditionID.equals(lastExp))
			loopConditionID = newExp;
	}

	public String getLoopConditionID() {
		return loopConditionID;
	}

	public void setLoopConditionID(String loopConditionID) {
		this.loopConditionID = loopConditionID;
	}

}
