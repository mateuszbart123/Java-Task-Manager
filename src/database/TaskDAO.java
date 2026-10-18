package database;
import model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class TaskDAO {

    public void saveTask(Task task) throws SQLException{

        String sql = "INSERT INTO Tasks " + "(task_name, task_desc, task_complete, task_priority)" +
                "Values (?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getShortDesc());
            statement.setBoolean(3, task.getCompleted());
            statement.setString(4, task.getPriority().name());


            statement.executeUpdate();
        }
    }

    public ArrayList<Task> getAllTasks() throws SQLException{
        String sql = "SELECT * FROM Tasks";
        ArrayList<Task> tasks = new ArrayList<>();
        try
            (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){


                while(resultSet.next()){
                    Task task = new Task(
                        resultSet.getString("task_name"),
                        Task.PriorityLevel.valueOf(resultSet.getString("task_priority")),
                        resultSet.getString("task_desc"),
                        resultSet.getBoolean("task_complete")
                        );

                    tasks.add(task);

            }
        }
        return tasks;


    }
}
