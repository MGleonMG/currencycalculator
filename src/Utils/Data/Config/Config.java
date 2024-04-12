package Utils.Data.Config;

public class Config {
    private String setting;
    private String value;

    public String getConfigSetting() {
        return setting;
    }

    public void setConfigSetting(String setting) { /* saveSetting() instead?? */
        this.setting = setting;
    }

    public String getConfigValue() {
        return value;
    }

    public void setConfigValue(String value) {/* saveSetting() instead?? */
        this.value = value;
    }
}
