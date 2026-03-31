package com.backend.aaz.shared.models.totemdevice.enums;

public enum TotemDeviceStatus {

    ONLINE("Online"),
    OFFLINE("Offline"),
    ERROR("Error");

    private final String displayName;

    TotemDeviceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}