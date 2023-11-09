import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    // static User user = null;
    // static Task task = null;
    User user = new User("username", "FirstName", "LastName", "password");
    Task task = new Task("name", "description","column", user);
    @BeforeClass
    public void oneTimeSetup() {
        
    }
    
    @AfterClass
    public void oneTimeTearDown() {
        // NOTHING
    }
    
    @BeforeEach
    public void setup() {
        task.editTask("name", "description", user);
    }
    
    @AfterEach
    public void tearDown() {
        task = new Task("name", "description", "column", user);
    }

    @Test
    public void testConstructorName() {
        assertEquals("name", task.getName());

    }

    @Test
    public void testConstructorDescription() {
        assertEquals("description", task.getDescription());
    }

    @Test
    public void testConstructorColumn() {
        assertEquals("column", task.getColumnTag());
    }
    
    @Test
    public void testChangeName() {
        task.editTask("newName", null, user);
        assertEquals("newName", task.getName());
        assertEquals("description", task.getDescription());
    }

    @Test
    public void testChangeDescription() {
        task.editTask(null, "newDescription", user);
        assertEquals("name", task.getName());
        assertEquals("newDescription", task.getDescription());
    }

    @Test
    public void testAddComment() {
        Comment newComment = new Comment("test", user.getUserID(), null);
        task.addComment(newComment);
        assertTrue(task.getComments().contains(newComment));
    }

    @Test
    public void testAddReply() {
        Comment newComment = new Comment("test", user.getUserID(), null);
        task.addComment(newComment);
        assertTrue(task.getComments().contains(newComment));
        task.getComments().get(0).addReply("reply", user.getUserID());
        assertEquals(task.getComments().get(0).getReplies().get(0).getCommentData(), "reply");
    }

    @Test
    public void testAddToDo() {
        ToDo todo = new ToDo("toDo");
        task.addToDo(todo, user);
        assertEquals(task.getTodo().get(0), todo);
    }

    @Test
    public void testIterateNull() {
        task.iterate(null);
    }

    @Test
    public void testEqualsNull() {
        task.equals(null);
    }

    @Test
    public void testRemoveTopLevelCommentBound() {
        task.addComment(new Comment("asdf", user.getUserID(), null));
        assertFalse(task.removeTopLevelComment(task.getComments().size() + 1, user.getUserID()));
        assertFalse(task.removeTopLevelComment(-1, user.getUserID()));
    }

    @Test
    public void testUnassignUser() {
        task.assignUser(user.getUserID(), user);
        assertTrue(task.unassignUser(user.getUserID(), user));
        assertTrue(task.getAssignedUsers().isEmpty());
        task.assignUser(user.getUserID(), user);
        User newUser = new User("user2", "user", "2", "lsdfjlsdfk");
        task.assignUser(newUser.getUserID(), user);
        assertTrue(task.unassignUser(user.getUserID(), user));
        assertEquals(task.getAssignedUsers().get(0), newUser.getUserID());
    }

    @Test
    public void testAddEvent() {
        task.changeCompletionStatus(user);
        assertTrue(task.getHistory().size() > 0);
    }


}
