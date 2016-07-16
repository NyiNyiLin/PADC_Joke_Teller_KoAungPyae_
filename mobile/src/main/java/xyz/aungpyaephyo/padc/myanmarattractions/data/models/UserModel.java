package xyz.aungpyaephyo.padc.myanmarattractions.data.models;

import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.UserVO;

/**
 * Created by IN-3442 on 16-Jul-16.
 */
public class UserModel {
    private static UserModel objInstance;

    private UserVO userVO;

    private UserModel(){}

    public static UserModel getInstance(){
        if(objInstance == null){
            objInstance = new UserModel();
        }
        return objInstance;
    }
    public boolean isUserLogin(){
        return userVO != null;
    }
}
