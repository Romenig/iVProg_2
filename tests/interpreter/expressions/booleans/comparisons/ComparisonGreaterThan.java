/** 
 * Instituto de Matem�tica e Estat�stica da Universidade de S�o Paulo (IME-USP)
 * iVProg is a open source and free software of Laborat�rio de Inform�tica na 
 * Educa��o (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Le�nidas de Oliveira Brand�o - leo@ime.usp.br
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
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.GreaterThan;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class ComparisonGreaterThan {

	@Test
	public void testGreaterThanForIntNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		GreaterThan gt = factory.createGreaterThan();
		gt.setExpressionA(a.getUniqueID());
		gt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 30);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(gt.getUniqueID(), gt);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		gt.setOperationResultID(result.getUniqueID());

		gt.evaluate(c, map, factory);
		
		
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateIntegerValue(c, 30);
		result = (IVPBoolean) gt.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testGreaterThanForDoubleNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		GreaterThan gt = factory.createGreaterThan();
		gt.setExpressionA(a.getUniqueID());
		gt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 3.0002);
		c.addDouble(b.getUniqueID(), 3.0001);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(gt.getUniqueID(), gt);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		gt.setOperationResultID(result.getUniqueID());
		
		gt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateDoubleValue(c, 4.0);
		result = (IVPBoolean) gt.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testGreaterThanForIntAndDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		GreaterThan gt = factory.createGreaterThan();
		gt.setExpressionA(a.getUniqueID());
		gt.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(gt.getUniqueID(), gt);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		gt.setOperationResultID(result.getUniqueID());

		gt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateIntegerValue(c, 2);
		result = (IVPBoolean) gt.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

}
