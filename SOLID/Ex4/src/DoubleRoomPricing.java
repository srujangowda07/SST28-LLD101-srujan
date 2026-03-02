public class DoubleRoomPricing implements RoomPricing {

    public int getRoomType() {
        return LegacyRoomTypes.DOUBLE;
    }

    public Money monthlyCharge() {
        return new Money(15000.0);
    }
}