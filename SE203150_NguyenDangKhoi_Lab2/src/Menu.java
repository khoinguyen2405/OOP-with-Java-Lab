
import Operations.CarsList;
import java.util.Scanner;
import Operations.BrandsList;

public class Menu {
    Scanner sc = new Scanner(System.in);
    BrandsList function = new BrandsList();
    CarsList function2 = new CarsList();
    
    
    int choice;
    public void ChoiceMenu(){
        function.LoadFromFile();
        function2.LoadFromCarFilewithoutprint();
        do{
            System.out.println("1.List all brands"); //FINISHED
            System.out.println("2.Add a new brand"); //FINISHED
            System.out.println("3.Search for a brand by ID"); //FINISHED
            System.out.println("4.Update a brand by ID"); //FINISHED
            System.out.println("5.List all brands with prices less than or equal to an input value"); //FINISHED
            System.out.println("6.List all cars in ascending order of brand names"); //FINISHED
            System.out.println("7.Search cars by partial brand name match");//FINISHED
            System.out.println("8.Add a new car");//FINISHED
            System.out.println("9.Remove a car by ID");//FINISHED
            System.out.println("10.Update a car by ID");//FINISHED
            System.out.println("11.List all cars by a specific color");//FINISHED
            System.out.println("12.Save data to files ");//FINISHED
            System.out.println("13.Quit program");//FINISHED
            System.out.println("Choose from 1 to 13: ");
            
            
            try{
                choice = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Please enter Number!");
            }
            
            switch(choice){
            case 1:
                function.LoadFromBrandFile();
                break;
            case 2:
                function.AddNewBrand();
                break;
            case 3:
                function.SearchByBrandId();
                break;
            case 4:
                function.UpdateBrand();
                break;
            case 5:
                function.ListAllBrands();
                break;
            case 6:
                function2.LoadFromCarFile();
                break;
            case 7:
                function.SearchByBrandName();
                break;
            case 8:
                function2.AddNewCar();
                break;
            case 9:
                function2.RemoveByCarId();
                break;
            case 10:
                function2.UpdateCar();
                break;
            case 11:
                function2.FilterByColor();
                break;
            case 12:
                function.SaveToBrandFile();
                function2.SaveToCarFile();
                break;
            case 13:
                // check unsaved (not tracked) - ask to save before exit
                System.out.print("Do you want to save changes before exit? (Y/N): ");
                String ans = sc.nextLine().trim().toUpperCase();
                if (ans.equals("Y")){
                    function.SaveToBrandFile();
                    function2.SaveToCarFile();
                }
                break;
            default:
                System.out.println("Please retry!");
                break;
        }
}while(choice != 13);
}
}