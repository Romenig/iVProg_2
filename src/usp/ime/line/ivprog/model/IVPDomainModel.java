/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.model;

import ilm.framework.assignment.model.AssignmentState;
import ilm.framework.domain.DomainModel;

/**
 * @author Romenig
 * 
 */
public class IVPDomainModel extends DomainModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.domain.DomainModel#getNewAssignmentState()
	 */
	public AssignmentState getNewAssignmentState() {
		// TODO Auto-generated method stub
		AssignmentState assignment = new AssignmentState();
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
		// TODO Auto-generated method stub
		return 0;
	}

}
