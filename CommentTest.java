
/**
 * @author hadleya
 * Designed to test the Comments class
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentTest {
  private ArrayList<User> users;
  Comment comment;
  static String commentData = "Comment Data";
  User author;

  @BeforeEach
  public void setup() {
    users = DataReader.getUsers();
    author = users.get(0);
    comment = new Comment(commentData, author.getUserID(), null);
  }

  @AfterEach
  public void tearDown() {

  } 


  @Test
  void testEditComment() {
    comment.editComment("Edited Comment");
    assertEquals("Edited Comment", comment.getCommentData());
  }

  @Test
  void testNullEditComment() {
    boolean result = comment.editComment(null);
    assertFalse(result);
  }
}
