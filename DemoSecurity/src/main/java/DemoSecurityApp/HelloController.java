package DemoSecurityApp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
     @GetMapping("/home")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
     public  String home()
     {
         return  "This is home";
     }

     @GetMapping("/world")
     public String world()
     {
         return "This is java world";
     }
}
