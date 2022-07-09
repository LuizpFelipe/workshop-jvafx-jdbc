package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entities.Departamento;

public class DepartamentoService {
	public List<Departamento> findAll(){
		List<Departamento> list = new ArrayList<>();
		list.add(new Departamento(1,"Books"));
		list.add(new Departamento(1,"Cars"));
		list.add(new Departamento(1,"Foods"));
		list.add(new Departamento(1,"Sports"));
		return list;
		
	}
}
