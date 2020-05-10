package shared.datatransfer;

import java.io.Serializable;

public class User implements Serializable
{
  private String username;
  private String email;
  private String password;
  private String repeatPassword;

  public User(String username, String password)
  {
    this.username = username;
    this.password = password;
  }
  public User(String username, String email, String password, String repeatPassword){
    this.username = username;
    this.email = email;
    this.password = password;
    this.repeatPassword = repeatPassword;
  }

  public String getUsername()
  {
    return username;
  }
  public String getEmail(){return email;}
  public void setEmail(String email){this.email = email;}

  public String getPassword()
  {
    return password;
  }
  public String getRepeatPassword(){return repeatPassword;}

}
