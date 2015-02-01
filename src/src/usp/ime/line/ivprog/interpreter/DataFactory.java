/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.interpreter;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Vector;

import usp.ime.line.ivprog.interpreter.execution.Context;
import usp.ime.line.ivprog.interpreter.execution.code.AttributionLine;
import usp.ime.line.ivprog.interpreter.execution.code.For;
import usp.ime.line.ivprog.interpreter.execution.code.Function;
import usp.ime.line.ivprog.interpreter.execution.code.FunctionReference;
import usp.ime.line.ivprog.interpreter.execution.code.IfElse;
import usp.ime.line.ivprog.interpreter.execution.code.RecursiveCall;
import usp.ime.line.ivprog.interpreter.execution.code.Return;
import usp.ime.line.ivprog.interpreter.execution.code.UserInput;
import usp.ime.line.ivprog.interpreter.execution.code.While;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Addition;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Division;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Multiplication;
import usp.ime.line.ivprog.interpreter.execution.expressions.arithmetic.Subtraction;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.And;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.Or;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.EqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.GreaterThan;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.GreaterThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThan;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.LessThanOrEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.booleans.comparisons.NotEqualTo;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.Concatenation;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.Contains;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.StringLength;
import usp.ime.line.ivprog.interpreter.execution.expressions.strings.SubString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPBoolean;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPNumber;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPString;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPVariable;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPMatrix;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPMatrixReference;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVector;
import usp.ime.line.ivprog.interpreter.execution.utils.IVPVectorReference;
import usp.ime.line.ivprog.language.Messages;

public class DataFactory {

	private Random r;
	private Vector v;

	public DataFactory() {
		r = new Random();
		v = new Vector();
	}

	private String getID() {
		String randomHash = Integer.toHexString(r.nextInt());
		while (v.contains(randomHash)) {
			randomHash = Integer.toHexString(r.nextInt());
		}
		v.add(randomHash);
		return (String) v.lastElement();
	}

	/**
	 * Get a new IVPNumber for a given context.
	 * 
	 * @return
	 */
	public IVPNumber createIVPNumber() {
		IVPNumber number = new IVPNumber();
		number.setUniqueID("" + getID());
		return number;
	}

	/**
	 * Get a new IVPString for a given context.
	 * 
	 * @return
	 */
	public IVPString createIVPString() {
		IVPString string = new IVPString();
		string.setValueType(IVPValue.STRING_TYPE);
		string.setUniqueID("" + getID());
		return string;
	}

	/**
	 * Get a new IVPBoolean for a given context.
	 * 
	 * @return
	 */
	public IVPBoolean createIVPBoolean() {
		IVPBoolean bool = new IVPBoolean();
		bool.setValueType(IVPValue.BOOLEAN_TYPE);
		bool.setUniqueID("" + getID());
		return bool;
	}

	/**
	 * Get a new addition object.
	 * 
	 * @return
	 */
	public Addition createAddition() {
		Addition addition = new Addition();
		addition.setUniqueID("" + getID());
		return addition;
	}

	/**
	 * Get a new Multiplication object.
	 * 
	 * @return
	 */
	public Multiplication createMultiplication() {
		Multiplication multiplication = new Multiplication();
		multiplication.setUniqueID("" + getID());
		return multiplication;
	}

	/**
	 * Get a new Subtraction object.
	 * 
	 * @return
	 */
	public Subtraction createSubtraction() {
		Subtraction sub = new Subtraction();
		sub.setUniqueID("" + getID());
		return sub;
	}

	/**
	 * Get a new division object.
	 * 
	 * @return
	 */
	public Division createDivision() {
		Division div = new Division();
		div.setUniqueID("" + getID());
		return div;
	}

	/**
	 * Get a new Variable for a given context.
	 * 
	 * @return
	 */
	public IVPVariable createIVPVariable() {
		IVPVariable v = new IVPVariable();
		v.setUniqueID("" + getID());
		return v;
	}

	/**
	 * Get a new And object.
	 * 
	 * @return
	 */
	public And createAnd() {
		And and = new And();
		and.setUniqueID("" + getID());
		return and;
	}

	/**
	 * Get a new Or object.
	 * 
	 * @return
	 */
	public Or createOr() {
		Or or = new Or();
		or.setUniqueID("" + getID());
		return or;
	}

	/**
	 * Get a new EqualTo object.
	 * 
	 * @return
	 */
	public EqualTo createEqualTo() {
		EqualTo eq = new EqualTo();
		eq.setUniqueID(getID());
		return eq;
	}

	/**
	 * Get a new LessThan object.
	 * 
	 * @return
	 */
	public LessThan createLessThan() {
		LessThan less = new LessThan();
		less.setUniqueID(getID());
		return less;
	}

	/**
	 * Get a new LessThanOrEqualTo object.
	 * 
	 * @return
	 */
	public LessThanOrEqualTo createLessThanOrEqualTo() {
		LessThanOrEqualTo less = new LessThanOrEqualTo();
		less.setUniqueID(getID());
		return less;
	}

	/**
	 * Get a new GreaterThan object.
	 * 
	 * @return
	 */
	public GreaterThan createGreaterThan() {
		GreaterThan great = new GreaterThan();
		great.setUniqueID(getID());
		return great;
	}

	/**
	 * Get a new GreaterThanOrEqualTo object.
	 * 
	 * @return
	 */
	public GreaterThanOrEqualTo createGreaterThanOrEqualTo() {
		GreaterThanOrEqualTo great = new GreaterThanOrEqualTo();
		great.setUniqueID(getID());
		return great;
	}

	/**
	 * Get a new NotQualTo object.
	 * 
	 * @return
	 */
	public NotEqualTo createNotEqualTo() {
		NotEqualTo notEq = new NotEqualTo();
		notEq.setUniqueID(getID());
		return notEq;
	}

	/**
	 * Get a new Concatenation object.
	 * 
	 * @return
	 */
	public Concatenation createConcatenation() {
		Concatenation concat = new Concatenation();
		concat.setUniqueID(getID());
		return concat;
	}

	/**
	 * Get a new SubString object.
	 * 
	 * @return
	 */
	public SubString createSubString() {
		SubString sub = new SubString();
		sub.setUniqueID(getID());
		return sub;
	}

	/**
	 * Get a new StringLength object.
	 * 
	 * @return
	 */
	public StringLength createStringLength() {
		StringLength strlen = new StringLength();
		strlen.setUniqueID(getID());
		return strlen;
	}

	/**
	 * Get a new Contains object.
	 * 
	 * @return
	 */
	public Contains createContains() {
		Contains contains = new Contains();
		contains.setUniqueID(getID());
		return contains;
	}

	/**
	 * Get a new AttributionLine object.
	 * 
	 * @return
	 */
	public AttributionLine createAttributionLine() {
		AttributionLine attLine = new AttributionLine();
		attLine.setUniqueID(getID());
		return attLine;
	}

	/**
	 * Get a new AttributionLine object.
	 * 
	 * @return
	 */
	public IVPVector createIVPVector() {
		IVPVector vector = new IVPVector();
		vector.setUniqueID(getID());
		return vector;
	}

	/**
	 * Get a new IVPVectorReference object.
	 * 
	 * @return
	 */
	public IVPVectorReference createIVPVectorReference() {
		IVPVectorReference ref = new IVPVectorReference();
		ref.setUniqueID(getID());
		return ref;
	}

	/**
	 * Get a new While object.
	 * 
	 * @return
	 */
	public While createWhile() {
		While w = new While();
		w.setUniqueID(getID());
		return w;
	}

	/**
	 * Get a new IfElse object.
	 * 
	 * @return
	 */
	public IfElse createIfElse() {
		IfElse ifElse = new IfElse();
		ifElse.setUniqueID(getID());
		return ifElse;
	}

	/**
	 * Get a new For object.
	 * 
	 * @return
	 */
	public For createFor() {
		For f = new For();
		f.setUniqueID(getID());
		return f;
	}

	/**
	 * Get a new Matrix object.
	 * 
	 * @return
	 */
	public IVPMatrix createIVPMatrix() {
		IVPMatrix m = new IVPMatrix();
		m.setUniqueID(getID());
		return m;
	}

	/**
	 * Get a new IVPMatrixReference object.
	 * 
	 * @return
	 */
	public IVPMatrixReference createIVPMatrixReference() {
		IVPMatrixReference mr = new IVPMatrixReference();
		mr.setUniqueID(getID());
		return mr;
	}

	/**
	 * Get a new Function object.
	 * 
	 * @return
	 */
	public Function createFunction() {
		Function f = new Function();
		f.setUniqueID(getID());
		return f;
	}

	/**
	 * Get a new Return object.
	 * @return
	 */
    public Return createReturn() {
    	Return r = new Return();
    	r.setUniqueID(getID());
	    return r;
    }

	/**
	 * Get a new FunctionReference object.
	 * @return
	 */
    public FunctionReference createFunctionReference() {
    	FunctionReference fr = new FunctionReference();
    	fr.setUniqueID(getID());
	    return fr;
    }

	/**
	 * Get a new RecursiveCall object.
	 * @return
	 */
    public RecursiveCall createRecursiveCall() {
		RecursiveCall rc = new RecursiveCall();
		rc.setUniqueID(getID());
		return rc;
    }

	/**
	 * Get a new UserInput object.
	 * @return
	 */
    public UserInput createUserInput() {
    	UserInput input = new UserInput();
    	input.setUniqueID(getID());
    	return input;
    }

}
