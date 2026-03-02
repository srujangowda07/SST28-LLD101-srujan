import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final TaxRule taxRule;
    private final DiscountRule discountRule;
    private final InvoiceFormatter formatter;

    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.store = new FileStore();
        this.taxRule = new TaxRules();
        this.discountRule = new DiscountRules();
        this.formatter = new InvoiceFormatter();
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double taxPct = taxRule.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountRule.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        String printable = formatter.format(out.toString());
        System.out.print(printable);

        store.save(invId, printable);

        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}