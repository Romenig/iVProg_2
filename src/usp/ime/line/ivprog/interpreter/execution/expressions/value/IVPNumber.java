/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPNumber extends IVPValue {

	/**
	 * Updates the integer value to the given value inside the given context.
	 * @param context
	 * @param ivpNumber
	 */
	public void updateIntegerValue(Context context, int integer) {
		context.updateInt(getUniqueID(), integer);
	}
	
	/**
	 * Updates the double value to the given value inside the given context.
	 * @param context
	 * @param ivpNumber
	 */
	public void updateDoubleValue(Context context, double doubleValue) {
		context.updateDouble(getUniqueID(), doubleValue);
	}

	/**
	 * Return the addition of this IVPNumber to the number given as parameter.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPNumber add(IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)){
			int resultInt = context.getInt(getUniqueID()) + context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		}else{
			double resultDouble = 0.0;
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultDouble = context.getDouble(getUniqueID()) + context.getDouble(number.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultDouble = context.getDouble(getUniqueID()) + context.getInt(number.getUniqueID());
				}else{
					resultDouble = context.getInt(getUniqueID()) + context.getDouble(number.getUniqueID());
				}
			}
			context.addDouble(result.getUniqueID(), resultDouble);
			result.setValueType(IVPValue.DOUBLE_TYPE);
		}
		return result;
	}

	/**
	 * Return the multiplication of this IVPNumber to the given parameter.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPNumber multiply(IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)){
			int resultInt = context.getInt(getUniqueID()) * context.getInt(number.getUniqueID());
			context.addInt(result.getUniqueID(), resultInt);
			result.setValueType(IVPValue.INTEGER_TYPE);
		}else{
			double resultDouble = 0.0;
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultDouble = context.getDouble(getUniqueID()) * context.getDouble(number.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultDouble = context.getDouble(getUniqueID()) * context.getInt(number.getUniqueID());
				}else{
					resultDouble = context.getInt(getUniqueID()) * context.getDouble(number.getUniqueID());
				}
			}
			result.setValueType(IVPValue.DOUBLE_TYPE);
			context.addDouble(result.getUniqueID(), resultDouble);
		}
		return result;
	}

	/**
	 * Return the division of this IVPNumber to the given parameter.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPNumber divide(IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)){
			int resultInt = context.getInt(getUniqueID()) / context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		}else{
			double resultDouble = 0.0;
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultDouble = context.getDouble(getUniqueID()) / context.getDouble(number.getUniqueID());
				result.setValueType(IVPValue.DOUBLE_TYPE);
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultDouble = context.getDouble(getUniqueID()) / context.getInt(number.getUniqueID());
				}else{
					resultDouble = context.getInt(getUniqueID()) / context.getDouble(number.getUniqueID());
				}
			}
			result.setValueType(IVPValue.DOUBLE_TYPE);
			context.addDouble(result.getUniqueID(), resultDouble);
		}
		return result;
	}

	/**
	 * Return the subtraction of this IVPNumber to the given parameter.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPNumber subtract(IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)){
			int resultInt = context.getInt(getUniqueID()) - context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		}else{
			double resultDouble = 0.0;
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultDouble = context.getDouble(getUniqueID()) - context.getDouble(number.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultDouble = context.getDouble(getUniqueID()) - context.getInt(number.getUniqueID());
				}else{
					resultDouble = context.getInt(getUniqueID()) - context.getDouble(number.getUniqueID());
				}
			}
			context.addDouble(result.getUniqueID(), resultDouble);
			result.setValueType(IVPValue.DOUBLE_TYPE);
		}
		return result;
	}

	/**
	 * Return the rest of this IVPNumber divided by the given parameter.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPNumber remainder(IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)){
			int resultInt = context.getInt(getUniqueID()) % context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		}else{
			double resultDouble = 0.0;
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultDouble = context.getDouble(getUniqueID()) % context.getDouble(number.getUniqueID());
				result.setValueType(IVPValue.DOUBLE_TYPE);
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultDouble = context.getDouble(getUniqueID()) % context.getInt(number.getUniqueID());
				}else{
					resultDouble = context.getInt(getUniqueID()) % context.getDouble(number.getUniqueID());
				}
			}
			result.setValueType(IVPValue.DOUBLE_TYPE);
			context.addDouble(result.getUniqueID(), resultDouble);
		}
		return result;
	}

	/**
	 * Verify if this object is less than the given IVPNumber num. This method
	 * returns an IVPBoolean with true or false.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPBoolean lessThan(IVPNumber num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) < c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) < c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) < c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) < c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

	/**
	 * Verify if this object is less than or equalt to the given IVPNumber num.
	 * This method returns an IVPBoolean with true or false.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPBoolean lessThanOrEqualTo(IVPNumber num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) <= c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) <= c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) <= c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) <= c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

	/**
	 * Verify if this object is greater than the given IVPNumber num. This
	 * method returns an IVPBoolean with true or false.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPBoolean greaterThan(IVPNumber num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) > c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) > c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) > c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) > c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

	/**
	 * Verify if this object is greater than or equal to the given IVPNumber
	 * num. This method returns an IVPBoolean with true or false.
	 * 
	 * @param ivpNumber
	 * @param context
	 * @param factory
	 * @return
	 */
	public IVPBoolean greaterThanOrEqualTo(IVPNumber num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) >= c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) >= c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) >= c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) >= c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue#
	 * ivpEqualTo
	 * (usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue,
	 * usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	
	public IVPBoolean ivpEqualTo(IVPValue num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) == c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) == c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) == c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) == c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue#
	 * ivpNotEqualTo
	 * (usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue,
	 * usp.ime.line.ivprog.interpreter.execution.Context, java.util.HashMap,
	 * usp.ime.line.ivprog.interpreter.DataFactory)
	 */
	
	public IVPBoolean ivpNotEqualTo(IVPValue num, Context c, HashMap map, DataFactory factory) {
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		boolean resultBoolean = false;
		if(getValueType().equals(IVPValue.INTEGER_TYPE) && num.getValueType().equals(IVPValue.INTEGER_TYPE)){
			resultBoolean = c.getInt(getUniqueID()) != c.getInt(num.getUniqueID());
		}else{
			if(getValueType().equals(IVPValue.DOUBLE_TYPE) && num.getValueType().equals(IVPValue.DOUBLE_TYPE)){
				resultBoolean = c.getDouble(getUniqueID()) != c.getDouble(num.getUniqueID());
			}else{
				if(getValueType().equals(IVPValue.DOUBLE_TYPE)){
					resultBoolean = c.getDouble(getUniqueID()) != c.getInt(num.getUniqueID());
				}else{
					resultBoolean = c.getInt(getUniqueID()) != c.getDouble(num.getUniqueID());
				}
			}
		}
		c.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}

}
