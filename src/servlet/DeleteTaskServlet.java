package servlet;

import dto.CategoriesNameDto;
import dto.UpdateTaskDto;
import dto.UrgencyLevelsDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TaskService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var taskId = Long.valueOf(req.getParameter("taskId"));
        req.setAttribute("Task",taskService.findById(taskId));
        req.getRequestDispatcher(JspHelper.getPath("deleteTask")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var taskId = Long.parseLong(req.getParameter("taskId"));
            taskService.deleteTask(taskId);
            resp.sendRedirect("/resultDeleteTask");
    }
}
