package wordToMusic;
import java.io.IOException;
import java.util.*;
public class Runner {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a word you want to search for: ");
		String word = in.next();
		System.out.println();
		ImageProcessor ip = new ImageProcessor(word);
		ip.search();
		System.out.println(ip.getUrl());
		ip.printImage();
		System.out.println("Dominant: " + ip.getDominantColor());
		System.out.println("Recessive: " + ip.getLeastColor());
		System.out.println("Average: " + ip.getAverageColor());
	}
}
