package com.jdbc.opration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.patient.model.Userfields;

public class PatientDAOJDBCTemplate implements PatientDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Userfields userfields) {
		String query = "insert into Userfields (id, name,age,address,sex,dob,height,weight,mobno) values (?,?,?,?,?,?,?,?,?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {userfields.getId(), userfields.getName(), userfields.getAge(),userfields.getAddress(),userfields.getSex(),userfields.getDob(),userfields.getHeight(),userfields.getWeight(),userfields.getMobno()};
		
		int out = jdbcTemplate.update(query, args);
		
		if(out !=0){
			System.out.println("Insert Successfully."+"  "+"Plz........Check.....DataBase");
		}else System.out.println("No");
	}

	public Userfields getById(int id) {
		String query = "select id,name,age,address,sex,dob,height,weight,mobno from Userfields where id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		Userfields user = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Userfields>(){

			public Userfields mapRow(ResultSet rs, int rowNum)
					throws SQLException {
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
				return user;
			}});
		
		return user;
	}

	public void update(Userfields userfields) {
		String query = "update Userfields set name=?, age=?, address=?, sex=?, dob=?, height=?, weight=?, mobno=? where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {userfields.getId(), userfields.getName(),
				userfields.getAge(), userfields.getAddress(),
				userfields.getSex(), userfields.getDob(),
				userfields.getHeight(), userfields.getWeight(),
				userfields.getMobno() };

		int out = jdbcTemplate.update(query, args);
		if(out!=0){
			System.out.println("Userfields  updated with id="+userfields.getId());
			
		}else System.out.println("No Userfields found with id="+userfields.getId());
	}

	public void deleteById(int id) {

		String query = "delete from Userfields where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		if(out !=0){
			System.out.println("Deleted Successfully."+"Deleted id="+id+" " +"Plz........Check.....DataBase");
		}else System.out.println("No Userfields found with id="+id);
	}

	public List<Userfields> getAll() {
		String query = "select id, name,age,address,sex,dob,height,weight,mobno from Userfields";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Userfields> userList = new ArrayList<Userfields>();

		List<Map<String,Object>> userRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> userRow : userRows){
			Userfields user = new Userfields();
			user.setId(Integer.parseInt(String.valueOf(userRow.get("id"))));
			user.setName(String.valueOf(userRow.get("name")));
			user.setAge(Integer.parseInt(String.valueOf(userRow.get("age"))));
			user.setAddress(String.valueOf(userRow.get("address")));
			user.setSex(String.valueOf(userRow.get("sex")));
			user.setDob(String.valueOf(userRow.get("dob")));
			user.setHeight(Integer.parseInt(String.valueOf(userRow.get("height"))));
			user.setWeight(Integer.parseInt(String.valueOf(userRow.get("weight"))));
			user.setMobno(String.valueOf(userRow.get("mobno")));
			userList.add(user);
		}
		return userList;
	}

	

}
