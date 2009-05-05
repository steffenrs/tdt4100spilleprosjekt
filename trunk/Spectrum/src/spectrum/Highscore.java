package spectrum;

public class Highscore {
	
	private static double highscore = 100000;
	private static double totalScore;
	
	public static double getHighscore()
	{
		return highscore;
	}

	public static void updateHighscore()
	{
		highscore -= 5;	
	}
	
	public static void addPenalty()
	{
		highscore -= 5000;
	}
	
	public static void updateTotalScore()
	{
		totalScore += highscore;
		highscore = 100000;
	}
	
	public static double getTotalscore()
	{
		return totalScore;
	}
}
