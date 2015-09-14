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
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVariableReference;

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
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());

		IVPNumber result = factory.createIVPNumber();
		
		Addition add = factory.createAddition();
		add.setExpressionA(ref.getUniqueID());
		add.setExpressionB(one.getUniqueID());
		add.setOperationResultID(result.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariableID(ref.getUniqueID());
		attLine.setExpression(add.getUniqueID());

		HashMap map = new HashMap();
		map.put(startingValue.getUniqueID(), startingValue);
		map.put(one.getUniqueID(), one);
		map.put(v.getUniqueID(), v);
		map.put(add.getUniqueID(), add);
		map.put(attLine.getUniqueID(), attLine);
		map.put(n_times.getUniqueID(), n_times);
		map.put(f.getUniqueID(), f);
		map.put(result.getUniqueID(), result);
		map.put(ref.getUniqueID(), ref);
		context.setFunctionID(f.getUniqueID());

		myFor.setExecutionMethod(For.FOR_N_TIMES);
		myFor.setUpperBound(n_times.getUniqueID());
		myFor.addChild(attLine.getUniqueID());
		
		myFor.evaluate(context, map, factory);

		IVPNumber result2 = (IVPNumber) v.evaluate(context, map, factory);
		System.out.println(context.getInt(result2.getUniqueID()));
		assertTrue(context.getInt(result2.getUniqueID()) == 5);
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
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());

		IVPVariable indexVar = factory.createIVPVariable();
		indexVar.setVariableType(IVPValue.INTEGER_TYPE);
		indexVar.setValueID(indexValue.getUniqueID());
		
		Addition add = factory.createAddition();
		add.setExpressionA(ref.getUniqueID());
		add.setExpressionB(one.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariableID(ref.getUniqueID());
		attLine.setExpression(add.getUniqueID());
		
		IVPNumber result = factory.createIVPNumber();
		add.setOperationResultID(result.getUniqueID());

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
		map.put(result.getUniqueID(), result);
		map.put(ref.getUniqueID(), ref);
		context.setFunctionID(func.getUniqueID());

		f.setExecutionMethod(For.FOR_N_TIMES_WITH_INDEX);
		f.setIndex(indexVar.getUniqueID());
		f.setUpperBound(n_times.getUniqueID());
		f.addChild(attLine.getUniqueID());
		f.evaluate(context, map, factory);

		IVPNumber value2 = (IVPNumber) v.evaluate(context, map, factory);
		assertTrue(context.getInt(value2.getUniqueID()) == 5);
		value2 = (IVPNumber) indexVar.evaluate(context, map, factory);
		assertTrue(context.getInt(value2.getUniqueID()) == 6);
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
		
		IVPValue one2 = factory.createIVPNumber();
		one2.setValueType(IVPValue.INTEGER_TYPE);
		context.addInt(one2.getUniqueID(), 1);

		IVPVariable v = factory.createIVPVariable();
		v.setVariableType(IVPValue.INTEGER_TYPE);
		v.setValueID(one.getUniqueID());
		
		IVPVariableReference ref = factory.createIVPVariableReference();
		ref.setReferencedID(v.getUniqueID());

		IVPVariable indexVar = factory.createIVPVariable();
		indexVar.setVariableType(IVPValue.INTEGER_TYPE);
		indexVar.setValueID(indexStartingValue.getUniqueID());
		
		IVPVariableReference ref2 = factory.createIVPVariableReference();
		ref2.setReferencedID(indexVar.getUniqueID());
		
		Addition add = factory.createAddition();
		add.setExpressionA(ref.getUniqueID());
		add.setExpressionB(one2.getUniqueID());
		
		IVPNumber result = factory.createIVPNumber();
		add.setOperationResultID(result.getUniqueID());

		AttributionLine attLine = factory.createAttributionLine();
		attLine.setVariableID(ref.getUniqueID());
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
		map.put(one2.getUniqueID(), one2);
		map.put(func.getUniqueID(), func);
		map.put(ref.getUniqueID(), ref);
		map.put(ref2.getUniqueID(), ref2);
		map.put(result.getUniqueID(), result);
		context.setFunctionID(func.getUniqueID());

		f.setExecutionMethod(For.FOR_N_TIMES_WITH_INDEX);
		f.setIndex(indexVar.getUniqueID());
		f.setUpperBound(upperBound.getUniqueID());
		f.setLowerBound(lowerBound.getUniqueID());
		f.setIncrement(increment.getUniqueID());
		f.addChild(attLine.getUniqueID());
		f.evaluate(context, map, factory);

		IVPNumber result2 = (IVPNumber) v.evaluate(context, map, factory);
		
		assertTrue(context.getInt(result2.getUniqueID()) == 6);
		result2 = (IVPNumber) indexVar.evaluate(context, map, factory);
		assertTrue(context.getInt(result2.getUniqueID()) == 5);
	}

}
