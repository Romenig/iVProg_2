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
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.listeners.IFunctionListener;
import usp.ime.line.ivprog.listeners.IVariableListener;
import usp.ime.line.ivprog.model.components.datafactory.dataobjetcs.AttributionLine;
import usp.ime.line.ivprog.model.components.datafactory.dataobjetcs.Expression;
import usp.ime.line.ivprog.model.components.datafactory.dataobjetcs.Reference;
import usp.ime.line.ivprog.model.components.datafactory.dataobjetcs.Variable;
import usp.ime.line.ivprog.model.components.datafactory.dataobjetcs.VariableReference;
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
	
	public IVPDomainModel(){
		factory = new DataFactory();
	}
	
	public void initializeModel() {
		createFunction(ResourceBundleIVP.getString("mainFunctionName"), IVPConstants.FUNC_RETURN_VOID, Services.getService().getCurrentState());
	}

	// Program actions
	public void createFunction(String name, String funcReturnVoid, AssignmentState state) {
		System.out.println("Create Function IVPDomainModel "+name);
		Function f = (Function) factory.createFunction();
		f.setFunctionName(name);
		f.setFunctionReturnType(funcReturnVoid);
		Services.getService().getModelMapping().put(f.getUniqueID(), f);
		Services.getService().getContextMapping().put(f.getUniqueID(), new Context());
		for (int i = 0; i < Services.getService().getProgramData().getFunctionListeners().size(); i++) {
			System.out.println("passou por aqui...");
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
		System.out.println("Criou o estado novo.");
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
		System.out.println("Adiciona function listener...");
		AssignmentState as = (AssignmentState) Services.getService().getCurrentState();
		((IVPProgramData)as.get(0)).getFunctionListeners().add(listener);
	}

	//------------- DOMAIN ACTION
	public String createVariable(String scopeID, String initialValue, AssignmentState state) {
		Function f = (Function) Services.getService().getModelMapping().get(scopeID);
		IVPVariable newVar = (IVPVariable) factory.createIVPVariable();
		newVar.setVariableName("variavel" + varCount);
		varCount++;
		newVar.setVariableType(IVPValue.INTEGER_TYPE);
		Context c = (Context) Services.getService().getContextMapping().get(f.getUniqueID());
		c.addInt(newVar.getUniqueID(), new Integer(initialValue).intValue());
		Services.getService().getModelMapping().put(newVar.getUniqueID(), newVar);
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
		/*
		for (int i = 0; i < v.getVariableReferenceList().size(); i++) {
			Reference r = (Reference) Services.getService().getModelMapping().get(v.getVariableReferenceList().get(i));
			r.setReferencedType(newType);
		}
		String lastType = v.getVariableType();
		
		returnedVector.add(lastType);
		v.setVariableType(newType);
		v.setVariableValue(getInitvalue(newType)+"");
		Vector attLines = new Vector();
		for (int i = 0; i < state.getData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) state.getData().getVariableListeners().get(i);
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
		for (int i = 0; i < attLines.size(); i++) { // ta errado... sï¿½ posso
													// mexer na attLine se eu
													// estiver mostrando (na ref
													// da esquerda) a var que
													// mudou
			AttributionLine attLine = (AttributionLine) Services.getService().getModelMapping().get(attLines.get(i));
			VariableReference varRef = (VariableReference) Services.getService().getModelMapping().get(attLine.getLeftVariableID());
			if (attLine.getLeftVariableType() != newType && id.equals(varRef.getReferencedVariable())) {
				state.remove((DomainObject) Services.getService().getModelMapping().get(attLine.getRightExpressionID()));
				attLine.setLeftVariableType(newType);
				AttributionLineUI attLineUI = (AttributionLineUI) Services.getService().getViewMapping().get(attLines.get(i));
				attLineUI.updateHoldingType(newType);
				returnedVector.add(deleteExpression(attLine.getRightExpressionID(), attLine.getUniqueID(), "", true, false, state));
			}
		}
		state.updateState(v);
		*/
		return returnedVector;
	}

	public void restoreVariableType(String id, Vector ret, AssignmentState state) {
		
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		/*
		v.setVariableType((Short) ret.get(0));
		v.setVariableValue(getInitvalue((Short) ret.get(0)));
		Vector attLines = new Vector();
		for (int i = 0; i < state.getData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) state.getData().getVariableListeners().get(i);
			listener.changeVariableType(id, (Short) ret.get(0));
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
		/*
		 * O VALOR DA VARIAVEL ESTÁ NO CONTEXT, PORTNTO PRECISO TER ACESSO A ELE AQUI!
		 */
		IVPVariable v = (IVPVariable) Services.getService().getModelMapping().get(id);
		String lastValue = v.getVariableValue();
		v.setVariableValue(value);
		for (int i = 0; i < state.getData().getVariableListeners().size(); i++) {
			IVariableListener listener = (IVariableListener) state.getData().getVariableListeners().get(i);
			listener.changeVariableValue(id, value);
		}
		state.updateState((DomainObject) Services.getService().getModelMapping().get(id));
		return lastValue;
	}
	
	public String getInitvalue(String type) {
		if (type.equals(IVPValue.BOOLEAN_TYPE)) {
			return "true";
		} else if (type.equals(IVPValue.DOUBLE_TYPE)) {
			return "1.0";
		} else if (type.equals(IVPValue.INTEGER_TYPE)) {
			return "1";
		} else if (type.equals(IVPValue.STRING_TYPE)) {
			return ResourceBundleIVP.getString("helloWorld.text");
		}
		return "";
	}
}
