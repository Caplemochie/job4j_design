package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Gleb", 0, new GregorianCalendar(1997, 6, 9));
        User user2 = new User("Gleb", 0, new GregorianCalendar(1997, 6, 9));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(user1.equals(user2));
        for (Map.Entry<User, Object> pair : map.entrySet()) {

            System.out.println(pair.toString());
        }
    }
}