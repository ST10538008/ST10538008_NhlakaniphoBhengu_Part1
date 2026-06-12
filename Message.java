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
    
    // ===================== ARRAYS ======================
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();

    // Total sent count and auto-increment counter
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
    public String sendMessageOption(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessageCount++;
                return "Message successfully sent.";
            case 2:
                disregardedMessages.add(this);
                return "Press 0 to delete the message.";
            case 3:
                storedMessages.add(this);
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
    
    // ===================== PART 3 METHODS =====================

    // 1. Display sender and recipient of all stored messages
    public static String displayStoredMessages() {
        if (storedMessages.isEmpty()) {
            return "No stored messages.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--- Stored Messages ---\n");
        for (Message m : storedMessages) {
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.messageText).append("\n");
            sb.append("----------------------------\n");
        }
        return sb.toString();
    }

    // 2. Display the longest stored message
    public static String displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages.";
        }
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.messageText.length() > longest.messageText.length()) {
                longest = m;
            }
        }
        return "Longest message: " + longest.messageText;
    }

    // 3. Search for a message by message ID
    public static String searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.messageID.equals(id)) {
                return "Recipient: " + m.recipient + "\nMessage: " + m.messageText;
            }
        }
        for (Message m : storedMessages) {
            if (m.messageID.equals(id)) {
                return "Recipient: " + m.recipient + "\nMessage: " + m.messageText;
            }
        }
        return "Message ID not found.";
    }

    // 4. Search all messages for a particular recipient
    public static String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            if (m.recipient.equals(recipient)) {
                sb.append("Message: ").append(m.messageText).append("\n");
            }
        }
        for (Message m : storedMessages) {
            if (m.recipient.equals(recipient)) {
                sb.append("Message: ").append(m.messageText).append("\n");
            }
        }
        if (sb.length() == 0) {
            return "No messages found for recipient: " + recipient;
        }
        return sb.toString();
    }

    // 5. Delete a message using the message hash
    public static String deleteMessageByHash(String hash) {
        for (Message m : sentMessages) {
            if (m.messageHash.equals(hash)) {
                String text = m.messageText;
                sentMessages.remove(m);
                messageHashes.remove(hash);
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        for (Message m : storedMessages) {
            if (m.messageHash.equals(hash)) {
                String text = m.messageText;
                storedMessages.remove(m);
                messageHashes.remove(hash);
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // 6. Display full report of all sent and stored messages
    public static String displayReport() {
        if (sentMessages.isEmpty() && storedMessages.isEmpty()) {
            return "No messages to display.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== Full Message Report ===\n");
        for (Message m : sentMessages) {
            sb.append("Message Hash: ").append(m.messageHash).append("\n");
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.messageText).append("\n");
            sb.append("----------------------------\n");
        }
        for (Message m : storedMessages) {
            sb.append("Message Hash: ").append(m.messageHash).append("\n");
            sb.append("Recipient: ").append(m.recipient).append("\n");
            sb.append("Message: ").append(m.messageText).append("\n");
            sb.append("----------------------------\n");
        }
        return sb.toString();
    }

    // ===================== GETTERS =====================
    public String getMessageID()   { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient()   { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber()  { return messageNumber; }
    public String getTimestamp()   { return timestamp; }
    public static ArrayList<Message> getSentMessages()        { return sentMessages; }
    public static ArrayList<Message> getStoredMessages()      { return storedMessages; }
    public static ArrayList<Message> getDisregardedMessages() { return disregardedMessages; }
    public static ArrayList<String> getMessageHashes()        { return messageHashes; }
    public static ArrayList<String> getMessageIDs()           { return messageIDs; }
}
