import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class display extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/info_system","postgres","nnn");
            PreparedStatement ps = con.prepareStatement("select img from student.album where id=? and aname=?");
            String id = request.getParameter("id");
            String an=request.getParameter("aname");
            ps.setString(1,id);
            ps.setString(2,an);
            ResultSet rs = ps.executeQuery();
            rs.next();
            byte[]  b = rs.getBytes("img");
            response.setContentType("image/jpg");
            //response.setContentLength( (int) b.length());
            InputStream is = rs.getBinaryStream("img");
            OutputStream os = response.getOutputStream();
            byte buf[] = new byte[(int) is.available()];
            is.read(buf);
            os.write(buf);
            os.close();
        }
        catch(Exception ex) {
             System.out.println(ex.getMessage());
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