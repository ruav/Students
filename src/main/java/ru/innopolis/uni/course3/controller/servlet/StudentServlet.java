package ru.innopolis.uni.course3.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.uni.course3.model.Student;
import ru.innopolis.uni.course3.service.StudentService;
import ru.innopolis.uni.course3.service.StudentServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Артем on 22.12.2016.
 */
public class StudentServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(StudentServlet.class);

    private ConfigurableApplicationContext springContext;
    private StudentService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring-context.xml");
        service = springContext.getBean(StudentServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null || "chooseStudent".equals(action)) {

            logger.info("StudentServlet: " + action == null ? "get" : "choose from" + " all students");

            req.setAttribute("students", service.getAll());
            req.setAttribute("journalId", req.getParameter("journalId"));
            req.setAttribute("lectureId", req.getParameter("lectureId"));
            req.setAttribute("lectureRepresentation", req.getParameter("lectureRepresentation"));
            req.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(req, resp);

        } else if ("delete".equals(action)){

            int id = getId(req);
            logger.info("StudentServlet: delete student with id", id);
            service.delete(id);
            resp.sendRedirect("students");

        } else if ("create".equals(action) || "update".equals(action)){

            final Student student = action.equals("create") ?
                    new Student("", "", "MALE", 1) : service.get(getId(req));
            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/jsp/editStudent.jsp").forward(req, resp);
        }
    }

    private int getId(HttpServletRequest req) {
        String paramId = Objects.requireNonNull(req.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        Student student = new Student(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("sex"),
                Integer.valueOf(req.getParameter("groupNumber")));
        logger.info("StudentServlet:  " + (student.isNew() ? "create of" : "update of") +  student);
        if(student.isNew() ){
            service.add(student);
        } else {
            service.update(student);
        }
        resp.sendRedirect("students");
    }
}
