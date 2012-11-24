package project;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * this class is to create the Frame of Mancala game that can plug in different
 * style of game board
 * @author lamlu
 *
 */
public class MancalaFrame extends JFrame
{
	private MancalaView view;

	int numStones;
	/**
	 * constructor for the MancalaFrame
	 * this class serves as the context class of the MancalaView Strategy pattern
	 * this class can plug in different style of view that implements the MancalaView
	 * @param view the MancalaView style that can be plugged in
	 */
	public MancalaFrame(MancalaView v)
	{
		numStones = 0;
		view = v;
		this.setTitle("Mancala Game");
		this.add(view.getPanel());
		this.setSize(view.getDimension());
		

	}
	
	/**
	 * create a dialog box to prompt the user enter the number of starting stones
	 */
	public void setStartingStones()
	{
		Object[] possibleValues = { "3", "4"};
		Object selectedValue = JOptionPane.showInputDialog(this,
				"Select Number of Starting Stones:\n","Pick One",JOptionPane.PLAIN_MESSAGE,null, possibleValues,possibleValues[0]);
		if(selectedValue != null)
			numStones = Integer.parseInt((String)selectedValue);
	}
	
	public int getNumStones()
	{
		setStartingStones();
		return numStones;
	}

}
