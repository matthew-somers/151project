package project;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * this is the interface of MancalaView
 *
 * @author Matthew Somers
 *
 */
public interface MancalaView {

    /**
     *
     * @param event
     */
    public void stateChanged(ChangeEvent event);

    /**
     *
     * @param button
     * @param data
     */
    public void updateButton(JButton button, int data);
}
