package org.epam.final_project.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessEntrantFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        // TODO document why this method is empty
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;

        HttpSession session=request.getSession();
        String role=String.valueOf(session.getAttribute("role"));
        if(!role.equals("entrant")){
            String message_index="У вас нет прав перейти на страницу";
            session.setAttribute("message_index",message_index);
            request.getRequestDispatcher("/").forward(servletRequest,servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        // TODO document why this method is empty
    }
}
