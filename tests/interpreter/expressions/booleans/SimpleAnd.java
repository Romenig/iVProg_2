/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.booleans;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Subtraction;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.And;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class SimpleAnd {

	@Test
	public void trueAndTrue() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		c.addBoolean(a.getUniqueID(), new Boolean(true));
		c.addBoolean(b.getUniqueID(), new Boolean(true));

		And and = factory.createAnd();
		and.setExpressionA(a.getUniqueID());
		and.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(and.getUniqueID(), and);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPBoolean result = (IVPBoolean) and.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.BOOLEAN_TYPE));
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void trueAndFalse() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		c.addBoolean(a.getUniqueID(), new Boolean(true));
		c.addBoolean(b.getUniqueID(), new Boolean(false));

		And and = factory.createAnd();
		and.setExpressionA(a.getUniqueID());
		and.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(and.getUniqueID(), and);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPBoolean result = (IVPBoolean) and.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.BOOLEAN_TYPE));
		assertTrue(!c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void falseAndTrue() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		c.addBoolean(a.getUniqueID(), new Boolean(false));
		c.addBoolean(b.getUniqueID(), new Boolean(true));

		And and = factory.createAnd();
		and.setExpressionA(a.getUniqueID());
		and.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(and.getUniqueID(), and);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPBoolean result = (IVPBoolean) and.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.BOOLEAN_TYPE));
		assertTrue(!c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void falseAndFalse() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		c.addBoolean(a.getUniqueID(), new Boolean(false));
		c.addBoolean(b.getUniqueID(), new Boolean(false));

		And and = factory.createAnd();
		and.setExpressionA(a.getUniqueID());
		and.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(and.getUniqueID(), and);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPBoolean result = (IVPBoolean) and.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.BOOLEAN_TYPE));
		assertTrue(!c.getBoolean(result.getUniqueID()));
	}

}
