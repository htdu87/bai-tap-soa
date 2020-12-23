package soa.t2d.client.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.t2d.base.RestfulResponse;
import soa.t2d.base.model.KhachHang;
import soa.t2d.base.model.NguoiDung;
import soa.t2d.client.Const;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		if(session.getAttribute("LOGED_USER")==null) {
			response.sendRedirect("LogInController");
		} else {
			String keyword=request.getParameter("keyword");
			if(keyword==null||keyword.equalsIgnoreCase(""))keyword="%20";
			
			String act=request.getParameter("act");
			
			String id=request.getParameter("id");
			int idkh=0;
			if(id==null||id.equalsIgnoreCase(""))id="0";
			try {
				idkh=Integer.valueOf(id);
			} catch(Exception ex) {
				idkh=0;
			}
			// Set attribute idKH
			request.setAttribute("KH_IDKH", idkh);
			
			
			NguoiDung nd=(NguoiDung) session.getAttribute("LOGED_USER");
			
			request.setAttribute("USER", nd);
			request.setAttribute("TITLE", "Trang chủ");
			request.setAttribute("CONTENT", "home");
			
			// Lay thong tin khach hang tu webservice
			if(idkh>0) {
				if(act.equalsIgnoreCase("del")) { // Xoa khach hang
					RestfulResponse resXoaKh=xoaKhachHang(nd.getTenDangNhap(), nd.getMatKhau(), idkh);
					if(resXoaKh!=null && resXoaKh.getResCode()>0) {
						request.setAttribute("DEL_MSG", "<span class=\"text-success\">Đã xóa khách hàng!</span>");
					} else {
						request.setAttribute("DEL_MSG", "<span class=\"text-danger\">Xóa khách hàng không thành công, vui lòng thử lại sau</span>");
					}
					request.setAttribute("KH_IDKH", 0);
				} else { // Lay thong tin khach hang
					RestfulResponse resKh=layKhachHang(nd.getTenDangNhap(), nd.getMatKhau(), idkh);
					if (resKh.getResCode()>0) {
						KhachHang kh=(KhachHang)resKh.getResData();
						
						request.setAttribute("KH_MAKH", kh.getMaKhachHang());
						request.setAttribute("KH_TKH", kh.getTenKhachHang());
						request.setAttribute("KH_DC", kh.getDiaChi());
						request.setAttribute("KH_SDT", kh.getSoDienThoai());
						
					} else if(resKh.getResCode() == 0) {
						request.setAttribute("SAVE_MSG", "Không tìm thấy khách hàng");
					} else {
						request.setAttribute("SAVE_MSG", "<span class=\"text-danger\">Lỗi lấy thông tin khách hàng, vui lòng thử lại sau</span>");
					}
				}
			}
			
			// Lay danh sach khach hang tu webservice
			RestfulResponse res=layDsKhachHang(nd.getTenDangNhap(), nd.getMatKhau(), keyword);
			if(res.getResCode()>0) {
				request.setAttribute("DS_KH", res.getResData());
			} else {
				request.setAttribute("DS_KH", new ArrayList<KhachHang>());
			}
			
			request.getRequestDispatcher("static/templates/layout.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("LOGED_USER")==null) {
			response.sendRedirect("LogInController");
		} else {
			NguoiDung nd=(NguoiDung) session.getAttribute("LOGED_USER");
			request.setCharacterEncoding("UTF-8");
			
			String idKhachHang=request.getParameter("idKhachHang");
			String maKhachHang=request.getParameter("maKhachHang");
			String tenKhachHang=request.getParameter("tenKhachHang");
			String diaChi=request.getParameter("diaChi");
			String soDienThoai=request.getParameter("soDienThoai");
			
			if(idKhachHang!=null && !idKhachHang.equalsIgnoreCase("")) {
				KhachHang kh=new KhachHang();
				kh.setIdKhachHang(Integer.valueOf(idKhachHang));
				kh.setMaKhachHang(maKhachHang);
				kh.setTenKhachHang(tenKhachHang);
				kh.setDiaChi(diaChi);
				kh.setSoDienThoai(soDienThoai);
				
				ObjectMapper mapper=new ObjectMapper();
				String data=mapper.writeValueAsString(kh);
				
				RestfulResponse res=luuKhachHang(nd.getTenDangNhap(), nd.getMatKhau(), nd.getIdNguoiDung(), data);
				String msg;
				if(res!=null && res.getResCode()>0)
					msg="<span class=\"text-success\">Lưu khách hàng thành công</span>";
				else if(res.getResCode()<0 && res.getResMessage().contains("Duplicate entry"))
					msg="<span class=\"text-danger\">Mã khách hàng đã tồn tại, vui lòng kiểm tra lại</span>";
				else
					msg="<span class=\"text-danger\">Lưu khách hàng không thành công, vui lòng thử lại sau</span>";
				
				request.setAttribute("SAVE_MSG", msg);
			}
			
			doGet(request, response);
		}
	}

	private RestfulResponse luuKhachHang(String usr, String pwd, int id, String data) {
		HttpURLConnection conn;
        InputStream in;
        OutputStream out;
        try {
	        URL url=new URL(Const.SVR_URL+ "qlkh/luu/"+usr+"/"+pwd+"/"+id);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setUseCaches(false);
	        conn.setConnectTimeout(30000);
	        conn.setReadTimeout(30000);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);
	        
	        //System.out.println(data);
	        out=conn.getOutputStream();
            out.write(data.getBytes("utf-8"));
            out.flush();
            out.close();
	        
            if(conn.getResponseCode()==200) {
		        in = new BufferedInputStream(conn.getInputStream());
		        Scanner scanner=new Scanner(in,"UTF-8");
		        String res=scanner.useDelimiter("\\A").next();
		        
		        scanner.close();
		        //System.out.println(res);
		        
		        ObjectMapper mapper=new ObjectMapper();
		        return mapper.readValue(res, RestfulResponse.class);
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private RestfulResponse layDsKhachHang(String usr, String pwd, String keyword) {
		InputStream in;
        HttpURLConnection conn;
        try {
	        URL url=new URL(Const.SVR_URL+ "qlkh/lay-ds/"+usr+"/"+pwd+"/"+keyword);
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
	        rest.setResData(mapper.convertValue(rest.getResData(), new TypeReference<List<KhachHang>>(){}));
	        return rest;
        } catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private RestfulResponse layKhachHang(String usr, String pwd, int id) {
		InputStream in;
        HttpURLConnection conn;
        try {
	        URL url=new URL(Const.SVR_URL+ "qlkh/lay-kh/"+usr+"/"+pwd+"/"+id);
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
	        rest.setResData(mapper.convertValue(rest.getResData(), KhachHang.class));
	        return rest;
        } catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private RestfulResponse xoaKhachHang(String usr, String pwd, int id) {
		InputStream in;
        HttpURLConnection conn;
        try {
	        URL url=new URL(Const.SVR_URL+ "qlkh/xoa/"+usr+"/"+pwd+"/"+id);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setUseCaches(false);
	        conn.setConnectTimeout(30000);
	        conn.setReadTimeout(30000);
	        conn.setRequestMethod("DELETE");
	        
	        in = new BufferedInputStream(conn.getInputStream());
	        Scanner scanner=new Scanner(in,"UTF-8");
	        
	        String res=scanner.useDelimiter("\\A").next();
	        
	        scanner.close();
	        //System.out.println(res);
	        
	        ObjectMapper mapper=new ObjectMapper();
	        return mapper.readValue(res, RestfulResponse.class);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return null;
	}
}
