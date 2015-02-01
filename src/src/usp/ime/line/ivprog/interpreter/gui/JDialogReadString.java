package usp.ime.line.ivprog.interpreter.gui;
/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import usp.ime.line.ivprog.language.Messages;
import usp.ime.line.ivprog.view.FlatUIColors;

public class JDialogReadString extends JDialog {
	private static JDialogReadString instance;
	private JButton btnOk;
	private JButton btnCancelar;
	private JPanel contentPane; 
	private JPanel textFieldPanel;
	private JCustomTextField customTextField;
	private JLabel textLabel;

	//Singleton
	public static JDialogReadString getInstance(){
		if(instance != null){ 
			return instance;
		} else {
			instance = new JDialogReadString();
			return instance;
		}
	}
	
	public JDialogReadString() {
		init();
		initTextPanel();
		initTextField();
		initBtnPanel();
		setLocationRelativeTo(null);
		pack();
		setModal(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
    private void initBtnPanel() {
		JPanel btnsPanel = new JPanel();
		getContentPane().add(btnsPanel, BorderLayout.SOUTH);
		btnsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	btnOk = new JButton(Messages.getString("Btn.OK"));
    	btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				JDialogReadString.getInstance().dispose();
            }
    	});
		btnsPanel.add(btnOk);
		
		btnCancelar = new JButton(Messages.getString("Btn.CANCEL"));
		btnCancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				JDialogReadString.getInstance().dispose();
            }
		});
		btnsPanel.add(btnCancelar);
    }

    private void initTextPanel() {
		textFieldPanel = new JPanel();
		contentPane.add(textFieldPanel, BorderLayout.CENTER);
		textFieldPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		textLabel = new JLabel(Messages.getString("JDialogReadInteger.textLabel.text"));
		textFieldPanel.add(textLabel);
    }

	private void init(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(FlatUIColors.MAIN_BG);
        setContentPane(contentPane);
	}

    private void initTextField() {
		customTextField = new JCustomTextField();
		customTextField.setColumns(20);
		customTextField.setRegexFilter(JCustomTextField.STRING);
		textFieldPanel.add(customTextField);
    }

	/**
	 * Get the input value.
	 * @return
	 */
    public String getValue() {
	    return customTextField.getText();
    }

}
