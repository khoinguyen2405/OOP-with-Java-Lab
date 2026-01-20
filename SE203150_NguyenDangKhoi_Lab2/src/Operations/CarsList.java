package Operations;

import Vehicles.Cars;
import Vehicles.Brands;
import Input.InputData;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class CarsList extends ArrayList<Cars>{

    InputData input = new InputData();
    private static final String DATA_FILE = "cars.txt";
    Scanner sc = new Scanner(System.in);


    public void LoadFromCarFilewithoutprint() {
        try ( BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            this.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    //First split ","
                    String CarId = parts[0].trim();
                    String BrandId = parts[1].trim();
                    String CarColor = parts[2].trim();
                    String FrameId = parts[3].trim();
                    String EngineId = parts[4].trim();

                    if (CarId.isEmpty() || BrandId.isEmpty() || CarColor.isEmpty() || FrameId.isEmpty() || EngineId.isEmpty()) {
                        continue;
                    }
                    this.add(new Cars(CarId, BrandId, CarColor, FrameId, EngineId));
                }
            }
            
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }

// 6)List all Cars in ascending order of brand names                   //BUILDING...
        public void LoadFromCarFile() {
    // Create and load BrandsList to map BrandId to BrandName
    BrandsList brandsList = new BrandsList();
    brandsList.LoadFromFile(); // Load brands from brands.txt

    // Check if CarsList is empty
    if (this.isEmpty()) {
        System.out.println("No cars available to display.");
        return;
    }

    // Create a sorted list of Cars based on BrandName
    List<Cars> sortedCars = this.stream()
        .sorted((car1, car2) -> {
            // Get BrandName for car1
            String brandName1 = "Unknown";
            Brands brand1 = brandsList.findById(car1.getBrandID());
            if (brand1 != null) {
                brandName1 = brand1.getBrandName();
            }
            // Get BrandName for car2
            String brandName2 = "Unknown";
            Brands brand2 = brandsList.findById(car2.getBrandID());
            if (brand2 != null) {
                brandName2 = brand2.getBrandName();
            }
            // Compare BrandNames (case-insensitive)
            return brandName1.compareToIgnoreCase(brandName2);
        })
        .collect(Collectors.toList());

    // Display the sorted cars
    System.out.println("Cars sorted by Brand Name (" + sortedCars.size() + " cars):");
    System.out.println("--------------------------------------------------------------");
    System.out.println(String.format("%-6s | %-15s | %-10s | %-10s | %-10s", 
        "CarID", "BrandId", "Car Color", "FrameId", "EngineId"));
    System.out.println("--------------------------------------------------------------");

    for (Cars car : sortedCars) {
        System.out.println(String.format("%-6s | %-15s | %-10s | %-10s | %-10s", 
            car.getCarID(), car.getBrandID(), car.getCarColor(), car.getFrameID(), car.getEngineID()));
    }
    System.out.println("--------------------------------------------------------------");
}

// 8)Add new car
    public void AddNewCar(){
        
        BrandsList function = new BrandsList();
        // ensure unique
        String CarId;
        boolean Check;
        int count = 0;
        do{
            CarId = input.InputCarId(); 
            Check = false;
            for(Cars c : this){
                if(c.getCarID().equalsIgnoreCase(CarId)){
                    System.out.println("Car ID already exists! Retry");
                    Check = true;
                    break;
                }
            }
        }while(Check);
        
        
        String BrandID = input.InputBrandId();
        function.LoadFromFile();
        while(function.CheckBrandIdExisted(BrandID)==false){
            System.out.println("BrandId does not exist! Retry!");
            BrandID = input.InputBrandId();
        }
        String CarColor = input.InputCarColor();
        String FrameID;
        do{
            FrameID = input.InputFrameId(count); Check = false;
            for(Cars c : this){
                if(c.getFrameID().equalsIgnoreCase(FrameID)){
                    System.out.println("Frame ID already exists! Retry");
                    Check = true;
                    break;
                }
            }
        }while(Check);
        String EngineID;
        do{
            EngineID = input.InputEngineId(count); Check = false;
            for(Cars c : this){
                if(c.getEngineID().equalsIgnoreCase(EngineID)){
                    System.out.println("Engine ID already exists! Retry");
                    Check = true;
                    break;
                }
            }
        }while(Check);
        
        Cars Cars = new Cars(CarId, BrandID, CarColor, FrameID, EngineID);
        this.add(Cars);
        System.out.println("--------------\nCar Added!\n--------------");
        SaveToCarFile();
    }
    
// 9)Remove a car by Id
    public void RemoveByCarId(){
        String Carid;
        System.out.println("PLease enter Car Id to Remove:");
        boolean Check; int count = 0;
        do{
            Carid = sc.nextLine().trim(); Check = false;
            for(Cars c : this){
                if(c.getCarID().equalsIgnoreCase(Carid)){
                    Check = false;
                    System.out.println("Car Details:");
                    System.out.println(c);
                    System.out.println("Are you sure you want to delete this registration? (Y/N): ");
                    String y = sc.nextLine().trim().toUpperCase();
                    if (y.equals("Y")) {
                        this.remove(c);}
                    count = 1;
                    break;
                }
            }if(count == 0){
                System.out.println("This car does not exist!");
            }
        }while(Check);
        
        }
    

// 4)Update a Car by Id
    public void UpdateCar() {
        System.out.println("Please enter CarID:");
        String id = sc.nextLine().trim();
        Cars CarFinding = findById(id);
        int count = 1;
        if (CarFinding == null) {
            System.out.println("------------------------\nThis Car does not exist!\n------------------------");
        } else {
            System.out.println("---------------------------------\nCar with id " + id + " has been found!\n---------------------------------");

            String newCarColor = input.InputCarColor();
            while(newCarColor.isEmpty()) {
                break;
            }if(!newCarColor.isEmpty()) CarFinding.setCarColor(newCarColor);

            String newFrameId = input.InputFrameId(count);
            while(newFrameId.isEmpty()) {
                break;
            }if(!newFrameId.isEmpty()) CarFinding.setFrameID(newFrameId);

            String newEngineId = input.InputEngineId(count);
            while(newEngineId.isEmpty()) {
                break;
            }if(!newEngineId.isEmpty()) CarFinding.setEngineID(newEngineId);
            
            System.out.println("--------------\nUpdated!\n--------------");
            }
            
        }

// 7)Search Car by brand name (2)
    public void SearchByBrandName2(String BrandId) {
        int count = 0;
        for(Cars c : this){
            if(BrandId.trim().equalsIgnoreCase(c.getBrandID().trim())){
                if(count == 0){
                    System.out.println("---------------------------------------------------------");
                    System.out.println(String.format("%-6s | %-10s | %-10s | %-10s | %-10s", "CarID", "BrandId", "Car Color", "FrameId", "EngineId"));
                    System.out.println("---------------------------------------------------------");
                }
                System.out.println(c);
                count++;
            }
        }if(count == 0){
            System.out.println("--------------------------------------------------");
            System.out.println("Brand with ID: "+BrandId + " Does not have any Car!");
            System.out.println("--------------------------------------------------");
        }
    } 

// 11)Search by Color
    public void FilterByColor() {
        System.out.println("Please enter Color to find: ");
        String Color =  sc.nextLine().trim();
        int i = 0;
        for(Cars c : this){
            if(Color.equalsIgnoreCase(c.getCarColor())){
                i++;
                if(i == 1){
                    System.out.println("---------------------------------------------------------");
                    System.out.println(String.format("%-6s | %-10s | %-10s | %-10s | %-10s", "CarID", "BrandId", "Car Color", "FrameId", "EngineId"));
                    System.out.println("---------------------------------------------------------");
                }
                System.out.println(c);
            }
        }
    }

// 8)Save data
    public void SaveToCarFile() {
        try ( PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            
            for (Cars s : this) {
                pw.println(s.getCarID() + ", " + s.getBrandID()+ ", " + s.getCarColor()+ ", " + s.getFrameID() + ", " +s.getEngineID());
            }
            System.out.println("Cars data has been successfully saved");
        } catch (Exception e) {
        }
    }

    private Cars findById(String id) {
        for (Cars Cars : this) {
            if (Cars.getCarID().equalsIgnoreCase(id)) {
                return Cars;
            }
        }
        return null;
    }
}
