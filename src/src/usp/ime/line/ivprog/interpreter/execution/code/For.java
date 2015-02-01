/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.code;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class For extends CodeComposite {

	public static String FOR_COPLETE = "for_complete";
	public static String FOR_N_TIMES = "for_n_times";
	public static String FOR_N_TIMES_WITH_INDEX = "for_n_times_with_increment";
	private String executionMethod;
	private String upperBound;
	private String lowerBound;
	private String index;
	private String increment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.DataObject#evaluate(usp.ime.line.ivprog
	 * .interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		IVPValue upperValue = null;
		IVPValue lowerValue = null;
		IVPVariable indexVariable = null;
		IVPValue incrementValue = null;
		Function f = (Function) map.get(c.getFunctionID());
		if (executionMethod.equals(FOR_N_TIMES)) {
			upperValue = (IVPValue) ((DataObject)map.get(upperBound)).evaluate(c, map, factory);
			int upperInt = c.getInt(upperValue.getUniqueID());
			for (int i = 0; i < upperInt; i++) {
				for (int j = 0; j < children.size(); j++) {
					DataObject component = (DataObject) map.get(children.get(j));
					if(component instanceof Return){
						DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
						f.setFunctionReturnedElementID(returnedElement.getUniqueID());
						f.setReturning(true);
						return returnedElement;
					}else if(f.isReturning()){
						return IVPValue.NULL;
					}
					component.evaluate(c, map, factory);
				}
			}
		} else if (executionMethod.equals(FOR_N_TIMES_WITH_INDEX)) {
			upperValue = (IVPValue) ((DataObject)map.get(upperBound)).evaluate(c, map, factory);
			indexVariable = (IVPVariable) map.get(index);
			IVPNumber indexValue = (IVPNumber) ((DataObject)map.get(indexVariable.getValueID())).evaluate(c, map, factory);
			int upperInt = c.getInt(upperValue.getUniqueID());
			for (int i = 0; i < upperInt; i++) {
				for (int j = 0; j < children.size(); j++) {
					DataObject component = (DataObject) map.get(children.get(j));
					if(component instanceof Return){
						DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
						f.setFunctionReturnedElementID(returnedElement.getUniqueID());
						f.setReturning(true);
						return returnedElement;
					}else if(f.isReturning()){
						return IVPValue.NULL;
					}
					component.evaluate(c, map, factory);
				}
				indexValue.updateIntegerValue(c, c.getInt(indexValue.getUniqueID())+1);
			}
		} else { // COMPLETE
			upperValue = (IVPValue) ((DataObject)map.get(upperBound)).evaluate(c, map, factory);
			lowerValue = (IVPValue) ((DataObject)map.get(lowerBound)).evaluate(c, map, factory);
			incrementValue = (IVPValue) ((DataObject)map.get(incrementValue)).evaluate(c, map, factory);
			indexVariable = (IVPVariable) map.get(index);
			IVPNumber indexValue = (IVPNumber) ((DataObject)map.get(indexVariable.getValueID())).evaluate(c, map, factory);
			int upperInt = c.getInt(upperValue.getUniqueID());
			int lowerInt = c.getInt(lowerValue.getUniqueID());
			int incrementInt = c.getInt(incrementValue.getUniqueID());
			for (int i = lowerInt; i < upperInt; i += incrementInt) {
				for (int j = 0; j < children.size(); j++) {
					DataObject component = (DataObject) map.get(children.get(j));
					if(component instanceof Return){
						DataObject returnedElement = (DataObject) component.evaluate(c, map, factory);
						f.setFunctionReturnedElementID(returnedElement.getUniqueID());
						f.setReturning(true);
						return returnedElement;
					}else if(f.isReturning()){
						return IVPValue.NULL;
					}
					component.evaluate(c, map, factory);
				}
				indexValue.updateIntegerValue(c, c.getInt(indexValue.getUniqueID())+1);
				incrementValue = (IVPValue) ((DataObject)map.get(incrementValue)).evaluate(c, map, factory);
				incrementInt = c.getInt(incrementValue.getUniqueID());
			}
		}
		return null;
	}

	/**
	 * @return the executionMethod
	 */
	public String getExecutionMethod() {
		return executionMethod;
	}

	/**
	 * @param executionMethod
	 *            the executionMethod to set
	 */
	public void setExecutionMethod(String executionMethod) {
		this.executionMethod = executionMethod;
	}

	/**
	 * @return the upperBound
	 */
	public String getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * @return the lowerBound
	 */
	public String getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the increment
	 */
	public String getIncrement() {
		return increment;
	}

	/**
	 * @param increment
	 *            the increment to set
	 */
	public void setIncrement(String increment) {
		this.increment = increment;
	}

}
