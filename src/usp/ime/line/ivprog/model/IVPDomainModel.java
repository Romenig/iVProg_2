/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.model;

import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.AttributionLine;
import usp.ime.line.ivprog.interpreter.execution.code.CodeComposite;
import usp.ime.line.ivprog.interpreter.execution.code.For;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.code.IfElse;
import usp.ime.line.ivprog.interpreter.execution.expressions.Expression;
import usp.ime.line.ivprog.interpreter.execution.expressions.Operation;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPPrinter;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVariableReference;
import usp.ime.line.ivprog.listeners.ICodeListener;
import usp.ime.line.ivprog.listeners.IExpressionListener;
import usp.ime.line.ivprog.listeners.IFunctionListener;
import usp.ime.line.ivprog.listeners.IOperationListener;
import usp.ime.line.ivprog.listeners.IValueListener;
import usp.ime.line.ivprog.listeners.IVariableListener;
import usp.ime.line.ivprog.model.domainaction.CreateExpression;
import usp.ime.line.ivprog.model.utils.IVPConstants;
import usp.ime.line.ivprog.model.utils.IVPMapping;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.workspace.codecomponents.AttributionLineUI;
import usp.ime.line.ivprog.view.domaingui.workspace.codecomponents.VariableSelectorUI;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;
import ilm.framework.assignment.model.AssignmentState;
import ilm.framework.assignment.model.DomainObject;
import ilm.framework.domain.DomainModel;

/**
 * @author Romenig
 */
public class IVPDomainModel extends DomainModel {

	private DataFactory factory;
	private int varCount = 0;

	public IVPDomainModel() {
		factory = new DataFactory();
	}

	public void initializeModel() {
		createFunction(ResourceBundleIVP.getString("mainFunctionName"), IVPConstants.FUNC_RETURN_VOID, Services.getService()
		        .getCurrentState());
	}

	/**
	 * Validates if there is already a variable with the given name.
	 * 
	 * @param modelScopeID
	 * @param name
	 * @return
	 */
	public boolean validateVariableName(String modelScopeID, String name) {
		Function f = (Function) Services.getService().getModelMapping().get(modelScopeID);
		Vector v = f.getLocalVariables();
		for (int i = 0; i < v.size(); i++) {
			IVPVariable var = (IVPVariable) Services.getService().getModelMapping().get(v.get(i));
			if (var.getVariableName().equals(name))
				return false;
		}
		return true;
	}

	// Program actions
	public void createFunction(String name, String funcReturnVoid, AssignmentState state) {
		Function f = (Function) factory.createFunction();
		f.setFunctionName(name);
		f.setFunctionReturnType(funcReturnVoid);
		Services.getService().getModelMapping().put(f.getUniqueID(), f);
		Services.getService().getProgramData().setMainFunction(f);
		Context c = new Context();
		c.setFunctionID(f.getUniqueID());
		Services.getService().getContextMapping().put(f.getUniqueID(), c);
		for (int i = 0; i < Services.getService().getProgramData().getFunctionListeners().size(); i++) {
			IFunctionListener listener = (IFunctionListener) Services.getService().getProgramData().getFunctionListeners().get(i);
			listener.functionCreated(f.getUniqueID());
		}
		state.stateChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.domain.DomainModel#getNewAssignmentState()
	 */
	public AssignmentState getNewAssignmentState() {
		AssignmentState assignment = new AssignmentState();
		assignment.add(new IVPProgramData()); // model
		assignment.add(new IVPMapping()); // view
		return assignment;
	}
	
	public String changeValue(String scopeID, String id, String constantValue, AssignmentState state) {
		IVPValue c = (IVPValue) Services.getService().getModelMapping().get(id);
		Context context =  (Context) Services.getService().getContextMapping().get(scopeID);
		String lastValue = getCurrentValueAndSetNew(constantValue, context, id, c.getValueType());
		IValueListener v = (IValueListener) Services.getService().getViewMapping().getObject(id);
		v.valueChanged(constantValue);
		return lastValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.domain.DomainModel#AutomaticChecking(ilm.framework.assignment
	 * .model.AssignmentState, ilm.framework.assignment.model.AssignmentState)
	 */
	public float AutomaticChecking(AssignmentState studentAnswer, AssignmentState expectedAnswer) {
		return 0;
	}

	/**
	 * @param iFunctionListener
	 */
	public void addFunctionListener(IFunctionListener listener) {
		AssignmentState as = (AssignmentState) Services.getService().getCurrentState();
		((IVPProgramData) as.get(0)).getFunctionListeners().add(listener);
	}

	// ------------- DOMAIN ACTION
	public String createVariable(String scopeID, String initialValue, AssignmentState state) {
		Function f = (Function) Services.getService().getModelMapping().get(scopeID);
		Context c = (Context) Services.getService().getContextMapping().get(f.getUniqueID());
		IVPVariable newVar = (IVPVariable) factory.createIVPVariable();
		newVar.setVariableName("variavel" + varCount);
		newVar.setScopeID(scopeID);
		newVar.setVariableType(IVPValue.INTEGER_TYPE);
		varCount++;
		f.addVariable(newVar, initialValue, c, Services.getService().getModelMapping(), factory);
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.addedVariable(newVar.getUniqueID());
		}
		state.stateChanged();
		return newVar.getUniqueID();
	}

	public void removeVariable(String scopeID, String variableID, AssignmentState state) {
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.removedVariable(variableID);
		}
		state.stateChanged();
	}

	public void restoreVariable(String scopeID, String variableID, AssignmentState state) {
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.variableRestored(variableID);
		}
		state.stateChanged();
	}

	public String changeVariableName(String id, String name, AssignmentState state) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		String lastName = v.getVariableName();
		v.setVariableName(name);
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableName(id, name, lastName);
		}

		for (int i = 0; i < v.getReferenceList().size(); i++) {
			IVPVariableReference r = (IVPVariableReference) Services.getService().getModelMapping().get(v.getReferenceList().get(i));
			r.setReferencedName(v.getVariableName());
		}

		state.stateChanged();
		return lastName;
	}

	public Vector changeVariableType(String scope, String id, String newType, AssignmentState state) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		Vector returnedVector = new Vector();
		for (int i = 0; i < v.getReferenceList().size(); i++) {
			IVPVariableReference r = (IVPVariableReference) Services.getService().getModelMapping().get(v.getReferenceList().get(i));
			r.setReferencedType(newType);
		}
		String lastType = v.getVariableType();
		returnedVector.add(lastType);
		Context c = (Context) Services.getService().getContextMapping().get(v.getScopeID());
		v.updateVariableType(newType, Services.getService().getModelMapping(), c);
		Vector attLines = new Vector();
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableType(id, newType);

			if (listener instanceof VariableSelectorUI) {
				if (((VariableSelectorUI) listener).isIsolated()) {
					String modelParentID = ((VariableSelectorUI) listener).getModelParent();
					if (Services.getService().getModelMapping().get(modelParentID) instanceof AttributionLine) {
						attLines.add(modelParentID);
					}
				}
			}
		}
		for (int i = 0; i < attLines.size(); i++) { // ta errado, só posso mexer
			                                        // na attline se eu estiver
			                                        // mostrando (na ref da
			                                        // esquerda) a var que mudou
			AttributionLine attLine = (AttributionLine) Services.getService().getModelMapping().get(attLines.get(i));
			IVPVariableReference varRef = (IVPVariableReference) Services.getService().getModelMapping().get(attLine.getVariableID());
			if (attLine.getLeftVariableType() != newType && id.equals(varRef.getReferencedID())) {
				state.remove((DomainObject) Services.getService().getModelMapping().get(attLine.getExpressionID()));
				attLine.setLeftVariableType(newType);
				AttributionLineUI attLineUI = (AttributionLineUI) Services.getService().getViewMapping()
				        .getObject((String) attLines.get(i));
				attLineUI.updateHoldingType(newType);
				returnedVector.add(deleteExpression(scope, attLine.getExpressionID(), attLine.getUniqueID(), "", true, false, state));
			}
		}
		state.stateChanged();
		return returnedVector;
	}

	/**
	 * @param newType
	 * @param v
	 * @param c
	 */
	private void initValueWhenTypeChanged(String newType, IVPVariable v, Context c) {
		if (newType.equals(IVPValue.INTEGER_TYPE)) {
			c.addInt(v.getValueID(), new Integer(IVPValue.DEFAULT_INTEGER).intValue());
		} else if (newType.equals(IVPValue.DOUBLE_TYPE)) {
			c.addDouble(v.getValueID(), new Double(IVPValue.DEFAULT_DOUBLE).doubleValue());
		} else if (newType.equals(IVPValue.STRING_TYPE)) {
			c.addString(v.getValueID(), IVPValue.DEFAULT_STRING);
		} else {
			c.addBoolean(v.getValueID(), new Boolean(IVPValue.DEFAULT_BOOLEAN).booleanValue());
		}
	}

	public void restoreVariableType(String scopeID, String id, Vector ret, AssignmentState state) {

		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		Context c = (Context) Services.getService().getContextMapping().get(v.getScopeID());

		v.updateVariableType((String) ret.get(0), Services.getService().getModelMapping(), c);

		Vector attLines = new Vector();
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableType(id, (String) ret.get(0));
			if (listener instanceof VariableSelectorUI) {
				if (((VariableSelectorUI) listener).isIsolated()) {
					String modelParentID = ((VariableSelectorUI) listener).getModelParent();
					if (Services.getService().getModelMapping().get(modelParentID) instanceof AttributionLine) {
						attLines.add(modelParentID);
					}
				}
			}
		}

		for (int i = 0; i < attLines.size(); i++) {
			AttributionLine attLine = (AttributionLine) Services.getService().getModelMapping().get(attLines.get(i));
			IVPVariableReference varRef = (IVPVariableReference) Services.getService().getModelMapping().get(attLine.getVariableID());
			if (attLine.getLeftVariableType() != (String) ret.get(0) && id.equals(varRef.getReferencedID())) {
				attLine.setLeftVariableType((String) ret.get(0));
				AttributionLineUI attLineUI = (AttributionLineUI) Services.getService().getViewMapping()
				        .getObject((String) attLines.get(i));
				attLineUI.updateHoldingType((String) ret.get(0));
			}
		}
		for (int i = 1; i < ret.size(); i++) {
			String restoredID = (String) ret.get(i);
			String holderID = ((Expression) Services.getService().getModelMapping().get(restoredID)).getParentID();
			restoreExpression(scopeID, restoredID, holderID, "", true, state);
			state.add((DomainObject) Services.getService().getModelMapping().get((String) ret.get(i)));
		}

		state.stateChanged();
	}

	public String changeVariableInitialValue(String id, String value, AssignmentState state) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		Context c = (Context) Services.getService().getContextMapping().get(v.getScopeID());
		String lastValue = getCurrentVariableValueAndSetNew(value, c, id, v.getVariableType());
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableValue(id, value);
		}
		state.stateChanged();
		return lastValue;
	}

	/**
	 * This needs an explanation. The IVPValue hold the true variable value. A
	 * variable has a value. (IVPVariable -> IVPValue).
	 * 
	 * @param newValue
	 * @param c
	 * @param variableID
	 * @param variableType
	 * @return
	 */
	private String getCurrentVariableValueAndSetNew(String newValue, Context c, String variableID, String variableType) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(variableID);
		if (variableType.equals(IVPValue.INTEGER_TYPE)) {
			int oldValue = c.getInt(v.getValueID());
			int intValue = new Integer(newValue).intValue();
			c.updateInt(v.getValueID(), intValue);
			return oldValue + "";
		} else if (variableType.equals(IVPValue.DOUBLE_TYPE)) {
			double oldValue = c.getDouble(v.getValueID());
			double doubleValue = new Double(newValue).doubleValue();
			c.updateDouble(v.getValueID(), doubleValue);
			return oldValue + "";
		} else if (variableType.equals(IVPValue.STRING_TYPE)) {
			String oldValue = c.getString(v.getValueID());
			c.updateString(v.getValueID(), newValue);
			return oldValue;
		} else if (variableType.equals(IVPValue.BOOLEAN_TYPE)) {
			boolean oldValue = c.getBoolean(v.getValueID());
			boolean booleanValue = new Boolean(newValue).booleanValue();
			c.updateBoolean(v.getValueID(), booleanValue);
			return oldValue + "" + "";
		}
		return "";
	}
	
	private String getCurrentValueAndSetNew(String newValue, Context c, String valueID, String valueType) {
		IVPValue v = (IVPValue) Services.getService().getModelMapping().get(valueID);
		if (valueType.equals(IVPValue.INTEGER_TYPE)) {
			int oldValue = c.getInt(v.getUniqueID());
			int intValue = new Integer(newValue).intValue();
			c.updateInt(v.getUniqueID(), intValue);
			return oldValue + "";
		} else if (valueType.equals(IVPValue.DOUBLE_TYPE)) {
			double oldValue = c.getDouble(v.getUniqueID());
			double doubleValue = new Double(newValue).doubleValue();
			c.updateDouble(v.getUniqueID(), doubleValue);
			return oldValue + "";
		} else if (valueType.equals(IVPValue.STRING_TYPE)) {
			String oldValue = c.getString(v.getUniqueID());
			c.updateString(v.getUniqueID(), newValue);
			return oldValue;
		} else if (valueType.equals(IVPValue.BOOLEAN_TYPE)) {
			boolean oldValue = c.getBoolean(v.getUniqueID());
			boolean booleanValue = new Boolean(newValue).booleanValue();
			c.updateBoolean(v.getUniqueID(), booleanValue);
			return oldValue + "" + "";
		}
		return "";
	}

	/**
	 * Get the default initial value for the given type of variable.
	 * 
	 * @param type
	 * @return
	 */
	public String getInitValue(String type) {
		if (type.equals(IVPValue.BOOLEAN_TYPE)) {
			return IVPValue.DEFAULT_BOOLEAN;
		} else if (type.equals(IVPValue.DOUBLE_TYPE)) {
			return IVPValue.DEFAULT_DOUBLE;
		} else if (type.equals(IVPValue.INTEGER_TYPE)) {
			return IVPValue.DEFAULT_INTEGER;
		} else if (type.equals(IVPValue.STRING_TYPE)) {
			return IVPValue.DEFAULT_STRING;
		}
		return "";
	}

	/**
	 * @param removedExpression
	 * @param holder
	 * @param context
	 * @param b
	 * @param _currentState
	 */
	public void restoreExpression(String scopeID, String expression, String holder, String context, boolean wasClean, AssignmentState state) {
		Expression exp = (Expression) Services.getService().getModelMapping().get(expression);
		for (int i = 0; i < Services.getService().getProgramData().getExpressionListeners().size(); i++) {
			if (wasClean) {
				((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i))
				        .expressionRestoredFromCleaning(holder, expression, context);
			} else {
				((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i)).expressionRestored(holder,
				        expression, context);
			}
		}
		state.add(exp);
	}

	/**
	 * @param lastExpression
	 * @param holder
	 * @param expressionType
	 * @param primitiveType
	 * @param context
	 * @param _currentState
	 * @return
	 */
	public String createExpression(String scopeID, String leftExpID, String holder, String expressionType, String primitiveType,
	        String context, AssignmentState state) {
		Expression exp = getNewExpression(scopeID, leftExpID, holder, expressionType, primitiveType, state);
		updateExpressionListeners(scopeID, holder, expressionType, context, state, exp);
		putExpressionOnRightPlace(holder, context, exp);
		state.add(exp);
		return exp.getUniqueID();
	}

	/**
	 * 
	 * @param leftExpID
	 * @param holder
	 * @param expressionType
	 * @param primitiveType
	 * @param state
	 * @return
	 */
	private Expression getNewExpression(String scopeID, String leftExpID, String holder, String expressionType, String primitiveType,
	        AssignmentState state) {
		Expression exp = null;
		Context c = (Context) Services.getService().getContextMapping().get(scopeID);
		if (expressionType.equals(Expression.VARIABLE)) {
			exp = (Expression) factory.createIVPVariableReference();
			exp.setExpressionType(primitiveType);
			((IVPVariableReference) exp).setReferencedType(primitiveType);
		} else if (expressionType.equals(IVPValue.INTEGER_TYPE) || expressionType.equals(IVPValue.DOUBLE_TYPE)
		        || expressionType.equals(IVPValue.STRING_TYPE) || expressionType.equals(IVPValue.BOOLEAN_TYPE)) {
			exp = (Expression) createValueToExpression(expressionType, c);
			exp.setExpressionType(expressionType);
		} else {
			exp = (Expression) createOperationToExpression(expressionType, state);
			exp.setExpressionType(expressionType);
			if(expressionType.equals(Expression.OPERATION_ADDITION)||
					expressionType.equals(Expression.OPERATION_SUBTRACTION)||
					expressionType.equals(Expression.OPERATION_DIVISION)||
					expressionType.equals(Expression.OPERATION_INTDIV)||
					expressionType.equals(Expression.OPERATION_MULTIPLICATION)){ //não estará no contexto ainda
				IVPValue operationValue = factory.createIVPNumber();
				Services.getService().getModelMapping().put(operationValue.getUniqueID(), operationValue);
				((Operation)exp).setOperationResultID(operationValue.getUniqueID());
			}
			if (leftExpID != "") {
				((Expression) Services.getService().getModelMapping().get(leftExpID)).setParentID(exp.getUniqueID());
				((Operation) exp).setExpressionA(leftExpID);
			}
		}
		exp.setParentID(holder);
		exp.setScopeID(scopeID);
		Services.getService().getModelMapping().put(exp.getUniqueID(), exp);
		return exp;
	}

	/**
	 * 
	 * @param holder
	 * @param expressionType
	 * @param context
	 * @param state
	 * @param exp
	 */
	private void updateExpressionListeners(String scopeID, String holder, String expressionType, String context, AssignmentState state,
	        Expression exp) {
		for (int i = 0; i < Services.getService().getProgramData().getExpressionListeners().size(); i++) {
			((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i)).expressionCreated(holder,
			        exp.getUniqueID(), context);
		}
		if (expressionType == Expression.OPERATION_AND || expressionType == Expression.OPERATION_OR) {
			Expression newExp = (Expression) createOperationToExpression(Expression.OPERATION_EQU, state);
			newExp.setParentID(exp.getUniqueID());
			newExp.setScopeID(scopeID);
			newExp.setExpressionType(Expression.OPERATION_EQU);
			((Operation) exp).setExpressionB(newExp.getUniqueID());
			Services.getService().getModelMapping().put(newExp.getUniqueID(), newExp);
			for (int i = 0; i < Services.getService().getProgramData().getExpressionListeners().size(); i++) {
				((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i)).expressionCreated(
				        exp.getUniqueID(), newExp.getUniqueID(), "right");
			}
			state.add(newExp);
		}
		

	}

	private Operation createOperationToExpression(String operationType, AssignmentState state) {
		Operation op = null;
		if (operationType.equals(Operation.OPERATION_ADDITION)) {
			op = factory.createAddition();
			IVPNumber result = factory.createIVPNumber();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_SUBTRACTION)) {
			IVPNumber result = factory.createIVPNumber();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createSubtraction();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_MULTIPLICATION)) {
			IVPNumber result = factory.createIVPNumber();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createMultiplication();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_DIVISION)) {
			IVPNumber result = factory.createIVPNumber();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createDivision();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_AND)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createAnd();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_OR)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createOr();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_LEQ)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createLessThanOrEqualTo();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_LES)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createLessThan();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_EQU)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createEqualTo();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_GRE)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createGreaterThan();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_GEQ)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createGreaterThanOrEqualTo();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_NEQ)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			op = factory.createNotEqualTo();
			((Operation)op).setOperationResultID(result.getUniqueID());
		} else if (operationType.equals(Operation.OPERATION_INTDIV)) {
			IVPBoolean result = factory.createIVPBoolean();
			Services.getService().getModelMapping().put(result.getUniqueID(), result);
			state.add(result);
			((Operation)op).setOperationResultID(result.getUniqueID());
			//op = factory.create();
		}
		return op;
	}

	private IVPValue createValueToExpression(String expressionType, Context c) {
		IVPValue value;
		if (expressionType.equals(Expression.INTEGER_TYPE)) {
			value = factory.createIVPNumber();
			value.setValueType(IVPValue.INTEGER_TYPE);
			c.addInt(value.getUniqueID(), new Integer(IVPValue.DEFAULT_INTEGER).intValue());
			return value;
		} else if (expressionType.equals(Expression.DOUBLE_TYPE)) {
			value = factory.createIVPNumber();
			value.setValueType(IVPValue.DOUBLE_TYPE);
			return value;
		} else if (expressionType.equals(Expression.STRING_TYPE)) {
			value = factory.createIVPNumber();
			value.setValueType(IVPValue.DOUBLE_TYPE);
			return value;
		} else if (expressionType.equals(IVPValue.BOOLEAN_TYPE)) {
			value = factory.createIVPNumber();
			value.setValueType(IVPValue.BOOLEAN_TYPE);
			return value;
		}
		return null;
	}

	/**
	 * 
	 * @param holder
	 * @param context
	 * @param exp
	 */
	private void putExpressionOnRightPlace(String holder, String context, Expression exp) {

		
		if (context.equals("right")) {
			((Operation) Services.getService().getModelMapping().get(holder)).setExpressionB(exp.getUniqueID());
		} else if (context.equals("left")) {
			((Operation) Services.getService().getModelMapping().get(holder)).setExpressionA(exp.getUniqueID());
		} else if (context.equals("leftVar")) {
			((AttributionLine) Services.getService().getModelMapping().get(holder)).setVariableID(exp.getUniqueID());
		} else if (context.equals("printable")) {
			((IVPPrinter) Services.getService().getModelMapping().get(holder)).setPrintableID(exp.getUniqueID());
		} else if (context.equals("writable")) {
			// ((ReadData)
			// Services.getService().getModelMapping().get(holder)).setWritableObject(exp.getUniqueID());
		} else if (context.equals("while")) {
			// ((While)
			// Services.getService().getModelMapping().get(holder)).setCondition(exp.getUniqueID());
		} else if (context.equals("ifElse")) {
			 ((IfElse)Services.getService().getModelMapping().get(holder)).setFlowCondition(exp.getUniqueID());
		} else if (context.equals("forIndex")) {
			// ((For)
			// Services.getService().getModelMapping().get(holder)).setIndexExpression(exp.getUniqueID());
		} else if (context.equals("forLowerBound")) {
			// ((For)
			// Services.getService().getModelMapping().get(holder)).setLowerBoundExpression(exp.getUniqueID());
		} else if (context.equals("forUpperBound")) {
			// ((For)
			// Services.getService().getModelMapping().get(holder)).setUpperBoundExpression(exp.getUniqueID());
		} else if (context.equals("forIncrement")) {
			// ((For)
			// Services.getService().getModelMapping().get(holder)).setIncrementExpression(exp.getUniqueID());
		}
	}

	/**
	 * @param expressionID
	 * @param context
	 * @param newType
	 * @param _currentState
	 * @return
	 */
	public String changeOperationSign(String expression, String context, String newType, AssignmentState state) {
		Expression exp = (Expression) Services.getService().getModelMapping().get(expression);
		String lastType = exp.getExpressionType();
		exp.setExpressionType(newType);
		for (int i = 0; i < Services.getService().getProgramData().getOperationListeners().size(); i++) {
			((IOperationListener) Services.getService().getProgramData().getOperationListeners().get(i)).operationTypeChanged(expression,
			        context);
		}
		return lastType;
	}

	/**
	 * @param expression
	 * @param holder
	 * @param context
	 * @param isClean
	 * @param isComparison
	 * @param _currentState
	 * @return
	 */
	public String deleteExpression(String currentScope, String expression, String holder, String context, boolean isClean,
	        boolean isComparison, AssignmentState state) {
		Expression exp = (Expression) Services.getService().getModelMapping().get(expression);
		DataObject dataHolder = (DataObject) Services.getService().getModelMapping().get(holder);
		String lastExpressionID;
		if (dataHolder instanceof AttributionLine) {
			((AttributionLine) dataHolder).setExpression("");
			lastExpressionID = "";
		} /*
		 * else if (dataHolder instanceof Print) { // acho que o print nem está
		 * // mais sendo usado para // remover lastExpressionID = ""; }
		 */else if (dataHolder instanceof Operation) {
			((Operation) dataHolder).removeExpression(expression);
			lastExpressionID = ((Operation) dataHolder).getExpressionA();
		} else if (dataHolder instanceof For) {
			((For) dataHolder).removeExpression(expression, context);
		}
		for (int i = 0; i < Services.getService().getProgramData().getExpressionListeners().size(); i++) {
			((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i)).expressionDeleted(expression,
			        context, isClean);
		}
		// checar aqui...
		if (isClean) {
			Expression newExp;
			// TODO: Hora de remover tudo e reinicializar a porra toda...
			if (!isComparison) {
				newExp = (Expression) factory.createIVPVariableReference();
				newExp.setExpressionType(Expression.VARIABLE);
				if (dataHolder instanceof For) {
					((IVPVariableReference) newExp).setReferencedType(Expression.INTEGER_TYPE);
				} else if (dataHolder instanceof AttributionLine) {
					((IVPVariableReference) newExp).setReferencedType(((AttributionLine) dataHolder).getLeftVariableType());
				} else if (dataHolder instanceof Operation) {
					String theOperationType = ((Operation) dataHolder).getExpressionType();
					((IVPVariableReference) newExp).setReferencedType(theOperationType);
				}
			} else {
				newExp = (Expression) createOperationToExpression(Expression.OPERATION_EQU,state);
			}
			newExp.setParentID(holder);
			newExp.setScopeID(currentScope);
			Services.getService().getModelMapping().put(newExp.getUniqueID(), newExp);
			for (int i = 0; i < Services.getService().getProgramData().getExpressionListeners().size(); i++) {
				((IExpressionListener) Services.getService().getProgramData().getExpressionListeners().get(i)).expressionCreated(holder,
				        newExp.getUniqueID(), context);
			}
			if (state != null)
				state.add(newExp);
		}
		if (state != null)
			state.remove(exp);
		return expression;
	}

	/**
	 * @param referenceID
	 * @param newVarID
	 * @param _currentState
	 * @return
	 */
	public String updateReferencedVariable(String refID, String newVarRef, AssignmentState state) {
		String lastReferencedVariable = "";
		IVPVariableReference ref = (IVPVariableReference) Services.getService().getModelMapping().get(refID);
		lastReferencedVariable = ref.getReferencedID();
		ref.setReferencedID(newVarRef);
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.updateReference(refID);
		}
		if (newVarRef != null && !"".equals(newVarRef)) {
			// significa que estou voltando ao estado inicial.
			IVPVariable newReferenced = (IVPVariable) Services.getService().getModelMapping().get(newVarRef);
			newReferenced.addReferenceToTheList(refID);
		}
		IVPVariable lastReferenced = (IVPVariable) Services.getService().getModelMapping().get(lastReferencedVariable);
		if (lastReferenced != null) {
			lastReferenced.removeReferenceFromTheList(refID);
		}
		return lastReferencedVariable;
	}

	/**
	 * @param parentModelID
	 * @param currentModelID
	 * @param newExpID
	 * @param context
	 */
	public void updateParent(String parentModelID, String currentModelID, String newExpID, String context) {
		DataObject parent = (DataObject) Services.getService().getModelMapping().get(parentModelID);
		if (parent instanceof Operation) {
			((Operation) parent).updateParent(currentModelID, newExpID, context);
		} else {
			((DataObject) parent).updateParent(currentModelID, newExpID, context);
		} 
	}

	/**
	 * @param containerID
	 * @param childID
	 * @param context
	 * @param _currentState
	 * @return
	 */
	public int removeChild(String scopeID, String containerID, String childID, String context, AssignmentState state) {
		CodeComposite parent = (CodeComposite) Services.getService().getModelMapping().get(containerID);
		int index = 0;
		if (parent instanceof IfElse) {
			if (context.equals("if")) {
				index = parent.removeChild(childID);
			} else if (context.equals("else")) {
				index = ((IfElse) parent).removeElseChild(childID);
			}
		} else {
			index = parent.removeChild(childID);
		}
		ICodeListener codeListener = (ICodeListener) Services.getService().getProgramData().getCodeListeners().get(containerID);
		codeListener.childRemoved(childID, context);
		// Check child dependencies and remove them
		DataObject child = (DataObject) Services.getService().getModelMapping().get(childID);
		if (child instanceof AttributionLine) {
			state.remove((DomainObject) Services.getService().getModelMapping().get(((AttributionLine) child).getVariableID()));
		} else if (child instanceof CodeComposite) {
			// remove children recursively
		}
		// Framework
		state.remove((DomainObject) Services.getService().getModelMapping().get(childID));
		return index;
	}
	
	public int moveChild(String component, String origin, String destiny, String originContext, String destinyContext, int dropIndex,
	        AssignmentState _currentState) {
		CodeComposite destinyCode = (CodeComposite) Services.getService().getModelMapping().get(destiny);
		CodeComposite originCode = (CodeComposite) Services.getService().getModelMapping().get(origin);
		DataObject componentCode = (DataObject) Services.getService().getModelMapping().get(component);
		int lastIndex = -1;
		ICodeListener destinyListener = (ICodeListener) Services.getService().getViewMapping().getObject(destiny);
		ICodeListener originListener = (ICodeListener) Services.getService().getViewMapping().getObject(origin);
		if (origin != destiny) {
			if (originCode instanceof IfElse) {
				if (originContext.equals("if")) {
					lastIndex = originCode.removeChild(component);
				} else if (originContext.equals("else")) {
					lastIndex = ((IfElse) originCode).removeElseChild(component);
				}
			} else {
				lastIndex = originCode.removeChild(component);
			}
			if (destinyCode instanceof IfElse) {
				if (destinyContext.equals("if")) {
					destinyCode.addChildAtIndex(component, dropIndex);
				} else if (destinyContext.equals("else")) {
					((IfElse) destinyCode).addElseChildToIndex(component, dropIndex);
				}
			} else {
				destinyCode.addChildAtIndex(component, dropIndex);
			}
			originListener.childRemoved(component, originContext);
			destinyListener.restoreChild(component, dropIndex, destinyContext);
		} else {
			if (originCode instanceof IfElse) {
				if (originContext.equals("if")) {
					lastIndex = originCode.moveChild(component, dropIndex);
				} else if (originContext.equals("else")) {
					lastIndex = ((IfElse) originCode).moveElseChild(component, dropIndex);
				}
			} else {
				lastIndex = originCode.moveChild(component, dropIndex);
			}
			originListener.moveChild(component, originContext, dropIndex);
		}
		componentCode.setParentID(destiny);
		return lastIndex;
	}

	/**
	 * @param containerID
	 * @param childID
	 * @param index
	 * @param context
	 * @param _currentState
	 */
	public void restoreChild(String scopeID, String containerID, String childID, int index, String context, AssignmentState state) {
		CodeComposite parent = (CodeComposite) Services.getService().getModelMapping().get(containerID);
		if (parent instanceof IfElse) {
			if (context.equals("if")) {
				parent.addChildAtIndex(childID, index);
			} else if (context.equals("else")) {
				((IfElse) parent).addElseChildAtIndex(childID, index);
			}
		} else {
			parent.addChildAtIndex(childID, index);
		}
		ICodeListener codeListener = (ICodeListener) Services.getService().getProgramData().getCodeListeners().get(containerID);
		codeListener.restoreChild(childID, index, context);
	}

	/**
	 * @param containerID
	 * @param classID
	 * @param context
	 * @param _currentState
	 * @return
	 */
	public String newChild(String scopeID, String containerID, String classID, String context, AssignmentState state) {
		DataObject codeBlock = null;
		if (classID.equals(IVPConstants.MODEL_WHILE)) {
			codeBlock = (CodeComposite) factory.createWhile();
			initCodeBlock(scopeID, containerID, codeBlock);
			createExpression(scopeID, "", codeBlock.getUniqueID(), Expression.OPERATION_EQU, "-1", "while", state);
		} else if (classID.equals(IVPConstants.MODEL_WRITE)) {
			codeBlock = (DataObject) factory.createIVPPrinter();
			initCodeBlock(scopeID, containerID, codeBlock);
			createExpression(scopeID, "", codeBlock.getUniqueID(), Expression.VARIABLE, "-1", "printable", state);
		} else if (classID.equals(IVPConstants.MODEL_IFELSE)) {
			codeBlock = (DataObject) factory.createIfElse();
			initCodeBlock(scopeID, containerID, codeBlock);
			createExpression(scopeID, "", codeBlock.getUniqueID(), Expression.OPERATION_EQU, "-1", "ifElse", state);
		} else if (classID.equals(IVPConstants.MODEL_ATTLINE)) {
			codeBlock = (DataObject) factory.createAttributionLine();
			initCodeBlock(scopeID, containerID, codeBlock);
			createExpression(scopeID, "", codeBlock.getUniqueID(), Expression.VARIABLE, "-1", "leftVar", state);
		} else if (classID.equals(IVPConstants.MODEL_READ)) {
			/*
			 * codeBlock = (DataObject) factory.createRead();
			 * initCodeBlock(containerID, codeBlock); createExpression("",
			 * codeBlock.getUniqueID(), Expression.EXPRESSION_VARIABLE, (short)
			 * -1, "writable", state);
			 */
		} else if (classID.equals(IVPConstants.MODEL_FOR)) {
			/*
			 * codeBlock = (DataObject) factory.createFor();
			 * initCodeBlock(containerID, codeBlock); createForExpressions((For)
			 * codeBlock, state);
			 */
		}
		CodeComposite container = (CodeComposite) Services.getService().getModelMapping().get(containerID);
		if (container instanceof IfElse) {
			if (context.equals("if")) {
				container.addChild(codeBlock.getUniqueID());
			} else if (context.equals("else")) {
				((IfElse) container).addElseChild(codeBlock.getUniqueID());
			}
		} else {
			container.addChild(codeBlock.getUniqueID());
		}
		ICodeListener listener = (ICodeListener) Services.getService().getProgramData().getCodeListeners().get(containerID);
		listener.addChild(codeBlock.getUniqueID(), context);
		// Framework
		state.add(codeBlock);
		return codeBlock.getUniqueID();
	}

	private void initCodeBlock(String scopeID, String containerID, DataObject codeBlock) {
		codeBlock.setParentID(containerID);
		codeBlock.setScopeID(scopeID);
		Services.getService().getModelMapping().put(codeBlock.getUniqueID(), codeBlock);
	}

	/**
	 * @param listener
	 * @param id
	 */
	public void addComponentListener(ICodeListener listener, String id) {
		Services.getService().getProgramData().getCodeListeners().put(id, listener);
	}

	boolean error = false;

	/**
	 * 
	 */
	public void playCode() {
		if (Services.getService().getController().isContentSet()) {
			Services.getService().getController().lockCodeDown();
			Object[] functionList = Services.getService().getProgramData().getFunctionMap().values().toArray();
			Function mainFunction = Services.getService().getProgramData().getMainFunction();
			
			Context mainContext = (Context) Services.getService().getContextMapping().get(mainFunction.getUniqueID());
			Context cloneContext = (Context) mainContext.clone();
			
			HashMap modelMapping = (HashMap) Services.getService().getModelMapping();
			HashMap cloneModel = (HashMap) modelMapping.clone();
			
			mainFunction.evaluate(cloneContext, cloneModel, factory);
			
		}
	}

}
