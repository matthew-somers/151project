package project;

import java.awt.*;

import javax.swing.*;

public class MancalaView extends JComponent
{
	public MancalaView()
	{	
		JComponent boardview = new JPanel();
		boardview.setLayout(new BorderLayout());
		JComponent p1Pits = new JPanel();
		p1Pits.setLayout(new FlowLayout());
		JComponent p2Pits = new JPanel();
		p2Pits.setLayout(new FlowLayout());

		//fill up pits with buttons
		for (int i = 0; i < 6; i++)
		{
			p1Pits.add(new JButton("0"));
			p2Pits.add(new JButton("0"));
		}
		p1Pits.setPreferredSize(new Dimension(325, 35));
		p2Pits.setPreferredSize(new Dimension(325, 35));
		
		JButton p1Mancala = new JButton("0");
		JButton p2Mancala = new JButton("0");
	
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
