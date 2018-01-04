package fr.pavnay.wordlister;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;

public class MainTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Rule
	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testHelpLongOption() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Usage help message",
						"usage: Main -f <path>" + System.lineSeparator()
								+ " -f,--file <path>   Path to the file to parse" + System.lineSeparator(),
						systemOutRule.getLog());
			}
		});
		Main.main(new String[] { "--help" });
	}

	@Test
	public void testHelpShortOption() {
		exit.expectSystemExitWithStatus(0);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Usage help message",
						"usage: Main -f <path>" + System.lineSeparator()
								+ " -f,--file <path>   Path to the file to parse" + System.lineSeparator(),
						systemOutRule.getLog());
			}
		});
		Main.main(new String[] { "-h" });

	}

	@Test
	public void testWithUnknownShortOption() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Unknown short option", "Unrecognized option: -d" + System.lineSeparator(),
						systemErrRule.getLog());
			}
		});
		Main.main(new String[] {"-d"});
	}

	@Test
	public void testWithUnknownLongOption() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Unknown long option", "Unrecognized option: --debug" + System.lineSeparator(),
						systemErrRule.getLog());
			}
		});
		Main.main(new String[] {"--debug"});
	}
	
	@Test
	public void testFileShortOption() {
		exit.expectSystemExitWithStatus(0);
		Main.main(new String[] { "-f", MainTest.class.getResource("/5flow.txt").getPath() });
	}
	
	@Test
	public void testFileLongOption() {
		exit.expectSystemExitWithStatus(0);
		Main.main(new String[] { "--file", MainTest.class.getResource("/5flow.txt").getPath() });
	}
	
	@Test
	public void testFileNotFound() {
		thrown.expect(IllegalArgumentException.class);
	    thrown.expectMessage("File " + File.separatorChar + "tmp" + File.separatorChar + "notfound.txt not found.");
		Main.main(new String[] { "--file", "/tmp/notfound.txt" });
	}
	
	@Test
	public void testFileIsDirectory() {
		String tmpDir = System.getProperty("java.io.tmpdir");
		thrown.expect(IllegalArgumentException.class);
		if( tmpDir.endsWith("/") ) {
		    tmpDir = tmpDir.substring(0,tmpDir.length() - 1 );
	    } 
	    thrown.expectMessage( tmpDir + " is not a valid file.");
		Main.main(new String[] { "--file", tmpDir });
	}
	
	@Test
	public void test5FlowFile() {
		
		final String test = "5flow" + System.lineSeparator()
		+ "est" + System.lineSeparator()
		+ "une" + System.lineSeparator()
		+ "société" + System.lineSeparator()
		+ "française" + System.lineSeparator()
		+ "basée" + System.lineSeparator()
		+ "à" + System.lineSeparator()
		+ "Tours" + System.lineSeparator()
		+ "pour" + System.lineSeparator()
		+ "la" + System.lineSeparator()
		+ "R" + System.lineSeparator()
		+ "D" + System.lineSeparator()
		+ "et" + System.lineSeparator()
		+ "sur" + System.lineSeparator()
		+ "Paris" + System.lineSeparator()
		+ "pour" + System.lineSeparator()
		+ "les" + System.lineSeparator()
		+ "projets" + System.lineSeparator()
		+ "clients" + System.lineSeparator()
		+ "Depuis" + System.lineSeparator()
		+ "sa" + System.lineSeparator()
		+ "création" + System.lineSeparator()
		+ "5flow" + System.lineSeparator()
		+ "s" + System.lineSeparator()
		+ "est" + System.lineSeparator()
		+ "focalisée" + System.lineSeparator()
		+ "sur" + System.lineSeparator()
		+ "le" + System.lineSeparator()
		+ "domaine" + System.lineSeparator()
		+ "du" + System.lineSeparator()
		+ "workflow" + System.lineSeparator()
		+ "afin" + System.lineSeparator()
		+ "d" + System.lineSeparator()
		+ "y" + System.lineSeparator()
		+ "apporter" + System.lineSeparator()
		+ "une" + System.lineSeparator()
		+ "nouvelle" + System.lineSeparator()
		+ "approche" + System.lineSeparator()
		+ "plus" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "simplicité" + System.lineSeparator()
		+ "plus" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "réactivité" + System.lineSeparator()
		+ "plus" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "souplesse" + System.lineSeparator()
		+ "Avec" + System.lineSeparator()
		+ "plus" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "10" + System.lineSeparator()
		+ "ans" + System.lineSeparator()
		+ "d" + System.lineSeparator()
		+ "expériences" + System.lineSeparator()
		+ "d" + System.lineSeparator()
		+ "aide" + System.lineSeparator()
		+ "à" + System.lineSeparator()
		+ "la" + System.lineSeparator()
		+ "traduction" + System.lineSeparator()
		+ "des" + System.lineSeparator()
		+ "processus" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "nos" + System.lineSeparator()
		+ "clients" + System.lineSeparator()
		+ "en" + System.lineSeparator()
		+ "workflow" + System.lineSeparator()
		+ "nos" + System.lineSeparator()
		+ "clients" + System.lineSeparator()
		+ "de" + System.lineSeparator()
		+ "5flow" + System.lineSeparator()
		+ "peuvent" + System.lineSeparator()
		+ "en" + System.lineSeparator()
		+ "témoigner" + System.lineSeparator()
		+ "un" + System.lineSeparator()
		+ "processus" + System.lineSeparator()
		+ "ne" + System.lineSeparator()
		+ "se" + System.lineSeparator()
		+ "transforme" + System.lineSeparator()
		+ "pas" + System.lineSeparator()
		+ "forcement" + System.lineSeparator()
		+ "en" + System.lineSeparator()
		+ "un" + System.lineSeparator()
		+ "workflow" + System.lineSeparator()
		+ "aussi" + System.lineSeparator()
		+ "simplement" + System.lineSeparator()
		+ "que" + System.lineSeparator()
		+ "cela" + System.lineSeparator()
		+ "C" + System.lineSeparator()
		+ "est" + System.lineSeparator()
		+ "pourquoi" + System.lineSeparator()
		+ "nous" + System.lineSeparator()
		+ "vous" + System.lineSeparator()
		+ "accompagnons" + System.lineSeparator()
		+ "pour" + System.lineSeparator()
		+ "modéliser" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "processus" + System.lineSeparator()
		+ "et" + System.lineSeparator()
		+ "nous" + System.lineSeparator()
		+ "les" + System.lineSeparator()
		+ "traduisons" + System.lineSeparator()
		+ "en" + System.lineSeparator()
		+ "un" + System.lineSeparator()
		+ "ou" + System.lineSeparator()
		+ "plusieurs" + System.lineSeparator()
		+ "workflows" + System.lineSeparator()
		+ "efficients" + System.lineSeparator()
		+ "Nous" + System.lineSeparator()
		+ "créons" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "portails" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "workflows" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "référentiels" + System.lineSeparator()
		+ "Vous" + System.lineSeparator()
		+ "collaborateurs" + System.lineSeparator()
		+ "managers" + System.lineSeparator()
		+ "clients" + System.lineSeparator()
		+ "fournisseurs" + System.lineSeparator()
		+ "les" + System.lineSeparator()
		+ "utilisez" + System.lineSeparator()
		+ "vous" + System.lineSeparator()
		+ "les" + System.lineSeparator()
		+ "managez" + System.lineSeparator()
		+ "pour" + System.lineSeparator()
		+ "les" + System.lineSeparator()
		+ "adapter" + System.lineSeparator()
		+ "à" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "contrats" + System.lineSeparator()
		+ "ou" + System.lineSeparator()
		+ "vos" + System.lineSeparator()
		+ "changements" + System.lineSeparator()
		+ "d" + System.lineSeparator()
		+ "organisation" + System.lineSeparator();
		
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Right text format", test, systemOutRule.getLog());
			}
		});
		
		Main.main(new String[] { "-f", MainTest.class.getResource("/5flow.txt").getPath() });
	}

	@Test
	public void testInputStreamReaderFile() {
		final String test = "public" + System.lineSeparator()
				+ "class" + System.lineSeparator()
				+ "InputStreamReader" + System.lineSeparator()
				+ "extends" + System.lineSeparator()
				+ "Reader" + System.lineSeparator()
				+ "An" + System.lineSeparator()
				+ "InputStreamReader" + System.lineSeparator()
				+ "is" + System.lineSeparator()
				+ "a" + System.lineSeparator()
				+ "bridge" + System.lineSeparator()
				+ "from" + System.lineSeparator()
				+ "byte" + System.lineSeparator()
				+ "streams" + System.lineSeparator()
				+ "to" + System.lineSeparator()
				+ "character" + System.lineSeparator()
				+ "streams" + System.lineSeparator()
				+ "It" + System.lineSeparator()
				+ "reads" + System.lineSeparator()
				+ "bytes" + System.lineSeparator()
				+ "and" + System.lineSeparator()
				+ "decodes" + System.lineSeparator()
				+ "them" + System.lineSeparator()
				+ "into" + System.lineSeparator()
				+ "characters" + System.lineSeparator()
				+ "using" + System.lineSeparator()
				+ "a" + System.lineSeparator()
				+ "specified" + System.lineSeparator()
				+ "charset" + System.lineSeparator()
				+ "The" + System.lineSeparator()
				+ "charset" + System.lineSeparator()
				+ "that" + System.lineSeparator()
				+ "it" + System.lineSeparator()
				+ "uses" + System.lineSeparator()
				+ "may" + System.lineSeparator()
				+ "be" + System.lineSeparator()
				+ "specified" + System.lineSeparator()
				+ "by" + System.lineSeparator()
				+ "name" + System.lineSeparator()
				+ "or" + System.lineSeparator()
				+ "may" + System.lineSeparator()
				+ "be" + System.lineSeparator()
				+ "given" + System.lineSeparator()
				+ "explicitly" + System.lineSeparator()
				+ "or" + System.lineSeparator()
				+ "the" + System.lineSeparator()
				+ "platform" + System.lineSeparator()
				+ "s" + System.lineSeparator()
				+ "default" + System.lineSeparator()
				+ "charset" + System.lineSeparator()
				+ "may" + System.lineSeparator()
				+ "be" + System.lineSeparator()
				+ "accepted" + System.lineSeparator()
				+ "Each" + System.lineSeparator()
				+ "invocation" + System.lineSeparator()
				+ "of" + System.lineSeparator()
				+ "one" + System.lineSeparator()
				+ "of" + System.lineSeparator()
				+ "an" + System.lineSeparator()
				+ "InputStreamReader" + System.lineSeparator()
				+ "s" + System.lineSeparator()
				+ "read" + System.lineSeparator()
				+ "methods" + System.lineSeparator()
				+ "may" + System.lineSeparator()
				+ "cause" + System.lineSeparator()
				+ "one" + System.lineSeparator()
				+ "or" + System.lineSeparator()
				+ "more" + System.lineSeparator()
				+ "bytes" + System.lineSeparator()
				+ "to" + System.lineSeparator()
				+ "be" + System.lineSeparator()
				+ "read" + System.lineSeparator()
				+ "from" + System.lineSeparator()
				+ "the" + System.lineSeparator()
				+ "underlying" + System.lineSeparator()
				+ "byte-input" + System.lineSeparator()
				+ "stream" + System.lineSeparator()
				+ "To" + System.lineSeparator()
				+ "enable" + System.lineSeparator()
				+ "the" + System.lineSeparator()
				+ "efficient" + System.lineSeparator()
				+ "conversion" + System.lineSeparator()
				+ "of" + System.lineSeparator()
				+ "bytes" + System.lineSeparator()
				+ "to" + System.lineSeparator()
				+ "characters" + System.lineSeparator()
				+ "more" + System.lineSeparator()
				+ "bytes" + System.lineSeparator()
				+ "may" + System.lineSeparator()
				+ "be" + System.lineSeparator()
				+ "read" + System.lineSeparator()
				+ "ahead" + System.lineSeparator()
				+ "from" + System.lineSeparator()
				+ "the" + System.lineSeparator()
				+ "underlying" + System.lineSeparator()
				+ "stream" + System.lineSeparator()
				+ "than" + System.lineSeparator()
				+ "are" + System.lineSeparator()
				+ "necessary" + System.lineSeparator()
				+ "to" + System.lineSeparator()
				+ "satisfy" + System.lineSeparator()
				+ "the" + System.lineSeparator()
				+ "current" + System.lineSeparator()
				+ "read" + System.lineSeparator()
				+ "operation" + System.lineSeparator()
				+ "For" + System.lineSeparator()
				+ "top" + System.lineSeparator()
				+ "efficiency" + System.lineSeparator()
				+ "consider" + System.lineSeparator()
				+ "wrapping" + System.lineSeparator()
				+ "an" + System.lineSeparator()
				+ "InputStreamReader" + System.lineSeparator()
				+ "within" + System.lineSeparator()
				+ "a" + System.lineSeparator()
				+ "BufferedReader" + System.lineSeparator()
				+ "For" + System.lineSeparator()
				+ "example" + System.lineSeparator()
				+ "BufferedReader" + System.lineSeparator()
				+ "in" + System.lineSeparator()
				+ "new" + System.lineSeparator()
				+ "BufferedReader" + System.lineSeparator()
				+ "new" + System.lineSeparator()
				+ "InputStreamReader" + System.lineSeparator()
				+ "System" + System.lineSeparator()
				+ "in" + System.lineSeparator();
		
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Right text format", test, systemOutRule.getLog());
			}
		});
		Main.main(new String[] { "-f", MainTest.class.getResource("/InputStreamReader.txt").getPath() });
	}
	
	@Test
	public void testTiobeFile() {
		final String test = "Programming" + System.lineSeparator()
				+ "Language" + System.lineSeparator()
				+ "Ratings" + System.lineSeparator()
				+ "Change" + System.lineSeparator()
				+ "Java" + System.lineSeparator()
				+ "13.231%" + System.lineSeparator()
				+ "-5.52%" + System.lineSeparator()
				+ "C" + System.lineSeparator()
				+ "9.293%" + System.lineSeparator()
				+ "+0.09%" + System.lineSeparator()
				+ "C" + System.lineSeparator()
				+ "5.343%" + System.lineSeparator()
				+ "-0.07%" + System.lineSeparator()
				+ "Python" + System.lineSeparator()
				+ "4.482%" + System.lineSeparator()
				+ "+0.91%" + System.lineSeparator()
				+ "C" + System.lineSeparator()
				+ "3.012%" + System.lineSeparator()
				+ "-0.65%" + System.lineSeparator()
				+ "JavaScript" + System.lineSeparator()
				+ "2.972%" + System.lineSeparator()
				+ "+0.27%" + System.lineSeparator()
				+ "Visual" + System.lineSeparator()
				+ "Basic" + System.lineSeparator()
				+ "NET" + System.lineSeparator()
				+ "2.909%" + System.lineSeparator()
				+ "-0.26%" + System.lineSeparator()
				+ "PHP" + System.lineSeparator()
				+ "1.897%" + System.lineSeparator()
				+ "-1.23%" + System.lineSeparator()
				+ "Delphi" + System.lineSeparator()
				+ "Object" + System.lineSeparator()
				+ "Pascal" + System.lineSeparator()
				+ "1.744%" + System.lineSeparator()
				+ "-0.21%" + System.lineSeparator()
				+ "Assembly" + System.lineSeparator()
				+ "language" + System.lineSeparator()
				+ "1.722%" + System.lineSeparator()
				+ "-0.72%" + System.lineSeparator()
				+ "R" + System.lineSeparator()
				+ "1.605%" + System.lineSeparator()
				+ "-0.11%" + System.lineSeparator()
				+ "MATLAB" + System.lineSeparator()
				+ "1.604%" + System.lineSeparator()
				+ "-0.36%" + System.lineSeparator()
				+ "Ruby" + System.lineSeparator()
				+ "1.593%" + System.lineSeparator()
				+ "-0.39%" + System.lineSeparator()
				+ "Go" + System.lineSeparator()
				+ "1.570%" + System.lineSeparator()
				+ "-0.43%" + System.lineSeparator()
				+ "Perl" + System.lineSeparator()
				+ "1.562%" + System.lineSeparator()
				+ "-0.80%" + System.lineSeparator()
				+ "Scratch" + System.lineSeparator()
				+ "1.550%" + System.lineSeparator()
				+ "+0.47%" + System.lineSeparator()
				+ "Visual" + System.lineSeparator()
				+ "Basic" + System.lineSeparator()
				+ "1.489%" + System.lineSeparator()
				+ "-0.43%" + System.lineSeparator()
				+ "PL" + System.lineSeparator()
				+ "SQL" + System.lineSeparator()
				+ "1.453%" + System.lineSeparator()
				+ "-0.06%" + System.lineSeparator()
				+ "Objective-C" + System.lineSeparator()
				+ "1.412%" + System.lineSeparator()
				+ "-0.83%" + System.lineSeparator()
				+ "Swift" + System.lineSeparator()
				+ "1.389%" + System.lineSeparator()
				+ "-0.65%" + System.lineSeparator();;
		
		exit.expectSystemExit();
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals("Right text format", test, systemOutRule.getLog());
			}
		});
		Main.main(new String[] { "-f", MainTest.class.getResource("/tiobe_201711.txt").getPath() });
	}
}
