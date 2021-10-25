package com.example.apartmentsforrent.persistence.model;

public class Owner {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String passwordHash;

    public Owner() {
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String phoneNumber;
        private String passwordHash;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Owner build() {
            return new Owner(this);
        }
    }

    private Owner(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.passwordHash = builder.passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
