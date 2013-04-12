package fast.io.benchmark;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BufferedFileInputStreamFactory implements AbstractInputStreamFactory {

	private final File _file;
	
	BufferedFileInputStreamFactory(File file) {
		_file = file;
	}
	
	@Override
	public InputStream createInputStream() throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(_file));
	}

}
