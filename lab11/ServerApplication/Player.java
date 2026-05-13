package org.example;

import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

@Entity
@Table(name = "players")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "PLAYER_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "total_score")
    private int score=0;

    @Transient
    public Socket socket;

    @Transient
    public BufferedReader in;

    @Transient
    public PrintWriter out;

    @Transient
    private long responseTime = 0;

    public Player() {}
    public Player(String name, Socket socket, BufferedReader in, PrintWriter out) {
        this.name = name;
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public void addScore()
    {
        this.score++;
    }

    public void addTime(long timeMs) {
        this.responseTime += timeMs;
    }

    public int getScore()
    {
        return score;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getResponseTime()
    {
        return responseTime;
    }
}