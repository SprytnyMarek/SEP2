package client.model;

import shared.datatransfer.SpendingsInfo;

import java.util.ArrayList;

public interface AdminModel
{
  ArrayList<SpendingsInfo> getInfoForAdmin(String userToGet);
  ArrayList getAllUsernamesForAdmin();
}
