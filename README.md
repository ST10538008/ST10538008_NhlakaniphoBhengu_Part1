# PROG5121 - QuickChat Application
### Student: Nhlakanipho Bhengu
### Subject: PROG5121
### Year: 2026

---

## Project Overview
This is a Java-based messaging application called **QuickChat** built across two parts. The application allows a user to register, log in, and send messages to recipients using a simple console menu.

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

### What it does:
- Only accessible after a **successful login**
- Displays **"Welcome to QuickChat"** after login
- User sets how many messages they want to send
- Runs a menu loop until the user quits:
  - **Option 1** — Send Messages
  - **Option 2** — Show recently sent messages (Coming Soon)
  - **Option 3** — Quit
- Displays total messages sent on exit

### Message Features:
| Feature | Description |
|---------|-------------|
| Message ID | Randomly generated 10-digit number |
| Message Number | Auto-increments with each message (1, 2, 3...) |
| Recipient | Must be in international format (+27XXXXXXXXX) |
| Message | Must not exceed 250 characters |
| Message Hash | Auto-generated e.g. 00:1:HITONIGHT |
| Timestamp | Date and time message was created |
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
| `storeMessage()` | Saves the message to a text file |
| `validateRecipientCell()` | Static validation used before Message object is created |
| `validateMessageLength()` | Static validation used before Message object is created |

---

## JUnit Tests

### What is tested:
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

### Test Data Used:
| Field | Task 1 | Task 2 |
|-------|--------|--------|
| Recipient | +27718693002 | 08575975889 |
| Message | "Hi Mike, can you join us for dinner tonight?" | "Hi Keegan, did you receive the payment?" |
| Action | Send | Disregard |

---

## How to Run
1. Open the project in **NetBeans**
2. Make sure `Message.java` and `PROG5121Part1.java` are in the same package
3. Right-click `PROG5121Part1.java` → **Run File**
4. Follow the prompts to register, log in and use QuickChat

## How to Run Tests
1. Right-click `MessageTest.java` → **Test File**
2. Results will show in the Output panel

---

## GitHub Branch Strategy
- **main** — stable working code
- **KhanbanTasks** — feature branch used for development as per assignment requirements

---

## Technologies Used
- Java
- JUnit 5 (unit testing)
- FileWriter (message storage)
- Maven (project management)
-
