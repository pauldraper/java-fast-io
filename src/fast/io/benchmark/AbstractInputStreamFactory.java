package fast.io.benchmark;

import java.io.IOException;
import java.io.InputStream;

interface AbstractInputStreamFactory {
	
	InputStream createInputStream() throws IOException;
	
}
