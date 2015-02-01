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
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVector;

public class IVPVectorTests {

	@Test
	public void createAVectorWithSizeX() {
		DataFactory factory = new DataFactory();
		Context context = new Context();

		IVPVector vect = factory.createIVPVector();
		IVPNumber size = factory.createIVPNumber();

		size.setValueType(IVPValue.INTEGER_TYPE);
		vect.setType(IVPValue.INTEGER_TYPE);

		context.addInt(size.getUniqueID(), 3);

		HashMap map = new HashMap();
		map.put(vect.getUniqueID(), vect);
		map.put(size.getUniqueID(), size);

		vect.setSize(size.getUniqueID(), context);

		assertTrue(vect.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(vect.getSize()) == 3);
		assertTrue(context.getBoolean(vect.isEmpty(factory, context, map)));
	}

	@Test
	public void createAVectorAndPopulateWithInteger() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPVector vect = factory.createIVPVector();
		IVPNumber size = factory.createIVPNumber();
		IVPNumber index = factory.createIVPNumber();

		size.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		index.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(index.getUniqueID(), 0);
		context.addInt(a.getUniqueID(), 3);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);
		context.addInt(size.getUniqueID(), 10);

		vect.setSize(size.getUniqueID(), context);

		vect.setType(IVPValue.INTEGER_TYPE);

		vect.add(0, a.getUniqueID());
		vect.add(1, b.getUniqueID());
		vect.add(2, c.getUniqueID());

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(index.getUniqueID(), index);
		map.put(vect.getUniqueID(), vect);

		assertTrue(vect.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(vect.getSize()) == 10);
		assertFalse(context.getBoolean(vect.isEmpty(factory, context, map)));

		IVPNumber result1 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));
		index.updateIntegerValue(context, 1);
		IVPNumber result2 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));
		index.updateIntegerValue(context, 2);
		IVPNumber result3 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));

		assertTrue(context.getInt(result1.getUniqueID()) == 3);
		assertTrue(context.getInt(result2.getUniqueID()) == 10);
		assertTrue(context.getInt(result3.getUniqueID()) == 2);

	}

	@Test
	public void removeFromAPopulatedVector() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPVector vect = factory.createIVPVector();
		IVPNumber size = factory.createIVPNumber();
		IVPNumber one = factory.createIVPNumber();
		IVPNumber index = factory.createIVPNumber();

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		size.setValueType(IVPValue.INTEGER_TYPE);
		one.setValueType(IVPValue.INTEGER_TYPE);
		index.setValueType(IVPValue.INTEGER_TYPE);

		context.addInt(one.getUniqueID(), 1);
		context.addInt(a.getUniqueID(), 3);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);
		context.addInt(size.getUniqueID(),10);
		context.addInt(index.getUniqueID(), 0);

		vect.setSize(size.getUniqueID(), context);
		vect.setType(IVPValue.INTEGER_TYPE);

		vect.add(0, a.getUniqueID());
		vect.add(1, b.getUniqueID());
		vect.add(2, c.getUniqueID());

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(one.getUniqueID(), one);
		map.put(vect.getUniqueID(), vect);
		map.put(index.getUniqueID(), index);

		assertTrue(vect.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(vect.getSize()) == 10);
		assertFalse(context.getBoolean(vect.isEmpty(factory, context, map)));

		IVPNumber result1 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));
		index.updateIntegerValue(context, 1);
		IVPNumber result2 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));
		index.updateIntegerValue(context, 2);
		IVPNumber result3 = (IVPNumber) map.get(vect.get(index.getUniqueID(), context));

		assertTrue(context.getInt(result1.getUniqueID()) == 3);
		assertTrue(context.getInt(result2.getUniqueID()) == 10);
		assertTrue(context.getInt(result3.getUniqueID()) == 2);

		String removed = vect.remove(one.getUniqueID(), context);
		index.updateIntegerValue(context, 1);
		assertTrue(removed.equals(b.getUniqueID()));
		assertTrue(vect.get(index.getUniqueID(), context).equals(IVPValue.NULL));
	}

}
