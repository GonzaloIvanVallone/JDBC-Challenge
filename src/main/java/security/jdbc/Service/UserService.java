package security.jdbc.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.LoginFailedException;
import security.jdbc.Model.User;
import security.jdbc.Repository.UserRepository;
import java.sql.SQLException;

@Service
public class UserService {
    @Autowired
    JwtService jwtService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    public void register(User user){
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.createUser(user);
        }catch(SQLException e){
            throw new CreationFailedException("Database error while creating user", e);
        }catch(Exception e){
            throw new CreationFailedException("Unexpected error while creating user", e);
        }
    }
    public String login(User user) throws SQLException {
        try {
            User userinfo = userRepository.getUserByUsername(user.getUsername());
            if (userinfo == null) {
                throw new LoginFailedException("User not found");
            }
            if (!bCryptPasswordEncoder.matches(user.getPassword(), userinfo.getPassword())) {
                throw new LoginFailedException("Incorrect password");
            }
            return jwtService.generateToken(user);
        }catch(SQLException e){
            throw new SQLException("Database error while trying to login", e);
        }catch(Exception e){
            throw new RuntimeException("Unexpected error while trying to login", e);
        }
    }
}