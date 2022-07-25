package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Vendedor;
import model.exceptions.ValidationException;
import model.service.VendedorService;

public class VendedorFormControl implements Initializable {
	
	private Vendedor entities;
	
	private VendedorService service;
	
	private List<DataChengeListener> dataChengeListeners = new ArrayList<>(); 
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private DatePicker dpBirthDate;
	
	@FXML
	private  TextField txtBaseSalary;
	
	@FXML 
	private Label labelErroName;
	
	@FXML 
	private Label labelErroEmail;

	@FXML 
	private Label labelErroBirthDate;
	
	@FXML 
	private Label labelErroBaseSalary;
	
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

	private Vendedor getFormData() {
		Vendedor obj = new Vendedor();
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
	
	public void setDeparmantoService(VendedorService service) {
		this.service = service;
		
	}
	
	public void setVendedor(Vendedor entities) {
		this.entities = entities;
	}
	
	public void subscribeDataChangeListener(DataChengeListener listener) {
		dataChengeListeners.add(listener);
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 70);
		Constraints.setTextFieldMaxLength(txtEmail, 70);
		Constraints.setTextFieldDouble(txtBaseSalary);
		Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
	}
	
	public void updateFormData() {
		if(entities == null) {
			throw new IllegalStateException("Entities Null");
		}
		txtId.setText(String.valueOf(entities.getId()));
		txtName.setText(entities.getNome());
		txtEmail.setText(entities.getEmail());
		Locale.setDefault(Locale.US);
		txtBaseSalary.setText(String.format("%.2f", entities.getBaseSalario()));
		if(entities.getDataAniversario() != null) {
			dpBirthDate.setValue(LocalDate.ofInstant(entities.getDataAniversario().toInstant(), ZoneId.systemDefault()));
		}
		
	}
	
	private void setErrorMensage(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("name")) {
			labelErroName.setText(errors.get("name"));
		}
	}
	
	
}
