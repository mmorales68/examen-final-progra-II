package Controladores;

import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/PastasJSONServlet")
public class PastasJSONServlet extends HttpServlet {
    
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/base_datos_final";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONArray jsonArray = new JSONArray();

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            String query = "SELECT id_tipo_pasta, descripcion FROM base_datos_final.tipo_pasta";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("id_tipo_pasta"));
                jsonObject.put("descripcion", rs.getString("descripcion"));
                jsonArray.put(jsonObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print(jsonArray.toString());
        out.flush();
    }
}
