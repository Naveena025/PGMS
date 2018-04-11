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

public class view extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/info_system","postgres","nnn");
            PreparedStatement ps = con.prepareStatement("select * from student.album where aname=?");
            String an=request.getParameter("aname");
            ps.setString(1,an);
            ResultSet rs = ps.executeQuery();
             out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"css\\css3.css\">");
            out.println("<div id=\"id2\">\n" +
"            <ul>\n" +
"                <li><a href=\"home.jsp\"><label for=\"home\">Home</label></a></li>\n" +
"                <li><a href=\"\"><label>Update</label></a></li>\n" +
"                <li><a href=\"signout.jsp\"><label>Logout</label></a></li>\n" +
"            </ul>\n" +
"        </div>");
            out.println("<center><h1>Photos</h1>");
            while ( rs.next()) {
                   
                
                  out.println("<div class=\"gallery\"><a target=\"_blank\" href=\"display?id="+rs.getString("id")+"&aname="+rs.getString("aname")+"\"><img width=\"300\" height=\"200\" src=display?id="+rs.getString("id")+"&aname="+rs.getString("aname")+"></img><div class=\"desc\"></div></a> \n" +
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}