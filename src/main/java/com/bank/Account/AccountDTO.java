package com.bank.Account;

public class AccountDTO {
    private String username = null;
    private String BACK_PIN = null;
    private String account = null;

    public AccountDTO() {
        super();
    }

    public AccountDTO(String username, String BACK_PIN, String account) {
        super();
        this.username = username;
        this.BACK_PIN = BACK_PIN;
        this.account = account;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBackPin() {
        return this.BACK_PIN;
    }

    public void setBackPin(String BACK_PIN) {
        this.BACK_PIN = BACK_PIN;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
