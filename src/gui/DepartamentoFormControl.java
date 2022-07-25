package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.util.Alertas;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Departamento;
import model.exceptions.ValidationException;
import model.service.DepartamentoService;

public class DepartamentoFormControl implements Initializable {
	
	private Departamento entities;
	
	private DepartamentoService service;
	
	private List<DataChengeListener> dataChengeListeners = new ArrayList<>(); 
	
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
			notifyDataChangeListener();
			Utils.currentStage(event).close();
		}catch(DbException e){
			Alertas.showAlert("Erro ao Salvar o Objeto", null, e.getMessage(), AlertType.ERROR);
		}catch(ValidationException e) {
			setErrorMensage(e.getErros());
		}
	}
	
	private void notifyDataChangeListener() {
		for(DataChengeListener listener : dataChengeListeners) {
			listener.onDatachanged();
		}
	}

	private Departamento getFormData() {
		Departamento obj = new Departamento();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		ValidationException exception = new ValidationException("Validation Error");
		if(txtName.getText() == null || txtName.getText().trim().equals(""));{
			exception.addError("name", "Field não pode estar Vazio");
		}	
		obj.setNome(txtName.getText());
		
		if(exception.getErros().size() == 0) {
			throw exception;
		}
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
	
	public void subscribeDataChangeListener(DataChengeListener listener) {
		dataChengeListeners.add(listener);
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 70);
	}
	
	public void updateFormData() {
		if(entities == null) {
			throw new IllegalStateException("Entities Null");
		}
		txtId.setText(String.valueOf(entities.getId()));
		txtName.setText(entities.getNome());
	}
	
	private void setErrorMensage(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("name")) {
			labelErroName.setText(errors.get("name"));
		}
	}
	
	
}
