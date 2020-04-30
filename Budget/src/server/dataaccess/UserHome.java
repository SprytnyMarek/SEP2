package server.dataaccess;

import shared.datatransfer.User;

public interface UserHome 
{
  String loginResult(User user);
  String registerUser(User user);
}
