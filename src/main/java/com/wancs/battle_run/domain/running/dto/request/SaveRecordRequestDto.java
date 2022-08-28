package com.wancs.battle_run.domain.running.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class SaveRecordRequestDto {
    private float distance;
    private String runningTime;
    private String face;
    private int calorie;
    private MultipartFile imageFile;

    @Builder
    public SaveRecordRequestDto(float distance, String runningTime, String face, int calorie, MultipartFile imageFile){
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageFile = imageFile;
    }
}
