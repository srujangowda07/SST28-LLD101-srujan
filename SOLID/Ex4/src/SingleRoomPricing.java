public class SingleRoomPricing implements RoomPricing {

    public int getRoomType() {
        return LegacyRoomTypes.SINGLE;
    }

    public Money monthlyCharge() {
        return new Money(14000.0);
    }
}