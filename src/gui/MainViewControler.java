package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainViewControler implements Initializable {
	
	
	@FXML
	private MenuItem menuItemVendedor;
	
	@FXML
	private MenuItem menuItemDepartamento;
	
	@FXML
	private MenuItem menuItemAjuda;
	
	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("Vendedor");
	}
	
	@FXML
	public void onMenuItemDepartamentoAction() {
		System.out.println("Departamento");
	}
	
	@FXML
	public void onMenuItemAjudaAction() {
		System.out.println("Ajuda");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}

}
