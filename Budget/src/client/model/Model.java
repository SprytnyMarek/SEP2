package client.model;

public interface Model
{
  String loginResult(String username, String password);
  String registerUser(String username, String email, String password, String repeatPassword);
}