public class Polynomial {
    double [] array;

    public Polynomial(){
        this.array = new double [0];
    }
    public Polynomial(double [] arr){
        this.array = arr;
    }

    public Polynomial add(Polynomial poly) {
        Polynomial max;
        Polynomial min;
        if (this.array.length < poly.array.length) {
            max = poly;
            min = this;
        }
        else {
            max = this;
            min = poly;
        }

        double [] arr = new double[max.array.length];
        Polynomial p = new Polynomial(arr);
       // System.out.println("Max array length is: "+max.array.length);

        for (int i = 0; i<max.array.length; i++){
            if (i<min.array.length) {
                p.array[i] = min.array[i] + max.array[i];
            }
            else p.array[i] = max.array[i];
        }
        return p;
    }

    public double evaluate(double x){
        double res = 0;
        for (int i=0; i<this.array.length;i++){
            res += this.array[i]*Math.pow(x, i);
        }
        return res;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}
