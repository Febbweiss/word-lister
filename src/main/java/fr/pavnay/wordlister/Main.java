package fr.pavnay.wordlister;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
	
	private final static Options helpOptions = configHelpParameter();
	private final static Options options = configParameters();
	
	public static void main(String[] args) {
		final CommandLineParser parser = new DefaultParser();
		CommandLine firstLine = null;
		try {
			firstLine = parser.parse(helpOptions, args, true);
		
			boolean helpMode = firstLine.hasOption("help");
			if (helpMode) {
			    final HelpFormatter formatter = new HelpFormatter();
			    formatter.printHelp("Main", options, true);
			    System.exit(0);
			}

			CommandLine line = parser.parse(options, args);
			WordLister fileReader = new WordLister(line.getOptionValue("file", ""));
			fileReader.parse();
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		System.exit(0);
	}
	
	private static Options configHelpParameter() {
		 final Option helpFileOption = Option.builder("h") 
				.longOpt("help")
				.desc("Display help message").build();

	    final Options firstOptions = new Options();

	    firstOptions.addOption(helpFileOption);

	    return firstOptions;
	}
	
	private static Options configParameters() {
		
		final Option fileOption = Option.builder("f")
				.longOpt("file") //
				.desc("Path to the file to parse")
				.hasArg(true)
				.argName("path")
				.required(true)
				.build();

		final Options options = new Options();

		options.addOption(fileOption);

		return options;
	}
}
