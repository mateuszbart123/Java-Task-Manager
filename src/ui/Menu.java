package ui;

import javax.swing.*;

public class Menu {

   public void mainMenu(){
       String choice[] = {"View","Create","Edit","Remove","Exit"};

       int menuOption = JOptionPane.showOptionDialog(
               null,
               "Which action do you wish to complete",
               "Task Manager",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.INFORMATION_MESSAGE,
               null,
               choice,
               null
               );


       switch (menuOption){
           case 0:
               System.out.println("viewing");
               break;
           case 1:
               System.out.println("creating");
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
