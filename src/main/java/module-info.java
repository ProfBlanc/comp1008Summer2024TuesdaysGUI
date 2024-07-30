module ca.georgiancollege.comp1008summer2024tuesdaysgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.georgiancollege.comp1008summer2024tuesdaysgui to javafx.fxml;
    exports ca.georgiancollege.comp1008summer2024tuesdaysgui;
}