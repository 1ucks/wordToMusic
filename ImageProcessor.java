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
        //if there is a space, change it to a + sign
        for(int i = 0; i < word.length(); i ++) {
        	if(word.charAt(i) == ' ') {
        		this.word = this.word.substring(0, i) + "+" + this.word.substring(i+1);

        	}
        }
        
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
    
    public String getDominantColor() throws IOException {
    	//returns a string of the most common color in the image in all lower case
    	
    	//getting the image from the link
    	BufferedImage im = ImageIO.read(this.imageUrl);
    	
    	
    	//cycling through every pixel, skipping every other if its large
    	int inc = 1;
		int imArea = im.getHeight() * im.getWidth();
    	if(im.getWidth() > 1000 || im.getHeight() > 1000) {
    		inc = 2;
    	}
    	int numO = 0;
    	int numY = 0;
    	int numB = 0;
    	int numG = 0;
    	int numP = 0;
    	int numR= 0;

    	for(int i = 0; i < im.getHeight(); i+=inc) {
    		
    		for(int j = 0; j < im.getWidth(); j += inc) {
    			//getting the rgb for the current pixel
    			int pixelUno = im.getRGB(j, i);
    			Color colorUno = new Color(pixelUno, true);
    			
    			int r = colorUno.getRed();
    			int b = colorUno.getBlue();
    			int g = colorUno.getGreen();
    			
    			//converting the rgb to hsb which lets us determine which color we are working with
    			float[] HSB = new float[3];
    			Color.RGBtoHSB(r, g, b, HSB);
    			
    			float hue = HSB[0]*360;
    			//setting the color w/ its name
    			if(hue < 15 || hue >= 345) {
    				numR++;
    			}
    			else if(hue >= 15 && hue < 45) {
    				numO++;
    			}
    			else if(hue >= 45 && hue < 75) {
    				numY++;
    			}
    			else if(hue >= 75 && hue < 145) {
    				numG++;
    			}
    			else if(hue >= 146 && hue < 250) {
    				numB++;
    			}
    			else if(hue>= 250 && hue < 345) {
    				numP++;
    			}
    			
    		}
    	}
    	//determining the most common color
    	int[] colors = {numR, numO, numY, numG, numB, numP};
    	int max = colors[0];
    	int count=0;
    	int ind = 0;
    	for(int i : colors) {
    		if(i > max) {
    			max = i;
    			ind = count;
    		}
    		count++;
    	}
    	
    	//returning
    	switch(ind) {
	    	case 0:
	    		return "red";
	    	case 1:
	    		return "orange";
	    	case 2:
	    		return "yellow";
	    	case 3:
	    		return "green";
	    	case 4:
	    		return "blue";
	    	case 5:
	    		return "purple";
	    	}
    	System.out.println("fail");
    	return "fail";
    	
    }
    public String getLeastColor() throws IOException {
    	//returns a string of the most common color in the image in all lower case
    	
    	//getting the image from the link
    	BufferedImage im = ImageIO.read(this.imageUrl);
    	
    	
    	//cycling through every pixel, skipping every other if its large
    	int inc = 1;
		int imArea = im.getHeight() * im.getWidth();
    	if(im.getWidth() > 1000 || im.getHeight() > 1000) {
    		inc = 2;
    	}
    	
    	int numO = 0;
    	int numY = 0;
    	int numB = 0;
    	int numG = 0;
    	int numP = 0;
    	int numR= 0;

    	for(int i = 0; i < im.getHeight(); i+=inc) {
    		
    		for(int j = 0; j < im.getWidth(); j += inc) {
    			//getting the rgb for the current pixel
    			int pixelUno = im.getRGB(j, i);
    			Color colorUno = new Color(pixelUno, true);
    			
    			int r = colorUno.getRed();
    			int b = colorUno.getBlue();
    			int g = colorUno.getGreen();
    			
    			//converting the rgb to hsb which lets us determine which color we are working with
    			float[] HSB = new float[3];
    			Color.RGBtoHSB(r, g, b, HSB);
    			
    			float hue = HSB[0]*360;
    			//setting the color w/ its name
    			if(hue < 15 || hue >= 345) {
    				numR++;
    			}
    			else if(hue >= 15 && hue < 45) {
    				numO++;
    			}
    			else if(hue >= 45 && hue < 75) {
    				numY++;
    			}
    			else if(hue >= 75 && hue < 145) {
    				numG++;
    			}
    			else if(hue >= 146 && hue < 250) {
    				numB++;
    			}
    			else if(hue>= 250 && hue < 345) {
    				numP++;
    			}
    			
    		}
    	}
    	//determining the least common color
    	int[] colors = {numR, numO, numY, numG, numB, numP};
    	int min = colors[0];
    	int count=0;
    	int ind = 0;
    	
    	for(int i : colors) {
    		if(i < min && i!= 0) {
    			min = i;
    			ind = count;
    		}
    		count++;
    	}
    	
    	//returning
    	switch(ind) {
	    	case 0:
	    		return "red";
	    	case 1:
	    		return "orange";
	    	case 2:
	    		return "yellow";
	    	case 3:
	    		return "green";
	    	case 4:
	    		return "blue";
	    	case 5:
	    		return "purple";
	    	}
    	System.out.println("fail");
    	return "fail";
    
    }
    public String getAverageColor() throws IOException {
    	//returns a string of the most common color in the image in all lower case
    	
    	//getting the image from the link
    	BufferedImage im = ImageIO.read(this.imageUrl);
    	
    	
    	//cycling through every pixel, skipping every other if its large
    	int inc = 1;
		int imArea = im.getHeight() * im.getWidth();
    	if(im.getWidth() > 1000 || im.getHeight() > 1000) {
    		inc = 2;
    	}
    	
    	double total = 0.0;
    	int numPixels =0;
    	
    	
    	
    	
    	for(int i = 0; i < im.getHeight(); i+=inc) {
    		
    		for(int j = 0; j < im.getWidth(); j += inc) {
    			//getting the rgb for the current pixel
    			int pixelUno = im.getRGB(j, i);
    			Color colorUno = new Color(pixelUno, true);
    			
    			int r = colorUno.getRed();
    			int b = colorUno.getBlue();
    			int g = colorUno.getGreen();
    			
    			//converting the rgb to hsb which lets us determine which color we are working with
    			float[] HSB = new float[3];
    			Color.RGBtoHSB(r, g, b, HSB);
    			
    			float hue = HSB[0]*360;

    			total+= hue;
    			numPixels++;
    			
    			
    		}
    	}
    	//averaging
    	total/=numPixels;
    	
    	
    	if(total < 15 || total >= 345) {
			return "red";
		}
		else if(total >= 15 && total < 45) {
			return "orange";
		}
		else if(total >= 45 && total < 75) {
			return "yellow";
		}
		else if(total >= 75 && total < 145) {
			return "green";
		}
		else if(total >= 146 && total < 250) {
			return "blue";
		}
		else if(total>= 250 && total < 345) {
			return "purple";
		}
    	System.out.println("fail");
    	return "fail";
    
    }
    
    public void printImage() throws IOException {
    	//method to print the image chosen in a new window
    	
    	//getting the image from the link
		BufferedImage im = ImageIO.read(this.imageUrl);
		
		Image im2 = im.getScaledInstance(im.getWidth() / 2, im.getHeight() / 2, Image.SCALE_DEFAULT);
		//opens the window with the image v
		JFrame f = new JFrame("Image Display");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adds image to f
		f.getContentPane().add(new JLabel(new ImageIcon(im2)));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
    }
    public void printImage(String domC, String recC) throws IOException {
    	//overloaded method to print the image chosen in a new window
    	
    	//getting the image from the link
		BufferedImage im = ImageIO.read(this.imageUrl);
		
		Image im2 = im.getScaledInstance(im.getWidth() / 2, im.getHeight() / 2, Image.SCALE_DEFAULT);
		//opens the window with the image v
		JFrame f = new JFrame("Image Display");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//adds image to f
		f.getContentPane().add(new JLabel(new ImageIcon(im2)));
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
    }
    
}
