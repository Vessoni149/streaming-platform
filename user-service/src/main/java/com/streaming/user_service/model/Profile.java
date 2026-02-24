package com.streaming.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatarUrl;
    private Boolean isChild;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(name, profile.name) && Objects.equals(avatarUrl, profile.avatarUrl) && Objects.equals(isChild, profile.isChild) && Objects.equals(user, profile.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatarUrl, isChild, user);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "isChild=" + isChild +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
