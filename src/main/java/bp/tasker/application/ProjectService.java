/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bp.tasker.application;

import bp.tasker.domain.project.Project;
import bp.tasker.domain.project.ProjectRepository;
import bp.tasker.domain.project.Task;
import bp.tasker.domain.project.TaskRepository;
import bp.tasker.domain.user.User;
import java.util.List;

/**
 *
 * @author Ильдар
 */
public class ProjectService {
    
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    
    public Project createProject(String name, String description, User owner) {
        return null;
    }
    
    public void addTask(String description, Project project, User owner) {
        
    }
    
    public void markAsFulfilld(Task task) {
        
    }
    
    public void delegateToAnother(Task task, User newOwner) {
        
    }
    
    public List<Task> getUserUnfulfilledTasks(Project project, User owner) {
        return null;
    }
    
    public List<Task> getUnfulfilledTasks(Project project) {
        return null;
    }
    
    public List<Task> getAllTasks(Project project) {
        return null;
    }
    
}
