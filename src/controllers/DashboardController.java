/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.*;

import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.fx.interaction.ChartMouseEventFX;
import org.jfree.chart.fx.interaction.ChartMouseListenerFX;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


import javax.swing.*;
import models.Event;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class DashboardController implements Initializable {

    @FXML
    private StackPane dashPane;

    /**
     * Initializes the controller class.
     */
     EventServices es= new EventServices();
    public DefaultPieDataset createDataset(List<Event> events) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> governmentCounts = new HashMap<>();

        for (Event event : events) {
            String government = event.getAdresseEvent();
            int count = governmentCounts.getOrDefault(government, 0) + 1;
            governmentCounts.put(government, count);
        }

        for (Map.Entry<String, Integer> entry : governmentCounts.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        return dataset;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SwingUtilities.invokeLater(() -> {
            Platform.runLater(() -> {
                DefaultPieDataset dataset = null;
                dataset = createDataset(es.afficherEvent());
                JFreeChart chart = ChartFactory.createPieChart(
                        "Evenement par ville",
                        dataset,
                        true,
                        true,
                        false
                );
                ChartViewer chartViewer = new ChartViewer(chart);

                dashPane.getChildren().add(chartViewer);

            });
        });
    }    
    
}
