package fr.pavnay.wordlister;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class WordLister {

	private File file;
	
	private static final String LETTER = "\\w\\p{InCombiningDiacriticalMarks}";
	private static final String WORD = "[" + LETTER + "]([-]?[" + LETTER + "])*";
	private static final String NUMBER = "[+-]?\\d([, .]?\\d)*[%]?";
	
	private static final Pattern PATTERN = Pattern.compile("(?U)(" + NUMBER + "|" + WORD + ")++");
	
	public WordLister(String path) {
		if( StringUtils.isBlank(path) ) {
			throw new IllegalArgumentException("No file provided.");
		}
		
		file = new File(path);
		if( !file.exists() ) {
			throw new IllegalArgumentException("File " + file + " not found.");
		}
		
		if( !file.isFile() ) {
			throw new IllegalArgumentException(file + " is not a valid file.");
		}
	}
	
	public void parse() {
		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(file));
			String line = null;
			
			while( (line = br.readLine()) != null ) {
				if( StringUtils.isNotBlank(line) ) {
					Matcher matcher = PATTERN.matcher(line);
					while( matcher.find() ) {
						System.out.println(matcher.group());
					}
				}
			}
			
			br.close();
		}catch( IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
