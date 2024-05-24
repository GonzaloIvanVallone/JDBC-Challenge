/*package security.jdbc.Repository.RowMapper;

import org.springframework.jdbc.core.RowMapper;
import security.jdbc.Model.Doctor;
import security.jdbc.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong("user_id"));
        user.setUsername(resultSet.getString("user_name"));
        user.setMail(resultSet.getString("mail"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}*/
