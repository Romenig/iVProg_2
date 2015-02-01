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
import usp.ime.line.ivprog.interpreter.execution.code.FunctionReference;
import usp.ime.line.ivprog.interpreter.execution.code.Return;
import usp.ime.line.ivprog.interpreter.execution.code.While;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class FunctionReferenceTest {

	@Test
	public void validatingNormalFunctionReference() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		HashMap map = new HashMap();
		While w = factory.createWhile();
		Function f = factory.createFunction();
		Return r = factory.createReturn();
		AttributionLine attLine2 = factory.createAttributionLine();
		FunctionReference fr = factory.createFunctionReference();

		IVPValue maximumValue = factory.createIVPNumber();
		maximumValue.setValueType(IVPValue.INTEGER_TYPE);
		f.addConstant(maximumValue, "9", context, map);
		
		IVPVariable result = factory.createIVPVariable();
		result.setVariableType(IVPValue.INTEGER_TYPE);
		f.addVariable(result,"-11",context,map,factory);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		f.addConstant(one, "1", context, map);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setVariableName("var1");
		f.addVariable(v, "1", context, map, factory);
		r.setReturnable(v.getUniqueID());

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
		map.put(attLine2.getUniqueID(), attLine2);
		map.put(w.getUniqueID(), w);
		map.put(r.getUniqueID(), r);
		map.put(f.getUniqueID(), f);
		map.put(fr.getUniqueID(), fr);
		
		context.setFunctionID(f.getUniqueID());

		w.setLoopCondition(leq.getUniqueID());
		w.addChild(attLine.getUniqueID());

		fr.setFunctionID(f.getUniqueID());
		
		f.addChild(w.getUniqueID());
		f.addChild(r.getUniqueID());
		
		attLine2.setVariable(result.getUniqueID());
		attLine2.setExpression(fr.getUniqueID());
		
		attLine2.evaluate(context, map, factory);
		
		IVPNumber result2 = (IVPNumber) result.evaluate(context, map, factory);
		
		assertTrue(context.getInt(result2.getUniqueID())== 10);
	}
	
}
