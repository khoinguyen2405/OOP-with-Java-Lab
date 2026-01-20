package Input;

import java.util.Scanner;
import java.util.ArrayList;
import Vehicles.Brands;


public class InputValid extends ArrayList<Brands>  {
    Scanner sc = new Scanner(System.in);

    public boolean isValidBrandIdFormat(String id) {
        return !id.equals("");
    }

    public boolean isValidBrandName(String name) {
        return !name.equals("");
    }

    public boolean isValidSoundSystem(String SoundSystem) {
        return !SoundSystem.equals("");
    } 

    public boolean isValidPrice(String price, int i) {
        if(i==0){
            if(price.equalsIgnoreCase("")){
                return true;
            }else {
                Double Price = Double.parseDouble(price);
                return  Price > 0;
            }
        }
        Double Price = Double.parseDouble(price);
        return  Price > 0;
    }
}
