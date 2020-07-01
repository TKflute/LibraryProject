package com.cognixia.jump.model;
public class PatronModel {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean accountFrozen;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public boolean isAccountFrozen() {
        return accountFrozen;
    }
    public void setAccountFrozen(boolean accountFrozen) {
        this.accountFrozen = accountFrozen;
    }
    @Override
    public String toString() {
        return "PatronModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
                + userName + ", passWord=" + passWord + ", accountFrozen=" + accountFrozen + ", getId()=" + getId()
                + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getUserName()="
                + getUserName() + ", getPassWord()=" + getPassWord() + ", isAccountFrozen()=" + isAccountFrozen()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
    public PatronModel(int id, String firstName, String lastName, String userName, String passWord,
            boolean accountFrozen) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.accountFrozen = accountFrozen;
    }
    
}