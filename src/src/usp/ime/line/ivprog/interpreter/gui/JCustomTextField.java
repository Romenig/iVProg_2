package usp.ime.line.ivprog.interpreter.gui;
/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import usp.ime.line.ivprog.interpreter.execution.expressions.value.IVPValue;
import usp.ime.line.ivprog.view.FlatUIColors;

import javax.swing.border.LineBorder;

import java.awt.Color;

public class JCustomTextField extends JTextField {

	private int maxLength = -1;
	private boolean isBlocked = false;
	private String valueType;
	private IVPInput myParent;

	private String currentRegex = "";
	private int index = 0;

	public static final String[] INTEGER = {"^[-|+]?[0-9]*$", "^[-|+]?[0-9]+$"};
	public static final String[] DOUBLE = {"^[-|+]?[0-9]*[.]?[0-9]*$", "^[-|+]?[0-9]+[.]?[0-9]+$"};
	public static final String STRING = "*";
	public static final String VARIABLE_NAME = "^[a-zA-Z_][a-zA-Z0-9_]*$";

	public JCustomTextField() {
		super();
		setBorder(new LineBorder(FlatUIColors.CODE_BORDER_BG));
		init();
	}

	public JCustomTextField(int cols) {
		super(cols);
		init();
	}

	public JCustomTextField(int cols, int maxLength) {
		super(cols);
		init();
		setMaximumLength(maxLength);
	}

    private void init() {
    	addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == 10){
					myParent.verifyRegex();
				}else if(e.getKeyChar() == 13){
					myParent.failRegex();
				}
			}
    	});
    }
	
	protected Document createDefaultModel() {
		return new RegexDocument(this);
	}

	public int getMaximumLength() {
		return maxLength;
	}

	public void setMaximumLength(int max) {
		maxLength = max;
	}

	public String getRegexFilter() {
		return String.valueOf(currentRegex);
	}

	public void setRegexFilter(String regex) {
		currentRegex = regex;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean bool) {
		isBlocked = bool;
	}

	/**
	 * @return the currentRegex
	 */
	public String getCurrentRegex() {
		return currentRegex;
	}

	/**
	 * Set the regex that will validate de user input.
	 * Choose onde value from:
	 * JCustomTextField.INTEGER
	 * JCustomTextField.DOUBLE
	 * JCustomTextField.VARIABLE_NAME
	 * @param currentRegex
	 *            the currentRegex to set
	 */
	public void setCurrentRegex(String currentRegex) {
		this.currentRegex = currentRegex;
	}

	/**
	 * @return the valueType
	 */
    public String getValueType() {
	    return valueType;
    }

	/**
	 * @param valueType the valueType to set
	 */
    public void setValueType(String valueType) {
	    this.valueType = valueType;
    }

	/**
	 * @return the myParent
	 */
    public JDialog getMyParent() {
	    return myParent;
    }

	/**
	 * @param myParent the myParent to set
	 */
    public void setMyParent(IVPInput myParent) {
	    this.myParent = myParent;
    }
    
	private final class RegexDocument extends PlainDocument {

		private JCustomTextField textField;

		public RegexDocument(JCustomTextField jCustomTextField) {
			textField = jCustomTextField;
		}

		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null) {
				return;
			}
			if (maxLength < 0) {
				if (!isBlocked) {
					super.insertString(offs, str, a);
				}
			} else {
			}
			String formedString = textField.getText();
			currentRegex = prepareRegex(formedString);
			if (formedString.matches(currentRegex)) {
				return;
			} else {
				textField.setText(formedString.substring(0, formedString.length() - 1));
				return;
			}	
		}

	}

	/**
	 * Update the regex for integer or double cases.
	 * @return
	 */
    public String prepareRegex(String formed) {
    	if(IVPValue.INTEGER_TYPE.equals(valueType)){
    		if(formed.length() > 1){
    			return INTEGER[1];
    		}else{
    			return INTEGER[0];
    		}
    	}else if(IVPValue.DOUBLE_TYPE.equals(valueType)){
    		if(IVPValue.DOUBLE_TYPE.equals(valueType)){
    			if(formed.length() >= 1){
        			return DOUBLE[1];
        		}else{
        			return DOUBLE[0];
        		}	
    		}
    	}else if(IVPValue.STRING_TYPE.equals(valueType)){
    		return STRING;
    	}
        return null;
    }
	
	/**
	 * Test if the current string is good enough to be returned as value.
	 * @return
	 */
    public boolean testOK() {
    	String formedString = this.getText();
    	currentRegex = prepareRegex(formedString);
    	if(valueType.equals(IVPValue.INTEGER_TYPE)||valueType.equals(IVPValue.DOUBLE_TYPE)){
    		if(formedString.indexOf("-")!=-1 || formedString.indexOf("+")!= -1){
    			if(formedString.length() > 1 && formedString.matches(currentRegex)){
    				return true;
    			}
    		}else{
    			if(formedString.length() > 0 && formedString.matches(currentRegex)){
    				return true;
    			}
    		}
    	}else{
    		if(formedString.length() > 1 && formedString.matches(currentRegex)){
				return true;
			}
    	}
	    return false;
    }

}
