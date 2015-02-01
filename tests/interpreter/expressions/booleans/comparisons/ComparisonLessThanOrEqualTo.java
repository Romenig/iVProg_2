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
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class ComparisonLessThanOrEqualTo {

	@Test
	public void testEqualForIntNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		LessThanOrEqualTo lt = factory.createLessThanOrEqualTo();
		lt.setExpressionA(a.getUniqueID());
		lt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 3);
		c.addInt(b.getUniqueID(), 30);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(lt.getUniqueID(), lt);

		IVPBoolean result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateIntegerValue(c, 3);
		result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));

	}

	@Test
	public void testEqualForDoubleNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		LessThanOrEqualTo lt = factory.createLessThanOrEqualTo();
		lt.setExpressionA(a.getUniqueID());
		lt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addDouble(b.getUniqueID(), 3.0002);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(lt.getUniqueID(), lt);

		IVPBoolean result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateDoubleValue(c, 3.0001);
		result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testEqualForIntAndDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		LessThanOrEqualTo lt = factory.createLessThanOrEqualTo();
		lt.setExpressionA(a.getUniqueID());
		lt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(lt.getUniqueID(), lt);

		IVPBoolean result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
		b.updateDoubleValue(c, 3.0001);
		result = (IVPBoolean) lt.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

}
