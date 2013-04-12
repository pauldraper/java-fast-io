package fast.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ScannerTokenizer implements Tokenizer {

	private final Scanner _scanner;
	
	public ScannerTokenizer(InputStream inputStream) {
		_scanner = new Scanner(inputStream);
	}
	
	@Override
	public String getDescription() {
		return "based on java.util.Scanner";
	}
	
	@Override
	public int readUnsignedInt() {
		return _scanner.nextInt();
	}

	@Override
	public long readUnsignedLong() {
		return _scanner.nextLong();
	}

	@Override
	public String readString() {
		return _scanner.next();
	}

	@Override
	public String readLine() throws IOException {
		return _scanner.nextLine();
	}
	
}
