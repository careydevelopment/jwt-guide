package com.careydevelopment.jwtguide.model;

public class Contact {

    private Integer id;
    private String firstName;
    private String lastName;
    private boolean authority;
    private String email;
    private Integer salesOwnerId = 1;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public boolean isAuthority() {
        return authority;
    }
    public void setAuthority(boolean authority) {
        this.authority = authority;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getSalesOwnerId() {
        return salesOwnerId;
    }
    public void setSalesOwnerId(Integer salesOwnerId) {
        this.salesOwnerId = salesOwnerId;
    }
}
