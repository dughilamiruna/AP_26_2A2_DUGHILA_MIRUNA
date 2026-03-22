# Resource Catalog Problem

Această aplicație gestionează un catalog de resurse bibliografice (cărți, articole, etc.).  
Permite adăugarea de elemente, salvarea/încărcarea acestora, vizualizarea lor folosind aplicațiile native ale sistemului de operare și generarea de rapoarte HTML.

## Funcționalități  

* **Modelare Orientată pe Obiecte:** Clasele `Catalog` și `Item` pentru reprezentarea resurselor. Fiecare resursă are un ID unic, titlu, locație (path local sau URL) și o listă de proprietăți dinamice (tag-uri).
* **Command Design Pattern:** Toate acțiunile aplicației sunt încapsulate în clase specifice care implementează o interfață generică `Command` (ex: `AddCommand`, `ListCommand`, `ViewCommand`, `ReportCommand`).
* **Desktop API Integration:** Comanda `ViewCommand` deschide automat fișierele locale sau link-urile web folosind aplicația implicită a sistemului de operare.
* **Generare Rapoarte HTML:** Comanda `ReportCommand` utilizează **FreeMarker Template Engine** pentru a genera un raport vizual al catalogului (`report.ftl`) și îl deschide automat în browser.
* **Excepții Personalizate:** Utilizarea clasei `InvalidCatalogException` pentru a semnala erorile apărute în timpul execuției comenzilor.
* **Fat JAR / Executable Archive:** Proiectul este configurat folosind Maven (`maven-shade-plugin`) pentru a compila o arhivă executabilă independentă.

## 🛠️ Tehnologii Utilizate

* **Java 17+**
* **Maven** (Dependency & Build Management)
* **FreeMarker** (HTML Template Engine)
* **Jackson** (JSON Serialization/Deserialization - opțional pentru Load/Save)

## ⚙️ Cum se construiește aplicația (Build)

Proiectul folosește Maven. Pentru a genera arhiva JAR executabilă, rulează următoarea comandă în rădăcina proiectului:

```bash
mvn clean package
