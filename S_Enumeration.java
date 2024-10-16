import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * How to create a comma separated list of values of Enum
 */
public class S_Enumeration {

  public static void main(String[] args) {
    System.out.println(
        "" + Stream.of(TransizioneStatoEnum.values())
            .map(x -> "\"" + x.getValue() + "\"")
            .collect(Collectors.joining(",")));
  }

  public enum TransizioneStatoEnum {
    Immessa("immessa"),
    InLavorazione("inlavorazione"),
    Gestita("gestita"),
    Conclusa("conclusa"),
    Archiviata("archiviata");

    private String stato;

    private TransizioneStatoEnum(String stato) {
      this.stato = stato;
    }

    public String getValue() {
      return stato;
    }

    public static TransizioneStatoEnum fromString(String text, TransizioneStatoEnum def) {
      for (TransizioneStatoEnum b : TransizioneStatoEnum.values()) {
        if (b.stato.equalsIgnoreCase(text)) {
          return b;
        }
      }
      return def;
    }

  }
}
