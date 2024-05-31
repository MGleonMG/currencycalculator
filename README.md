<div align="center" id="top"> 
  <img src="src\resources\app_icon\app_icon.png" alt="Currencycalculator" />

  &#xa0;

</div>

<h1 align="center">Währungsrechner - <img alt="version" src="https://img.shields.io/badge/version-1.0.0-dark_green"></h1>

<p align="center">
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/mgleonmg/currencycalculator?color=56BEB8">
  <img alt="commit activity" src="https://img.shields.io/github/commit-activity/w/mgleonmg/currencycalculator">
  <img alt="last commit" src="https://img.shields.io/github/last-commit/mgleonmg/currencycalculator">
</p>

<p align="center"> 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#sparkles-features">Libs und APIs</a> &#xa0; | &#xa0;
  <a href="#white_check_mark-requirements">Requirements</a> &#xa0; | &#xa0;
  <a href="#arrow_down-download">Download</a> &#xa0; | &#xa0;
  <a href="#checkered_flag-Git-Commands">Commands</a> &#xa0; | &#xa0;
  <a href="#trollface-Autoren">Autoren</a> &#xa0;
</p>
<hr>
<br>

## :dart: Einführung ##

In diesem Projekt geht es darum Währungen umzurechnen, beispielsweise "Euro" auf "Amerikanische Dollar". Derzeit gibt es weltweit über 160 Währungen mit ständig schwankenden Wechselkursen. Das Ziel ist es dem Benutzer beim Umwandeln die Rechnungen zu erleichtern. Dazu wählt der Benutzer die Ausgangswährung und die Zielwährung aus und gibt den Geldbetrag ein, den er umtauschen möchte.

## :sparkles: Features ##

:heavy_check_mark: Enthält wichtige Währungen die berechnet werden können.\
:heavy_check_mark: Entnimmt aktuelle Wechselkurse von [Google Finances](https://www.google.com/finance/) und berechnet diese.\
:heavy_check_mark: Enthält ein GUI -> Benutzerfreundlich und Easy to use\
:heavy_check_mark: Ermöglicht Theme-Wechsel zwischen Darkmode und Lightmode\
:heavy_check_mark: Benutzereinstellungen können gespeichert werden

## :rocket: Libs und APIs ##
- [Java](https://www.java.com/de/)
- [FlatLaf Lib](https://github.com/JFormDesigner/FlatLaf)
- [Gson](https://github.com/google/gson)

## :white_check_mark: Requirements ##

Für den Start des Programmes :checkered_flag: musst du [Git](https://git-scm.com) und [Java Development Kit](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) auf dem System installiert haben.

Git lässt sich mit Hilfe des unten genannten commands auch über Windows Powershell installieren
```powershell
winget install --id Git.Git -e --source winget
```

Wir empfehlen "Visual Studio Code" als Umgebungsentwicklung. Nachdem das erledigt ist, sollte man sich einen Ordner erstellen lassen (am besten auf dem Desktop). Im Visual Studio Code muss man den neu erstellten Ordner öffnen. Die unten stehenden Git Commands sind für die Nutzung des Programmes wichtig.

## :checkered_flag: Nützliche Git Commands ##
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

## :arrow_down: Download

See [releases](https://github.com/MGleonMG/currencycalculator/releases)

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
   │    └─ Components                // Paket für die GUI-Komponenten
   │         ├─ SettingsGUI.java     // Klasse für die Einstellungen
   │         ├─ Miscellaneous.java   // Klasse für diverse Inhalte
   │         └─ InputOutput.java     // Klasse für den Input-Output
   ├─ lang                           // Paket für Sprachunterstützung
   │    └─ Language.java             // Klasse für Spracheinstellungen
   ├─ lib                            // Bibliotheksordner für externe JAR-Dateien
   │    ├─ flatlaf-3.4.1.jar         // Bibliothek für Look and Feel
   │    └─ gson-2.10.1.jar           // Bibliothek für JSON-Verarbeitung
   ├─ Main                           // Paket für die Hauptklasse
   │    └─ CurrencyCalculator.java   // Hauptklasse für dieses Projekt
   ├─ resources                      // Ressourcenverzeichnis für Icons, Sprachpakete, etc.
   │    ├─ app_icon                  // Verzeichnis für das Anwendungsicon
   │    │    └─ app_icon.png         // Anwendungsicon-Datei
   │    ├─ buttons                   // Verzeichnis für Schaltflächenbilder
   │    │    ├─ button_loading.gif   // Ladeanimation für Schaltflächen
   │    │    ├─ icon_copy-button-dark.png   // Schaltflächenbild zum Kopieren (dunkel)
   │    │    ├─ icon_copy-button-light.png  // Schaltflächenbild zum Kopieren (hell)
   │    │    └─ settings_button.png         // Schaltflächenbild für Einstellungen
   │    │
   │    └─ languages
   │         ├─ lang_danish.properties  // Sprachpaket: Dänsich
   │         ├─ lang_english.properties // Sprachpaket: Englisch
   │         ├─ lang_german.properties  // Sprachpaket: Deutsch
   │         └─ lang_spanish.properties // Sprachpaket: Spanisch
   │ 
   └─ Utils                          // Paket für Hilfsklassen
         ├─ Data                     // Paket für Datenverarbeitung
         │    ├─ Calculations.java   // Klasse für Berechnungen
         │    ├─ Config              // Paket für die Config
         │    │    ├─ Config.java    // Klasse für Grundfunktionen der Config
         │    │    ├─ ConfigDefaults.java         // Klasse für Standardkonfiguration
         │    │    └─ Settings                    // Paket für die einzelnen Einstellungen in der Config Datei
         │    │         ├─ AppLanguage.java       // Klasse fürs Lesen und Schreiben der Spracheinstellungen
         │    │         ├─ AppTheme.java          // Klasse fürs Lesen und Schreiben des AppThemes
         │    │         └─ LastCalculation.java   // Klasse fürs Lesen und Schreiben der letzen Umrechnung
         │    └─ ExchangeRateFetcher.java         // Klasse für den Abruf von Wechselkursen
         ├─ Utils.java                            // Hilfsklasse mit allgemeinen Hilfsmethoden
         └─ RestartHelper.java                    // Klasse für den Neustart

```

## :trollface: Autoren

 - Leon (github.com/mgleonmg)
 - Jonas (github.com/JonasOFFF & github.com/GetGemueseKebapInYourLife)
 - Ewin (github.com/Syhnx)

&#xa0;

<a href="#top">Zurück zum Anfang</a>
