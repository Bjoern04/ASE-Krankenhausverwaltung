# Schriftliche Dokumentation 

## 0 Musterbefehle
Dieses Kapitel dient zur Darstellung der Befehlsstruktur. Es werden Beispiele für die möglichen Befehle gegeben. Anhand dieser kann das Programm auch ausprobiert werden. Zudem waren sie die Grundlage für das manuelle Testen der Anwendung. Die bestehenden JSOn Dateien mitherunterladen und weder verschieben noch löschen.<br>
Die Befehle können zum Testen in dieser Reihenfolge ausgeführt werden.

### 0.1 Create Doctor

Syntax:
- create doctor => \<vorname>, \<nachname>, \<straße>, \<hausnummer>, \<plz>, \<ort>, \<geburtsdatum>, \<telefonnummer>, \<email>, \[\<untersuchungsart>, \<untersuchungsart>]

Beispiel:
- create doctor => Max, Musterdoktor, Teststraße, 1, 12345, Musterstadt, 09.01.1999, 98457474, doctor@gmail.com, \[GeneralExamination, Biopsy]

### 0.2 Create Patient
Syntax:
- create patient => \<vorname>, \<nachname>, \<straße>, \<hausnummer>, \<plz>, \<ort>, \<geburtsdatum>, \<telefonnummer>, \<email>

Beispiel:
- create patient => Max, Musterpatient, Teststraße, 1, 12345, Musterstadt, 09.01.1999, 98457474, patient@gmail.com 

### 0.3 Create Room
Syntax:
- create room => \<gebäude>, \<stockwerk>, \<zimmernummer>, \<größe>

Beispiel:
- create room => 1, 1, 101, 2

### 0.4 Create Examination
Syntax:
- create examination => \<untersuchungsart>, \<startzeitpunkt>, \<endzeitpunkt>, \<patientId>, \<arztId>

Beispiel:
- create examination => Biopsy, 08.09.2026T16:17, 08.09.2026T16:20, e70f12d9-617a-4161-8fb2-b2a47d33dd66, c197a445-eab2-4662-847f-5cdb2191c151 

### 0.5 Create Assignment
Syntax:
- create assignment => \<zimmerId>, \<patientId>, \<aufnahmedatum>, \<entlassungsdatum>

Beispiel:
- create assignment => 6f36ca8c-d248-41d2-a550-542114307de0, e70f12d9-617a-4161-8fb2-b2a47d33dd66, 08.09.2026, 08.10.2026

### 0.6 Read Doctor
Syntax:
- read doctor => \<doctorId>

Beispiel:
- read doctor => c197a445-eab2-4662-847f-5cdb2191c151

### 0.7 Read Patient
Syntax:
- read patient => \<patientId>

Beispiel:
- read patient => e70f12d9-617a-4161-8fb2-b2a47d33dd66

### 0.8 Read Room
Syntax:
- read room => \<roomId>

Beispiel:
- read room => 6f36ca8c-d248-41d2-a550-542114307de0

### 0.9 Read Examination
Syntax:
- read examination => \<examinationId>

Beispiel:
- read examination => f831fc85-5dde-4a88-92e7-fa819a2309fd

### 0.10 Read Assignment
Syntax:
- read assignment => \<assignmentId>


### 0.11 Read DoctorExaminationPlan
Syntax:
- read doctorexaminationplan => \<doctorId>, \<sortByDate>

Beispiel:
- read doctorexaminationplan => c197a445-eab2-4662-847f-5cdb2191c151, false



## 1 Domain Driven Design 

### 1.1 Analyse der Ubiquitous Language
Im Rahmen des Domain-Driven Designs (DDD) ist die Ubiquitous Language ein essenzielles Konzept, das sicherstellt, dass alle Beteiligten – Entwickler, Domänenexperten und andere Stakeholder
– eine einheitliche und präzise Sprache verwenden. Diese Sprache spiegelt die Geschäftslogik wider und dient als Grundlage für die Modellierung der Software. Durch das definieren der verwendeten Begriffe und dem Hinterlegen von Beschreibung und Definitionen für diese, werden Missverständnisse vermieden und eine klare
Kommunikation zwischen den Beteiligten ermöglicht. Diese könnten ansonsten, zum Beispiel durch Bezeichnungen mit unterschiedlichen Bedeutungsbereichen zu Problemen bei der Kommunikation und fehlerhaften Umsetzungen führen. Wichtig ist ebenfalls die bereits bestehenden Begriffe der Fachsprache zu nutzen und keine neuen zu erfinden. Dies trägt ebenfalls zu einer unter den Stakeholdern verständlichen Kommunikation und Sprache bei.<br>
All diese Kriterien wurden beim Verwenden der nachfolgenden Bezeichnungen beachtet. So wurden beispielsweise bereits bekannte Begriffe der Domäne wie "Patient" und
"Arzt" verwendet und keine eigen erstellten. Ebenfalls sind uneindeutige Begriffe der Domäne weiter erläutert. So ist zum Beispiel bei der "Untersuchungsart" erläutert, dass 
sich diese auf die Untersuchung eines Patienten von einem Arzt bezieht. Sie stellt keine Untersuchungen des Krankenhauses durch Behörden oder Gesundheitsaufsichten dar.

#### 1.1.1 Fachliche Begriffe und ihre Bedeutung
Die Analyse der Ubiquitous Language basiert auf den zentralen Entitäten und Werten in der Problemstellung eines Krankenhausmanagementsystems. Dabei werden in Klammern die im Quellcode verwendeten englischen Übersetzungen der Begriffe angegeben.<br>
- **Patient (Patient)**: Eine Person, die in dem Krankenhaus medizinisch behandelt wird. Ein Patient besitzt Attribute wie **Name**, **Adresse**, **Geburtsdatum** und **Kontaktinformationen**. Ein Patient kann mehreren **Untersuchungen** unterzogen werden.
- **Arzt (Doctor)**: Ein medizinischer Fachmann, der Patienten in einer bestimmten Untersuchung behandelt. Ärzte haben Attribute wie **Name**, **Adresse**, **Geburtsdatum** und eine Liste von **Untersuchungen**, die sie durchführen.
- **Untersuchung (Examination)**: Ein medizinisches Verfahren, das einem Patienten zugeordnet ist und von einem Arzt durchgeführt wird. Es umfasst die beteiligten Personen, eine **Untersuchungsart**, einen **Startzeitpunkt** und einen **Endzeitpunkt**.
- **Zimmer (Room)**: Ein physischer Raum im Krankenhaus, in welchem Patienten während ihres Aufenthalts untergebracht sind. Ein Zimmer enthält eine **Zimmergröße**, eine **Zimmeradresse** und **Belegungen**. Manche Zimmer bieten Platz für mehrere Patienten, weshalb ein Zimmer mehrere **Belegungen** haben kann.
- **Belegung (Assignment)**: Die Zuweisung eines Patienten zu einem Zimmer für einen bestimmten Zeitraum. Eine Belegung enthält Daten wie **Aufnahmedatum** und **Entlassungsdatum**. Dort ist hinterlegt, welcher Patient in welchem Zimmer untergebracht ist.

- **Adresse (Address)**: Ein Wertobjekt, das den Wohnort eines Patienten oder Arztes beschreibt, bestehend aus **Straße**, **Hausnummer**, **PLZ** und dem **Ort**.
- **Zimmeradresse (RoomAddress)**: Die Adressierung eines Zimmers des Krankenhauses innerhalb von diesem. Dabei ist die Anschrift des Krankenhauses nicht Teil davon. Die Adressierung des Zimmers erfolgt über das Gebäude des Krankenhauses, das Stockwerk innerhalb dieses Gebäudes und der Nummer, die das Zimmer hat.
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
10. Der Arzt, der für eine spezielle Untersuchung verantwortlich ist, kann nur geändert werden, solange die Untersuchung noch nicht begonnen hat und nicht abgeschlossen ist. Der neue Arzt muss ebenfalls für die Untersuchung qualifiziert sein.

## 1.2 Analyse und Begründung der verwendeten Muster
**Address** ist als Value Object implementiert, weil es keine eigene Identität besitzt und vollständig durch seine Werte Straße, Hausnummer, PLZ und Ort beschrieben wird. Zwei Address-Objekte mit denselben Werten sind gleich und austauschbar. Wenn alle ihre Attribute gleich sind, so sind auch die beiden Objekte gleich. Sie geben die gleiche Adresse an. Zudem hat die Adresse keinen Lebenszyklus und wird sich nicht verändern. Sie ist unveränderlich (immutable) und wird nur zur Beschreibung der Entität Patient oder Arzt verwendet. Falls diese eine neue Adresse benötigen, wird ein neues Address-Objekt erstellt.
<br><br>
**Patient** ist als Entity implementiert, weil jedes Patient-Objekt eine eigene Identität (UUID) besitzt, die es eindeutig unterscheidet, unabhängig von seinen Attributwerten wie Name oder Adresse. Zwei Patienten können die gleichen Namen haben (andere Attribute null), aber sie sind dennoch unterschiedliche Personen/ Entitäten, solange ihre UUIDs verschieden sind. Patienten haben zudem einen Lebenszyklus, da sich ihre Eigenschaften während ihres Aufenthaltes im Krankenhaus ändern können. Sie können in andere Zimmer verlegt werden oder neue Untersuchungen zugewiesen bekommen. Die Eigenschaften eines Patienten müssen sich ändern können. Es soll bei Änderungen kein neues Patienten-Objekt erstellt werden, sondern das Bestehende aktualisiert werden.
<br><br>
**Room** ist als Aggregat implementiert, weil es die Entitäten und Value Objects, welche zu einem Raum gehören, zu einer gemeinsam verwalteten Einheit gruppiert. Dadurch kann die Komplexität der Beziehungen zwischen Objekten reduziert werden. Durch die Einteilung in Aggregate können komplexe Objekt-Graphen vermieden und eindeutige Stellen zur Prüfung von domänenobjekt-übergreifender Invarianten erstellt werden. Das Aggregate Root des Room-Aggregates ist die Room-Entität. Nur über sie kann auf das Aggregat zugegriffen werden. Dies ergibt Sinn, da auf das Value Object RoomAddress, welches Teil des Aggregates ist, nur von einem Room heraus drauf zugegriffen werden muss. Durch das Aggregate Root Room hat das Aggregat eine zentrale Stelle zur Durchsetzung von unveränderlichen Regeln (Invarianten) über mehrere Domänenobjekte hinweg.
<br><br>
**PatientRepository** ist als Repository implementiert, weil es als Interface die zentrale Schnittstelle zur Verwaltung und zum Zugriff auf Patient-Entitäten bereitstellt. Es kapselt die Persistenzlogik und trennt die Geschäftslogik von der konkreten Speicherimplementierung. Dadurch kann die Anwendung flexibel auf verschiedene Speicherlösungen (z.B. JSON-Dateien, Datenbanken) zugreifen, ohne die Domain- oder Application-Schicht anpassen zu müssen. Ein Beispiel ist die Methode findPatientById(UUID id), die es ermöglicht, einen Patienten anhand seiner eindeutigen ID aus einer beliebigen Datenquelle zu laden. Die konkrete Implementierung, wie z.B. in PatientStorage, übernimmt die technische Umsetzung, während das Interface die fachliche Schnittstelle definiert.
<br><br>
**DoctorExaminationPlanService** ist als Domain Service implementiert, weil er fachliche Logik kapselt, die sich nicht eindeutig einer einzelnen Entität zuordnen lässt, sondern mehrere Aggregate (Doctor, Examination, Patient) betrifft. Im Source Code (DoctorExaminationPlanService) koordiniert der Service das Zusammenspiel zwischen Arzt-, Patienten- und Untersuchungs-Repository, um einen Untersuchungsplan für einen bestimmten Arzt zu erstellen. Die Methode createDoctorExaminationPlan(UUID doctorId) prüft z.\ B., ob der Arzt existiert, lädt alle Untersuchungen und ergänzt diese um Patientennamen. Diese domänenspezifische Logik ist nicht Teil einer einzelnen Entität, sondern wird als eigenständiger Service abgebildet, um die Trennung von Verantwortlichkeiten und die Wiederverwendbarkeit zu gewährleisten.
<br><br>


## 2 Clean Architecture
### 2.1 Grundlagen Clean Architecture
Die Architektur der Krankenhausverwaltungsanwendung basiert auf den Prinzipien der Clean Architecture. Dieses Architekturmuster fördert eine klare Trennung von Verantwortlichkeiten, eine hohe Testbarkeit und eine geringe Kopplung zwischen den Schichten. Dadurch wird eine flexible, wartbare und erweiterbare Softwarelösung gewährleistet.

### 2.2 Schichten der Anwendung
Die Anwendung besteht aus drei aktiv verwendeten Schichten, die jeweils unterschiedliche Verantwortlichkeiten haben. Diese Schichten sind:

1. Domain-Schicht
2. Application-Schicht
3. Plugin-Schicht
<br>
Im Nachfolgenden werden die einzelnen Schichten erklärt und erläutert, warum diese implementiert wurde und welche Aufgaben sie jeweils erfüllen. 

### 2.2.1 Domain-Schicht
Die Domain-Schicht enthält die zentrale und organisationsweit geltende Geschäftslogik und definiert die grundlegenden Geschäftsobjekte (Entities) sowie deren Verhalten. Sie kapselt lediglich die in einer Organisation geltenden Regeln. Dabei sollen diese von äußeren Elementen wie Datenbanken oder APIs aber auch der UI unabhängig sein. APIs und Datenbankanbindungen sollen sich ändern können, ohne dass dadurch die Bestandteile der Domain-Schicht beeinflusst werden. So können z.B. in Zukunft die JSON-Dateien zur Speicherung der Daten durch eine Datenbank ersetzt werden, ohne dass dadurch die Geschäftslogik der Krankenhausverwaltung beeinflusst wird.<br><br>

#### Bestandteile
- **Entities**: Die Entities Patient, Arzt, Zimmer, Untersuchung und Belegung repräsentieren die zentralen Domänenobjekte, welche zur Abbildung und Modellierung der Geschäftslogik benötigt werden. Diese Objekte können sich im Ablauf des Programms verändern und haben einen Lebenszyklus.
- **Value Objects**: Auch die Value Objects sind in der Domain-Schicht enthalten. Sie sind unveränderliche Werte wie Name, Adresse, etc., welche zur Beschreibung der Entities verwendet werden. 
- **Aggregate**: Die Aggregate gruppieren Entitäten und Value Objects zu zusammengehörigen Einheiten. Dadurch kann die Komplexität der Beziehungen zwischen Objekten reduziert werden. Zudem kann über das Aggregate Root eine zentrale Stelle zur Prüfung von domänenobjekt-übergreifender Invarianten erstellt werden, welches ebenfalls die Komplexität reduziert und die korrekte Einhaltung aller Regeln fördert.
- **Repositories**: Die Repositories definieren Interfaces für die Aggregate Roots, welche in äußeren Schichten implementiert werden können, um auf Daten z.B. aus einer Datenbank zuzugreifen.
- **Domain Services**: Der letzte Bestandteil sind die Domain Services, welche Geschäftslogik beinhalten an welcher mehrere unterschiedliche Entities beteiligt sind.<br><br>

#### Warum wurde diese Schicht gewählt?
Die Domain-Schicht wurde ausgewählt, um die organisationsweite Geschäftslogik und die dazu gehörenden Geschäftsobjekte abzubilden. Innerhalb der Schicht können die einzelnen Entitäten, Value Objects, Aggregate, etc. erstellt und organisiert werden, um die Regeln der Organisation abzubilden. Die Schicht soll sie vor äußeren Einflüssen schützen und eine klare Trennung von anderen Schichten gewährleisten. Dadurch wird sichergestellt, dass die Geschäftslogik unabhängig von der Implementierung des Speichersystems mit den JSON-Dateien oder der Benutzeroberfläche/ Konsole bleibt. Dadurch können auch im Nachhinein noch weitere Änderungen an Benutzeroberfläche vorgenommen werden, ohne dabei Probleme mit den Geschäftsobjekten oder den geltenden Regeln zu verursachen. Diese bleiben korrekt erhalten. So kann zum Beispiel erst eine korrekt funktionierende Konsolenanwendung entwickelt werden und diese in Zukunft bei Bedarf in eine Anwendung mit einer richtigen UI umgewandelt werden. Dabei bleiben die Geschäftsobjekte und deren Regeln unverändert. <br>
Zudem kann auch das Speichersystem überarbeitet werden. Falls in Zukunft eine größere Anzahl an Patienten und Doktoren vorhanden ist und diese weiterhin in der Verwaltung hinterlegt werden müssen, kann einfach auf eine SQL-Datenbank umgestiegen werden. Diese kann dann über die Repositories angesprochen werden. Die Domain-Schicht bleibt dabei unverändert und kann weiterhin genutzt werden. Nur die Implementierung der Repositories muss verändert werden. Dies ist zudem sinnvoll, da Änderungen in der Domain-Schicht bzw. der Geschäftsregeln seltener vorkommen und die häufiger vorkommenden Änderungen in der Darstellung und Anbindung weiterer Ressourcen die Geschäftslogik nicht beeinflussen. Dies reduziert nicht nur den Arbeitsaufwand bei dem Hinzufügen von Neuerungen, sondern ermöglicht auch, dass diese vor unerwünschten Änderungen geschützt ist.<br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Wie bereits erwähnt bildet die Domain-Schicht die organisationsweite Geschäftslogik und die dazu gehörenden Geschäftsobjekte ab. In diesem Programm bildet sie die zentralen Bestandteile, wie Patienten, Ärzte, Zimmer, Untersuchungen und Belegungen der Krankenhausverwaltung ab. Auch die dazu gehörenden Value Objects, welche die Entities beschreiben, sind in dieser Schicht enthalten. Ebenfalls enthält die Domain-Schicht die Repository Interfaces, welche in der Plugin-Schicht implementiert werden. Es sind auch einzelne util-Klassen enthalten, die einzelne Aufgaben und Regeln für die Entitäten übernehmen, welche jedoch nicht den Anforderungen eines Domain Services entsprechen. Sie ermöglichen es zum Beispiel, Nummern zu validieren, welche für Kontaktdaten oder Adressen verwendet werden (NumberValidator).

### 2.2.2 Application-Schicht

#### Bestandteile
- **Use Cases**: Die Use Cases sind die Anwendungsfälle, die die Interaktion zwischen der Benutzeroberfläche und der Domain-Schicht definieren. Sie enthalten die Logik zur Verarbeitung von Benutzeranfragen und zur Koordination der Interaktion mit den Entitäten und Repositories.<br><br>

#### Warum wurde diese Schicht gewählt?
Die Application-Schicht wurde gewählt, um die Anwendungsfälle der Krankenhausverwaltung zu definieren und die Interaktion zwischen der Benutzeroberfläche und der Domain-Schicht zu koordinieren. Sie stellt sicher, dass die Geschäftslogik korrekt angewendet wird und die Benutzeranfragen ordnungsgemäß verarbeitet werden. Diese Schicht fungiert als Vermittler zwischen der Benutzeroberfläche und der Domain-Schicht und sorgt dafür, dass die Geschäftsregeln eingehalten werden. Sie ruft die für einen Use Case relevanten Methoden und Services der Domain-Schicht auf und verarbeitet die Ergebnisse, um sie an die Benutzeroberfläche zurückzugeben. Dadurch wird eine klare Trennung zwischen der Benutzeroberfläche und der Geschäftslogik erreicht, was die Erweiterbarkeit und vor allem die Wartbarkeit der Anwendung verbessert. <br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Die Application-Schicht enthält die einzelnen Use Cases, welche für eine Krankenhausveraltung relevant sind. Es sind alle Use Cases zum Auslesen und Erstellen von Patienten, Ärzten, Zimmern, Belegungen und Untersuchungen enthalten. Diese sind in dieser sehr schlanken Anwendung als Use Cases implementiert, da sie die Hauptfunktionalität der Anwendung abbliden. Die Application-Schicht ist dafür verantwortlich, die Benutzeranfragen zu verarbeiten und die entsprechenden Methoden der Domain-Schicht aufzurufen. Sie stellt sicher, dass die Geschäftslogik korrekt angewendet wird und die Benutzeranfragen ordnungsgemäß verarbeitet werden. Zum Beispiel wird bei der Erstellung des DoctorExaminationPlan der entsprechende Domain Service aufgerufen. Dieser überprüft, ob der Arzt existiert und ob relevante Untersuchungen vorhanden sind. Die zurückgegebenen Daten können dann im Use Case, je nach Nutzereingabe weiterverarbeitet und sortiert werden. Danach werden die Informationen an die Plugin-Schicht gegeben, um sie dem Nutzer darzustellen. Dabei ist die Darstellungsform auf der UI für den Use Case nicht relevant (Decoupling).<br><br>

### 2.2.3 Plugin-Schicht

#### Bestandteile
- **Repositories**: Die Repositories sind die Implementierungen der Repository-Interfaces aus der Domain-Schicht. Sie sind dafür verantwortlich, die Daten aus den JSON-Dateien zu laden und zu speichern. 
- **UI**: Die Benutzeroberfläche (UI) ist die Implementierung der Benutzerinteraktion. Sie ermöglicht es den Benutzern, mit der Anwendung zu interagieren und die verschiedenen Funktionen der Krankenhausverwaltung zu nutzen. In diesem Fall ist die UI eine Konsolenanwendung.
- **Parser**: Die Parser sind dafür verantwortlich, die Benutzereingaben zu verarbeiten und diese den richtigen Befehlen zuzuordnen, damit diese die Eingaben weiter verarbeiten und weiterleiten können.
- **Commands**: Die Commands sind die einzelnen Befehle, die von der Benutzeroberfläche an die Anwendung gesendet werden. Sie repräsentieren die verschiedenen Aktionen, die der Benutzer in der Anwendung durchführen kann, wie z.B. das Erstellen eines neuen Patienten oder das Zuweisen eines Arztes zu einer Untersuchung. Sie rufen die dazu passenden Use Cases auf. Davor versuchen sie die einzelnen Eingabewerte des Nutzers mithilfe des Parsers und seiner Methoden in die von den Use Cases benötigten Objekte zu parsen.
- **Serializer**: Der Serializer ist dafür verantwortlich, die Daten in das JSON-Format zu konvertieren und diese in den JSON-Dateien zu speichern. Er sorgt dafür, dass die Daten korrekt formatiert sind und in der richtigen Struktur gespeichert werden. Er wird von den Methoden der implementierten Repositories in unterschiedlichen Formen aufgerufen. Dadurch muss nicht in jedem Repository die gleiche triviale JSON-Serialisierung implementiert werden.

#### Warum wurde diese Schicht gewählt?
Die Plugin-Schicht wurde gewählt, um die Implementierung der Benutzeroberfläche und die Interaktion mit den Benutzern zu kapseln. Sie enthält die Repositories, die für den Zugriff auf die Daten verantwortlich sind, sowie die Benutzeroberfläche, die es den Benutzern ermöglicht, mit der Anwendung zu interagieren. Diese Schicht ist von der Domain-Schicht und der Application-Schicht unabhängig und kann leicht geändert oder ersetzt werden, ohne dass dies Auswirkungen auf die Geschäftslogik hat. Dadurch wird eine klare Trennung zwischen der Benutzeroberfläche und der Geschäftslogik erreicht, was die Erweiterbarkeit und Wartbarkeit der Anwendung verbessert. Zudem können in dieser Schicht auch andere Implementierungen der Repositories hinterlegt werden. So könnte zum Beispiel in Zukunft eine SQL-Datenbank anstelle von JSON-Dateien verwendet werden. Auch hier bleibt die Domain- und Application-Schicht unverändert. Lediglich die Implementierung der Repositories und der verwendete JSON-Serializer müssen angepasst werden.<br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Die Plugin-Schicht enthält die Implementierung der Benutzeroberfläche, die es den Benutzern ermöglicht, mit der Anwendung zu interagieren. Sie ist dafür verantwortlich, die Benutzereingaben zu verarbeiten und diese an die entsprechenden Use Cases weiterzuleiten. Zudem enthält sie die Repositories, die für den Zugriff auf die Daten verantwortlich sind. Diese laden und speichern die Daten in den JSON-Dateien. Die Plugin-Schicht ist auch dafür verantwortlich, die Ergebnisse der Use Cases an die Benutzeroberfläche zurückzugeben und diese dem Benutzer korrekt anzuzeigen.<br> Beispielsweise wird die Benutzeroberfläche erzeugt und auf eine Eingabe des Benutzers gewartet. Wenn der Benutzer einen Befehl eingegeben hat, wird dieser mithilfe des Parsers geparst. Anschließend wird der korrekte Command aufgerufen, welcher die Fehler des Parsers abfängt und den korrekten Use Case mitsamt der benötigten Parameter aufruft. Danach nimmt er die für die Ausgaben gedachten Daten zurück und gibt sie an die UI weiter, sodass der Nutzer informiert wird. 

## 3 Programming Principles
Es gibt eine Vielzahl an Prinzipien, die beim Programmieren beachtet werden sollten. Diese helfen dabei, den Code lesbar, wartbar und erweiterbar zu gestalten. Im Folgenden werden einige der in diesem Projekt verwendeten Prinzipien erläutert und an in der Anwendung verwendeten Implementationen verdeutlicht.

### 3.1 Single Responsibility Principle (SRP)
Das Single Responsibility Principle ist Teil der SOLID-Prinzipien und besagt, dass eine Klasse nur eine klar abgegrenzte Verantwortung haben sollte. Dies bedeutet, dass eine Klasse nur für eine bestimmte Funktionalität zuständig ist und nicht mehrere Verantwortlichkeiten übernimmt. Dadurch wird die Wartbarkeit und Testbarkeit des Codes verbessert, da Änderungen an einer Klasse keine Auswirkungen auf andere Klassen haben sollten.<br>

 Das Prinzip wird zum Beispiel in der Klasse CreateDoctor berücksichtigt, weil sie ausschließlich für die Erstellung und das Speichern eines neuen Arztes im Repository zuständig ist. Alle anderen Aufgaben, wie die Validierung der Eingaben oder die Verwaltung der Arzt-Entität, sind in anderen Klassen gekapselt. Somit werden die einzelnen Aufgaben klar auf die unterschiedlichen Klassen aufgeteilt. Dadurch bleibt die Klasse übersichtlich und leicht wartbar. Auch wird sie durch Änderungen an den anderen Klassen weniger beeinflusst.<br>

Das Prinzip ist ebenfalls bei den einzelnen Commands angewandt. Diese sind nur für einen bestimmten Anwendungsfall zuständig und übernehmen keine weiteren Aufgaben. So ist zum Beispiel der CreatePatientCommand nur dafür zuständig, Patienten zu erstellen und zu bearbeiten. Er ist nicht für die Verwaltung von Ärzten oder Zimmern verantwortlich. Das ermöglicht es neue Commands schnell hinzuzufügen, da sie nicht von bestehenden abhängig sind. Zudem können die bestehenden Commands unabhängig voneinander getestet und erweitert werden, sodass die Arbeit des Entwicklers er erleichtert und beschleunigt wird. <br>

### 3.2 Open/Closed Principle (OCP)
Das Open/Closed Principle ist ein weiteres Prinzip der SOLID-Prinzipien und besagt, dass Software-Entitäten (Klassen, Module, Funktionen, etc.) offen für Erweiterungen, aber geschlossen für Änderungen sein sollten. Dies bedeutet, dass bestehender Code nicht verändert werden sollte, um neue Funktionalitäten hinzuzufügen. Stattdessen sollten neue Funktionalitäten durch Erweiterungen des bestehenden Codes erreicht werden.<br>

Dieses Prinzip ist durch die Repositories implementiert. Die Repositories sind als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch können neue Repositories hinzugefügt werden, ohne den bestehenden Quellcode zu ändern. Diese Interfaces sind offen für Erweiterungen, da sie in neuen Repositories implementiert werden können. Gleichzeitig sind sie geschlossen für Änderungen, da das Interface und bestehende Repositories nicht verändert werden müssen, um neue Funktionalitäten hinzuzufügen. <br>So kann zum Beispiel ein neues Repository für eine SQL-Datenbank hinzugefügt werden, welches das entsprechende Interface implementiert. Dabei müssen weder an dem Interface noch an dem bestehenden Repository für JSON-Dateien Änderungen vorgenommen werden. Dies ermöglicht eine einfache Erweiterung der Anwendung, ohne dass bestehende Funktionalitäten beeinträchtigt werden. Dabei wird der existierende Quellcode nicht verändert und es entstehen zwei unterschiedliche Möglichkeiten zur Persistierung, welche von der Anwendung genutzt werden können.<br>

### 3.3 Dependency Inversion Principle (DIP)
Das Dependency Inversion Principle ist ein weiteres Prinzip der SOLID-Prinzipien und besagt, dass hochrangige Module nicht von niederrangigen Modulen abhängen sollten. Beide sollten von Abstraktionen abhängen. Dies bedeutet, dass die Abhängigkeiten zwischen den Modulen durch Interfaces oder abstrakte Klassen definiert werden sollten, anstatt durch konkrete Implementierungen.<br>

Im Programm sollen die inneren Schichten nicht von äußeren Schichten abhängen. Dies bedeutet, dass die Domain- und Application-Schicht nicht von der Plugin-Schicht abhängen sollten. Stattdessen sollten sie nur von Abstraktionen (Interfaces) abhängen. Dadurch wird die Kopplung zwischen den Schichten reduziert und die Wartbarkeit und Testbarkeit des Codes verbessert.<br>

Das Prinzip ist in der Anwendung durch die Verwendung von Interfaces in den Repositories umgesetzt. Die Repositories sind als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch können die inneren Schichten (Domain- und Application-Schicht) von den Abstraktionen (Interfaces) abhängen, anstatt von konkreten Implementierungen. Die Use Cases in der Application Schicht sind nicht von der genauen Implementierung in der Plugin-Schicht abhängig, sondern nur von der Abstraktion durch das Interface. Zum Beispiel kann der Read Patient Use Case, der ein PatientRepository benötigt, mit jedem Repository arbeiten, das das PatientRepository-Interface implementiert. Dadurch wird die Kopplung zwischen den Schichten reduziert. So kann ein Patient unabhängig von der genauen Persistenzschicht und ihrer Implementierung gelesen werden. Dies trifft auch auf die anderen Use Cases zu. <br> 

Die Beachtung dieses Prinzips ermöglicht eine einfache Erweiterung und Änderung der Anwendung, da neue Implementierungen der Repositories hinzugefügt werden können, ohne dass Änderungen an den inneren Schichten erforderlich sind. Zudem wird auch hier die Testbarkeit des Codes verbessert, eine bessere Wiederverwendbarkeit und flexiblere Zusammenarbeit ermöglicht und zudem klare Schnittstellen definiert.<br>

### 3.4 DRY (Don't Repeat Yourself)
Das DRY-Prinzip besagt, dass Informationen und Logik nicht dupliziert werden sollten. Stattdessen sollten sie in einer einzigen Quelle gespeichert werden. Dies reduziert Redundanz und Inkonsistenzen und verbessert die Wartbarkeit des Codes.<br>

Das DRY-Prinzip ist in der Anwendung durch die Verwendung von gemeinsamen Methoden und Klassen umgesetzt. Zum Beispiel gibt es in der Serializer-Klasse eine Methode, die für alle Repositories verwendet wird, um die Daten in das JSON-Format zu konvertieren. Diese Methode wird in allen Repositories verwendet, die JSON-Daten speichern müssen. Dadurch wird sichergestellt, dass die Serialisierung an einer einzigen Stelle implementiert ist und nicht in jedem Repository dupliziert werden muss. Dies reduziert Redundanz und Inkonsistenzen beim Serialisieren. Zudem können Änderungen schneller hinzugefügt werden. Wenn eine Änderung am Serialisierungsvorgang durchgeführt werden soll, muss diese nur an einer einzigen Stelle implementiert und getestet werden. Sie muss nicht in jedem Repository einzeln implementiert werden.<br>

Weitere Beispiele von der Verwendung des DRY-Prinzips ist die Parser-Klasse und die Commands. Diese verwenden ebenfalls gemeinsame Methoden, um die Eingaben des Benutzers zu verarbeiten und diese an die entsprechenden Use Cases weiterzuleiten. Dadurch wird sichergestellt, dass die Logik an einer einzigen Stelle implementiert ist und nicht in jedem Command dupliziert werden muss. Dies reduziert Redundanz und Inkonsistenzen beim Verarbeiten der Benutzereingaben. Zudem können Änderungen schneller hinzugefügt werden. Wenn eine Änderung am Verarbeitungsprozess durchgeführt werden soll, muss diese nur an einer einzigen Stelle implementiert und getestet werden. Sie muss nicht in jedem Command einzeln umgesetzt werden.<br>

### 3.5 GRASP - Low Coupling
Das Low Coupling-Prinzip besagt, dass die Kopplung zwischen den Klassen und Modulen so gering wie möglich gehalten werden sollte. Dies bedeutet, dass Klassen und Module unabhängig voneinander arbeiten sollten und nur über klar definierte Schnittstellen miteinander kommunizieren sollten. Dadurch wird die Wartbarkeit und Testbarkeit des Codes verbessert, da unerwünschte Auswirkungen auf andere Klassen vermieden werden.<br>

Das Low Coupling-Prinzip ist in der Anwendung durch die Verwendung von Interfaces und Abstraktionen umgesetzt. Zum Beispiel sind die Repositories als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch hängen die inneren Schichten nur von den Interfaces ab, anstatt von konkreten Implementierungen. Dies reduziert die Kopplung zwischen den Schichten und wurde umgesetzt, um eine gute Testbarkeit, eine einfache Verständlichkeit, eine erhöhte Wiederverwendbarkeit und eine leichte Anpassbarkeit zu gewährleisten. Es ermöglicht wie vorhin schon bei anderen Prinzipien erwähnt, dass einfache Austauschen von Zugriffen auf die Persistenzschicht und damit auch den Austausch der Persistenzschicht selbst. So kann einfach von dem Speichern in JSON-Dateien auf eine SQL-Datenbank gewechselt werden, ohne dass in der Application Schicht große Änderungen vorgenommen werden müssen. Wie genau die Umsetzung aussieht, ist für die Use Cases aufgrund der losen Kopplung nicht relevant.<br>


## 4 Refactoring
### 4.1 Code Smells
Code Smells sind die Bezeichnung für eine verdächtige und potentiell verbesserungswürdige Codestelle.

#### Long Method im Input Parser
Die Methode parseCommand() im Input Parser ist eine lange Methode, die mehrere Aufgaben gleichzeitig ausführt. Sie ist dafür verantwortlich, die Eingabe aufzuteilen, einfache Befehle wie "exit" und "help" zu behandeln, die Benutzereingaben zu verarbeiten und die entsprechenden Befehlsobjekte zu erstellen. Zudem enthielt sie zu Beginn weitere Elemente zum Parsen, welche nun bereits in der parseArgument() Methode ausgelagert wurden. Diese lange parseCommand() Methode führt zu einer hohen Komplexität und macht den Code schwer verständlich und wartbar (seit Auslagerung von Teilen in parseArgument() Methode aber etwas leichter). Dazu trägt zudem das überaus lange switch statement bei, welches bei Erweiterung des Programms mit jenem neuen Command wächst. Jede case-Anweisung innerhalb des switch-Blocks ist für die Instanziierung eines spezifischen Command-Objekts zuständig<br>
 - Geringe Lesbarkeit: Durch die genannten Gründe kann es zu einer geringeren Lesbarkeit kommen, da die Methode schwer zu überblicken und zu verstehen ist. Ein Entwickler muss den gesamten Codeblock durchgehen, um die Logik für einen einzelnen Befehl zu erfassen.
 - Erhöhte Komplexität: Die Vermischung der Logik zur Befehlserkennung und der Instanziierung verschiedener Command-Typen erhöht die kognitive Last und die Komplexität der Methode.
 - Verletzung des Single Responsibility Principles (SRP): Die parseCommand-Methode hat mehrere Verantwortlichkeiten: das Parsen des Eingabestrings, die Identifizierung des Befehlsschlüsselworts und die Erstellung des entsprechenden Command-Objekts. Dies widerspricht dem SRP und den daraus folgenden Vorteilen, welches besagt, dass eine Klasse oder Methode nur einen Grund zur Änderung haben sollte.

#### Switch Statement im Input Parser
Aufbauen auf dem vorherigen Code Smell kann auch detailliert auf das switch statement eingegangen werden. Dieses ist für die Instanziierung der einzelnen Command-Objekte zuständig. Es ist jedoch sehr lang und unübersichtlich, da es für jeden Befehl einen eigenen case-Block enthält. Dies führt zu einer hohen Komplexität und macht den Code schwer verständlich und wartbar. Dieses switch statement ist ein eigener Code Smell an sich und trägt dabei gleichzeitig zu dem vorherigen der Long Method bei.

#### Dead Code im Input Parser
Im Input Parser gibt es die Methode parseUuidList(). Diese Methode wird im gesamten Projekt nicht aufgerufen. Sie ist daher ein Code Smell vom Typ Dead Code. Dead Code ist Code, der nicht verwendet wird und daher entfernt werden kann. Diese Methode wurde nur am Anfang des Projektes verwendet und wird mittlerweile nicht mehr benötigt. Sie kann daher entfernt werden, um den Code sauberer und wartbarer zu gestalten. Dead Code kann auch zu Verwirrung führen, da andere Entwickler möglicherweise denken, dass der Code noch benötigt wird. Durch Beheben dieses Code Smells wird zudem die Länge des Quellcodes reduziert.

#### Duplicate Code im ShowDoctorExaminationPlanCommand
Im vorliegenden Code findet sich der Code Smell "Duplicated Code". Dieser manifestiert sich in der ShowDoctorExaminationPlanCommand Klasse, wo die Formatierung der Ausgabe für jedes ExaminationWithPatientName Objekt manuell in der execute() Methode erfolgt. Anstatt dessen existiert in der Klasse ExaminationWithPatientName bereits eine toString() Methode, die genau diese Formatierung implementiert.
Die manuelle Formatierung wiederholt die Logik, die bereits in der toString() Methode der ExaminationWithPatientName Klasse definiert ist. Dies führt zu Redundanz, erschwert die Wartung und erhöht das Risiko von Inkonsistenzen, falls sich das Ausgabeformat zukünftig ändern sollte. Durch die Nutzung der vorhandenen toString() Methode könnte der Code in ShowDoctorExaminationPlanCommand vereinfacht und die Wartbarkeit verbessert werden.

### 4.2 Refactoring
Definition Refactoring: Eine Änderung an den Internas einer Software, um diese verständlicher und änderbarer zu gestalten, ohne ihr (sichtbares) Verhalten zu ändern.

#### Refactoring der Long Method mit Extract Method 
Die vorliegende parseCommand() Methode weist Charakteristika des Code Smells "Long Method" auf. Ihre übermäßige Länge und die damit verbundene Vielzahl an Verantwortlichkeiten von der initialen Eingabeaufteilung und der Behandlung einfacher Sonderfälle bis hin zur Identifizierung von Befehlsschlüsselwörtern und der Instanziierung spezifischer Befehlsobjekte beeinträchtigen die Lesbarkeit und Wartbarkeit de Codes. Eine solche Struktur erschwert das Verständnis der Methode als Ganzes und erhöht das Risiko unbeabsichtigter Seiteneffekte bei Modifikationen.
Um diese Problematik zu adressieren, bietet sich das Refactoring-Muster "Extract Method" an. Dieses beinhaltet die Auslagerung von zusammenhängenden Codeblöcken in separate, benannte Methoden. Im Falle der parseCommand() Methode könnte dies beispielsweise die Extraktion von Logik zur Behandlung spezifischer Befehlsgruppen (z.B. "create"-Befehle, "read"-Befehle) oder einzelner Befehle in spezifische private Hilfsmethoden umfassen. Auch die Aufteilung der Eingabe könnte in eine separate Methode ausgelagert werden.
Die Anwendung des "Extract Method"-Refactorings würde zu einer Reduktion der Komplexität der ursprünglichen parseCommand() Methode führen, die Lesbarkeit durch die Schaffung kleinerer, fokussierter Einheiten verbessern und die Testbarkeit der einzelnen Komponenten erhöhen. Darüber hinaus erleichtert die klare Trennung von Verantwortlichkeiten die zukünftige Wartung und Erweiterung des Codes, da Änderungen an der Behandlung eines spezifischen Befehls auf die entsprechende extrahierte Methode beschränkt bleiben.

#### Refactoring des Duplicate Code mit Call Existing Method
Das Refactoring, um den "Duplicated Code" zu beseitigen, besteht darin, die bestehende toString() Methode der ExaminationWithPatientName Klasse in der ShowDoctorExaminationPlanCommand Klasse aufzurufen, anstatt die Formatierungslogik manuell zu wiederholen. Auch im weiteren Sinne unter dem Prinzip "Don't Repeat Yourself" (DRY) zusammengefasst, welches besagt, dass jede Information im System eine einzige, eindeutige und maßgebliche Repräsentation haben sollte. Das Refactoring, die toString() Methode aufzurufen, folgt diesem DRY-Prinzip, indem die Formatierungslogik nur an einer Stelle existiert. Commit: 9d8adaeecfdbe41c6e7ae5ea74fb9c72900e40ee<br>
- Eliminierung von Redundanz: Durch den Aufruf der toString() Methode wird die Formatierungslogik nur noch an einer Stelle verwaltet; in der ExaminationWithPatientName Klasse. Dies beseitigt die Duplizierung des Codes in der ShowDoctorExaminationPlanCommand Klasse.
- Verbesserte Wartbarkeit: Wenn sich das Format der Ausgabe für eine ExaminationWithPatientName ändern sollte, muss nur die toString() Methode in dieser Klasse angepasst werden. Alle Stellen, an denen diese Methode aufgerufen wird (in diesem Fall die ShowDoctorExaminationPlanCommand), profitieren automatisch von der Änderung. Dies reduziert den Aufwand und das Fehlerrisiko bei zukünftigen Anpassungen.
- Erhöhte Lesbarkeit und Verständlichkeit: Der Code in der ShowDoctorExaminationPlanCommand Klasse wird durch die einfachere Schleife lesbarer und verständlicher. Der Fokus liegt nun auf der Iteration durch die Liste der Untersuchungstermine, ohne sich um die Details der String-Formatierung kümmern zu müssen.
- Stärkere Kohäsion: Die ExaminationWithPatientName Klasse übernimmt die Verantwortung für ihre eigene String-Repräsentation. Dies erhöht die Kohäsion der Klasse, da sie nun sowohl die Daten als auch die Art und Weise, wie diese Daten als String dargestellt werden, kapselt.

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
ermöglichen. Dadurch können die unten genannten Vorteile erreicht werden, welche den Entwicklungsprozess erleichtern und die Lesbarkeit des Codes erhöhen.


#### Verbesserung des Codes durch das Builder-Pattern
Lesbarkeit & Wartbarkeit: Durch die Verwendung von Methoden mit sprechenden Namen (withName(), withAddress(), etc.) wird der Code lesbarer und intuitiver.
Flexibilität: Das Muster erlaubt es, optionale Parameter zu setzen, ohne überladene Konstruktoren zu benötigen. Somit muss sich kein Gedanke über die Reihenfolge der Parameter gemacht werden. 

#### Vor- und Nachteile des Builder-Patterns

Vorteile:
- Verbesserte Lesbarkeit und Verständlichkeit des Codes
- Flexibilität bei der Objekterstellung
- Vermeidung von zu vielen Konstruktoren mit unterschiedlichen Parameterkombinationen
- Erleichterte Wartbarkeit und Erweiterbarkeit des Codes
- Ermöglicht die Verwendung von optionalen Parametern ohne Überladung von Konstruktoren
- Ermöglicht eine klare Trennung zwischen der Objekterstellung und der Objektverwendung

Nachteile:
- Zusätzlicher Implementierungsaufwand durch eine separate Builder-Klasse
- Erhöhter Speicherbedarf, da ein zusätzliches Objekt (PatientBuilder) benötigt wird 
- Möglicherweise längere Initialisierungszeit, da ein zusätzliches Objekt erstellt werden muss
- Komplexität durch zusätzliche Klasse, die verwaltet werden muss


#### Vor- und Nachteile ohne das Builder-Pattern

Vorteile:
- Weniger Code durch direkte Nutzung von Konstruktoren
- Geringerer Speicherverbrauch, da keine zusätzliche Builder-Klasse notwendig ist
- Einfachere Implementierung, da keine separate Builder-Klasse erstellt werden muss

Nachteile:
- Lange, schwer verständliche Konstruktoraufrufe
- Höhere Fehleranfälligkeit durch falsche Parameterreihenfolge
- Geringere Erweiterbarkeit des Codes
- Möglicherweise schwerer verständliche Tests zu schreiben, da alle Parameter in den Konstruktoren angegeben werden müssen
- Eingeschränkte Flexibilität bei der Objekterstellung, da alle Parameter im Konstruktor angegeben werden müssen

#### Fazit
Das Builder-Pattern verbessert die Struktur und Lesbarkeit des Codes erheblich, insbesondere bei komplexen Objekten mit vielen optionalen Attributen. 
Während der Mehraufwand an Code und Speicherverbrauch als Nachteil betrachtet werden kann, überwiegen die Vorteile, da das Muster die Wartbarkeit und 
Erweiterbarkeit des Codes deutlich verbessert.
