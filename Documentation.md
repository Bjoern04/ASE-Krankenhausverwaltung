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
**Address** ist als Value Object implementiert, weil es keine eigene Identität besitzt und vollständig durch seine Werte Straße, Hausnummer, PLZ und Ort beschrieben wird. Zwei Address-Objekte mit denselben Werten sind gleich und austauschbar. Wenn alle ihre Attribute gleich sind, so sind auch die beiden Objekte gleich. Sie geben die gleiche Adresse an. Zudem hat die Adresse keinen Lebenszyklus und wird sich nicht verändern. Sie ist unveränderlich (immutable) und wird nur zur Beschreibung der Entität Patient oder Arzt verwendet. Falls diese eine neue Adresse benötigen, wird ein neues Address-Objekt erstellt.
<br><br>
**Patient** ist als Entity implementiert, weil jedes Patient-Objekt eine eigene Identität (UUID) besitzt, die es eindeutig unterscheidet, unabhängig von seinen Attributwerten wie Name oder Adresse. Zwei Patienten können die gleichen Namen haben (andere Attribute nullen), aber sie sind dennoch unterschiedliche Personen/ Entitäten, solange ihre UUIDs verschieden sind. Patienten haben zudem einen Lebenszyklus, da sich ihre Eigenschaften während ihres Aufenthaltes im Krankenhaus ändern können. Sie können in andere Zimmer verlegt werden oder neue Untersuchungen zugewiesen bekommen. Die Eigenschaften eines Patienten müssen sich ändern können. Es soll bei Änderungen kein neues Patienten-Objekt erstellt werden, sondern das bestehende aktualisiert werden.
<br><br>
**Room** ist als Aggregat implementiert, weil es die Entitäten und Value Objects, welche zu einem Raum gehören zu einer gemeinsam verwalteten Einheit gruppiert. Dadurch kann die Komplexität der Beziehungen zwischen Objekten reduziert werden. Durch die Einteilung in Aggregate können komplexe Objekt-Graphen vermieden und eindeutige Stellen zur Prüfung von domänenobjekt-übergreifender Invarianten erstellt werden. Das Aggregate Root des Room-Aggregates ist die Room-Entität. Nur über sie kann auf das Aggregat zugegriffen werden. Dies ergibt Sinn, da auf das Value Object RoomAddress, welches Teil des Aggregates ist, nur von einem Room heraus drauf zugegriffen werden muss. Durch das Aggregate Root Room hat das Aggregat eine zentrale Stelle zur Durchsetzung von unveränderlichen Regeln (Invarianten) über mehrere Domänenobjekte hinweg.
<br><br>
**PatientRepository** ist ein Repository, weil 
**ExaminationReassignmentDomainService** ist eine Domain Service, weil es Geschäftslogik enthält, die mehrere Entitäten (Examination und Doctor) betrifft. Er definiert eine Regel der Problemdomäne, welche nicht einer bestimmten Entität zugewiesen werden kann, da mehrere beteiligt sind. Der Domain Service überprüft, ob die vom Nutzer gewünschte Neuzuweisung eines Arztes zu einer bestehenden Untersuchung möglich ist. Dabei müssen unterschiedliche Bedingungen und Regeln der Examination und Doctor Entität überprüft werden, damit die Krankenhausverwaltung in einem korrekten Zustand bleibt. Der Domain Service stellt sicher, dass der neue Doktor die Untersuchungsart durchführen kann, die Untersuchung nicht in der Vergangenheit liegt und der Doktor zum Zeitpunkt der Untersuchung verfügbar ist.
<br><br>


## 2 Clean Architecture
### 2.1 Grundlagen Clean Architecture
Die Architektur der Krankenhausverwaltungsanwendung basiert auf den Prinzipien der Clean Architecture. Dieses Architekturmuster fördert eine klare Trennung von Verantwortlichkeiten, eine hohe Testbarkeit und eine geringe Kopplung zwischen den Schichten. Dadurch wird eine flexible, wartbare und erweiterbare Softwarelösung gewährleistet.

### 2.2 Schichten der Anwendung
Die Anwendung besteht aus drei verschiedenen Schichten, die jeweils unterschiedliche Verantwortlichkeiten haben. Diese Schichten sind:

1. Domain-Schicht
2. Application-Schicht
3. Plugin-Schicht
<br>
Im Nachfolgenden werden die einzelnen Schichten erklärt und erläutert, warum diese implementiert wurde und welche Aufgaben sie jeweils erfüllen. 

### 2.2.1 Domain-Schicht
Die Domain-Schicht enthält die zentrale und organisationsweit geltende Geschäftslogik und definiert die grundlegenden Geschäftsobjekte (Entities) sowie deren Verhalten. Sie kapselt lediglich die in einer Organisation geltenden Regeln. Dabei sollen diese von äußeren Elementen wie Datenbanken oder APIs aber auch der UI unabhängig sein. APIs und Datenbankanbindungen sollen sich ändern können, ohne das dadurch die Bestandteile der Domain-Schicht beeinflusst werden.<br><br>

#### Bestandteile
- **Entities**: Die Entities Patient, Arzt, Zimmer, Untersuchung und Belegung 
repräsentieren die zentralen Domänenobjekte, welche zur Abbildung und Modellierung
der Geschäftslogik benötigt werden. Diese Objekte können sich im Ablauf des 
Programms verändern und haben einen Lebenszyklus.
- **Value Objects**: Auch die Value Objects sind in der Domain-Schicht enthalten.
Sie sind unveränderliche Werte wie Name, Adresse, etc., welche zur Beschreibung
der Entities verwendet werden. 
- **Aggregate**: Die Aggregate gruppieren Entitäten und Value Objects zu zusammengehörigen Einheiten. Dadurch kann die Komplexität der Beziehungen zwischen Objekten reduziert werden. Zudem kann über das Aggregate Root eine zentrale Stelle zur Prüfung von domänenobjekt-übergreifender Invarianten erstellt werden, welches ebenfalls die Komplexität reduziert und die korrekte Einhaltung aller Regeln fördert.
- **Repositories**: Die Repositories definieren Interfaces für die Aggregate 
Roots, welche in äußeren Schichten implementiert werden können, um auf Daten
z.B. aus einer Datenbank zuzugreifen.
- **Domain Services**: Der letzte Bestandteil sind die Domain Services,
welche Geschäftslogik beinhalten an welcher mehrere unterschiedliche
Entities beteiligt sind.<br><br>

#### Warum wurde diese Schicht gewählt?
Die Domain-Schicht wurde ausgewählt, um die organisationsweite Geschäftslogik und die dazu gehörenden Geschäftsobjekte abzubilden. Innerhalb der Schicht können die einzelnen Entitäten, Value Objects, Aggregate, etc. erstellt und organisiert werden, um die Regeln der Organisation abzubilden. Die Schicht soll sie vor äußeren Einflüssen schützen und eine klare Trennung von anderen Schichten gewährleisten. Dadurch wird sichergestellt, dass die Geschäftslogik unabhängig von der Implementierung dem Speichersystem mit den JSON-Dateien oder der Benutzeroberfläche/ Konsole bleibt. Dadurch können auch im Nachhinein noch weitere Änderungen an Benutzeroberfläche vorgenommen werden, ohne dabei Probleme mit den Geschäftsobjekten oder den geltenden Regeln zu verursachen. Diese bleiben korrekt erhalten. So kann zum Beispiel erst eine korrekt funktionierende Konsolenanwendung entwickelt werden und diese in Zukunft bei Bedarf in eine Anwendung mit einer richtigen UI umgewandelt werden. Dabei bleiben die Geschäftsobjekte und deren Regeln unverändert. <br>
Zudem kann auch das Speichersystem überarbeitet werden. Falls in Zukunft eine größere Anzahl an Patienten und Doktoren vorhanden ist und diese weiterhin in der Verwaltung hinterlegt werden müssen, kann einfach auf eine SQL-Datenbank umgestiegen werden. Diese kann dann über die Repositories angesprochen werden. Die Domain-Schicht bleibt dabei unverändert und kann weiterhin genutzt werden. Nur die Implementierung der Repositories muss verändert werden. Dabei bleibt die Domain-Schicht davon unberührt.<br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Wie bereits erwähnt bildet die Domain-Schicht die organisationsweite Geschäftslogik und die dazu gehörenden Geschäftsobjekte ab. In diesem Programm bildet sie die zentralen Bestandteile, wie Patienten, Ärzte, Zimmer, Untersuchungen und Belegungen der Krankenhausverwaltung ab. Auch die dazu gehörenden Value Objects, welche die Entities beschreiben, sind in dieser Schicht enthalten. Ebenfalls enthält die Domain-Schicht die repository Interfaces, welche in der Plugin-Schicht implementiert werden. Es sind auch einzelne util-Klassen in den Aggregaten enthalten, die einzelne Aufgaben und Regeln für die Entitäten übernehmen, welche jedoch nicht den Anforderungen eines Domain Services entsprechen. Sie ermöglichen es zum Beispiel einen Patienten in einer Liste von Patienten zu aktualisieren. Als letztes ist auch noch der Examination Reassignment Domain Service enthalten. Dieser stellt sicher das alle Regeln der Examination und Doctor Entität beim Ändern des Arztes einer Untersuchung eingehalten werden.

### 2.2.2 Application-Schicht

#### Bestandteile
- **Use Cases**: Die Use Cases sind die Anwendungsfälle, die die Interaktion zwischen der Benutzeroberfläche und der Domain-Schicht definieren. Sie enthalten die Logik zur Verarbeitung von Benutzeranfragen und zur Koordination der Interaktion mit den Entitäten und Repositories.<br><br>

#### Warum wurde diese Schicht gewählt?
Die Application-Schicht wurde gewählt, um die Anwendungsfälle der Krankenhausverwaltung zu definieren und die Interaktion zwischen der Benutzeroberfläche und der Domain-Schicht zu koordinieren. Sie stellt sicher, dass die Geschäftslogik korrekt angewendet wird und die Benutzeranfragen ordnungsgemäß verarbeitet werden. Diese Schicht fungiert als Vermittler zwischen der Benutzeroberfläche und der Domain-Schicht und sorgt dafür, dass die Geschäftsregeln eingehalten werden. Sie ruft die für einen Use Case relevanten Methoden und Services der Domain-Schicht auf und verarbeitet die Ergebnisse, um sie an die Benutzeroberfläche zurückzugeben. Dadurch wird eine klare Trennung zwischen der Benutzeroberfläche und der Geschäftslogik erreicht, was die Erweiterbarkeit und vor allem die Wartbarkeit der Anwendung verbessert. <br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Die Application-Schicht enthält die einzelnen Use Cases, welche für eine Krankenhausveraltung relevant sind. Es sind alle Use Cases zum Auslesen und Erstellen von Patienten, Ärzten, Zimmern und Untersuchungen enthalten. Diese sind in den Use Case Klassen implementiert. Die Application-Schicht ist dafür verantwortlich, die Benutzeranfragen zu verarbeiten und die entsprechenden Methoden der Domain-Schicht aufzurufen. Sie stellt sicher, dass die Geschäftslogik korrekt angewendet wird und die Benutzeranfragen ordnungsgemäß verarbeitet werden. Zum Beispiel organisiert der Examination Reassignment Use Case die Neuzuweisung eines Arztes zu einer Untersuchung. Dabei werden die relevanten Daten über die Implementierung der Repositories in der Plugin-Schicht aus den JSON-Dateien geladen und die Rückgaben überprüft. Zudem wird der Aufruf von Services der Domain-Schicht organisiert und die zurückgegebenen Werte interpretiert. So wird festgestellt, ob eine Neuzuweisung möglich ist. Je nach Ergebnis wird diese durchgeführt und eine entsprechende Benachrichtigung an die Plugin-Schicht zurückgegeben, um diese dem Benutzer in der erstellten UI anzuzeigen.

### 2.2.3 Plugin-Schicht

#### Bestandteile
- **Repositories**: Die Repositories sind die Implementierungen der Repository-Interfaces aus der Domain-Schicht. Sie sind dafür verantwortlich, die Daten aus den JSON-Dateien zu laden und zu speichern. 
- **UI**: Die Benutzeroberfläche (UI) ist die Implementierung der Benutzerinteraktion. Sie ermöglicht es den Benutzern, mit der Anwendung zu interagieren und die verschiedenen Funktionen der Krankenhausverwaltung zu nutzen. In diesem Fall ist die UI eine Konsolenanwendung.
- **Parser**: Die Parser sind dafür verantwortlich, die Benutzereingaben zu verarbeiten und diese den richtigen Befehlen zuzuordnen, damit diese die Eingaben weiter verarbeiten und weiterleiten können.
- **Commands**: Die Commands sind die einzelnen Befehle, die von der Benutzeroberfläche an die Anwendung gesendet werden. Sie repräsentieren die verschiedenen Aktionen, die der Benutzer in der Anwendung durchführen kann, wie z.B. das Erstellen eines neuen Patienten oder das Zuweisen eines Arztes zu einer Untersuchung. Sie leiten rufen die dazu passenden Use Cases auf. Davor versuchen sie die einzelnen Eingabewerte des Nutzers mithilfe des Parsers und seiner Methoden in die von den Use Cases benötigten Objekte zu parsen.
- **Serializer**: Der Serializer ist dafür verantwortlich, die Daten in das JSON-Format zu konvertieren und diese in den JSON-Dateien zu speichern. Er sorgt dafür, dass die Daten korrekt formatiert sind und in der richtigen Struktur gespeichert werden. Er wird von den Methoden der implementierten Repositories in unterschiedlichen Formen aufgerufen. Dadurch muss nicht in jedem Repository die gleiche triviale JSON-Serialisierung implementiert werden.

#### Warum wurde diese Schicht gewählt?
Die Plugin-Schicht wurde gewählt, um die Implementierung der Benutzeroberfläche und die Interaktion mit den Benutzern zu kapseln. Sie enthält die Repositories, die für den Zugriff auf die Daten verantwortlich sind, sowie die Benutzeroberfläche, die es den Benutzern ermöglicht, mit der Anwendung zu interagieren. Diese Schicht ist von der Domain-Schicht und der Application-Schicht unabhängig und kann leicht geändert oder ersetzt werden, ohne dass dies Auswirkungen auf die Geschäftslogik hat. Dadurch wird eine klare Trennung zwischen der Benutzeroberfläche und der Geschäftslogik erreicht, was die Erweiterbarkeit und Wartbarkeit der Anwendung verbessert. Zudem können in dieser Schicht auch andere Implementierungen der Repositories hinterlegt werden. So könnte zum Beispiel in Zukunft eine SQL-Datenbank anstelle von JSON-Dateien verwendet werden. Auch hier bleibt die Domain- und Application-Schicht unverändert. Lediglich die Implementierung der Repositories und der verwendete JSON-Serializer müssen angepasst werden.<br><br>

#### Welche Aufgaben erfüllt diese Schicht?
Die Plugin-Schicht enthält die Implementierung der Benutzeroberfläche, die es den Benutzern ermöglicht, mit der Anwendung zu interagieren. Sie ist dafür verantwortlich, die Benutzereingaben zu verarbeiten und diese an die entsprechenden Use Cases weiterzuleiten. Zudem enthält sie die Repositories, die für den Zugriff auf die Daten verantwortlich sind. Diese laden und speichern die Daten in den JSON-Dateien. Die Plugin-Schicht ist auch dafür verantwortlich, die Ergebnisse der Use Cases an die Benutzeroberfläche zurückzugeben und diese dem Benutzer korrekt anzuzeigen. <br>Beispielsweise wird die Benutzeroberfläche erzeugt und auf eine Eingabe des Benutzers gewartet. Wenn der Benutzer einen Befehl eingegeben hat, wird dieser mithilfe des Parsers geparst. Anschließend wird der korrekte Command aufgerufen, welcher die Fehler des Parsers abfängt und den korrekten Use Case mitsamt der benötigten Parameter aufruft. Anschließend nimmt er die für die Ausgaben gedachten Daten zurück und gibt sie an die UI weiter, sodass der Nutzer informiert wird. 

## 3 Programming Principles
Es gibt eine Vielzahl an Prinzipien, die beim Programmieren beachtet werden sollten. Diese helfen dabei, den Code lesbar, wartbar und erweiterbar zu gestalten. Im Folgenden werden einige der in diesem Projekt verwendeten Prinzipien erläutert und an in der Anwendung verwendeten Implementationen verdeutlicht.

### 3.1 Single Responsibility Principle (SRP)
Das Single Responsibility Principle ist Teil der SOLID-Prinzipien und besagt, dass eine Klasse nur eine einzige Verantwortung haben sollte. Dies bedeutet, dass eine Klasse nur für eine bestimmte Funktionalität zuständig ist und nicht mehrere Verantwortlichkeiten übernimmt. Dadurch wird die Wartbarkeit und Testbarkeit des Codes verbessert, da Änderungen an einer Klasse keine Auswirkungen auf andere Klassen haben sollten.<br>

Dieses Prinzip ist beispielweise im Input Parser berücksichtigt. Diese Klasse ist nur dafür zuständig die Eingaben des Benutzers aufzuteilen und zu parsen. Sie ist nicht für die Verarbeitung der Eingaben oder die Interaktion mit der Benutzeroberfläche verantwortlich. Dadurch wird der Inhalt der Klasse reduziert und leichter verständlich. Dies ist besonders wichtig, da Methoden zum Parsen von Strings für den Menschen recht unübersichtlich sein können. Somit ist es essenziell, dass diese möglichst unabhängig von anderen Klassen sind. Ebenfalls ermöglicht es, dass neue Vorgänge zum Parsen vergleichsweise einfach hinzugefügt werden können und diese auch nicht von weiteren Elementen, wie zum Beispiel der UI abhängig sind. Auch die Benutzeroberfläche kann so unabhängig von der Parser-Klasse weiterentwickelt werden. So kann zum Beispiel eine andere Einlesestrategie gewählt werden, ohne das der Parser davon betroffen ist.<br>

Das Prinzip ist ebenfalls bei den einzelnen Commands angewandt. Diese sind nur für einen bestimmten Anwendungsfall zuständig und übernehmen keine weiteren Aufgaben. So ist zum Beispiel der CreatePatientCommand nur dafür zuständig, Patienten zu erstellen und zu bearbeiten. Er ist nicht für die Verwaltung von Ärzten oder Zimmern verantwortlich. Das ermöglicht es neue Commands schnell hinzuzufügen, da sie nicht von bestehenden abhängig sind. Zudem können die bestehenden Commands unabhängig voneinander getestet und erweitert werden, sodass die Arbeit des Entwicklers er erleichtert und beschleunigt wird. <br>

### 3.2 Open/Closed Principle (OCP)
Das Open/Closed Principle ist ein weiteres Prinzip der SOLID-Prinzipien und besagt, dass Software-Entitäten (Klassen, Module, Funktionen, etc.) offen für Erweiterungen, aber geschlossen für Änderungen sein sollten. Dies bedeutet, dass bestehende Code nicht verändert werden sollte, um neue Funktionalitäten hinzuzufügen. Stattdessen sollten neue Funktionalitäten durch Erweiterungen des bestehenden Codes erreicht werden.<br>

Dieses Prinzip ist durch die Repositories implementiert. Die Repositories sind als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch können neue Repositories hinzugefügt werden, ohne den bestehenden Quellcode zu ändern. Diese Interfaces sind für offen für Erweiterungen, da sie in neuen Repositories implementiert werden können. Gleichzeitig sind sie geschlossen für Änderungen, da das Interface und bestehende Repositories nicht verändert werden müssen, um neue Funktionalitäten hinzuzufügen. <br>So kann zum Beispiel ein neues Repository für eine SQL-Datenbank hinzugefügt werden, welches das entsprechende Interface implementiert. Dabei müssen weder an dem Interface noch an dem bestehenden Repository für JSON-Dateien Änderungen vorgenommen werden. Dies ermöglicht eine einfache Erweiterung der Anwendung, ohne dass bestehende Funktionalitäten beeinträchtigt werden. Dabei wird der existierende Quellcode nicht verändert und es entstehen zwei unterschiedliche Möglichkeiten zur Persistierung, welche von der Anwendung genutzt werden können.<br>

### 3.3 Dependency Inversion Principle (DIP)
Das Dependency Inversion Principle ist ein weiteres Prinzip der SOLID-Prinzipien und besagt, dass hochrangige Module nicht von niederrangigen Modulen abhängen sollten. Beide sollten von Abstraktionen abhängen. Dies bedeutet, dass die Abhängigkeiten zwischen den Modulen durch Interfaces oder abstrakte Klassen definiert werden sollten, anstatt durch konkrete Implementierungen.<br>

Im Programm sollen die inneren Schichten nicht von äußeren Schichten abhängen. Dies bedeutet, dass die Domain- und Application-Schicht nicht von der Plugin-Schicht abhängen sollten. Stattdessen sollten sie nur von Abstraktionen (Interfaces) abhängen. Dadurch wird die Kopplung zwischen den Schichten reduziert und die Wartbarkeit und Testbarkeit des Codes verbessert.<br>

Das Prinzip ist in der Anwendung durch die Verwendung von Interfaces in den Repositories umgesetzt. Die Repositories sind als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch können die inneren Schichten (Domain- und Application-Schicht) von den Abstraktionen (Interfaces) abhängen, anstatt von konkreten Implementierungen. Die Use Cases in der Application Schicht sind nicht von der genauen Implementierung in der Plugin-Schicht abhängig, sondern nur von der Abstraktion durch das Interface. Zum Beispiel kann der Read Patient Use Case, der ein PatientRepository benötigt, mit jedem Repository arbeiten, das das PatientRepository-Interface implementiert. Dadurch wird die Kopplung zwischen den Schichten reduziert. So kann ein Patient unabhängig von der genauen Persistenzschicht und ihrer Implementierung gelesen werden. Dies trifft auch auf die anderen Use Cases zu. <br> 

Die Beachtung dieses Prinzips ermöglicht eine einfache Erweiterung der Anwendung, da neue Implementierungen der Repositories hinzugefügt werden können, ohne dass Änderungen an den inneren Schichten erforderlich sind. Zudem wird auch hier die Testbarkeit des Codes verbessert, eine bessere Wiederverwendbarkeit und flexiblere Zusammenarbeit ermöglicht und zudem klare Schnittstellen definiert.<br>

### 3.4 DRY (Don't Repeat Yourself)
Das DRY-Prinzip besagt, dass Informationen und Logik nicht dupliziert werden sollten. Stattdessen sollten sie in einer einzigen Quelle gespeichert werden. Dies reduziert Redundanz und Inkonsistenzen und verbessert die Wartbarkeit des Codes.<br>

Das DRY-Prinzip ist in der Anwendung durch die Verwendung von gemeinsamen Methoden und Klassen umgesetzt. Zum Beispiel gibt es in der Serializer-Klasse eine Methode, die für alle Repositories verwendet wird, um die Daten in das JSON-Format zu konvertieren. Diese Methode wird in allen Repositories verwendet, die JSON-Daten speichern müssen. Dadurch wird sichergestellt, dass die Serialisierung an einer einzigen Stelle implementiert ist und nicht in jedem Repository dupliziert werden muss. Dies reduziert Redundanz und Inkonsistenzen beim Serialisieren. Zudem können Änderungen schneller hinzugefügt werden. Wenn eine Änderung am Serialisierungsvorgang durchgeführt werden soll, muss diese nur an einer einzigen Stelle implementiert und getestet werden. Sie muss nicht in jedem Repository einzeln implementiert werden.<br>

Weitere Beispiele von der Verwendung des DRY-Prinzips ist die Parser-Klasse und die Commands. Diese verwenden ebenfalls gemeinsame Methoden, um die Eingaben des Benutzers zu verarbeiten und diese an die entsprechenden Use Cases weiterzuleiten. Dadurch wird sichergestellt, dass die Logik an einer einzigen Stelle implementiert ist und nicht in jedem Command dupliziert werden muss. Dies reduziert Redundanz und Inkonsistenzen beim Verarbeiten der Benutzereingaben. Zudem können Änderungen schneller hinzugefügt werden. Wenn eine Änderung am Verarbeitungsprozess durchgeführt werden soll, muss diese nur an einer einzigen Stelle implementiert und getestet werden. Sie muss nicht in jedem Command einzeln umgesetzt werden.<br>

### 3.5 GRASP - Low Coupling
Das Low Coupling-Prinzip besagt, dass die Kopplung zwischen den Klassen und Modulen so gering wie möglich gehalten werden sollte. Dies bedeutet, dass Klassen und Module unabhängig voneinander arbeiten sollten und nur über klar definierte Schnittstellen miteinander kommunizieren sollten. Dadurch wird die Wartbarkeit und Testbarkeit des Codes verbessert, da Änderungen an einer Klasse keine Auswirkungen auf andere Klassen haben sollten.<br>

Das Low Coupling-Prinzip ist in der Anwendung durch die Verwendung von Interfaces und Abstraktionen umgesetzt. Zum Beispiel sind die Repositories als Interfaces definiert, die in der Plugin-Schicht implementiert werden. Dadurch hängen die inneren Schichten nur von den Interfaces ab, anstatt von konkreten Implementierungen. Dies reduziert die Kopplung zwischen den Schichten und wurde umgesetzt, um eine gute Testbarkeit, eine einfache Verständlichkeit, eine erhöhte Wiederverwendbarkeit und eine leicht Anpassbarkeit zu gewährleisten. Es ermöglicht wie vorhin schon bei anderen Prinzipien erwähnt, dass einfache Austauschen von Zugriffen auf die Persistenzschicht und damit auch den Austausch Persistenzschicht selbst. So kann einfach von dem Speichern in JSON-Dateien auf eine SQL-Datenbank gewechselt werden, ohne dass in der Application Schicht große Änderungen vorgenommen werden müssen. Die Use Cases in der Application Schicht, da diese nur eine Klasse benötigen, welche das entsprechende Interface implementiert. Wie genau es dann umgesetzt ist, ist aufgrund der losen Kopplung nicht relevant für die Use Cases.<br>


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
