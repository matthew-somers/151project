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
    private int[] reverseIndex; // reverse lookup index
    ArrayList<MancalaView> views;
    public static final int PIT_SIZE = 6;
    Boolean gameOver;

    public MancalaModel() {
        p1board = new int[PIT_SIZE + 1]; // 6 pits, last is p1's mancala
        p2board = new int[PIT_SIZE + 1]; // 6 pits, last is p2's mancala
        reverseIndex = new int[PIT_SIZE + 1]; // reverse index across the game board

        for (int i = 0; i < PIT_SIZE; i++) {
            reverseIndex[i] = (PIT_SIZE) - i - 1;
        }
        reverseIndex[PIT_SIZE] = PIT_SIZE;
        views = new ArrayList<>();
        gameOver = false;
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
        Arrays.fill(p1board, 0, PIT_SIZE, starting_stones);
        Arrays.fill(p2board, 0, PIT_SIZE, starting_stones);
        gameOver = false;
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
        System.out.println("player id = " + playerId);
        System.out.println("buttion id = " + buttonId);

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
                if (currentPl == 1) {
                    currentPl = 2;
                } else {
                    currentPl = 1;
                }
                currentButton = 0;
            }
            //System.out.println("cp = " + currentPl + " cb = " + currentButton);
            if (currentPl == 1) {
                p1board[currentButton] = p1board[currentButton] + 1;
            } else {
                p2board[currentButton] = p2board[currentButton] + 1;
            }
           
            moreStones--;
        }
        //System.out.println("Reverse:" + Arrays.toString(reverseIndex));
        System.out.println("PL="+currentPl+"CB="+currentButton+" 1=" + Arrays.toString(p1board) + " 2=" + Arrays.toString(p2board));
        if ((currentPl == lastPlayer) && (currentButton < PIT_SIZE)) {
            // condition checks if we are in correct state
            //  finished on move players side of board
            //    and not in mancala
           checkCaptureMove(currentPl,currentButton);
        }
        
        checkWinningMove();
        
        // not checking if last move was mancala and giving player another turn
        
        notifyViews();
    }

    private void checkWinningMove() {
        int board2sum = 0, board1sum = 0;
        for (int i = 0; i < PIT_SIZE; i++) {
            board1sum += p1board[i];
            board2sum += p2board[i];
        }
        if (board1sum == 0) {
            for (int i = 0; i < PIT_SIZE; i++) {
                p2board[i] = 0;
            }
            p2board[PIT_SIZE] += board2sum;
            gameOver = true;
        } else if (board2sum == 0) {
            for (int i = 0; i < PIT_SIZE; i++) {
                p1board[i] = 0;
            }
            p1board[PIT_SIZE] += board1sum;
            gameOver = true;
        }
    }

    private void checkCaptureMove(int player, int pit) {
        // check if this last pit has 1 stone. If so then check
        //  opposite pit (use reverseIndex[pit]) to see if it
        //  has any stones... if so move both sets of stones to 
        //  players mancala (PIT_SZIE)
        if (player == 1) {
            // player 1
            if (p1board[pit] == 1) {
                // placed stone in empty pit
                if (p2board[reverseIndex[pit]] > 0) {
                    //move all stones to p1 mancala
                    p1board[PIT_SIZE]+=1+p2board[reverseIndex[pit]];
                    p1board[pit]=0;
                    p2board[reverseIndex[pit]]=0;
                    
                    
                }
            }
        } else {if (player == 2) {
            // player 1
            if (p2board[pit] == 1) {
                // placed stone in empty pit
                if (p1board[reverseIndex[pit]] > 0) {
                    //move all stones to p1 mancala
                    p2board[PIT_SIZE]+=1+p1board[reverseIndex[pit]];
                    p2board[pit]=0;
                    p1board[reverseIndex[pit]]=0;
                    
                    
                }
            }
        }
            // player two ... same as above
        }
    }

    public void notifyViews() {
        for (MancalaView view : views) {
            view.stateChanged(new ChangeEvent(this));
        }
    }

    public boolean isEmpty() {
        return views.isEmpty();
    }

    public boolean isDone() {
        return gameOver;
    }
}
