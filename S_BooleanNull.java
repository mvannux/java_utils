public class S_BooleanNull {

  /**
   * Example how to handle Boolean null
   */
  public static void main(String[] args) {
    Boolean a = null;

    if (a == null)
      System.out.println("NULL");
    else if (a)
      System.out.println("TRUE");
    else if (!a)
      System.out.println("FALSE");
  }
}
