package fast.io;

import java.io.Closeable;
import java.io.IOException;

public interface Tokenizer extends Closeable {

	String getDescription();
	
	int readUnsignedInt() throws IOException;
	
	long readUnsignedLong() throws IOException ;
	
	String readString() throws IOException;
	
	String readLine() throws IOException;
	
}
