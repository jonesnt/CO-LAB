import java.util.UUID;

public class TUI {
  public static void main(String[] args) {
    Facade f = Facade.getInstance();
    f.logInUser("j", "l");
  }
}
