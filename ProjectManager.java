
/*
 * by 
 *  Dillion Norris
 */

import java.util.ArrayList;

public class ProjectManager {
    
    private static ProjectManager instance;
    private ArrayList<Project> projects = null;
    private Project currrentProject;
    private Task currentTask;
    private ToDo currentToDo;

    private ProjectManager(){
        projects = new ArrayList<Project>();
        
    };

    //gets instance of ProjectManager
    public static ProjectManager getInstance(){
        if(instance == null)
            instance = new ProjectManager();
        return instance;
    }

    //changes aspects of a project 
    // may need to edit peramiters
    public boolean changeproject(int projectNum){

    }
    //adds a new project
    // may need to edit peramiters
    public boolean addproject(String projectName){
        if(checkNames(projectName)) {
            projects.add(new Project(projectName));
            return true;
        }
        return false;
    }

    //removes a project
    // may need to edit peramiters
    public boolean removeProject(String projectName){
        for(Project specificProject : projects) {
            if(specificProject.equals(projectName)) {
                projects.remove(specificProject);
                return true;
            }
        }
        return false;
    }
    
    //edits a project
    // may need to edit peramiters
    public boolean editproject(String projectName){
        
    }
    
    public Project getCurrentProject() {
        return currrentProject;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public ToDo getCurrentToDo() {
        return currentToDo;
    }

    public ArrayList<Comment> getComments() {
        return currentTask.getComments();
    }

    private boolean checkNames(String name) {
        for(Project specificProject : projects) {
            if(specificProject.equals(name))
                return false;
        }
        return true;
    }

    public ArrayList<Project> getProjectList() {
        return projects;
    }

    public ArrayList<Task> getTaskList() {
        return currrentProject.getTasks();
    }

    public ArrayList<ToDo> getToDoList() {
        return currentTask.getToDoList();
    }
}
