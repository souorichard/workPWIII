package br.com.etec.exerc.trabalho.repository.payaccount;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.filter.PayAccountFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
        addRestrictionsOfPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(payAccountFilter));
    }

    private Long total(PayAccountFilter payAccountFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PayAccount> root = criteria.from(PayAccount.class);

        Predicate[] predicates = createRepositories(payAccountFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("date")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void addRestrictionsOfPagination(TypedQuery<PayAccount> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordPerPage = pageable.getPageSize();
        int primaryRegisterOfPage = currentPage * totalRecordPerPage;

        query.setFirstResult(primaryRegisterOfPage);
        query.setMaxResults(totalRecordPerPage);
    }

    private Predicate[] createRepositories(PayAccountFilter payAccountFilter, CriteriaBuilder builder, Root<PayAccount> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(payAccountFilter.getDate())) {
            predicates.add(builder.like(builder.lower(root.get("date")), "%" + payAccountFilter.getDate() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
