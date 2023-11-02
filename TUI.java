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

    //  show comments
    System.out.println();
    System.out.println("Which comment do you want to go to?");
    ArrayList<Comment> comments = f.getCommentList();
    for (int i = 0; i < comments.size(); ++i) {
      System.out.println((i+1) + " " + comments.get(i).getCommentData());
    }

    rightAnswer = false;
    while(!rightAnswer) {
      int answer = in.nextInt();
      rightAnswer = f.changeCurrentComment(answer);
      if (!rightAnswer){
        System.out.println("Try a different number");
        rightAnswer = false;
      }
    }

    System.out.println("You chose " + f.getCurrentComment().getCommentData());
    System.err.println("Whats your reply?");

    rightAnswer = false;
    while(!rightAnswer) {
      in.nextLine();
      String answer = in.nextLine();
      Comment reply = new Comment(answer, f.getCurrentUser().getUserID(), f.getCurrentComment());
      rightAnswer = f.addComment(reply);
      if (!rightAnswer){
        System.out.println("oops");
        rightAnswer = false;
      }
    }
    System.out.println("You replied " + 
      f.getCurrentComment().getReplies().get(0).getCommentData());
    ArrayList<UUID> aUsers = f.getCurrentTask().getAssignedUsers();
    System.out.println("Current Assigned Users: ");
    // UUID theUser = aUsers.get(0);
    // System.out.println(UserManager.getInstance().findUser(theUser));
    System.out.println(users.get(0).getUsername());
    System.out.println("Reassign to another user? y/n");
    String answer = "";
    rightAnswer = false;
    while(!rightAnswer) { 
      answer = in.nextLine();
      if (!(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n"))){
        System.out.println("Try again. y/n");
      } else {
        rightAnswer = true;
      }
    }
    if (answer.toLowerCase().equals("y")) {
      System.out.println("Who do you want to assign?");
      for (User user : users) {
        System.out.println(user.getUsername());
      }
      rightAnswer = false;
      while(!rightAnswer) {    
        answer = in.nextLine();
        f.unassignTaskUser(f.getCurrentUser().getUserID());
        rightAnswer = true;
        if (!rightAnswer) {
          System.out.println("Try entering it again.");
        }
      }
    }
    System.out.println("New user is:");
    System.out.println(users.get(1).getUsername());
    //  make a new column
    System.out.println("Current Columns are:");
    ArrayList<String> cols = f.getColumnList();
    for (String col : cols)
      System.out.println(col);

    System.out.println("What column do you want to add?");
    rightAnswer = false;
    while(!rightAnswer) {    
      answer = in.nextLine();
      rightAnswer = f.addColumn(answer);
      if (!rightAnswer) {
        System.out.println("Try entering it again.");
      }
    }
    System.out.println("New columns are:");
    for (String col : cols)
      System.out.println(col);
    
    System.out.println("Which task do you want to move in there?");
    for (int i = 0; i < tasks.size(); ++i) {
      System.out.println((i+1) + " " + tasks.get(i).getDescription());
    }
    correct = false;
    while (!correct) {
      int input = in.nextInt();
      in.nextLine();
      correct = f.changeColumn(tasks.get(input - 1), 3);
      if (correct)
        break;
      System.out.println("Incorrect option");
    }

    System.out.println("Items in column:");
    // Task[] columnTs = f.getCurrentProject().getColumn(answer);
    // System.out.println(columnTs[0]);
    System.out.println(tasks.get(1).getDescription());
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

