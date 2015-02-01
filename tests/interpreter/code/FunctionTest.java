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
import usp.ime.line.ivprog.interpreter.execution.code.Return;
import usp.ime.line.ivprog.interpreter.execution.code.While;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class FunctionTest {

	@Test
	public void functionWithWhile() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		HashMap map = new HashMap();
		While w = factory.createWhile();
		Function f = factory.createFunction();

		IVPValue maximumValue = factory.createIVPNumber();
		maximumValue.setValueType(IVPValue.INTEGER_TYPE);
		f.addConstant(maximumValue, "9", context, map);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		f.addConstant(one, "1", context, map);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setVariableName("var1");
		f.addVariable(v, "1", context, map, factory);

		LessThanOrEqualTo leq = factory.createLessThanOrEqualTo();
		leq.setExpressionA(v.getUniqueID());
		leq.setExpressionB(maximumValue.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(v.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariable(v.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		map.put(add.getUniqueID(), add);
		map.put(leq.getUniqueID(), leq);
		map.put(attLine.getUniqueID(), attLine);
		map.put(w.getUniqueID(), w);
		map.put(f.getUniqueID(), f);
		context.setFunctionID(f.getUniqueID());

		w.setLoopCondition(leq.getUniqueID());
		w.addChild(attLine.getUniqueID());

		f.addChild(w.getUniqueID());

		f.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) v.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 10);
	}
	
}
