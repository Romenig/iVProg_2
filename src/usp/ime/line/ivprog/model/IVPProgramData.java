package usp.ime.line.ivprog.model;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.Function;

public class IVPProgramData extends DomainObject {
	
	private HashMap objectMap;
	private Context context;
	private Function mainFunction;
	private List variableListeners;
	private List functionListeners;
	private List expressionListeners;
	private List operationListeners;
	private HashMap codeListeners;
	private HashMap modelHash;
	private HashMap contextMap;

	public IVPProgramData() {
		super("IVProgramData","All the instance data.");
		variableListeners = new Vector();
		functionListeners = new Vector();
		expressionListeners = new Vector();
		operationListeners = new Vector();
		codeListeners = new HashMap();
		modelHash = new HashMap();
		contextMap = new HashMap();
	}

	/* (non-Javadoc)
	 * @see ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment.model.DomainObject)
	 */
    public boolean equals(DomainObject o) {
	    return false;
    }

	/**
	 * @return the modelHash
	 */
    public HashMap getModelMapping() {
	    return modelHash;
    }

	/**
	 * @param modelHash the modelHash to set
	 */
    public void setModelHash(HashMap modelHash) {
	    this.modelHash = modelHash;
    }

	/**
	 * @return the codeListeners
	 */
    public HashMap getCodeListeners() {
	    return codeListeners;
    }

	/**
	 * @param codeListeners the codeListeners to set
	 */
    public void setCodeListeners(HashMap codeListeners) {
	    this.codeListeners = codeListeners;
    }

	/**
	 * @return the operationListeners
	 */
    public List getOperationListeners() {
	    return operationListeners;
    }

	/**
	 * @param operationListeners the operationListeners to set
	 */
    public void setOperationListeners(List operationListeners) {
	    this.operationListeners = operationListeners;
    }

	/**
	 * @return the expressionListeners
	 */
    public List getExpressionListeners() {
	    return expressionListeners;
    }

	/**
	 * @param expressionListeners the expressionListeners to set
	 */
    public void setExpressionListeners(List expressionListeners) {
	    this.expressionListeners = expressionListeners;
    }

	/**
	 * @return the functionListeners
	 */
    public List getFunctionListeners() {
	    return functionListeners;
    }

	/**
	 * @param functionListeners the functionListeners to set
	 */
    public void setFunctionListeners(List functionListeners) {
	    this.functionListeners = functionListeners;
    }

	/**
	 * @return the variableListeners
	 */
    public List getVariableListeners() {
	    return variableListeners;
    }

	/**
	 * @param variableListeners the variableListeners to set
	 */
    public void setVariableListeners(List variableListeners) {
	    this.variableListeners = variableListeners;
    }

	/**
	 * @return the mainFunction
	 */
    public Function getMainFunction() {
	    return mainFunction;
    }

	/**
	 * @param mainFunction the mainFunction to set
	 */
    public void setMainFunction(Function mainFunction) {
	    this.mainFunction = mainFunction;
    }

	/**
	 * @return the context
	 */
    public Context getContext() {
	    return context;
    }

	/**
	 * @param context the context to set
	 */
    public void setContext(Context context) {
	    this.context = context;
    }

	/**
	 * @return the objectMap
	 */
    public HashMap getObjectMap() {
	    return objectMap;
    }

	/**
	 * @param objectMap the objectMap to set
	 */
    public void setObjectMap(HashMap objectMap) {
	    this.objectMap = objectMap;
    }

	/**
	 * @return the contextMap
	 */
    public HashMap getContextMap() {
	    return contextMap;
    }

	/**
	 * @param contextMap the contextMap to set
	 */
    public void setContextMap(HashMap contextMap) {
	    this.contextMap = contextMap;
    }

}