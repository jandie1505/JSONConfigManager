# JSONConfigManager
A config manager for json in java.

## How to use

```java

// JSONObject of default config values
JSONObject defaultConfigValues;

// Override defaults
// Should the config values loaded with reloadConfig() override or just be added to the default config values
// false = recommended
boolean overrideDefaults;

// Directory where the config is located
File dataFolder;

// Config file name
String configFileName;

// Create ConfigManager
ConfigManager configManager = new ConfigManager(defaultConfigValues, overrideDefaults, dataFolder, configFileName);

// (Re)load config
configManager.reloadConfig();

// Get config to read values
JSONObject config = configManager.getConfig();

// Loading values
int testInt = configManager.getConfig().optInt("testInt", 0);
String testString = configManager.getConfig().optString("testString", "test123");
JSONObject testJSONObject = configManager.getConfig().optJSONObject("testJSONObject", new JSONObject());

// Get config to modify values
JSONObject config = configManager.getRawConfig();

// Modify values
configManager.getRawConfig().put("testInt", 0);
configManager.getRawConfig().put("testString", "test");

```
