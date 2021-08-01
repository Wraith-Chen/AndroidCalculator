package com.example.androidcalculator;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToSuffix {
    //中缀转后缀
    public static List<String> Infix(String str) {

        List<String> list = new ArrayList<>();//储存中间结果结果队列
        Stack<Character> stack = new Stack<>();//运算符栈
        StringBuilder sb = new StringBuilder("");//储存数字

        for (int i = 0; i < str.length(); i++) {

            // 如果是数字
            if ((i == 0 && str.charAt(i) != '(') || (i != 0 && isDigit(str.charAt(i), str.charAt(i - 1)))) {
                sb.append(str.charAt(i));
                // 如果是最后一位 或者下一位是字符，数字添加到队列,sb清空
                if (i == str.length() - 1 || (i + 1 < str.length() && isSymbol(str.charAt(i + 1)))) {
                    list.add(sb.toString());
                    sb = new StringBuilder("");
                }

                // 如果是括号
            } else if (isBracket(str.charAt(i))) {
                // 如果是左括号 直接入栈
                if (str.charAt(i) == '(') {
                    stack.push(str.charAt(i));
                } else {
                    // 右括号
                    // 将元素出栈 添加到list直到遇到'(',将这一对 '(' ')' 舍去
                    char temp;
                    while ((temp = stack.pop()) != '(') {
                        list.add(temp + "");
                    }
                }

                // 如果是操作符
            } else if (isOperation(str.charAt(i))) {
                while (true) {
                    // 空的栈直接入栈
                    if (stack.isEmpty()) {
                        stack.push(str.charAt(i));
                        break;
                        // 如果栈顶的符号优先级小于 扫描到的符号 入栈
                    } else if (getPriority(stack.peek()) < getPriority(str.charAt(i))) {
                        stack.push(str.charAt(i));
                        break;
                        // 栈顶的符号优先级大于等于 扫描到的符号 出栈给list,并继续扫描栈顶下一个符号
                    } else {
                        list.add(stack.pop() + "");
                    }
                }
            }
        }

        // 将剩余的符号全部入list
        while (!stack.isEmpty()) {
            list.add(stack.pop() + "");
        }
        return list;
    }

    //后缀表达式
    public static String Suffix(List<String> suffixExp) {

        Stack<BigDecimal> numStack = new Stack<>();

        for (String str : suffixExp) {
            // 如果是操作符
            if (str.length() == 1 && isOperation(str.charAt(0))) {
                BigDecimal num2 = numStack.pop();
                BigDecimal num1 = numStack.pop();
                numStack.push(calcValueOfTwoNum(num1, num2, str));
            } else {
                numStack.push(new BigDecimal(str));
            }
        }
        return String.valueOf(numStack.peek());
    }

    //后缀表达式计算
    public static BigDecimal calcValueOfTwoNum(BigDecimal num1, BigDecimal num2, String op) {
        switch (op) {
            case "+":
                return num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "*":
                return num1.multiply(num2);
            case "/":
                if (num2.signum() == 0) {
                    throw new RuntimeException("Error");
                }
                // 除法保留2位小数，四舍五入
                return num1.divide(num2, 2, RoundingMode.HALF_UP);
        }
        throw new RuntimeException("Error");
    }

    // 获取优先级
    public static int getPriority(char ch) {
        switch (ch) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        throw new RuntimeException("Error");
    }

    //符号判断
    public static boolean isSymbol(char ch) {
        return isOperation(ch) || isBracket(ch);
    }

    //操作符
    public static boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //括号判断
    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

    public static boolean isDigit(char ch, char leftBracket) {
        // 如果前一个是左括号 右边的数字可能带有正负号
        if (leftBracket == '(') {
            return ch == '-' || ch == '+' || (ch >= 48 && ch <= 57);
        }
        // 前一个符号不是左括号只能是数字或小数点
        return (ch >= 48 && ch <= 57) || ch == '.';
    }
}


