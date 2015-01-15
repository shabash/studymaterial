package com.jdbc.opration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.patient.model.Userfields;

public class PatientDAOImpl implements PatientDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Userfields userfields) {
		String query = "insert into Userfields (id, name,age,address,sex,dob,height,weight,mobno) values (?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,userfields.getId());
			ps.setString(2,userfields.getName());
			ps.setString(3, userfields.getAddress());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Userfields saved with id="
						+ userfields.getId());
			} else
				System.out.println("Userfields save failed with id="
						+ userfields.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Userfields getById(int id) {
		String query = "select name,age,address,sex,dob,height,weight,mobno from Userfields where id = ?";
		Userfields user= null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new Userfields();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setAddress(rs.getString("address"));
				user.setSex(rs.getString("sex"));
				user.setDob(rs.getString("dob"));
				user.setHeight(rs.getInt("height"));
				user.setWeight(rs.getInt("weight"));
				user.setMobno(rs.getString("mobno"));
				System.out.println("Userfields Found::" + user);
			} else {
				System.out.println("No Userfields found with id=" + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void update(Userfields userfields) {
		String query = "update Userfields set name=?, age=?,address=?,sex=?,dob=?,height=?,weight=?,mobno=? where id= ?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userfields.getName());
			ps.setInt(2, userfields.getAge());
			ps.setString(3, userfields.getAddress());
			ps.setString(4, userfields.getSex());
			ps.setString(5, userfields.getDob());
			ps.setInt(6, userfields.getHeight());
			ps.setInt(7, userfields.getWeight());
			ps.setString(8, userfields.getMobno());
			//ps.setInt(9, userfields.getId());
			int out = ps.executeUpdate();
			if (out!=0) {
				System.out.println("Userfields updated with id="
						+ userfields.getId());
			} else
				System.out.println("No Userfields found with id="
						+ userfields.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id) {
		String query = "delete from Userfields where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Userfields deleted with id=" + id);
			} else
				System.out.println("No Userfields found with id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Userfields> getAll() {
		String query = "select id,name,age,address,sex,dob,height,weight,mobno from Userfields";
		List<Userfields> userList = new ArrayList<Userfields>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Userfields user = new Userfields();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setAddress(rs.getString("address"));
				user.setSex(rs.getString("sex"));
				user.setDob(rs.getString("dob"));
				user.setHeight(rs.getInt("height"));
				user.setWeight(rs.getInt("weight"));
				user.setMobno(rs.getString("mobno"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	

}
