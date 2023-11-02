import java.util.ArrayList;
import java.util.UUID;

public class TUI {
  public static void main(String[] args) {
    Facade f = Facade.getInstance();
    boolean yes = f.logInUser("sMcNug", "password");
    System.out.println(yes);
    System.out.println(f.getCurrentUser().getFirstName());
    ArrayList<Project> projs = f.getProjectList();
    f.changeCurrentProject(1);
    System.out.println(f.getCurrentProject().getName());
    Task beep = new Task("ouch", "wowzers", f.getCurrentProject().getColumnList().get(1), f.getCurrentUser());
    yes = f.addTask(beep);
    System.out.println(yes);
    ArrayList<Task> tasks = f.getTaskList();
    for (Task task : tasks) {
      System.out.println(task.getName());
    }
    f.changeCurrentTask(1);
    Comment comment = new Comment("BLOWS UP", f.getCurrentUser().getUserID(), null);
    yes = f.addComment(comment);
    ArrayList<Comment> comments = f.getCommentList();
    for (Comment comment1 : comments) {
      System.out.println(comment1.getCommentData());
    }
    yes = f.changeCurrentComment(2);
    System.out.println(yes);
    System.out.println(f.getCurrentComment().getCommentData());
    Comment epic = new Comment("epic", f.getCurrentUser().getUserID(), f.getCurrentComment());
    yes = f.addComment(epic);
    System.out.println(yes);
    yes = f.changeCurrentComment(1);
    System.out.println(yes);
    System.out.println(f.getCurrentComment().getCommentData());
    yes = f.changeCurrentComment(0);
    System.out.println(yes);
    System.out.println(f.getCurrentComment().getCommentData());
    f.logOutUser();
  }
}
