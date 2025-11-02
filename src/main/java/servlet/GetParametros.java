/*package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("parametros")


public class GetParametros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // El tipo de documento que va a devolver el servlet
        resp.setContentType("txt/html");
        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet GetParametros</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet GetParametros</h1>");
        out.println("<h2>usuario: " + usuario + "</h2>");
        out.println("<h2>password: " + password + "</h2>");
        out.println("<h2>nombre: " + nombre + "</h2>");
        out.println("<h2>apellido: " + apellido + "</h2>");
        out.println("<h2>carrera: " + carrera + "</h2>");
        out.println("<h2>nacimiento: " + nacimiento + "</h2>");
        out.println("<h2>Edad calculada: " + edad + "</h2>");
        out.println("<h1>Datos procesados con éxito</h1>");
        out.println("</body>");
        out.println("</html>");
        out.println("<p>");
        out.println("</body>");
        out.println("</html>");
    }
}
*/