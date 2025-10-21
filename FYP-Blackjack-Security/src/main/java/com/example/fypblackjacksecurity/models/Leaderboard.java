package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Leaderboard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "leaderboard_id")
    private int leaderboardId;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "amount_won")
    private int amountWon;
    @Basic
    @Column(name = "game_number")
    private int gameNumber;
    @Basic
    @Column(name = "player_id")
    private int playerId;
    @Basic
    @Column(name = "player_name")
    private String playerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leaderboard that = (Leaderboard) o;
        return leaderboardId == that.leaderboardId && amountWon == that.amountWon && gameNumber == that.gameNumber && playerId == that.playerId && Objects.equals(date, that.date) && Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaderboardId, date, amountWon, gameNumber, playerId, playerName);
    }

    public int compareTo(Leaderboard a) {
        return a.getAmountWon();
    }
}
