package com.SW.DA;

import com.SW.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDA  {
    @Autowired
    private EntityManager entityManager;

    private Set<String> logedInAdminUsers = new HashSet ();
    public void addUser (User user) {
        Session currentSession = entityManager.unwrap ( Session.class );
        currentSession.save ( user );
    }

    public Object retrieveRegisteredUsers (String name) {
        Session currentSession = entityManager.unwrap ( Session.class );
        for (String s:logedInAdminUsers){
            if(s.equals ( name )){
            Query<User> query = currentSession.createQuery ( "from User",User.class );
            List<User> list = query.getResultList();
            return list;
            }
        }
        return "you are not allowed to list users";
    }

    public User retrieveUserByEmail(String email){
        Session currentSession = entityManager.unwrap ( Session.class );
        User user = currentSession.getReference ( User.class,email);
        if(user.getType ().equalsIgnoreCase ( "admin" )){
            logedInAdminUsers.add ( user.getName () );
        }
        return user;
    }
}
