package com.wancs.battle_run.global.common;

public final class InitializationByType {
    public static String stringCheck(String target){
        if(target == null){
            return "";
        }
        return target;
    }

    public static Long longCheck(Long target){
        if(target == null){
            return 0L;
        }
        return target;
    }

    public static Float floatCheck(Float target){
        if(target == null){
            return 0F;
        }
        return target;
    }

    public static Integer integerCheck(Integer target){
        if(target == null){
            return 0;
        }
        return target;
    }

    public static Double doubleCheck(Double target){
        if(target == null){
            return 0D;
        }
        return target;
    }
}
