package spectrum;

public class Highscore {
	
	private static double highscore = 100000;
	
	public static double getHighscore()
	{
		return highscore;
	}

	public static void updateHighscore(){
		highscore -= 5;
		
	}
	
	public static void addPenalty(){
		highscore -= 5000;
	}
	
}
