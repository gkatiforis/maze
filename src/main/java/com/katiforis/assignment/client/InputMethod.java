package com.katiforis.assignment.client;

/**
 * Holds the possible input methods
 */
public enum InputMethod {

    FROM_FILE(1),
    FROM_CACHE(2);

    int code;

    InputMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static boolean isValid(int inputMethod) {
        for(InputMethod method:values()){
            if(method.getCode() == inputMethod){
                return true;
            }
        }
        return false;
    }
}
