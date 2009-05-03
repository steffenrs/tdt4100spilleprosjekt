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
	
	
	
	public void clear()
	{
		textures.clear();
		level.clear();
		Actor.actors.clear();
	}
	
	/**
	 * @author Steffen R. Stenersen
	 * @param path to the level
	 */
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
						Sprite sprite;

						if (ic.getDescription().equals("block") || ic.getDescription().equals("platform")) {
							sprite = new Sprite(ic.getImage(), 1, 1, 1, false, "normal", io);
							new Block(sprite, x * 32, y * 32, true);
						}
						else if(ic.getDescription().equals("goal")){
							sprite = new Sprite(ic.getImage(), 1, 1, 1, false, "normal", io);
							new Goal(sprite, x * 32, y * 32, false);
						}
						else if(ic.getDescription().equals("green"))
						{
							sprite = new Sprite(ic.getImage(), 2, 1, 1, false, "normal", io);
							new Green(sprite, x * 32, y * 32, true, false);
						}
						else if(ic.getDescription().equals("red"))
						{
							sprite = new Sprite(ic.getImage(), 2, 1, 1, false, "normal", io);
							new Red(sprite, x * 32, y * 32, false, false);
						}
						else if(ic.getDescription().equals("blue"))
						{
							sprite = new Sprite(ic.getImage(), 2, 1, 1, false, "normal", io);
							new Blue(sprite, x * 32, y * 32, true, false);
						}
						else if(ic.getDescription().equals("purple"))
						{
							sprite = new Sprite(ic.getImage(), 2, 1, 1, false, "normal", io);
							new Purple(sprite, x * 32, y * 32, true, false);
						}
						else if(ic.getDescription().equals("player"))
						{
							sprite = new Sprite(ic.getImage(), 2, 4, 10, true, "normal", io);
							new Player(sprite, x*32,y*32);
						}
						else
						{
							sprite = new Sprite(ic.getImage(), 1, 1, 1, false, "normal", io);
							new Actor(sprite, x * 32, y * 32, false);
						}
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
