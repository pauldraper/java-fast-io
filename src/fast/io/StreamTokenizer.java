package fast.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StreamTokenizer implements Tokenizer {

	private final Closeable _closeable;
	private final java.io.StreamTokenizer _tokenizer;
	
	public StreamTokenizer(InputStream input) {
		Reader reader = new InputStreamReader(input);
		_closeable = reader;
		_tokenizer = new java.io.StreamTokenizer(reader);
	}
	
	@Override
	public String getDescription() {
		return "based on java.io.StreamTokenizer";
	}

	@Override
	public int readUnsignedInt() throws IOException {
		while (_tokenizer.nextToken() != java.io.StreamTokenizer.TT_NUMBER) {	
		}
		return (int)_tokenizer.nval;
	}

	@Override
	public long readUnsignedLong() throws IOException {
		while (_tokenizer.nextToken() != java.io.StreamTokenizer.TT_NUMBER) {	
		}
		return (long)_tokenizer.nval;
	}

	@Override
	public String readString() throws IOException {
		while (_tokenizer.nextToken() != java.io.StreamTokenizer.TT_WORD) {	
		}
		return _tokenizer.sval;
	}

	@Override
	public String readLine() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		lineLoop:
		while (true) {
			switch (_tokenizer.nextToken()) {
			case java.io.StreamTokenizer.TT_NUMBER:
				stringBuilder.append(_tokenizer.nval);
				break;
			case java.io.StreamTokenizer.TT_WORD:
				stringBuilder.append(_tokenizer.sval);
				break;
			case java.io.StreamTokenizer.TT_EOL:
			case java.io.StreamTokenizer.TT_EOF:
				break lineLoop;
			default:
				assert false;
			}
		}
		return stringBuilder.toString();
	}

	@Override
	public void close() throws IOException {
		_closeable.close();
	}

}
