import lab1.Polynomial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Polynomial p = new Polynomial();
        //  System.out.println(p.evaluate(3));
//        double [] c1 = {6,5};
//        int [] c2 = {0,3};
        double [] c1 = {6,-4,5};
        int [] c2 = {0,1,3};
        Polynomial p1 = new Polynomial(c1,c2);
        double [] d1 = {-2,-9};
        int [] d2 = {1,4};
        Polynomial p2 = new Polynomial(d1,d2);
        Polynomial s1 = p1.add(p2);
        Polynomial s2 = p1.multiply(p2);

//        System.out.println("The length is "+s.array.length);
//        for (int i=0; i<s.exp.length; i++)
//            System.out.println(s.exp[i]);
//        for (int i=0; i<s.array.length; i++)
//            System.out.println(s.array[i]);

        System.out.println("s1(0) = " + s1.evaluate(0));
        System.out.println("s1(1) = " + s1.evaluate(1));
        if(s1.hasRoot(1))
            System.out.println("1 is a root of s2");
        else
            System.out.println("1 is not a root of s2");

        System.out.println("s2(0) = " + s2.evaluate(0));
        System.out.println("s2(1) = " + s2.evaluate(1));
        if(s2.hasRoot(1))
            System.out.println("1 is a root of s2");
        else
            System.out.println("1 is not a root of s2");
//
//        File file = new File("C:/UTSC/2nd year/CSCB07/lab2.txt");
//        Scanner scan = new Scanner(file);

        Polynomial po = new Polynomial(c1, c2);

//        for (int i=0; i<po.exp.length; i++){
//            System.out.println("Coe "+i+ " is "+po.array[i]);
//            System.out.println("Exp "+i+ " is "+po.exp[i]);
//        }

        po.saveToFile("function.txt");

//        String content = scan.nextLine().replace("-", "+-");
//        content = content.substring(1,content.length());
//        String [] lines = content.split("\\+");
//        for (String f:lines) System.out.println(f);
//         System.out.println(content);
//         System.out.println("lines length is "+ lines.length);
//        String function = "";
//        for (int i=0; i<c1.length; i++){
//            if (c2[i] == 0) function = function+(int)c1[i] + '+';
//            else {
//                function = function  +(int)c1[i] + 'x' + c2[i] + '+';
//            }
//        }
//        function = function.substring(0, function.length()-1);
//      function = function.replace("+-", "-");
//        System.out.println(function);


    }
}
