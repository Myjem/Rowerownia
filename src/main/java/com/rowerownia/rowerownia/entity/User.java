package com.rowerownia.rowerownia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class User{
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    @Column(updatable = false)
    private Integer userId;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "accessLevel", nullable = false)
    private Enums.level accessLevel= Enums.level.USER;
    @Column(name = "failedLoginAttempts", nullable = false)
    private int failedLoginAttempts = 0;
    @Column(name = "isBlocked", nullable = false)
    private boolean isBlocked = false;
//    private Boolean locked = false;
//    private Boolean enabled = false;

    public User() {
    }

    public User(String login, String password, LocalDate birthDate, String name, String surname) {
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.name = name;
        this.surname = surname;
        this.accessLevel = Enums.level.USER;
        this.failedLoginAttempts = 0;
        this.isBlocked = false;
    }

    public User(String login, String password, LocalDate birthDate, String name, String surname, Enums.level accessLevel) {
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.name = name;
        this.surname = surname;
        this.accessLevel = accessLevel;
        this.failedLoginAttempts = 0;
        this.isBlocked = false;
    }

    public Integer getUserId() {
        return userId;
    }

    public Enums.level getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Enums.level accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+this.accessLevel.name());
//        return Collections.singletonList(authority);
//    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public String getUsername() {
//        return getLogin();
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public int getFailedLoginAttempts(){
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts){
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean getIsBlocked(){
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked){
        this.isBlocked = isBlocked;
    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !locked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    @Override
//    public final boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
//        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
//        if (thisEffectiveClass != oEffectiveClass) return false;
//        User user = (User) o;
//        return getUserId() != null && Objects.equals(getUserId(), user.getUserId());
//    }
//
//    @Override
//    public final int hashCode() {
//        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
//    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts + '\'' +
                ", isBlocked=" + isBlocked + '\'' +
                '}';
    }
}
