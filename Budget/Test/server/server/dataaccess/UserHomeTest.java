package server.dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.datatransfer.User;

import static org.junit.jupiter.api.Assertions.*;

class UserHomeTest
{
  private UserHome userHome;

  @BeforeEach
  public void setup(){
    userHome = new InDatabaseUsers();
  }

  //Validate a user that exists in database and insert correct password
  @Test
  public void validateValidUser(){
    User user = new User("Chair","");
    String result = userHome.loginResult(user);
    assertEquals("OK", result);
  }

  //validate a user that does not exist in database
  @Test
  public void validateInvalidUsername(){
    User user = new User("Troe","123");
    String result = userHome.loginResult(user);
    assertEquals("User not found", result);
  }

  //validate a user that exists in database but you haven't inserted correct password
  @Test
  public void validateInvalidPassword(){
    User user = new User("Troels","123456");
    String result = userHome.loginResult(user);
    assertEquals("Password incorrect", result);
  }

  //register a valid user
  @Test
  public void registerValidUser(){
    User user = new User("Chair", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("OK", result);
  }

  //register user that already exists in database
  @Test
  public void registerUserThatExists(){
    User user = new User("Troels", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("There is already a user with this name", result);
  }

  //register user leaving username empty
  @Test
  public void registerNullUsername(){
    User user = new User(null, "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid username", result);
  }

  //register user leaving username empty
  @Test
  public void registerEmptyUsername(){
    User user = new User(" ", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid username", result);
  }

  //register user with symbols in the username
  @Test
  public void registerUsernameWithSymbols(){
    User user = new User("Chair*", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid username", result);
  }

  //register user with username less than 3 characters
  @Test
  public void registerShortUsername(){
    User user = new User("Ch*", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid username", result);
  }

  //register user with username more than 12 characters
  @Test
  public void registerLongUsername(){
    User user = new User("ChairAndTableAndArmChair", "chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid username", result);
  }

  //register email that is empty
  @Test
  public void registerNullEmail(){
    User user = new User("Chair", null, "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register email that is empty
  @Test
  public void registerEmptyEmail(){
    User user = new User("Chair", " ", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register email that has less than 1 characters
  @Test
  public void registerShortEmail(){
    User user = new User("Chair", "", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register email that has more than 13 characters
  @Test
  public void registerLongEmail(){
    User user = new User("Chair", "qwertyuioocagjvnxzuj", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register email that has symbols
  @Test
  public void registerSymbolsEmail(){
    User user = new User("Chair", "*#chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register email that starts with a number
  @Test
  public void registerEmailFirstNumber(){
    User user = new User("Chair", "1chair", "1234", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid email", result);
  }

  //register password that is empty
  @Test
  public void registerNullPassword(){
    User user = new User("Chair", "chair", null, "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid password", result);
  }

  //register password that is empty
  @Test
  public void registerEmptyPassword(){
    User user = new User("Chair", "chair", " ", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid password", result);
  }

  //register password that is less than 4 characters
  @Test
  public void registerShortPassword(){
    User user = new User("Chair", "chair", "123", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid password", result);
  }

  //register password more than 14 characters
  @Test
  public void registerLongPassword(){
    User user = new User("Chair", "chair", "1234567890123445678", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Invalid password", result);
  }

  //register user with password that is different than repeat password
  @Test
  public void registerUnmatchingPasswords(){
    User user = new User("Chair", "chair", "123456", "1234");
    String result = userHome.registerUser(user);
    assertEquals("Passwords do not match", result);
  }
}