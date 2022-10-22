package com.wancs.battle_run.domain.running.common;

import static com.wancs.battle_run.global.common.InitializationByType.floatCheck;
import static com.wancs.battle_run.global.common.InitializationByType.longCheck;

public final class RecordCommonMethod {

    public static double getSecondPace(Float distance, Long runningTime){
        if(floatCheck(distance) <= 0 || longCheck(runningTime) <= 0){
            return 0;
        }

        return runningTime / distance / 1000;
    }
}
