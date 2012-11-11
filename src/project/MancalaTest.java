package project;

public class MancalaTest 
{
	
	public static void main(String[] args) 
	{
		int starting_stones = 3; // returned from interface eventually
		
		MancalaModel model = new MancalaModel(starting_stones);
		MancalaController controller = new MancalaController(model);
		NumView numview = new NumView(controller);
		IconView iconview = new IconView();
		
		model.addView(numview);
		model.addView(iconview);
	}

}
