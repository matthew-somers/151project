package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class NumView extends MancalaView 
{
	public NumView(MancalaController controller)
	{
		super();
		
		JComponent boardview = new JPanel();
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


}
