import java.math.BigInteger;
import java.util.Random;

public class SoloveyStrassenTest {
    public static Boolean definition(BigInteger n, BigInteger r){
        Random rnd = new Random();
        if (n.mod(new BigInteger(String.valueOf("2"))).equals(new BigInteger("0"))){
            return false;
        }
        if (n.mod(new BigInteger(String.valueOf("3"))).equals(new BigInteger("0"))){
            return false;
        }
        BigInteger j = new BigInteger("0");
        BigInteger jacobi = new BigInteger("0");
        for (int i = 0; r.compareTo(new BigInteger(String.valueOf(i))) != 0; i++) {
            //System.out.println(i);
            //int a = rnd.nextInt(n-4)+3;
            BigInteger a = GeneratorEaseNumber.generatorRndValue(n.toString().length());
            //System.out.println(a);
//            if (gcd(a,n) != 1){
//                return false;
//            }
//            0: Когда оба равны .
//            1: Когда первое больше второго (одно в скобках).
//           -1: Когда первое меньше секунды.
            //System.out.println(a.gcd(n).compareTo(new BigInteger(String.valueOf(1))) != 0);
            if (!a.gcd(n).equals(new BigInteger("1"))){
                return false;
            }
            //j = new BigInteger(String.valueOf(a)).modPow(new BigInteger(String.valueOf((n-1)/2)), new BigInteger(String.valueOf(n))).intValue();
            j = a.modPow((n.subtract(new BigInteger(String.valueOf("1")))).divide(new BigInteger(String.valueOf("2"))), n);
            //j = a.modPow(new BigInteger(String.valueOf((n.intValue()-1)/2)), n);
            jacobi = symbolJacobi(a,n);
            //System.out.println(j + " " + jacobi);
            //System.out.println(jacobi);
//            if (jacobi == -1){
//                jacobi=n-1;
//            }
            if (jacobi.equals(new BigInteger("-1"))) {
                jacobi = n.subtract(new BigInteger(String.valueOf(1)));
            }
//            if (j != jacobi){
//                return false;
//            }

            if (!j.equals(jacobi)){
                return false;
            }
            //System.out.println("ok");
        }
        return true;
    }

    public static BigInteger symbolJacobi(BigInteger n, BigInteger m){
        //int s = 1;
        BigInteger s = new BigInteger("1");
//        if (gcd(n,m) != 1){
//            return 0;
//        }
        if (!n.gcd(m).equals(new BigInteger("1"))){
            return new BigInteger(String.valueOf(0));
        }
        while (true){
            //n = n%m;
            n = n.mod(m);
            //int k = 0;
            BigInteger k = new BigInteger("0");
//            while (n % 2 == 0){
//                n = n >> 1;
//                k +=1;
//            }
            while (n.mod(new BigInteger(String.valueOf(2))).equals(new BigInteger("0"))){
                n = n.shiftRight(1);
                k = k.add(new BigInteger(String.valueOf(1)));
            }
//            k %=2;
            k = k.mod(new BigInteger(String.valueOf(2)));
//            if (k==1 && (m%8 == 3 || m % 8==5)){
//                s *=-1;
//            }
            if (k.equals(new BigInteger("1")) && (m.mod(new BigInteger(String.valueOf(8))).equals(new BigInteger("3")) || m.mod(new BigInteger(String.valueOf(8))).equals(new BigInteger("5")))){
                s = s.multiply(new BigInteger(String.valueOf(-1)));
            }
//            if (n == 1 || (n == m-1 && m %4 ==1)){
//                return s;
//            }
            if (n.equals(new BigInteger("1")) || (n.equals(m.subtract(new BigInteger(String.valueOf(1)))) && m.mod(new BigInteger(String.valueOf(4))).equals(new BigInteger("1")))){
                return s;
            }
//            if (n == m-1 && m % 4 == 3){
//                return -s;
//            }
            if (n.equals(m.subtract(new BigInteger(String.valueOf(1)))) && m.mod(new BigInteger(String.valueOf(4))).equals(new BigInteger("3"))){
                return s.multiply(new BigInteger(String.valueOf(-1)));
            }
//            int t = n;
//            n = m;
//            m = t;
            BigInteger t = n;
            n = m;
            m = t;
//            if ((n-1)%4!=0 && (m-1)%4!=0){//Нужно проверить эту строку
//                s*=-1;
//            }
            if ( !n.subtract(new BigInteger(String.valueOf(1))).mod(new BigInteger(String.valueOf(4))).equals(new BigInteger("0")) &&  !m.subtract(new BigInteger(String.valueOf(1))).mod(new BigInteger(String.valueOf(4))).equals(new BigInteger("0"))){
                s = s.multiply(new BigInteger(String.valueOf(-1)));
            }

        }
    }
    public static Boolean definition(int n, int r){
        Random rnd = new Random();
        if (n%2==0){
            return false;
        }
        if (n % 3==0){
            return false;
        }
        int j = 0;
        int jacobi = 0;
        for (int i = 0; i < r; i++) {
            int a = rnd.nextInt(n-4)+3;
            if (gcd(a,n) != 1){
                return false;
            }
            j = new BigInteger(String.valueOf(a)).modPow(new BigInteger(String.valueOf((n-1)/2)), new BigInteger(String.valueOf(n))).intValue();

            jacobi = symbolJacobi(a,n);
            if (jacobi == -1){
                jacobi=n-1;
            }
            //System.out.println(j + " " + jacobi);
            if (j != jacobi){
                return false;
            }
        }
        return true;
    }
    public static int symbolJacobi(int n, int m){
        int s = 1;
        if (gcd(n,m) != 1){
            return 0;
        }
        while (true){
            n = n%m;
            int k = 0;
            while (n % 2 == 0){
                n = n >> 1;
                k +=1;
            }
            k %=2;
            if (k==1 && (m%8 == 3 || m % 8==5)){
                s *=-1;
            }
            if (n == 1 || (n == m-1 && m %4 ==1)){
                return s;
            }
            if (n == m-1 && m % 4 == 3){
                return -s;
            }
            int t = n;
            n = m;
            m = t;
            if ((n-1)%4!=0 && (m-1)%4!=0){//Нужно проверить эту строку
                s*=-1;
            }

        }
    }

    private static long gcd(long a, long b)
    {
        while (b != 0)
            b = a % (a = b);
        return Math.abs(a);
    }
}
