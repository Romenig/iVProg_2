/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.utils.vector;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.AttributionLine;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVector;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVectorReference;

public class IVPVectorReferenceTest {

	@Test
	public void setVectorElementThroughReference() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		Addition add = factory.createAddition();
		IVPNumber size = factory.createIVPNumber();
		IVPNumber position = factory.createIVPNumber();
		IVPVector vect = factory.createIVPVector();
		AttributionLine attLine = factory.createAttributionLine();
		IVPVectorReference ref = factory.createIVPVectorReference();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		position.setValueType(IVPValue.INTEGER_TYPE);
		size.setValueType(IVPValue.INTEGER_TYPE);

		context.addInt(size.getUniqueID(), 10);
		context.addInt(a.getUniqueID(), 1);
		context.addInt(b.getUniqueID(), 2);
		context.addInt(position.getUniqueID(), 2);

		vect.setSize(size.getUniqueID(), context);
		vect.setType(IVPValue.INTEGER_TYPE);

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(position.getUniqueID(), position);
		map.put(add.getUniqueID(), add);
		map.put(ref.getUniqueID(), ref);
		map.put(vect.getUniqueID(), vect);

		ref.setVectorID(vect.getUniqueID());
		ref.setPositionID(position.getUniqueID());

		add.setExpressionA(a.getUniqueID());
		add.setExpressionB(b.getUniqueID());

		attLine.setVariable(ref.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		attLine.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) ref.evaluate(context, map, factory);

		assertTrue(context.getInt(result.getUniqueID()) == 3);

	}

	@Test
	public void getVectorElementThroughReference() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPVariable var = factory.createIVPVariable();
		IVPVector vect = factory.createIVPVector();
		AttributionLine attLine = factory.createAttributionLine();
		IVPVectorReference ref = factory.createIVPVectorReference();

		IVPNumber n1 = factory.createIVPNumber();
		n1.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(n1.getUniqueID(), 1);

		IVPNumber position = factory.createIVPNumber();
		position.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(position.getUniqueID(), 2);

		IVPNumber size = factory.createIVPNumber();
		size.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(size.getUniqueID(), 10);

		var.setValueID(n1.getUniqueID());
		var.setVariableType(IVPValue.INTEGER_TYPE);

		vect.setSize(size.getUniqueID(), context);
		vect.setType(IVPValue.INTEGER_TYPE);

		vect.add(2, n1.getUniqueID());

		HashMap map = new HashMap();
		map.put(n1.getUniqueID(), n1);
		map.put(position.getUniqueID(), position);
		map.put(ref.getUniqueID(), ref);
		map.put(vect.getUniqueID(), vect);
		map.put(var.getUniqueID(), var);

		ref.setVectorID(vect.getUniqueID());
		ref.setPositionID(position.getUniqueID());

		attLine.setVariable(var.getUniqueID());
		attLine.setExpression(ref.getUniqueID());

		IVPNumber result = (IVPNumber) var.evaluate(context, map, factory);

		assertTrue(context.getInt(result.getUniqueID()) == 1);
		n1.updateIntegerValue(context, 3);
		attLine.evaluate(context, map, factory);
		result = (IVPNumber) var.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 3);
	}

}
