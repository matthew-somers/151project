package project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * An interface for MancalaViews
 * @author Matthew Somers
 */
public interface MancalaView {

    /**
     * Method called by it's model.
     * @param event The model that called it.
     */
    public void stateChanged(ChangeEvent event);

    /**
     * Separate from stateChanged, updates specifics of button.
     * @param button The button to be updated.
     * @param data The data to put into the button.
     */
    public void updateButton(JButton button, int data);
    
    /**
     * A method to be able to write "undo" on a button from controller.
     * @return
     */
    public String getPitTextColor();
    
    /**
     * A method to be called when game is reset.
     */
    public void close();
}
