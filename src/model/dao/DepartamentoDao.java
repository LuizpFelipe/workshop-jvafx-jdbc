package model.dao;

import java.util.List;

import model.entidades.Departamento;

public interface DepartamentoDao{
	void insert(Departamento obj);
	void update(Departamento obj);
	void deleteId(Integer id);
	Departamento procureId(Integer id);
	List<Departamento> procureTodos();
}
