package cn.jsu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletConfig config=null;
    //���ݿ����
    // JDBC �����������ݿ� URL
    static String JDBC_DRIVER = "";  
    static String DB_URL = "";
    
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static String USER = "";
    static String PASS = ""; 
    /** 
     * @see HttpServlet#HttpServlet() 
     */
    
    public void init(ServletConfig config)throws ServletException{  
        super.init(config);                            //�̳и����init()����  
        this.config=config;                            //��ȡ������Ϣ  
        JDBC_DRIVER=config.getInitParameter("driverName");//�������ļ��л�ȡJDBC������  
        USER=config.getInitParameter("name");    //��ȡ���ݿ��û���  
        PASS=config.getInitParameter("password");    //��ȡ���ݿ���������  
        DB_URL=config.getInitParameter("dbName");    //��ȡҪ���ӵ����ݿ�  
     }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        response.setContentType("text/xml;charset=UTF-8");
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String sql = "select * from `users` where user_id = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        if( rs.next()==false ) {
	        	response.getWriter().write("<span style='color:red;'>������ʾ��û�д��û�,����ע��<span>");
	        	return ;
	        }
	        sql = "select * from `new_user` where user_name = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        rs = pstmt.executeQuery();
	        if( rs.next() ) response.getWriter().write("<span style='color:red;'>������ʾ�����û��ѱ���<span>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
