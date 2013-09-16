package org.amse.fedotov.noplag.data_storage.impl.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.amse.fedotov.noplag.data_storage.IMetainfo;
import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.data_storage.impl.DataStorage;
import org.amse.fedotov.noplag.data_storage.impl.Metainfo;
import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.impl.Author;
import org.amse.fedotov.noplag.settings.Settings;

public class MetainfoIOAgent {

	private static final String METAINFO_EXTENSION = ".met";
	
	public void write(IProgramId id, IMetainfo metainfo) throws BadInputException {
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(getFullname(id)), Settings.getInternalCharset());
			writer.write(metainfo.getAuthor().getName());
			writer.write("\n");
			writer.write(metainfo.getFilename());
		} catch (FileNotFoundException e) {
			BadInputException ex = new BadInputException("Can't create metainfo file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't write to metainfo file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					BadInputException ex = new BadInputException("Can't close writer of metainfo file for program with id " + id.getValue());
					ex.initCause(e);
					throw ex;
				} 
			}
		}
	}
	
	public IMetainfo read(IProgramId id) throws BadInputException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(getFullname(id)), Settings.getInternalCharset()));
			IAuthor author = new Author(reader.readLine());
			String filename = reader.readLine(); 
			return new Metainfo(author, filename);
		} catch (FileNotFoundException e) {
			BadInputException ex = new BadInputException("Can't find metainfo file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't read from metainfo for program file with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					BadInputException ex = new BadInputException("Can't close reader of metainfo file for program with id " + id.getValue());
					ex.initCause(e);
					throw ex;
				}
			} 
		}
	}
	
	private String getFullname(IProgramId id) {
		return DataStorage.getStoragePath() + id.getValue() + METAINFO_EXTENSION; 
	}
	
}
