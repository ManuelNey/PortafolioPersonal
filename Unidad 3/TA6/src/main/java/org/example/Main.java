package org.example;

import java.util.ArrayList;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    Stack<Character> stack = new Stack<>();
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.IsValid("{{{{{}}}}"));
    }
    public Boolean IsValid(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            char ch = cadena.charAt(i);
            if (ch == '{' )
            {
                stack.push(ch);
            }
            if (ch == '}' ){
                if (stack.isEmpty())
                {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}