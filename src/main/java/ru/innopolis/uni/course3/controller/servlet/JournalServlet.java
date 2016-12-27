package ru.innopolis.uni.course3.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.uni.course3.model.Journal;
import ru.innopolis.uni.course3.model.Lecture;
import ru.innopolis.uni.course3.model.Student;
import ru.innopolis.uni.course3.service.JournalService;
import ru.innopolis.uni.course3.service.JournalServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Артем on 24.12.2016.
 */
public class JournalServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(JournalServlet.class);

    private ConfigurableApplicationContext springContext;
    private JournalService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring-context.xml");
        service = springContext.getBean(JournalServiceImpl.class);
   }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null) {

            logger.info("Journal Servlet: get all records");

            req.setAttribute("journal", service.getAll());
            req.getRequestDispatcher("/WEB-INF/jsp/journal.jsp").forward(req, resp);

        } else if (action.equals("delete")){

            int id = getId(req);
            logger.info("Journal Servlet: delete record with id", id);
            service.delete(id);
            resp.sendRedirect("journal");

        } else if (action.equals("create") || action.equals("update")) {

            Integer id = getId(req);
            final Journal journal = (id == null) ?
                    new Journal(null, null, LocalDate.now()) : service.get(id);
            req.setAttribute("journal", journal);

            String studentId = req.getParameter("studentId");
            String studentRepresentation = req.getParameter("studentRepresentation");
            if (studentId != null && studentRepresentation != null) {
                req.setAttribute("studentId", studentId.trim());
                req.setAttribute("studentRepresentation", studentRepresentation.trim());
            }
            String lectureId = req.getParameter("lectureId");
            String lectureRepresentation = req.getParameter("lectureRepresentation");
            if (lectureId != null && lectureRepresentation != null) {
                req.setAttribute("lectureId", lectureId.trim());
                req.setAttribute("lectureRepresentation", lectureRepresentation.trim());
            }

            req.getRequestDispatcher("/WEB-INF/jsp/editJournalRecord.jsp").forward(req, resp);
        }
    }

    private Integer getId(HttpServletRequest req) {
        String id = req.getParameter("id");
        if(id == null || id.isEmpty()) { return null; }
        String paramId = Objects.requireNonNull(id).trim();
        return Integer.valueOf(paramId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String studentId = req.getParameter("studentId");
        String lectureId = req.getParameter("lectureId");
        String dateString = req.getParameter("date");
        Journal journal = new Journal(id.isEmpty() ? null : Integer.valueOf(id),
                new Student(studentId.isEmpty() ? null : Integer.valueOf(studentId)),
                new Lecture(lectureId.isEmpty() ? null : Integer.valueOf(lectureId)),
                LocalDate.parse(dateString.isEmpty() ? null : dateString));
        logger.info("Lecture Servlet:  " + (journal.isNew() ? "create of" : "update of") +  journal);
        if(journal.isNew() ){
            service.add(journal);
        } else {
            service.update(journal);
        }
        resp.sendRedirect("journal");
    }
}
