package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Payment;

public class PaymentDAO extends HibernateGenericDAO<Payment> implements
        GenericDAO<Payment> {

    private static final long serialVersionUID = 9035841822549761007L;

    @Override
    public List<Payment> filterByName(String concept) {
        @SuppressWarnings("unchecked")
        List<Payment> payments = this.getSession().createCriteria(getDomainClass())
                .add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE))
                .list();
        return payments;
    }
    @Override
    protected Class<Payment> getDomainClass() {
        return Payment.class;
    }

    @Override
    public Payment findByName(String concept) {
       Payment payment = (Payment) this.getSession().createCriteria(getDomainClass())
               .add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE))
               .uniqueResult();
       return payment;
    }

}
