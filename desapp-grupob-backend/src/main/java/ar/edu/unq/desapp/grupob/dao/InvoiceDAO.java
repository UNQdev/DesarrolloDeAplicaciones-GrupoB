package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Invoice;

public class InvoiceDAO extends HibernateGenericDAO<Invoice> implements
        GenericDAO<Invoice> {
    private static final long serialVersionUID = 939209274312970536L;

    @Override
    protected Class<Invoice> getDomainClass() {
        return Invoice.class;
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> filterByName(String number) {
        List<Invoice> invoices = this.getSession()
                .createCriteria(getDomainClass())
                .add(Restrictions.ilike("number", number, MatchMode.ANYWHERE))
                .list();
        return invoices;
    }

    @Override
    public Invoice findByName(String number) {
        Invoice invoice = (Invoice) this.getSession()
                .createCriteria(getDomainClass())
                .add(Restrictions.ilike("number", number, MatchMode.ANYWHERE))
                .uniqueResult();
        return invoice;
    }
}
