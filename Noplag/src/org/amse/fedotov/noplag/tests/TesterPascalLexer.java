package org.amse.fedotov.noplag.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.amse.fedotov.noplag.lexer.ILexer;
import org.amse.fedotov.noplag.lexer.pascal.PascalLexer;
import org.amse.fedotov.noplag.model.IToken;

public class TesterPascalLexer extends TestCase {
	
	private static final String PATH = TesterHelper.PATH + "pascal";
	
	private static final File DIRECTORY = new File(PATH);

	private static TesterHelper myHelper = new TesterHelper() {
		@Override
		protected File getDirectory() {
			return DIRECTORY;
		}
	};
	
	private final File myFile;
	
	private TesterPascalLexer(File file) {
		super(file.getName());
		myFile = file;
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("LexerTests");
		File[] files = myHelper.getFiles(".in");

		for (File file : files) {
			suite.addTest(new TesterPascalLexer(file));
		}
		return suite;
	}
	
	@Override
	protected void runTest() throws Throwable {
		assertTrue(isCorrectResult(myFile.getCanonicalPath()));
	}
	
	private boolean isCorrectResult(String fullFilename) {
		try {
			BufferedReader reader = myHelper.getReader(fullFilename);
			String okFileName = fullFilename.replace(".in", ".ok");
			BufferedReader readerOK = myHelper.getReader(okFileName);
			String outFileName = fullFilename.replace(".in", ".out");
			PrintWriter writer = new PrintWriter(outFileName);
	
			ILexer lexer = new PascalLexer(reader);
			String line;
			boolean res = true;
			
			while ((line = readerOK.readLine()) != null) {
				IToken pascalToken = lexer.nextToken();
				line = readerOK.readLine(); // token
				writer.println(pascalToken.toString());
				if (!line.equals(pascalToken.toString())) {
					res = false;
				}
			}
			if (lexer.hasNextToken()) {
				res = false;
			}
			
			try {
				lexer.nextToken();
				res = false;
			} catch (NullPointerException e) {
				// ok
			} catch (Exception e) {
				res = false;
			}

			writer.close();
			if (res) {
				File outFile = new File(outFileName);
				outFile.delete();
			}
			return res;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}

