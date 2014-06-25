package problem1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileLineReader {

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        Stream<String> stream = getLineStream("english.txt");
        long end = System.currentTimeMillis();

        System.out.printf("The time %d", end - start);
    }

    /**
     * @param file The name of a file to read.
     * @return A stream to read the contents of that file.  */
    public static Stream<String> getLineStream(String file){
        Stream<String> lines = null;

        try {
            lines = Files.lines(Paths.get(file));
        } catch (IOException e){
            System.err.println("Unable to open the file.");
            e.printStackTrace();
        }

        return lines;
    }

}