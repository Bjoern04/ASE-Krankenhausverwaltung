# Schriftliche Dokumentation 

## 1 Domain Driven Design 

### 1.1 Analyse der Ubiquitous Language
Im Rahmen des Domain-Driven Designs (DDD) ist die Ubiquitous Language ein essenzielles Konzept,
das sicherstellt, dass alle Beteiligten – Entwickler, Domänenexperten und andere Stakeholder
– eine einheitliche und präzise Sprache verwenden. Diese Sprache spiegelt die Geschäftslogik wider
und dient als Grundlage für die Modellierung der Software. Durch das definieren der verwendeten Begriffe und dem
Hinterlegen von Beschreibung und Definitionen für diese, werden Missverständnisse vermieden und eine klare
Kommunikation zwischen den Beteiligten ermöglicht. Diese könnten ansonsten, zum Beispiel durch Bezeichnungen mit
unterschiedlichen Bedeutungsbereichen zu Problemen bei der Kommunikation und fehlerhaften Umsetzungen führen.
Wichtig ist ebenfalls die bereits bestehenden Begriffe der Fachsprache zu nutzen und keine neuen zu erfinden. Dies trägt ebenfalls zu einer unter den Stakeholdern
verständlichen Kommunikation und Sprache bei.<br>
All diese Kriterien wurden beim Verwenden der nachfolgenden Bezeichnungen beachtet. So wurden beispielsweise bereits bekannte Begriffe der Domäne wie "Patient" und
"Arzt" verwendet und keine eigenen erstellt. Ebenfalls sind uneindeutige Begriffe der Domäne weiter erläutert. So ist zum Beispiel bei der "Zimmeradresse" erläutert, dass 
sich diese auf die Adressierung eines Zimmers innerhalb des Krankenhauses bezieht und nicht auch die Adresse des Krankenhauses an sich beinhaltet.

#### 1.1.1 Fachliche Begriffe und ihre Bedeutung
Die Analyse der Ubiquitous Language basiert auf den zentralen Entitäten und Werten in der Problemstellung 
eines Krankenhausmanagementsystems. Dabei werden in Klammern die im Quellcode verwendeten englischen Übersetzungen der Begriffe angegeben.<br>
- **Patient (Patient)**: Eine Person, die in dem Krankenhaus medizinisch behandelt wird. Ein Patient besitzt Attribute wie **Name**, **Adresse**, **Geburtsdatum** und **Kontaktinformationen**. Ein Patient kann mehreren **Untersuchungen** unterzogen werden.
- **Arzt (Doctor)**: Ein medizinischer Fachmann, der Patienten in einer bestimmten Untersuchung behandelt. Ärzte haben Attribute wie **Name**, **Adresse**, **Geburtsdatum** und eine Liste von **Untersuchungen**, die sie durchführen.
- **Untersuchung (Examination)**: Ein medizinisches Verfahren, das einem Patienten zugeordnet ist und von einem Arzt durchgeführt wird. Es umfasst eine **Untersuchungsart**, einen **Startzeitpunkt** und einen **Endzeitpunkt**.
- **Zimmer (Room)**: Ein physischer Raum im Krankenhaus, in welchem Patient während ihres Aufenthalts untergebracht sind. Ein Zimmer enthält eine **Zimmernummer** und eine **Zimmeradresse**. Manche Zimmer bieten Platz für mehrere Patienten, weshalb ein Zimmer mehrere **Belegungen** haben kann.
- **Belegung (Assignment)**: Die Zuweisung eines Patienten zu einem Zimmer für einen bestimmten Zeitraum. Eine Belegung enthält Daten wie **Aufnahmedatum** und **Entlassungsdatum**. Dort ist hinterlegt, welcher Patient in welchem Zimmer untergebracht ist.
- **Zeitpunkt **: Eine Kombination aus **Datum** und **Uhrzeit**, um einen genauen Moment festzuhalten, an dem beispielweise eine Untersuchung beginnt.
- **Adresse (Address)**: Ein Wertobjekt, das den Wohnort eines Patienten oder Arztes beschreibt, bestehend aus **Straße**, **Hausnummer**, **PLZ** und den **Ort**.
- **Zimmeradresse (RoomAddress)**: Die Adressierung eines Zimmers des Krankenhauses innerhalb von diesem. Dabei ist die Anschrift des Krankenhauses nicht Teil davon. Die Adressierung des Zimmers erfolgt über das Gebäude des Krankenhauses, das Stockwerk innerhalb dieses Gebäudes und der Nummer die das Zimmer hat.
- **Kontakt (Contact)**: Ein Wertobjekt, das Kommunikationsdaten einer Person speichert. Dazu gehören **Telefonnummer** und **E-Mail**.
- **Datum (Date)**: Ein Wertobjekt, das einen bestimmten Tag eines Jahres festhält.
- **Name (Name)**: Der offizielle Name einer Person bestehend aus ihrem Vornamen und Nachnamen.
- **Uhrzeit (Time)**: 

#### 1.1.2 Domänenregeln und Verantwortlichkeiten
1. **Ein Patient kann sich in einem bestimmten Zeitraum nur in einem Zimmer befinden.**
2. **Ein Arzt kann mehrere Untersuchungen durchführen, aber jede Untersuchung ist genau einem Arzt zugewiesen.**
3. **Ein Zimmer hat eine maximale Kapazität basierend auf seiner Größe.**
4. **Eine Untersuchung hat immer einen klar definierten Start- und Endzeitpunkt.**
5. **Die Entlassung eines Patienten darf nicht vor seinem Aufnahmedatum liegen.**


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
## 4 Refactoring
## 6 Entwurfsmuster