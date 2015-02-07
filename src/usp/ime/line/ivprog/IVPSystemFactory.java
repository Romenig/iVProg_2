/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog;

import java.util.HashMap;
import java.util.Vector;

import usp.ime.line.ivprog.model.IVPDomainConverter;
import usp.ime.line.ivprog.model.IVPDomainModel;
import usp.ime.line.ivprog.view.domaingui.IVPAuthoringGUI;
import usp.ime.line.ivprog.view.domaingui.IVPDomainGUI;
import ilm.framework.SystemFactory;
import ilm.framework.assignment.model.AssignmentState;
import ilm.framework.config.SystemConfig;
import ilm.framework.domain.DomainConverter;
import ilm.framework.domain.DomainGUI;
import ilm.framework.domain.DomainModel;
import ilm.framework.gui.AuthoringGUI;

/**
 * @author Romenig
 * 
 */
public class IVPSystemFactory extends SystemFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.SystemFactory#createDomainModel()
	 */
	protected DomainModel createDomainModel() {
		IVPDomainModel model = new IVPDomainModel();
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ilm.framework.SystemFactory#createDomainConverter()
	 */
	protected DomainConverter createDomainConverter() {
		IVPDomainConverter converter = new IVPDomainConverter();
		return converter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.SystemFactory#createDomainGUI(ilm.framework.config.SystemConfig
	 * , ilm.framework.domain.DomainModel)
	 */
	public DomainGUI createDomainGUI(SystemConfig config, DomainModel domainModel) {
		IVPDomainGUI gui = new IVPDomainGUI();
		gui.initDomainActionList(domainModel);
		return gui;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ilm.framework.SystemFactory#createAuthoringGUI(ilm.framework.domain.DomainGUI
	 * , java.lang.String, ilm.framework.assignment.model.AssignmentState,
	 * ilm.framework.assignment.model.AssignmentState,
	 * ilm.framework.assignment.model.AssignmentState, java.util.HashMap,
	 * java.util.HashMap)
	 */
	public AuthoringGUI createAuthoringGUI(DomainGUI domainGUI, String proposition, AssignmentState initial, AssignmentState current,
	        AssignmentState expected, HashMap config, HashMap metadata) {
		IVPAuthoringGUI gui = new IVPAuthoringGUI();
		return gui;
	}

	protected Vector getIlmModuleList() {
		Vector list = new Vector();
		// list.add(new ScriptModule());
		// list.add(new ExampleTracingTutorModule());
		// list.add(new ScormModule());
		return list;
	}
}
