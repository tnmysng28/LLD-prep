
import java.util.Map;

enum PaymentType{
    UPI, 
    CARD,
    CASH
}

interface PaymentStrategy{

    boolean pay(PaymentRequest request);

}

class PaymentRequest {
    String id;
    PaymentType type;
    double amount;
    String userId;
}

class UpiPaymentRequest extends PaymentRequest {
    String upiId;
    String deviceId;
}

class CardPaymentRequest extends PaymentRequest {
    String cardNumber;
    String cvv;
    String expiryDate;
}

class NetbankingPaymentRequest extends PaymentRequest {
    String bankName;
    String accountNumber;
    String ifscCode;
}

class CashPaymentRequest extends PaymentRequest {
    String collectionPointId;
    String proofOfPayment;
}


class PaymentStrategyFactory{

   private final Map<PaymentType, PaymentStrategy> m;
    
    public PaymentStrategyFactory(Map<PaymentType, PaymentStrategy> m){
        this.m = m;
    }

    PaymentStrategy getStrategy(PaymentType type){
        PaymentStrategy strategy = m.get(type);
        if(strategy == null){
            throw new IllegalArgumentException("Strategy not found for type: " + type);
        }
        return strategy;
    }


}