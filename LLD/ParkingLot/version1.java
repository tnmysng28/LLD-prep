
import java.util.List;
import java.util.Map;

enum VehicleType{
    CAR, 
    BIKE, 
    TRUCK,
    VAN,
    WHEELCHAIR
}

enum ParkingSpotType{
    COMPACT,
    SMALL,
    MEDIUM,
    LARGEs
}
   

class Vehicle{
    String vehicleNo;
    VehicleType vehicleType;

}

class ParkingSpot{
    String spotId;
    ParkingSpotType spotType;
    boolean isOccupied;

}

class ParkingFloor{

    Map<ParkingSpotType, List<ParkingSpot>> floors;

    ParkingSpot getAvailableParkingSpot(ParkingSpotType spotType){

        return null;
    }

}

