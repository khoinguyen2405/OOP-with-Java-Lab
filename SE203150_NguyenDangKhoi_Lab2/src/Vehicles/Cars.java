package Vehicles;


public class Cars{
    private String CarID;
    private String BrandID;
    private String CarColor;
    private String FrameID;
    private String EngineID;
    private String BrandName;

    public Cars(String CarID, String BrandID, String CarColor, String FrameID, String EngineID) {
        this.CarID = CarID;
        this.BrandID = BrandID;
        this.CarColor = CarColor;
        this.FrameID = FrameID;
        this.EngineID = EngineID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getCarID() {
        return CarID;
    }

    public String getBrandID() {
        return BrandID;
    }

    public String getCarColor() {
        return CarColor;
    }

    public String getFrameID() {
        return FrameID;
    }

    public String getEngineID() {
        return EngineID;
    }

    public void setCarID(String CarID) {
        this.CarID = CarID;
    }

    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    public void setCarColor(String CarColor) {
        this.CarColor = CarColor;
    }

    public void setFrameID(String FrameID) {
        this.FrameID = FrameID;
    }

    public void setEngineID(String EngineID) {
        this.EngineID = EngineID;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-10s | %-10s | %-10s | %-10s", CarID, BrandID, CarColor, FrameID, EngineID);
    }
    
    
}

