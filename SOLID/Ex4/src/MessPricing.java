public class MessPricing implements AddOnPricing {

    public AddOn type() {
        return AddOn.MESS;
    }

    public Money charge() {
        return new Money(1000.0);
    }
}