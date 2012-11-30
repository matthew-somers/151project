package project;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * this class is to create a luxury view for macala game
 *
 * @author lamlu
 *
 */
public class LuxuryView implements MancalaView {

    private JPanel pits;// the panel to contain both p1Pits and p2Pits
    private JPanel pit1;
    private JPanel pit2;
    private JPanel p1MancalaPanel;
    private JPanel p2MancalaPanel;
    private GameButton p1Mancala;
    private GameButton p2Mancala;
    private BackgroundPanel boardview;

    @Override
    public String getPitTextColor() {
        return "#FFFF00";
    }

    /**
     * this inner class is to create a panel with background image this inner
     * class extends the JPanel
     *
     * @author lamlu
     *
     */
    class BackgroundPanel extends JPanel {

        ImageIcon bgIcon = new ImageIcon("images/woodenBackground.png");
        Image bg = bgIcon.getImage();

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * constructor for LuxuryView
     *
     * @param controller the controller of the Mancala game
     */
    public LuxuryView(MancalaController controller) {
        p1MancalaPanel = new BackgroundPanel();
        p1MancalaPanel.setLayout(new FlowLayout());

        p2MancalaPanel = new BackgroundPanel();
        p2MancalaPanel.setLayout(new FlowLayout());
        boardview = new BackgroundPanel();
        pits = new JPanel(new BorderLayout());
        pit1 = new BackgroundPanel();
        pit1.setLayout(new GridLayout(0,6));
        pit1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pit2 = new BackgroundPanel();
        pit2.setLayout(new GridLayout(0,6));
        pit2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        

        p1Mancala = new GameButton(this,null, "images/woodenMancala.png", 0, 0,1,6);
        p2Mancala = new GameButton(this,null, "images/woodenMancala.png", 0, 0,2,6);

        boardview.setLayout(new BorderLayout());


        //fill up pits with buttons
        //pit2
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) 
        {
            GameButton p2butt = new GameButton(this,controller, "images/woodenround.png", 80, 80,2,i);
            pit2.add(p2butt);
        }

        //pit1
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) {
            GameButton p1butt = new GameButton(this,controller, "images/woodenround.png", 80, 80,1,i);
            pit1.add(p1butt);
        }

        //p1Mancala.setText();
        //p2Mancala.setText(starting_stones + "");

        p1Mancala.setPreferredSize(new Dimension(300, 100));
        p2Mancala.setPreferredSize(new Dimension(300, 100));
        
        pits.add(pit1, BorderLayout.SOUTH);
        pits.add(pit2, BorderLayout.NORTH);
        p1MancalaPanel.add(p1Mancala);
        p2MancalaPanel.add(p2Mancala);
        boardview.add(p1MancalaPanel, BorderLayout.PAGE_END);
        
        
        boardview.add(pits, BorderLayout.CENTER);
        boardview.add(p2MancalaPanel, BorderLayout.PAGE_START);

        JFrame frame = new JFrame();
        frame.setSize(650, 402);
        frame.setResizable(false);
        frame.setTitle("Luxury View");
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
    		if(count >= 10) {
                s = "O" + "x" + count;
            }
    	}
    	String htmlString = "<html> <font color="+getPitTextColor()+">" + s + "</font>";
    		     button.setText(htmlString);
    	
    	button.setBorder(null);

    }

}
