package servlet;

import dto.TaskFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TaskService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/allTasks")
public class AllTasksServlet extends HttpServlet {

    TaskFilter taskFilter = new TaskFilter(15,0,null,null,null,null);
    TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTasks",taskService.findAll(taskFilter));
        req.getRequestDispatcher(JspHelper.getPath("allTasks")).forward(req,resp);
    }


}
