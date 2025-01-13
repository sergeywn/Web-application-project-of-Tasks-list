package servlet;

import dto.AccountDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.AccountService;
import util.JspHelper;
import util.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        accountService.login(req.getParameter("accountName"), req.getParameter("password")).ifPresentOrElse(
                accountDto -> onLoginSuccess(accountDto,req,resp),
                () -> onLoginFail(req, resp)
        );
    }

    @SneakyThrows
    private void onLoginSuccess(AccountDto accountDTO, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", accountDTO);
        resp.sendRedirect("/allTasks");
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&accountName=" + req.getParameter("accountName"));
    }
}

