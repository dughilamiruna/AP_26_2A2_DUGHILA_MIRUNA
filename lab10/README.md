# Java Multiplayer Quiz Game

Acesta este un joc de trivia multiplayer implementat în Java, bazat pe o arhitectură **Client-Server** folosind **TCP/IP Sockets** și programare concurentă (**Multithreading**). Jocul testează cunoștințele jucătorilor contratimp, departajarea în caz de egalitate făcându-se pe baza vitezei de reacție.

## Funcționalități 

* **Arhitectură Client-Server:** Comunicare bidirecțională robustă prin socket-uri pe un port.
* **Matchmaking (1v1):** Serverul așteaptă conectarea a doi clienți pentru a porni un meci. Mai multe meciuri pot rula simultan datorită unui `ExecutorService` (Thread Pool).
* **Blitz Time Control:** Fiecare întrebare are o limită de timp de 10 secunde. Timer-ul este gestionat asincron folosind `Callable`, `Future` și `invokeAll`, înregistrând timpul exact de răspuns pentru fiecare jucător.
* **Sistem de Scor și Tie-Breaker:** Dacă la finalul meciului ambii jucători au același punctaj, câștigătorul este desemnat jucătorul care a avut timpul total de răspuns mai mic.
* **Client Asincron (Dual-Threaded):** Clientul folosește două fire de execuție separate, unul pentru a citi input-ul de la tastatură si altul pentru a asculta permanent și a afișa mesajele primite de la server în timp real.
* **Graceful Shutdown:** Serverul se închide curat, eliberând resursele și Thread Pool-ul la oprirea aplicației.

## Structura

1. **ServerApplication**
   * `GameServer.java` - Așteaptă conexiuni, asociază câte 2 jucători și delegă meciurile către Thread Pool.
   * `QuizMatch.java` - Conține logica jocului, trimite întrebările, procesează răspunsurile asincron și calculează scorul si timpul.
   * `Player.java` - Modelul de date pentru un client conectat.
   * `Question.java` - Modelul pentru o întrebare, variante de răspuns și verificarea corectitudinii.

2. **ClientApplication**
   * `GameClient.java` - Gestionează conexiunea la server, citirea comenzilor de la tastatură și afișarea răspunsurilor primite.

## Formatul Fișierului cu Întrebări

Întrebările sunt stocate într-un fișier text. Formatul folosește caracterul `|` ca delimitator:
`[Întrebare] | [Opțiunea 1] | [Opțiunea 2] | [Opțiunea 3] | [Opțiunea 4] | [Index Răspuns Corect (1-4)]`
