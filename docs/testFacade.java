package model;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class facadeTest{

    Manager f =  Manager.getInstance();
    ArrayList<Project> projectList = f.getProjectList();

    @BeforeEach
    public void  setup(){

    }


    @AfterEach
    public void tearDown(){

    }

    @Test
    void changeProject(){
        Boolean test = f.changeCurrentProject(0);
        //if(f.getCurrentProject().equals() )
        assertTrue(test);

    }


    @Test
    void testAddProject(){
        Project p = new Project("test", "test");
        boolean projectAdded = f.addProject(p);
        assertTrue(projectAdded);
    }

    @Test
    public void testEditProject(){
        Project p = new Project("test", "test");
        f.addProject(p);
      //  for(int i = 0; i<projectList.size();i++){
        //    if(projectList..equals(p));
        //}
        f.changeCurrentProject(0);
        Boolean projectEdited = f.editProject("worked", "worked");
        assertTrue(projectEdited);
    }

    @Test
    void testRemoveProject(){
        Project p = new Project("test", "test");
        f.addProject(p);
        Boolean projectRemoved = f.removeProject(0);
        assertTrue(projectRemoved);

    }



}
