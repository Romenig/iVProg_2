/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.utils.matrix;

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
import usp.ime.line.ivprog.interpreter.execution.utils.IVPMatrix;

public class IVPMatrixTests {

	@Test
	public void createAMatrixWithSizeX() {
		DataFactory factory = new DataFactory();
		Context context = new Context();

		IVPNumber size = factory.createIVPNumber();
		size.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(size.getUniqueID(), 3);

		IVPMatrix matrix = factory.createIVPMatrix();
		matrix.setType(IVPValue.INTEGER_TYPE);

		HashMap map = new HashMap();
		map.put(matrix.getUniqueID(), matrix);
		map.put(size.getUniqueID(), size);

		matrix.setSize(size.getUniqueID(), size.getUniqueID(), context);

		assertTrue(matrix.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(matrix.getNColID()) == 3);
		assertTrue(context.getInt(matrix.getNLinID()) == 3);
		assertTrue(context.getBoolean(matrix.isEmpty(factory, context, map)));
	}

	@Test
	public void createAMatrixAndPopulateWithInteger() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPMatrix matrix = factory.createIVPMatrix();
		IVPNumber size = factory.createIVPNumber();
		IVPNumber line = factory.createIVPNumber();
		IVPNumber column = factory.createIVPNumber();

		size.setValueType(IVPValue.INTEGER_TYPE);
		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		line.setValueType(IVPValue.INTEGER_TYPE);
		column.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(line.getUniqueID(), 0);
		context.addInt(column.getUniqueID(), 0);
		context.addInt(a.getUniqueID(), 3);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);
		context.addInt(size.getUniqueID(), 3);

		matrix.setSize(size.getUniqueID(), size.getUniqueID(), context);

		matrix.setType(IVPValue.INTEGER_TYPE);

		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, a.getUniqueID());
		line.updateIntegerValue(context, 1);
		column.updateIntegerValue(context, 1);
		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, b.getUniqueID());
		line.updateIntegerValue(context, 2);
		column.updateIntegerValue(context, 2);
		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, c.getUniqueID());

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(line.getUniqueID(), line);
		map.put(column.getUniqueID(), column);
		map.put(size.getUniqueID(), size);
		map.put(matrix.getUniqueID(), matrix);

		assertTrue(matrix.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(matrix.getNColID()) == 3);
		assertTrue(context.getInt(matrix.getNLinID()) == 3);
		assertFalse(context.getBoolean(matrix.isEmpty(factory, context, map)));

		line.updateIntegerValue(context, 0);
		column.updateIntegerValue(context, 0);
		IVPNumber result1 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));
		line.updateIntegerValue(context, 1);
		column.updateIntegerValue(context, 1);
		IVPNumber result2 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));
		line.updateIntegerValue(context, 2);
		column.updateIntegerValue(context, 2);
		IVPNumber result3 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));

		assertTrue(context.getInt(result1.getUniqueID()) == 3);
		assertTrue(context.getInt(result2.getUniqueID()) == 10);
		assertTrue(context.getInt(result3.getUniqueID()) == 2);

	}

	@Test
	public void removeFromAPopulatedMatrix() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPNumber line = factory.createIVPNumber();
		IVPNumber column = factory.createIVPNumber();
		IVPMatrix matrix = factory.createIVPMatrix();
		IVPNumber size = factory.createIVPNumber();

		line.setValueType(IVPValue.INTEGER_TYPE);
		column.setValueType(IVPValue.INTEGER_TYPE);

		a.setValueType(IVPValue.INTEGER_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		size.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(a.getUniqueID(), 3);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);
		context.addInt(size.getUniqueID(), 3);
		context.addInt(line.getUniqueID(), 0);
		context.addInt(column.getUniqueID(), 0);

		matrix.setSize(size.getUniqueID(), size.getUniqueID(), context);
		matrix.setType(IVPValue.INTEGER_TYPE);

		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, a.getUniqueID());
		line.updateIntegerValue(context, 1);
		column.updateIntegerValue(context, 1);
		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, b.getUniqueID());
		line.updateIntegerValue(context, 2);
		column.updateIntegerValue(context, 2);
		matrix.addElement(line.getUniqueID(), column.getUniqueID(), context, c.getUniqueID());

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(matrix.getUniqueID(), matrix);
		map.put(line.getUniqueID(), line);
		map.put(column.getUniqueID(), column);

		assertTrue(matrix.getType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(context.getInt(matrix.getNColID()) == 3);
		assertTrue(context.getInt(matrix.getNLinID()) == 3);
		assertFalse(context.getBoolean(matrix.isEmpty(factory, context, map)));

		line.updateIntegerValue(context, 0);
		column.updateIntegerValue(context, 0);
		IVPNumber result1 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));
		line.updateIntegerValue(context, 1);
		column.updateIntegerValue(context, 1);
		IVPNumber result2 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));
		line.updateIntegerValue(context, 2);
		column.updateIntegerValue(context, 2);
		IVPNumber result3 = (IVPNumber) map.get(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context));

		assertTrue(context.getInt(result1.getUniqueID()) == 3);
		assertTrue(context.getInt(result2.getUniqueID()) == 10);
		assertTrue(context.getInt(result3.getUniqueID()) == 2);

		line.updateIntegerValue(context, 1);
		column.updateIntegerValue(context, 1);
		String removed = matrix.removeElement(line.getUniqueID(), column.getUniqueID(), context);

		assertTrue(removed.equals(b.getUniqueID()));
		assertTrue(matrix.getElement(line.getUniqueID(), column.getUniqueID(), context).equals(IVPValue.NULL));
	}

}
