package com.forty7.toyoung.util;

import com.forty7.toyoung.model.Result;
import com.forty7.toyoung.model.Results;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class MiscUtil {

    static public Result getValidateError(BindingResult bindingResult) {
        if(bindingResult.hasErrors() == false)
            return null;
        Map<String,String> fieldErrors = new HashMap<String, String>();
        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getCode() + "|" + error.getDefaultMessage());
        }
        Result ret = new Result(422, "输入错误"); //rfc4918 - 11.2. 422: Unprocessable Entity
        ret.setData(fieldErrors);
        return ret;
    }


    static public Results getValidateErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors() == false){
            return null;
        }
        Map<String,String> fieldErrors = new HashMap<String, String>();
        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getCode() + "|" + error.getDefaultMessage());
        }
        Results ret = new Results(422, "输入错误"); //rfc4918 - 11.2. 422: Unprocessable Entity
        ret.putData("fieldErrors", fieldErrors);
        return ret;
    }

}
