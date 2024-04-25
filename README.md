# CurrencyCalculator / Währungsrechner
Währungsrechner um verschiedene Währungen umzurechnen.
Dies ist ein Projekt von Leon, Jonas und Ewin aus der [BS IT 23c].

## Autoren
 - Leon (github.com/toxytoxy)
 - Jonas (github.com/JonasOFFF & github.com/GetGemueseKebapInYourLife)
 - Ewin (github.com/Syhnx)

## Einführung
In diesem Projekt geht es darum Währungen umzurechnen, beispielsweise "Euro" auf "Amerikanische Dollar". Derzeit gibt es weltweit über 160 Währungen mit ständig schwankenden Wechselkursen. Das Ziel ist es dem Benutzer beim Umwandeln die Rechnungen zu erleichtern. Dazu wählt der Benutzer die Ausgangswährung und die Zielwährung aus und gibt den Geldbetrag ein, den er umtauschen möchte.

## Features
- Enthält wichtige Währungen die berechnet werden können.
- Entnimmt aktuelle Wechselkurse von Google Finances und berechnet diese.
- Enthält ein GUI -> Benutzerfreundlich
- Easy to use
- Ermöglicht Theme-Wechsel zwischen Darkmode und Lightmode

## Download (Java Datei)
TBD

## Installation (Visual Studio Code)
Um dieses Projekt zu installieren und auszuführen, benötigst du das Java Development Kit (JDK) auf deinem Computer.
Das bekommst du auf der offiziellen Oracle Website: 
```
https://www.oracle.com/java/technologies/javase-jdk15-downloads.html
```

Es wird hierbei empfohlen "[Visual Studio Code](https://code.visualstudio.com/)" zu benutzen. Dabei brauchst du eine winget Erweiterung in Powershell, die benötigt wird, um die Dateien herunterzuladen. Hierzu musst du Powershell als Admin starten und den folgenden Befehl ausführen:

### Powershell

```powershell
winget install --id Git.Git -e --source winget
```
Nachdem das erledigt ist, erstellst du dir einen Ordner (am besten auf dem Desktop). Darauf werden die heruntergeladenen Dateien abgespeichert. 
Im Visual Studio Code musst du diesen Ordner erstmals öffnen. Dafür gehst du auf "File -> Open Folder" und wählst dementsprechend den neu erstellten Ordner aus. Auf dem Terminal verbindest du dich mit der Repository. 
Folgende Befehle müssen ausgeführt werden:

### Terminal (Virtual Studio Code, ctrl + ö)

```powershell
git clone https://github.com/toxytoxy/currencycalculator.git
```

- Optional (sucht nach aktualisierungen)

```powershell
git checkout main; git pull
```

So erhälst du die aktuelle Version des Codes.

## Projekt Struktur [Work in Progress]
currencycalculator/
│
├── src/
│   ├── Main/                             // Hauptpaket
│   │   └── CurrencyCalculator.java       // Hauptklasse, in der die Anwendung startet
│   │
│   ├── Utils/                            // Hilfsprogramme-Paket
│   │   ├── Utils.java                    // Hilfsklasse mit allgemeinen Dienstprogrammfunktionen
│   │   │
│   │   └── Data/                         // Daten-Paket
│   │       ├── Calculations.java         // Klasse für Berechnungen
│   │       └── ExchangeRateFetcher.java  // Klasse zum Abrufen von Wechselkursen
│   │
│   └── GUI/                              // GUI-Paket
│       ├── GUI.java                      // Hauptklasse für die grafische Benutzeroberfläche
│       │
│       └── Menu/                         // Menü-Paket
│           └── Menu.java                 // Klasse für das Menü der Anwendung
│
├── lib/                                  // Externe Bibliothekenverzeichnis
│   └── flatlaf-3.4.1.jar                 // Bibliothek für das FlatLaf-Look-and-Feel
│
├── .gitignore                            // Git Ignorieren-Datei
└── README.md                             // Projekt-README-Datei