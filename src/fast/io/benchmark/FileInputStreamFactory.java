package fast.io.benchmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class FileInputStreamFactory implements AbstractInputStreamFactory {
	
	private final File _file;
	
	FileInputStreamFactory(File file) {
		_file = file;
	}
	
	@Override
	public FileInputStream createInputStream() throws FileNotFoundException {
		return new FileInputStream(_file);
	}

}
