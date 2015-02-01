/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.arithmetic;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Division;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class SimpleDivisionTest {

	@Test
	public void divideIntToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 10);
		c.addInt(b.getUniqueID(), 2);

		Division division = factory.createDivision();
		division.setExpressionA(a.getUniqueID());
		division.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(division.getUniqueID(), division);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) division.evaluate(c, map, factory);

		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(c.getInt(result.getUniqueID()) == 5);
	}

	@Test
	public void divideIntToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addInt(a.getUniqueID(), 6);
		c.addDouble(b.getUniqueID(), 1.5);

		Division division = factory.createDivision();
		division.setExpressionA(a.getUniqueID());
		division.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(division.getUniqueID(), division);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) division.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 4.0);

	}

	@Test
	public void divideDoubleToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 1.5);
		c.addInt(b.getUniqueID(), 2);

		Division division = factory.createDivision();
		division.setExpressionA(a.getUniqueID());
		division.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(division.getUniqueID(), division);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) division.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 0.75);
	}

	@Test
	public void divideDoubleToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.DOUBLE_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 2.5);
		c.addDouble(b.getUniqueID(), 0.25);

		Division division = factory.createDivision();
		division.setExpressionA(a.getUniqueID());
		division.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(division.getUniqueID(), division);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) division.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 10.0);

	}
}
