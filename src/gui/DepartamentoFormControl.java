package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Action;

import db.DbException;
import gui.util.Alertas;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Departamento;
import model.service.DepartamentoService;

public class DepartamentoFormControl implements Initializable {
	
	private Departamento entities;
	
	private DepartamentoService service;
	
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
	public void onBtSaveAction(ActionEvent event) {
		if(entities == null) {
			throw new IllegalStateException("Entities Null");
		}
		if(service == null) {
			throw new IllegalStateException("Service Null");
		}
		try {
			entities = getFormData();
			service.saveOrUpdate(entities);
			Utils.currentStage(event).close();
		}catch(DbException e){
			Alertas.showAlert("Erro ao Salvar o Objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Departamento getFormData() {
		Departamento obj = new Departamento();
		obj.setId(Utils.tryParseToInt(txtName.getText()));
		obj.setNome(txtName.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	public void setDeparmantoService(DepartamentoService service) {
		this.service = service;
		
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
