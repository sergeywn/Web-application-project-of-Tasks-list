package servlet;

import dto.CategoriesNameDto;
import dto.CreateTaskDto;
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

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {

    TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("Categories", CategoriesNameDto.values());
        req.setAttribute("UrgencyLevel", UrgencyLevelsDto.values());

        req.getRequestDispatcher(JspHelper.getPath("addTask")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createTaskDto = CreateTaskDto.builder()
                .taskName(req.getParameter("nameTask"))
                .categoryName(req.getParameter("categoryName"))
                .urgencyLevel(req.getParameter("urgencyLevel"))
                .date(req.getParameter("date"))
                .time(req.getParameter("time"))
                .build();

        try {
            taskService.addTask(createTaskDto);
            resp.sendRedirect("/resultAddTask");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req,resp);
        }
    }
}
