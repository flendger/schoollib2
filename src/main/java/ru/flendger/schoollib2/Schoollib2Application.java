package ru.flendger.schoollib2;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.flendger.schoollib2.gui.ClientFxApp;

@SpringBootApplication
public class Schoollib2Application {

	public static void main(String[] args) {
		Application.launch(ClientFxApp.class, args);
	}

}
