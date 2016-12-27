package ru.innopolis.uni.course3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*<servlet>
    <servlet-name>MyFirstServlet</servlet-name>
    <servlet-class>ru.innopolis.uni.course3.MyFirstServlet</servlet-class>
    &lt;!&ndash;<jsp-file>/index_myfirstservlet.jsp.jsp</jsp-file>&ndash;&gt;
</servlet>
    <servlet-mapping>
    <servlet-name>MyFirstServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>*/

/**
 * Created by Артем on 19.12.2016.
 */
public class MyFirstServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        name = (name == null) ? "Мир" : name;

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>MyServlet.java:doGet(): StartServlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>Привет, " + name + "</h1>");

            writer.println("</body>");
            writer.println("</html>");
        }

    }
}
