package database;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import model.Task;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class TaskDAO {

    public void saveTask(Task task) throws SQLException{

        String sql = "INSERT INTO Tasks " + "(task_name, task_desc, task_complete, task_priority)" +
                "Values (?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
           PreparedStatement statement = connection.prepareStatement(sql,
                   Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getShortDesc());
            statement.setBoolean(3, task.getCompleted());
            statement.setString(4, task.getPriority().name());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                task.setTaskId(generatedKeys.getInt(1));
            }
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
                            resultSet.getInt("task_id"),
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




    public void deleteTask(Task task) throws SQLException {
        String sql = "DELETE FROM Tasks WHERE task_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, task.getTaskId());

            statement.executeUpdate();
        }


    }

    public void updateTask(Task task) throws SQLException{
        String sql = "UPDATE Tasks SET task_name = ?, task_desc = ?, task_complete = ?" +
                ", task_priority = ? WHERE task_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getShortDesc());
            statement.setBoolean(3, task.getCompleted());
            statement.setString(4, task.getPriority().name());
            statement.setInt(5, task.getTaskId());

            statement.executeUpdate();

        }

    }

}
