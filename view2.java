/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Naveena
 */
public class view2 extends HttpServlet {

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
         PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            //String s1=session.getAttribute("uid");
               Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/info_system","postgres","nnn");
            PreparedStatement ps = con.prepareStatement("select distinct aname from student.album");
            ResultSet rs = ps.executeQuery();
             out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"css\\css3.css\">");
            out.println("<div id=\"id2\">\n" +
"            <ul>\n" +
"                <li><a href=\"home.jsp\"><label for=\"home\">Home</label></a></li>\n" +
"                <li><a href=\"\"><label>Update</label></a></li>\n" +
"                <li><a href=\"signout.jsp\"><label>Logout</label></a></li>\n" +
"            </ul>\n" +
"        </div>");
            out.println("<center><h1>Albums</h1>");
            while ( rs.next()) {
                    
                  //out.println("<h4>" + rs.getString("title") + "</h4>");
                 
                
                  out.println("<div class=\"gallery\"><a target=\"_blank\" href=\"view?aname="+rs.getString("aname")+"\"><img width=\"300\" height=\"200\" src=display?id=e001&aname="+rs.getString("aname")+"></img><div class=\"desc\">"+rs.getString("aname")+"</div></a> \n" +
"</div> <p/>");
                  
            }
  
 
 
 

            con.close();
        }
        catch(Exception ex) {

        }
        finally { 
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
