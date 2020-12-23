package soa.t2d.client.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import soa.t2d.base.RestfulResponse;
import soa.t2d.base.model.NguoiDung;
import soa.t2d.client.Const;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/LogInController")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		String usr=request.getParameter("username");
		String pwd=request.getParameter("password");
		
		if(usr!=null && !usr.equalsIgnoreCase("") 
				&& pwd!=null && !pwd.equalsIgnoreCase("")) {
			
			RestfulResponse res=doLogin(usr, pwd);
			if(res!=null && res.getResCode()>0) {
				session.setAttribute("LOGED_USER", res.getResData());
				response.sendRedirect("HomeController");
			} else {
				request.setAttribute("MSG", "<span style=\"color:#dd4b39\">Đăng nhập không thành công!</span>");
				request.getRequestDispatcher("static/templates/login.jsp").forward(request, response);
			}
		} else {
			if(session.getAttribute("LOGED_USER")!=null) {
				response.sendRedirect("HomeController");
			} else {
				request.setAttribute("MSG", "Đăng nhập tài khoản");
				request.getRequestDispatcher("static/templates/login.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private RestfulResponse doLogin(String usr, String pwd) {
		InputStream in;
        HttpURLConnection conn;
        try {
	        URL url=new URL(Const.SVR_URL+ "qlkh/login/"+usr+"/"+pwd);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setUseCaches(false);
	        conn.setConnectTimeout(30000);
	        conn.setReadTimeout(30000);
	        conn.setRequestMethod("GET");
	        
	        in = new BufferedInputStream(conn.getInputStream());
	        Scanner scanner=new Scanner(in,"UTF-8");
	        
	        String res=scanner.useDelimiter("\\A").next();
	        
	        scanner.close();
	        //System.out.println(res);
	        
	        ObjectMapper mapper=new ObjectMapper();
	        RestfulResponse rest=mapper.readValue(res, RestfulResponse.class);
	        rest.setResData(mapper.convertValue(rest.getResData(), NguoiDung.class));
	        //return mapper.readValue(res, new TypeReference<RestfulResponse>() {});
	        return rest;
        } catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
}
