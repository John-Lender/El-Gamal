import java.math.BigInteger;
import java.util.Random;

public class GeneratorEaseNumber {
    private static Random rnd = new Random();
    private static MillerTest millerTest = new MillerTest();
    private static SoloveyStrassenTest ssTest = new SoloveyStrassenTest();

    public static BigInteger generatorRndValue(int numberOfDigits){//передается количество цифр
        String value = "";
        int len = rnd.nextInt(numberOfDigits-1)+2;
        for (int i = 0; i < len; i++) {
            value += String.valueOf(rnd.nextInt(9));
        }
        return new BigInteger(value);
    }
    public static BigInteger generateEV(int numberOfDigits){
        while (true){
            BigInteger value = generatorRndValue(numberOfDigits);
            if (SoloveyStrassenTest.definition(value, new BigInteger(String.valueOf(100))))
                return value;
        }
    }
    private static int generatorRndValue(int start, int stop){
        return rnd.nextInt(stop-start)+start;
    }
    public static int generateEV(int start, int stop) throws Exception {
        if (stop-start < 100){//100
            throw new Exception("Small borders");
        }
        while (true){
            int value = generatorRndValue(start,stop);
            if (MillerTest.definition(value,100) && SoloveyStrassenTest.definition(value, 100)){
                return value;
            }
        }
    }
    public static BigInteger generateNbitEV(int valueBit){
        return generateEV(new BigInteger(String.valueOf(2)).pow(valueBit).bitLength());
    }
    public static  BigInteger generateNlenEV(int len){
        while(true){
            BigInteger value = generatorRndValue(len-1);
            if (SoloveyStrassenTest.definition(value, new BigInteger("100"))){
                return value;
            }
        }
    }
}
