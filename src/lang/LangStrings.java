package lang;

public class LangStrings {
    /*
     * Auflistung von Strings mit allen titeln, button-texten, und mehr in
     * verschiedenen Sprachen
     */

    /*
     * TODO: @Jonas OR @Ewin
     * - Das unten muss noch vervollständigt werden, hab mal angefangen damit ihr
     * checkt wofür die Klasse hier ist
     * - var-aufbau ist immer "SPRACHE_KOMPONENTE_KURZER-NAME-DAFÜR"
     * - für den title hab ich keine komponente angegeben weils im window titel und
     * im titel label auf dem frame einfach genau das selbe ist
     * - Könnt gerne noch Sprache(n) hinzufügen aber kein Französisch!! >:(
     */

    // Deutsch
    public static final String GER_TITLE = "Währungsrechner";
    public static final String GER_BTN_CALC = "Umrechnen";
    public static final String GER_LBL_OUTPUT = "Eingetippt: %INPUT% %BASE_ISO%"
            + "\nDas Ergebnis ist %RESULT% %TARGET_ISO"
            + "\nDie Operation dauerte %FETCH_TIME%ms";

    // Englisch
    public static final String ENG_TITLE = "Currencycalculator";
    public static final String ENG_BTN_CALC = "Convert";
}
