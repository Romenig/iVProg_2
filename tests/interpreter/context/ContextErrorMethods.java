/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.context;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.language.Messages;

public class ContextErrorMethods {

	@Test
	public void verifyNumberErrorMessages() {
		Context c = new Context();
		IVPError e1 = c.addInt("key1", 10);
		IVPError e2 = c.addInt("key1", 11);
		assertTrue(e2.getErrorMessage().equals(Messages.getString("Context.keyExists")));
		assertTrue(e1 == null);
	}

	@Test
	public void verifyStringErrorMessages() {
		Context c = new Context();
		IVPError e1 = c.addString("key1", "Hello, world!");
		IVPError e2 = c.addString("key1", "Hello, world 2!");
		assertTrue(e2.getErrorMessage().equals(Messages.getString("Context.keyExists")));
		assertTrue(e1 == null);
	}

	@Test
	public void verifyBooleanErrorMessages() {
		Context c = new Context();
		IVPError e1 = c.addBoolean("key1", new Boolean("true"));
		IVPError e2 = c.addBoolean("key1", new Boolean("true"));
		assertTrue(e2.getErrorMessage().equals(Messages.getString("Context.keyExists")));
		assertTrue(e1 == null);
	}

}
