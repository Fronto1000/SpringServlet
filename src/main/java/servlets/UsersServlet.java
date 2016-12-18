package servlets;

import org.springframework.context.ApplicationContext;
import service.UserService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet{

    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = (ApplicationContext)config.getServletContext().getAttribute("jdbcSpringContext");
        userService = (UserService) context.getBean("userService");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("users_list", userService.findAll());

        try {
            getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
