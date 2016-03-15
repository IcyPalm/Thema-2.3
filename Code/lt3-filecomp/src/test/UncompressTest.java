package test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import huffman.Hzip;

public class UncompressTest {

	private String filename = "TestA";
	private InputStream inputOF;
	private InputStream inputDF;
	
	@Test
	public void testUncompress() throws Exception {
		String compressedFile =  filename + ".dat.huf";
		Hzip.uncompress(compressedFile);
		
		
		String originalFile = filename + ".dat";
		String decompressedFile =  filename + ".dat.uc";
		
		inputOF = new BufferedInputStream(
				new FileInputStream( originalFile ) );
		inputDF = new BufferedInputStream(
				new FileInputStream( decompressedFile ) );
		
		boolean same=false;
		int original;
		int decompressed;
		while(true) {
			original = inputOF.read();
			decompressed = inputDF.read();
			
			if (original == -1) {
				same = true;
				break;
			}
			
			if (original != decompressed) {
				same = false;
				break;
			}
		}
		
		Assert.assertTrue(same);
	}

}
