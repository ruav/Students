package ru.innopolis.uni.course3.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.uni.course3.model.Lecture;
import ru.innopolis.uni.course3.service.LectureService;
import ru.innopolis.uni.course3.service.LectureServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by Артем on 24.12.2016.
 */
public class LectureServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LectureServlet.class);

    private ConfigurableApplicationContext springContext;
    private LectureService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring-context.xml");
        service = springContext.getBean(LectureServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action == null || "chooseLecture".equals(action)) {

            logger.info("Lecture Servlet: " + action == null ? "get" : "choose from" + " all lectures");

            req.setAttribute("lectures", service.getAll());
            req.setAttribute("journalId", req.getParameter("journalId"));
            req.setAttribute("studentId", req.getParameter("studentId"));
            req.setAttribute("studentRepresentation", req.getParameter("studentRepresentation"));
            req.getRequestDispatcher("/WEB-INF/jsp/lectures.jsp").forward(req, resp);

        } else if (action.equals("delete")){

            int id = getId(req);
            logger.info("Lecture Servlet: delete lecture with id", id);
            service.delete(id);
            resp.sendRedirect("lectures");

        } else if (action.equals("create") || action.equals("update")){

            final Lecture lecture = action.equals("create") ?
                    new Lecture("", 90) : service.get(getId(req));
            req.setAttribute("lecture", lecture);
            req.getRequestDispatcher("/WEB-INF/jsp/editLecture.jsp").forward(req, resp);
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
        Lecture lecture = new Lecture(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("topic"),
                Integer.valueOf(req.getParameter("duration")));
        logger.info("Lecture Servlet:  " + (lecture.isNew() ? "create of" : "update of") +  lecture);
        if(lecture.isNew() ){
            service.add(lecture);
        } else {
            service.update(lecture);
        }
        resp.sendRedirect("lectures");
    }
}
