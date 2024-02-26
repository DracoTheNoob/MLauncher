package fr.dtn.mlauncher.utils;

import fr.flowarg.flowcompat.Platform;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
    private static final String folderName = ".mlauncher";
    private static final String windowTitle = "MLauncher - v" + Constants.class.getPackage().getImplementationVersion();
    private static final File gamePath;

    static {
        // Set game path folder depending on the user OS

        Path path;
        switch(Platform.getCurrentPlatform()) {
            case WINDOWS:
                path = Paths.get(System.getenv("APPDATA"));
                break;
            case MAC:
                path = Paths.get(System.getProperty("user.home"), "/Library/Application Support/");
                break;
            case LINUX:
                path = Paths.get(System.getProperty("user.home"), ".local/share");
                break;
            default:
                path = Paths.get(System.getProperty("user.home"));
        }

        gamePath = Paths.get(path.toString(), folderName).toFile();
    }

    public File getGamePath() {
        if(!gamePath.exists() && !gamePath.mkdirs()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Le dossier '" + gamePath + "' nécessaire au fonctionnement du launcher n'existe pas est ne peut pas être créé.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return gamePath;
    }

    public String getWindowTitle() { return windowTitle; }
}