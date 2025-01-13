package servlet;

import dto.CategoriesNameDto;
import dto.CreateTaskDto;
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

import javax.swing.*;
import java.io.IOException;

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var taskId = Long.valueOf(req.getParameter("taskId"));
        req.setAttribute("Task",taskService.findById(taskId));
        req.setAttribute("Categories", CategoriesNameDto.values());
        req.setAttribute("UrgencyLevel", UrgencyLevelsDto.values());
        req.getRequestDispatcher(JspHelper.getPath("editTask")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var updateTaskDto = UpdateTaskDto.builder()
                .id(req.getParameter("taskId"))
                .taskName(req.getParameter("nameTask"))
                .categoryName(req.getParameter("categoryName"))
                .urgencyLevel(req.getParameter("urgencyLevel"))
                .date(req.getParameter("date"))
                .time(req.getParameter("time"))
                .build();

        try {
            taskService.updateTask(updateTaskDto);
            resp.sendRedirect("/resultUpdateTask");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req,resp);
        }
    }
}
