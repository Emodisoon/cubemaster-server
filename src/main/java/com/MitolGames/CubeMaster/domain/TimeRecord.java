package com.MitolGames.CubeMaster.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "timerecord_table")
public class TimeRecord {

    @Column(name = "id", updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "time", updatable = false, nullable = false)
    Long time;

    @JoinColumn(name="user_id", updatable = false, nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    ApplictaionUser user;

    @Column(name = "Scramble")
    String scramble;

    private long userID;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    @Column(name = "Date")
    private LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public ApplictaionUser getUser() {
        return user;
    }

    public void setUser(ApplictaionUser user) {
        this.user = user;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }
}
