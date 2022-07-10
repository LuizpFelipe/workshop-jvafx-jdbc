package model.service;

import java.util.List;

import model.dao.DepartamentoDao;
import model.dao.FactorDao;
import model.entidades.Departamento;

public class DepartamentoService {
	
	private DepartamentoDao dao = FactorDao.criadorDepartamentoDAO();
	
	public List<Departamento> findAll(){
		return dao.procureTodos();
		
	}
}
