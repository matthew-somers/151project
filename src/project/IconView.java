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
        pits = new JPanel();
        pits.setLayout(new GridLayout(2, 6));

        p1Mancala = new GameButton(null,"images/bluemacala.png",0,0,1,6);
        p2Mancala = new GameButton(null,"images/greenmacala.png",0,0,1,6);

        boardview.setLayout(new BorderLayout());


        //fill up pits with buttons
        //pit2
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) {
            JButton p2butt = new GameButton(controller,"images/greenround",80,80,2,5-i);
            pits.add(p2butt);
        }

        //pit1
        for (int i = 0; i < MancalaModel.PIT_SIZE; i++) {
            JButton p1butt = new GameButton(controller,"images/blueround.png",80,80,1,i);
            pits.add(p1butt);
        }

        //p1Mancala.setText();
        //p2Mancala.setText(starting_stones + "");

        p1Mancala.setPreferredSize(new Dimension(100, 120));
        p2Mancala.setPreferredSize(new Dimension(100, 120));

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
        Component[] pitComponents = pits.getComponents();

        //pits 2
        for (int i = 5, j = 0; i >= 0; i--, j++) {
            JButton jb2 = (JButton) pitComponents[i];
            updateButton(jb2, p2board[j]);
        }

        //pits 1
        for (int i = 6; i < 12; i++) {
            JButton jb1 = (JButton) pitComponents[i];
            updateButton(jb1, p2board[i % 6]);
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
    public void updateButton(JButton button, int data) {
        //update button with new icons instead of a number?
        //update button with new icons instead of a number?
        button.setText(data + "");
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
