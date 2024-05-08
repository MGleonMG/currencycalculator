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

<p align="center">
  <a href="#dart-Einführung">About</a> &#xa0; | &#xa0; 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requirements">Requirements</a> &#xa0; | &#xa0;
  <a href="#arrow_down-download">Download</a> &#xa0; | &#xa0;
  <a href="#checkered_flag-Git-Commands">Commands</a> &#xa0; | &#xa0;
</p>
<hr>
<br>

## :dart: Einführung ##

In diesem Projekt geht es darum Währungen umzurechnen, beispielsweise "Euro" auf "Amerikanische Dollar". Derzeit gibt es weltweit über 160 Währungen mit ständig schwankenden Wechselkursen. Das Ziel ist es dem Benutzer beim Umwandeln die Rechnungen zu erleichtern. Dazu wählt der Benutzer die Ausgangswährung und die Zielwährung aus und gibt den Geldbetrag ein, den er umtauschen möchte.

## :sparkles: Features ##

:heavy_check_mark: Enthält wichtige Währungen die berechnet werden können.\
:heavy_check_mark: Entnimmt aktuelle Wechselkurse von Google Finances und berechnet diese.\
:heavy_check_mark: Enthält ein GUI -> Benutzerfreundlich\
:heavy_check_mark: Easy to use\
:heavy_check_mark: Ermöglicht Theme-Wechsel zwischen Darkmode und Lightmode

## :rocket: LIBs und APIs ##
- [Java](https://www.java.com/de/)
- [FlatLaf Lib](https://github.com/JFormDesigner/FlatLaf)
- [Gson](https://github.com/google/gson)

## :white_check_mark: Requirements ##

Für den Start des Programmes :checkered_flag: musst du [Git](https://git-scm.com) und Java Development Kit [JDK](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) auf dem System installiert haben.

Es wird hierbei empfohlen "Visual Studio Code" zu benutzen. Dabei brauchst du eine winget Erweiterung in Powershell, die benötigt wird, um die nötigen Dateien herunterzuladen. Hierzu musst du Powershell als Admin starten und den folgenden Befehl ausführen:

### Powershell

```powershell
winget install --id Git.Git -e --source winget
```
Nachdem das erledigt ist, erstellst du dir einen Ordner (am besten auf dem Desktop). Darauf werden die heruntergeladenen Dateien abgespeichert. 
Im Visual Studio Code musst du diesen Ordner erstmals öffnen. Dafür gehst du auf "File -> Open Folder" und wählst dementsprechend den neu erstellten Ordner aus. Auf dem Terminal verbindest du dich mit dem Repository. 
Folgende Befehle müssen ausgeführt werden:

## :arrow_down: Download

TBD

## :checkered_flag: Git Commands ##
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

## :deciduous_tree: Projektstruktur ##
```
[currencycalculator]
├─ .gitignore                        // Verzeichnis für Git-ignorierte Dateien und Ordner
├─ README.md                         // Readme-Datei mit Projektinformationen
└─ src
   ├─ GUI                            // Paket für die grafische Benutzeroberfläche
   │    ├─ GUI.java                  // Hauptklasse für die GUI
   │    ├─ Popups                    // Paket für Popup-Fenster
   │    │    └─ PopupDisplay.java    // Klasse für die Anzeige von Popup-Fenstern
   │    └─ Settings                  // Paket für das Einstellungs-GUI
   │         └─ SettingsGUI.java     // Klasse für das Einstellungs-GUI
   ├─ lang                           // Paket für Sprachunterstützung
   │    └─ Language.java             // Klasse für Spracheinstellungen
   ├─ lib                            // Bibliotheksordner für externe JAR-Dateien
   │    ├─ flatlaf-3.4.1.jar         // Bibliothek für Look and Feel
   │    └─ gson-2.10.1.jar           // Bibliothek für JSON-Verarbeitung
   ├─ Main                           // Paket für die Hauptklasse
   │    └─ CurrencyCalculator.java   // Hauptklasse für den Währungsrechner
   ├─ resources                      // Ressourcenverzeichnis für Anwendungsdaten
   │    ├─ app_icon                  // Verzeichnis für Anwendungsicons
   │    │    └─ app_icon.png         // Anwendungsicon-Datei
   │    ├─ buttons                   // Verzeichnis für Schaltflächenbilder
   │    │    ├─ button_loading.gif   // Ladeanimation für Schaltflächen
   │    │    ├─ icon_copy-button-dark.png   // Schaltflächenbild zum Kopieren (dunkel)
   │    │    ├─ icon_copy-button-light.png  // Schaltflächenbild zum Kopieren (hell)
   │    │    └─ settings_button.png         // Schaltflächenbild für Einstellungen
   │    │
   │    └─ languages
   │         ├─ lang_danish.properties  // Paketsprache: Dänsich
   │         ├─ lang_english.properties // Paketsprache: Englisch
   │         ├─ lang_german.properties  // Paketsprache: Deutsch
   │         └─ lang_spanish.properties // Paketsprache: Spanisch
   │ 
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

## :trollface: Autoren

 - Leon (github.com/mgleonmg)
 - Jonas (github.com/JonasOFFF & github.com/GetGemueseKebapInYourLife)
 - Ewin (github.com/Syhnx)

&#xa0;

<a href="#top">Zurück zum Anfang</a>
