package DemoSecurityApp.Repository;

import DemoSecurityApp.Entity.Roles;
import java.util.Optional;

public interface RolesRepository
{
    Optional<Roles> findByName();
}
