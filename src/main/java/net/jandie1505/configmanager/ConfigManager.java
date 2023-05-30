package net.jandie1505.configmanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class ConfigManager {
    private final File configFile;
    private final boolean overrideDefaults;
    private JSONObject config;

    public ConfigManager(JSONObject defaultConfig, boolean overrideDefaults, File dataFolder, String filename) {
        this.configFile = new File(dataFolder, filename);
        this.config = new JSONObject(defaultConfig.toString());
        this.overrideDefaults = overrideDefaults;
    }

    private JSONObject loadConfig() throws IOException, JSONException {
        BufferedReader br = new BufferedReader(new FileReader(this.configFile));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return new JSONObject(sb.toString());
    }

    private void writeConfig() throws IOException {
        FileWriter writer = new FileWriter(this.configFile);
        writer.write(this.config.toString(4));
        writer.flush();
        writer.close();
    }

    /**
     * Reload the config from file.
     */
    public void reloadConfig() {
        try {
            if (!this.configFile.exists()) {
                this.configFile.getParentFile().mkdirs();
                this.configFile.createNewFile();
                this.writeConfig();
            }

            JSONObject loadedConfig = this.loadConfig();

            if (this.overrideDefaults) {

                this.config = new JSONObject(loadedConfig.toString());

            } else {

                if (loadedConfig.optBoolean("recreateConfig", false)) {
                    this.writeConfig();
                } else {
                    for (String key : loadedConfig.keySet()) {
                        this.config.put(key, loadedConfig.get(key));
                    }
                }

            }
        } catch (IOException | JSONException e) {
            System.err.println("Error loading config, using defaults");
        }
    }

    /**
     * Get a copy of the config.
     * @return copy of config (JSONObject)
     */
    public JSONObject getConfig() {
        return new JSONObject(this.config.toString());
    }

    /**
     * Get the "real" config object.
     * @return config (JSONObject)
     */
    @Deprecated
    public JSONObject getRawConfig() {
        return this.config;
    }
}
