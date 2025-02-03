/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.util.Scanner;

import tool.inputter;

/**
 *
 * @author Duy
 */
public class Menu {
    
    public static int getChoice(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your options from 1 - " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        String s="";
        do{
            System.out.println("Enter valid value!");
            s=sc.nextLine();
        }
        while(s.isEmpty()||!s.matches("[\\d+]"));
        return Integer.parseInt(s);
    }
}

