/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package interpreter.expressions.string;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import usp.ime.line.ivprog.interpreter.DataFactory;
import usp.ime.line.ivprog.interpreter.DataObject;
import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.EqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.Concatenation;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.Contains;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.StringLength;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.SubString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;

public class StringOperations {

	@Test
	public void concatenationTest() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		IVPString b = factory.createIVPString();
		Concatenation conc = factory.createConcatenation();
		conc.setExpressionA(a.getUniqueID());
		conc.setExpressionB(b.getUniqueID());

		c.addString(a.getUniqueID(), "Hello, ");
		c.addString(b.getUniqueID(), "world!");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(conc.getUniqueID(), conc);

		IVPString result = (IVPString) conc.evaluate(c, map, factory);
		assertTrue(c.getString(result.getUniqueID()).equals("Hello, world!"));
	}

	@Test
	public void obtainingSubstringTest() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		SubString substring = factory.createSubString();
		substring.setString(a.getUniqueID());
		substring.setBeginIndex("12");
		substring.setEndIndex("17");

		c.addString(a.getUniqueID(), "ABCDEFGHIJKLMNOPQRSTUWXYZ");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(substring.getUniqueID(), substring);

		IVPString result = (IVPString) substring.evaluate(c, map, factory);
		assertTrue(c.getString(result.getUniqueID()).equals("MNOPQ"));
	}

	@Test
	public void obtainingStrlen() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		StringLength string = factory.createStringLength();
		string.setString(a.getUniqueID());

		c.addString(a.getUniqueID(), "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(string.getUniqueID(), string);

		IVPNumber result = (IVPNumber) string.evaluate(c, map, factory);
		assertTrue(result.getValueType().equals(IVPValue.INTEGER_TYPE));
		assertTrue(c.getInt(result.getUniqueID()) == 26);
	}

	@Test
	public void containsSubstringTest() {
		Context c = new Context();
		DataFactory factory = new DataFactory();
		IVPString a = factory.createIVPString();
		IVPString b = factory.createIVPString();

		Contains contains = factory.createContains();
		contains.setString(a.getUniqueID());
		contains.setSubString(b.getUniqueID());

		c.addString(a.getUniqueID(), "ABCDEFGHIJKLMNOPQRSTUWXYZ");
		c.addString(b.getUniqueID(), "KLMN");

		HashMap map = new HashMap();
		map.put(a.getUniqueID(), a);
		map.put(b.getUniqueID(), b);
		map.put(contains.getUniqueID(), contains);

		IVPNumber result = (IVPNumber) contains.evaluate(c, map, factory);
		assertTrue(c.getInt(result.getUniqueID()) == 10);
	}

}
