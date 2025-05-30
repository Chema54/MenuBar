package com.example.menubar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {

    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("Archivo seleccionado:");

        MenuBar menuBar = new MenuBar();

        Menu configuracion = new Menu("Configuración");
        MenuItem archivo = new MenuItem("Cargar archivo MP3");

        archivo.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo MP3");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Archivos MP3", "*.mp3")
            );

            File archivoSeleccionado = fileChooser.showOpenDialog(stage);

            if (archivoSeleccionado != null) {
                titulo.setText("Archivo seleccionado: " + archivoSeleccionado.getName());

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                Media media = new Media(archivoSeleccionado.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        });

        configuracion.getItems().addAll(archivo);

        Menu audioConfig = new Menu("Audio Config");

        MenuItem silenciar = new MenuItem("Silenciar / Activar sonido");
        MenuItem pausar = new MenuItem("Pausar");
        MenuItem reanudar = new MenuItem("Reanudar");

        silenciar.setOnAction(e -> {
            if (mediaPlayer != null) {
                boolean silenciado = mediaPlayer.isMute();
                mediaPlayer.setMute(!silenciado);
                System.out.println(silenciado ? "Audio activado" : "Audio silenciado");
            } else {
                System.out.println("No hay audio cargado.");
            }
        });

        pausar.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                System.out.println("Audio pausado.");
            }
        });

        reanudar.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.play();
                System.out.println("Audio reanudado.");
            }
        });

        audioConfig.getItems().addAll(silenciar, pausar, reanudar);

        menuBar.getMenus().addAll(configuracion, audioConfig);

        BorderPane raiz = new BorderPane();
        raiz.setTop(menuBar);
        raiz.setCenter(titulo);

        Scene scene = new Scene(raiz, 400, 300);
        stage.setTitle("Configuración de Audio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

