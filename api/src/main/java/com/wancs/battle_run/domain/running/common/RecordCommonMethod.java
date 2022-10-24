package com.wancs.battle_run.domain.running.common;

import static com.wancs.battle_run.global.common.InitializationByType.checkFloat;
import static com.wancs.battle_run.global.common.InitializationByType.checkLong;

public final class RecordCommonMethod {

    public static double getSecondPace(Float distance, Long runningTime){
        if(checkFloat(distance) <= 0 || checkLong(runningTime) <= 0){
            return 0;
        }

        return runningTime / distance / 1000;
    }
}
