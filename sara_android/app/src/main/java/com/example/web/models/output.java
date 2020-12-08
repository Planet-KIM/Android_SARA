package com.example.web.models;


// https://walkplanetcat.com/api/xlsx?planetkey="planetkey"&output="coupledclutches.xlsx" //
// 위 처럼 정상적인 값이 입력되었을 때 밑에서 요구하는 값은 return합니다.
public class output {


    private String status;
    public String getStatus(){
        return status;
    }

    private String time;
    public String getTime(){
        return time;
    }

    private String id;
    public String getId() {
        return id;
    }

    private String parameter;
    public String getParameter() {
        return parameter;
    }

    private String unit;
    public String getUnit() {
        return unit;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
