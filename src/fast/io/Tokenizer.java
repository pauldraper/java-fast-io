package fast.io;

import java.io.IOException;

public interface Tokenizer {

	String getDescription();
	
	int readUnsignedInt() throws IOException;
	
	long readUnsignedLong() throws IOException ;
	
	String readString() throws IOException;
	
	String readLine() throws IOException;
	
}
