package br.com.etec.exerc.trabalho.repository.payaccount;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.filter.PayAccountFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PayAccountRepositoryImpl implements PayAccountRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<PayAccount> filter(PayAccountFilter payAccountFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PayAccount> criteria = builder.createQuery(PayAccount.class);
        Root<PayAccount> root = criteria.from(PayAccount.class);

        Predicate[] predicates = createRepositories(payAccountFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("date")));

        TypedQuery<PayAccount> query = manager.createQuery(criteria);

        return null;
    }

    private Predicate[] createRepositories(PayAccountFilter payAccountFilter, CriteriaBuilder builder, Root<PayAccount> root) {
        return null;
    }

}
