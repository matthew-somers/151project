package project;

// todo: player control tracking and free move from Mancala or undo
//       Gentlemen's rules....
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author jory
 */
public class MancalaModel extends Observable {

    int lastPlayer;
    int lastButtonId;
    private int[] p1board;
    private int[] p2board;
    private int[] reverseIndex; // reverse lookup index
    private int[] undoP1board;
    private int[] undoP2board;
    ArrayList<MancalaView> views;
    /**
     *
     */
    public static final int PIT_SIZE = 6;
    private boolean gameOver;
    private boolean freemove; // undo type free move only
    
    private int undoCount;
    private boolean legalMove;

    /**
     *
     */
    public MancalaModel() {
        p1board = new int[PIT_SIZE + 1]; // 6 pits, last is p1's mancala
        p2board = new int[PIT_SIZE + 1]; // 6 pits, last is p2's mancala
        reverseIndex = new int[PIT_SIZE + 1]; // reverse index across the game board
        undoP1board = new int[PIT_SIZE + 1];//for copying the board state
        undoP2board = new int[PIT_SIZE + 1];//for copying the board state
        undoCount = 0;
        for (int i = 0; i < PIT_SIZE; i++) {
            reverseIndex[i] = (PIT_SIZE) - i - 1;
        }
        reverseIndex[PIT_SIZE] = PIT_SIZE;
        views = new ArrayList<MancalaView>();
        gameOver = false;
        legalMove = false;
        // randomly set last player
        lastPlayer = 1 + (int) (Math.floor(Math.random() * 2.0));
    }

    /**
     *
     * @return
     */
    public int[] getp1board() {
        return p1board;
    }

    /**
     *
     * @return
     */
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
        p1board[PIT_SIZE] = 0;
        p2board[PIT_SIZE] = 0;
        gameOver = false;
        notifyViews();
    }

    /**
     *
     * @param mview
     */
    public void addView(MancalaView mview) {
        views.add(mview);
        notifyViews();
    }

    /**
     *
     * @param playerId
     * @param buttonId
     */
    public void makeMove(int playerId, int buttonId) {
        int moreStones;
        int currentPl;
        int currentButton;

        legalMove = false;
        // saftey checks for bad interfaces

        // ignore moves once game is over
        if (gameOver) {
            return;
        }

        // ignore moves of empty pits
        if (lastPlayer != playerId
                && ((playerId == 1 && p1board[buttonId] == 0)
                || (playerId == 2 && p2board[buttonId] == 0))) {
            return;
        }

        // really bad data
        if (playerId > 2 || playerId < 1) {
            // program error there are only 2 players
            return;
        }
        if (buttonId > PIT_SIZE || buttonId < 0) {
            // unknown pit
            return;
        }
        // end saftey checks

        //System.out.println("player id = " + playerId);
        //System.out.println("buttion id = " + buttonId);

        //game logic goes here
        // undo & set up check
        if (lastPlayer == playerId) {
            if (lastButtonId == buttonId
                    && ((lastPlayer == 1 && p1board[buttonId] == 0) || (lastPlayer == 2 && p2board[buttonId] == 0))) {
                //undo move
                if (undoCount < 3) {
                    System.arraycopy(undoP1board, 0, p1board, 0, p1board.length);
                    System.arraycopy(undoP2board, 0, p2board, 0, p2board.length);
                    undoCount++;
                    resetLastPlayer(); // give player back their turn
                    notifyViews();
                    freemove = true;
                }
                return;
            } else {
                // player cheated and clicked twice not an undo move
                //  ignore them
                return;
            }
        }
        // save current position and reset undo count
        System.arraycopy(p1board, 0, undoP1board, 0, p1board.length);
        System.arraycopy(p2board, 0, undoP2board, 0, p2board.length);
        if (!freemove) {
            undoCount = 0;
        }
        freemove = false;
        // continue with normal move
        lastPlayer = playerId;
        lastButtonId = buttonId;

        if (playerId == 1) {
            moreStones = p1board[buttonId];
            p1board[buttonId] = 0;
        } else {
            moreStones = p2board[buttonId];
            p2board[buttonId] = 0;
        }
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
        //System.out.println("PL=" + currentPl + "CB=" + currentButton + " 1=" + Arrays.toString(p1board) + " 2=" + Arrays.toString(p2board));
        if ((currentPl == lastPlayer) && (currentButton < PIT_SIZE)) {
            // condition checks if we are in correct state
            //  finished on move players side of board
            //    and not in mancala
            checkCaptureMove(currentPl, currentButton);
        }

        checkWinningMove();

        if (!gameOver && (currentPl == lastPlayer && currentButton == PIT_SIZE)) {
            resetLastPlayer(); // free turn
        }

        notifyViews();
        if (!gameOver) {
            legalMove = true;
        }
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
        if (player == 1) {
            if (p1board[pit] == 1) {
                if (p2board[reverseIndex[pit]] > 0) {
                    p1board[PIT_SIZE] += 1 + p2board[reverseIndex[pit]];
                    p1board[pit] = 0;
                    p2board[reverseIndex[pit]] = 0;
                }
            }
        } else {
            if (player == 2) {
                if (p2board[pit] == 1) {
                    if (p1board[reverseIndex[pit]] > 0) {
                        p2board[PIT_SIZE] += 1 + p1board[reverseIndex[pit]];
                        p2board[pit] = 0;
                        p1board[reverseIndex[pit]] = 0;
                    }
                }
            }
        }
    }

    /**
     *
     */
    public void notifyViews() {
        for (MancalaView view : views) {
            view.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return views.isEmpty();
    }

    /**
     *
     * @return
     */
    public boolean isDone() {
        return gameOver;
    }
    
    public boolean isLegalMove() {
        return legalMove;
    }

    public int getCurrentPlayer() {
        return lastPlayer == 1 ? 2 : 1;
    }

    private void resetLastPlayer() {
        lastPlayer = lastPlayer == 1 ? 2 : 1;
    }
    
    public void resetGame() {
        for (MancalaView view : views) {
            view.close();
        }
    	views.clear();
    	//gameOver = false;
    }
}
