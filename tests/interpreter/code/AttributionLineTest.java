/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.code;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.AttributionLine;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.And;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVariableReference;

public class AttributionLineTest {

	@Test
	public void varReceivesInteger() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPNumber n1 = factory.createIVPNumber();
		n1.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(n1.getUniqueID(), 10);

		IVPNumber n2 = factory.createIVPNumber();
		n2.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(n2.getUniqueID(), 31);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(n1.getUniqueID());


		
		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setExpression(n2.getUniqueID());

		HashMap map = new HashMap();
		map.put(v.getUniqueID(), v);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);

		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());

		assertTrue(c.getInt(v.getValueID()) == 10);
		attLine.evaluate(c, map, factory);
		assertTrue(c.getInt(v.getValueID()) == 31);
	}

	@Test
	public void varReceivesDouble() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPNumber n1 = factory.createIVPNumber();
		n1.setValueType(IVPValue.DOUBLE_TYPE);
		c.addDouble(n1.getUniqueID(), 10.37);

		IVPNumber n2 = factory.createIVPNumber();
		n2.setValueType(IVPValue.DOUBLE_TYPE);
		c.addDouble(n2.getUniqueID(), 31.33333);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.DOUBLE_TYPE);
		v.setValueID(n1.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setExpression(n2.getUniqueID());

		HashMap map = new HashMap();
		map.put(v.getUniqueID(), v);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());

		assertTrue(c.getDouble(v.getValueID()) == 10.37);
		attLine.evaluate(c, map, factory);
		assertTrue(c.getDouble(v.getValueID()) == 31.33333);
	}

	@Test
	public void varReceivesString() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPString n1 = factory.createIVPString();
		n1.setValueType(IVPValue.STRING_TYPE);
		c.addString(n1.getUniqueID(), "Test 1");

		IVPString n2 = factory.createIVPString();
		n2.setValueType(IVPValue.STRING_TYPE);
		c.addString(n2.getUniqueID(), "Test 2");

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.STRING_TYPE);
		v.setValueID(n1.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setExpression(n2.getUniqueID());

		HashMap map = new HashMap();
		map.put(v.getUniqueID(), v);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());

		assertTrue(c.getString(v.getValueID()).equals("Test 1"));
		attLine.evaluate(c, map, factory);
		assertTrue(c.getString(v.getValueID()).equals("Test 2"));
	}

	@Test
	public void varReceivesBoolean() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPBoolean n1 = factory.createIVPBoolean();
		n1.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n1.getUniqueID(), new Boolean("true"));

		IVPBoolean n2 = factory.createIVPBoolean();
		n2.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n2.getUniqueID(), new Boolean("false"));

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.BOOLEAN_TYPE);
		v.setValueID(n1.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setExpression(n2.getUniqueID());

		HashMap map = new HashMap();
		map.put(v.getUniqueID(), v);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);

		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());
		
		assertTrue(c.getBoolean(v.getValueID()));
		attLine.evaluate(c, map, factory);
		assertFalse(c.getBoolean(v.getValueID()));
	}

	@Test
	public void varReceivesVariable() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPBoolean n1 = factory.createIVPBoolean();
		n1.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n1.getUniqueID(), new Boolean("true"));

		IVPBoolean n2 = factory.createIVPBoolean();
		n2.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n2.getUniqueID(), new Boolean("false"));

		IVPVariable v1 = factory.createIVPVariable();
		v1.setVariableType(IVPValue.BOOLEAN_TYPE);
		v1.setValueID(n1.getUniqueID());

		IVPVariable v2 = factory.createIVPVariable();
		v2.setVariableType(IVPValue.BOOLEAN_TYPE);
		v2.setValueID(n2.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		

		HashMap map = new HashMap();
		map.put(v1.getUniqueID(), v1);
		map.put(v2.getUniqueID(), v2);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v1.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());

		IVPVariableReference ref2 = factory.createIVPVariableReference();
		ref2.setReferencedID(v2.getUniqueID());
		map.put(ref2.getUniqueID(), ref2);
		attLine.setExpression(ref2.getUniqueID());
		
		assertTrue(c.getBoolean(v1.getValueID()));
		attLine.evaluate(c, map, factory);
		assertFalse(c.getBoolean(v1.getValueID()));
	}

	@Test
	public void varReceivesBooleanExpression() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPBoolean n1 = factory.createIVPBoolean();
		n1.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n1.getUniqueID(), new Boolean("true"));

		IVPBoolean n2 = factory.createIVPBoolean();
		n2.setValueType(IVPValue.BOOLEAN_TYPE);
		c.addBoolean(n2.getUniqueID(), new Boolean("false"));

		IVPVariable v1 = factory.createIVPVariable();
		v1.setVariableType(IVPValue.BOOLEAN_TYPE);
		v1.setValueID(n1.getUniqueID());

		And and = factory.createAnd();
		and.setExpressionA(n1.getUniqueID());
		and.setExpressionB(n2.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setExpression(and.getUniqueID());

		HashMap map = new HashMap();
		map.put(and.getUniqueID(), and);
		map.put(v1.getUniqueID(), v1);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);
		
		IVPBoolean result = factory.createIVPBoolean();
		map.put(result.getUniqueID(), result);
		and.setOperationResultID(result.getUniqueID());
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v1.getUniqueID());
		map.put(ref.getUniqueID(), ref);
		attLine.setVariableID(ref.getUniqueID());
		
		assertTrue(c.getBoolean(v1.getValueID()));
		attLine.evaluate(c, map, factory);
		assertFalse(c.getBoolean(v1.getValueID()));
	}

	@Test
	public void varReceivesIntegerExpression() {
		Context c = new Context();
		DataFactory factory = new DataFactory();

		IVPNumber n1 = factory.createIVPNumber();
		n1.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(n1.getUniqueID(), 10);

		IVPNumber n2 = factory.createIVPNumber();
		n2.setValueType(IVPValue.INTEGER_TYPE);
		c.addInt(n2.getUniqueID(), 31);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(n1.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(n1.getUniqueID());
		add.setExpressionB(n2.getUniqueID());

		// Supondo variável já inicializada com o valor 10.

		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());
		
		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariableID(ref.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(add.getUniqueID(), add);
		map.put(v.getUniqueID(), v);
		map.put(n1.getUniqueID(), n1);
		map.put(n2.getUniqueID(), n2);
		map.put(attLine.getUniqueID(), attLine);
		map.put(ref.getUniqueID(), ref);
		
		IVPNumber result = factory.createIVPNumber();
		map.put(result.getUniqueID(), result);
		add.setOperationResultID(result.getUniqueID());

		assertTrue(c.getInt(v.getValueID()) == 10);
		attLine.evaluate(c, map, factory);
		assertTrue(c.getInt(v.getValueID()) == 41);
	}

}
;