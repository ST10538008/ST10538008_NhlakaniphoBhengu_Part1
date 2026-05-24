package com.mycompany.prog5121part1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String timestamp;
    
    //Store sent message
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static int totalMessageCount = 0;
    
    private static int messageCounter = 0;
    
    //Constructor
    public Message(String recipient, String messageText){
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = ++messageCounter;
        this.messageHash = createMessageHash();
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    //Generate 10-digit message ID
    private String generateMessageID(){
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }
    
    //============STATIC VALIDATION METHODS=========
    
    // validates recipient cell number
    public static boolean validateRecipientCell(String recipient){
        return recipient.matches("^\\+27\\d{9}$");
    }
    
    //Validates message length 
    public static String validateMessageLength(String messageText){
        if (messageText.length() <=250){
            return "Message sent";
        }
        else {
            return "Please enter a message of less than 250 characters.";
        }
    }
    
    //================INSTANCE METHODS=================
    
    // checkMessageID - ensure message ID is not more than 10 characters
    public boolean checkMessageID(){
        return messageID.length()<=10;
    }
    // checkRecipientCell - checks this message's recipient
    public boolean checkRecipientCell(){
        return validateRecipientCell(this.recipient);
    }
    // chekMessageLength - ensure message length is not more than 250
    public String checkMessageLength(){
        return validateMessageLength(this.recipient);
    }
    
    //Create message hash
    public String createMessageHash(){
        String firstTwo = messageID.substring(0, 2);
        String [] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    // sendMessageOption - 1=Send , 2=Disregard, 3=Store
    public String sendMessageOption(int choice){
        switch (choice){
            case 1:
                sentMessages.add(this);
                totalMessageCount++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";            
            default:
                return "Invalid option.";
        }
    }
    
    //Print all sent messages
    public static String printMessages(){
        if (sentMessages.isEmpty()){
            return "No messages sent yet.";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (Message m : sentMessages){
            sb.append("Message ID: ").append(m.messageID).append("\n");
            sb.append("Message Hash: ").append(m.messageHash).append("\n");
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.messageText).append("\n");
            sb.append("Date sent: ").append(m.timestamp).append("\n");
            sb.append("----------------------\n");
        }
        return sb.toString();
    }
    
    //Return total messages sent
    public static int returnTotalMessages(){
        return totalMessageCount;
    }
    
    //JSON STORAGE 
    public void storeMessage(){
       try {
           JSONObject msgJson = new JSONObject();
           msgJson.put("messageID", messageID);
           msgJson.put("messageNumber", messageNumber);
           msgJson.put("recipient", recipient);
           msgJson.put("messageText", messageText);
           msgJson.put("timestamp", timestamp);
           
           JSONArray jsonArray;
           java.io.File file = new java.io.File("messages.json");
           
           if (file.exists()){
               String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
               jsonArray = new JSONArray(content);
           }
           else {
               jsonArray = new JSONArray();
           }
           
           jsonArray.put(msgJson);
           
           FileWriter fw = new FileWriter("messages.json");
           fw.write(jsonArray.toString(2));
           fw.close();
       } 
       catch (IOException e){
           System.out.println("Error storing message: " + e.getMessage());
       }
    }
    
    //Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
    public String getTimestamp() { return timestamp; }
}
