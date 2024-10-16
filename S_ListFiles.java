import java.io.File;
import java.util.stream.Stream;

/**
 * Print directory files
 */
public interface S_ListFiles {

  static void main(String[] args) {
    File f = new File(".");
    Stream.of(f.list()).forEach(System.out::println);
  }
}