package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenGetLastProp() {
        Config config = new Config("./app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenGetLastProp2() {
        Config config = new Config("./app.proper.properties");
        config.load();
        config.value("name");
    }

    @Test
    public void whenTryToGetPropWithComment() {
        Config config = new Config("./app.comment.properties");
        config.load();
        assertNull(config.value("hibernate.connection.password2"));
    }
}