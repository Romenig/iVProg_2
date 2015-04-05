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
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.error.IVPError;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPPrinter;

public class PrintTest {

	@Test
	public void printTest() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPPrinter p = factory.createIVPPrinter();

		a.setValueType(IVPValue.DOUBLE_TYPE);
		b.setValueType(IVPValue.INTEGER_TYPE);
		c.setValueType(IVPValue.INTEGER_TYPE);
		context.addDouble(a.getUniqueID(), 3.4313);
		context.addInt(b.getUniqueID(), 10);
		context.addInt(c.getUniqueID(), 2);

		Addition addition1 = factory.createAddition();
		addition1.setExpressionA(a.getUniqueID());
		addition1.setExpressionB(b.getUniqueID());

		Addition addition2 = factory.createAddition();
		addition2.setExpressionA(c.getUniqueID());
		addition2.setExpressionB(addition1.getUniqueID());

		HashMap map = new HashMap();
		map.put(p.getUniqueID(), p);
		map.put(addition1.getUniqueID(), addition1);
		map.put(addition2.getUniqueID(), addition2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		// addition2(c + addition1(a + b))
		// c + (a + b)
		IVPString result = (IVPString) p.evaluate(context, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.STRING_TYPE));
		assertTrue(context.getString(result.getUniqueID()).equals("15.4313"));
	}

}
