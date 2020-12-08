package com.example.web.models;


// https://walkplanetcat.com/api/xlsx?planetkey="planetkey"&input="coupledclutches.xlsx" //
// 위 처럼 정상적인 값이 입력되었을 때 밑에서 요구하는 값은 return합니다.
public class PostItem {

    /**
     * {
     *     "address": 1,
     *     "email": 1,
     *     "name": "sunt aut facere repellat ~~~",
     *     "body": "quia et suscipit\nsuscipit ~~~"
     * },
     */

    /*
    private String address;
    private String email;
    private String name;

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName(){
        return name;
    }
     */

    // 현재 api에는 id가 요구되지않아 이는 호출하지 않아도 좋습니다.
    private String id;
    public String getId() {
        return id;
    }

    // Parameters 값을 return 해줍니다.
    private String parameter;
    public String getParameter() {
        return parameter;
    }

    // Units 값을 return 해줍니다.
    private String unit;
    public String getUnit() {
        return unit;
    }

    // Values 값을 return 해줍니다.
    private String value;
    public String getValue() { return value; }

}
