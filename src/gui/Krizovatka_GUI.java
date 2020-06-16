/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import casovani.Casovac;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import krizovatka.IKrizovatka;
import krizovatka.KrizovatkaSimulator;

public class Krizovatka_GUI extends Application {

    private TextField tf1;
    private TextField tf2;
    private TextField tf3;
    private TextField tf4;
    private TextField tf5;
    private TextField tf6;
    private TextField tf7;

    TextField field1;
    TextField field2;
    TextField field3;
    TextField field4;
    TextField field5;
    private IKrizovatka krizovatka;
    private ListView<String> list;

    private final int maxItemesInListView = 18;
    private static int i = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        krizovatka = new KrizovatkaSimulator();
        list = new ListView<>();
        list.getItems().addListener((Observable observable) -> {
            field1.setText(Long.toString(krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.SEVER)));
            field2.setText(String.valueOf(krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.VYCHOD)));
            field3.setText(String.valueOf(krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.JIH)));
            field4.setText(String.valueOf(krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.ZAPAD)));
            krizovatka.setHlaseniSemaforu(smerPrujezdu -> field5.setText(smerPrujezdu.toString()));
            if (list.getItems().size() > maxItemesInListView) {
                list.getItems().remove(0);

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
        });

        BorderPane root = new BorderPane();

        root.setMaxWidth(300);
        root.setRight(createAnchorPane());
        root.setLeft(createListView());
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Node createAnchorPane() {
        FlowPane flow = new FlowPane();

        flow.setPadding(new Insets(5, 10, 0, 10));
        flow.setVgap(10);
        flow.setHgap(15);
        flow.setPrefWrapLength(250);
        field1 = new TextField(String.valueOf((krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.SEVER))));
        field2 = new TextField(String.valueOf((krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.VYCHOD))));
        field3 = new TextField(String.valueOf((krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.JIH))));
        field4 = new TextField(String.valueOf((krizovatka.getPocetCekajicichZeSmeru(IKrizovatka.Smer.ZAPAD))));
        field5 = new TextField();

        Label label = new Label("Auta z severu");
        label.setPadding(new Insets(0, 9, 0, 0));
        Label labe2 = new Label("Auta z vychodu");
        Label labe3 = new Label("Auta z jihu");
        labe3.setPadding(new Insets(0, 25, 0, 0));
        Label labe4 = new Label("Auta z zapadu");
        labe4.setPadding(new Insets(0, 4, 0, 0));
        Label labe5 = new Label("Semafor");
        labe5.setPadding(new Insets(0, 36, 0, 0));
        Button config = new Button("Config");
        config.setOnAction((e) -> config());
        Button start = new Button("Start");
        start.setOnAction((e) -> zpustit());
        Button stop = new Button("Stop");
        stop.setOnAction((e) -> zastavit());
        Button zrus = new Button("Zrus vypis");
        zrus.setOnAction((e) -> zrusit());
        flow.getChildren().addAll(label, field1, labe2, field2, labe3, field3, labe4, field4, labe5, field5, config, start, stop, zrus);
        return flow;
    }

    private Node createListView() {
        list.setLayoutX(10);
        list.setLayoutY(10);
        list.setPrefWidth(400);
        return list;

    }

    private void config() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Parametry krizovatky");
        alert.setTitle("Configurace");
        BorderPane bp = new BorderPane();
        bp.setPrefSize(500, 300);
        bp.setCenter(createVBox());

        alert.getDialogPane().setContent(bp);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Integer.decode(tf1.getText());
            Integer.decode(tf2.getText());
            Integer.decode(tf3.getText());
            Integer.decode(tf4.getText());
            Integer.decode(tf5.getText());
            Integer.decode(tf6.getText());
            Integer.decode(tf7.getText());

            krizovatka.setCetnostPrijezdu(IKrizovatka.Smer.SEVER, Long.parseLong(tf1.getText()));
            krizovatka.setCetnostPrijezdu(IKrizovatka.Smer.VYCHOD, Long.parseLong(tf2.getText()));
            krizovatka.setCetnostPrijezdu(IKrizovatka.Smer.JIH, Long.parseLong(tf3.getText()));
            krizovatka.setCetnostPrijezdu(IKrizovatka.Smer.ZAPAD, Long.parseLong(tf4.getText()));
            krizovatka.setSemaforDobaZelena(IKrizovatka.SmerPrujezdu.SEVER_JIH, Long.parseLong(tf5.getText()));
            krizovatka.setSemaforDobaZelena(IKrizovatka.SmerPrujezdu.VYCHOD_ZAPAD, Long.parseLong(tf6.getText()));
            krizovatka.setDobaPrujezdu(Long.parseLong(tf7.getText()));
        }
    }

    private void zpustit() {
        Casovac.instance().start();

        krizovatka.setHlaseniSemaforu(
                s -> list.getItems().add(String.format(
                        "Zmena smeru na %s\n", i, s)));

        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> list.getItems().add(String.format(
                        "T= %04d: Prijezd ze severu %s\n", i, s)));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.SEVER,
                s -> list.getItems().add(String.format(
                        "T= %04d: Odjezd ze severu %s\n", i, s)));

        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> list.getItems().add(String.format(
                        "T= %04d: Prijezd z jihu %s\n", i, s)));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.JIH,
                s -> list.getItems().add(String.format(
                        "T= %04d: Odjezd z jihu %s\n", i, s)));

        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> list.getItems().add(String.format(
                        "T= %04d: Prijezd z vychodu %s\n", i, s)));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.VYCHOD,
                s -> list.getItems().add(String.format(
                        "T= %04d: Odjezd z vychodu %s\n", i, s)));

        krizovatka.setHlaseniPrijezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> list.getItems().add(String.format(
                        "T= %04d: Prijezd ze zapadu %s\n", i, s)));
        krizovatka.setHlaseniOdjezduZeSmeru(
                IKrizovatka.Smer.ZAPAD,
                s -> list.getItems().add(String.format(
                        "T= %04d: Odjezd ze zapadu %s\n", i, s)));
        i++;

    }

    private void zastavit() {
        Casovac.instance().stop();
    }

    private void zrusit() {
        list.getItems().clear();
    }

    private Node createVBox() {
        VBox vb = new VBox();
        vb.setSpacing(10);

        Label lb0 = new Label("aut/hod");
        Label lb1 = new Label("ms");
        Label lbl1 = new Label("aut/hod");
        Label lbm1 = new Label("ms");
        Label lbl2 = new Label("aut/hod");
        Label lbm2 = new Label("ms");
        Label lbl3 = new Label("aut/hod");
        Label lb2 = new Label("Cetnost ze severu(A1)");

        tf1 = new TextField(String.valueOf((krizovatka.getCetnostPrijezdu(
                IKrizovatka.Smer.SEVER))));
        tf2 = new TextField(String.valueOf((krizovatka.getCetnostPrijezdu(
                IKrizovatka.Smer.VYCHOD))));
        tf3 = new TextField(String.valueOf((krizovatka.getCetnostPrijezdu(
                IKrizovatka.Smer.JIH))));
        tf4 = new TextField(String.valueOf((krizovatka.getCetnostPrijezdu(
                IKrizovatka.Smer.ZAPAD))));
        tf5 = new TextField(String.valueOf((krizovatka.getSemaforDobaZelena(IKrizovatka.SmerPrujezdu.SEVER_JIH))));
        tf6 = new TextField(String.valueOf((krizovatka.getSemaforDobaZelena(IKrizovatka.SmerPrujezdu.VYCHOD_ZAPAD))));
        tf7 = new TextField(String.valueOf((krizovatka.getDobaPrujezdu())));

        Label lb3 = new Label("Cetnost ze vychodu(A2)");
        Label lb4 = new Label("Cetnost ze jihu(A3)");
        Label lb5 = new Label("Cetnost ze zapadu(A4)");
        Label lb6 = new Label("Doba prujezdu sever-jih(K1)");
        Label lb7 = new Label("Doba prujezdu vychod-zapad(K2)");
        Label lb8 = new Label("Doba odjezdu auta(S1)");

        HBox hb1 = new HBox();
        hb1.setSpacing(20);
        hb1.getChildren().addAll(lb2, tf1, lb0);
        lb2.setPadding(new Insets(0, 61, 0, 0));

        HBox hb2 = new HBox();
        hb2.setSpacing(20);
        hb2.getChildren().addAll(lb3, tf2, lbl1);
        lb3.setPadding(new Insets(0, 50, 0, 0));

        HBox hb3 = new HBox();
        hb3.setSpacing(20);
        hb3.getChildren().addAll(lb4, tf3, lbl2);
        lb4.setPadding(new Insets(0, 74, 0, 0));

        HBox hb4 = new HBox();
        hb4.setSpacing(20);
        hb4.getChildren().addAll(lb5, tf4, lbl3);
        lb5.setPadding(new Insets(0, 55, 0, 0));

        HBox hb5 = new HBox();
        hb5.setSpacing(20);
        hb5.getChildren().addAll(lb6, tf5, lb1);
        lb6.setPadding(new Insets(0, 28, 0, 0));

        HBox hb6 = new HBox();
        hb6.setSpacing(20);
        hb6.getChildren().addAll(lb7, tf6, lbm1);

        HBox hb7 = new HBox();
        hb7.setSpacing(20);
        hb7.getChildren().addAll(lb8, tf7, lbm2);
        lb8.setPadding(new Insets(0, 56, 0, 0));

        vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        return vb;
    }
}
