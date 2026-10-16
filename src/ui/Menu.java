package ui;

import model.Task;

import javax.swing.*;
import java.util.ArrayList;

public class Menu {

    ArrayList<Object> tasks = new ArrayList<>();

    public void mainMenu() {

       String choice[] = {"View", "Create", "Edit", "Remove", "Exit"};
       int menuOption = 0;

       while (menuOption != 4) {
           menuOption = JOptionPane.showOptionDialog(
                   null,
                   "Which action do you wish to complete",
                   "Task Manager",
                   JOptionPane.DEFAULT_OPTION,
                   JOptionPane.INFORMATION_MESSAGE,
                   null,
                   choice,
                   null
           );


           switch (menuOption) {
               case 0:
                   System.out.println("viewing");
                   break;
               case 1:
                   createTask();
                   break;
               case 2:
                   System.out.println("editing");
                   break;
               case 3:
                   System.out.println("removing");
                   break;
               case 4:
                   System.exit(-1);
               default:
                   System.exit(-1);
           }
       }
   }

   public void createTask(){
       String taskNames = JOptionPane.showInputDialog(null,"What is the name of your task:");
       if(taskNames == null || taskNames.isEmpty()){
           return;
       }
       String taskDesc = JOptionPane.showInputDialog(null,"Describe the task: ");
       if(taskDesc == null || taskDesc.isEmpty()){
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
       if(priorityIndex < 0) {
           return;
       }
       Task.PriorityLevel priorityValue = Task.PriorityLevel.values()[priorityIndex];

       Task newtask = new Task(taskNames,priorityValue,taskDesc,false);
       tasks.add(newtask);
       System.out.println(tasks.size());
   }

}
