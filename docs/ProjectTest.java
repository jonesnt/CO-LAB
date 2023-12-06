package model;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.util.HashMap;




public class ProjectTest {

    private Project p = new Project("test", "test");
    private Task t = new Task("task", "taskDes", null, null);

   @Before
   public void setup(){


   }

   @Test
   void testConstructer(){
    Project p = new Project("test", "test");
    if (p.getName() != null && p.getDescription() != null){
        assertTrue(true);
    }else assertTrue(false);
   }

@Test
void testAddColumn(){
   Boolean test =  p.addColumn("column");
   assertTrue(test);
}

@Test
void testAddTask(){
    Task t = new Task("task", "taskDes", null, null);
    boolean testAdd = p.addTask(t, null);
    assertTrue(testAdd);
}


@Test
void testAssignUser(){
    Boolean testAssign = p.assignUser(null);
    assertTrue(testAssign);
}

@Test
void testUnassignUser(){
    Boolean test = p.unassignUser(null);

    assertTrue(test);
}

@Test
void testChangeColumn(){
    Boolean test = p.changeColumn(null, 0);
    assertTrue(test);
}

@Test
void testRemoveTask(){
 Boolean test = p.removeTask(t);
 assertTrue(test);

}
@Test
void removeColumn(){
    Boolean test = p.removeColumn(0);
    assertTrue(test);

}


}
