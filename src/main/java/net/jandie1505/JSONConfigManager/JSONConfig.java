package net.jandie1505.JSONConfigManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class JSONConfig {
    private File jsonFile;
    private boolean verboseLogging;

    /**
     * Creates JSONConfig by String path name
     * @throws IOException
     */
    public JSONConfig(String filePath) throws IOException {
        jsonFile = new File(filePath);
        setVerbose(false);
        init();
    }

    /**
     * Creates JSONConfig by File Object
     * @throws IOException
     */
    public JSONConfig(File file) throws IOException {
        jsonFile = file;
        setVerbose(false);
        init();
    }

    /**
     * Returns the file path
     * @return FilePath (String)
     */
    public String getFilePath(){
        return jsonFile.getPath();
    }

    /**
     * Returns the file name
     * @return FileName (String)
     */
    public String getFileName(){
        return jsonFile.getName();
    }

    /**
     * Returns the file
     * @return File (File)
     */
    public File getFile(){
        return jsonFile;
    }

    /**
     * getJSONObject
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject getJSONObject() throws IOException {
        return load();
    }

    /**
     * Override the JSONObject.
     * Not recommended to use this.
     * @param jsonObject
     * @throws IOException
     */
    public void setJSONObject(JSONObject jsonObject) throws IOException {
        save(jsonObject);
    }

    public void put(String key, Object value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, int value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, boolean value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, String value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, double value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, long value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, float value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, Map value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, Collection value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }
    public void put(String key, JSONObject value) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.put(key, value);
        save(jsonObject);
    }

    public Object get(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.get(key);
    }
    public String getString(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getString(key);
    }
    public int getInt(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getInt(key);
    }
    public boolean getBoolean(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getBoolean(key);
    }
    public float getFloat(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getFloat(key);
    }
    public double getDouble(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getDouble(key);
    }
    public JSONArray getJSONArray(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getJSONArray(key);
    }
    public BigDecimal getBigDecimal(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getBigDecimal(key);
    }
    public BigInteger getBigInteger(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getBigInteger(key);
    }
    public JSONObject getJSONObject(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.getJSONObject(key);
    }

    public boolean has(String key) throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.has(key);
    }

    /**
     * Enable/Disable verbose logging.
     * @param verbose
     */
    public void setVerbose(boolean verbose) {
        verboseLogging = verbose;
    }

    private void init() throws IOException {
        if(!jsonFile.exists()){
            JSONObject jsonObject = new JSONObject();
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(jsonObject.toString());
            writer.close();
            verbose("Initialized JSONConfig. File was created.");
        } else {
            load();
            verbose("Initialized JSONConfig. File was loaded.");
        }
    }

    private JSONObject load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        JSONObject jsonObject = new JSONObject(reader.readLine());
        verbose("Loaded JSON file " + jsonFile.getPath() + ".");
        return jsonObject;
    }

    private void save(JSONObject jsonObject) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        writer.write(jsonObject.toString());
        writer.close();
        verbose("Saved JSON file to " + jsonFile.getPath() + ".");
    }

    private void verbose(String message) {
        if(verboseLogging){
            System.out.println("[JSONConfig] " + message);
        }
    }

    private static void message(String message) {
        System.out.println("[JSONConfig] " + message);
    }
}
