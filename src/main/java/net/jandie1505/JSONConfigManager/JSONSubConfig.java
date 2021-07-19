package net.jandie1505.JSONConfigManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class JSONSubConfig {
    private String name;
    private Object parentConfig;
    private boolean verboseLogging;

    public JSONSubConfig(String keyName, JSONConfig parent) throws IOException {
        name = keyName;
        parentConfig = parent;
        setVerbose(false);
        init();
    }

    public JSONSubConfig(String keyName, JSONSubConfig parent) throws IOException {
        name = keyName;
        parentConfig = parent;
        setVerbose(false);
        init();
    }

    /**
     * This method returns the parent config. You need to cast the Object to JSONConfig or JSONSubConfig.
     * @return Object
     */
    public Object getParent(){
        return parentConfig;
    }

    /**
     * Get the JSONObject of the JSONConfig
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
     * @deprecated Use overrideJSONObject instead
     */
    @Deprecated
    public void setJSONObject(JSONObject jsonObject) throws IOException {
        save(jsonObject);
    }
    /**
     * Override the JSONObject.
     * Not recommended to use this.
     * @param jsonObject
     * @throws IOException
     */
    public void overrideJSONObject(JSONObject jsonObject) throws IOException {
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
    public void put(String key, JSONArray value) throws IOException {
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
     * Removes a key from the JSONConfig.
     * @param key
     * @throws IOException
     */
    public void remove(String key) throws IOException {
        JSONObject jsonObject = load();
        jsonObject.remove(key);
        save(jsonObject);
    }

    /**
     * Returns the JSONObject as a String.
     * @return String
     */
    @Override
    public String toString(){
        JSONObject jsonObject = null;
        try {
            jsonObject = load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * Returns an Array with the keys in it (for for each loops).
     * @return Object[] keyArray
     * @throws IOException
     */
    public Object[] getKeyArray() throws IOException {
        JSONObject jsonObject = load();
        return jsonObject.keySet().toArray();
    }

    /**
     * Clears the JSONConfig.
     * @throws IOException
     */
    public void clear() throws IOException {
        JSONObject jsonObject = load();
        jsonObject.clear();
        save(jsonObject);
    }

    /**
     * Enable/Disable verbose logging.
     * @param verbose
     */
    public void setVerbose(boolean verbose) {
        verboseLogging = verbose;
    }

    private void init() throws IOException {
        if(parentConfig instanceof JSONConfig){
            if(!((JSONConfig) parentConfig).has(name)){
                JSONObject jsonObject = new JSONObject();
                save(jsonObject);
            } else {
                JSONObject jsonObject = load();
                save(jsonObject);
            }
        } else if(parentConfig instanceof JSONSubConfig){
            if(!((JSONSubConfig) parentConfig).has(name)){
                JSONObject jsonObject = new JSONObject();
                save(jsonObject);
            } else {
                JSONObject jsonObject = load();
                save(jsonObject);
            }
        } else {
            message("An error occured while setting up the JSONConfig");
        }
    }

    private JSONObject load() throws IOException {
        if(parentConfig instanceof JSONConfig) {
            verbose("Loaded subconfig");
            return ((JSONConfig) parentConfig).getJSONObject(name);
        } else if(parentConfig instanceof JSONSubConfig) {
            verbose("Loaded subconfig");
            return ((JSONSubConfig) parentConfig).getJSONObject(name);
        } else {
            verbose("An error occured while loading the subconfig");
            return null;
        }
    }

    private void save(JSONObject jsonObject) throws IOException {
        if(parentConfig instanceof JSONConfig){
            ((JSONConfig) parentConfig).put(name, jsonObject);
            verbose("Saved subconfig");
        } else if(parentConfig instanceof JSONSubConfig){
            ((JSONSubConfig) parentConfig).put(name, jsonObject);
            verbose("Saved subconfig");
        } else {
            verbose("An error occured while saving the subconfig");
        }
    }

    private void verbose(String message) {
        if(verboseLogging){
            System.out.println("[JSONSubConfig] " + message);
        }
    }

    private static void message(String message) {
        System.out.println("[JSONSubConfig] " + message);
    }
}
