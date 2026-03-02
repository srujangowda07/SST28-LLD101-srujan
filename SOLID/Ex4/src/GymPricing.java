public class GymPricing implements AddOnPricing {

    public AddOn type() {
        return AddOn.GYM;
    }

    public Money charge() {
        return new Money(300.0);
    }
}