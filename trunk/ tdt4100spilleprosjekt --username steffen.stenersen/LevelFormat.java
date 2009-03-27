package oving9;

import java.io.InputStream;
import java.io.OutputStream;

public interface LevelFormat 
{
	public void read(Level level, InputStream inputStream);
	public void write(Level level, OutputStream outputStream);
}
