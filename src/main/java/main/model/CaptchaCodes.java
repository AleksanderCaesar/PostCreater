package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "captcha_codes")
public class CaptchaCodes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date time;
    private String code;
    @Column(name = "secret_code")
    private String secretCode;

    public CaptchaCodes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
}
