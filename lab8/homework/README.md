# Maze Generator

O aplicație desktop Java performantă care permite generarea, editarea manuală, validarea și salvarea labirinturilor. Proiectul folosește o interfață grafică construită cu **Java Swing** și **Java2D**.

---

## Caracteristici

### 1. Generare Automată (Algoritm DFS)
* Generează labirinturi perfecte folosind algoritmul **Randomized Depth-First Search**, cu backtracking prin stivă.
* Garantează că orice două puncte din labirint sunt conectate printr-o cale unică.

### 2. Editare Manuală
* **Toggle Walls:** Utilizatorul poate adăuga sau elimina pereți prin simplu click pe aceștia.
* Logica de detectare a peretelui se bazează pe calculul distanței minime față de coordonatele click-ului.

### 3. Validare Drum (Algoritm BFS)
* Verifică dacă există un drum valid între punctul de **Start (0,0)** și cel de **Ieșire (N-1, N-1)**, dupa ce utilizatorul și-a creat labirintul.
* Folosește algoritmul **Breadth-First Search** pentru a găsi cea mai scurtă cale și a confirma traversabilitatea.

### 4. Persistența Datelor & Export
* **Serializare:** Salvează starea curentă a labirintului într-un fișier binar (`new_maze`) pentru a fi restaurată ulterior.
* **Export PNG:** Exportă labirintul ca imagine folosind `BufferedImage` și `ImageIO`.

---

## Detalii Tehnice

* **GUI:** Swing (JFrame, JPanel, JButton, JTextField, JOptionPane).
* **Grafică:** Java2D (Graphics2D).
* **Structuri de date:**
    * `Cell[][]` - Matrice pentru reprezentarea grilei.
    * `Stack<Cell>` - Pentru generarea labirintului (DFS).
    * `Queue<Cell>` - Pentru validarea drumului (BFS).
    * `Serializable` - Interfață pentru salvarea stării.

---

## Structura

- **`Main.java`**: Clasa principală care asamblează interfața.
- **`Canvas.java`**: Motorul grafic și logic al aplicației (randare, algoritmi, mouse events).
- **`Cell.java`**: Modelul de date pentru o singură unitate a labirintului.
- **`ConfigurationPanel.java`**: Panoul de sus pentru introducerea dimensiunilor.
- **`ControlPanel.java`**: Panoul de jos pentru butoanele de acțiune.

---

## Instrucțiuni de Utilizare

1.  **Setare:** Introduceți dimensiunea dorită în căsuța de text și apăsați **"Draw Grid"**.
2.  **Generare:** Apăsați **"Create"** pentru a genera un labirint aleatoriu.
3.  **Editare:** Dați click pe orice linie neagră (perete) pentru a o șterge sau pe spațiul dintre celule pentru a adăuga un perete.
4.  **Validare:** Apăsați **"Validate"** pentru a vedea dacă labirintul creat manual este rezolvabil.
5.  **Salvare:** Folosiți **"Save"** pentru a păstra progresul și **"Load"** pentru a-l recupera.
6.  **Exportare:** Folosiți **"Export"** pentru a exporta labirintul curent într-un fișier PNG.
