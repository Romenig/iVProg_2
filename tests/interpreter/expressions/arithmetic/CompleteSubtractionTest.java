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
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Subtraction;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class CompleteSubtractionTest {

	@Test
	public void verifyIntegerWithMixedExpression() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		context.addDouble(a.getUniqueID(), 3.4313);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);

		Subtraction subtraction1 = factory.createSubtraction();
		subtraction1.setExpressionA(a.getUniqueID());
		subtraction1.setExpressionB(b.getUniqueID());

		Subtraction subtraction2 = factory.createSubtraction();
		subtraction2.setExpressionA(c.getUniqueID());
		subtraction2.setExpressionB(subtraction1.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction1.getUniqueID(), subtraction1);
		map.put(subtraction2.getUniqueID(), subtraction2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// subtraction2(c - subtraction1(a - b))
		// c - (a - b)
		IVPNumber result = (IVPNumber) subtraction2.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(context.getDouble(result.getUniqueID()) == 8.5687);

	}

	@Test
	public void verifyExpressionWithInteger() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);

		context.addInt(a.getUniqueID(), 7);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);

		Subtraction subtraction1 = factory.createSubtraction();
		subtraction1.setExpressionA(a.getUniqueID());
		subtraction1.setExpressionB(b.getUniqueID());

		Subtraction subtraction2 = factory.createSubtraction();
		subtraction2.setExpressionA(c.getUniqueID());
		subtraction2.setExpressionB(subtraction1.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction1.getUniqueID(), subtraction1);
		map.put(subtraction2.getUniqueID(), subtraction2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// subtraction2(c - subtraction1(a - b))
		// c - (a - b)
		IVPNumber result = (IVPNumber) subtraction2.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result.getUniqueID()) == 5);
	}

	@Test
	public void verifyExpressionWithExpression() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPNumber d = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		d.setValueType(IVPValue.INTEGER_TYPE);

		context.addInt(a.getUniqueID(), 7);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);
		context.addInt(d.getUniqueID(), 2);

		Subtraction subtraction1 = factory.createSubtraction();
		subtraction1.setExpressionA(a.getUniqueID());
		subtraction1.setExpressionB(b.getUniqueID());

		Subtraction subtraction2 = factory.createSubtraction();
		subtraction2.setExpressionA(c.getUniqueID());
		subtraction2.setExpressionB(d.getUniqueID());

		Subtraction subtraction3 = factory.createSubtraction();
		subtraction3.setExpressionA(subtraction1.getUniqueID());
		subtraction3.setExpressionB(subtraction2.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction1.getUniqueID(), subtraction1);
		map.put(subtraction2.getUniqueID(), subtraction2);
		map.put(subtraction3.getUniqueID(), subtraction3);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(d.getUniqueID(), d);
		// subtraction3(subtraction1(a - b) - subtraction2(c - d))
		// ((a - b) - (c - d))
		IVPNumber result = (IVPNumber) subtraction3.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result.getUniqueID()) == -3);
	}

}
