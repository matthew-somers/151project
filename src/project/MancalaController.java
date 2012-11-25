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
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{
            GameButton gb = (GameButton) e.getSource();
		model.makeMove(gb.getPlayerId(),gb.getButtonId());
	}

}
