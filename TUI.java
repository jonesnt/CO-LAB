import java.util.UUID;

public class TUI {
  public static void main(String[] args) {
    Facade f = Facade.getInstance();
    boolean yes = f.logInUser("bJohn", "1234");
    System.out.println(yes);
  }
}
