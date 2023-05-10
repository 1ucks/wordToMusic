import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class imagePuller {

	public static void main(String[] args) throws IOException {
		String key = "AIzaSyA5S3Uhzg6XRN1AdKHMEDI1boEvb2kThSs";
		String cx = "216e98543441a4b8f";
		//what we're searching v
		String qry = "yellow";
		String link = null;
		List<String> linkList = new ArrayList<String>();

		//Makes a request to our Google Custom Search API
		URL url = new URL(
				"https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q=" + qry
						+ "&searchType=image&imgColorType=color");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		//The response is in json :|

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {

			if (output.contains("\"link\": \"")) {

				link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(),
						output.indexOf("\","));
				System.out.println(link);
				linkList.add(link);
			}
		}
		conn.disconnect();


	}}
