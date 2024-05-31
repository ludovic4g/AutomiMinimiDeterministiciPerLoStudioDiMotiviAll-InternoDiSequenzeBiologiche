package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyMenu extends JFrame{
	private JFrame genmenu;
	private JFrame hamenu;
	private JPanel panel;
	private JButton gen_string;
	private JButton ham_string;
	private JLabel label;
	private ActionListener genlist;
	private ActionListener hamlist;

	public MyMenu() {
		class GenButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				createGenMenu();
				
			}
			
		}
		genlist = new GenButton();
		gen_string = new JButton("STRINGHE GENERALIZZATE");
		gen_string.addActionListener(genlist);
		
		class HamButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				createHamMenu();
				
			}
			
			
		}
		hamlist = new HamButton();
		ham_string = new JButton("STRINGHE CONSENSO");
		ham_string.addActionListener(hamlist);
		
		label = new JLabel("              SCEGLI IL TIPO DI STRINGA PER LA COSTRUZIONE");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(gen_string);
		panel.add(ham_string);
		
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		setSize(250,100);
		
	}
	
	public void createGenMenu() {
		genmenu= new GeneralizedMenu();
		genmenu.setSize(600,500);
		genmenu.setLocation(600,200);
		genmenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		genmenu.setVisible(true);
	}
	
	public void createHamMenu() {
		hamenu= new HammingMenu();
		hamenu.setSize(600,500);
		hamenu.setLocation(600,200);
		hamenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		hamenu.setVisible(true);		
	}
}
