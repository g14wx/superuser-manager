package com.genericcompany.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "seed")
@Validated
public class SeedConfiguration {
    private String adminEmail;
    private String adminPassword;
    private String regularUserEmail;
    private String regularUserPassword;
    private String devUserEmail;
    private String devUserPassword;
    private String qaUserEmail;
    private String qaUserPassword;
    private String testerEmail;
    private String testerPassword;

    // Getters and Setters
    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }
    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
    public String getRegularUserEmail() { return regularUserEmail; }
    public void setRegularUserEmail(String regularUserEmail) { this.regularUserEmail = regularUserEmail; }
    public String getRegularUserPassword() { return regularUserPassword; }
    public void setRegularUserPassword(String regularUserPassword) { this.regularUserPassword = regularUserPassword; }
    public String getDevUserEmail() { return devUserEmail; }
    public void setDevUserEmail(String devUserEmail) { this.devUserEmail = devUserEmail; }
    public String getDevUserPassword() { return devUserPassword; }
    public void setDevUserPassword(String devUserPassword) { this.devUserPassword = devUserPassword; }
    public String getQaUserEmail() { return qaUserEmail; }
    public void setQaUserEmail(String qaUserEmail) { this.qaUserEmail = qaUserEmail; }
    public String getQaUserPassword() { return qaUserPassword; }
    public void setQaUserPassword(String qaUserPassword) { this.qaUserPassword = qaUserPassword; }
    public String getTesterEmail() { return testerEmail; }
    public void setTesterEmail(String testerEmail) { this.testerEmail = testerEmail; }
    public String getTesterPassword() { return testerPassword; }
    public void setTesterPassword(String testerPassword) { this.testerPassword = testerPassword; }
}