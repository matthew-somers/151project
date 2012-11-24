package project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MenuFrame extends JFrame
{
	private MancalaModel model;
	private MancalaController controller;
	private int numStones;
	
	public MenuFrame(MancalaModel model2, final MancalaController controller2)
	{
		model = model2;
		controller = controller2;
		
		setTitle("Mancala");
		setLayout(new FlowLayout());
		setSize(new Dimension(200,200));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton simpleViewButton = new JButton("SimpleView");
		JButton LuxuryViewButton = new JButton("LuxuryView");
		JButton IconViewButton = new JButton("IconView");
		
		simpleViewButton.setPreferredSize(new Dimension(100,50));
		simpleViewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{	
				NumView numview = new NumView(controller);
				if (model.isEmpty())
				{
					setStartingStones();
					model.initializeStones(numStones);
				}
				
				model.addView(numview);
			}
		});
		
		IconViewButton.setPreferredSize(new Dimension(100,50));
		IconViewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{	
				IconView iconview = new IconView(controller);
				if (model.isEmpty())
				{
					setStartingStones();
					model.initializeStones(numStones);
				}
				
				model.addView(iconview);
			}
		});
		
		LuxuryViewButton.setPreferredSize(new Dimension(100,50));
		LuxuryViewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{	
				LuxuryView luxview = new LuxuryView(controller);
				if (model.isEmpty())
				{
					setStartingStones();
					model.initializeStones(numStones);
				}
				
				model.addView(luxview);
			}
		});

		this.add(simpleViewButton);
		this.add(IconViewButton);
		this.add(LuxuryViewButton);
	}
	
	public void setStartingStones()
	{
		Object[] possibleValues = { "3", "4"};
		Object selectedValue = JOptionPane.showInputDialog(this,
				"Select Number of Starting Stones:\n","Pick One",JOptionPane.PLAIN_MESSAGE,null, possibleValues,possibleValues[0]);
		if(selectedValue != null)
			numStones = Integer.parseInt((String)selectedValue);
	}
}
