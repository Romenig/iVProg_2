/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.arithmetic;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Division;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class CompleteDivisionTest {

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

		Division division1 = (Division) factory.createDivision();
		division1.setExpressionA(a.getUniqueID());
		division1.setExpressionB(b.getUniqueID());

		Division division2 = (Division) factory.createDivision();
		division2.setExpressionA(c.getUniqueID());
		division2.setExpressionB(division1.getUniqueID());

		HashMap map = new HashMap();
		map.put(division1.getUniqueID(), division1);
		map.put(division2.getUniqueID(), division2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// division2(c / division1(a / b))
		// c / (a / b)

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Division)division1).setOperationResultID(result.getUniqueID());
		
		IVPNumber result2 = factory.createIVPNumber();
		map.put(result2.getUniqueID(), result2);
		((Division)division2).setOperationResultID(result2.getUniqueID());
		
		division2.evaluate(context, map, factory);
		
		assertTrue(result2.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(context.getDouble(result2.getUniqueID()) == 5.828694663830035);
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

		Division division1 = (Division) factory.createDivision();
		division1.setExpressionA(a.getUniqueID());
		division1.setExpressionB(b.getUniqueID());

		Division division2 = (Division) factory.createDivision();
		division2.setExpressionA(division1.getUniqueID());
		division2.setExpressionB(c.getUniqueID());

		HashMap map = new HashMap();
		map.put(division1.getUniqueID(), division1);
		map.put(division2.getUniqueID(), division2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// division2(c / division1(a / b))
		// c / (a / b)
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Division)division1).setOperationResultID(result.getUniqueID());
		
		IVPNumber result2 = factory.createIVPNumber();
		map.put(result2.getUniqueID(), result2);
		((Division)division2).setOperationResultID(result2.getUniqueID());
		division2.evaluate(context, map, factory);
		
		assertTrue(result2.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(result.getUniqueID()) == 0);
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

		Division division1 = (Division) factory.createDivision();
		division1.setExpressionA(a.getUniqueID());
		division1.setExpressionB(b.getUniqueID());

		Division division2 = (Division) factory.createDivision();
		division2.setExpressionA(c.getUniqueID());
		division2.setExpressionB(d.getUniqueID());

		Division division3 = (Division) factory.createDivision();
		division3.setExpressionA(division1.getUniqueID());
		division3.setExpressionB(division2.getUniqueID());

		HashMap map = new HashMap();
		map.put(division1.getUniqueID(), division1);
		map.put(division2.getUniqueID(), division2);
		map.put(division3.getUniqueID(), division3);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(d.getUniqueID(), d);
		// division3(division1(a / b) / division2(c / d))
		// ((a / b) / (c / d))
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Division)division1).setOperationResultID(result.getUniqueID());
		
		IVPNumber result2 = factory.createIVPNumber();
		map.put(result2.getUniqueID(), result2);
		((Division)division2).setOperationResultID(result2.getUniqueID());
		
		IVPNumber result3 = factory.createIVPNumber();
		map.put(result3.getUniqueID(), result3);
		((Division)division3).setOperationResultID(result3.getUniqueID());
		
		division3.evaluate(context, map, factory);
		
		assertTrue(result3.getValueType().equals(IVPValue.INTEGER_TYPE));
		
		assertTrue(context.getInt(result3.getUniqueID()) == 0);
	}

}
