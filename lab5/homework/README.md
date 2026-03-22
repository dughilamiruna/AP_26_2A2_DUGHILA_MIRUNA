# Resource Catalog Problem

Această aplicație gestionează un catalog de resurse bibliografice (cărți, articole, etc.).  
Permite adăugarea de elemente, salvarea/încărcarea acestora, vizualizarea lor folosind aplicațiile native ale sistemului de operare și generarea de rapoarte HTML.

## Funcționalități  

* Clasele `Catalog` și `Item` pentru reprezentarea resurselor: fiecare resursă are un ID unic, titlu, locațieși o listă de proprietăți dinamice (tag-uri).
* Toate acțiunile aplicației sunt încapsulate în clase specifice care implementează o interfață generică `Command` (`AddCommand`, `ListCommand`, `ViewCommand`, `ReportCommand`).
* Comanda `ViewCommand` deschide automat fișierele locale sau link-urile web folosind aplicația implicită a sistemului de operare.
* Comanda `ReportCommand` utilizează **FreeMarker Template Engine** pentru a genera un raport vizual al catalogului (`report.ftl`) și îl deschide automat în browser.
* Utilizarea clasei `InvalidCatalogException` pentru a semnala erorile apărute în timpul execuției comenzilor.
* Proiectul este configurat folosind Maven pentru a compila o arhivă executabilă independentă.

## Tehnologii 

* **Java 17+**
* **Maven** 
* **FreeMarker** 
* **Jackson** 

## Build-ul aplicatiei

Proiectul folosește Maven. Pentru a genera arhiva JAR executabilă, rulează următoarea comandă în rădăcina proiectului:
```
mvn clean package
```
Acest proces va descărca dependențele (cum ar fi FreeMarker) și va genera un fișier .jar executabil în folderul target/.

## Run-ul

Odată ce arhiva a fost generată cu succes, aplicația poate fi lansată direct din terminal:

    java -jar target/nume-proiect-1.0-SNAPSHOT.jar

La rulare, aplicația va:
1. Crea un catalog în memorie.
2. Adăuga resurse (cărți, articole).
3. Genera un fișier catalog_report.html pe baza șablonului FreeMarker.
4. Deschide automat raportul în browserul web implicit.

## Structura Proiectului

* src/main/java/org/example/ - Conține codul sursă (clasele model, excepțiile custom și comenzile).
* src/main/resources/ - Conține fișierul report.ftl (șablonul pentru FreeMarker).
* pom.xml - Configurarea Maven pentru dependențe și plugin-ul de generare a JAR-ului executabil.
