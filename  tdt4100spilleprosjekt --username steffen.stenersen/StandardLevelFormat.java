package oving9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class StandardLevelFormat implements LevelFormat 
{

	public void read(Level level, InputStream inputStream) throws IOException
	{
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(inputStream));
		
		String readString;

			while((readString = br.readLine()) != null)
			{
				level.addLine(readString);
			}	
		} 

	public void write(Level level, OutputStream outputStream) 
	{
		
	}

}
