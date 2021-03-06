/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
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
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Division;
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
		IVPNumber result1 = factory.createIVPNumber();
		map.put(result1.getUniqueID(), result1);
		((Multiplication)multiplication1).setOperationResultID(result1.getUniqueID());
		
		IVPNumber result2 = factory.createIVPNumber();
		map.put(result2.getUniqueID(), result2);
		((Multiplication)multiplication2).setOperationResultID(result2.getUniqueID());
		
		multiplication2.evaluate(context, map, factory);
		
		assertTrue(result2.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(context.getDouble(result2.getUniqueID()) == 68.62599999999999);
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
		IVPNumber result1 = (IVPNumber) factory.createIVPNumber();
		IVPNumber result2 = (IVPNumber) factory.createIVPNumber();
		map.put(result1.getUniqueID(), result1);
		map.put(result2.getUniqueID(), result2);
		multiplication1.setOperationResultID(result1.getUniqueID());
		multiplication2.setOperationResultID(result2.getUniqueID());
		
		
		multiplication2.evaluate(context, map, factory);
		assertTrue(result2.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result2.getUniqueID()) == 140);
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
		IVPNumber result1 = (IVPNumber) factory.createIVPNumber();
		IVPNumber result2 = (IVPNumber) factory.createIVPNumber();
		IVPNumber result3 = (IVPNumber) factory.createIVPNumber();
		map.put(result1.getUniqueID(), result1);
		map.put(result2.getUniqueID(), result2);
		map.put(result3.getUniqueID(), result3);
		multiplication1.setOperationResultID(result1.getUniqueID());
		multiplication2.setOperationResultID(result2.getUniqueID());
		multiplication3.setOperationResultID(result3.getUniqueID());
				
		multiplication3.evaluate(context, map, factory);
		assertTrue(result3.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result3.getUniqueID()) == 280);
	}

}
