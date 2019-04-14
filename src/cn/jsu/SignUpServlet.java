package cn.jsu;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
 
  
/** 
 * Servlet implementation class AjaxDemo1 
 */  
public class SignUpServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;
    ServletConfig config=null;
    //数据库相关
    // JDBC 驱动名及数据库 URL
    static String JDBC_DRIVER = "";  
    static String DB_URL = "";
    
    // 数据库的用户名与密码，需要根据自己的设置
    static String username = "";
    static String PASS = ""; 
    /** 
     * @see HttpServlet#HttpServlet() 
     */
    
    public void init(ServletConfig config)throws ServletException{  
        super.init(config);                            //继承父类的init()方法  
        this.config=config;                            //获取配置信息  
        JDBC_DRIVER=config.getInitParameter("driverName");//从配置文件中获取JDBC驱动名  
        username=config.getInitParameter("username");    //获取数据库用户名  
        PASS=config.getInitParameter("password");    //获取数据库连接密码  
        DB_URL=config.getInitParameter("dbName");    //获取要连接的数据库  
     } 
    
    private String enCoding(String str) {
    	try {
			str = new String(str.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return str;
    }
    
    public SignUpServlet() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
          
        this.doPost(request, response);  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = null;
    	// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        String userName= enCoding(request.getParameter("userName"));
        String school = enCoding(request.getParameter("school"));
        String myMajor = enCoding(request.getParameter("major"));
        String addername = enCoding(request.getParameter("name"));
        String adderss = enCoding(request.getParameter("adderss"));
        String phone = enCoding(request.getParameter("phone"));
        int t = school.equals("吉首大学")?1:0;
        try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,username,PASS);
			String sql;
			sql = "INSERT INTO `new_user` (user_name,school,major,name,adder,phone,is_jsu) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, school);
			pstmt.setString(3, myMajor);
			pstmt.setString(4, addername);
			pstmt.setString(5, adderss);
			pstmt.setString(6, phone);
			pstmt.setInt(7, t);
			pstmt.executeUpdate();
			response.sendRedirect("/Siginup/success.html");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}  