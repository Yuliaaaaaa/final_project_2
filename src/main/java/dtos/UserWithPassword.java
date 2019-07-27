package dtos;

import java.util.Date;

/**
 * @author Yuliia Shcherbakova ON 19.07.2019
 * @project publishing
 */
public class UserWithPassword {
    private int userId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private char sex;
    private Long phoneNumber;
    private String email;
    private String password;

    /**
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param sex
     * @param email
     * @param password
     */
    public UserWithPassword(String firstName, String lastName, Date birthDate, char sex, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.email = email;
        this.password = password;
    }

    /**
     *
     */
    public UserWithPassword() {
    }

    /**
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return
     */
    public char getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * @return
     */
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
