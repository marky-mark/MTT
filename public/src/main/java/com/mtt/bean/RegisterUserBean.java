package com.mtt.bean;

import com.mtt.validation.Matches;
import com.mtt.validation.NotHtml;
import com.mtt.validation.UniqueEmail;
import com.mtt.validation.groups.RegisterUserBeanValidationGroups;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Register User Bean
 */
@Matches.List({
        @Matches(field = "emailAddress",
                verifyField = "confirmedEmailAddress",
                message = "email addresses foes not match",
                groups = RegisterUserBeanValidationGroups.ConfirmedEmail3.class),
        @Matches(field = "password",
                verifyField = "confirmedPassword",
                message = "passwords do not match",
                groups = RegisterUserBeanValidationGroups.ConfirmedPassword3.class)
})
public class RegisterUserBean {

    public static final java.lang.String URL_REG_EX = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
    public static final java.lang.String LOOKS_LIKE_PHONE_NUMBER_REGEX = "^[0-9 ()\\-\\+]+$";

    @NotBlank(message = "must have a first name", groups = RegisterUserBeanValidationGroups.FirstName1.class)
    @Size(message = "must have certain length",
            min = 1,
            max = 10,
            groups = RegisterUserBeanValidationGroups.FirstName2.class)
    @NotHtml(message = "no html is allowed", groups = RegisterUserBeanValidationGroups.FirstName3.class)
    private String firstName;

    @NotBlank(message = "must have a last name", groups = RegisterUserBeanValidationGroups.LastName1.class)
    @Size(message = "must have a certain length",
            min = 1,
            max = 10,
            groups = RegisterUserBeanValidationGroups.LastName2.class)
    @NotHtml(message = "no html is allowed", groups = RegisterUserBeanValidationGroups.LastName3.class)
    private String lastName;

    @NotBlank(message = "email is missing", groups = RegisterUserBeanValidationGroups.Email1.class)
    @Email(message = "not a valid email", groups = RegisterUserBeanValidationGroups.Email3.class)
    @UniqueEmail(message = "email already exists", groups = RegisterUserBeanValidationGroups.Email4.class)
    @Size(message = "email lengh",
            min = 1,
            max = 25,
            groups = RegisterUserBeanValidationGroups.Email2.class)
    private String emailAddress;

    @NotBlank(message = "please confirm email address", groups = RegisterUserBeanValidationGroups.ConfirmedEmail1.class)
    @Size(message = "must be of a certain lenth",
            min = 1,
            max = 25,
            groups = RegisterUserBeanValidationGroups.ConfirmedEmail2.class)
    private String confirmedEmailAddress;

    @NotBlank(message = "telephone number is missing", groups = RegisterUserBeanValidationGroups.Telephone1.class)
    @Pattern(message = "not a telephone number", regexp = LOOKS_LIKE_PHONE_NUMBER_REGEX, groups = RegisterUserBeanValidationGroups.Telephone3.class)
    @Size(message = "telephone must be of a certain length",
            min = 1,
            max = 25,
            groups = RegisterUserBeanValidationGroups.Telephone2.class)
    private String telephoneNumber;

    @NotBlank(message = "password is missing", groups = RegisterUserBeanValidationGroups.Password1.class)
    @NotHtml(message = "no html is allowed for the password", groups = RegisterUserBeanValidationGroups.Password2.class)
    @Size.List({
            @Size(message = "password min length",
                    min = 1,
                    groups = RegisterUserBeanValidationGroups.Password3.class),
            @Size(message = "password max length",
                    max = 10,
                    groups = RegisterUserBeanValidationGroups.Password3.class)
    })
    protected String password;

    @NotBlank(message = "please confirm your password",
              groups = RegisterUserBeanValidationGroups.ConfirmedPassword1.class)
    @Size.List({
            @Size(message = "confirm password min length",
                    min = 1,
                    groups = RegisterUserBeanValidationGroups.ConfirmedPassword2.class),
            @Size(message = "confirm password max length",
                    max = 10,
                    groups = RegisterUserBeanValidationGroups.ConfirmedPassword2.class)
    })
    protected String confirmedPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getConfirmedEmailAddress() {
        return confirmedEmailAddress;
    }

    public void setConfirmedEmailAddress(String confirmedEmailAddress) {
        this.confirmedEmailAddress = confirmedEmailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterUserBean)) return false;

        RegisterUserBean bean = (RegisterUserBean) o;

        if (confirmedEmailAddress != null ? !confirmedEmailAddress.equals(bean.confirmedEmailAddress) : bean.confirmedEmailAddress != null)
            return false;
        if (confirmedPassword != null ? !confirmedPassword.equals(bean.confirmedPassword) : bean.confirmedPassword != null)
            return false;
        if (emailAddress != null ? !emailAddress.equals(bean.emailAddress) : bean.emailAddress != null) return false;
        if (firstName != null ? !firstName.equals(bean.firstName) : bean.firstName != null) return false;
        if (lastName != null ? !lastName.equals(bean.lastName) : bean.lastName != null) return false;
        if (password != null ? !password.equals(bean.password) : bean.password != null) return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(bean.telephoneNumber) : bean.telephoneNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (confirmedEmailAddress != null ? confirmedEmailAddress.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmedPassword != null ? confirmedPassword.hashCode() : 0);
        return result;
    }
}
