/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter.error;

import ilm.framework.assignment.model.DomainObject;

import java.util.HashMap;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;

public class IVPError extends DataObject {

	private String message;

	public IVPError() {
		super("IVPError", "Error class");
	}

	public IVPError(String error) {
		super("IVPError", "Error class");
		message = error;
	}

	public String getErrorMessage() {
		return message;
	}

	public void setErrorMessage(String m) {
		message = m;
	}

	public Object evaluate(Context c, HashMap map, DataFactory factory) {
		return this;
	}

	/* (non-Javadoc)
	 * @see ilm.framework.assignment.model.DomainObject#equals(ilm.framework.assignment.model.DomainObject)
	 */
    @Override
    public boolean equals(DomainObject o) {
	    return false;
    }

}
