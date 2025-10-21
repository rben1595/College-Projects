package com.example.fypblackjacksecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game_details", schema = "FYP-DB", catalog = "")
public class GameDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "game_id")
    private int gameId;
    @Basic
    @Column(name = "player_id")
    private int playerId;
    @Basic
    @Column(name = "total_bet")
    private String totalBet;
    @Basic
    @Column(name = "total_won")
    private String totalWon;
    @Basic
    @Column(name = "total_lost")
    private String totalLost;
    @Basic
    @Column(name = "number_hands_won")
    private String numberHandsWon;
    @Basic
    @Column(name = "game_start_time")
    private Date gameStartTime;
    @Basic
    @Column(name = "game_end_time")
    private Date gameEndTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDetails that = (GameDetails) o;
        return gameId == that.gameId && playerId == that.playerId && totalBet == that.totalBet && Objects.equals(totalWon, that.totalWon) && Objects.equals(totalLost, that.totalLost) && Objects.equals(numberHandsWon, that.numberHandsWon) && Objects.equals(gameStartTime, that.gameStartTime) && Objects.equals(gameEndTime, that.gameEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId, totalBet, totalWon, totalLost, numberHandsWon, gameStartTime, gameEndTime);
    }
}
