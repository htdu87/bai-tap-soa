package soa.t2d.server;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import soa.t2d.base.RestfulResponse;
import soa.t2d.base.Utility;
import soa.t2d.base.model.KhachHang;
import soa.t2d.base.model.NguoiDung;

@Path("/qlkh")
public class QlkhRestfulService {

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public void testServ() {
		
		NguoiDung nd = new NguoiDung();
		nd.testFunc();
		
	}
	
	@GET
	@Path("/login/{a}/{b}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse doLogin(@PathParam("a") String usr, @PathParam("b") String pwd) {
		Connection conn=null;
		RestfulResponse res=new RestfulResponse();
		
		try {
			conn=getConnection();
			String sql = "call DANG_NHAP(? , ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, usr);
	        statement.setString(2, Utility.encode(usr+ pwd));
	        
	        NguoiDung nd=null;
	        ResultSet result = statement.executeQuery();
	        
	        if (result.next()) {
	        	nd = new NguoiDung();
	            nd.setIdNguoiDung(result.getInt("ND_ID"));
	            nd.setTenDangNhap(result.getString("TEN_DANG_NHAP"));
	            nd.setHoTen(result.getString("HO_TEN"));
	            nd.setKhoa(result.getBoolean("KHOA"));
	            nd.setMatKhau(pwd);
	            
	            res.setResCode(1);
	            res.setResMessage("OK");
	            res.setResData(nd);
	        } else {
	        	res.setResCode(0);
	            res.setResMessage("User not found");
	        }
	        return res;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			res.setResCode(-1);
            res.setResMessage(e.getMessage());
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					res.setResCode(-1);
		            res.setResMessage(e.getMessage());
				}
			}
		}
		
		return res;
	}
	
	@POST
	@Path("/luu/{a}/{b}/{c}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse luuKhachHang(KhachHang kh, @PathParam("a") String usr, 
			@PathParam("b") String pwd, @PathParam("c") Integer id) {
		RestfulResponse res=new RestfulResponse();
		if(!isValidUser(usr, pwd)) {
			res.setResCode(-1);
			res.setResMessage("User not valid");
		} else {
			try {
				Connection conn=getConnection();
				
				String sql = "call LUU_KHACH_HANG(?,?,?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setInt(1, kh.getIdKhachHang());
		        statement.setString(2, kh.getMaKhachHang());
		        statement.setString(3, kh.getTenKhachHang());
		        statement.setString(4, kh.getDiaChi());
		        statement.setString(5, kh.getSoDienThoai());
		        statement.setInt(6, id);
		        statement.executeQuery();
		        conn.close();
		        
		        res.setResCode(1);
		        res.setResMessage("OK");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				res.setResCode(-1);
				res.setResMessage(e.getMessage());
			}
		}
		return res;
	}
	
	@DELETE
	@Path("/xoa/{a}/{b}/{c}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse xoaKhachHang(@PathParam("a") String usr, 
			@PathParam("b") String pwd, @PathParam("c") Integer id) {
		RestfulResponse res=new RestfulResponse();
		if(!isValidUser(usr, pwd)) {
			res.setResCode(-1);
			res.setResMessage("User not valid");
		} else {
			try {
				Connection conn=getConnection();
				
				String sql = "call XOA_KHACH_HANG(?)";
				PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setInt(1, id);
		        statement.executeQuery();
		        conn.close();
		        
		        res.setResCode(1);
		        res.setResMessage("OK");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				res.setResCode(-1);
				res.setResMessage(e.getMessage());
			}
		}
		return res;
	}
	
	@GET
	@Path("/lay-ds/{a}/{b}/{c}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse layDsKhachHang(@PathParam("a") String usr, 
			@PathParam("b") String pwd, @PathParam("c") String keyword) {
		RestfulResponse res=new RestfulResponse();
		if(!isValidUser(usr, pwd)) {
			res.setResCode(-1);
			res.setResMessage("User not valid");
		} else {
			try {
				Connection conn=getConnection();
				
				String sql = "call LAY_DS_KHACH_HANG(?)";
				PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setString(1, keyword);
		        
		        ResultSet result=statement.executeQuery();
		        List<KhachHang> dsKH=new ArrayList<>();
		        
		        while(result.next()) {
		        	KhachHang kh=new KhachHang();
		        	kh.setIdKhachHang(result.getInt("KH_ID"));
		        	kh.setMaKhachHang(result.getString("MA_KHACH_HANG"));
		        	kh.setTenKhachHang(result.getString("TEN_KHACH_HANG"));
		        	kh.setDiaChi(result.getString("DIA_CHI"));
		        	kh.setSoDienThoai(result.getString("SO_DT"));
		        	kh.setIdNguoiDung(result.getInt("ND_ID"));
		        	
		        	dsKH.add(kh);
		        }
		        
		        conn.close();
		        
		        res.setResCode(1);
		        res.setResMessage("OK");
		        res.setResData(dsKH);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				res.setResCode(-1);
				res.setResMessage(e.getMessage());
			}
		}
		return res;
	}
	
	@GET
	@Path("/lay-kh/{a}/{b}/{c}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse layKhachHang(@PathParam("a") String usr, 
			@PathParam("b") String pwd, @PathParam("c") int id) {
		RestfulResponse res=new RestfulResponse();
		Connection conn=null;
		
		if(!isValidUser(usr, pwd)) {
			res.setResCode(-1);
			res.setResMessage("User not valid");
		} else {
			try {
				conn=getConnection();
				String sql = "call LAY_KHACH_HANG(?)";
				PreparedStatement statement = conn.prepareStatement(sql);
		        statement.setInt(1, id);
		        
		        KhachHang kh=null;
		        ResultSet result = statement.executeQuery();
		        
		        if (result.next()) {
		        	kh=new KhachHang();
		        	kh.setIdKhachHang(result.getInt("KH_ID"));
		        	kh.setMaKhachHang(result.getString("MA_KHACH_HANG"));
		        	kh.setTenKhachHang(result.getString("TEN_KHACH_HANG"));
		        	kh.setDiaChi(result.getString("DIA_CHI"));
		        	kh.setSoDienThoai(result.getString("SO_DT"));
		        	kh.setIdNguoiDung(result.getInt("ND_ID"));
		            
		            res.setResCode(1);
		            res.setResMessage("OK");
		            res.setResData(kh);
		        } else {
		        	res.setResCode(0);
		            res.setResMessage("Record not found");
		        }
		        return res;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				res.setResCode(-1);
	            res.setResMessage(e.getMessage());
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						res.setResCode(-1);
			            res.setResMessage(e.getMessage());
					}
				}
			}
		}
		return res;
	}
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		String jdbcURL = "jdbc:mysql://localhost:3306/t2d_qlkh";
        String dbUser = "root";
        String dbPassword = "";
 
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	}
	
	private boolean isValidUser(String usr, String pwd) {
		try {
			Connection conn = getConnection();
			String sql = "call DANG_NHAP(? , ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, usr);
	        statement.setString(2, Utility.encode(usr+ pwd));
	        
	        ResultSet result = statement.executeQuery();
	        boolean valid= result.next();
	        conn.close();
	        return valid;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
