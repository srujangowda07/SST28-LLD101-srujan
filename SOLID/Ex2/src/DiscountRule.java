public interface DiscountRule {
    double discountAmount(String customerType, double subtotal, int distinctLines);
}