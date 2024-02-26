package fr.dtn.mlauncher;

import fr.dtn.mlauncher.ui.panel.PanelManager;
import fr.dtn.mlauncher.utils.Constants;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Launcher extends Application {
    private PanelManager panelManager;
    private final Constants constants;
    private final ILogger logger;

    public Launcher(){
        this.constants = new Constants();
        this.logger = new Logger("[ML-FX]", new File(constants.getGamePath(), "launcher.log"));
    }

    @Override public void start(Stage stage) throws Exception {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
    }

    /**
     * A method to check if the launcher is ready to be closed or not to prevent closing it while doing important tasks
     * @return True if the launcher is ready to be closed
     */
    public boolean prepareExit(){
        return true;
    }

    public Constants getConstants() { return constants; }
    public ILogger getLogger() { return logger; }
}