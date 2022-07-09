package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.service.DepartamentoService;

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
		loadView2("/gui/DepartamentoList.fxml");
	}
	
	@FXML
	public void onMenuItemAjudaAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox);
			
		}catch(IOException e) {
			Alertas.showAlert("IOException", "Erro no Loading View", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox);
			
			DepartamentoListControle controle = loader.getController();
			controle.setDepartamentoService(new DepartamentoService());
			controle.uptadeTableView();
			
		}catch(IOException e) {
			Alertas.showAlert("IOException", "Erro no Loading View", e.getMessage(), AlertType.ERROR);
		}
	}

}
