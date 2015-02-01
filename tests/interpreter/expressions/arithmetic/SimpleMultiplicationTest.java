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
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Multiplication;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class SimpleMultiplicationTest {

	@Test
	public void multiplicateIntToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(a.getUniqueID(), 10);
		c.addInt(b.getUniqueID(), 3);

		Multiplication multiplication = factory.createMultiplication();
		multiplication.setExpressionA(a.getUniqueID());
		multiplication.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication.getUniqueID(), multiplication);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) multiplication.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(c.getInt(result.getUniqueID()) == 30);
	}

	@Test
	public void multiplicateIntToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);
		c.addInt(a.getUniqueID(), 10);
		c.addDouble(b.getUniqueID(), 3.4313);

		Multiplication multiplication = factory.createMultiplication();
		multiplication.setExpressionA(a.getUniqueID());
		multiplication.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication.getUniqueID(), multiplication);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) multiplication.evaluate(c, map, factory);
		assertTrue(c.getDouble(result.getUniqueID()) == 34.312999999999995);

	}

	@Test
	public void multiplicateDoubleToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);
		c.addInt(b.getUniqueID(), 10);
		c.addDouble(a.getUniqueID(), 3.4313);

		Multiplication multiplication = factory.createMultiplication();
		multiplication.setExpressionA(a.getUniqueID());
		multiplication.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication.getUniqueID(), multiplication);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		IVPNumber result = (IVPNumber) multiplication.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 34.312999999999995);
	}

	@Test
	public void multiplicateDoubleToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.DOUBLE_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);
		c.addDouble(b.getUniqueID(), 3.4313);
		c.addDouble(a.getUniqueID(), 10.1111);

		Multiplication multiplication = factory.createMultiplication();
		multiplication.setExpressionA(a.getUniqueID());
		multiplication.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication.getUniqueID(), multiplication);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = (IVPNumber) multiplication.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 34.69421743);
	}

}
