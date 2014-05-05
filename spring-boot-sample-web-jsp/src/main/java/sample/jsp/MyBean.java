//package sample.jsp;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyBean {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public MyBean(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @PostConstruct
//    public void version() {
//        jdbcTemplate.execute("CALL CURRENT_TIME()");
//    }
//
//}