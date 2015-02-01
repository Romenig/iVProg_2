/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Vector;

import ilm.framework.assignment.model.DomainAction;
import ilm.framework.domain.DomainGUI;
import ilm.framework.domain.DomainModel;

/**
 * @author Romenig
 *
 */
public class IVPDomainGUI extends DomainGUI {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ilm.framework.domain.DomainGUI#initDomainGUI()
	 */
	protected void initDomainGUI() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ilm.framework.domain.DomainGUI#initDomainActionList(ilm.framework.domain.DomainModel)
	 */
	public void initDomainActionList(DomainModel model) {
		_actionList = new HashMap();
	}

	/* (non-Javadoc)
	 * @see ilm.framework.domain.DomainGUI#getSelectedObjects()
	 */
	public Vector getSelectedObjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
