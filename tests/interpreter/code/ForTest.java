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
import usp.ime.line.ivprog.interpreter.execution.code.For;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;

public class ForTest {

	@Test
	public void forNTimesTest() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		For myFor = factory.createFor();
		Function f = factory.createFunction();

		IVPValue startingValue = factory.createIVPNumber();
		startingValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(startingValue.getUniqueID(), 0);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(one.getUniqueID(), 1);

		IVPValue n_times = factory.createIVPNumber();
		n_times.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(n_times.getUniqueID(), 5);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(startingValue.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(v.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariable(v.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(startingValue.getUniqueID(), startingValue);
		map.put(one.getUniqueID(), one);
		map.put(v.getUniqueID(), v);
		map.put(add.getUniqueID(), add);
		map.put(attLine.getUniqueID(), attLine);
		map.put(n_times.getUniqueID(), n_times);
		map.put(f.getUniqueID(), f);
		context.setFunctionID(f.getUniqueID());

		myFor.setExecutionMethod(For.FOR_N_TIMES);
		myFor.setUpperBound(n_times.getUniqueID());
		myFor.addChild(attLine.getUniqueID());
		myFor.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) v.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 5);
	}

	@Test
	public void forNTimesWithIndex() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		For f = factory.createFor();
		Function func = factory.createFunction();
		
		IVPValue startingValue = factory.createIVPNumber();
		startingValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(startingValue.getUniqueID(), 0);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(one.getUniqueID(), 1);

		IVPValue indexValue = factory.createIVPNumber();
		indexValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(indexValue.getUniqueID(), 1);

		IVPValue n_times = factory.createIVPNumber();
		n_times.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(n_times.getUniqueID(), 5);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(startingValue.getUniqueID());

		IVPVariable indexVar = factory.createIVPVariable();
		indexVar.setVariableType(IVPValue.INTEGER_TYPE);
		indexVar.setValueID(indexValue.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(v.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariable(v.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(startingValue.getUniqueID(), startingValue);
		map.put(one.getUniqueID(), one);
		map.put(indexValue.getUniqueID(), indexValue);
		map.put(v.getUniqueID(), v);
		map.put(indexVar.getUniqueID(), indexVar);
		map.put(add.getUniqueID(), add);
		map.put(attLine.getUniqueID(), attLine);
		map.put(n_times.getUniqueID(), n_times);
		map.put(func.getUniqueID(), func);
		context.setFunctionID(func.getUniqueID());

		f.setExecutionMethod(For.FOR_N_TIMES_WITH_INDEX);
		f.setIndex(indexVar.getUniqueID());
		f.setUpperBound(n_times.getUniqueID());
		f.addChild(attLine.getUniqueID());
		f.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) v.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 5);
		result = (IVPNumber) indexVar.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 6);
	}

	@Test
	public void forComplete() {
		DataFactory factory = new DataFactory();
		Context context = new Context();
		For f = factory.createFor();
		Function func = factory.createFunction();

		IVPValue lowerBound = factory.createIVPNumber();
		lowerBound.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(lowerBound.getUniqueID(), 0);

		IVPValue upperBound = factory.createIVPNumber();
		upperBound.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(upperBound.getUniqueID(), 5);

		IVPValue increment = factory.createIVPNumber();
		increment.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(increment.getUniqueID(), 1);

		IVPValue indexStartingValue = factory.createIVPNumber();
		indexStartingValue.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(indexStartingValue.getUniqueID(), 0);

		IVPValue one = factory.createIVPNumber();
		one.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(one.getUniqueID(), 1);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(one.getUniqueID());

		IVPVariable indexVar = factory.createIVPVariable();
		indexVar.setVariableType(IVPValue.INTEGER_TYPE);
		indexVar.setValueID(indexStartingValue.getUniqueID());

		Addition add = factory.createAddition();
		add.setExpressionA(v.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariable(v.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(lowerBound.getUniqueID(), lowerBound);
		map.put(upperBound.getUniqueID(), upperBound);
		map.put(indexStartingValue.getUniqueID(), indexStartingValue);
		map.put(v.getUniqueID(), v);
		map.put(indexVar.getUniqueID(), indexVar);
		map.put(add.getUniqueID(), add);
		map.put(attLine.getUniqueID(), attLine);
		map.put(one.getUniqueID(), one);
		map.put(func.getUniqueID(), func);
		context.setFunctionID(func.getUniqueID());

		f.setExecutionMethod(For.FOR_N_TIMES_WITH_INDEX);
		f.setIndex(indexVar.getUniqueID());
		f.setUpperBound(upperBound.getUniqueID());
		f.setLowerBound(lowerBound.getUniqueID());
		f.setIncrement(increment.getUniqueID());
		f.addChild(attLine.getUniqueID());
		f.evaluate(context, map, factory);

		IVPNumber result = (IVPNumber) v.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 6);
		result = (IVPNumber) indexVar.evaluate(context, map, factory);
		assertTrue(context.getInt(result.getUniqueID()) == 5);
	}

}
