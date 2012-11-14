package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class NumView implements MancalaView 
{
	private JPanel p1Pits;
	private JPanel p2Pits;
	private JButton p1Mancala;
	private JButton p2Mancala;
	
	public NumView(MancalaController controller)
	{	
		JComponent boardview = new JPanel();
		p1Pits = new JPanel();
		p2Pits = new JPanel();
		p1Mancala = new JButton();
		p2Mancala = new JButton();
		
		boardview.setLayout(new BorderLayout());
		p1Pits.setLayout(new FlowLayout());
		p2Pits.setLayout(new FlowLayout());

		//fill up pits with buttons
		for (int i = 0; i < MancalaModel.PIT_SIZE; i++)
		{
			JButton p1butt = new JButton();
			JButton p2butt = new JButton();
			
			p1butt.addActionListener(controller);
			p2butt.addActionListener(controller);
			p1Pits.add(p1butt);
			p2Pits.add(p2butt);
		}
		
		//p1Mancala.setText();
		//p2Mancala.setText(starting_stones + "");
		
		p1Pits.setPreferredSize(new Dimension(325, 35));
		p2Pits.setPreferredSize(new Dimension(325, 35));
	
		boardview.add(p1Mancala, BorderLayout.EAST);
		boardview.add(p2Mancala, BorderLayout.WEST);
		boardview.add(p1Pits, BorderLayout.SOUTH);
		boardview.add(p2Pits, BorderLayout.NORTH);
		
		//make labels
		JComponent labels = new JPanel();
		labels.setLayout(new BorderLayout());
		JLabel p1pits = new JLabel("P1 Pits", JLabel.CENTER);
		p1pits.setLocation(200, 50);
		JLabel p2pits = new JLabel("P2 Pits", JLabel.CENTER);
		JLabel p1mancala = new JLabel("P1 Mancala");
		JLabel p2mancala = new JLabel("P2 Mancala");
		labels.add(p1pits, BorderLayout.SOUTH);
		labels.add(p2pits, BorderLayout.NORTH);
		labels.add(p1mancala, BorderLayout.EAST);
		labels.add(p2mancala, BorderLayout.WEST);
		
		boardview.add(labels, BorderLayout.CENTER);
		
		//frame stuff
		JFrame frame = new JFrame();
		//frame.setSize(400, 200);
		frame.setTitle("Mancala Game");
		frame.add(boardview);
		//frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	public void stateChanged(ChangeEvent event) 
	{
		MancalaModel model = (MancalaModel) event.getSource();
		int[] p1board = model.getp1board();
		int[] p2board = model.getp2board();
		Component[] b1 = p1Pits.getComponents();
		Component[] b2 = p2Pits.getComponents();
		
		for (int i = 0; (i < b1.length) && (i < b2.length); i++)
		{
			JButton jb1 = (JButton) b1[i];
			updateButton(jb1, p1board[i]);
			
			JButton jb2 = (JButton) b2[i];
			// remember, p2 starts counting in reverse
			// as if they were on opposite side of table
			updateButton(jb2, p2board[(MancalaModel.PIT_SIZE-1-i)]);
		}
		
		updateButton(p1Mancala, p1board[MancalaModel.PIT_SIZE]);
		updateButton(p2Mancala, p2board[MancalaModel.PIT_SIZE]);
	}
	
	public void updateButton(JButton button, int data)
	{
		button.setText(data + "");
	}


}
