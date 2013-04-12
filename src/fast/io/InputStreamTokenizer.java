package fast.io;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamTokenizer implements Tokenizer {

	private final InputStream _inputStream;
	
	public InputStreamTokenizer(InputStream inputStream) {
		_inputStream = inputStream;
	}
	
	@Override
	public String getDescription() {
		return "based on java.io.InputStream";
	}

	@Override
	public int readUnsignedInt() throws IOException {
		int c;
		while ((c = _inputStream.read()) <= ' ') {
		}
		int n;
		for (n = c - '0'; (c = _inputStream.read() - '0') >= 0; n = 10 * n + c) {
		}
		return n;
	}

	@Override
	public long readUnsignedLong() throws IOException {
		int c;
		while ((c = _inputStream.read()) <= ' ') {
		}
		long n;
		for (n = c - '0'; (c = _inputStream.read() - '0') >= 0; n = 10 * n + c) {
		}
		return n;
	}

	@Override
	public String readString() throws IOException {
		int c;
		while ((c = _inputStream.read()) <= ' ') {
		}
		StringBuilder sb = new StringBuilder();
		for (sb.append((char)c); (c = _inputStream.read()) > ' '; sb.append((char)c)) {
		}
		return sb.toString();
	}

	@Override
	public String readLine() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		_inputStream.close();
	}

}
