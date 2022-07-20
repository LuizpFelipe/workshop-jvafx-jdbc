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
	
	public void saveOrUpdate(Departamento obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
		
	}
	
	public void remove(Departamento obj) {
		dao.deleteId(obj.getId());
	}
}
