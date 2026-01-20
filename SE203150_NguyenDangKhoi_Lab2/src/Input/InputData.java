package Input;

import java.util.Scanner;

public class InputData {
    Scanner sc = new Scanner(System.in);
    InputValid valid = new InputValid();

    public String InputBrandId() {
        while (true) {
            System.out.println("Enter Brand ID:");
            String id = sc.nextLine().trim().toUpperCase();
            if(!valid.isValidBrandIdFormat(id)){
                System.out.println("Invalid Brand ID. Brand ID cannot empty!");
                continue;
            }
            return id;
        }
    }

    public String InputBrandName() {
        while (true) {
            System.out.println("Enter Brand Name:");
            String name = sc.nextLine().trim();
            if (!valid.isValidBrandName(name)) {
                System.out.println("Invalid name. Brand Name cannot empty!");
                continue;
            }
            return name;
        }
    }

    public String InputSoundSysName() {
        while (true) {
            System.out.println("Please enter sound system brand:");
            String SoundSystem = sc.nextLine().trim();
            if (!valid.isValidSoundSystem(SoundSystem)) {
                System.out.println("Invalid Sound manufacturers. It cannot be empty!");
                continue;
            }
            return SoundSystem;
        }
    }

    public String InputPrice(int i) {
        while (true) {
            System.out.println("Enter price: ");
            String price;
            try{
                price = sc.nextLine().trim();
                price = price.replace(",", ".");
                if (!valid.isValidPrice(price,i)){
                    System.out.println("-----------------------\nInvalid price, Retry!\n-----------------------");
                    continue;
                }
            }catch(NumberFormatException e){
                System.out.println("-----------------------\nInvalid price, Retry!\n-----------------------");
                continue;
            }
            return price;
        }
    }

    public String InputCarId(){
        System.out.println("Please enter Car Id: ");
        String CarId = sc.nextLine().trim();
        return CarId;
    }
    
    public String InputCarColor(){
        System.out.println("Please enter Car Color: ");
        String color = sc.nextLine().trim();
        
        while(color == null){
            System.out.println("Color cannot be empty! Retry");
            color = sc.nextLine().trim();
        }
        return color;
    }
    
    public String InputFrameId(int count){
        System.out.println("Please enter Car Frame Id: ");
        String FrameId = sc.nextLine().trim();
        if(count == 1){
            if(FrameId.equalsIgnoreCase("")) return FrameId;
            while(!FrameId.matches("^F\\d{5}$")){
            if(FrameId.equalsIgnoreCase("")) return FrameId;
            System.out.println("Frame Id must follow the format “F00000”, Retry!");
            FrameId = sc.nextLine().trim();
        }return FrameId;
        }
        while(!FrameId.matches("^F\\d{5}$")){
            System.out.println("Frame Id must follow the format “F00000”, Retry!");
            FrameId = sc.nextLine().trim();
        }
        return FrameId;
    }
    
    public String InputEngineId(int count){
        System.out.println("Please enter Car Engine Id :");
        String EngineId = sc.nextLine().trim();
        if(count == 1){
            if(EngineId.equalsIgnoreCase("")) return EngineId;
            while(!EngineId.matches("^E\\d{5}$")){
            if(EngineId.equalsIgnoreCase("")) return EngineId;
            System.out.println("Frame Id must follow the format “E00000”, Retry!");
            EngineId = sc.nextLine().trim();
            
        }return EngineId;
        }
        while(!EngineId.matches("^E\\d{5}$")){
            System.out.println("Frame Id must follow the format “E00000”, Retry!");
            EngineId = sc.nextLine().trim();
        
        
    }return EngineId;
    }
    
    public String BrandidFinding(){
     System.out.println("Please enter Brand id: ");
        return sc.nextLine().trim();

}
}