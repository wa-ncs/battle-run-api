package com.wancs.battle_run.domain.running.common;

import org.springframework.stereotype.Component;

@Component
public class RecordCommonMethord {

    public String getMinuteFace(Float distance, Long runningTime){
        if(distance <= 0 || runningTime <= 0){
            return null;
        }

        double secondFace = runningTime / distance / 1000;

        int minute = (int) secondFace / 60;
        int second = (int) secondFace % 60;

        //4'15/km 형식으로 노출되기 위함
        String minuteFace = minute + "'" + second;

        return minuteFace;
    }
}
