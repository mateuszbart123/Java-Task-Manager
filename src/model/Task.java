package model;

public class Task {

    public enum PriorityLevel{
        HIGH, MEDIUM, LOW
    }

    private int taskId;
    private String taskName;
    private PriorityLevel priority;
    private String shortDesc;
    private boolean completed;

    //Constructor
    public Task(Integer id,String tname,PriorityLevel priority, String sdesc, boolean complete){

        this.taskId = id;
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
    public void setTaskId(int taskId){
        this.taskId = taskId;
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
    public int getTaskId(){
        return taskId;
    }

}
