/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121part1;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class PROG5121Part1 {
    //Method to check username validation
    public static boolean checkUserName(String username){
        return username.length()<=5 && username.contains("_");
    }
    
    //Method to check the password complexity
    public static boolean checkPasswordComplexity(String password){
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(regex, password);  
    }
    
    //Method to check cellphone number
    //It must start start with an international code (+27) followed by 9 characters in total must have 12 characters
    public static boolean checkCellPhoneNumber(String cellNumber){
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellNumber);
    }
    
    //Method to register the user
    public static String registerUser(String username, String password){
        //check username
        if (!checkUserName(username)){
            return "Username is not correct. Must have _ and max 5 chars.";
        }
        
        //check password
        if (!checkPasswordComplexity(password)){
            return "Password is not correct. Must have 8+ chars, capital letter, number, and special char.";
        }
        //check if both conditions are correct
        return "User has been registered successfully.";
    }
    
    //Method to check login details
    public static boolean loginUser(String enteredUsername, String enteredPassword, String storedUsername, String storedPassword){
        //check username if its entered correctly
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
        }
    
    //Show login message
    public static String returnLoginStatus(boolean loginSuccess, String firstName, String lastName){
        if (loginSuccess){
            return "\nWelcome " + firstName + " " + lastName + " it is great to see you again.";
        }
        else{
            return "Username or password incorrect, please try again.";
        }
    }
    
    //====================================Part 2: sendMessage method=========================================
    public static void sendMessage(Scanner input) {
        // Get and validate recipient cell number
        System.out.print("Input recipient number (+27XXXXXXXXX): ");
        String recipient = input.nextLine();
        if (!Message.validateRecipientCell(recipient)) {
            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
            return;
        }
        System.out.println("Cell phone number successfully captured.");
 
        // Get and validate message text
        System.out.print("Enter your message (max 250 characters): ");
        String messageText = input.nextLine();
        String lengthCheck = Message.validateMessageLength(messageText);
        if (!lengthCheck.equals("Message sent")) {
            System.out.println(lengthCheck);
            return;
        }
        System.out.println(lengthCheck);
 
        // Create the message object
        Message msg = new Message(recipient, messageText);
 
        // Display message details
        System.out.println("\nMessage ID: " + msg.getMessageID());
        System.out.println("Message Hash: " + msg.getMessageHash());
        System.out.println("Recipient: " + msg.getRecipient());
        System.out.println("Message: " + msg.getMessageText());
        System.out.println("Date: " + msg.getTimestamp());
 
        // Ask user what to do with the message
        System.out.println("\nSelect action:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Choice: ");
 
        int action = Integer.parseInt(input.nextLine());
        System.out.println(msg.sendMessageOption(action));;
    }
    
    //Main method starts
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String username;
        String password;
        String cellNumber;
        
        boolean validUsername;
        boolean validPassword;
        boolean validCellNumber;
        
        //Register User
        System.out.println("Register User details");
        
        //loop until valid username
        while (true) { 

            System.out.println("Enter Username (must contain '_' and max 5 chars): "); 

            username = input.nextLine(); 

            if (checkUserName(username)) break; 

            System.out.println("Invalid username. Example: user_");
        }
        
        
        //loop through password characters
        while (true) { 

            System.out.println("Enter Password (8+ chars, 1 capital, 1 number, 1 special char): "); 

            password = input.nextLine(); 

            if (checkPasswordComplexity(password)) break; 

            System.out.println("Invalid password. Example: Password1!");
        }
        
        //loop until valid cellphone number
        while (true) { 

            System.out.println("Enter Cell Phone (+27 followed by 9 digits): "); 

            cellNumber = input.nextLine(); 

            if (checkCellPhoneNumber(cellNumber)) { 

                System.out.println("Cell phone number successfully captured."); 
                break;
            }
            else {
                System.out.println("Invalid cellphone number. example: +27831234567");
            }
        }
        
        
        //Call registerUser after the loop to display the final message
        System.out.println("User has been registered successfully.");
        
        
        //Login Section
        System.out.println("\nLogin");
        
        
        //Login user loop
        String enteredUsername;
        String enteredPassword;
        boolean loginSuccess;
        
        do {
            System.out.println("Enter username to login: ");
            enteredUsername = input.nextLine().trim();
            
            System.out.println("Enter password to login: ");
            enteredPassword = input.nextLine().trim();
        
        //Check login
            loginSuccess = loginUser(enteredUsername, enteredPassword, username, password);
            
            if (!loginSuccess) {
                System.out.println("Username or password incorrect, please try again.");
            }
        }
        while (!loginSuccess);
        
        System.out.println("Login successful");
        
        //After Successful login
        System.out.println("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.println("Enter last name: ");
        String lastName = input.nextLine();
        
        //Display welcome meassage 
        System.out.println(returnLoginStatus(loginSuccess, firstName, lastName));
        
        //===================PART 2: QUICKCHAT====================
        System.out.println("\nWelcome to QuickChat.");
 
        // Ask how many messages the user wants to send
        int maxMessages = 0;
        try {
            System.out.print("How many messages do you wish to send? ");
            maxMessages = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, exiting programme.");
            return;
        }
        // Menu loop - keeps running until user selects Exit
        boolean exit = false;
 
        while (!exit) {
            System.out.println("\nSelect an Option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choice: ");
 
            int choice = Integer.parseInt(input.nextLine());
 
            switch (choice) {
                case 1:
                    // Check message limit before allowing send
                    if (Message.returnTotalMessages() < maxMessages) {
                        sendMessage(input);
                    } else {
                        System.out.println("Maximum Messages Reached. You may not send more.");
                    }
                    break;
 
                case 2:
                    System.out.println("Coming Soon.");
                    break;
 
                case 3:
                    exit = true;
                    break;
 
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
 
        // Show summary when user quits
        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
        System.out.println(Message.printMessages());
 
        input.close();
    }
}
