package com.wancs.battle_run.global.common;

public final class InitializationByType {
    public static String checkString(String target){
        if(target == null){
            return "";
        }
        return target;
    }

    public static Long checkLong(Long target){
        if(target == null){
            return 0L;
        }
        return target;
    }

    public static Float checkFloat(Float target){
        if(target == null){
            return 0F;
        }
        return target;
    }

    public static Integer checkInteger(Integer target){
        if(target == null){
            return 0;
        }
        return target;
    }
}
