/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.execution;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.language.Messages;

public class Context implements Cloneable {

	private String functionID;
	private HashMap integerMap;
	private HashMap doubleMap;
	private HashMap stringMap;
	private HashMap booleanMap;

	public Context() {
		integerMap = new HashMap();
		doubleMap = new HashMap();
		stringMap = new HashMap();
		booleanMap = new HashMap();
	}

	private Context(HashMap intMap, HashMap dMap, HashMap sMap, HashMap bMap) {
		integerMap = intMap;
		doubleMap = dMap;
		stringMap = sMap;
		booleanMap = bMap;
	}

	//DOUBLE 
	/**
	 * Add a double to the hashmap of doubleValues.
	 * 
	 * @param key
	 * @param doubleValue
	 * @return IVPError
	 */
	public IVPError addDouble(String key, double doubleValue) {
		IVPError e = null;
		if (!doubleMap.containsKey(key)) {
			doubleMap.put(key, doubleValue);
		} else {
			e = new IVPError(Messages.getString("Context.keyExists"));
		}

		return e;
	}

	/**
	 * Get a doubleValue from the hashmap of doubleValues.
	 * 
	 * @param key
	 * @return IVPError
	 */
	public double getDouble(String key) {
		return (double) doubleMap.get(key);
	}

	/**
	 * Update the doubleValue with the specified key.
	 * 
	 * @param key
	 * @param doubleValue
	 * @return IVPError
	 */
	public IVPError updateDouble(String key, double doubleValue) {
		IVPError e = null;
		if (doubleMap.containsKey(key)) {
			doubleMap.put(key, doubleValue);
		} else {
			e = new IVPError(Messages.getString("Context.keyDoesNotExists"));
		}
		return e;
	}
	//---------------- INTEGER
	/**
	 * Add an int to the hashmap of integers.
	 * 
	 * @param key
	 * @param integer
	 * @return IVPError
	 */
	public IVPError addInt(String key, int integer) {
		IVPError e = null;
		if (!integerMap.containsKey(key)) {
			integerMap.put(key, integer);
		} else {
			e = new IVPError(Messages.getString("Context.keyExists"));
		}

		return e;
	}

	/**
	 * Get an integer from the hashmap of integers.
	 * 
	 * @param key
	 * @return IVPError
	 */
	public int getInt(String key) {
		return (int) integerMap.get(key);
	}

	/**
	 * Update the integer with the specified key.
	 * 
	 * @param key
	 * @param integer
	 * @return IVPError
	 */
	public IVPError updateInt(String key, int integer) {
		IVPError e = null;
		if (integerMap.containsKey(key)) {
			integerMap.put(key, integer);
		} else {
			e = new IVPError(Messages.getString("Context.keyDoesNotExists"));
		}
		return e;
	}
	
	
	/**
	 * Add a string object to the hashmap of strings.
	 * 
	 * @param key
	 * @param string
	 * @return IVPError
	 */
	public IVPError addString(String key, String string) {
		IVPError e = null;
		if (!stringMap.containsKey(key)) {
			stringMap.put(key, string);
		} else {
			e = new IVPError(Messages.getString("Context.keyExists"));
		}
		return e;
	}

	/**
	 * Get a String from the hashmap of strings.
	 * 
	 * @param key
	 * @return IVPError
	 */
	public String getString(String key) {
		return (String) stringMap.get(key);
	}

	/**
	 * Update the string with the specified key.
	 * 
	 * @param key
	 * @param string
	 * @return IVPError
	 */
	public IVPError updateString(String key, String string) {
		IVPError e = null;
		if (stringMap.containsKey(key)) {
			stringMap.put(key, string);
		} else {
			e = new IVPError(Messages.getString("Context.keyDoesNotExists"));
		}
		return e;
	}

	/**
	 * Add a boolean to the hashmap of booleans.
	 * 
	 * @param key
	 * @param b
	 * @return IVPError
	 */
	public IVPError addBoolean(String key, boolean b) {
		IVPError e = null;
		if (!booleanMap.containsKey(key)) {
			booleanMap.put(key, b);
		} else {
			e = new IVPError(Messages.getString("Context.keyExists"));
		}
		return e;
	}

	/**
	 * Get a boolean from the hashmap of booleans.
	 * 
	 * @param key
	 * @return IVPError
	 */
	public boolean getBoolean(String key) {
		return (boolean) booleanMap.get(key);
	}

	/**
	 * Update the boolean with the specified key.
	 * 
	 * @param key
	 * @param b
	 * @return IVPError
	 */
	public IVPError updateBoolean(String key, boolean b) {
		IVPError e = null;
		if (booleanMap.containsKey(key)) {
			booleanMap.put(key, b);
		} else {
			e = new IVPError(Messages.getString("Context.keyDoesNotExists"));
		}
		return e;
	}

	/**
	 * The clone method will be used only during a recursive call.
	 */
	public Object clone() {
		Context c = new Context((HashMap) integerMap.clone(),(HashMap) doubleMap.clone(), (HashMap) stringMap.clone(),
		        (HashMap) booleanMap.clone());
		return c;
	}

	/**
	 * @return the functionID
	 */
    public String getFunctionID() {
	    return functionID;
    }

	/**
	 * @param functionID the functionID to set
	 */
    public void setFunctionID(String functionID) {
	    this.functionID = functionID;
    }

}
