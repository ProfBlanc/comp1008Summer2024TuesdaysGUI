package ca.georgiancollege.comp1008summer2024tuesdaysgui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class LoginModel {

    private String username, password;

    private Path folderPath = Path.of("src/main/resources/ca/georgiancollege/data");
    private Path filePath = folderPath.resolve("credentials.txt");

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        if(username.length() < 5)
            throw new IllegalArgumentException(username + " is too short. Needs to be at least 5 characters");

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() < 5)
            throw new IllegalArgumentException("Password is too short. Needs to be at least 5 characters");

        this.password = password;
    }

    public LoginModel(){

        try {
            if (!Files.exists(folderPath)) {
                    Files.createDirectories(folderPath);
            }

            if(!Files.exists(filePath)){
                filePath.toFile().createNewFile();
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

    }
    public boolean login(String username, String password) {

        //return username.equals("admin") && password.equals("pass");
        return validate(username, password);

    }
    private boolean validate(String username, String password){

        boolean found = false;

        try{
            List<String> lines = Files.readAllLines(filePath);
            for(String credential : lines){
                String[] components = credential.split("-");
                String fileUsername = components[0];
                String filePassword = components[1];

                if(filePassword.equals(password) && fileUsername.equals(username)){
                    found = true;
                    break;
                }

            }
        }
        catch (Exception e){
            System.err.println(e);
        }
        return found;

    }
    public void register(String username, String password){
        setUsername(username);
        setPassword(password);

        addCredentialsToFile(username, password);
    }
    private void addCredentialsToFile(String username, String password){

        try{
            Files.writeString(filePath, String.format("%s-%s%n", username, password), StandardOpenOption.APPEND);
        }
        catch (Exception e){

            throw new IllegalArgumentException("Could not add credentials to file.");
        }
    }
}
