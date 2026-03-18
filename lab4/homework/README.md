# City Street Network Analysis
Acest proiect este o aplicație Java care modelează rețeaua stradală a unui oraș sub forma unui graf ponderat. Proiectul demonstrează utilizarea conceptelor de Programare Orientată pe Obiect (OOP), procesarea avansată a datelor folosind Java Stream API și rezolvarea de probleme algoritmice pe grafuri folosind biblioteca Graph4J.

1. Funcționalități:
Arhitectura separă clar entitățile rețelei (City, Intersection, Street), utilizând corect colecțiile din Java (HashSet, ArrayList, HashMap) prin suprascrierea metodelor equals, hashCode și implementarea interfeței Comparable.
Generare de date false prin utilizarea bibliotecii JavaFaker, pentru a popula automat rețeaua cu nume realiste pentru străzi și intersecții.
Filtrează eficient lista de străzi pentru a găsi muchiile care îndeplinesc condiții stricte (java streams):
-lungimea străzii depășește o anumită valoare
-strada conectează intersecții care, cumulat, se leagă de cel puțin alte 3 străzi (calculul gradului fiecărui nod)
Transformă modelul obiectual într-o reprezentare matematică folosind Graph4J și utilizează algoritmul WeightedSpanningTreeIterator pentru a găsi și afișa Arborii Parțiali de Cost Minim ai rețelei.

2. Structura Proiectului:
-Intersection.java: Reprezintă un nod în graf. Suprascrie equals și hashCode pentru unicitate în colecții de tip Set și pentru a fi folosit ca o cheie în Map.
-Street.java: Reprezintă o muchie ponderată, lungimea fiind costul. Implementează Comparable<Street> pentru a permite sortarea ușoară a străzilor după lungime.
-City.java: Clasa agregator care conține setul de intersecții și lista de străzi asociate orașului.
-Main.java: Punctul de intrare al aplicației. Conține logica de asamblare, generarea datelor, filtrarea cu Streams și inițializarea grafului Graph4J.

3. Tehnologii și Dependențe
Acest proiect folosește Maven pentru managementul dependențelor, în fișierul pom.xml. Pentru generarea numelor aleatorii s-a folosit JavaFaker, iar pentru structura și algoritmii pe grafuri s-a folosit Graph4J.

Configurare pom.xml:
```xml
 <dependencies> 
    <dependency>
        <groupId>org.graph4j</groupId>
        <artifactId>graph4j</artifactId>
        <version>1.0.8</version>
    </dependency>
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>
    </dependencies>
    ```

4. Rulare
Asigurați-vă că aveți instalat Java Development Kit (JDK) 11 sau mai nou.
Deschideți proiectul într-un IDE care suportă Maven.
Lăsați Maven să descarce dependențele (Sync Project / Reload Maven Project).
Rulați metoda main(String[] args) din clasa Main.java.
