package fast.io.benchmark;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import fast.io.BufferedReaderTokenizer;
import fast.io.InputStreamTokenizer;
import fast.io.ScannerTokenizer;
import fast.io.StreamTokenizer;
import fast.io.Tokenizer;

public class Benchmark {

	private static final String TEST_FILE_PATH = "test.txt";
	private static final long RANDOM_SEED = 42L;
	
	private static String randString(char low, char high, int length, Random random) {
		char[] chars = new char[length];
		for (int i = 0; i < length; i++) {
			chars[i] = (char)(random.nextInt(high - low) + low);
		}
		return new String(chars);
	}
	
	public static void createTest(OutputStream outputStream) {
		PrintStream printStream = new PrintStream(outputStream);
		Random random = new Random(RANDOM_SEED);
		
		// TODO checksum: int totalInt = 0;
		printStream.println(10000);
		for (int i = 0; i < 10000; i++) {
			printStream.print(random.nextInt(Integer.MAX_VALUE));
			if (random.nextBoolean()) {
				printStream.println();
			} else {
				printStream.print(" ");
			}
		}
		
		printStream.println(10000);
		for (int i = 0; i < 10000; i++) {
			printStream.print(max(0, abs(random.nextLong())));
			if (random.nextBoolean()) {
				printStream.println();
			} else {
				printStream.print(" ");
			}
		}
		
		printStream.println(10000);
		for (int i = 0; i < 10000; i++) {
			printStream.print(randString((char)33, (char)120, random.nextInt(50)+1, random));
			if (random.nextBoolean()) {
				printStream.println();
			} else {
				printStream.println();
			}
		}
	}
	
	private static long benchmark(Tokenizer tokenizer) throws IOException {
		long start = System.currentTimeMillis();
		for (int n = tokenizer.readUnsignedInt(); n > 0; n--) {
			tokenizer.readUnsignedInt();
		}
		for (int n = tokenizer.readUnsignedInt(); n > 0; n--) {
			tokenizer.readUnsignedLong();
		}
		for (int n = tokenizer.readUnsignedInt(); n > 0; n--) {
			tokenizer.readString();
		}
		return System.currentTimeMillis() - start;
	}
	
	private static void printBenchmarks(PrintStream out, AbstractInputStreamFactory factory)
			throws IOException {
		for (Tokenizer tokenizer : new TokenizerFactory(factory).createTokenizers()) {
			try {
				out.printf("%s: %dms%n", tokenizer.getClass(), benchmark(tokenizer));
			} catch (IOException e) {
				out.printf("%s: failed%n", tokenizer.getClass());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		File testFile = new File(TEST_FILE_PATH);
		createTest(new FileOutputStream(testFile));
		
		// TODO: It seems that first tests are always ~20ms slow...
		System.out.println("UNBUFFERED");
		printBenchmarks(System.out, new FileInputStreamFactory(testFile));
		System.out.println();
		
		System.out.println("BUFFERED");
		printBenchmarks(System.out, new BufferedFileInputStreamFactory(testFile));
		System.out.println();
		
		testFile.delete();
	}
	
}
