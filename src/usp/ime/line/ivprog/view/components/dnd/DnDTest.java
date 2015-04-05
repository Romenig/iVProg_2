/** 
 * Instituto de Matemática e Estatística da Universidade de São Paulo (IME-USP)
 * iVProg is a open source and free software of Laboratório de Informática na 
 * Educação (LInE) licensed under GNU GPL2.0 license.
 * Prof. Dr. Leônidas de Oliveira Brandão - leo@ime.usp.br
 * Romenig da Silva Ribeiro - romenig@ime.usp.br | romenig@gmail.com
 * @author Romenig
 */
package usp.ime.line.ivprog.view.components.dnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

/**
 * @author Romenig
 */
public class DnDTest extends JFrame {

	private JPanel contentPane;
	private ScrolledTargetPanel target;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DnDTest frame = new DnDTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DnDTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JButton btnAddLeaf = new JButton("Add Leaf");
		btnAddLeaf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// target.addComponent(new LeafPanel());
			}
		});
		panel_1.add(btnAddLeaf);
		JButton btnAddComposite = new JButton("Add Composite");
		btnAddComposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// target.addComponent(new CompositePanel());
			}
		});
		panel_1.add(btnAddComposite);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// target = new ScrolledTargetPanel("");
		panel.add(target);
		target.setBackground(Color.white);
	}

}
