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
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class SimpleAdditionTestWithVariable {

	/*
	 * Bastam estes testes para verificar se as vari�veis num�ricas est�o
	 * funcionando.
	 */

	@Test
	public void addIntToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b_var = factory.createIVPNumber();
		IVPVariable v = factory.createIVPVariable();
		v.setValueID(b_var.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b_var.setValueType(IVPValue.INTEGER_TYPE);
		v.setVariableType(IVPValue.INTEGER_TYPE);

		c.addInt(a.getUniqueID(), 10);
		c.addInt(b_var.getUniqueID(), 3);

		Addition addition = factory.createAddition();
		addition.setExpressionA(a.getUniqueID());
		addition.setExpressionB(v.getUniqueID());

		HashMap map = new HashMap();
		map.put(addition.getUniqueID(), addition);
		map.put(a.getUniqueID(), a);
		map.put(b_var.getUniqueID(), b_var);
		map.put(v.getUniqueID(), v);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Addition)addition).setOperationResultID(result.getUniqueID());
		addition.evaluate(c, map, factory);
		
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(c.getInt(result.getUniqueID()) == 13);
	}

	@Test
	public void addIntToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b_var = factory.createIVPNumber();
		IVPVariable v = factory.createIVPVariable();
		v.setValueID(b_var.getUniqueID());

		a.setValueType(IVPValue.INTEGER_TYPE);
		b_var.setValueType(IVPValue.DOUBLE_TYPE);
		v.setVariableType(IVPValue.DOUBLE_TYPE);

		c.addInt(a.getUniqueID(), 10);
		c.addDouble(b_var.getUniqueID(), 3.4313);

		Addition addition = factory.createAddition();
		addition.setExpressionA(a.getUniqueID());
		addition.setExpressionB(v.getUniqueID());

		HashMap map = new HashMap();
		map.put(addition.getUniqueID(), addition);
		map.put(a.getUniqueID(), a);
		map.put(b_var.getUniqueID(), b_var);
		map.put(v.getUniqueID(), v);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Addition)addition).setOperationResultID(result.getUniqueID());
		addition.evaluate(c, map, factory);
		
		addition.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 13.4313);

	}

	@Test
	public void addDoubleToInt() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b_var = factory.createIVPNumber();
		IVPVariable v = factory.createIVPVariable();
		v.setValueID(b_var.getUniqueID());

		b_var.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);
		v.setVariableType(IVPValue.INTEGER_TYPE);

		c.addDouble(a.getUniqueID(), 3.4313);
		c.addInt(b_var.getUniqueID(), 10);

		Addition addition = factory.createAddition();
		addition.setExpressionA(a.getUniqueID());
		addition.setExpressionB(b_var.getUniqueID());

		HashMap map = new HashMap();
		map.put(addition.getUniqueID(), addition);
		map.put(a.getUniqueID(), a);
		map.put(b_var.getUniqueID(), b_var);
		map.put(v.getUniqueID(), v);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Addition)addition).setOperationResultID(result.getUniqueID());
		addition.evaluate(c, map, factory);
		
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 13.4313);
	}

	@Test
	public void addDoubleToDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b_var = factory.createIVPNumber();
		IVPVariable v = factory.createIVPVariable();
		v.setValueID(b_var.getUniqueID());

		b_var.setValueType(IVPValue.DOUBLE_TYPE);
		a.setValueType(IVPValue.DOUBLE_TYPE);
		v.setVariableType(IVPValue.DOUBLE_TYPE);

		c.addDouble(a.getUniqueID(), 10.1111);
		c.addDouble(b_var.getUniqueID(), 3.4313);

		Addition addition = factory.createAddition();
		addition.setExpressionA(a.getUniqueID());
		addition.setExpressionB(b_var.getUniqueID());

		HashMap map = new HashMap();
		map.put(addition.getUniqueID(), addition);
		map.put(a.getUniqueID(), a);
		map.put(b_var.getUniqueID(), b_var);
		map.put(v.getUniqueID(), v);

		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		((Addition)addition).setOperationResultID(result.getUniqueID());
		addition.evaluate(c, map, factory);
		
		assertTrue(result.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(c.getDouble(result.getUniqueID()) == 13.5424);
	}
}
