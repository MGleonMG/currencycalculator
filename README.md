<div align="center" id="top"> 
  <img src="src\resources\app_icon\app_icon.png" alt="Currencycalculator" />

  &#xa0;

</div>

<h1 align="center">Währungsrechner</h1>

<p align="center">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/mgleonmg/currencycalculator?color=56BEB8">
  <img alt="commit activity" src="https://img.shields.io/github/commit-activity/w/mgleonmg/currencycalculator">
  <img alt="last commit" src="https://img.shields.io/github/last-commit/mgleonmg/currencycalculator">
</p>

<!-- ================================= -->
<!-- @Ewin hier das muss noch geupdatet werden -->
<!-- Für https://currencycalculator.atlassian.net/browse/CUR-28 -->
<!-- ================================= -->
<p align="center">
  <a href="#dart-about">About</a> &#xa0; | &#xa0; 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#rocket-technologies">Technologies</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requirements">Requirements</a> &#xa0; | &#xa0;
  <a href="#checkered_flag-starting">Starting</a> &#xa0; | &#xa0;
  <a href="#memo-license">License</a> &#xa0; | &#xa0;
  <a href="https://github.com/{{YOUR_GITHUB_USERNAME}}" target="_blank">Author</a>
</p>
<hr>
<br>

## :dart: Über das Projekt ##

TBD

## :sparkles: Features ##

:heavy_check_mark: Feature 1;\
:heavy_check_mark: Feature 2;\
:heavy_check_mark: Feature 3;

## :rocket: Libs / APIs ##
- [Java](https://www.java.com/de/)
- [FlatLaf Lib](https://github.com/JFormDesigner/FlatLaf)
- [Gson](https://github.com/google/gson)

## :tree: Projektstruktur ##
```
[currencycalculator]
├─ .gitignore                        // Verzeichnis für Git-ignorierte Dateien und Ordner
├─ README.md                        // Readme-Datei mit Projektinformationen
└─ src
   ├─ GUI                            // Paket für die grafische Benutzeroberfläche
   │    ├─ GUI.java                  // Hauptklasse für die GUI
   │    ├─ Popups                    // Paket für Popup-Fenster
   │    │    └─ PopupDisplay.java    // Klasse für die Anzeige von Popup-Fenstern
   │    └─ Settings                  // Paket für das Einstellungs-GUI
   │         └─ SettingsGUI.java     // Klasse für das Einstellungs-GUI
   ├─ lang                           // Paket für Sprachunterstützung
   │    ├─ LangStrings.java          // Klasse für Sprachzeichenfolgen
   │    └─ Language.java             // Klasse für Spracheinstellungen
   ├─ lib                            // Bibliotheksordner für externe JAR-Dateien
   │    ├─ flatlaf-3.4.1.jar         // Bibliothek für Look and Feel
   │    └─ gson-2.10.1.jar           // Bibliothek für JSON-Verarbeitung
   ├─ Main                           // Paket für die Hauptklasse
   │    └─ CurrencyCalculator.java   // Hauptklasse für den Währungsrechner
   ├─ resources                      // Ressourcenverzeichnis für Anwendungsdaten
   │    ├─ app_icon                  // Verzeichnis für Anwendungsicons
   │    │    └─ app_icon.png         // Anwendungsicon-Datei
   │    └─ buttons                   // Verzeichnis für Schaltflächenbilder
   │         ├─ button_loading.gif   // Ladeanimation für Schaltflächen
   │         ├─ icon_copy-button-dark.png   // Schaltflächenbild zum Kopieren (dunkel)
   │         ├─ icon_copy-button-light.png  // Schaltflächenbild zum Kopieren (hell)
   │         └─ settings_button.png         // Schaltflächenbild für Einstellungen
   └─ Utils                          // Paket für Hilfsklassen
         ├─ Data                     // Paket für Datenverarbeitung
         │    ├─ Calculations.java   // Klasse für Berechnungen
         │    ├─ Config              // Paket für Konfiguration
         │    │    ├─ Config.java    // Klasse für Konfigurationseinstellungen
         │    │    ├─ ConfigDefaults.java   // Klasse für Standardkonfiguration
         │    │    └─ Settings       // Paket für Einstellungen
         │    │         ├─ AppLanguage.java   // Klasse für Anwendungssprache
         │    │         ├─ AppTheme.java      // Klasse für Anwendungsthema
         │    │         └─ LastCalculation.java   // Klasse für die letzte Berechnung
         │    └─ ExchangeRateFetcher.java   // Klasse für den Abruf von Wechselkursen
         └─ Utils.java                // Hilfsklasse mit allgemeinen Dienstprogrammen

```

## :white_check_mark: Requirements ##

Before starting :checkered_flag:, you need to have [X](https://X) and [Y](https://Y) installed.

## :checkered_flag: Starting | Git Commands to know ##
```bash
# Projekt auf ein lokales Gerät klonen
$ git clone https://github.com/mgleonmg/currencycalculator

# Auf Main Branch wechseln und die neusten infos und code runterladen
$ git checkout main; git fetch; git pull

# Neue branch erstellen und zu dieser wechseln
$ git checkout -b some-new-branch-name

# Die neusten Änderungen auf der main branch in einer deiner beliebigen branches mergen
$ git pull origin main
```

&#xa0;

<a href="#top">Zurück zum Anfang</a>
