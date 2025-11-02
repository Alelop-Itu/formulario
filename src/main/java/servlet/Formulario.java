package servlet;

/*Author: López Alejandra
/ Fecha: 1/11/2025
 / Descripción: Esta clase se encarga de presentar las validaciones de la información solicitada , como es
 /la validacion de los dígitos de la cédula, la edad en el momento, la selección de la carrera, ingreso de nombre
 / y apellido.
*/

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/servletFormulario")

/*Método Formulario usado para crear el array de carreras
*@param carrerasValidas, parámetro que contiene las carreras que se van a mostrar
* */


public class Formulario extends HttpServlet {

    //Lista de carreras válidas, se crea un Array para mostrar una lista de selección
    public static final List<String> carrerasValidas = Arrays.asList(
            "Desarrollo de software",
            "Marketing",
            "Multimedia",
            "Veterinaria"
    );

    /*Método doPost, se desarrollan las validaciones de los datos solicitados en la tarea
    *@param nombre, apellido, cedula, carrera, edad, usuario, password, fechaNacimiento, carrerasValidas. parámetros que
    * resuelven las restricciones solicitadas
    */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //Se obtienen los parámetros
        String usuario = req.getParameter("usuario");
        String clave = "12345";
        String password = req.getParameter("password");

        //Parámetros tarea
        String nombre = req.getParameter("nombre"); //String
        String apellido = req.getParameter("apellido"); //String
        String cedula = req.getParameter("cedula"); //String , no menor a 9 dígitos ni mayor a 10
        String carrera = req.getParameter("carrera");
        String nacimiento = req.getParameter("fechaNacimiento");


        //validación longitud cédula
        PrintWriter out = resp.getWriter(); //Necesario para lectura de datos ingresados por el usuario

        if (cedula == null || cedula.trim().isEmpty()) {
            out.println("<p>Debe ingresar una cédula.</p>");
            return;
        }

        int longitud = cedula.length();
        if (longitud < 9 || longitud > 10) {
            out.printf("<p>La cédula '%s' tiene %d dígitos. Debe tener entre 9 y 10 dígitos.</p>",
                    cedula, longitud);
            return;
        }

        //Cálculo edad - Fecha de nacimiento
        //Variable usada para calcular edad
        int edad = 0;
        try {
            //Se importa LocalDate para realizar el cálculo
            LocalDate fechaNacimiento = LocalDate.parse(nacimiento);
            LocalDate fechaActual = LocalDate.now();

            // Validar que la fecha no sea futura
            if (fechaNacimiento.isAfter(fechaActual)) {
                out.printf("La fecha de nacimiento no puede ser una fecha futura.");
                return;
            }
            //Se obtiene la edad calculando el tiempo entre periodos
            edad = Period.between(fechaNacimiento, fechaActual).getYears();

            if (edad < 1 || edad > 90) { // Validación simple de edad
                out.printf("La edad calculada (" + edad + " años) no es válida.");
                return;
            }

        } catch (DateTimeParseException e) {
            out.printf("El formato de la fecha de nacimiento no es válido. Use YYYY-MM-DD.");
            return;
        }

        // Validación de carrera
        if (carrera == null || carrera.trim().isEmpty()) {
            out.println("<p>Debe seleccionar una carrera.</p>");
            return;
        }

        if (!carrerasValidas.contains(carrera)) {
            out.println("<p>La carrera seleccionada no es válida.</p>");
            return;
        }

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



