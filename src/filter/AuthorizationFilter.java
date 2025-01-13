package filter;

import dto.AccountDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static util.UrlPath.LOGIN;
import static util.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    public static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isUserLoggedInNoAdmin(servletRequest)) {
            if (uri.equals("/allTasksChild")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (uri.equals("/logout")) {
                filterChain.doFilter(servletRequest, servletResponse);;
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/allTasksChild");
            }
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : "/login");
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (AccountDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null && user.getRoleAdmin();
    }

    private boolean isUserLoggedInNoAdmin(ServletRequest servletRequest) {
        var user = (AccountDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
