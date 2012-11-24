package project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * this class is to test the program
 * contains main method
 * @author Matthew Somers
 *
 */
public class MancalaTest 
{
	/**
	 * main method
	 * @param args not used
	 */
	public static void main(String[] args) 
	{		

		//the menu frame to let user to choose the style of the board
		//and start the game
		final JFrame menuframe = new JFrame();
		
		//the button to choose simpleView
		JButton simpleViewButton = new JButton("Simple View");
		simpleViewButton.setPreferredSize(new Dimension(100,50));
		simpleViewButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				MancalaModel model = new MancalaModel();//starting_stones);
				MancalaController controller = new MancalaController(model);
				
				MancalaView numview = new NumView(controller);
				model.addView(numview);
				MancalaFrame mframe = new MancalaFrame(numview);
				mframe.add(numview.getPanel());
				mframe.setVisible(true);
				mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				model.initializeStones(mframe.getNumStones());
				
			}
		});
		
		//button to choose colorview
		JButton colorViewButton = new JButton("Color View");
		colorViewButton.setPreferredSize(new Dimension(100,50));
		colorViewButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) 
			{
				MancalaModel model = new MancalaModel();//starting_stones);
				MancalaController controller = new MancalaController(model);
				MancalaView iconview = new IconView(controller);
				model.addView(iconview);
				MancalaFrame mframe = new MancalaFrame(iconview);
				mframe.add(iconview.getPanel());
				mframe.setVisible(true);
				mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				model.initializeStones(mframe.getNumStones());		
			}
			
		});
		
		
		//button to choose luxury view
		JButton luxuryViewButton = new JButton("Luxury View");
		luxuryViewButton.setPreferredSize(new Dimension(100,50));
		luxuryViewButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event) 
			{
				MancalaModel model = new MancalaModel();//starting_stones);
				MancalaController controller = new MancalaController(model);
				MancalaView luxuryview = new LuxuryView(controller);
				model.addView(luxuryview);
				MancalaFrame mframe = new MancalaFrame(luxuryview);
				mframe.add(luxuryview.getPanel());
				mframe.setVisible(true);
				mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				model.initializeStones(mframe.getNumStones());
				
			}
		});
		
		//the button to choose 
		menuframe.setTitle("Mancala");
		menuframe.setLayout(new FlowLayout());
		menuframe.add(simpleViewButton);
		menuframe.add(colorViewButton);
		menuframe.add(luxuryViewButton);
		menuframe.setSize(new Dimension(150,200));
		menuframe.setResizable(false);
		//frame.pack();
		menuframe.setVisible(true);
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
