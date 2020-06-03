import java.math.BigInteger;

public class SignedMessage {
    public  String msg;
    public BigInteger a;
    public BigInteger b;
    public BigInteger y;

    public SignedMessage(String msg, BigInteger a, BigInteger b, BigInteger y) {
        this.msg = msg;
        this.a = a;
        this.b = b;
        this.y = y;

    }
    @Override
    public String toString() {
        return  "===============" + "\n" +
                "Message: " + msg + "\n" +
                "a: " + a + "\n" +
                "b: " + b + "\n" +
                "y: " + y + "\n" +
                "===============" + "\n";
    }
}
