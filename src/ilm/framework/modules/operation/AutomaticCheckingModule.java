package ilm.framework.modules.operation;

import ilm.framework.IlmProtocol;
import ilm.framework.assignment.IAssignment;
import ilm.framework.assignment.IAssignmentOperator;
import ilm.framework.domain.DomainModel;
import ilm.framework.modules.OperationModule;

import java.util.Vector;

public class AutomaticCheckingModule extends OperationModule {

	private DomainModel _model;

	public AutomaticCheckingModule(IAssignment assignments, IAssignmentOperator operator) {
		setAssignmentList(assignments);
		setAssignmentOperator(operator);
		_name = IlmProtocol.AUTO_CHECKING_MODULE_NAME;
		_gui = new AutoCheckingModuleToolbar(this);
	}

	public float getEvaluation() {
		if (_assignmentList.getExpectedAnswer(_assignmentIndex) == null) {
			return 0;
		}
		return _model.AutomaticChecking(_assignmentList.getCurrentState(_assignmentIndex),
		        _assignmentList.getExpectedAnswer(_assignmentIndex));
	}

	public String getAnswer() {
		Vector list = _assignmentList.getCurrentState(_assignmentIndex).getList();
		return _operator.getConverter().convertObjectToString(list);
	}

	public void setModel(DomainModel model) {
		_model = model;
	}

	public void print() {
	}

	public boolean hasExpectedAnswer() {
		if (_assignmentList.getExpectedAnswer(_assignmentIndex) == null) {
			return false;
		}
		if (_assignmentList.getExpectedAnswer(_assignmentIndex).getList().size() < 1) {
			return false;
		}
		return true;
	}

}
