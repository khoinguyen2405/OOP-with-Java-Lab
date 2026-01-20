package Operations;


import Input.InputData;
import Vehicles.Brands;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class BrandsList extends ArrayList<Brands>{

    Brands b;
    CarsList function = new CarsList();
    InputData input = new InputData();
    private static final String DATA_FILE = "brands.txt";
    Scanner sc = new Scanner(System.in);

//Load Brands.txt file
    public void LoadFromFile() {
        try ( BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            this.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    //First split ","
                    String BrandId = parts[0].trim();
                    String BrandName = parts[1].trim();
                    String SsbAndPrice = parts[2].trim();

                    if (BrandId.isEmpty() || BrandName.isEmpty() || !SsbAndPrice.contains(":")) {
                        // skip wrong format line
                        continue;
                    }

                    //second split ":"
                    String[] secondsplit = SsbAndPrice.split(":");
                    String SoundSystemBrand = secondsplit[0].trim();
                    String price = secondsplit[1].trim();
                    this.add(new Brands(BrandId, BrandName, SoundSystemBrand, price));
                }
            }
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }

// 1)Load Brands File
    public void LoadFromBrandFile() {
        try ( BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            this.clear();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    //First split ","
                    String BrandId = parts[0].trim();
                    String BrandName = parts[1].trim();
                    String SsbAndPrice = parts[2].trim();

                    if (BrandId.isEmpty() || BrandName.isEmpty() || !SsbAndPrice.contains(":")) {
                        // bỏ qua dòng sai format
                        continue;
                    }

                    //second split ":"
                    String[] secondsplit = SsbAndPrice.split(":");
                    String SoundSystemBrand = secondsplit[0].trim();
                    String price = secondsplit[1].trim();
                    this.add(new Brands(BrandId, BrandName, SoundSystemBrand, price));
                }
            }
            System.out.println("Loaded " + this.size() + " Brands from file.");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-15s | %-40s | %-18s | %-10s ", "BrandID", "BrandName", "sound system brand", "price"));
            System.out.println("------------------------------------------------------------------------------------------");
            for (Brands b : this) {
                System.out.println(b);
            }
            System.out.println("------------------------------------------------------------------------------------------");
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        }
    }

// 2)Add Brand
    public void AddNewBrand() {
        // ensure unique
        String BrandId = input.InputBrandId();
        
        for(Brands s : this){
            if(s.getBrandId().equalsIgnoreCase(BrandId)){
                System.out.println("Student ID already exists!");
            return;
            }
        }
        String BrandName = input.InputBrandName();
        String SoundSystemName = input.InputSoundSysName();
        String Price = input.InputPrice(1) + "B";
        Brands Brand = new Brands(BrandId, BrandName, SoundSystemName, Price);
        this.add(Brand);
        System.out.println("--------------\nBrand Added!\n--------------");
        SaveToBrandFile();
    }

// 3)Search by Id
    public void SearchByBrandId() {
        System.out.println("PLease enter Brand Id to search:");
        String Brandid = new java.util.Scanner(System.in).nextLine().trim();
        boolean Found = false;
        int i = 0;
        for (Brands b : this) {
            if (b.getBrandId().equalsIgnoreCase(Brandid)) {
                i++;
                if (i == 1) {
                    System.out.println("------------------------------------------------------------------------------------------");
                    System.out.println(String.format("%-15s | %-40s | %-18s | %-10s ", "BrandID", "BrandName", "sound system brand", "price"));
                    System.out.println("------------------------------------------------------------------------------------------");
                }
                System.out.println(b);
                Found = true;
            }
        }
        if (!Found) {
            System.out.println("Brand not found!");
        }
    }

// 4)Update Brand
    public void UpdateBrand() {
        String id = input.BrandidFinding();
        Brands BrandFinding = findById(id);
        if (BrandFinding == null) {
            System.out.println("This Brand does not exist.");
        } else {
            System.out.println("----------------------------\nBrand with id " + id + " has been found!\nPlease enter Brand new information");

            System.out.println("Please enter new Brand Name:");
            String newBrandName = sc.nextLine().trim();
            if (!newBrandName.isEmpty()) {
                BrandFinding.setBrandName(newBrandName);
            }

            System.out.println("Please enter new Sound System Name:");
            String newSSN = sc.nextLine().trim();
            if (!newSSN.isEmpty()) {
                BrandFinding.setSoundSystemBrand(newSSN);
            }

            System.out.println("Please enter new Price:");
            String newPrice = input.InputPrice(0);
            if (!newPrice.isEmpty()) {
                BrandFinding.setPrice(newPrice +"B");
            }if(newPrice.isEmpty()){
                newPrice = BrandFinding.getPrice();
                BrandFinding.setPrice(newPrice);
            }
            System.out.println("--------------\nUpdated!\n--------------");
        }
        SaveToBrandFile();
    }

// 5)BrandsList all brands with prices less than or equal to an input value
    public void ListAllBrands() {
        String price = input.InputPrice(1);
        double price2 = Double.parseDouble(price);
        int count = 0;
        for (Brands b : this) {
            double b2 = Double.parseDouble(b.getPrice().substring(0, b.getPrice().length() - 1));
            if (b2 <= price2) {
                count++;
                System.out.println(b);
            }
        }
        if (count == 0) {
            System.out.println("------------------------------------");
            System.out.println("There is no Price ≤ " + price);
            System.out.println("------------------------------------");
        }
    }
    
// 7)Search Car by brand name (1)
    public void SearchByBrandName() {
        String BrandName;
        String check;
        String BrandId;
        boolean Found = false;
        function.LoadFromCarFilewithoutprint();

        while (true) {
            System.out.println("Please enter Brand Name to search: ");
            BrandName = sc.nextLine().trim();

            if (BrandName.isEmpty()) {
                System.out.println("Brand cannot be empty! Retry? (Y/N):");
                check = sc.nextLine().trim().toUpperCase();
                if (check.equals("N")) {
                    return; // người dùng không muốn nhập nữa
                }
            } else {
                break; // nhập hợp lệ thì thoát vòng lặp
            }
        }

        for (Brands b : this) {
            if (BrandName.equalsIgnoreCase(b.getBrandName().trim())) {
                Found = true;
                BrandId = b.getBrandId().trim();
                function.SearchByBrandName2(BrandId);
            }
        }

        if (!Found) {
            System.out.println("No brand found with name: " + BrandName);
        }
    }

// 8)Save data
    public void SaveToBrandFile() {
        try ( PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {

            for (Brands s : this) {
                pw.println(s.getBrandId() + ", " + s.getBrandName() + ", " + s.getSoundSystemBrand() + ": " + s.getPrice());
            }
            System.out.println("Brands data has been successfully saved");
        } catch (Exception e) {
        }
    }

    public Brands findById(String id) {
        for (Brands brand : this) {
            if (brand.getBrandId().equalsIgnoreCase(id)) {
                return brand;
            }
        }
        return null;
    }

    public boolean CheckBrandIdExisted(String BrandId) {
        if (BrandId == null) {
            return false;
        }
        for (Brands b : this) {
            if (BrandId.trim().equalsIgnoreCase(b.getBrandId().trim())) {
                return true; // BrandId exists
            }
        }
        return false; // BrandId does not exist
    }
}
