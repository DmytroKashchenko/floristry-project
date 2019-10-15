package ua.dmytrokashchenko.floristry.domain.user;

import java.util.Objects;

public class User {
    private static Long counter = 0L;
    private final Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole userRole;
    private Address address;

    private User(UserBuilder userBuilder) {
        if (!userBuilder.setId) {
            this.id = -1L;
        } else {
            this.id = ++counter;
        }
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.userRole = userBuilder.userRole;
        this.address = userBuilder.address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getUserRole() == user.getUserRole() &&
                Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail(), getPassword(), getUserRole(), getAddress());
    }

    @Override
    public String toString() {
        return id.toString() + " " + name + " " + surname + " " + email;
    }

    public static class UserBuilder {
        private String name;
        private String surname;
        private String email;
        private String password;
        private UserRole userRole;
        private Address address;
        private boolean setId = true;

        public UserBuilder() {
        }

        public User build() {
            return new User(this);
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder doNotSetId() {
            this.setId = false;
            return this;
        }
    }
}
