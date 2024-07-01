package DemoSecurityApp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator
{
    public static void main(String[] args)
    {
        PasswordEncoder str=new BCryptPasswordEncoder();
        System.out.println(str.encode("selvin"));
        System.out.println(str.encode("admin"));
    }
}
