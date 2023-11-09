
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
  static String replyData = "Reply Data";
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
    boolean result = comment.editComment("Edited Comment");
    assertTrue(result);
  }

  @Test
  void testNullEditComment() {
    boolean result = comment.editComment(null);
    assertFalse(result);
  }

  @Test
  void testAddReply() {
    boolean result = comment.addReply(replyData, author.getUserID());
    assertTrue(result);
  }

  @Test
  void testAddNullReply() {
    boolean result = comment.addReply(null, null);
    assertFalse(result);
  }

  @Test
  void testEquals() {
    Comment temp = comment;
    boolean result = comment.equals(temp);
    assertTrue(result);
  }

  @Test
  void testNotEquals() {
    Comment temp = new Comment("Fake", author.getUserID(), null);
    boolean result = comment.equals(temp);
    assertFalse(result);
  }

  @Test
  void testRemoveReply() {
    comment.addReply(replyData, author.getUserID());
    boolean result = comment.removeReply(0, author.getUserID());
    assertTrue(result);
  }

  @Test
  void testRemoveNullReply() {
    boolean result = comment.removeReply(10, null);
    assertFalse(result);
  }
}
