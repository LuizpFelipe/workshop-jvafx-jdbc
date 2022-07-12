package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Departamento;

public class DepartamentoFormControl implements Initializable {
	
	private Departamento entities;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML 
	private Label labelErroName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("!!!");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("!!2!");
	}
	
	public void setDepartamento(Departamento entities) {
		this.entities = entities;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	public void updateFormData() {
		if(entities == null) {
			throw new IllegalStateException("Entities Null");
		}
		txtId.setText(String.valueOf(entities.getId()));
		txtName.setText(entities.getNome());
	}
	
	
}
