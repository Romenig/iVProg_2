/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.error;

import static org.junit.Assert.*;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.error.IVPError;

public class CreateErrorMessage {

	@Test
	public void setAndGetErrorMessage() {
		IVPError e = new IVPError();
		e.setErrorMessage("An error message");
		assertTrue("An error message".equals(e.getErrorMessage()));
	}

}
