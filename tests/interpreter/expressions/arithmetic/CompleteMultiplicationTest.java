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

public class CompleteMultiplicationTest {

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

		Multiplication multiplication1 = factory.createMultiplication();
		multiplication1.setExpressionA(a.getUniqueID());
		multiplication1.setExpressionB(b.getUniqueID());

		Multiplication multiplication2 = factory.createMultiplication();
		multiplication2.setExpressionA(c.getUniqueID());
		multiplication2.setExpressionB(multiplication1.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication1.getUniqueID(), multiplication1);
		map.put(multiplication2.getUniqueID(), multiplication2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// multiplication2(c * multiplication1(a * b))
		// c * (a * b)
		IVPNumber result = (IVPNumber) multiplication2.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		System.out.println(context.getDouble(result.getUniqueID()));
		assertTrue(context.getDouble(result.getUniqueID()) == 68.62599999999999);

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

		Multiplication multiplication1 = factory.createMultiplication();
		multiplication1.setExpressionA(a.getUniqueID());
		multiplication1.setExpressionB(b.getUniqueID());

		Multiplication multiplication2 = factory.createMultiplication();
		multiplication2.setExpressionA(c.getUniqueID());
		multiplication2.setExpressionB(multiplication1.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication1.getUniqueID(), multiplication1);
		map.put(multiplication2.getUniqueID(), multiplication2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// multiplication2(c * multiplication1(a * b))
		// c * (a * b)
		IVPNumber result = (IVPNumber) multiplication2.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result.getUniqueID()) == 140);
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

		Multiplication multiplication1 = factory.createMultiplication();
		multiplication1.setExpressionA(a.getUniqueID());
		multiplication1.setExpressionB(b.getUniqueID());

		Multiplication multiplication2 = factory.createMultiplication();
		multiplication2.setExpressionA(c.getUniqueID());
		multiplication2.setExpressionB(d.getUniqueID());

		Multiplication multiplication3 = factory.createMultiplication();
		multiplication3.setExpressionA(multiplication1.getUniqueID());
		multiplication3.setExpressionB(multiplication2.getUniqueID());

		HashMap map = new HashMap();
		map.put(multiplication1.getUniqueID(), multiplication1);
		map.put(multiplication2.getUniqueID(), multiplication2);
		map.put(multiplication3.getUniqueID(), multiplication3);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(d.getUniqueID(), d);
		// multiplication3(multiplication1(a * b) * multiplication2(c * d))
		// ((a * b) * (c * d))
		IVPNumber result = (IVPNumber) multiplication3.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result.getUniqueID()) == 280);
	}

}
