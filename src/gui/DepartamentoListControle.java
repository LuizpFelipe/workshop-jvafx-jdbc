package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entidades.Departamento;
import model.service.DepartamentoService;

public class DepartamentoListControle implements Initializable{
	
	private DepartamentoService service;
	
	@FXML
	private TableView<Departamento> tableViewDepartamento;
	
	@FXML
	private TableColumn<Departamento, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Departamento, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	private ObservableList<Departamento> obsList;
	
	@FXML
	public void onButtonNew() {
		System.out.println("Departamento!!");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNode();
	}
	
	public void setDepartamentoService(DepartamentoService service) {
		this.service = service;
	}
	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
	}
	 
	public void uptadeTableView() {
		if(service == null ) {
			throw new IllegalStateException("service está null.");
		}
		
		List<Departamento> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartamento.setItems(obsList);
		
	}

}
