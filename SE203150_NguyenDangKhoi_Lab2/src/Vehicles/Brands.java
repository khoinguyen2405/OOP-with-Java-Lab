package Vehicles;

public class Brands{
    private String BrandId;
    private String BrandName;
    private String SoundSystemBrand;
    private String Price;
    

    public Brands(String BrandId, String BrandName, String SoundSystemBrand, String Price) {
        this.BrandId = BrandId;
        this.BrandName = BrandName;
        this.SoundSystemBrand = SoundSystemBrand;
        this.Price = Price;
    }

    public String getBrandId() {
        return BrandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getSoundSystemBrand() {
        return SoundSystemBrand;
    }

    public String getPrice() {
        return Price;
    }

    public void setBrandId(String BrandId) {
        this.BrandId = BrandId;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public void setSoundSystemBrand(String SoundSystemBrand) {
        this.SoundSystemBrand = SoundSystemBrand;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-40s | %-18s | %-10s", BrandId, BrandName, SoundSystemBrand, Price);
    }
}

