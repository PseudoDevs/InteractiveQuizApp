package interactive.android.app.ui;

public class AccountDetails {
    private String account_id,fullname,role;

    public AccountDetails(String account_id, String fullname, String role) {
        this.account_id = account_id;
        this.fullname = fullname;
        this.role = role;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
