package project;

public class MancalaTest 
{
	
	public static void main(String[] args) 
	{
		MancalaModel model = new MancalaModel();
		MancalaView view = new MancalaView();
		model.addView(view);
	}

}
