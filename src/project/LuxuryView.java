package project;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * this class is to create a luxury view for macala game
 * @author lamlu
 *
 */
public class LuxuryView implements MancalaView
{

	private JPanel pits;// the panel to contain both p1Pits and p2Pits
	private JPanel p1MancalaPanel;
	private JPanel p2MancalaPanel;
	private JButton p1Mancala;
	private JButton p2Mancala;
	private BackgroundPanel boardview;
	
	/**
	 * this inner class is to create a panel with background image
	 * this inner class extends the JPanel
	 * @author lamlu
	 *
	 */
	class BackgroundPanel extends JPanel
	{
		ImageIcon bgIcon = new ImageIcon("images/woodenBackground.png");
		Image bg = bgIcon.getImage();
		public void paintComponent(Graphics g)
		{
			g.drawImage(bg,0 , 0, getWidth(), getHeight(), this);
		}
	}
	
	/**
	 * constructor for LuxuryView
	 * @param controller the controller of the Mancala game
	 */
	public LuxuryView(MancalaController controller)
	{
		p1MancalaPanel = new BackgroundPanel();
		p1MancalaPanel.setLayout(new FlowLayout());
		
		p2MancalaPanel = new BackgroundPanel();
		p2MancalaPanel.setLayout(new FlowLayout());
		boardview = new BackgroundPanel();
		pits = new BackgroundPanel();
		pits.setLayout(new GridLayout(2,6));
		
		p1Mancala = new JButton( new ImageIcon("images/woodenMancala.png"));
		p1Mancala.setBorder(null);
		p1Mancala.setHorizontalTextPosition(JButton.CENTER);
		p1Mancala.setVerticalTextPosition(JButton.CENTER);
		p2Mancala = new JButton(new ImageIcon("images/woodenMancala.png"));
		p2Mancala.setBorder(null);
		p2Mancala.setHorizontalTextPosition(JButton.CENTER);
		p2Mancala.setVerticalTextPosition(JButton.CENTER);
		
		boardview.setLayout(new BorderLayout());


		//fill up pits with buttons
		//pit2
		for (int i = 0; i < MancalaModel.PIT_SIZE; i++)
		{
			JButton p2butt = new JButton(new ImageIcon("images/woodenround.png"));	
			p2butt.setHorizontalTextPosition(JButton.CENTER);
			p2butt.setVerticalTextPosition(JButton.CENTER);
			p2butt.setPreferredSize(new Dimension (80,80));
			p2butt.setBorder(null);			
			p2butt.addActionListener(controller);	
			
			pits.add(p2butt);
			

		}
		
		//pit1
		for(int i = 0; i < MancalaModel.PIT_SIZE; i++)
		{
			JButton p1butt = new JButton(new ImageIcon("images/woodenround.png"));
			p1butt.setPreferredSize(new Dimension(80,80));
			p1butt.setBorder(null);
			p1butt.setHorizontalTextPosition(JButton.CENTER);
			p1butt.setVerticalTextPosition(JButton.CENTER);
			p1butt.addActionListener(controller);
			pits.add(p1butt);
		}
		
		//p1Mancala.setText();
		//p2Mancala.setText(starting_stones + "");
		
		p1Mancala.setPreferredSize(new Dimension (300,100));
		p2Mancala.setPreferredSize(new Dimension(300,100));
		
		p1MancalaPanel.add(p1Mancala);
		p2MancalaPanel.add(p2Mancala);
		boardview.add(p1MancalaPanel, BorderLayout.PAGE_END);

		boardview.add(pits, BorderLayout.CENTER);
		boardview.add(p2MancalaPanel, BorderLayout.PAGE_START);	
		
		JFrame frame = new JFrame();
		frame.setSize(700, 300);
		frame.setTitle("Luxury View");
		frame.add(boardview);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	/**
	 * the method to be called when the state of the game is changed
	 * @param event the ChangeEvent
	 */
	public void stateChanged(ChangeEvent event) 
	{
		MancalaModel model = (MancalaModel) event.getSource();
		int[] p1board = model.getp1board();
		int[] p2board = model.getp2board();
		Component[] pitComponents = pits.getComponents();

		//pits 2
		for (int i = 5, j = 0; i >= 0 ; i--, j++)
		{
			JButton jb2 = (JButton) pitComponents[i];
//	jb2.setLayout(new GridLayout(10,10));			
			updateButton(jb2, p2board[j]);
		}
		
		//pits 1
		for (int i = 6; i < 12; i++)
		{
			JButton jb1 = (JButton) pitComponents[i];
			updateButton(jb1, p2board[i%6]);

		}
		updateButton(p1Mancala, p1board[MancalaModel.PIT_SIZE]);
		updateButton(p2Mancala, p2board[MancalaModel.PIT_SIZE]);
	}
	
	/**
	 * method to update the View
	 * @param button the button 
	 * @param int the data index to be changed
	 */
	public void updateButton(JButton button, int data)
	{
		//update button with new icons instead of a number?
		button.setText(data + "");
		button.setBorder(null);
		//System.out.println(data);
		//for (int z = 0; z < data; z++)
		//{
	//		button.add(new JLabel(new ImageIcon("images/stone.png")));
	//		System.out.println("add");
	//	}
		

	}
}
