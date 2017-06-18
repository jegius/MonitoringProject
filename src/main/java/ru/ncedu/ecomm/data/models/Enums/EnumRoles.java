package ru.ncedu.ecomm.data.models.Enums;

public enum EnumRoles {
    SUPERUSER(1),
    USER(3);

    private long roleIdentifier;

    EnumRoles(long role) {
        this.roleIdentifier = role;
    }

    public long getRole() {
        return roleIdentifier;
    }
}
