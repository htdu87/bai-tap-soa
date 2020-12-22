package soa.t2d.base.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import soa.t2d.base.Utility;

public class NguoiDung {
	private int idNguoiDung;
	private String tenDangNhap;
	private String matKhau;
	private String hoTen;
	private boolean khoa;
	
	
	public NguoiDung() {
		
	}

	public NguoiDung(int idNguoiDung, String tenDangNhap, String matKhau, String hoTen, boolean khoa) {
		this.idNguoiDung = idNguoiDung;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.khoa = khoa;
	}

	public int getIdNguoiDung() {
		return idNguoiDung;
	}

	public void setIdNguoiDung(int idNguoiDung) {
		this.idNguoiDung = idNguoiDung;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isKhoa() {
		return khoa;
	}

	public void setKhoa(boolean khoa) {
		this.khoa = khoa;
	}

	public void testFunc() {
		System.out.println("Hello, i'm working!!!");
		
		try {
			System.out.println(Utility.encode("duht123a@"));
			System.out.println(Utility.encode("thaonv123a@"));
			System.out.println(Utility.encode("ducvp123a@"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
