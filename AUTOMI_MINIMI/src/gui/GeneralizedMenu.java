package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import automi.Automa;
import operazioni.SimpleNFAGeneralized_NewVersion;

public class GeneralizedMenu extends JFrame{

	private JPanel panel;
	private JTextArea area;
	private JButton generate;
	private JLabel label;
	private JLabel label1;
	private JTextField alf; 
	private AutomaViewer viewer;
	private String[] str;
	private String[] alph;
	private ArrayList<String> alfabeto;
	private ArrayList<String> stringhe;
	private ActionListener genlist;
	
	
	public GeneralizedMenu() {
		
		label1= new JLabel("Inserire alfabeto seguendo il pattern a,b,c: ");
		alf= new JTextField(1);
		label= new JLabel("Inserire stringhe seguendo questo pattern 'ab#ab a#ac#b' separate da un invio: ");
		area= new JTextArea(5,20);
		
		class GenButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				str = area.getText().split("\n");
				stringhe = new ArrayList<String>();
				for(int i=0; i<str.length; i++) {
					stringhe.add(str[i].toString());
				}

				alph = alf.getText().split(",");
				alfabeto = new ArrayList<String>();
				for(int i=0; i<alph.length; i++) {
					alfabeto.add(alph[i].toString());
				}
				
				SimpleNFAGeneralized_NewVersion nfa = new SimpleNFAGeneralized_NewVersion();
				Automa automa = nfa.compute(alfabeto, stringhe);

				createViewer(automa, alfabeto, stringhe);
				
			}
			
		}
		genlist = new GenButton();
		generate= new JButton("GENERA");
		generate.addActionListener(genlist);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(label1);
		panel.add(alf);
		panel.add(label);
		panel.add(area);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1,3));
		p1.add(generate);
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.NORTH);
		add(p1, BorderLayout.CENTER);
	}
	
	public void createViewer(Automa a, ArrayList<String> alf, ArrayList<String> str) {
		viewer= new AutomaViewer(a, alf, str);
		viewer.setSize(800,700);
		viewer.setLocation(600,200);
		viewer.setResizable(true);
		viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewer.setVisible(true);
	}
}
