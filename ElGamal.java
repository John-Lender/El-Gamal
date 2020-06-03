import java.math.BigInteger;
import java.util.Random;

//проблема с поиском коэфф b по расширенному алгоритму Евклида
//не работает с большими числами проверка > 100000
//http://crypto-r.narod.ru/glava6/glava6_3.html - El Gamal

public class ElGamal {
    private Random rnd = new Random();
    private BigInteger ONE = new BigInteger(String.valueOf("1"));
    //G < P
    private BigInteger P;//197
    private BigInteger G;//199
    //1<X<=P-1
    private BigInteger X;//198 // Число Х является секретным ключом отправителя для подписывания документов и должно храниться в секрете.
    private BigInteger Y;
    private BigInteger K;
    private BigInteger a;
    private BigInteger b;
    private BigInteger m;
//        this.P = new BigInteger(String.valueOf(GeneratorEaseNumber.generateEV(1100,10000)));
//        this.G = new BigInteger(String.valueOf(GeneratorEaseNumber.generateEV(1000,P.intValue())));
//        this.X = new BigInteger(String.valueOf(rnd.nextInt(P.intValue()-1)+100));
//        this.K = new BigInteger(String.valueOf(GeneratorEaseNumber.generateEV(1,P.intValue()-1)));
//        this.Y = findY();

    public ElGamal() throws Exception {
        this.P = GeneratorEaseNumber.generateNbitEV(16);
        this.G = GeneratorEaseNumber.generateNlenEV(P.toString().length());
        this.X = GeneratorEaseNumber.generateNlenEV(P.toString().length()-1);
        this.K = GeneratorEaseNumber.generateNlenEV(P.toString().length()-1);
        this.Y = findY();
    }
    public ElGamal(int P, int G, int X, int K) throws Exception {
        this.P = new BigInteger(String.valueOf(P));
        this.G = new BigInteger(String.valueOf(G));
        this.X = new BigInteger(String.valueOf(X));
        this.K = new BigInteger(String.valueOf(K));
        this.Y = findY();
    }
    private BigInteger findY(){//Число Y является открытым ключом, используемым для проверки подписи отправителя. Число Y открыто передается всем потенциальным получателям документов.
        return G.modPow(X, P);
    }
    private BigInteger hash(String msg){
        return (new BigInteger(String.valueOf(msg.hashCode())).mod(P.subtract(ONE))).abs() ;
    }
    public SignedMessage signMissage(String msg) throws Exception {
        m = hash(msg);
        a = G.modPow(K, P);
        b = findB();
        checkCorrectEilerFun();
        return new SignedMessage(msg, a, b, Y);
    }
    private BigInteger findB(){
        BigInteger right = (X.multiply(new BigInteger(String.valueOf("-1"))).multiply(a).add(m)).mod(P.subtract(new BigInteger(String.valueOf("1"))));
        System.out.println(right.mod(P));
        for (BigInteger i = ONE; !P.subtract(ONE).equals(i); i = i.add(ONE)) {
            BigInteger left = K.multiply(i).mod(P.subtract(ONE));
            if (left.equals(right)){
                System.out.println("b = (m - X*a)*k^(-1) mod(P-1)");
                System.out.println("b = (" + m + " - " + X + " * " + a + ")" + " * " + K + "^(-1)" +  "mod(P-1)");
                System.out.println("b = (" +  (m.subtract(X.multiply(a))) + ")" + " * " + K + "^(-1)" +  "mod(" + P.subtract(ONE) + ")" );
                return i;
            }
        }
        return new BigInteger(String.valueOf(0));
    }
    private void checkCorrectEilerFun(){
        if (m.equals((X.multiply(a).add(K.multiply(b))).mod(P.subtract(ONE)))){
            return;
        }else{
            new Exception("Coeff b is not correct!");
        }
    }

    public  Boolean checkCorrectSignature(SignedMessage sm){
        BigInteger first = sm.y.modPow(a, P).multiply(sm.a.modPow(b,P)).mod(P) ;
        BigInteger second = G.modPow(hash(sm.msg), P);
        System.out.println("Y^a * a^b (mod Р) = " + first + "\n      G^m (mod Р) = " + second);
        if (first.equals(second)){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return  "===============" + "\n" +
                "P: " + P + "\n"+
                "G: " + G + "\n" +
                "X: " + X + "\n"+
                "Y: " + Y + "\n" +
                "K: " + K + "\n" +
                "a: " + a + "\n" +
                "b: " + b + "\n" +
                "m: " + m + "\n" +
                "==============="  + "\n";
    }
}
