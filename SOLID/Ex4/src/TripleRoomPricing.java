public class TripleRoomPricing implements RoomPricing {

    public int getRoomType() {
        return LegacyRoomTypes.TRIPLE;
    }

    public Money monthlyCharge() {
        return new Money(12000.0);
    }
}