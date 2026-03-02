public class LaundryPricing implements AddOnPricing {

    public AddOn type() {
        return AddOn.LAUNDRY;
    }

    public Money charge() {
        return new Money(500.0);
    }
}