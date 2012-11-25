package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

public class MancalaModel extends Observable {

    int lastPlayer;
    int lastCount;
    int lastButtonId;
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
        int moreStones;
        int currentPl;
        int currentButton;
        System.out.println("player id = "+playerId);
        System.out.println("buttion id = "+buttonId);

        //game logic goes here

        // undo & set up check
        if (lastPlayer == playerId) {
            //undo move
        }

        // continue with normal move
        lastPlayer = playerId;
        lastButtonId = buttonId;

        if (playerId == 1) {
            lastCount = p1board[buttonId];
            p1board[buttonId] = 0;
        } else {
            lastCount = p2board[buttonId];
            p2board[buttonId] = 0;
        }
        moreStones = lastCount;
        currentPl = lastPlayer;
        currentButton = lastButtonId;
        // move stone loop
        while (moreStones > 0) {
            currentButton++;
            //mancala check
            if (currentButton == 6) {
                if (currentPl != lastPlayer) {
                    continue;
                }
            }
            // array swap check
            if (currentButton == 7) {
                //currentPl = (currentPl == 1) ? 2 : 1;
                if(currentPl==1){currentPl=2;}
                else{currentPl=1;}
                currentButton = 0;
            }
            System.out.println("cp = "+currentPl+ " cb = "+currentButton);
            if (currentPl == 1) {
                p1board[currentButton] = p1board[currentButton] + 1;
            } else {
                p2board[currentButton] = p2board[currentButton] + 1;
            }
            System.out.println("1="+Arrays.toString(p1board) + " 2=" + Arrays.toString(p2board));
            moreStones--;
        }

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
