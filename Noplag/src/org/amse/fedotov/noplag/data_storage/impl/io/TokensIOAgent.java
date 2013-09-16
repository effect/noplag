package org.amse.fedotov.noplag.data_storage.impl.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.data_storage.impl.DataStorage;
import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.model.IToken;
import org.amse.fedotov.noplag.model.ITokenFactory;
import org.amse.fedotov.noplag.settings.Settings;

public class TokensIOAgent {
	
	/**
	 * Extension of file with tokens. 
	 */
	private static final String TOKENS_EXTENSION = ".tok";

	public void write(IProgramId id, List<IToken> tokens) throws BadInputException {
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(getFullname(id)), Settings.getInternalCharset());
			for (IToken token : tokens) {
				writer.write(token.getId());
			}
		} catch (FileNotFoundException e) {
			BadInputException ex = new BadInputException("Can't create tokens file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't write to tokens file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					BadInputException ex = new BadInputException("Can't close writer of tokens file for program with id " + id.getValue());
					ex.initCause(e);
					throw ex;
				}
			}
		}
	}
	
	public List<IToken> read(IProgramId id, ITokenFactory tokenFactory) throws BadInputException {
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(getFullname(id)), Settings.getInternalCharset());
			List<IToken> tokens = new ArrayList<IToken>();
			int cur;
			while ((cur = reader.read()) != -1) {
				tokens.add(tokenFactory.createToken(cur));
			}
			return tokens;
		} catch (FileNotFoundException e) {
			BadInputException ex = new BadInputException("Can't find tokens file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't read from tokens file for program with id " + id.getValue());
			ex.initCause(e);
			throw ex;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					BadInputException ex = new BadInputException("Can't close reader of tokens file for program with id " + id.getValue());
					ex.initCause(e);
					throw ex;
				}
			} 
		}	
	}
	
	private String getFullname(IProgramId id) {
		return DataStorage.getStoragePath() + id.getValue() + TOKENS_EXTENSION; 
	}
	
}
