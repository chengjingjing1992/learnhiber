import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

/**
 * @author chengjinging
 * @date 2020/9/21 下午5:28
 */
public class Main1 {
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
         *  hibernate sql 查询
         */
        final Session session = getSession();
        try {
            String hql="from Account";
           Query query=session.createQuery(hql);
            for (Object o: query.list()
                 ) {
                System.out.println(o.toString());
            }
        } finally {
            session.close();
        }
    }
}