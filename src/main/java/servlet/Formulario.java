package servlet;

/*Author: López Alejandra
/ Fecha: 1/11/2025
 / Descripción: Esta clase se encarga de presentar las validaciones de la información solicitada , como son:
 /la validacion de los dígitos de la cédula, la edad en el momento ingreso de nombre
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

//Clave de llamada a index
@WebServlet("/servletFormulario")


public class Formulario extends HttpServlet {

    /*Método doPost, se desarrollan las validaciones de los datos solicitados en la tarea
     *@param nombre, apellido, cedula, carrera, edad, fechaNacimiento. parámetros que
     * resuelven las restricciones solicitadas
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //Es necesario que vaya antes de todas las lecturas  ( out.println)
        PrintWriter out = resp.getWriter(); //Necesario para lectura de datos ingresados por el usuario

        //Se obtienen los parámetros
        String usuario = req.getParameter("usuario");
        String clave = "*9465*";
        String password = req.getParameter("password");
        //Parámetros tarea
        String nombre = req.getParameter("nombre"); //String
        String apellido = req.getParameter("apellido"); //String
        String cedula = req.getParameter("cedula"); //String , no menor a 9 dígitos ni mayor a 10
        String carrera = req.getParameter("carrera");
        String nacimiento = req.getParameter("nacimiento");

        //Variables usadas para calcular edad

        int edadAnios = 0;
        int edadMeses = 0;
        int edadDias = 0;
        int edad = 0;

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

        try {
            //Se importa LocalDate para realizar el cálculo
            LocalDate fechaNacimiento = LocalDate.parse(nacimiento);
            LocalDate fechaActual = LocalDate.now();

            // Validar que la fecha no sea futura
            if (fechaNacimiento.isAfter(fechaActual)) {
                out.printf("La fecha de nacimiento no puede ser una fecha futura.");
                return;
            }

            Period periodo = Period.between(fechaNacimiento, fechaActual);
            edadAnios = periodo.getYears();
            edadMeses = periodo.getMonths();
            edadDias = periodo.getDays();

            //Se obtiene la edad calculando el tiempo entre periodos
            edad = Period.between(fechaNacimiento, fechaActual).getYears();

            if (edad < 1 || edad > 90) { // Validación simple de edad
                out.printf("La edad calculada (" + edad + " años) no es válida.");
                return;
            }

        } catch (DateTimeParseException e) {
            out.printf("El formato de la fecha de nacimiento no es válido.");
            return;
        }


        // Validación de carrera
        if (carrera == null || carrera.trim().isEmpty()) {
            out.println("<p>Debe seleccionar una carrera.</p>");
            return;
        }

        //impresión
        out.println("<h2>Bienvenid@  " + usuario + "<h2>");
        //out.println("<h2>Código del formulario  " + clave + "<h2>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Apellido: " + apellido + "</p>");
        out.println("<p>Cédula: " + cedula + "</p>");
        out.println("<p>Carrera: " + carrera + "</p>");
        out.println("<p>Fecha de nacimiento: " + nacimiento + "</p>");
        out.printf("Edad Actual: %d años, %d meses y %d días.", edadAnios, edadMeses, edadDias);


    }
}



