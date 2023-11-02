import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TUI {
  public static void main(String[] args) {
    Facade f = Facade.getInstance();
    Scanner in = new Scanner(System.in);
    Boolean login =false;
    while (!login){
    System.out.println("-----------------------------------------------------------------");
    System.out.println(" ");
    System.out.println("Username:");//aMadden
    String username = in.nextLine();
    System.out.println("Password:");//1234
    String pass= in.nextLine();

    login = f.logInUser(username, pass);
    if(!login){
      System.out.println(" ");
      System.out.println("login Unsuccessful");
    System.out.println(" ");
    System.out.println("----------------------------------------------------------------");
    
    }
     if (login){
      System.out.println(" ");
      System.out.println("login Successfull");
      System.out.println(" ");
    System.out.println("----------------------------------------------------------------");
     }

  }
 

    ArrayList<Project> projs = f.getProjectList();
    System.out.println("choose project:");
    for (int i = 1; i < projs.size() + 1; ++i) {
      System.out.println(i + ": " + projs.get(i - 1).getName());
    }
    boolean correct = false;
    while (!correct) {
      int input = in.nextInt();
      correct = f.changeCurrentProject(input);
      if (correct)
        break;
      System.out.println("Incorrect option");
    }
    System.out.println("You chose " + f.getCurrentProject().getName() );

    /* 
    Facade f = Facade.getInstance();
    boolean yes = f.logInUser("sMcNug", "password");
    System.out.println(yes);
    System.out.println(f.getCurrentUser().getFirstName());
    ArrayList<Project> projs = f.getProjectList();
    f.changeCurrentProject(1);
    System.out.println(f.getCurrentProject().getName());

    Project project = new Project(null, "project","hell", null, null, null, null);
    projs.add(project);
    
  
    f.addProject(project);

   
    
    System.out.println(f.getProjectList().get(1).getName());
*/
  }
}

