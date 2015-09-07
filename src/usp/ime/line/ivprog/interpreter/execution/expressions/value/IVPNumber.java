/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution.expressions.value;

import ilm.framework.assignment.model.DomainObject;

import java.math.BigDecimal;
import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPNumber extends IVPValue {

	/**
	 * @param name
	 * @param description
	 */
	public IVPNumber() {
		super("IVPNumber", "IVPNumber object.");
	}

	/**
	 * Updates the integer value to the given value inside the given context.
	 * 
	 * @param context
	 * @param ivpNumber
	 */
	public void updateIntegerValue(Context context, int integer) {
		context.updateInt(getUniqueID(), integer);
	}

	/**
	 * Updates the double value to the given value inside the given context.
	 * 
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
	public IVPNumber add(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = (IVPNumber) map.get(resultID);
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			int resultInt = context.getInt(getUniqueID()) + context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		} else {
			double resultDouble = 0.0;
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultDouble = context.getDouble(getUniqueID()) + context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultDouble = context.getDouble(getUniqueID()) + context.getInt(number.getUniqueID());
				} else {
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
	public IVPNumber multiply(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = (IVPNumber) map.get(resultID);
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			int resultInt = context.getInt(getUniqueID()) * context.getInt(number.getUniqueID());
			context.addInt(result.getUniqueID(), resultInt);
			result.setValueType(IVPValue.INTEGER_TYPE);
		} else {
			double resultDouble = 0.0;
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultDouble = context.getDouble(getUniqueID()) * context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultDouble = context.getDouble(getUniqueID()) * context.getInt(number.getUniqueID());
				} else {
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
	public IVPNumber divide(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = (IVPNumber) map.get(resultID);
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			int resultInt = context.getInt(getUniqueID()) / context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		} else {
			double resultDouble = 0.0;
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultDouble = context.getDouble(getUniqueID()) / context.getDouble(number.getUniqueID());
				result.setValueType(IVPValue.DOUBLE_TYPE);
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultDouble = context.getDouble(getUniqueID()) / context.getInt(number.getUniqueID());
				} else {
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
	public IVPNumber subtract(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = (IVPNumber) map.get(resultID);
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			int resultInt = context.getInt(getUniqueID()) - context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		} else {
			double resultDouble = 0.0;
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultDouble = context.getDouble(getUniqueID()) - context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultDouble = context.getDouble(getUniqueID()) - context.getInt(number.getUniqueID());
				} else {
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
	public IVPNumber remainder(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPNumber result = (IVPNumber) map.get(resultID);
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			int resultInt = context.getInt(getUniqueID()) % context.getInt(number.getUniqueID());
			result.setValueType(IVPValue.INTEGER_TYPE);
			context.addInt(result.getUniqueID(), resultInt);
		} else {
			double resultDouble = 0.0;
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultDouble = context.getDouble(getUniqueID()) % context.getDouble(number.getUniqueID());
				result.setValueType(IVPValue.DOUBLE_TYPE);
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultDouble = context.getDouble(getUniqueID()) % context.getInt(number.getUniqueID());
				} else {
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
	public IVPBoolean lessThan(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) < context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) < context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) < context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) < context.getDouble(number.getUniqueID());
				}
			}
		}
		context.addBoolean(result.getUniqueID(), resultBoolean);
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
	public IVPBoolean lessThanOrEqualTo(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) <= context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) <= context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) <= context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) <= context.getDouble(number.getUniqueID());
				}
			}
		}
		context.addBoolean(result.getUniqueID(), resultBoolean);
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
	public IVPBoolean greaterThan(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) > context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) > context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) > context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) > context.getDouble(number.getUniqueID());
				}
			}
		}
		if(context.addBoolean(result.getUniqueID(), resultBoolean) != null){
			context.updateBoolean(result.getUniqueID(), resultBoolean);
		}
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
	public IVPBoolean greaterThanOrEqualTo(String resultID, IVPNumber number, Context context, DataFactory factory, HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) >= context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) >= context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) >= context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) >= context.getDouble(number.getUniqueID());
				}
			}
		}
		context.addBoolean(result.getUniqueID(), resultBoolean);
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

	public IVPBoolean ivpEqualTo(String resultID, IVPValue number, Context context, DataFactory factory,
			HashMap map) {
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) == context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) == context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) == context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) == context.getDouble(number.getUniqueID());
				}
			}
		}
		
		context.addBoolean(result.getUniqueID(), resultBoolean);
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

	public IVPBoolean ivpNotEqualTo(String resultID, IVPValue n, Context context, DataFactory factory, HashMap map) {
		IVPNumber number = (IVPNumber) n;
		IVPBoolean result = (IVPBoolean) map.get(resultID);
		boolean resultBoolean = false;
		if (getValueType().equals(IVPValue.INTEGER_TYPE) && number.getValueType().equals(IVPValue.INTEGER_TYPE)) {
			resultBoolean = context.getInt(getUniqueID()) != context.getInt(number.getUniqueID());
		} else {
			if (getValueType().equals(IVPValue.DOUBLE_TYPE) && number.getValueType().equals(IVPValue.DOUBLE_TYPE)) {
				resultBoolean = context.getDouble(getUniqueID()) != context.getDouble(number.getUniqueID());
			} else {
				if (getValueType().equals(IVPValue.DOUBLE_TYPE)) {
					resultBoolean = context.getDouble(getUniqueID()) != context.getInt(number.getUniqueID());
				} else {
					resultBoolean = context.getInt(getUniqueID()) != context.getDouble(number.getUniqueID());
				}
			}
		}
		context.addBoolean(result.getUniqueID(), resultBoolean);
		return result;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment
	 * .model.DomainObject)
	 */
	public boolean equals(DomainObject o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toXML()
	 */
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see usp.ime.line.ivprog.interpreter.DataObject#toCString()
	 */
	public String toCString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see usp.ime.line.ivprog.interpreter.DataObject#updateParent(java.lang.String, java.lang.String, java.lang.String)
	 */
    public void updateParent(String lastExp, String newExp, String operationContext) {
	    // TODO Auto-generated method stub
	    
    }
    
    public Object clone(){
    	IVPNumber n = new IVPNumber();
    	n.setParentID(getParentID());
    	n.setScopeID(getScopeID());
    	n.setUniqueID(getUniqueID());
    	n.setExpressionType(getExpressionType());
    	n.setValueType(getValueType());
    	return n;
    }

}
