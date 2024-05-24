/*package security.jdbc.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Model.User;
import security.jdbc.Repository.RowMapper.UserRowMapper;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    String GET_BY_USERNAME = "SELECT * FROM USERS WHERE user_name = ?";
    String CREATE_USER = "INSERT INTO USERS (user_name, mail, password) " +
            "VALUES (?,?,?)";
    String MODIFY_USER = "UPDATE USERS SET username=?, mail=?, password=?";
    public void createUser(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getMail());
            preparedStatement.setString(3, user.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 1) {
                throw new CreationFailedException("Error while creating user");
            }
        }catch(CreationFailedException e){
            throw new CreationFailedException("Error while creating user");
        }catch(SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
    public User getUserByUsername(String username) throws SQLException {
        return jdbcTemplate.queryForObject(GET_BY_USERNAME, new UserRowMapper(),username);
    }
}*/
