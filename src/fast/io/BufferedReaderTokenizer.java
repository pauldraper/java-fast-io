package fast.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BufferedReaderTokenizer implements Tokenizer {

	private final BufferedReader _bufferedReader;
	private StringTokenizer _stringTokenizer;
	
	public BufferedReaderTokenizer(InputStream inputStream) {
		_bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		_stringTokenizer = new StringTokenizer("");
	}

	@Override
	public String getDescription() {
		return "based on java.io.BufferedReader (line-by-line)";
	}

	private String getNextToken() throws IOException {
		while (!_stringTokenizer.hasMoreElements()) {
			_stringTokenizer = new StringTokenizer(_bufferedReader.readLine());
		}
		return _stringTokenizer.nextToken();
	}
	
	@Override
	public int readUnsignedInt() throws IOException {
		return Integer.parseInt(getNextToken());
	}

	@Override
	public long readUnsignedLong() throws IOException {
		return Long.parseLong(getNextToken());
	}

	@Override
	public String readString() throws IOException {
		return getNextToken();
	}

	@Override
	public String readLine() throws IOException {
		return _bufferedReader.readLine(); //TODO: misses rest of current line
	}

	@Override
	public void close() throws IOException {
		_bufferedReader.close();
	}
	
}
