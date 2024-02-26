package fr.dtn.mlauncher.ui.panel;

import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager manager);
    GridPane getLayout();
    void onShow();
    String getName();
}