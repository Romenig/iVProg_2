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
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.NotEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class ComparisonNotEqualTo {

	@Test
	public void testNotEqualForIntNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		NotEqualTo neq = factory.createNotEqualTo();
		neq.setExpressionA(a.getUniqueID());
		neq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 3);
		c.addInt(b.getUniqueID(), 2);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(neq.getUniqueID(), neq);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		neq.setOperationResultID(result.getUniqueID());
		
		neq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testNotEqualForDoubleNumbers() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		NotEqualTo neq = factory.createNotEqualTo();
		neq.setExpressionA(a.getUniqueID());
		neq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 3.0002);
		c.addDouble(b.getUniqueID(), 3.0001);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(neq.getUniqueID(), neq);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		neq.setOperationResultID(result.getUniqueID());
		
		neq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testNotEqualForIntAndDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		NotEqualTo neq = factory.createNotEqualTo();
		neq.setExpressionA(a.getUniqueID());
		neq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addDouble(a.getUniqueID(), 3.0001);
		c.addInt(b.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(neq.getUniqueID(), neq);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		neq.setOperationResultID(result.getUniqueID());
		
		neq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testNotEqualForStrings() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		IVPString b = factory.createIVPString();
		NotEqualTo neq = factory.createNotEqualTo();
		neq.setExpressionA(a.getUniqueID());
		neq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.STRING_TYPE);
		b.setValueType(IVPValue.STRING_TYPE);

		c.addString(a.getUniqueID(), "hello");
		c.addString(b.getUniqueID(), "hello1");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(neq.getUniqueID(), neq);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		neq.setOperationResultID(result.getUniqueID());
		
		neq.evaluate(c, map, factory);
		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateValue(c, "hello");
		result = (IVPBoolean) neq.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

	@Test
	public void testNotEqualForBooleans() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPBoolean a = factory.createIVPBoolean();
		IVPBoolean b = factory.createIVPBoolean();

		NotEqualTo neq = factory.createNotEqualTo();
		neq.setExpressionA(a.getUniqueID());
		neq.setExpressionB(b.getUniqueID());

		a.setValueType(IVPValue.BOOLEAN_TYPE);
		b.setValueType(IVPValue.BOOLEAN_TYPE);

		c.addBoolean(a.getUniqueID(), new Boolean(true));
		c.addBoolean(b.getUniqueID(), new Boolean(false));

		HashMap map = new HashMap();

		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(neq.getUniqueID(), neq);

		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		neq.setOperationResultID(result.getUniqueID());
		
		neq.evaluate(c, map, factory);

		assertTrue(c.getBoolean(result.getUniqueID()));
		b.updateValue(c, new Boolean(true));
		neq.evaluate(c, map, factory);
		assertFalse(c.getBoolean(result.getUniqueID()));
	}

}
