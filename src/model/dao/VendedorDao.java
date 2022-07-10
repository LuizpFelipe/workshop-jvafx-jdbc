package model.dao;

import java.util.List;

import model.entidades.Departamento;
import model.entidades.Vendedor;

public interface VendedorDao {
	void insert(Vendedor obj);
	void update(Vendedor obj);
	void deleteId(Integer id);
	Vendedor procureId(Integer id);
	List<Vendedor> procureTodos();
	List<Vendedor> procurePeloDepartamento(Departamento departamento);
}
