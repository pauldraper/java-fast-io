package fast.io.benchmark;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import fast.io.BufferedReaderTokenizer;
import fast.io.InputStreamTokenizer;
import fast.io.ScannerTokenizer;
import fast.io.StreamTokenizer;
import fast.io.Tokenizer;

class TokenizerFactory {
	
	private final AbstractInputStreamFactory _factory;
	
	TokenizerFactory(AbstractInputStreamFactory factory) {
		_factory = factory;
	}
	
	Collection<Tokenizer> createTokenizers() throws IOException {
		return Arrays.asList(
			new BufferedReaderTokenizer(_factory.createInputStream())
			, new InputStreamTokenizer(_factory.createInputStream())
			, new ScannerTokenizer(_factory.createInputStream())
			, new StreamTokenizer(_factory.createInputStream())
		);
	}
	
}
