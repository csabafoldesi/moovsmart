package com.progmasters.moovsmart.dto;

public class ResendEmailCommand {

    private String emailResend;

    public ResendEmailCommand() {
    }

    public ResendEmailCommand(String emailResend) {
        this.emailResend = emailResend;
    }

    public String getEmailResend() {
        return emailResend;
    }

    public void setEmailResend(String emailResend) {
        this.emailResend = emailResend;
    }
}
