# PROG5121 - QuickChat Application
### Student: Nhlakanipho Bhengu
### Module: PROG5121
### Year: 2026

---

## Project Overview
This is a Java-based messaging application called **QuickChat**. The application allows a user to register, log in, and send messages to recipients using a simple console menu. The project is split into three parts — Part 1 handles user registration and login, Part 2 adds the QuickChat messaging functionality, and Part 3 adds array-based message storage and management features.

---

## Part 1 — User Registration and Login

### What it does:
- Allows a user to **register** with a username, password and cell phone number
- Validates all registration details before accepting them
- Allows the user to **log in** after registering
- Displays a personalised welcome message after successful login

### Validation Rules:
| Field | Rule |
|-------|------|
| Username | Must contain an underscore (_) and be max 5 characters |
| Password | Must be 8+ characters, have 1 capital letter, 1 number, and 1 special character |
| Cell Number | Must start with +27 followed by 9 digits |

### Methods in PROG5121Part1.java:
| Method | What it does |
|--------|-------------|
| `checkUserName()` | Validates the username format |
| `checkPasswordComplexity()` | Validates the password meets complexity rules |
| `checkCellPhoneNumber()` | Validates the cell number format |
| `registerUser()` | Combines username and password checks and registers the user |
| `loginUser()` | Checks entered credentials against stored credentials |
| `returnLoginStatus()` | Returns welcome or failure message after login |

---

## Part 2 — QuickChat Messaging
### Note: PROG5121Part1.java has been updated to include Part 2 functionality

### What it does:
- Only accessible after a **successful login**
- Displays **"Welcome to QuickChat"** after login
- I ask the user how many messages they want to send at the start
- The menu keeps running until the user chooses to quit:
  - **Option 1** — Send Messages
  - **Option 2** — Show recently sent messages (Coming Soon)
  - **Option 3** — Stored Messages
  - **Option 4** — Quit
- Displays total messages sent when the user exits

### Message Features:
| Feature | Description |
|---------|-------------|
| Message ID | Randomly generated 10-digit number |
| Message Number | Auto-increments with each message (1, 2, 3...) |
| Recipient | Must be in international format (+27XXXXXXXXX) |
| Message | Must not exceed 250 characters |
| Message Hash | Auto-generated e.g. 00:1:HITONIGHT |
| Timestamp | Date and time the message was created |
| Send Options | Send, Disregard, or Store message |

### Methods in Message.java:
| Method | What it does |
|--------|-------------|
| `checkMessageID()` | Ensures message ID is not more than 10 characters |
| `checkRecipientCell()` | Validates the recipient cell number format |
| `checkMessageLength()` | Checks message does not exceed 250 characters |
| `createMessageHash()` | Builds the message hash e.g. 00:1:HITONIGHT |
| `sendMessageOption()` | Handles send, disregard or store choice |
| `printMessages()` | Returns all sent messages as a formatted string |
| `returnTotalMessages()` | Returns the total number of messages sent |
| `storeMessage()` | Saves the message to a JSON file |
| `validateRecipientCell()` | Static validation used before a Message object is created |
| `validateMessageLength()` | Static validation used before a Message object is created |

---

## Part 3 — Store Data and Display
### Note: PROG5121Part1.java and Message.java have been updated to include Part 3 functionality

### What it does:
- Adds 5 arrays to store messages during the session
- Adds a **Stored Messages** option to the main menu
- Users can manage and search through their messages using a sub-menu

### Arrays used:
| Array | What it stores |
|-------|---------------|
| Sent Messages | All messages that were sent |
| Disregarded Messages | All messages that were disregarded |
| Stored Messages | All messages that were stored |
| Message Hashes | All message hashes auto-populated on creation |
| Message IDs | All message IDs auto-populated on creation |

### Stored Messages Sub-Menu:
| Option | What it does |
|--------|-------------|
| 1 | Display sender and recipient of all stored messages |
| 2 | Display the longest stored message |
| 3 | Search for a message by message ID |
| 4 | Search all messages for a particular recipient |
| 5 | Delete a message using the message hash |
| 6 | Display a full report of all sent and stored messages |
| 7 | Back to main menu |

### New Methods added to Message.java for Part 3:
| Method | What it does |
|--------|-------------|
| `displayStoredMessages()` | Displays recipient and message of all stored messages |
| `displayLongestMessage()` | Finds and returns the longest stored message |
| `searchByMessageID()` | Searches sent and stored messages by message ID |
| `searchByRecipient()` | Returns all messages for a specific recipient |
| `deleteMessageByHash()` | Deletes a message from the array using its hash |
| `displayReport()` | Displays full report showing hash, recipient and message |

---

## JUnit Tests

### MessageTest.java — What I tested:
| Test | Expected Result |
|------|----------------|
| Message length valid (under 250 chars) | "Message sent" |
| Message length invalid (over 250 chars) | "Please enter a message of less than 250 characters." |
| Recipient cell valid (+27XXXXXXXXX) | Returns true |
| Recipient cell invalid (no international code) | Returns false |
| Message hash is correct | e.g. 00:0:HITONIGHT |
| Message ID is created | 10 digit number generated |
| MessageSent — Send option | "Message successfully sent." |
| MessageSent — Disregard option | "Press 0 to delete the message." |
| MessageSent — Store option | "Message successfully stored." |
| Sent messages array populated | Contains "Did you get the cake?" |
| Display longest message | Returns msg2 as longest |
| Search by message ID | Returns correct message for given ID |
| Search by recipient | Returns all messages for +27838884567 |
| Delete by message hash | Message successfully deleted |
| Display report | Shows hash, recipient and message |

### PROG5121Part1Test.java — What I tested:
| Test | Expected Result |
|------|----------------|
| Username valid | Returns true |
| Username no underscore | Returns false |
| Username too long | Returns false |
| Password valid | Returns true |
| Password no capital | Returns false |
| Password too short | Returns false |
| Cell number valid | Returns true |
| Cell number no international code | Returns false |
| Register user success | "User has been registered successfully." |
| Register user invalid username | Returns username error message |
| Register user invalid password | Returns password error message |
| Login success | Returns true |
| Login wrong password | Returns false |
| Login wrong username | Returns false |
| Login status success | Returns welcome message |
| Login status failure | Returns failure message |

### Test Data Used:
| Field | Part 2 Task 1 | Part 2 Task 2 |
|-------|--------------|--------------|
| Recipient | +27718693002 | 08575975889 |
| Message | "Hi Mike, can you join us for dinner tonight?" | "Hi Keegan, did you receive the payment?" |
| Action | Send | Disregard |

| Field | Part 3 Message 1 | Part 3 Message 2 | Part 3 Message 3 | Part 3 Message 5 |
|-------|-----------------|-----------------|-----------------|-----------------|
| Recipient | +27834557896 | +27838884567 | +27834484567 | +27838884567 |
| Message | "Did you get the cake?" | "Where are you? You are late!" | "Yohoooo, I am at your gate." | "Ok, I am leaving without you." |
| Flag | Sent | Stored | Disregard | Stored |

---

## How to Run
1. Open the project in **NetBeans**
2. Right-click `PROG5121Part1.java` and select **Run File**
3. Follow the prompts to register, log in and use QuickChat

## How to Run Tests
1. Right-click `MessageTest.java` or `PROG5121Part1Test.java` and select **Test File**
2. Results will show in the Output panel

## Technologies Used
- Java
- JUnit 5 (unit testing)
- FileWriter (message storage)
- Maven (project management)
