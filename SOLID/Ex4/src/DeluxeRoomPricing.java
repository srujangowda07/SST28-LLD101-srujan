public class DeluxeRoomPricing implements RoomPricing {

    public int getRoomType() {
        return LegacyRoomTypes.DELUXE;
    }

    public Money monthlyCharge() {
        return new Money(16000.0);
    }
}