import java.io.File;
import java.util.stream.Stream;

/**
 * S_ListFiles
 */
public interface S_ListFiles {

  static void main(String[] args) {
    File f = new File(".");
    Stream.of(f.list()).forEach(System.out::println);
  }
}