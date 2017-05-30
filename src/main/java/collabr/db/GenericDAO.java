package collabr.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class GenericDAO<E> extends AbstractDAO<E> {

    public GenericDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Session currentSession(){
        return super.currentSession();
    }

    @Override
    public Criteria criteria(){
        return super.criteria();
    }

    @Override
    public List<E> list(Query query){
        return super.list(query);
    }

    @Override
    public List<E> list(Criteria criteria){
        return super.list(criteria);
    }

    public E saveOrUpdate(E entity){
        return persist(entity);
    }

    @Override
    public E uniqueResult(Criteria criteria){
        return super.uniqueResult(criteria);
    }

    @Override
    public E uniqueResult(Query query){
        return super.uniqueResult(query);
    }

    public E findyId(int id){
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq("id", id));
        return uniqueResult(criteria);
    }

    public List<E> findAll(){
        Criteria criteria = criteria();
        return list(criteria);
    }

    public void delete(E entity) {
        currentSession().delete(entity);
    }
}
