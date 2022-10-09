package com.wancs.battle_run.domain.running.common;

public final class RecordCommonMethod {

    public static double getSecondFace(Float distance, Long runningTime){
        if(distance <= 0 || runningTime <= 0){
            return 0;
        }

        return runningTime / distance / 1000;
    }

    public static String getFace(Float distance, Long runningTime){
        if(distance <= 0 || runningTime <= 0){
            return null;
        }

        double secondFace = getSecondFace(distance, runningTime);

        int minute = (int) secondFace / 60;
        int second = (int) secondFace % 60;

        //4'15/km 형식으로 노출되기 위함
        String minuteFace = minute + "'" + second;

        return minuteFace;
    }
}
