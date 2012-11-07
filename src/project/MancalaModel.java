package project;

import java.util.ArrayList;

public class MancalaModel 
{
	private int[] p1board;
	private int[] p2board;
	
	ArrayList<MancalaView> views;

	public MancalaModel()
	{
		p1board = new int[7]; // 6 pits, last is p1's mancala
		p2board = new int[7]; // 6 pits, last is p2's mancala
		views = new ArrayList<MancalaView>();
	}
	
	public void addView(MancalaView mview)
	{
		views.add(mview);
	}
}
