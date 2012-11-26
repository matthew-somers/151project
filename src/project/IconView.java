package project;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * this class is to create an icon view for macala game
 *
 * @author lamlu
 *
 */
public class IconView implements MancalaView {

    private JPanel pits;// the panel to contain both p1Pits and p2Pits
    private JPanel pit1;
    private JPanel pit2;
    
    private JButton p1Mancala;
    private JButton p2Mancala;
    private JPanel boardview;

    /**
     * constructor for IconView
     *
     * @param controller the controller of the Mancala game
     */
    public IconView(MancalaController controller) {

        boardview = new JPanel();
        pits = new JPanel(new BorderLayout());
        pit1 = new JPanel(new GridLayout(0,6));
        pit1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        pit2 = new JPanel(new GridLayout(0,6));
        pit2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        p1Mancala = new GameButton(null,"images/greenmacala.png",0,0,1,6);
        p2Mancala = new GameButton(null,"images/bluemacala.png",0,0,2,6);

        boardview.setLayout(new BorderLayout());


        //fill up pits with buttons
        //pit1
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) {
            JButton p1butt = new GameButton(controller,"images/greenround.png",80,80,1,i);
            pit1.add(p1butt);
        }

        //pit2
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) {
            JButton p2butt = new GameButton(controller,"images/blueround.png",80,80,2,i);
            pit2.add(p2butt);
        }

        //p1Mancala.setText();
        //p2Mancala.setText(starting_stones + "");

        p1Mancala.setPreferredSize(new Dimension(100, 120));
        p2Mancala.setPreferredSize(new Dimension(100, 120));
        
        
        pits.add(pit1, BorderLayout.SOUTH);
        pits.add(pit2, BorderLayout.NORTH);
        
        boardview.add(p1Mancala, BorderLayout.LINE_END);

        boardview.add(pits, BorderLayout.CENTER);
        boardview.add(p2Mancala, BorderLayout.LINE_START);

        JFrame frame = new JFrame();
        frame.setSize(700, 300);
        frame.setTitle("Icon View");
        frame.add(boardview);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * the method to be called when the state of the game is changed
     *
     * @param event the ChangeEvent
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        MancalaModel model = (MancalaModel) event.getSource();
        int[] p1board = model.getp1board();
        int[] p2board = model.getp2board();
        Component[] pit1Components = pit1.getComponents();
        Component[] pit2Components = pit2.getComponents();

        //pits 1
        for (int i = 0; i < 6; i++)
        {
            JButton jb1 = (JButton) pit1Components[i];
            updateButton(jb1, p1board[i]);
        }

        //pits 2
        for (int i = 0; i < 6; i++) 
        {
            JButton jb2 = (JButton) pit2Components[i];
            updateButton(jb2, p2board[i]);
        }
        updateButton(p1Mancala, p1board[MancalaModel.PIT_SIZE]);
        updateButton(p2Mancala, p2board[MancalaModel.PIT_SIZE]);
    }

    /**
     * method to update the View
     *
     * @param button the button
     * @param int the data index to be changed
     */
    @Override
    public void updateButton(JButton button, int data)
    {
    	String s = "";
    	int count = 0;
    	for (int i = 0; i < data; i++)
    	{
    		s += "o";
    		count++;
    		if(count >= 10)
    			s = "O" + "x" + count;
    	}
    	String htmlString = "<html> <font color=#FFFF00  >" + s + "</font>";
    		     button.setText(htmlString);
    	
    	button.setBorder(null);

    }

    /**
     * accessor to get the main panel to the view
     *
     * @return the panel of this view
     */
    public JPanel getPanel() {
        return boardview;
    }

    /**
     * accessor to get the dimension of this view
     *
     * @return the dimension of this view
     */
    public Dimension getDimension() {
        return new Dimension(700, 200);
    }

    public void setVisible() {
    }

}
