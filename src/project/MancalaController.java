package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MancalaController implements ActionListener
{
	private MancalaModel model;
	
	public MancalaController(MancalaModel model)
	{
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		model.makeMove( (JButton) e.getSource());
	}

}
