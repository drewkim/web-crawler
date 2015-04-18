import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.io.*;

public class Spider
{
	public static ArrayList <String> tested = new ArrayList<String>();
	
	public Spider(String startLink, String exceptionLink) throws IOException
	{
		//printAll(new URL("http://www.fox.com/new-girl/"));
		spider(new URL(startLink), new URL(exceptionLink));
	}

	public static void printAll(URL url) throws IOException
	{
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) 
		{
//			System.out.println(line);
		}
	}
	
	public static void spider(URL url, URL prevURL) throws IOException
	{
		try
		{
			URLConnection con = url.openConnection();
			InputStream is;
			is = con.getInputStream();		
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) 
			{
				if(searchForLink(line))
				{
					int index1 = line.indexOf("http://");
					int index2 = searchForEnd(line, index1);
					if(index1 >= 0 && index2 >= 0 && index1 < index2)
					{
						String link = line.substring(index1, index2);
						boolean tested1 = false;					
						for(int i = 0; i < tested.size(); i++)
						{
							if(link.equals(tested.get(i)))
								tested1 = true;
						}
						if(!link.equals(url.toString()) && !link.equals(prevURL.toString()) && !tested1)
						{
							try {
								PrintWriter out = new PrintWriter("links.txt");
								out.append(link);
								out.append('\n');
								out.close();
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							
							System.out.println(link);
							tested.add(link);
							spider(new URL(link), url);
						}
					}
				}
			}
		}
		catch(IOException e)
		{
			
		}
		catch(IllegalArgumentException e)
		{
			
		}
		finally
		{
			
		}

	}

	public static int searchForEnd(String line, int startIndex)
	{
		if(startIndex < 0)
			return 0;
		for(int i = startIndex; i < line.length(); i++)
		{
			if(line.charAt(i) == '"' || line.charAt(i) == '\'')
				return i;
		}
		return 0;
	}

	public static boolean searchForLink(String line)
	{
		if(line.indexOf("link") >= 0)
			return true;
		else if(line.indexOf("href") >= 0)
			return true;
		else
			return false;
	}
}