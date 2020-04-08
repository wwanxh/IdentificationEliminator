import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentificationEliminator {
	// Please modify the path of the directory containing regular expressions here
	public final static String regex_dir = "regex/";
	// Please modify the path of the directory containing input text here
	public final static String text_path = "text/cleaner_sample";
	// Regular Expression (regex) patterns read from regex files
	private List<Pattern> patterns;
	
	// Constructor, read regex patterns from files at the startup time
	public IdentificationEliminator() {
		File folder = new File(regex_dir);
		File[] listOfRegex = folder.listFiles();
		patterns = new ArrayList<Pattern>();
		for(File file : listOfRegex) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				while((st = br.readLine()) != null) {
					// Exclude lines that are either empty or comments (starting with  '#')
					if(st.length() == 0 || st.charAt(0) == '#') continue;
					// Split the line by the delimiter 
					String[] strs = st.split("\t");
					
					patterns.add(Pattern.compile(strs[1]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// Perform pattern matching and replacement on text
	// Argument: 	String text -> input text
	// Return:		text with identification eliminated
	public String eliminateIndentif(String text) {
		String newtext = text;
		for(Pattern pattern : patterns) {
			Matcher matcher = pattern.matcher(newtext);
			newtext = matcher.replaceAll(x -> randomIdentfGen(5));
		}
		return newtext;
	}
	// Generate random String to be replaced with identifications in the input text
	// Input:	the length of random string to be generated
	// Return:	a randomized string consisting of numbers and English alphabets 
	final String alphabet = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String randomIdentfGen(int length) {
		String r = "";
		for(int i = 0; i < length; i ++) {
			int index = (int)(Math.random() * alphabet.length());
			r += alphabet.charAt(index); 
		}
		return r;
	}
	
	public static void main(String[] args) {
		IdentificationEliminator IE = new IdentificationEliminator();
		StringBuilder text = new StringBuilder();
		// Change input text file path here --->
		try {
			BufferedReader br = new BufferedReader(new FileReader(text_path));
			String line;
			while((line = br.readLine()) != null) {
				text.append(line);
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(IE.eliminateIndentif(text.toString()));
	}
}
