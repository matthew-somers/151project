package project;

import java.awt.Component;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaView extends JComponent implements ChangeListener
{
	protected JPanel p1Pits;
	protected JPanel p2Pits;
	protected JButton p1Mancala;
	protected JButton p2Mancala;
	
	public MancalaView()
	{
		p1Pits = new JPanel();
		p2Pits = new JPanel();
		p1Mancala = new JButton();
		p2Mancala = new JButton();
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
			jb1.setText(p1board[i] + "");
			
			JButton jb2 = (JButton) b2[i];
			// remember, p2 starts counting in reverse
			// as if they were on opposite side of table
			jb2.setText(p2board[(MancalaModel.PIT_SIZE-1-i)] + "");
		}
		
		p1Mancala.setText(p1board[MancalaModel.PIT_SIZE] + "");
		p2Mancala.setText(p2board[MancalaModel.PIT_SIZE] + "");
	}

}
