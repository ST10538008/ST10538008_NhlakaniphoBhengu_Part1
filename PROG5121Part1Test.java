/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121part1;

import java.util.Scanner;
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
public class PROG5121Part1Test {
    
    public PROG5121Part1Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class PROG5121Part1.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        boolean expResult = true;
        boolean result = PROG5121Part1.checkUserName(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class PROG5121Part1.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = PROG5121Part1.checkPasswordComplexity(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class PROG5121Part1.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellNumber = "0838968976";
        boolean expResult = false;
        boolean result = PROG5121Part1.checkCellPhoneNumber(cellNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class PROG5121Part1.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String expResult = "User has been registered successfully.";
        String result = PROG5121Part1.registerUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class PROG5121Part1.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String enteredUsername = "kyl_1";
        String enteredPassword = "Ch&&sec@ke99!";
        String storedUsername = "kyl_1";
        String storedPassword = "password";
        boolean expResult = false;
        boolean result = PROG5121Part1.loginUser(enteredUsername, enteredPassword, storedUsername, storedPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class PROG5121Part1.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccess = false;
        String firstName = "Nhlakanipho";
        String lastName = "Bhengu";
        String expResult = "Username or password incorrect, please try again.";
        String result = PROG5121Part1.returnLoginStatus(loginSuccess, firstName, lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PROG5121Part1.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PROG5121Part1.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class PROG5121Part1.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        Scanner input = null;
        PROG5121Part1.sendMessage(input);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}

