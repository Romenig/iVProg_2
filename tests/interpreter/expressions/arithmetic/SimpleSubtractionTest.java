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
import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Subtraction;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class SimpleSubtractionTest {

	@Test
	public void subtractIntFromInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 10);
		c.addInt(b.getUniqueID(), 3);

		Subtraction subtraction = factory.createSubtraction();
		subtraction.setExpressionA(a.getUniqueID());
		subtraction.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction.getUniqueID(), subtraction);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		subtraction.setOperationResultID(result.getUniqueID());
		
		subtraction.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(c.getInt(result.getUniqueID()) == 7);
	}

	@Test
	public void subtractIntFromDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.DOUBLE_TYPE);

		c.addInt(a.getUniqueID(), 10);
		c.addDouble(b.getUniqueID(), 3.4313);

		Subtraction subtraction = factory.createSubtraction();
		subtraction.setExpressionA(a.getUniqueID());
		subtraction.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction.getUniqueID(), subtraction);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		subtraction.setOperationResultID(result.getUniqueID());
				
		subtraction.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 6.5687);

	}

	@Test
	public void subtractDoubleFromInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 3.4313);
		c.addInt(b.getUniqueID(), 10);

		Subtraction subtraction = factory.createSubtraction();
		subtraction.setExpressionA(a.getUniqueID());
		subtraction.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction.getUniqueID(), subtraction);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		subtraction.setOperationResultID(result.getUniqueID());
		
		subtraction.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == -6.5687);
	}

	@Test
	public void subtractDoubleFromDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();

		b.setValueType(IVPValue.DOUBLE_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 10.1111);
		c.addDouble(b.getUniqueID(), 3.4313);

		Subtraction subtraction = factory.createSubtraction();
		subtraction.setExpressionA(a.getUniqueID());
		subtraction.setExpressionB(b.getUniqueID());

		HashMap map = new HashMap();
		map.put(subtraction.getUniqueID(), subtraction);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		subtraction.setOperationResultID(result.getUniqueID());
		
		subtraction.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 6.6798);

	}
}
