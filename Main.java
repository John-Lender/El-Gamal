import java.math.BigInteger;
import java.util.Random;



public class Main {
    public static void main(String[] args) throws Exception {
        ElGamal test = new ElGamal();
        SignedMessage sm = test.signMissage("This is my new message");
        System.out.println(sm);
        System.out.println(test);
        System.out.println(test.checkCorrectSignature(sm));
    }


}
