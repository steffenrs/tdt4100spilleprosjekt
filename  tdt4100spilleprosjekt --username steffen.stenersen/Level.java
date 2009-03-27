package oving9;

import java.util.ArrayList;
import java.util.Iterator;

public class Level 
{
	ArrayList<String> level;
	ArrayList<Move> moves;
	private String name;
	
	public Level() 
	{ 
		level = new ArrayList<String>();
		moves = new ArrayList<Move>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String[] getLines()
	{
		String[] a = new String[level.size()];
		level.toArray(a);
		return a;
	}
	
	public void addLine(String a)
	{
		level.add(a);
	}
	
	public Iterator<Move> moves()
	{
		return moves.iterator();
	}
	
	public void addMove(Move move)
	{
		moves.add(move);
	}
}
