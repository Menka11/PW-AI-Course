# Salesforce Login Automation Framework

## Enterprise-Level Selenium Java Framework

### Project Structure
```
AI-Exercises/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── salesforce/
│   │               └── pages/
│   │                   └── LoginPage.java
│   └── test/
│       └── java/
│           └── com/
│               └── salesforce/
│                   └── tests/
│                       ├── ValidLoginTest.java
│                       └── InvalidLoginTest.java
```

### Prerequisites
- Java JDK 11 or higher
- Maven 3.6+
- Chrome browser installed

### Dependencies
- Selenium WebDriver 4.16.1
- TestNG 7.8.0
- WebDriverManager 5.6.3

### Execution Commands

#### Install Dependencies
```bash
mvn clean install
```

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Class
```bash
mvn test -Dtest=ValidLoginTest
mvn test -Dtest=InvalidLoginTest
```

#### Run via TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Framework Features
- Page Object Model with PageFactory
- XPath-only locator strategy
- WebDriverWait explicit waits
- Comprehensive exception handling
- TestNG annotations (@BeforeTest, @AfterTest, @Test)
- Enterprise-grade code structure
- No Thread.sleep() usage
- Modular and reusable components

### Test Coverage

#### ValidLoginTest.java
1. Verify login page elements displayed
2. Verify valid login with credentials
3. Verify login with remember me functionality

#### InvalidLoginTest.java
1. Verify login with invalid username
2. Verify login with invalid password
3. Verify login with empty username
4. Verify login with empty password
5. Verify login with empty credentials
6. Verify login with special characters in username
7. Verify login with SQL injection attempt
