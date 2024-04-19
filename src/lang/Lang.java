package lang;

import java.util.Locale;

public class Lang {
    // Deutsch
    public static final String GER_LABEL_TITLE = "Währungsrechner";

    // Englisch
    public static final String ENG_LABEL_TITLE = "Currencycalculator";

    // Alle Sprachen
    public enum Language {
        GERMAN,
        ENGLISH
    }

    public static void switchLanguage(/* Language lang */) {
        Locale locale = Locale.US;
        Locale.setDefault(locale);

        // TODO: ..
    }
}
