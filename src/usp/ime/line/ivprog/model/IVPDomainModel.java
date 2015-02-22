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
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariableReference;
import usp.ime.line.ivprog.listeners.IFunctionListener;
import usp.ime.line.ivprog.listeners.IVariableListener;
import usp.ime.line.ivprog.model.utils.IVPConstants;
import usp.ime.line.ivprog.model.utils.IVPMapping;
import usp.ime.line.ivprog.model.utils.Services;
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
	
	public IVPDomainModel(){
		factory = new DataFactory();
	}
	
	public void initializeModel() {
		createFunction(ResourceBundleIVP.getString("mainFunctionName"), IVPConstants.FUNC_RETURN_VOID, Services.getService().getCurrentState());
	}
	
	/**
	 * Validates if there is already a variable with the given name.
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
		Services.getService().getContextMapping().put(f.getUniqueID(), new Context());
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
		assignment.add(new IVPProgramData()); //model
		assignment.add(new IVPMapping()); //view
		return assignment;
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
		((IVPProgramData)as.get(0)).getFunctionListeners().add(listener);
	}

	//------------- DOMAIN ACTION
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
		/*
		for (int i = 0; i < v.getVariableReferenceList().size(); i++) {
			Reference r = (Reference) Services.getService().getModelMapping().get(v.getVariableReferenceList().get(i));
			r.setReferencedName(v.getVariableName());
		}
		*/
		state.stateChanged();
		return lastName;
	}

	public Vector changeVariableType(String id, String newType, AssignmentState state) {
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		Vector returnedVector = new Vector();
		for (int i = 0; i < v.getReferenceList().size(); i++) {
			IVPVariableReference r = (IVPVariableReference) Services.getService().getModelMapping().get(v.getReferenceList().get(i));
			r.setReferencedType(newType);
		}
		String lastType = v.getVariableType();
		returnedVector.add(lastType);
		v.setVariableType(newType);
		Context c = (Context) Services.getService().getContextMapping().get(v.getScopeID());
		initValueWhenTypeChanged(newType, v, c);
		Vector attLines = new Vector();
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener)Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableType(id, newType);
			/*
			if (listener instanceof VariableSelectorUI) {
				if (((VariableSelectorUI) listener).isIsolated()) {
					String modelParentID = ((VariableSelectorUI) listener).getModelParent();
					if (Services.getService().getModelMapping().get(modelParentID) instanceof AttributionLine) {
						attLines.add(modelParentID);
					}
				}
			}*/
		}
		/* ARRUMA AS LINHAS DE ATRIBUIÇÃO QUE TINHAM A VARIÁVEL
		for (int i = 0; i < attLines.size(); i++) { // ta errado, só posso mexer na attline se eu estiver mostrando (na ref da esquerda) a var que mudou 
			AttributionLine attLine = (AttributionLine) Services.getService().getModelMapping().get(attLines.get(i));
			VariableReference varRef = (VariableReference) Services.getService().getModelMapping().get(attLine.getLeftVariableID());
			if (attLine.getLeftVariableType() != newType && id.equals(varRef.getReferencedVariable())) {
				state.remove((DomainObject) Services.getService().getModelMapping().get(attLine.getRightExpressionID()));
				attLine.setLeftVariableType(newType);
				AttributionLineUI attLineUI = (AttributionLineUI) Services.getService().getViewMapping().get(attLines.get(i));
				attLineUI.updateHoldingType(newType);
				returnedVector.add(deleteExpression(attLine.getRightExpressionID(), attLine.getUniqueID(), "", true, false, state));
			}
		}*/
		state.stateChanged();
		return returnedVector;
	}

	/**
	 * @param newType
	 * @param v
	 * @param c
	 */
    private void initValueWhenTypeChanged(String newType, IVPVariable v, Context c) {
	    if(newType.equals(IVPValue.INTEGER_TYPE)){
			c.addInt(v.getValueID(), new Integer(IVPValue.DEFAULT_INTEGER).intValue());
		} else if(newType.equals(IVPValue.DOUBLE_TYPE)){
			c.addDouble(v.getValueID(), new Double(IVPValue.DEFAULT_DOUBLE).doubleValue());
		} else if(newType.equals(IVPValue.STRING_TYPE)){
			c.addString(v.getValueID(), IVPValue.DEFAULT_STRING);
		} else {
			c.addBoolean(v.getValueID(), new Boolean(IVPValue.DEFAULT_BOOLEAN).booleanValue());
		}
    }

	public void restoreVariableType(String id, Vector ret, AssignmentState state) {
		
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		Context c = (Context) Services.getService().getContextMapping().get(v.getScopeID());
		v.setVariableType((String) ret.get(0));
		changeVariableInitialValue(v.getUniqueID(), getInitValue((String) ret.get(0)), state);
		
		Vector attLines = new Vector();
		for (int i = 0; i < Services.getService().getProgramData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) Services.getService().getProgramData().getVariableListeners().get(i);
			listener.changeVariableType(id, (String) ret.get(0));
			/*	if (listener instanceof VariableSelectorUI) {
				if (((VariableSelectorUI) listener).isIsolated()) {
					String modelParentID = ((VariableSelectorUI) listener).getModelParent();
					if (Services.getService().getModelMapping().get(modelParentID) instanceof AttributionLine) {
						attLines.add(modelParentID);
					}
				}
			}*/
		}
		/*
		for (int i = 0; i < attLines.size(); i++) {
			AttributionLine attLine = (AttributionLine) Services.getService().getModelMapping().get(attLines.get(i));
			VariableReference varRef = (VariableReference) Services.getService().getModelMapping().get(attLine.getLeftVariableID());
			if (attLine.getLeftVariableType() != (Short) ret.get(0) && id.equals(varRef.getReferencedVariable())) {
				attLine.setLeftVariableType((Short) ret.get(0));
				AttributionLineUI attLineUI = (AttributionLineUI) Services.getService().getViewMapping().get(attLines.get(i));
				attLineUI.updateHoldingType((Short) ret.get(0));
			}
		}
		for (int i = 1; i < ret.size(); i++) {
			String restoredID = (String) ret.get(i);
			String holderID = ((Expression) Services.getService().getModelMapping().get(ret.get(i))).getParentID();
			restoreExpression(restoredID, holderID, "", true, state);
			state.add((DomainObject) Services.getService().getModelMapping().get((String) ret.get(i)));
		}
		*/
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
	 * This needs an explanation. The IVPValue hold the true variable value. 
	 * A variable has a value. (IVPVariable -> IVPValue).
	 * @param newValue
	 * @param c
	 * @param variableID
	 * @param variableType
	 * @return
	 */
	private String getCurrentVariableValueAndSetNew(String newValue, Context c, String variableID, String variableType){
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(variableID);
		if(variableType.equals(IVPValue.INTEGER_TYPE)){
			int oldValue = c.getInt(v.getValueID());
			int intValue = new Integer(newValue).intValue();
			c.updateInt(v.getValueID(), intValue);
			return oldValue+"";
		}else if(variableType.equals(IVPValue.DOUBLE_TYPE)){
			double oldValue = c.getDouble(v.getValueID());
			double doubleValue = new Double(newValue).doubleValue();
			c.updateDouble(v.getValueID(), doubleValue);
			return oldValue+"";
		}else if(variableType.equals(IVPValue.STRING_TYPE)){
			String oldValue = c.getString(v.getValueID());
			c.updateString(v.getValueID(), newValue);
			return oldValue;
		}else if(variableType.equals(IVPValue.BOOLEAN_TYPE)){
			boolean oldValue = c.getBoolean(v.getValueID());
			boolean booleanValue = new Boolean(newValue).booleanValue();
			c.updateBoolean(v.getValueID(), booleanValue);
			return oldValue+""+"";
		}
		return "";
	}
	
	/**
	 * Get the default initial value for the given type of variable.
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
	
	
}
