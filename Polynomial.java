import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
    double [] array;
    int [] exp;

    public Polynomial(){
        this.array = new double [0];
        this.exp = new int [0];
    }
    public Polynomial(double [] arr, int [] ex){
        this.exp = ex;
        this.array = arr;
    }

    public Polynomial (File file) throws IOException {
        Scanner scan = new Scanner(file);
        String str = scan.nextLine().replace("-", "+-");
        scan.close();
        if (str.charAt(0) == '+') str = str.substring(1, str.length());

        String [] lines = str.split("\\+");
        int len = lines.length;
        double [] coe = new double [len];
        int [] ex = new int [len];

        for (int i=0; i< len; i++){
            int idx = lines[i].indexOf('x');
            if (idx == -1){
                coe[i] = Integer.parseInt(lines[i]);
                ex[i] = 0;
            }
            else {
                coe[i] = Integer.parseInt(lines[i].substring(0,idx));
                ex[i] = Integer.parseInt(lines[i].substring(idx+1));
            }
        }
        this.array = coe;
        this.exp = ex;
    }

    public int dup (int [] arr){
        int total = 0;
        for (int i=0; i<arr.length-1; i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i] == arr[j]) total++;
            }
        }
        return total;
    }

    public lab1.Polynomial sort (double [] coe, int [] exp){
        double [] c = coe;
        int [] e = exp;
        int len = coe.length;

        for (int i=0; i<len; i++){
            int smallest = i;
            for (int j=i+1; j<len; j++){
                if (exp[j] < exp[smallest])  smallest = j;
            }
            if (exp[smallest] < exp[i]){
                int tmp1 = exp[smallest];
                double tmp2 = coe[smallest];
                exp[smallest] = exp[i];
                coe[smallest] = coe[i];
                exp[i] = tmp1;
                coe[i] = tmp2;
            }
        }
        lab1.Polynomial p = new lab1.Polynomial(c,e);
        return p;
    }
    public double [] convert(double [] coe, int [] ex, int len){
        double [] a = new double [len];
        for (int i=0; i<ex.length; i++)
            a[ex[i]] = coe[i];

        return a;
    }
    public lab1.Polynomial add(lab1.Polynomial poly) {
        int [] new_array = new int [this.exp.length+poly.exp.length];
        for (int i=0; i<this.exp.length; i++) new_array[i] = this.exp[i];
        for (int i=this.exp.length; i< new_array.length; i++) new_array[i] = poly.exp[i- exp.length];
        // int len = this.exp.length+ poly.exp.length - dup(this.exp, poly.exp);
        int len = this.exp.length+ poly.exp.length - dup(new_array);
        int len1 = Math.max(this.exp[this.exp.length-1], poly.exp[poly.exp.length-1])+1;
        double [] coe = new double[len];
        int [] ex = new int [len];
        double [] a = convert(this.array, this.exp, len1);
        double [] b = convert(poly.array, poly.exp, len1);
        double [] ab = new double [Math.max(this.exp[this.exp.length-1], poly.exp[poly.exp.length-1])+1];

        for (int i=0; i<ab.length; i++)
            ab[i] = a[i] + b[i];

        int index = 0;
        for (int i=0; i<ab.length; i++){
            if (ab[i]!=0){
                coe[index] = ab[i];
                ex[index] = i;
                index++;
            }
        }

        lab1.Polynomial p = new lab1.Polynomial(coe, ex);
        return p;
    }

    public lab1.Polynomial multiply (lab1.Polynomial poly){
        int len = this.exp.length*poly.exp.length;

        int [] merge_exp = new int [len];
        double [] merge_coe = new double [len];
        for (int i=0; i<this.exp.length;i++){
            for (int j=0; j<poly.exp.length; j++){
                int index = i*poly.exp.length+j;
                merge_exp[index] = this.exp[i]+poly.exp[j];
                merge_coe[index] = this.array[i]*poly.array[j];
            }
        }

        int [] real_exp = new int [len-dup(merge_exp)] ;
        double [] real_coe = new double [len-dup(merge_exp)] ;

        for (int i=0; i<merge_exp.length-1; i++){
            for (int j=i+1; j<merge_exp.length; j++){
                if ( merge_exp[i] != 0 && merge_exp[i] == merge_exp[j]) {
                    merge_exp[j] = 0;
                    merge_coe[i]+=merge_coe[j];
                    merge_coe[j] = 0;
                }
            }
        }

        int idx = 0;
        for (int i=0; i<merge_exp.length; i++){
            if (merge_exp[i] != 0){
                real_exp[idx] = merge_exp[i];
                real_coe[idx] = merge_coe[i];
                idx++;
            }
        }

        lab1.Polynomial p = sort(real_coe, real_exp);
        return p;
    }
    public double evaluate(double x){
        double res = 0;
        for (int i=0; i<this.array.length;i++){
            res += this.array[i]*Math.pow(x, this.exp[i]);
        }
        return res;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public void saveToFile(String name) throws IOException {
        FileWriter write = new FileWriter(name);
        String function = "";

        for (int i=0; i<this.exp.length; i++){
            if (this.exp[i] == 0) function = function+(int)this.array[i] + '+';
            else {
                function = function  +(int)this.array[i] + 'x' + this.exp[i] + '+';
            }
        }
        function = function.substring(0, function.length()-1);
        function = function.replace("+-", "-");

        // System.out.println("file is "+function);

        write.write(function);
        write.close();
    }
}
