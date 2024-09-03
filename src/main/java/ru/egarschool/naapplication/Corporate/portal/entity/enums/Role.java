package ru.egarschool.naapplication.Corporate.portal.entity.enums;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return null;
    }

    public String getRoleName() {
        return this.name(); // Или можно вернуть любое другое значение
    }
}

