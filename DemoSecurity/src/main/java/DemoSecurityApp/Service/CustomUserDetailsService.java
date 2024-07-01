package DemoSecurityApp.Service;
import DemoSecurityApp.Entity.Users;
import DemoSecurityApp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UsersRepository usersRepository;
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException
    {
      Users user= usersRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
              .orElseThrow(()->new UsernameNotFoundException("user not found"));

      Set<GrantedAuthority> authorities= user.getRoles().stream()
                    .map(roles-> new SimpleGrantedAuthority(roles.getName()))
                    .collect(Collectors.toSet());

        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
