package fr.dtn.mlauncher.ui.panel;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import fr.dtn.mlauncher.Launcher;
import fr.dtn.mlauncher.ui.panels.partials.TopBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {
    private final Launcher launcher;
    private final Stage stage;
    private GridPane layout;
    private TopBar topBar;
    private GridPane content = new GridPane();

    public PanelManager(Launcher launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
    }

    public void init(){
        this.stage.setTitle(launcher.getConstants().getWindowTitle());
        this.stage.setMinWidth(800);
        this.stage.setMinHeight(500);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.getIcons().add(new Image("images/icon.png"));

        this.layout = new GridPane();

        BorderlessScene scene = new BorderlessScene(this.stage, StageStyle.UNDECORATED, this.layout);
        scene.setResizable(true);
        scene.removeDefaultCSS();

        this.stage.setScene(scene);
        this.stage.show();

        this.topBar = new TopBar(launcher);
        this.content = new GridPane();

        RowConstraints topConstraints = topBar.generateConstraints();
        this.layout.getRowConstraints().addAll(topConstraints, new RowConstraints());
        this.layout.add(this.topBar.getLayout(), 0, 0);
        this.topBar.init(this);

        scene.setMoveControl(this.topBar.getLayout());
    }

    public void showPanel(IPanel panel) {
        this.content.getChildren().clear();
        this.content.getChildren().add(panel.getLayout());

        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() { return stage; }
}