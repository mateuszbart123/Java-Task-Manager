import model.Task;
public class Main {
    public static void main(String[] args) {

        Task mytask = new Task(Task.PriorityLevel.HIGH,"Test","testing to see if it works",false);

        mytask.setPriority(Task.PriorityLevel.MEDIUM);

        System.out.println(mytask.getPriority());
        System.out.println(mytask.getShortDesc());
        mytask.setShortDesc("New desc shouldnt print since its after");
        System.out.println(mytask.getShortDesc());
    }
}