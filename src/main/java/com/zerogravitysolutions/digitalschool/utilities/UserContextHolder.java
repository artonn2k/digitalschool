package com.zerogravitysolutions.digitalschool.utilities;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();

    public static UserContext getContext(){

        UserContext userCx = userContextThreadLocal.get();

        if(userCx == null){
            userCx = creatEmptyContext();
            userContextThreadLocal.set(userCx);
        }
        return userContextThreadLocal.get();
    }

    public static void setContext(UserContext userCx){

        userContextThreadLocal.set(userCx);
    }

    private static UserContext creatEmptyContext(){
        return new UserContext();
    }
}
