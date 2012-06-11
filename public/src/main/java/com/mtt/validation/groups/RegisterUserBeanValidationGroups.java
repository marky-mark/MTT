package com.mtt.validation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

public class RegisterUserBeanValidationGroups {

    public interface FirstName1{}
    public interface FirstName2{}
    public interface FirstName3{}

    @GroupSequence({Default.class, FirstName1.class, FirstName2.class, FirstName3.class})
    public interface FirstName{}

    public interface LastName1{}
    public interface LastName2{}
    public interface LastName3{}

    @GroupSequence({Default.class, LastName1.class, LastName2.class, LastName3.class})
    public interface LastName{}

    public interface Email1{}
    public interface Email2{}
    public interface Email3{}
    public interface Email4{}
    public interface Email5{}

    @GroupSequence({Default.class, Email1.class, Email2.class, Email3.class, Email4.class, Email5.class})
    public interface EmailAddress{}

    public interface ConfirmedEmail1{}
    public interface ConfirmedEmail2{}
     public interface ConfirmedEmail3{}

    @GroupSequence({Default.class, ConfirmedEmail1.class, ConfirmedEmail2.class, ConfirmedEmail3.class})
    public interface ConfirmedEmailAddress{}

    public interface Telephone1{}
    public interface Telephone2{}
    public interface Telephone3{}

    @GroupSequence({Default.class, Telephone1.class, Telephone2.class, Telephone3.class})
    public interface Telephone{}

    public interface Password1{}
    public interface Password2{}
    public interface Password3{}
    public interface Password4{}

    @GroupSequence({Default.class, Password1.class, Password2.class, Password3.class})
    public interface Password{}

    public interface ConfirmedPassword1{}
    public interface ConfirmedPassword2{}
    public interface ConfirmedPassword3{}

    @GroupSequence({Default.class, ConfirmedPassword1.class, ConfirmedPassword2.class, ConfirmedPassword3.class})
    public interface ConfirmedPassword{}

}
