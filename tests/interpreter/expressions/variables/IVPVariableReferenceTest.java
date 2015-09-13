/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.variables;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVariableReference;

/**
 * @author Romenig
 * 
 */
public class IVPVariableReferenceTest {

	@Test
	public void TestReferenceInExpression() {
		Context context = new Context();
		DataFactory factory = new DataFactory();
		IVPNumber a = factory.createIVPNumber();
		IVPNumber b = factory.createIVPNumber();
		IVPNumber c = factory.createIVPNumber();
		IVPVariable var = factory.createIVPVariable();
		var.setValueID(c.getUniqueID());
		IVPVariableReference reference = factory.createIVPVariableReference();
		reference.setReferencedID(var.getUniqueID());

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
		addition2.setExpressionA(reference.getUniqueID());
		addition2.setExpressionB(addition1.getUniqueID());

		HashMap map = new HashMap();
		map.put(addition1.getUniqueID(), addition1);
		map.put(addition2.getUniqueID(), addition2);
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(c.getUniqueID(), c);
		map.put(var.getUniqueID(), var);
		map.put(reference.getUniqueID(), reference);
		
		IVPNumber result1 = factory.createIVPNumber();
		map.put(result1.getUniqueID(), result1);
		addition1.setOperationResultID(result1.getUniqueID());
		
		IVPNumber result2 = factory.createIVPNumber();
		map.put(result2.getUniqueID(), result2);
		addition2.setOperationResultID(result2.getUniqueID());
		
		// addition2(c + addition1(a + b))
		// c + (a + b)
		addition2.evaluate(context, map, factory);
		assertTrue(result2.getValueType().equals(IVPValue.DOUBLE_TYPE));
		assertTrue(context.getDouble(result2.getUniqueID()) == 15.4313);
	}

}
