import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

import static java.awt.Color.*;

public class Main {
    public static void main(String[] args) {
        Color
                brown = new Color(130,69,19),
                lightblue = new Color(0,191,255),
                pink = new Color(255,105,180),
                orange = new Color(255,165,0),
                red = new Color(220,20,60),
                yellow = new Color(255,215,0),
                green = new Color(50,205,50),
                darkblue = new Color(0,0,205);

        GUI_Field[] fields = new GUI_Field[]{
                new GUI_Street("Start", "Modtag: 2", "", "2", WHITE, BLACK), // Start
                new GUI_Street("Brugerbaren", "Pris: 1", "", "1", brown, BLACK),
                new GUI_Street("Pizzariaet", "Pris: 1", "", "1", brown, BLACK),
                new GUI_Chance(),
                new GUI_Street("Slikbutikken", "Pris: 1", "", "1", lightblue, BLACK),
                new GUI_Street("Iskiosken", "Pris: 1", "", "1", lightblue, BLACK),
                new GUI_Street("Fængsel", "", "", "", WHITE, BLACK), // Jail
                new GUI_Street("Museet", "Pris: 2", "", "2", pink, BLACK),
                new GUI_Street("Biblioteket", "Pris: 2", "", "2", pink, BLACK),
                new GUI_Chance(),
                new GUI_Street("Skateparken", "Pris: 2", "", "2", orange, BLACK),
                new GUI_Street("Swimmingpoolen", "Pris: 2", "", "2", orange, BLACK),
                new GUI_Street("Parkeringsplads", "", "", "", WHITE, BLACK), // Parking
                new GUI_Street("Spillehallen", "Pris: 3", "", "3", red, BLACK),
                new GUI_Street("Biografen", "Pris: 3", "", "3", red, BLACK),
                new GUI_Chance(),
                new GUI_Street("Legetøjsbutikken", "Pris: 3", "", "3", yellow, BLACK),
                new GUI_Street("Dyrehandlen", "Pris: 3", "", "3", yellow, BLACK),
                new GUI_Street("Politistation", "", "", "", WHITE, BLACK), // Police
                new GUI_Street("Bowlinghallen", "Pris: 4", "", "4", green, BLACK),
                new GUI_Street("Zoo", "Pris: 4", "", "4", green, BLACK),
                new GUI_Chance(),
                new GUI_Street("Vandlandet", "Pris: 5", "", "5", darkblue, BLACK),
                new GUI_Street("Strandpromenaden", "Pris: 5", "", "5", darkblue, BLACK),
        };
        GUI gui = new GUI(fields);
        // Spiller ikke med huse eller hoteller
        gui.showMessage("Welcome");
        gui.setDie(2);
        int n = gui.getUserInteger("Enter a number");
        System.out.println(n);
    }

}