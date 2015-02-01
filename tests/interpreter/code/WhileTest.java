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

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.AttributionLine;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.code.While;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.EqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class WhileTest {

	@Test
	public void whileTest() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		While w = factory.createWhile();
		Function f = factory.createFunction();

		IVPValue startingValue = factory.createIVPNumber();
		startingValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(startingValue.getUniqueID(), 0);

		IVPValue maximumValue = factory.createIVPNumber();
		maximumValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(maximumValue.getUniqueID(), 9);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(one.getUniqueID(), 1);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(startingValue.getUniqueID());

		LessThanOrEqualTo leq = factory.createLessThanOrEqualTo();
		leq.setExpressionA(v.getUniqueID());
		leq.setExpressionB(maximumValue.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(v.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariable(v.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(maximumValue.getUniqueID(), maximumValue);
		map.put(startingValue.getUniqueID(), startingValue);
		map.put(one.getUniqueID(), one);
		map.put(v.getUniqueID(), v);
		map.put(add.getUniqueID(), add);
		map.put(leq.getUniqueID(), leq);
		map.put(attLine.getUniqueID(), attLine);
		map.put(f.getUniqueID(), f);
		context.setFunctionID(f.getUniqueID());
		
		w.setLoopCondition(leq.getUniqueID());
		w.addChild(attLine.getUniqueID());
		w.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) v.evaluate(context, map, factory);

		assertTrue(context.getInt(result.getUniqueID()) == 10);
	}

}
