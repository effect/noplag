package org.amse.fedotov.noplag.data_storage.impl.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.data_storage.impl.DataStorage;
import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.settings.Settings;

public class SourceIOAgent {

	/**
	 * Extension of storage program sources. 
	 */
	private static final String SOURCE_EXTENSION = ".dpr";
	
	public Reader getReader(IProgramId id) throws BadInputException {
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(getFullname(id)), Settings.getInternalCharset());
			return reader;
		} catch (FileNotFoundException e) {
			BadInputException ex = new BadInputException("Can't find source file stored in data storage for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} finally {
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					BadInputException ex = new BadInputException("Can't close source file stored in data storage for program with id " + id.getValue());
//					ex.initCause(e);
//					throw ex;
//				}
//			}
		}
	}
	
	public void write(IProgramId id, Reader sourceReader) throws BadInputException {
		File file = new File(getFullname(id));
		try {
			copyToFile(sourceReader, file);
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't copy source of program with id " + id.getValue() + " to data storage");
			ex.initCause(e);
			throw ex;
		}
	}
	
	/**
	 * Copies data from reader to file. 
	 * Output file writes in <code>Settings.getInternalCharset()</code> encoding.  
	 * @param reader reader.
	 * @param out output file. 
	 * @throws IOException 
	 */
	private void copyToFile(Reader reader, File out) throws IOException {
		FileUtils.createNewFile(out);
		Writer writer = new OutputStreamWriter(new FileOutputStream(out), Settings.getInternalCharset());
		// TODO use buffer
		int c;
		while ((c = reader.read()) >= 0) {
		    writer.write(c);
		}
		writer.close();
	}
	
	private String getFullname(IProgramId id) {
		return DataStorage.getStoragePath() + id.getValue() + SOURCE_EXTENSION; 
	}
	
}
