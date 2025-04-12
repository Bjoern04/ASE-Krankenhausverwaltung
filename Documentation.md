# Schriftliche Dokumentation 

## 1 Domain Driven Design 

### 1.1 Analyse der Ubiquitous Language
Im Rahmen des Domain-Driven Designs (DDD) ist die Ubiquitous Language ein essenzielles Konzept, das sicherstellt, dass alle Beteiligten – Entwickler, Domänenexperten und andere Stakeholder
– eine einheitliche und präzise Sprache verwenden. Diese Sprache spiegelt die Geschäftslogik wider und dient als Grundlage für die Modellierung der Software. Durch das definieren der verwendeten Begriffe und dem Hinterlegen von Beschreibung und Definitionen für diese, werden Missverständnisse vermieden und eine klare
Kommunikation zwischen den Beteiligten ermöglicht. Diese könnten ansonsten, zum Beispiel durch Bezeichnungen mit unterschiedlichen Bedeutungsbereichen zu Problemen bei der Kommunikation und fehlerhaften Umsetzungen führen. Wichtig ist ebenfalls die bereits bestehenden Begriffe der Fachsprache zu nutzen und keine neuen zu erfinden. Dies trägt ebenfalls zu einer unter den Stakeholdern verständlichen Kommunikation und Sprache bei.<br>
All diese Kriterien wurden beim Verwenden der nachfolgenden Bezeichnungen beachtet. So wurden beispielsweise bereits bekannte Begriffe der Domäne wie "Patient" und
"Arzt" verwendet und keine eigenen erstellt. Ebenfalls sind uneindeutige Begriffe der Domäne weiter erläutert. So ist zum Beispiel bei der "Untersuchungsart" erläutert, dass 
sich diese auf die Untersuchung eines Patienten von einem Arzt bezieht. Sie stellt keine Untersuchungen des Krankenhauses durch Behörden oder Gesundheitsaufsichten dar.

#### 1.1.1 Fachliche Begriffe und ihre Bedeutung
Die Analyse der Ubiquitous Language basiert auf den zentralen Entitäten und Werten in der Problemstellung eines Krankenhausmanagementsystems. Dabei werden in Klammern die im Quellcode verwendeten englischen Übersetzungen der Begriffe angegeben.<br>
- **Patient (Patient)**: Eine Person, die in dem Krankenhaus medizinisch behandelt wird. Ein Patient besitzt Attribute wie **Name**, **Adresse**, **Geburtsdatum** und *Kontaktinformationen**. Ein Patient kann mehreren **Untersuchungen** unterzogen werden.
- **Arzt (Doctor)**: Ein medizinischer Fachmann, der Patienten in einer bestimmten Untersuchung behandelt. Ärzte haben Attribute wie **Name**, **Adresse**, **Geburtsdatum** und eine Liste von **Untersuchungen**, die sie durchführen.
- **Untersuchung (Examination)**: Ein medizinisches Verfahren, das einem Patienten zugeordnet ist und von einem Arzt durchgeführt wird. Es umfasst die beteiligten Personen, eine **Untersuchungsart**, einen **Startzeitpunkt** und einen **Endzeitpunkt**.
- **Zimmer (Room)**: Ein physischer Raum im Krankenhaus, in welchem Patient während ihres Aufenthalts untergebracht sind. Ein Zimmer enthält eine **Zimmergröße**, eine **Zimmeradresse** und Belegungen. Manche Zimmer bieten Platz für mehrere Patienten, weshalb ein Zimmer mehrere **Belegungen** haben kann.
- **Belegung (Assignment)**: Die Zuweisung eines Patienten zu einem Zimmer für einen bestimmten Zeitraum. Eine Belegung enthält Daten wie **Aufnahmedatum** und **Entlassungsdatum**. Dort ist hinterlegt, welcher Patient in welchem Zimmer untergebracht ist.

- **Adresse (Address)**: Ein Wertobjekt, das den Wohnort eines Patienten oder Arztes beschreibt, bestehend aus **Straße**, **Hausnummer**, **PLZ** und den **Ort**.
- **Zimmeradresse (RoomAddress)**: Die Adressierung eines Zimmers des Krankenhauses innerhalb von diesem. Dabei ist die Anschrift des Krankenhauses nicht Teil davon. Die Adressierung des Zimmers erfolgt über das Gebäude des Krankenhauses, das Stockwerk innerhalb dieses Gebäudes und der Nummer die das Zimmer hat.
- **Kontakt (Contact)**: Ein Wertobjekt, das Kommunikationsdaten einer Person speichert. Dazu gehören **Telefonnummer** und **E-Mail**.
- **Name (Name)**: Der offizielle Name einer Person bestehend aus ihrem Vornamen und Nachnamen.
- **Untersuchungsart (ExaminationType)**: Die Untersuchungsart beschreibt die unterschiedlichen Typen von Untersuchungen, welche in einem Krankenhaus von einem Arzt an einem Patienten durchgeführt werden.

#### 1.1.2 Domänenregeln und Verantwortlichkeiten
1. Ein Patient kann sich in einem bestimmten Zeitraum nur in einem Zimmer befinden. 
2. Ein Arzt kann mehrere Untersuchungen durchführen, aber jede Untersuchung ist genau einem Arzt zugewiesen. Ohne einen Arzt kann keine Untersuchung durchgeführt werden.
3. Ein Zimmer hat eine maximale Kapazität basierend auf seiner Größe. Diese Größe gibt die maximale Anzahl an Patienten an, welche gleichzeitig in einem Zimmer untergebracht werden können.
4. Eine Untersuchung hat immer einen klar definierten Start- und Endzeitpunkt.
5. Die Entlassung eines Patienten darf nicht vor seinem Aufnahmedatum liegen.
6. Ein Patient kann mehrere Untersuchungen haben, welche jedoch nicht gleichzeitig stattfinden können.
7. Ärzte müssen einen Namen, Geburtsdatum, Kontaktdaten und Adresse haben, da nicht ausgewiesene Personen keine Untersuchungen an Patienten durchführen dürfen. Patienten wiederum müssen nicht zwingend einen Namen haben/ ihn angeben, da allen Personen eine Gesundheitsversorgung geboten wird.
8. Ein Arzt kann nur Untersuchungen durchführen, für die er qualifiziert ist. Dies wird durch die **Untersuchungsart** der Untersuchung bestimmt.
9. Es kann keine zwei Zimmer mit der gleichen Adresse geben. Dies ist wichtig, um Verwirrung bei der Zuordnung von Patienten zu Zimmern zu vermeiden.
10. Der Arzt der für eine spezielle Untersuchung verantwortlich ist, kann nur geändert werden, solange die Untersuchung noch nicht begonnen hat und nicht abgeschlossen ist. Der neue Arzt muss ebenfalls für die Untersuchung qualifiziert sein.

## 1.2 Analyse und Begründung der verwendeten Muster
**Adresse (Address)** ist als Value Object implementiert, weil es keine eigene Identität besitzt und vollständig durch seine Werte Straße, Hausnummer, PLZ und Ort beschrieben wird. Zwei Address-Objekte mit denselben Werten sind gleich und austauschbar. Wenn alle ihre Attribute gleich sind, so sind auch die beiden Objekte gleich. Sie geben die gleiche Adresse an. Zudem hat die Adresse keinen Lebenszyklus und wird sich nicht verändern. Sie ist unveränderlich (immutable) und wird nur zur Beschreibung der Entität Patient oder Arzt verwendet. Falls diese eine neue Adresse benötigen, wird ein neues Address-Objekt erstellt.
<br><br>
**Patient (Patient)** ist als Entity implementiert, weil jedes Patient-Objekt eine eigene Identität (UUID) besitzt, die es eindeutig unterscheidet, unabhängig von seinen Attributwerten wie Name oder Adresse. Zwei Patienten können die gleichen Namen haben (andere Attribute nullen), aber sie sind dennoch unterschiedliche Personen/ Entitäten, solange ihre UUIDs verschieden sind. Patienten haben zudem einen Lebenszyklus, da sich ihre Eigenschaften während ihres Aufenthaltes im Krankenhaus ändern können. Sie können in andere Zimmer verlegt werden oder neue Untersuchungen zugewiesen bekommen. Die Eigenschaften eines Patienten müssen sich ändern können. Es soll bei Änderungen kein neues Patienten-Objekt erstellt werden, sondern das bestehende aktualisiert werden.
<br><br>
**ExaminationReassignmentDomainService** ist eine Domain Service, weil es Geschäftslogik enthält, die mehrere Entitäten (Examination und Doctor) betrifft. Er definiert eine Regel der Problemdomäne, welche nicht einer bestimmten Entitle zugewiesen werden kann, da mehrere beteiligt sind. Der Domain Service überprüft, ob die vom Nutzer gewünschte Neuzuweisung eines Arztes zu einer bestehenden Untersuchung möglich ist. Dabei müssen unterschiedliche Bedingungen und Regeln der Examination und Doctor Entität überprüft werden, damit die Krankenhausverwaltung in einem korrekten Zustand bleibt. Der Domain Service stellt sicher, dass der neue Doktor die Untersuchungsart durchführen kann, die Untersuchung nicht in der Vergangenheit liegt und der Doktor zum Zeitpunkt der Untersuchung verfügbar ist.

## 2 Clean Architecture
### 2.1 Grundlagen Clean Architecture
Die Architektur der Krankenhausverwaltungsanwendung basiert auf den 
Prinzipien der Clean Architecture. Dieses Architekturmuster fördert eine
klare Trennung von Verantwortlichkeiten, eine hohe Testbarkeit und eine 
geringe Kopplung zwischen den Schichten. Dadurch wird eine flexible, wartbare
und erweiterbare Softwarelösung gewährleistet.

### 2.2 Schichten der Anwendung
Die Anwendung besteht aus den typischen fünf Hauptschichten der Clean Architecture:
1. Abstraktions-Schicht
2. Domain-Schicht
3. Application-Schicht
4. Adapter-Schicht
5. Plugin-Schicht

### 2.2.1 Abstraktions-Schicht
### 2.2.2 Domain-Schicht
Die Domain-Schicht enthält die zentrale Geschäftslogiken und definiert die
grundlegenden Geschäftsobjekte (Entities) sowie deren Verhalten. Sie 
Dabei sollen diese nicht von äußeren Elementen wie Datenbanken oder APIs 
abhängig sein. 

#### Bestandteile
- **Entities**: Die Entities Patient, Arzt, Zimmer, Untersuchung und Belegung 
repräsentieren die zentralen Domänenobjekte, welche zur Abbildung und Modellierung
der Geschäftslogik benötigt werden. Diese Objekte können sich im Ablauf des 
Programms verändern und haben einen Lebenszyklus.
- **Value Objects**: Auch die Value Objects sind in der Domain-Schicht enthalten.
Sie sind unveränderliche Werte wie Name, Adresse, etc., welche zur Beschreibung
der Entities verwendet werden. 
- **Repositories**: Die Repositories definieren Interfaces für die Aggregate 
Roots, welche in äußeren Schichten implementiert werden können, um auf Daten
z.B. aus einer Datenbank zuzugreifen.
- **Domain Services**: Der letzte Bestandteil sind die Domain Services,
welche Geschäftslogik beinhalten an welcher mehrere unterschiedliche
Entities beteiligt sind.

<br>


## 3 Programming Principles
Single respons bei Belegung patient und zimmer und bei herausnehmen der parser methoden aus create room command (auch refactoring)

## 4 Refactoring
## 6 Entwurfsmuster
### 6.1 Builder Pattern
#### Begründung für den Einsatz des Builder-Patterns
Das Builder-Pattern wird eingesetzt, um die Instanziierung von Objekten mit vielen optionalen Attributen zu erleichtern und Konstruktoren mit gleichen Signaturen 
zu ermöglichen. Insbesondere für eine komplexe Domänenklasse, die viele Felder besitzt und sich mit der Zeit erweitern kann, bietet dieses Muster 
eine effektive Möglichkeit zur Objekterzeugung. Aufgrund seiner Vorteile wurde das Muster in unterschiedlichen Klassen des Projektes verwendet. Im Folgenden
sollen die Vor- und Nachteile dieses Patterns am Beispiel der Patient Entität erläutert werden.

#### Warum das Muster an dieser Stelle eingesetzt wurde
Die Klasse Patient besitzt mehrere Attribute, von denen einige optional sind. Ein konventioneller Konstruktor mit vielen Parametern wäre unübersichtlich und 
fehleranfällig. Das Builder-Pattern erlaubt es, nur die benötigten Parameter zu setzen und so eine lesbare, flexible und intuitive Objektinitialisierung zu 
ermöglichen.

#### Verbesserung des Codes durch das Builder-Pattern
Lesbarkeit & Wartbarkeit: Durch die Verwendung von Methoden mit sprechenden Namen (withName(), withAddress(), etc.) wird der Code lesbarer und intuitiver.
Flexibilität: Das Muster erlaubt es, optionale Parameter zu setzen, ohne überladene Konstruktoren zu benötigen.
Unveränderlichkeit: Das fertige Patient-Objekt kann als immutable behandelt werden, da alle Attribute final gesetzt werden.
Vermeidung von inkonsistenten Zuständen: Die notwendigen Validierungen können zentral im build()-Methodenaufruf erfolgen.

#### Vor- und Nachteile des Builder-Patterns

Vorteile:
- Verbesserte Lesbarkeit und Verständlichkeit des Codes
- Flexibilität bei der Objekterstellung
- Vermeidung von zu vielen Konstruktoren mit unterschiedlichen Parameterkombinationen
- Förderung von Immutability (wenn gewünscht)

Nachteile:
- Zusätzlicher Implementierungsaufwand durch eine separate Builder-Klasse
- Erhöhter Speicherbedarf, da ein zusätzliches Objekt (PatientBuilder) benötigt wird
- Vor- und Nachteile ohne das Builder-Pattern

#### Vorteile ohne Builder-Pattern:
- Weniger Code durch direkte Nutzung von Konstruktoren
- Geringerer Speicherverbrauch, da keine zusätzliche Builder-Klasse notwendig ist

#### Nachteile ohne Builder-Pattern:
- Lange, schwer verständliche Konstruktoraufrufe
- Höhere Fehleranfälligkeit durch falsche Parameterreihenfolge
- Geringere Erweiterbarkeit des Codes

#### Fazit
Das Builder-Pattern verbessert die Struktur und Lesbarkeit des Codes erheblich, insbesondere bei komplexen Objekten mit vielen optionalen Attributen. 
Während der Mehraufwand an Code und Speicherverbrauch als Nachteil betrachtet werden kann, überwiegen die Vorteile, da das Muster die Wartbarkeit und 
Erweiterbarkeit des Codes deutlich verbessert.
