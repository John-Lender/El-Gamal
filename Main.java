import java.math.BigInteger;
import java.util.Random;



public class Main {
    public static void main(String[] args) throws Exception {
        ElGamal test = new ElGamal();
        SignedMessage sm = test.signMissage("This is my new message");
        System.out.println(sm);
        System.out.println(test);
        System.out.println(test.checkCorrectSignature(sm));
//        BigInteger value = new BigInteger(String.valueOf("-261726"));
//        System.out.println(new BigInteger(String.valueOf("4909")).multiply(value).mod(new BigInteger(String.valueOf("7368"))));
        //System.out.println((new BigInteger(String.valueOf(23)).pow(5901)).mod(new BigInteger(String.valueOf(5902))));
//        System.out.println(new BigInteger(String.valueOf(1)).mod(new BigInteger(String.valueOf(5902))));
//          System.out.println(new BigInteger(String.valueOf(4093)).pow(7367).mod(new BigInteger(String.valueOf(7369))));
//b: 4170
//b = (-261726) * 4093^(-1)mod(7368)


    }


}
