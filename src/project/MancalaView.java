package project;

import java.awt.Component;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface MancalaView 
{
	public void stateChanged(ChangeEvent event);
	public void updateButton(JButton button, int data);
}
