import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadphoto1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String r1= (String) session.getAttribute("uid");
String r2= (String) session.getAttribute("upd");
if(r1==null||r2==null)
{
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css\\msg.css\">");
    out.println("<center>"+"<div id=\"msg\">\n" + "Please Login"+"<br>" + "<a href=\"index.html\"><label>Login Page</label></a>" + "</div>"+"</center>");          
}
else
{
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu  = new ServletFileUpload(factory);

            if (! ServletFileUpload.isMultipartContent(request)) {
                System.out.println("sorry. No file uploaded");
                return;
            }
 out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"css\\menu.css\">");
            out.println("<div id=\"id2\">\n" +
"        \n" +
"            <ul>\n" +
"                 <li><a href=\"signout.jsp\"><label>Logout</label></a></li>\n" +
"                <li><a href=\"viewjsp.jsp\"><label for=\"home\">Home</label></a></li>\n" +
"                <li style=\"float:left\">&nbsp;<img src=\"css/icon.png\" width=\"70\" height=\"60\"/><label for=gl><font size=6px>Gallery</font></label></li>\n" +
"               \n" +
"            </ul>\n" +
"        </div>");
            // parse request
            List items = sfu.parseRequest(request);
            /*FileItem  id = (FileItem) items.get(0);
            String name =  id.getString();*/
          String r3= (String) session.getAttribute("an");
            
            /*FileItem name = (FileItem) items.get(1);
            String   phototitle =  name.getString();

            FileItem addr = (FileItem) items.get(2);
            String   photoaddr =  addr.getString();
*/
            // get uploaded file
            FileItem file = (FileItem) items.get(0);
              String fname=file.getName();          
            // Connect to Oracle
            Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/info_system","postgres","nnn");
            con.setAutoCommit(false);
            
           PreparedStatement ps1=con.prepareStatement("select id from student.album where name=? and pwd=? and aname=? order by id desc");
           ps1.setString(1,r1);
           ps1.setString(2,r2);
           ps1.setString(3,r3);
           ResultSet rs=ps1.executeQuery();
           String cd="";
           if(!rs.next())
           {
               cd="e001";
           }
           else
           {
               
	String cd1=rs.getString("id");
	cd1=cd1.substring(1);
	int n=Integer.parseInt(cd1);
	n++;
	if(n>=0 && n<10)
		cd="E00" + n;
	else if(n>=10 && n<100)
		cd="E0" + n;
	else if(n>=100 && n<1000)
		cd="E" + n;
           }
           
            PreparedStatement ps = con.prepareStatement("insert into student.album values(?,?,?,?,?) ");
                ps.setString(1, r1);
            ps.setString(2, r2);
            ps.setString(3,r3);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(4, file.getInputStream(), (int) file.getSize());
            ps.setString(5,cd);
            ps.executeUpdate();
            con.commit();
            con.close();
             out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css\\msg.css\">");
          out.println("<center>"+"<br><br><br>"+"<div id=\"msg\">\n" + "Photo added successfully"+"<br>"+"<a target=\"_self\" href=view.jsp?aname="+r3+">Back to Album</a>"+"</div>"+"</center>"); 
           
            }
            
            
        catch(Exception ex) {
            out.println( "Error --> " + ex.getMessage());
        }
    } 
    }
}