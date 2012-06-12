package com.mtt.domain.entity;

import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity representing a key generated for user activation.
 */
@Entity
@Table(name = "usr_activation_key")
public class UserActivationKey {

    private static final int ACTIVE_DAYS = 14;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activation_key", nullable = false, unique = true)
    private String key;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    //Many Activation Keys may map to 1 User...but the activation Key may only have 1 User
    //ANOTHER EXAMPLE: Many Students may have the same address..but they may only have 1 address
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public DateTime getExpiryDate() {
        return new DateTime(expiryDate);
    }

    public User getUser() {
        return user;
    }

    /**
     * Initialise the activation key.
     *
     * @param user                the user who "owns" the key.
     * @param keyGeneratorService service for generating key
     * @param clock               the date/time source
     */
//    public void initialise(User user, KeyGeneratorService keyGeneratorService, Clock clock) {
//        if (id == null) {
//            this.user = user;
//            expiryDate = clock.now().plus(Days.days(ACTIVE_DAYS)).toDate();
//            key = keyGeneratorService.generateKey(user.getUsername(), user.getSalt());
//        }
//    }
}

