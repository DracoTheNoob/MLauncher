package fr.dtn.mlauncher.ui.panel;

import fr.dtn.mlauncher.Launcher;
import fr.flowarg.flowlogger.ILogger;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public abstract class Panel implements IPanel, IMovable, ITakePlace {
    protected final Launcher launcher;
    protected final ILogger logger;
    protected GridPane layout = new GridPane();
    protected PanelManager manager;

    public Panel(Launcher launcher) {
        this.launcher = launcher;
        this.logger = launcher.getLogger();
    }

    @Override public void init(PanelManager manager) {
        this.manager = manager;
        setCanTakeAllSize(this.layout);
    }

    @Override public GridPane getLayout() { return layout; }

    @Override public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override public abstract String getName();

    @Override public void setAlignment(Node node, Alignment alignment) {
        switch(alignment) {
            case RIGHT:
                GridPane.setHalignment(node, HPos.RIGHT);
                break;
            case LEFT:
                GridPane.setHalignment(node, HPos.LEFT);
                break;
            case TOP:
                GridPane.setValignment(node, VPos.TOP);
                break;
            case BOTTOM:
                GridPane.setValignment(node, VPos.BOTTOM);
                break;
            case BASELINE:
                GridPane.setValignment(node, VPos.BASELINE);
                break;
            case HORIZONTAL_CENTER:
                GridPane.setHalignment(node, HPos.CENTER);
                break;
            case VERTICAL_CENTER:
                GridPane.setValignment(node, VPos.CENTER);
                break;
        }
    }
}