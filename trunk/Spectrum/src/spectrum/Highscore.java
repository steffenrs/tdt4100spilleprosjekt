package spectrum;

import java.io.File;

public class Highscore {
	
	private static double highscore = 100000;
	private static double totalScore;
	private File highscoreFile;
	
	public Highscore(){
		
	}
	
	public static double getHighscore()
	{
		return highscore;
	}

	public static void updateHighscore()
	{
		if(highscore > 0)
		highscore -= 5;	
	}
	
	public static void addPenalty()
	{
		if(highscore > 5000)
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
	
	
//	public void saveHighscore(){
//		if (condition) {
//			
//		}
//	}
	
	
}
