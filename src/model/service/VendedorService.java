package model.service;

import java.util.List;

import model.dao.VendedorDao;
import model.dao.FactorDao;
import model.entidades.Vendedor;

public class VendedorService {
	
	private VendedorDao dao = FactorDao.criadorVendedorDAO();
	
	public List<Vendedor> findAll(){
		return dao.procureTodos();
		
	}
	
	public void saveOrUpdate(Vendedor obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}else {
			dao.update(obj);
		}
		
	}
	
	public void remove(Vendedor obj) {
		dao.deleteId(obj.getId());
	}
}
