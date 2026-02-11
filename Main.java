import javax.swing.*;

public class Main {
    public static Complex arrToComplex(double[] arr){
        return new Complex(arr[0], arr[1]);
    }

    public static double[] func(double[] c){
        // Do not change this
        Complex z = new Complex(arrToComplex(c).getReal(), arrToComplex(c).getImag());
        // Modify function here using methods from Complex class I made
        z = z.multiply(new Complex(Math.log(2), 0)).exp();
        // Modify matrix for vector function in R^2
        double[][] M = {{1, 0},
                        {0, 1}};
        //z = new Complex(z.getReal()*M[0][0]+z.getImag()*M[0][1], z.getReal()*M[1][0]+z.getImag()*M[1][1]);
        // Do not change this
        double a = z.getReal();
        double b = z.getImag();
        return new double[]{a, b};
    }

    public static double sig(double a){
        return 1-Math.exp(-1*a);
    }

    static double rad(double x, double y){
        return Math.pow(Math.pow(x,2)+Math.pow(y,2), 0.5);
    }
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Phase Portrait");
        window.setLocationRelativeTo(null);
        window.getContentPane().add(new ShapeDrawing());
        window.setVisible(true);
    }
}