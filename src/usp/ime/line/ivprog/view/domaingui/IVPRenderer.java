package usp.ime.line.ivprog.view.domaingui;

import javax.swing.JComponent;

import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.model.utils.Services;
import usp.ime.line.ivprog.view.domaingui.workspace.FunctionBodyUI;
import usp.ime.line.ivprog.view.domaingui.workspace.variable.IVPVariableBasic;
import usp.ime.line.ivprog.view.utils.language.ResourceBundleIVP;

public class IVPRenderer {
	
	public JComponent paint(String objectKey, String scope) {
		DataObject codeElementModel = (DataObject) Services.getService().getModelMapping().get((String) objectKey);
		if (codeElementModel instanceof Function) {
			return renderFunction((Function) codeElementModel);
		} 
		else if (codeElementModel instanceof IVPVariable) {
			return renderVariable((IVPVariable) codeElementModel, scope);
		} 
		/*else if (codeElementModel instanceof While) {
			return renderWhile((While) codeElementModel);
		} else if (codeElementModel instanceof IfElse) {
			return renderIfElse((IfElse) codeElementModel);
		} else if (codeElementModel instanceof Print) {
			return renderWrite((Print) codeElementModel);
		} else if (codeElementModel instanceof AttributionLine) {
			return renderAttributionLine((AttributionLine) codeElementModel);
		} else if (codeElementModel instanceof Expression) {
			return renderExpresion((Expression) codeElementModel);
		} else if (codeElementModel instanceof Reference) {
			return renderReference((Reference) codeElementModel);
		} else if (codeElementModel instanceof ReadData) {
			return renderRead((ReadData) codeElementModel);
		} else if (codeElementModel instanceof For) {
			return renderFor((For) codeElementModel);
		}
		*/
		return null;
	}

	/*
	private JComponent renderReference(Reference referenceModel) {
		return null;
	}

	private JComponent renderExpresion(Expression expressionModel) {
		VariableSelectorUI var;
		OperationUI exp;
		ConstantUI constant;
		if (expressionModel instanceof VariableReference) {
			var = new VariableSelectorUI(expressionModel.getParentID());
			var.setModelID(expressionModel.getUniqueID());
			var.setScopeID(expressionModel.getScopeID());
			Services.getService().getViewMapping().put(expressionModel.getUniqueID(), var);
			return var;
		} else if (expressionModel instanceof Constant) {
			constant = new ConstantUI(expressionModel.getUniqueID());
			constant.setExpressionType(expressionModel.getExpressionType());
			constant.setModelScope(expressionModel.getScopeID());
			Services.getService().getViewMapping().put(expressionModel.getUniqueID(), constant);
			return constant;
		} else {// It's an operation
			if (expressionModel.getExpressionType() >= Expression.EXPRESSION_OPERATION_AND
			        && expressionModel.getExpressionType() != Expression.EXPRESSION_OPERATION_CONCAT
			        && expressionModel.getExpressionType() != Expression.EXPRESSION_OPERATION_INTDIV) {
				exp = new BooleanOperationUI(expressionModel.getParentID(), expressionModel.getScopeID(), expressionModel.getUniqueID());
			} else if (expressionModel.getExpressionType() == Expression.EXPRESSION_OPERATION_CONCAT) {
				exp = new StringOperationUI(expressionModel.getParentID(), expressionModel.getScopeID(), expressionModel.getUniqueID());
			} else {
				exp = new ArithmeticOperationUI(expressionModel.getParentID(), expressionModel.getScopeID(), expressionModel.getUniqueID());
			}
			if (((Operation) expressionModel).getExpressionA() != null && !"".equals(((Operation) expressionModel).getExpressionA())) {
				exp.setExpressionBaseUI_1((JComponent) Services.getService().getViewMapping()
				        .get(((Operation) expressionModel).getExpressionA()));
			}
			((OperationUI) exp).setModelScope(expressionModel.getScopeID());
			Services.getService().getViewMapping().put(expressionModel.getUniqueID(), exp);
			return exp;
		}
	}

	private JComponent renderAttributionLine(AttributionLine attLineModel) {
		AttributionLineUI attLine = new AttributionLineUI(attLineModel.getUniqueID(), attLineModel.getScopeID(), attLineModel.getParentID());
		attLine.setModelParent(attLineModel.getParentID());
		attLine.setModelScope(attLineModel.getScopeID());
		attLine.setLeftVarModelID(attLineModel.getLeftVariableID());
		Services.getService().getViewMapping().put(attLineModel.getUniqueID(), attLine);
		return attLine;
	}

	private JComponent renderWhile(While object) {
		WhileUI w = new WhileUI(object.getUniqueID());
		w.setModelParent(object.getParentID());
		w.setModelScope(object.getScopeID());
		Services.getService().getViewMapping().put(object.getUniqueID(), w);
		return w;
	}

	private JComponent renderFor(For object) {
		ForUI f = new ForUI(object.getUniqueID());
		f.setModelParent(object.getParentID());
		f.setModelScope(object.getScopeID());
		Services.getService().getViewMapping().put(object.getUniqueID(), f);
		return f;
	}

	private JComponent renderIfElse(IfElse object) {
		IfElseUI i = new IfElseUI(object.getUniqueID());
		i.setModelParent(object.getParentID());
		i.setModelScope(object.getScopeID());
		Services.getService().getViewMapping().put(object.getUniqueID(), i);
		return i;
	}
	*/
	public FunctionBodyUI renderFunction(Function f) {
		FunctionBodyUI function;
		if (f.getFunctionName().equals(ResourceBundleIVP.getString("mainFunctionName"))) {
			function = new FunctionBodyUI(f.getUniqueID(), true);
		} else
			function = new FunctionBodyUI(f.getUniqueID(), false);
		// parameters and variables need to be rendered
		function.setName(f.getFunctionName());
		function.setType(f.getFunctionReturnType());
		Services.getService().getViewMapping().addToMap(f.getUniqueID(), function);
		return function;
	}
	
	/*
	private JComponent renderWrite(Print p) {
		PrintUI print = new PrintUI(p.getUniqueID(), p.getParentID(), p.getScopeID());
		Services.getService().getViewMapping().put(p.getUniqueID(), print);
		return print;
	}

	private JComponent renderRead(ReadData r) {
		ReadUI read = new ReadUI(r.getUniqueID(), r.getParentID(), r.getScopeID());
		Services.getService().getViewMapping().put(r.getUniqueID(), read);
		return read;
	}
	 */	
	private JComponent renderVariable(IVPVariable object, String scope) {
		IVPVariableBasic variable = new IVPVariableBasic(object.getUniqueID(), scope);
		variable.setVariableName(object.getVariableName());
		Services.getService().getViewMapping().addToMap(object.getUniqueID(), variable);
		return variable;
	}
	
}