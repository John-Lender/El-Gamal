import java.math.BigInteger;
import java.util.Random;

public class MillerTest {

    public static Boolean definition(int n, int r){
        Random rnd = new Random();
        int t = n-1;
        int s = 0;
        while(t%2==0){
            t/=2;
            s+=1;
        }
        for (int i = 0; i < r; i++) {
            int a = rnd.nextInt(n-4)+2;
            int x = new BigInteger(String.valueOf(a)).modPow(new BigInteger(String.valueOf(n-1)), new BigInteger(String.valueOf(n))).intValue();
            if (x==1 || x == n-1){
                continue;
            }
            for (int j = 0; j < s-1; j++) {
                x = x = new BigInteger(String.valueOf(x)).modPow(new BigInteger(String.valueOf(2)), new BigInteger(String.valueOf(n))).intValue();
                if (x==1){
                    return false;
                }
                if (x == n-1){
                    continue;
                }
            }
            return false;
        }
        return true;
    }
}
