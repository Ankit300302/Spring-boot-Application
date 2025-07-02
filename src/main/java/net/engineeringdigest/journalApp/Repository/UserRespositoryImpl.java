package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRespositoryImpl
{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> getUserForSA(){
        Query query = new Query();
        //query.addCriteria(Criteria.where("username").is("user3"));
        //query.addCriteria(Criteria.where("age").gte(20));
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n"));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//        Criteria.where("email").exists(true), Criteria.where("sentimentAnalysis").is(true)));

       List<Users> users =  mongoTemplate.find(query,Users.class);
        return users;
    }
}
