package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje = null;
        Cookie[] cookies = request.getCookies();
        Cookie contador = buscaCookie("contador", cookies);

        if (contador == null) {
            
            // Creamos el cookie
            Cookie cookie = new Cookie("contador", "1");
            cookie.setMaxAge(180);
            response.addCookie(cookie);

            // Imprimir el resultado de la primera visita
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            mensaje = "Gracias por visitar nuestra pagina ";
            out.println("<h1 align=center style=color:red;>" + mensaje + "</h1>");
            out.println("<h2 style=color:blue;>Primera visita</h2>");
            out.println("<button><a href='index.jsp'>Ir al Inicio</a></button>");

        } else {

            // Obtenemos el valor actual del contador
            int contCookies = Integer.parseInt(contador.getValue());
            contCookies++;

            // Modificamos el valor de la cookie incrementando el contador
            Cookie cookie = new Cookie("contador", "" + contCookies);
            cookie.setMaxAge(180);
            response.addCookie(cookie);

            // Imprimir el resultado de la primera visita
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            mensaje = "Estamos agradecidos por tenerlo nuevamente";
            out.println("<h1 align=center style=color:red;>" + mensaje + "</h1>");
            out.println("<h2 style=color:blue;>Visita numero " + contCookies + "</h2>");
            out.println("<button><a href='index.jsp'>Ir al Inicio</a></button>");
        }
    }
    
    // Busca la cookie devuelve null si no esta
    private Cookie buscaCookie(String nombre,
            Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(nombre)) {
                return cookies[i];
            }
        }
        return null;
    }
}
