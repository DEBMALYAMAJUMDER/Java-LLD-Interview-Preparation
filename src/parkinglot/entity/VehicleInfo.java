package parkinglot.entity;

public class VehicleInfo {
    private String vehicleType;
    private int vehicleNo;

    public VehicleInfo(String vehicleType, int vehicleNo) {
        this.vehicleType = vehicleType;
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(int vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}
