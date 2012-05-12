package com.mtt.domain.entity;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void testEqualWhenIdsMatch() {
        User user1 = createUser(1L);
        user1.setUserName("test1");
        user1.setPassword("test1");
        User user2 = createUser(1L);
        user2.setUserName("test2");
        user2.setPassword("test2");
        assertThat(user1.equals(user2), equalTo(true));
    }

    @Test
    public void testNotEqualWhenIdsNotEqual() {
        User user1 = createUser(1L);
        user1.setUserName("test1");
        user1.setPassword("test1");
        User user2 = createUser(2L);
        user2.setUserName("test1");
        user2.setPassword("test1");
        assertThat(user1.equals(user2), equalTo(false));
    }

    @Test
    public void testHashCode() {
        User user1 = createUser(1L);
        assertThat(user1.hashCode(), equalTo(user1.getId().hashCode()));
    }

    @Test
    public void testToString() {
        User user1 = createUser(1L);
        user1.setUserName("mkelly");
        user1.setPassword("password");
        assertThat(user1.toString(), equalTo("User{id=1, username=\'mkelly\'}"));
    }

    private User createUser(Long id) {
        User user = new User();
        ReflectionTestUtils.setField(user, "id", id);
        return user;
    }
}
