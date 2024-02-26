package fr.dtn.mlauncher.ui.panels.partials;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.dtn.mlauncher.Launcher;
import fr.dtn.mlauncher.ui.panel.Alignment;
import fr.dtn.mlauncher.ui.panel.Panel;
import fr.dtn.mlauncher.ui.panel.PanelManager;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.swing.*;

public class TopBar extends Panel {
    private GridPane topBar;

    public TopBar(Launcher launcher) {
        super(launcher);
    }

    @Override
    public void init(PanelManager manager) {
        super.init(manager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: #232828;");

        // Create top bar icon
        ImageView icon = new ImageView();
        icon.setImage(new Image("images/icon.png"));
        icon.setPreserveRatio(true);
        icon.setFitHeight(25);
        setAlignment(icon, Alignment.LEFT);

        // Create top bar center title
        Label title = new Label(launcher.getConstants().getWindowTitle());
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 18f));
        title.setStyle("-fx-text-fill: white;");
        setAlignment(title, Alignment.HORIZONTAL_CENTER);

        // Control buttons of the top bar
        GridPane buttons = new GridPane();
        buttons.setMinWidth(100d);
        buttons.setMaxWidth(100d);
        setCanTakeAllSize(buttons);
        setAlignment(buttons, Alignment.RIGHT);

        FontAwesomeIconView closeButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_CLOSE);
        closeButton = setupButton(closeButton, 70);
        closeButton.setOnMouseClicked(e -> {
            if(launcher.prepareExit()) {
                System.exit(0);
            }else {
                JOptionPane.showMessageDialog(
                        null,
                        "Impossible de fermer le launcher pour le moment.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        FontAwesomeIconView maximizeButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MAXIMIZE);
        maximizeButton = setupButton(maximizeButton, 50);
        maximizeButton.setOnMouseClicked(e -> manager.getStage().setMaximized(!manager.getStage().isMaximized()));

        FontAwesomeIconView minimizeButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MINIMIZE);
        minimizeButton = setupButton(minimizeButton, 26);
        minimizeButton.setOnMouseClicked(e -> manager.getStage().setIconified(true));

        setCanTakeAllWidth(closeButton, maximizeButton, minimizeButton);
        buttons.getChildren().addAll(closeButton, maximizeButton, minimizeButton);

        // Fill the top bar with elements created before
        this.layout.getChildren().add(icon);
        this.layout.getChildren().add(title);
        this.layout.getChildren().add(buttons);
    }

    public FontAwesomeIconView setupButton(FontAwesomeIconView icon, double translate) {
        icon.setFill(Color.WHITE);
        icon.setOpacity(.7);
        icon.setSize("14px");
        icon.setOnMouseEntered(e -> icon.setOpacity(1));
        icon.setOnMouseExited(e -> icon.setOpacity(.7));
        icon.setTranslateX(translate);

        return icon;
    }

    public RowConstraints generateConstraints() {
        RowConstraints constraints = new RowConstraints();
        constraints.setValignment(VPos.TOP);
        constraints.setMinHeight(25);
        constraints.setMaxHeight(25);
        return constraints;
    }

    @Override public String getName() { return "TopBar"; }
}