package com.streaming.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole rol;
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status")
    private SubscriptionStatus subscriptionStatus;
    @CreatedDate
    private LocalDateTime createdAt;
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @Getter(AccessLevel.NONE)
    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile){
        if(profile != null){
            this.profiles.add(profile);
            profile.setUser(this);
        }
    }

    public void removeProfile(Profile profile){
        profile.setUser(null);
        this.profiles.remove(profile);
    }

    public void removeProfiles(){
        Iterator iterator = this.profiles.iterator();
        while(iterator.hasNext()){
            Profile profile = (Profile) iterator.next();
            profile.setUser(null);
            iterator.remove();
        }
    }

    public List<Profile> getProfiles(){
        return Collections.unmodifiableList(profiles);
    }

    @Override
    public String toString() {
        return "User{" +
                "enabled=" + enabled +
                ", createdAt=" + createdAt +
                ", subscriptionStatus='" + subscriptionStatus + '\'' +
                ", rol=" + rol +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
