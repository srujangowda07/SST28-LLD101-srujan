import java.util.*;

public class HostelFeeCalculator {

    private FakeBookingRepo repo;
    private List<RoomPricing> roomPricings;
    private List<AddOnPricing> addOnPricings;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;

        roomPricings = List.of(
                new SingleRoomPricing(),
                new DoubleRoomPricing(),
                new TripleRoomPricing(),
                new DeluxeRoomPricing()
        );

        addOnPricings = List.of(
                new MessPricing(),
                new LaundryPricing(),
                new GymPricing()
        );
    }

    public void process(BookingRequest req) {

        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {

        Money total = new Money(0.0);

        for (RoomPricing r : roomPricings) {
            if (r.getRoomType() == req.roomType) {
                total = total.plus(r.monthlyCharge());
                break;
            }
        }

        for (AddOn a : req.addOns) {
            for (AddOnPricing p : addOnPricings) {
                if (p.type() == a) {
                    total = total.plus(p.charge());
                }
            }
        }

        return total;
    }
}