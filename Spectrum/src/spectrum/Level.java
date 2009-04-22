package spectrum;

import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Level 
{
	private ArrayList<ImageIcon> textures;
	private ArrayList<String[]> level;
	ImageObserver io;
	
	/**
	 * @author Steffen R. Stenersen
	 * @param io
	 */
	public Level(ImageObserver io)
	{
		textures = new ArrayList<ImageIcon>();
		level = new ArrayList<String[]>();
		this.io = io;
	}
	
	/**
	 * @author Steffen R. Stenersen
	 * @param path to the level
	 */
	public void Load(String path)
	{
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader(path));
			
			String input;
			int step = 0;
			
			while((input = reader.readLine()) != null)
			{
				if(input.length() != 0)
				{
					if(input.charAt(0) == '[')
					{
						step++;
						input = reader.readLine();
					}
				}
				
				switch(step)
				{
				case 1: 
					if(input.equals(""))
						continue;
					textures.add(new ImageIcon(System.getProperty("user.dir") + "\\Content\\" + input + ".png"));
					int t = input.indexOf("_");
					textures.get(textures.size() - 1).setDescription(input.substring(0, t));
					break;
					
					case 2:
					break;
				case 3:
					String[] cells = input.split(" ");
					level.add(cells);
					break;
				}
			}
		
			int current = 0;
			for (int x = 0; x < level.get(0).length; x++) 
			{
				for (int y = 0; y < level.size(); y++) 
				{
					current = Integer.parseInt(level.get(y)[x]);
					if(current != -1)
					{
						ImageIcon ic = textures.get(current);
						Sprite sprite = new Sprite(ic.getImage(), 1, 1, io);

						if (ic.getDescription().equals("block")) {
							new Block(sprite, x * 32, y * 32, true);
						}
						else if(ic.getDescription().equals("goal")){
							new Goal(sprite, x * 32, y * 32, false);
						}
						else
							new Actor(sprite, x * 32, y * 32, false);
						
					}
				}
			}
		}
		
		catch(IOException exc)
		{
			System.out.println(exc.getMessage());
		}
	}
	
	public void Load(URL path)
	{
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader(path.getPath()));
			
			String input;
			int step = 0;
			
			while((input = reader.readLine()) != null)
			{
				if(input.length() != 0)
				{
					if(input.charAt(0) == '[')
					{
						step++;
						input = reader.readLine();
					}
				}
				
				switch(step)
				{
				case 1: 
					if(input.equals(""))
						continue;
					textures.add(new ImageIcon(getClass().getResource("content//" + input + ".png")));
					int t = input.indexOf("_");
					textures.get(textures.size() - 1).setDescription(input.substring(0, t));
					break;
					
					case 2:
					break;
				case 3:
					String[] cells = input.split(" ");
					level.add(cells);
					break;
				}
			}
		
			int current = 0;
			for (int x = 0; x < level.get(0).length; x++) 
			{
				for (int y = 0; y < level.size(); y++) 
				{
					current = Integer.parseInt(level.get(y)[x]);
					if(current != -1)
					{
						ImageIcon ic = textures.get(current);
						Sprite sprite = new Sprite(ic.getImage(), 1, 1, io);

						if (ic.getDescription().equals("block")) {
							new Block(sprite, x * 32, y * 32, true);
						}
						else if(ic.getDescription().equals("goal")){
							new Goal(sprite, x * 32, y * 32, false);
						}
						else
							new Actor(sprite, x * 32, y * 32, false);
						
					}
				}
			}
			reader.close();
		}
		
		catch(IOException exc)
		{
			System.out.println(exc.getMessage());
		}
	}
}
