# EGR - EssensGetter Registration

EGR ist das erste Tool aus der EssensGetter Familie. Es ist ein Vaadin Projekt, das mit Spring Boot, Java, Hibernate, MariaDB und Maven entwickelt wurde. Mit EGR können Benutzer sich für den EssensGetter registrieren.

## Zugang

Das Tool ist unter der URL https://egr.olech2412.de erreichbar (nur mit Zugangsdaten, da es sich um ein privates Studentenprojekt handelt, das nicht für die Öffentlichkeit zur Verfügung steht).

## Installation

1. Klonen Sie das Repository mit `git clone https://github.com/username/EGR.git`
2. Wechseln Sie in das Projektverzeichnis mit `cd EGR`
3. Führen Sie `mvn clean install` aus, um die Abhängigkeiten zu installieren
4. Starten Sie das Projekt mit `mvn spring-boot:run`
5. Öffnen Sie Ihren Browser und navigieren Sie zu `http://localhost:8080` (kann in den application.properties angepasst werden)

## Registrierung

Die Registrierung ist kostenlos. Nach erfolgreicher Registrierung erhält der Benutzer einen Aktivierungscode und einen Deaktivierungscode, die ihm zugeordnet werden. Der Benutzer muss dann seine E-Mail-Adresse bestätigen, um freigeschaltet zu werden. Anschließend wird der EssensGetter 2.0 den Newsletter an ihn versenden.

## Datenbank

EGR verwendet MariaDB als Datenbank. Um die Datenbank zu setzen müssen Sie die folgenden Schritte ausführen:

1. Installieren Sie MariaDB auf Ihrem Computer
2. Erstellen Sie eine Datenbank mit dem Namen "egr"
3. Erstellen Sie einen Benutzer mit dem Namen "egruser" und einem Passwort "password"
4. Geben Sie dem Benutzer die Berechtigungen für die erstellte Datenbank

Die Datenbankverbindungseinstellungen können in der Datei "application.properties" im Projektverzeichnis angepasst werden.

## Deaktivierung

Falls der Benutzer seine Registrierung deaktivieren möchte, kann er dies mit dem zugewiesenen Deaktivierungscode tun. 
Der Deaktivierungscode ermöglicht somit eine sofortige Abmeldung vom Newsletter mit jeder E-Mail die an den Nutzer gesendet wird (da der EssensGetter 2.0 auf die selben Daten zurückgreift) sowie auch eine Abmeldung falls ein fremder die E-Mail Adresse registriert.
Durch klick auf den Link werden alle personenbezogenen Daten sofort gelöscht.

## Das Konzept
![EssensGetter SoftwareArchitektur drawio (1)](https://user-images.githubusercontent.com/76694468/212769942-63c4dd74-2664-4111-9736-429c27f669c5.png)

## Anmerkung

Bitte beachten Sie, dass es sich hierbei um ein privates Studentenprojekt handelt und daher eventuelle Fehler oder Inkonsistenzen vorhanden sein können.
