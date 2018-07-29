package fr.perrine.essaiallodisney.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectHelper {

    public static void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userPseudo = (String) request.getSession().getAttribute("user");
        if (userPseudo == null || userPseudo.isEmpty()) {
            response.sendRedirect(request.getContextPath() +"/");
            return;
        }
    }
}
