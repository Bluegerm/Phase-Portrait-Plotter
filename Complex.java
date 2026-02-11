public class Complex {
    double real;
    double imaginary;

    public Complex(double re, double imag){
        real = re;
        imaginary = imag;
    }

    public String toString(){
        return real+" + "+imaginary+"i";
    }

    public double getReal(){
        return real;
    }

    public double getImag(){
        return imaginary;
    }

    public Complex exp(){
        double a = real;
        double b = imaginary;
        return new Complex(Math.exp(a)*Math.cos(b), Math.exp(a)*Math.sin(b));
    }

    public Complex ln(){
        double a = real;
        double b = imaginary;
        return new Complex(0.5*Math.log(a*a+b*b), Math.atan2(b, a));
    }

    public Complex add(Complex a){
        return new Complex(real+a.getReal(), imaginary+a.getImag());
    }

    public Complex subtract(Complex a){
        return new Complex(real-a.getReal(), imaginary-a.getImag());
    }

    public Complex multiply(Complex n){
        double a = real;
        double b = imaginary;
        double c = n.getReal();
        double d = n.getImag();
        return new Complex(a*c-b*d, a*d+b*c);
    }

    public Complex divide(Complex n){
        double a = real;
        double b = imaginary;
        double c = n.getReal();
        double d = n.getImag();
        return new Complex((a*c+b*d)/(c*c+d*d), (c*b-a*d)/(c*c+d*d));
    }

    public Complex pow(Complex z){
        double a = real;
        double b = imaginary;
        Complex t = new Complex(a, b);
            t = t.ln();
            t=t.multiply(z);
            t=t.exp();
        return t;
    }

    public Complex sin(){
        Complex z = new Complex(real, imaginary);
        z = z.multiply(new Complex(0, 1)).exp().subtract(z.multiply(new Complex(0, -1)).exp()).divide(new Complex(0, 2));
        return z;
    }

    public Complex cos(){
        Complex z = new Complex(real, imaginary);
        z = z.multiply(new Complex(0, 1)).exp().add(z.multiply(new Complex(0, -1)).exp()).divide(new Complex(2, 0));
        return z;
    }

    public Complex tan(){
        Complex z = new Complex(real, imaginary);
        z = z.sin().divide(z.cos());
        return z;
    }

    public Complex gamma(){
        Complex z = new Complex(real-1, imaginary);
        Complex z1 = new Complex(1, 0);
        for(int i = 1; i<1000; i++){
            z1 = z1.multiply(new Complex(i, 0).divide(z.add(new Complex(i, 0))));
        }
        z1 = z1.multiply(z.multiply(new Complex(Math.log(999), 0)).exp());
        return z1;
    }

    public Complex zeta(){
        Complex z = new Complex(real, imaginary);
        Complex z1 = new Complex(0, 0);
        if(real > 1){
            for (int i = 1; i<1000; i++){
                z1 = z1.add(new Complex(1, 0).divide(new Complex(i, 0).pow(z)));
            }
            return z1;
        } else if (real <= 0) {
            for (int i = 1; i<1000; i++){
                z1 = z1.add(new Complex(1, 0).divide(new Complex(i, 0).pow(new Complex(1-real, -1*imaginary))));
            }
            return new Complex(2, 0).pow(z).multiply(new Complex(Math.PI, 0).pow(new Complex(real - 1, imaginary))).multiply(new Complex(Math.PI / 2 * real, Math.PI / 2 * imaginary).sin()).multiply(new Complex(1 - real, -1 * imaginary).gamma()).multiply(z1);
        } else {
           z = new Complex(2, 0).pow(z).multiply(new Complex(Math.PI, 0).pow(new Complex(real - 1, imaginary))).multiply(new Complex(Math.PI / 2 * real, Math.PI / 2 * imaginary).sin()).multiply(new Complex(1 - real, -1 * imaginary).gamma());
           return z;
        }
    }

    public Complex asin(){
        Complex z = new Complex(real, imaginary);
        return z.multiply(new Complex(0, 1)).add(new Complex(1, 0).subtract(z.pow(new Complex(2, 0))).pow(new Complex(0.5, 0))).ln().multiply(new Complex(0, -1));
    }

    public double Arg(){
        return Math.atan2(imaginary, real);
    }

    public Complex conj(){
        return new Complex(real, -1*imaginary);
    }

}
