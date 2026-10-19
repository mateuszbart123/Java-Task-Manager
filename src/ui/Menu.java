package ui;

import model.Task;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import database.TaskDAO;
import java.sql.SQLException;

public class Menu {

    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskDAO taskDAO = new TaskDAO();



    public void mainMenu() {

       String choice[] = {"View", "Create", "Edit", "Remove", "Exit"};
       int menuOption = 0;

       // grabs all tasks from database before menu loop to save sessions
        try{
            tasks.addAll(taskDAO.getAllTasks());
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Could not load database");
            e.printStackTrace();
        }


       while (menuOption != 4) {
           menuOption = JOptionPane.showOptionDialog(
                   null,
                   "Which action do you wish to complete:",
                   "Task Manager",
                   JOptionPane.DEFAULT_OPTION,
                   JOptionPane.INFORMATION_MESSAGE,
                   null,
                   choice,
                   null
           );


           switch (menuOption) {
               case 0:
                   viewTasks();
                   break;
               case 1:
                   createTask();
                   break;
               case 2:
                   editTask();
                   break;
               case 3:
                   removeTask();
                   break;
               case 4:
                   System.exit(-1);
               default:
                   System.exit(-1);
           }
       }
   }
    public void viewTasks(){
        TextArea textArea = new TextArea(30,40);
        Font textAreaFont = new Font("monospaced",Font.PLAIN,12);
        textArea.setFont(textAreaFont);
        String data = "";
        if(tasks.isEmpty()){
            JOptionPane.showMessageDialog(null,"No tasks created.");
            return;
        }
        for(int a = 0; a < tasks.size(); a++){
            data += "Task: " + (a + 1) + "\n";
            data += "Name: " + tasks.get(a).getTaskName() + "\n";
            data += "Priority: " + tasks.get(a).getPriority() + "\n";
            data += "Short Description: " + tasks.get(a).getShortDesc() + "\n";
            data += "Completed: " + tasks.get(a).getCompleted() + "\n\n";

        }
        textArea.append(data);
        JOptionPane.showMessageDialog(null,textArea);
    }
   public void createTask() {
       String taskNames = JOptionPane.showInputDialog(null, "What is the name of your task:");
       if (taskNames == null || taskNames.isEmpty()) {
           return;
       }
       String taskDesc = JOptionPane.showInputDialog(null, "Describe the task: ");
       if (taskDesc == null || taskDesc.isEmpty()) {
           return;
       }
       int priorityIndex = JOptionPane.showOptionDialog(null,
               "Set the priority Level",
               "Priority",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.INFORMATION_MESSAGE,
               null,
               Task.PriorityLevel.values(),
               null);
       if (priorityIndex < 0) {
           return;
       }
       Task.PriorityLevel priorityValue = Task.PriorityLevel.values()[priorityIndex];
       int taskId = 0;

       Task newtask = new Task(taskId,taskNames, priorityValue, taskDesc, false);

       try {
           taskDAO.saveTask(newtask);
           tasks.add(newtask);

           JOptionPane.showMessageDialog(null,"Task created!");
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog(null,"Failed to save to database");
           e.printStackTrace();
       }


   }
   public void editTask(){
        String displayTaskNames = "";
        if(tasks.isEmpty()){
            JOptionPane.showMessageDialog(null,"No tasks created.");
            return;
        }
        for(int a = 0; a < tasks.size(); a++){
            displayTaskNames += "task " + (a + 1) + ": " + tasks.get(a).getTaskName() + "\n ";

        }

        String pickTask = JOptionPane.showInputDialog(null,"Enter task number:\n\n " + displayTaskNames);

        if(pickTask == null || pickTask.isEmpty()){
            JOptionPane.showMessageDialog(null,"Doesn't exist (enter a valid number)");
            return;
        }

        int pickTaskInt = 0;

        try {
            pickTaskInt = Integer.parseInt(pickTask);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }

        if(pickTaskInt < 1 || pickTaskInt > tasks.size()){
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }

        pickTaskInt = pickTaskInt - 1;
        String editChoice[] = {"Name","Priority","Description","Completed"};

        int chosen = JOptionPane.showOptionDialog(null,
                "What do you want to edit:",
                "Edit task",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
               editChoice,
                null);

       Task selectedTask = tasks.get(pickTaskInt);
        switch (chosen) {
            case 0:
                String newName = JOptionPane.showInputDialog(null, "New name: ");
                if (newName == null || newName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty Name error");
                    return;
                }
                tasks.get(pickTaskInt).setTaskName(newName);

                break;
            case 1:
                int priorityIndex = JOptionPane.showOptionDialog(null,
                        "Set the priority Level:",
                        "Priority",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        Task.PriorityLevel.values(),
                        null);
                if (priorityIndex < 0) {
                    return;
                }
                Task.PriorityLevel priorityValue = Task.PriorityLevel.values()[priorityIndex];
                tasks.get(pickTaskInt).setPriority(priorityValue);

                break;
            case 2:
                String newDesc = JOptionPane.showInputDialog(null, "New description: ");
                if (newDesc == null || newDesc.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty Desc error.");
                    return;
                }
                tasks.get(pickTaskInt).setShortDesc(newDesc);

                break;
            case 3:
                String completedSet[] = {"Yes", "No"};
                boolean completeTrue = false;
                int completeIndex = JOptionPane.showOptionDialog(null,
                        "Task Completed:",
                        "Status",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        completedSet,
                        null);
                if (completeIndex < 0) {
                    return;
                } else if (completeIndex == 0) {
                    completeTrue = true;
                } else {
                    completeTrue = false;
                }
                tasks.get(pickTaskInt).setCompleted(completeTrue);
                break;
            default:
                return;

        }
       try {
           taskDAO.updateTask(selectedTask);
       }
       catch (SQLException e){
           JOptionPane.showMessageDialog(null,"not updated.");
           e.printStackTrace();
       }
    }
    public void removeTask(){

        String displayTaskNames = "";
        if(tasks.isEmpty()){
            JOptionPane.showMessageDialog(null,"No tasks created.");
            return;
        }
        for(int a = 0; a < tasks.size(); a++){
            displayTaskNames += "task " + (a + 1) + ": " + tasks.get(a).getTaskName() + "\n ";

        }

        String pickTask = JOptionPane.showInputDialog(null,"Enter task number:\n\n " + displayTaskNames);

        if(pickTask == null || pickTask.isEmpty()){
            JOptionPane.showMessageDialog(null,"Doesn't exist (enter a valid number)");
            return;
        }

        int pickTaskInt = 0;

        try {
            pickTaskInt = Integer.parseInt(pickTask);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }

        if(pickTaskInt < 1 || pickTaskInt > tasks.size()){
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }

        pickTaskInt = pickTaskInt - 1;

        boolean confirmTrue = false;
        String confirmSet[] = {"Yes", "No"};
        int confirmIndex = JOptionPane.showOptionDialog(null,
                "Are you sure you want to remove the task:",
                "Confirmation",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                confirmSet,
                null);

        if(confirmIndex < 0 || confirmIndex == 1){
            return;
        }
        try {
            Task selectedTask = tasks.get(pickTaskInt);
            taskDAO.deleteTask(selectedTask);
            tasks.remove(pickTaskInt);

            JOptionPane.showMessageDialog(null,"Tasks deleted");

        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"Task deleted error");
           e.printStackTrace();
        }
    }
}
