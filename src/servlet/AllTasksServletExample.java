/*
package servlet;

import dto.TaskFilter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TaskService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/Family_tasks_list")
public class AllTasksServletExample extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();
    private final TaskFilter taskFilter = new TaskFilter(5, 0, null, null, null, null);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<!DOCTYPE html>\n" +
                    "<html lang=\"ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Семейная программа</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            background-color: #f4f4f4;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .header {\n" +
                    "            background-color: #333;\n" +
                    "            color: white;\n" +
                    "            text-align: center;\n" +
                    "            padding: 10px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        table {\n" +
                    "            width: 100%;\n" +
                    "            border-collapse: collapse;\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        th, td {\n" +
                    "            border: 1px solid #ddd;\n" +
                    "            padding: 8px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        th {\n" +
                    "            background-color: #ddd;\n" +
                    "        }\n" +
                    "        \n" +
                    "        tbody tr:nth-child(even) {\n" +
                    "            background-color: #f2f2f2;\n" +
                    "        }\n" +
                    "        \n" +
                    "        button {\n" +
                    "            padding: 8px 16px;\n" +
                    "            background-color: #4CAF50;\n" +
                    "            color: white;\n" +
                    "            border: none;\n" +
                    "            cursor: pointer;\n" +
                    "            transition: 0.3s;\n" +
                    "        }\n" +
                    "        \n" +
                    "        button:hover {\n" +
                    "            background-color: #45a049;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .add-button {\n" +
                    "            float: right;\n" +
                    "            margin-right: 15px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    \n" +
                    "    <!-- Шапка сайта -->\n" +
                    "    <div class=\"header\">\n" +
                    "        <h1>Список дел / мероприятий</h1>\n" +
                    "    </div>\n" +
                    "    \n" +
                    "    <!-- Кнопки управления -->\n" +
                    "    <button class=\"add-button\">Добавить</button>\n" +
                    "    \n" +
                    "    <!-- Таблица данных -->\n" +
                    "       <table>\n" +
                    "        <thead>\n" +
                    "            <tr>\n" +
                    "                <th>Наименование</th>\n" +
                    "                <th>Категория</th>\n" +
                    "                <th>Важность</th>\n" +
                    "                <th>Дата</th>\n" +
                    "                <th>Время</th>\n" +
                    "                <th>Действия</th>\n" +
                    "            </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>");
            taskService.findAll(taskFilter).forEach(taskDto -> {
                writer.write("""
                        <tr>
                        <td> %s </td> <td> %s </td> <td> %s </td> <td> %s </td> <td> %s </td> <td> <button>Редактировать</button> <button>Удалить</button> </td>
                        </tr>
                        """.formatted(taskDto.getTaskName(), taskDto.getCategoryName(), taskDto.getUrgencyLevel(), taskDto.getDate(), taskDto.getTime()));
            });

            writer.write("</tbody>\n" +
                    "    </table>\n" +
                    "    \n" +
                    "</body>\n" +
                    "</html>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
*/