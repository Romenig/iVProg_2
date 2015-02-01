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
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.code.IfElse;
import usp.ime.line.ivprog.interpreter.execution.code.RecursiveCall;
import usp.ime.line.ivprog.interpreter.execution.code.Return;
import usp.ime.line.ivprog.interpreter.execution.code.UserInput;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Multiplication;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Subtraction;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.EqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class RecursiveCallWithUserInputTest {

	@Test
	public void recursiveCall() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		HashMap map = new HashMap();
		
		Function fatorial = factory.createFunction();
		context.setFunctionID(fatorial.getUniqueID());
		fatorial.setFunctionName("fatorial");
		fatorial.setFunctionReturnType(IVPValue.INTEGER_TYPE);
		fatorial.addArgument(IVPValue.INTEGER_TYPE, context, map, factory);
		IVPNumber argu = (IVPNumber) map.get(fatorial.getArgument(0));
		map.put(fatorial.getUniqueID(), fatorial);
		
		UserInput input = factory.createUserInput();
		input.setType(IVPValue.INTEGER_TYPE);
		input.setValueID(argu.getUniqueID());
		map.put(input.getUniqueID(), input);
		
		Return r1 = factory.createReturn();
		IVPNumber one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		fatorial.addConstant(one, "1", context, map);
		r1.setReturnable(one.getUniqueID());
		map.put(r1.getUniqueID(), r1);
		
		Return r2 = factory.createReturn();
		RecursiveCall recursion = factory.createRecursiveCall();
		map.put(recursion.getUniqueID(), recursion);
		recursion.setFunctionID(fatorial.getUniqueID());
		map.put(r2.getUniqueID(), r2);
		
		IfElse ifElse = factory.createIfElse();
		ifElse.addChild(r1.getUniqueID());
		ifElse.addElseChild(r2.getUniqueID());
		map.put(ifElse.getUniqueID(), ifElse);
		
		EqualTo eq = factory.createEqualTo();
		eq.setExpressionA(fatorial.getArgument(0));
		eq.setExpressionB(one.getUniqueID());
		ifElse.setFlowCondition(eq.getUniqueID());
		map.put(eq.getUniqueID(), eq);
		fatorial.addChild(ifElse.getUniqueID());
		
		Multiplication m = factory.createMultiplication();
		m.setExpressionA(fatorial.getArgument(0));
		m.setExpressionB(recursion.getUniqueID());
		r2.setReturnable(m.getUniqueID());
		map.put(m.getUniqueID(), m);
		
		Subtraction sub = factory.createSubtraction();
		sub.setExpressionA(fatorial.getArgument(0));
		sub.setExpressionB(one.getUniqueID());
		map.put(sub.getUniqueID(), sub);
		recursion.addParameter(0, sub.getUniqueID());
		
		input.evaluate(context, map, factory);
		IVPNumber result = (IVPNumber) fatorial.evaluate(context, map, factory);
		
		assertTrue(context.getInt(result.getUniqueID()) == 120); 
	}

}
