package org.amse.fedotov.noplag.tests;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;

import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.in_memory_program.InMemoryProgramFactory;
import org.amse.fedotov.noplag.lexer.pascal.PascalLexer;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.impl.Author;
import org.amse.fedotov.noplag.program_storage.IProgramStorage;
import org.amse.fedotov.noplag.settings.Settings;

public class TesterDatabase extends TestCase {

	private static final String PATH = TesterHelper.PATH + "database";
	
	private static final File DIRECTORY = new File(PATH);
	
	private static final String myTestsNoplagPath = Settings.getNoplagDataPath() + "tests" + File.separatorChar;
	
	private IProgram[] myPrograms;
	private IProgramStorage myProgramStorage;

	public TesterDatabase() {
		super("TesterDatabase");
		// set special test directory for project
		Settings.setNoplagDataPath(myTestsNoplagPath);
		clearDirectoryWithTests();
	}
	
	@Override
	public void setUp() {
		Settings.setNoplagDataPath(myTestsNoplagPath);
	}
	
	@Override
	public void tearDown() {
		clearDirectoryWithTests();
	}
	
	private void clearDirectoryWithTests() {
		FileUtils.deleteDir(new File(myTestsNoplagPath));
	}
	
	public void testAddOneProgram() throws IOException {
		String[] filename = {"file1.dpr"};
		String[] authorname = {"x"}; 
		addPrograms(filename, authorname);
		checkTest();
		checkTest();
	}
	
	public void testAddTwoPrograms() throws IOException {
		String[] filename = {"file1.dpr", "file2.dpr"};
		String[] authorname = {"x", "y"};
		addPrograms(filename, authorname);
		checkTest();
	}
	
	public void testAddOneProgramAndLoadItFromDataStorage() throws IOException {
		String[] filename = {"file1.dpr"};
		String[] authorname = {"x"}; 
		addPrograms(filename, authorname);
		checkTest();
		Settings.setNoplagDataPath(myTestsNoplagPath);
		checkTest();
	}
	
	private void addPrograms(String[] filename, String[] authorname) throws IOException {
		myPrograms = getPrograms(filename, authorname);
		IProgramStorage programStorage = Settings.getDatabase();
		for (IProgram p : myPrograms) {
			programStorage.addProgram(p);
		}
		myProgramStorage = programStorage; 
	}
	
	private void checkTest() {
		int num = 0;
		for (IProgram p : myProgramStorage) {
			assertTrue(isCorrectResult(myPrograms[num], p));
			num++;
		}
		assertEquals(myPrograms.length, num);
	}

	private boolean isCorrectResult(IProgram a, IProgram b) {
		assertEquals(a.getFilename(), b.getFilename());
		assertEquals(a.getAuthor(), b.getAuthor());
		assertTrue(Arrays.equals(a.getSource(), b.getSource()));
		assertEquals(a.getTokens(), b.getTokens());
		return true;
	}
	
	private IProgram[] getPrograms(String[] filename, String[] authorname) throws IOException {
		int n = filename.length;
		IProgram[] program = new IProgram[n];
		for (int i = 0; i < n; i++) {
			File file = new File(DIRECTORY, filename[i]);
			IAuthor author = new Author(authorname[i]);
			program[i] = InMemoryProgramFactory.getInstance().create(author, filename[i], 
					FileUtils.createReader(file), new PascalLexer(FileUtils.createReader(file)));
		}
		return program;
	}
	
}
