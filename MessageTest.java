/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {

    // Test message objects using assignment test data
    private Message message1;
    private Message message2;

    public MessageTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        // Test Data Task 1
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // Test Data Task 2
        message2 = new Message("08575975889", "Hi Keegan, did you receive the payment?");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of validateRecipientCell method, of class Message.
     * Success - valid international number
     */
    @Test
    public void testValidateRecipientCell() {
        System.out.println("validateRecipientCell");
        String recipient = "+27718693002";
        boolean expResult = true;
        boolean result = Message.validateRecipientCell(recipient);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateRecipientCell method, of class Message.
     * Failure - no international code
     */
    @Test
    public void testValidateRecipientCellFailure() {
        System.out.println("validateRecipientCell - failure");
        String recipient = "08575975889";
        boolean expResult = false;
        boolean result = Message.validateRecipientCell(recipient);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateMessageLength method, of class Message.
     * Success - message under 250 characters
     */
    @Test
    public void testValidateMessageLength() {
        System.out.println("validateMessageLength");
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        String expResult = "Message sent";
        String result = Message.validateMessageLength(messageText);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateMessageLength method, of class Message.
     * Failure - message over 250 characters
     */
    @Test
    public void testValidateMessageLengthFailure() {
        System.out.println("validateMessageLength - failure");
        String messageText = "This is a very long message that exceeds the two hundred "
                + "and fifty character limit. We need to make sure the system correctly "
                + "identifies that this message is too long and returns the appropriate "
                + "error message to the user so they can reduce its size accordingly.";
        String expResult = "Please enter a message of less than 250 characters.";
        String result = Message.validateMessageLength(messageText);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        boolean expResult = true;
        boolean result = message1.checkMessageID();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     * Success - valid number
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        boolean expResult = true;
        boolean result = message1.checkRecipientCell();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     * Failure - invalid number
     */
    @Test
    public void testCheckRecipientCellFailure() {
        System.out.println("checkRecipientCell - failure");
        boolean expResult = false;
        boolean result = message2.checkRecipientCell();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessageLength method, of class Message.
     * Success - message under 250 characters
     */
    @Test
    public void testCheckMessageLength() {
        System.out.println("checkMessageLength");
        String expResult = "Message sent";
        String result = message1.checkMessageLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of createMessageHash method, of class Message.
     * Tests hash ends with correct first and last word
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Message[] messages = {message1, message2};
        String[] expectedEndings = {":HITONIGHT", ":HIPAYMENT"};

        for (int i = 0; i < messages.length; i++) {
            String hash = messages[i].getMessageHash();
            assertTrue(hash.endsWith(expectedEndings[i]),
                    "Hash " + (i + 1) + " should end with '"
                    + expectedEndings[i] + "' but got: " + hash);
            assertTrue(hash.equals(hash.toUpperCase()),
                    "Hash should be all uppercase");
        }
    }

    /**
     * Test of sendMessageOption method, of class Message.
     * 1 - Send Message
     */
    @Test
    public void testSendMessageOptionSend() {
        System.out.println("sendMessageOption - send");
        int choice = 1;
        String expResult = "Message successfully sent.";
        String result = message1.sendMessageOption(choice);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMessageOption method, of class Message.
     * 2 - Disregard Message
     */
    @Test
    public void testSendMessageOptionDisregard() {
        System.out.println("sendMessageOption - disregard");
        int choice = 2;
        String expResult = "Press 0 to delete the message.";
        String result = message1.sendMessageOption(choice);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMessageOption method, of class Message.
     * 3 - Store Message
     */
    @Test
    public void testSendMessageOptionStore() {
        System.out.println("sendMessageOption - store");
        int choice = 3;
        String expResult = "Message successfully stored.";
        String result = message1.sendMessageOption(choice);
        assertEquals(expResult, result);
    }

    /**
     * Test of printMessages method, of class Message.
     * No messages sent yet
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        String expResult = "No messages sent yet.";
        String result = Message.printMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 0;
        int result = Message.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test of storeMessage method, of class Message.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        message1.storeMessage();
    }

    /**
     * Test of getMessageID method, of class Message.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        String result = message1.getMessageID();
        assertNotNull(result, "Message ID should not be null");
        assertTrue(result.length() <= 10,
                "Message ID generated: " + result);
    }

    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        String result = message1.getMessageHash();
        assertNotNull(result, "Message hash should not be null");
        assertTrue(result.endsWith(":HITONIGHT"),
                "Hash should end with :HITONIGHT but got: " + result);
    }

    /**
     * Test of getRecipient method, of class Message.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        String expResult = "+27718693002";
        String result = message1.getRecipient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageText method, of class Message.
     */
    @Test
    public void testGetMessageText() {
        System.out.println("getMessageText");
        String expResult = "Hi Mike, can you join us for dinner tonight?";
        String result = message1.getMessageText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageNumber method, of class Message.
     */
    @Test
    public void testGetMessageNumber() {
        System.out.println("getMessageNumber");
        // message1 is the first message created so number should be 1
        int result = message1.getMessageNumber();
        assertTrue(result >= 1, "Message number should be 1 or more");
    }
}