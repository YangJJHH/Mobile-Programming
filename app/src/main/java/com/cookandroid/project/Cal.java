package com.cookandroid.project;

public class Cal {
    String result;

    public void addCal(int num1, int num2){
        result="두수의 덧셈="+(num1+num2);
    }
    public void subCal(int num1, int num2){
        result="두수의 뺄셈="+(num1-num2);
    }
    public void mulCal(int num1, int num2){
        result="두수의 곱셈="+(num1*num2);
    }
    public void divCal(int num1, int num2) {
        if (num2 == 0) {
            result="두수의 나눗셈 = 불능(분모=0)";
        } else {
            result = "두수의 나눗셈="+(num1 / num2);
        }
    }
    public String getResult(){
        return result;
    }

}
