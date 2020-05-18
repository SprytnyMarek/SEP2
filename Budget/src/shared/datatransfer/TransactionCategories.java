package shared.datatransfer;

public class TransactionCategories
{
  private String category_code;
  private String category_description;
  private String parent_category;

  public TransactionCategories(String category_code, String category_description, String parent_category)
  {
    this.category_code = category_code;
    this.category_description = category_description;
    this.parent_category = parent_category;
  }

  public String getCategory_code()
  {
    return category_code;
  }

  public void setCategory_code(String category_code)
  {
    this.category_code = category_code;
  }

  public String getCategory_description()
  {
    return category_description;
  }

  public void setCategory_description(String category_description)
  {
    this.category_description = category_description;
  }

  public String getParent_category()
  {
    return parent_category;
  }

  public void setParent_category(String parent_category)
  {
    this.parent_category = parent_category;
  }
}
