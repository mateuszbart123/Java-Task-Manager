package model;

public class Task {

    public enum PriorityLevel{
        HIGH, MEDIUM, LOW
    }

    private String taskName;
    private PriorityLevel priority;
    private String shortDesc;
    private boolean completed;

    //Constructor
    public Task(String tname,PriorityLevel priority, String sdesc, boolean complete){


        this.taskName = tname;
        this.priority = priority;
        this.shortDesc = sdesc;
        this.completed = complete;

    }



    //Setters

    public void setTaskName(String taskName){

        this.taskName = taskName;
    }
    public void setPriority(PriorityLevel priority){

        this.priority = priority;
    }
    public void setCompleted(boolean completed){

        this.completed = completed;
    }
    public void setShortDesc(String shortDesc){

        this.shortDesc = shortDesc;
    }

    //getters


    public String getTaskName(){

        return taskName;
    }

    public PriorityLevel getPriority(){

        return priority;
    }
    public boolean getCompleted(){
        return completed;
    }
    public String getShortDesc(){
        return shortDesc;
    }

}
