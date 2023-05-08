package com.sudagoarth.springquasar.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(nullable = false)
    private boolean enabled;

    @Column(length = 64)
    private String image;

    @Column(name = "iqama", length = 64)
    private String iqama;

    @Column(name = "phone", length = 64)
    private String phone;

    @Column(name = "gender", length = 64)
    private String gender;

    @Column(name = "birth_date", length = 64)
    private String birthDate;



    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();

    public void addRoles(Role role) {
        roles.add(role);
    }

    public void removeRoles(Role role) {
        roles.remove(role);
    }

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, String image, String iqama, String phone, String gender, String birthDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.iqama = iqama;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }

    public String getIqama() {
        return iqama;
    }

    public void setIqama(String iqama) {
        this.iqama = iqama;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", enabled=" + enabled + ", image='" + image + '\'' + '}';
    }

    @Transient
    public String getImagePath() {
        if (id == null || image == null)
            return "/images/default-user.png";
        return "/user-images/" + this.id + "/" + this.image;
    }

}
