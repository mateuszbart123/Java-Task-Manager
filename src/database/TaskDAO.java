package database;
import model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
}
