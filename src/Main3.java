import com.kede.h2.entity.Account;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author chengjinging
 * @date 2020/9/21 下午5:28
 */
public class Main3 {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        /**
         *  hibernate sql update
         */
        final Session session = getSession();
        try {
            Account account=new Account();
            account.setId(1006);
            account.setAge(99);
            account.setBalance(10000);
            account.setPassword("123456");
            account.setUsername("张四丰");
            /**
             * save() 方法需要放到 save() 方法
             */
            Transaction transaction=session.beginTransaction();
            session.update(account);
            transaction.commit();

        } finally {
            session.close();
        }
    }
}