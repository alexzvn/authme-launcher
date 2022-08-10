package dev.alexzvn.authentication.data;

public class RegisterData {
  
  private String email;
  private String username;
  private String password;

  public RegisterData() {}

  public RegisterData(String email, String username, String password) {
    super();
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isValid() {
    return email != null && username != null && password != null && email.length() > 0 && username.length() > 0 && password.length() > 0;
  }

  @Override
  public String toString() {
    return "RegisterData [email=" + email + ", username=" + username + ", password=" + password + "]";
  }
}
