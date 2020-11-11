package com.progmasters.moovsmart.dto;

public class SendNewPasswordEmailCommand {

    private String emailReset;

    public SendNewPasswordEmailCommand() {
    }

    public SendNewPasswordEmailCommand(String emailReset) {
        this.emailReset = emailReset;
    }

    public String getEmailReset() {
        return emailReset;
    }

    public void setEmailReset(String emailReset) {
        this.emailReset = emailReset;
    }
}
