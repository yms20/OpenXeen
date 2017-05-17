package Game;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by duckman on 18/06/2016.
 */
public enum GlobalSettings
{
    INSTANCE;

    boolean debugMode = true;
    boolean rebuildProxies = false;
    private boolean disableHUD = false;
    private boolean discoverFileNames = false;
    private volatile ResourceBundle localText = null;

    public boolean debugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * True if ccFiles should save proxie files when loaded.
     */
    public boolean rebuildProxies() {
        return rebuildProxies;
    }
    public void setRebuildProxies(boolean rebuildProxies) {
        this.rebuildProxies = rebuildProxies;
    }

    public void setDisableHUD(boolean disableHUD) {
        this.disableHUD = disableHUD;
    }

    public boolean isHUDDisabled() {
        return disableHUD;
    }

    public boolean discoverFileNames() {
        return discoverFileNames;
    }

    public ResourceBundle getLocalText() {
        if(localText == null) {
            localText = ResourceBundle.getBundle("openXeen", Locale.ENGLISH);
        }
        return localText;
    }
}