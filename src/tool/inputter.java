package tool;

import java.util.Scanner;


public class inputter {
    /**
     * allow enter from keyboard
     * @param mess : message to show
     * @return the mess(age) that user input
     */

    public String getStringNotNull(String inpMsg, String errMsg){
       
            Scanner sc = new Scanner(System.in);
            System.out.println(inpMsg);
           String str="";
           for(;;){
                try {
                     str= sc.nextLine();
                    if (str.isEmpty()) {
                        throw new Exception();
                    }
                    return str;
                } catch (Exception e) {
                    System.out.println(errMsg);
                }
           }
        }
    

    public  String inputString(String mess){
        String kq="";
        Scanner sc = new Scanner(System.in);
        System.out.println(mess);
        try{
            kq=sc.nextLine();
        }
        catch(Exception e){
            System.out.println("Invalid input, please enter a string");
            kq=inputString(mess);
        }
        return kq;
    }

    public  int inputInt(String mess){
        int kq=0;
        String t=inputString(mess);
        try{
            kq=Integer.parseInt(t);
        }
        catch(Exception e){
            System.out.println("Invalid input, please enter an integer");
            kq=inputInt(mess);
        }
       
        return kq;
    }

    public  double inputDouble(String mess){
        double kq=0;
        String t=inputString(mess);
       try{
            kq=Double.parseDouble(t);
        }
        catch(Exception e){
            System.out.println("Invalid input, please enter a double");
            kq=inputDouble(mess);
       }
        return kq;
    }


}
