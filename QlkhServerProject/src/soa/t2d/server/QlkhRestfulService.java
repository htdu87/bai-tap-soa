import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import soa.t2d.base.RestfulResponse;
import soa.t2d.base.Utility;

import soa.t2d.base.model.NguoiDung;

@Path("/qlkh")
public class QlkhRestfulService {
	
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		String jdbcURL = "jdbc:mysql://localhost:3306/t2d_qlkh";
        String dbUser = "root";
        String dbPassword = "";
 
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	}

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
	@Path("/addcustomer/{a}/{b}/{c}/{d}/{e}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse addCustomer(@PathParam ("a") String maKhachHang,
							  @PathParam ("b") String tenKhachHang,
							  @PathParam ("c") String diaChi,
							  @PathParam ("d") String soDienThoai,
							  @PathParam ("e") String idNguoiDung) {
		
		Connection conn=null;
		RestfulResponse res=new RestfulResponse();
		try {
			conn=getConnection();
			String sql = "call ADD_CUSTOMER(? , ? , ? , ? )";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, maKhachHang);
			statement.setString(1, maKhachHang);
			statement.setString(3, diaChi);
			statement.setString(4, soDienThoai);
			statement.setInt(5, Integer.parseInt(idNguoiDung));
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				res.setResCode(1);
	            res.setResMessage(result.getString("KETQUA"));
			}else {
				res.setResCode(0);
	            res.setResMessage("User not found");
			}
		}catch (ClassNotFoundException | SQLException  e) {
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
	
	@PUT
	@Path("/updatecustomer/{a}/{b}/{c}/{d}/{e}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse updateCustomer(@PathParam ("a") String maKhachHang,
							  @PathParam ("b") String tenKhachHang,
							  @PathParam ("c") String diaChi,
							  @PathParam ("d") String soDienThoai,
							  @PathParam ("e") String idNguoiDung) {
		
		Connection conn=null;
		RestfulResponse res=new RestfulResponse();
		try {
			conn=getConnection();
			String sql = "call UPDATE_CUSTOMER(? , ? , ? , ? )";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, maKhachHang);
			statement.setString(2, tenKhachHang);
			statement.setString(3, diaChi);
			statement.setString(4, soDienThoai);
			statement.setInt(5, Integer.parseInt(idNguoiDung));
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				res.setResCode(1);
	            res.setResMessage(result.getString("KETQUA"));
			}else {
				res.setResCode(0);
	            res.setResMessage("User not found");
			}
		}catch (ClassNotFoundException | SQLException  e) {
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
				
	@DELETE
	@Path("/deletecustomer/{a}/{b}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestfulResponse deleteCustomer(@PathParam ("a") String maKhachHang,							  
							  @PathParam ("e") String idNguoiDung) {
		
		Connection conn=null;
		RestfulResponse res=new RestfulResponse();
		try {
			conn=getConnection();
			String sql = "call DEL_CUSTOMER(? , ? )";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, maKhachHang);			
			statement.setInt(2, Integer.parseInt(idNguoiDung));
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				res.setResCode(1);
	            res.setResMessage(result.getString("KETQUA"));
			}else {
				res.setResCode(0);
	            res.setResMessage("User not found");
			}
		}catch (ClassNotFoundException | SQLException  e) {
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
	
	@GET
	@Path("/findCustomer/{a}/{b}")
	@Produces(MediaType.APPLICATION_JSON)
	public List findCustomer(@PathParam ("a") String tenKhachHang,							  
							  @PathParam ("e") String idNguoiDung) {
		Connection conn=null;
		try {
			conn=getConnection();
			String sql = "call find_CUSTOMER(? , ? )";
			//List<Map<String, Object>> listCustomer =
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tenKhachHang);			
			statement.setInt(2, Integer.parseInt(idNguoiDung));
			
			List<Map<String, Object>> listCustomer= (List<Map<String, Object>>) statement.executeQuery();		
			return listCustomer;
		}catch (Exception e) {
			return null;
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();					
				}
			}
		}
		 
	} 
	
}
