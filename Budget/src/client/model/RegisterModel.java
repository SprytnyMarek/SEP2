package client.model;

public interface RegisterModel
{
  String registerUser(String username, String email, String password, String repeatPassword);
}
