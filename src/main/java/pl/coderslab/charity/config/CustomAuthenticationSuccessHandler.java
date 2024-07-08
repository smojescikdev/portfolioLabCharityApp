package pl.coderslab.charity.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


//obsługuje zdarzenie pomyślnego uwierzytelnienia użytkownika,
// drukując jego nazwę użytkownika i przekierowując go na stronę "/dashboard/"
// w zależności od posiadanych ról ("Job Seeker" lub "Recruiter")


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("The username " + username + " is logged in");
        boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("User"));
        boolean hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Admin"));




        //role admin or user
        if (hasAdminRole) {
            response.sendRedirect("/admin/admin-dashboard");
        } else if (hasUserRole) {
            response.sendRedirect("/");
        }



    }
}
