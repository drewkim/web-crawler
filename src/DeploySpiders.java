import java.io.IOException;


public class DeploySpiders 
{
	public static void main (String[] args) throws IOException
	{
		Spider spider1 = new Spider("http://www.techcrunch.com/", "https://www.netflix.com");
		Spider spider2 = new Spider("https://www.youtube.com/", "https://www.khanacademy.org");
		Spider spider3 = new Spider("https://www.yahoo.com/", "http://espn.go.com/");
	}
}
