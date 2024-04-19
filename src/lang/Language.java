package lang;

import java.util.Locale;

public class Language {
    private static Locale locale;

    // Auflistung aller unterstützten Sprachen
    public enum Languages {
        GERMAN,
        ENGLISH
    }

    public static void switchAppLanguage(Languages language) {
        switch (language) {
            case Languages.ENGLISH:
                locale = Locale.US;
                Locale.setDefault(locale);
                break;

            case Languages.GERMAN:
                locale = Locale.US;
                Locale.setDefault(locale);
                break;
        }

        // TODO: ..
    }
}
