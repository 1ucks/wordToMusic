package wordToMusic;
//vs code made me add this for the project, dont know if its different for eclipse, but you can prob just delte it

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageProcessor {
    //Class to take a word and deal with all the implications an image brings, including fetching it via google custom search api

    private String word;
    private URL imageUrl;
    
    public ImageProcessor(String word) throws MalformedURLException {
        //word chosen by the user
        this.word = word;
        //url for the image selected by the api
        this.imageUrl = new URL("https://www.googleapis.com");
    }

    public void search() throws IOException{
        //uses the inputted word to make a request to the api and sets the imageUrl field to the returned link
            //the key and code are what identify our api to google because we have specific search requests
        String key = "AIzaSyA5S3Uhzg6XRN1AdKHMEDI1boEvb2kThSs";
		String apiCode = "216e98543441a4b8f";

        //initializing the var for our link
        String link = null;

        //makes the url to follow to sauce the desired image
            //includes the key, code, query,and some of our filters such as only color images
		URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + apiCode + "&q=" + this.word + "&searchType=image&imgColorType=color");

        //connecting to the api and making a request
		HttpURLConnection connect = (HttpURLConnection)url.openConnection();
        //pulling tnot pushing data
		connect.setRequestMethod("GET");
        //gonna get the response in json which is a massive set of data, but we will filter it for the link later
		connect.setRequestProperty("Accept", "application/json");
		//geting the response from the api
		BufferedReader br = new BufferedReader(new InputStreamReader((connect.getInputStream())));
        
        String output;
        //reading each line with the reader and if its null, we stop reading
		while ((output = br.readLine()) != null) {
            //looking for "Link": in the json
			if (output.contains("\"link\": \"")) {
                //substringing the link out of the json and putting it in the var
				link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(),output.indexOf("\","));
                break;
            }
        }
        //setting our var to the selected url
        this.imageUrl = new URL(link);

    }

    public URL getUrl(){
        //returns imageUrl
        return this.imageUrl;
    }
    
    
    
    public void printImage() throws IOException {
    	//method to print the image chosen in a new window
    	
    	//getting the image from the link
		BufferedImage im = ImageIO.read(this.imageUrl);
		
		Image im2 = im.getScaledInstance(im.getWidth() / 2, im.getHeight() / 2, Image.SCALE_DEFAULT);
		//opens the window with the image v
		JFrame f = new JFrame("aNFT");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adds image to f
		f.getContentPane().add(new JLabel(new ImageIcon(im2)));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
    }
}
