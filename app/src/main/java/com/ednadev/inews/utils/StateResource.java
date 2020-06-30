package com.ednadev.inews.utils;

public class StateResource<T> {

    RegisterStatus status;
    String mes;

    public StateResource(RegisterStatus registerStatus, String message) {
        status = registerStatus;
        mes = message;
    }

    public static <T> StateResource<T> success(){
        return new StateResource<T>(RegisterStatus.SUCCESS, null);
    }

    public static <T> StateResource<T> loading() {
        return new StateResource<T>(RegisterStatus.LOADING, null);
    }

    public static <T> StateResource<T> error(String msg) {
        return new StateResource<T>(RegisterStatus.ERROR, msg);
    }

    enum RegisterStatus {
        SUCCESS, ERROR, LOADING
    }
}
