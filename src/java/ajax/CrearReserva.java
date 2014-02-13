
package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import generalsax.XMLLector;

/**
 * Reservarà una sèrie d'habitacions pels usuaris donats
 */
@WebServlet(name = "CrearReserva", urlPatterns = {"/CrearReserva"})
public class CrearReserva extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CrearReserva</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearReserva at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final int places = Integer.parseInt(request.getParameter("placesVal"));
        String dataIni = request.getParameter("dataIni");
        String dataFi = request.getParameter("dataFi");
        StringBuilder sb = new StringBuilder();
        sb.append("<reserva>");
        //Guardam tots els paràmetres ordenadament
        for (int i = 0; i < places; i++) {
            sb.append("<hoste>");
            sb.append("<nom>"); sb.append(request.getParameter("nom-" + i)); sb.append("</nom>");
            sb.append("<mail>"); sb.append(request.getParameter("mail-" + i)); sb.append("</mail>");
            sb.append("<dni>"); sb.append(request.getParameter("dni-" + i)); sb.append("</dni>");
            sb.append("<nac>"); sb.append(request.getParameter("nac-" + i)); sb.append("</nac>");
            sb.append("<tip>"); sb.append(request.getParameter("tip-" + i)); sb.append("</tip>");
            sb.append("</hoste>");
        }
        sb.append("<dataini>").append(dataIni).append("</dataini>");
        sb.append("<datafi>").append(dataFi).append("</datafi>");
        sb.append("</reserva>");
        
        webservice.ConnexioHotel_Service servei = new webservice.ConnexioHotel_Service();
        webservice.ConnexioHotel port = servei.getConnexioHotelPort();
        String reserva = port.crearReserva(sb.toString());
        StringBuilder res = new StringBuilder();
        new XMLLector().comprovarCreacioReserva(reserva, res);
        
        if (res.toString().equals("true")) {
            request.getSession().setAttribute("preu", request.getParameter("preuFinal"));
            response.sendRedirect("reservaOk/reservaOk.jsp");
        } else {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<reserva>S'ha produit un error</reserva>");
        }

    }

}
