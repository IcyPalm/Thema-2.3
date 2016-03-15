package benchmark;

import java.io.File;

import huffman.Hzip;

public class Benchmark {

	public static void main(String[] args) {
		new Benchmark();
	}

	public Benchmark(){
		String path = "src/benchmark/tekst/";
        String[] files = {
        		"kortNatuurlijk.txt",
        		"langNatuurlijk.txt",
        		"kortRandom.txt",
        		"langRandom.txt",
        		"kortEnkel.txt",
        		"langEnkel.txt",
        		"kortDubbel.txt",
        		"langDubbel.txt"
        };

        for(int i = 0; i < files.length; i++) {
            String file = path + files[i];

            try {
                Hzip.compress(file);

                long beforeLength = fileSize(file);
                long afterLength = fileSize(file + ".huf");
                float compression = (float) afterLength / (float) beforeLength * 100.0f;

                System.out.println("File: " + file);
                System.out.println("Voor compressie: " + beforeLength + " bytes");
                System.out.println("Na compressie: " + afterLength + " bytes");
                System.out.println("Compressiepercentage: " + compression + "%");
                System.out.println();
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }

    private long fileSize(String filename) {
        File file = new File(filename);

        return file.length();
    }
}
