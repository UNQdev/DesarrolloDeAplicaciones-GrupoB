package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Vendor;



public class VendorDAO extends HibernateGenericDAO<Vendor> implements
GenericDAO<Vendor>  {

    private static final long serialVersionUID = -2670019955898943211L;

    @Override
    protected Class<Vendor> getDomainClass() {
        return Vendor.class;
    }

    @SuppressWarnings("unchecked")
    public List<Vendor> filterByName(String name) {
        List<Vendor> vendors = this.getSession().createCriteria(getDomainClass())
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return vendors;

    }

    @Override
    public Vendor findByName(String name) {
        Vendor vendor = (Vendor) this.getSession().createCriteria(getDomainClass())
                .add(Restrictions.ilike("name", name, MatchMode.EXACT)).uniqueResult();
        return vendor;
    }

}
