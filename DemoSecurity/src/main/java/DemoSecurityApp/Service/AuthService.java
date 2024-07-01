package DemoSecurityApp.Service;
import DemoSecurityApp.Dto.LoginDto;
import DemoSecurityApp.Dto.RegisterDto;
import DemoSecurityApp.Entity.Roles;
import DemoSecurityApp.Entity.Users;
import DemoSecurityApp.Repository.RolesRepository;
import DemoSecurityApp.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService
{
        private AuthenticationManager authenticationManager;
        private UsersRepository usersRepository;
        private RolesRepository rolesRepository;
        private PasswordEncoder passwordEncoder;

        @Autowired
    public AuthService(AuthenticationManager authenticationManager, UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder)
    {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  String login(LoginDto loginDto)
        {
            Authentication authentication=
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "login SuccessFull";
        }

        public String register(RegisterDto registerDto)
        {
            if(usersRepository.existsByUsername(registerDto.getUserName()))
            {
                throw new UsernameNotFoundException("username already exists..!!");
            }
            if(usersRepository.existsByEmail(registerDto.getEmail()))
            {
                 throw  new UsernameNotFoundException("Email already exists..!!");
            }
            Users users = new Users();
            users.setName(registerDto.getName());
            users.setUsername(registerDto.getUserName());
            users.setEmail(registerDto.getEmail());
            users.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Set<Roles> roles = new HashSet<Roles>();
             Roles userRole= rolesRepository.findByName("ROLE_USER").get();
                       roles.add(userRole);
                       users.setRoles(roles);

              usersRepository.save(users);
              return "User Register SuccessFull..!!";
        }
}
