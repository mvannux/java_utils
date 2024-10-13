import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Modify readme.md with java file comment
 */
public class S_Readme {

  /**
   * Modify readme.md with java file comment
   * 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws Exception {
    readme = Files.readString(Paths.get("readme.md"));
    var file = new File(".");
    Stream.of(file.list())
        .filter(x -> x.endsWith(".java"))
        .forEach(f -> modify(f));

    System.out.println(readme);

    Files.write(Paths.get("readme.md"), readme.getBytes());
    System.out.println("File aggiornato con successo.");

  }

  static String readme;

  static void modify(String f) {
    try (Scanner scanner = new Scanner(new File(f))) {
      var comment = new StringBuilder();
      var read = false;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.contains("*/"))
          break;
        if (read)
          comment.append(line.replaceAll("^\\s*\\*\\s*", "").trim());
        if (line.contains("/**")) {
          read = true;
        }
      }
      // System.out.println(comment);

      // modify readme
      String patternString = "\\| " + Pattern.quote(f) + "\\s*\\|\\s*(.*?)\\s*\\|";
      Pattern pattern = Pattern.compile(patternString);
      Matcher matcher = pattern.matcher(readme);

      if (matcher.find()) {
        readme = matcher.replaceFirst("| " + f + " | " + comment + " |");
      } else {
        // Se non trova una corrispondenza, aggiungi una nuova riga alla tabella
        // Questo pattern trova l'inizio della tabella, la riga di separazione e tutte
        // le righe di dati
        Pattern tablePattern = Pattern.compile("(\\|[^\\n]*\\|\\s*\\n\\|[-:\\s|]*\\|\\s*\\n)((?:\\|.*\\|\\s*\\n)*)");
        Matcher tableMatcher = tablePattern.matcher(readme);

        if (tableMatcher.find()) {
          String tableStart = tableMatcher.group(1); // Intestazione e riga di separazione
          String tableBody = tableMatcher.group(2); // Righe di dati esistenti
          String newRow = "| " + f + " | " + comment + " |\n";

          // Ricostruisci la tabella con la nuova riga
          String newTable = tableStart + tableBody + newRow;
          readme = readme.replace(tableMatcher.group(), newTable);

          System.out.println("Nuova riga aggiunta per " + f);
        } else {
          System.out.println("Non è stato possibile trovare la fine della tabella.");
          return;
        }
      }

    } catch (

    Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
