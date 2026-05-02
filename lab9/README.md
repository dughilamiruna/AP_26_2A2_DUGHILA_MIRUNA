# Concurrent Maze Simulator

Acest proiect este o aplicație Java bazată pe consolă care simulează explorarea concurentă a unui labirint. Un iepuraș și doi roboți navighează simultan prin labirint folosind fire de execuție separate (Multi-threading). Jocul se termină când un robot reușește să prindă iepurașul pe aceeași celulă.

## Funcționalități 

*   **Generare Procedurală (DFS):** Labirintul este generat automat la fiecare rulare folosind algoritmul Depth-First Search, garantând un labirint perfect, fără bucle închise.
*   **Programare Concurentă:** Fiecare personaj rulează pe propriul său `Thread`.
*   **Sincronizare Fină (Fine-Grained Synchronization):** Pentru a eficientiza simularea, sincronizarea nu se face pe tot labirintul, ci la nivel de celulă. Aceasta permite roboților să se miște cu adevărat în paralel.
*   **Explorare Sistematică:** Roboții colaborează prin intermediul unei memorii partajate, asigurându-se că preferă să viziteze celule neexplorate de echipă.
*   **Control Interactiv în Timp Real:** Un fir de execuție principal ascultă comenzile utilizatorului direct din consolă, permițând modificarea vitezei sau punerea pe pauză a tuturor firelor simultan.
*   **Daemon Thread:** Un thread care rulează în fundal se ocupă exclusiv de afișarea periodică a stării labirintului și de verificarea limitei de timp.

##  Arhitectura 

*   `Main`: Clasa principală care inițializează labirintul, personajele, firele de execuție și procesează comenzile utilizatorului.
*   `Maze`: Gestionează matricea de tip `Cell[][]`, logica de eliminare a pereților și validarea mutărilor.
*   `Cell`: Reprezintă o "cameră" din labirint, stochează starea pereților și prezența entităților.
*   `Bunny`: Implementează `Runnable`. Se mișcă aleatoriu prin labirint.
*   `Robot`: Implementează `Runnable`. Se mișcă strategic, consultând `SharedMemory` pentru a evita rutele deja explorate.
*   `SharedMemory`: Un obiect monitor partajat care reține harta celulelor explorate de roboți. Metodele sale sunt `synchronized` pentru a preveni coruperea datelor.

## Comenzile

| Comandă | Efect |
| :--- | :--- |
| `p` | **Pause:** Îngheață toate firele de execuție și oprește afișarea consolei. |
| `r` | **Resume:** Reia mișcarea personajelor și randarea labirintului. |
| `-` | **Slow down:** Mărește timpul de somn al thread-urilor cu 500ms. |
| `+` | **Speed up:** Scade timpul de somn al thread-urilor cu 500ms. |
| `x` / `stop` | **Stop:** Oprește curat toate firele de execuție și închide aplicația. |
