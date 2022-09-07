package com.wancs.battle_run.domain.running.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Float distance;

    private String runningTime;

    private String face;

    private Integer calorie;

    private String imageUrl;

    @Builder
    public Record(Long id, Long userId, Float distance, String runningTime,
                  String face, Integer calorie, String imageUrl){
        this.id = id;
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }
}
