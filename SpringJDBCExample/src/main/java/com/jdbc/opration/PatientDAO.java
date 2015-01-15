package com.jdbc.opration;

import java.util.List;

import com.patient.model.Userfields;

			//CRUD operations//
public interface PatientDAO {
	
	//Create
	public void save(Userfields userfields);
	//Read
	public Userfields getById(int id);
	//Update
	public void update(Userfields userfields);
	//Delete
	public void deleteById(int id);
	//Get All
	public List<Userfields> getAll();
}
