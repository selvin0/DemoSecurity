package DemoSecurityApp.Repository;
import DemoSecurityApp.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository  extends JpaRepository<Users,Long>
{
    Optional<Users> findByUsername(String name);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsernameOrEmail(String username,String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String  email);

}
