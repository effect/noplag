package org.amse.fedotov.lcs_algo.tests;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.amse.fedotov.lcs_algo.comparator.ComparatorBasedOnLongestCommonSubsequence;
import org.amse.fedotov.lcs_algo.comparator.PercentResult;
import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.in_memory_program.InMemoryProgramFactory;
import org.amse.fedotov.noplag.lexer.pascal.PascalLexer;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.impl.Author;
import org.amse.fedotov.noplag.tests.TesterHelper;

public class TesterComparatorBasedOnLCS extends TestCase {
	
	private static final String PATH = TesterHelper.PATH + "lcs";
	
	private static final File DIRECTORY = new File(PATH);
	
	private static TesterHelper myHelper = new TesterHelper() {
		@Override
		protected File getDirectory() {
			return DIRECTORY;
		}
	};
	
	private final File myFile;
	
	private TesterComparatorBasedOnLCS(File file) {
		super(file.getName());
		myFile = file;
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("LCSTests");
		File[] files = myHelper.getFiles(".ok");

		for (File file : files) {
			suite.addTest(new TesterComparatorBasedOnLCS(file));
		}
		return suite;
	}
	
	@Override
	protected void runTest() throws Throwable {
		assertTrue(isCorrectResult(myFile));
	}
	
	public boolean isCorrectResult(File inputFile) throws IOException {
		Scanner in = new Scanner(inputFile);
		double left = in.nextDouble();
		double right = in.nextDouble();
		double res = getResult(inputFile.getCanonicalPath());
		PrintWriter out = new PrintWriter(inputFile.getCanonicalPath().replace(".ok", ".out"));
		out.println(res);
		out.close();
		return isInRange(res, left, right);
	}

	private double getResult(String filename) throws IOException {
		filename = filename.replace(".ok", "");
		IProgram prog0 = getProgram(filename + "_0.in");
		IProgram prog1 = getProgram(filename + "_1.in");
		ComparatorBasedOnLongestCommonSubsequence comp = new ComparatorBasedOnLongestCommonSubsequence();
		PercentResult res = comp.compare(prog0, prog1);
		return res.getValue();
	}
	
	private IProgram getProgram(String filename) throws IOException {
		File file = new File(filename);
		return InMemoryProgramFactory.getInstance().create(new Author("x"), filename, 
				FileUtils.createReader(file), new PascalLexer(FileUtils.createReader(file)));
	}
	
	private boolean isInRange(double x, double left, double right) {
		return left <= x && x <= right;
	}
}
