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
    // System.out.println("Username:");//aMadden
    // String username = in.nextLine();
    // System.out.println("Password:");//1234
    // String pass= in.nextLine();
    
    // login = f.logInUser(username, pass);
    login = f.logInUser("aMadden", "1234");
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
  //  make arraylist of all users:
  ArrayList<User> users = f.getuserList();

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

    //  add new task
    Task newTask = new Task("Avoid Civilians Task", "Avoid civilians Jeff!", "ToDo", f.getCurrentUser());
    f.addTask(newTask);
    //  assign user
    f.changeCurrentTask(f.getTaskList().size());
    f.assignTaskUser(f.findUser("jBlum").getUserID());
    //  add new comment
    Comment newComment = new Comment("Avoid civilians Jeff!", f.getCurrentUser().getUserID(), null);
    f.addComment(newComment);
    System.out.println(f.getCommentList().get(f.getCommentList().size() - 1).getCommentData());
    //  move curve the metal to make a cylindrical shape
    f.changeCurrentTask(0);
    System.out.println("Choose the task you want to do:\n");
    for (int i = 0; i < f.getTaskList().size(); ++i) {
      System.err.println((i+1) + " " + f.getTaskList().get(i).getName());
    }
    boolean rightAnswer = false;
    while(!rightAnswer) {
      int answer = in.nextInt();
      rightAnswer = f.changeCurrentTask(answer);
      if (!rightAnswer || answer == 0){
        System.out.println("Try a different number");
        rightAnswer = false;
      }
    }
    System.out.println("You chose " + f.getCurrentTask().getName());
    System.out.println("Change to which column?");
    ArrayList<String> columns = f.getColumnList();
    for (int i = 0; i < columns.size(); ++i) {
      System.out.println((i+1) + " " + columns.get(i));
    }
    rightAnswer = false;
    while(!rightAnswer) {
      int answer = in.nextInt();
      rightAnswer = f.changeColumn(f.getCurrentTask(), answer - 1);
      if (!rightAnswer){
        System.out.println("Try a different number");
        rightAnswer = false;
      }
    }
    System.out.println("Column \"Doing\" contains:");
    ArrayList<Task> tasks = f.getTaskList();
    for (Task task : tasks) {
      if (task.getColumnTag().equals("Doing"))
        System.out.println(task.getName());
    }

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

