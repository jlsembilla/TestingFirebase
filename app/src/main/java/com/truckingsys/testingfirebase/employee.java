package com.truckingsys.testingfirebase;

public class employee {
    private String firstname;
    private String lastname;
    public employee(){}
    public employee(String Firstname, String Lastname)
    {
        this.firstname = Firstname;
        this.lastname = Lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
