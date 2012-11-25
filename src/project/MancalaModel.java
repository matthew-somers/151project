package project;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

public class MancalaModel extends Observable {

    private int[] p1board;
    private int[] p2board;
    ArrayList<MancalaView> views;
    public static final int PIT_SIZE = 6;

    public MancalaModel() {
        p1board = new int[PIT_SIZE + 1]; // 6 pits, last is p1's mancala
        p2board = new int[PIT_SIZE + 1]; // 6 pits, last is p2's mancala

        views = new ArrayList<MancalaView>();
    }

    public int[] getp1board() {
        return p1board;
    }

    public int[] getp2board() {
        return p2board;
    }

    /**
     * class to initialize the number of stones and update the view
     *
     * @param starting_stones the number of stones
     */
    public void initializeStones(int starting_stones) {
        for (int i = 0; i < PIT_SIZE; i++) {
            p1board[i] = starting_stones;
            p2board[i] = starting_stones;
        }
        notifyViews();
    }

    public void addView(MancalaView mview) {
        views.add(mview);
        notifyViews();
    }

    public void makeMove(int playerId, int buttonId) {
        //game logic goes here

        //test
        p1board[0] = 9;
        p2board[6] = 5; // p2 mancala
        p2board[5] = 2;
        p2board[0] = 1;
        p1board[6] = 6;

        notifyViews();
    }

    public void notifyViews() {
        for (MancalaView view : views) {
            view.stateChanged(new ChangeEvent(this));
        }
    }

    public boolean isEmpty() {
        return views.isEmpty();
    }

}
