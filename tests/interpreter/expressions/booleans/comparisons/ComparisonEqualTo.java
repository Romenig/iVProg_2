/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.booleans.comparisons;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.EqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class ComparisonEqualTo {

	@Test
	public void testEqualForIntNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(a.getUniqueID());
		eq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 3);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(eq.getUniqueID(), eq);

		IVPBoolean result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testEqualForDoubleNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(a.getUniqueID());
		eq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addDouble(b.getUniqueID(), 3.0001);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(eq.getUniqueID(), eq);

		IVPBoolean result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testEqualForIntAndDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(a.getUniqueID());
		eq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(eq.getUniqueID(), eq);

		IVPBoolean result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testEqualForStrings() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		IVPString b = factory.createIVPString();
		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(a.getUniqueID());
		eq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.STRING_TYPE);
		b.setValueType(IVPValue.STRING_TYPE);

		c.addString(a.getUniqueID(), "hello");
		c.addString(b.getUniqueID(), "hello1");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(eq.getUniqueID(), eq);

		IVPBoolean result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
		b.updateValue(c, "hello");
		result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testEqualForBooleans() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(a.getUniqueID());
		eq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.BOOLEAN_TYPE);
		b.setValueType(IVPValue.BOOLEAN_TYPE);

		c.addBoolean(a.getUniqueID(), new Boolean(true));
		c.addBoolean(b.getUniqueID(), new Boolean(false));

		HashMap map = new HashMap();

		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(eq.getUniqueID(), eq);

		IVPBoolean result = (IVPBoolean) eq.evaluate(c, map, factory);

		assertFalse(c.getBoolean(result.getUniqueID()));
		b.updateValue(c, new Boolean(true));
		result = (IVPBoolean) eq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

}
