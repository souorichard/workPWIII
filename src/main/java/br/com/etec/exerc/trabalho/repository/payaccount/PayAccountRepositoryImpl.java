package br.com.etec.exerc.trabalho.repository.payaccount;

import br.com.etec.exerc.trabalho.model.PayAccount;
import br.com.etec.exerc.trabalho.repository.filter.PayAccountFilter;
import br.com.etec.exerc.trabalho.repository.projections.PayAccountDTO;
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
    public Page<PayAccountDTO> filter(PayAccountFilter payAccountFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PayAccountDTO> criteria = builder.createQuery(PayAccountDTO.class);
        Root<PayAccount> root = criteria.from(PayAccount.class);

        criteria.select(builder.construct(PayAccountDTO.class, root.get("id"),
                root.get("date"),
                root.get("date_exp"),
                root.get("client").get("name"),
                root.get("value")));

        Predicate[] predicates = createRepositories(payAccountFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("date")));

        TypedQuery<PayAccountDTO> query = manager.createQuery(criteria);
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

    private void addRestrictionsOfPagination(TypedQuery<PayAccountDTO> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordPerPage = pageable.getPageSize();
        int primaryRegisterOfPage = currentPage * totalRecordPerPage;

        query.setFirstResult(primaryRegisterOfPage);
        query.setMaxResults(totalRecordPerPage);
    }

    private Predicate[] createRepositories(PayAccountFilter payAccountFilter, CriteriaBuilder builder, Root<PayAccount> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (payAccountFilter.getDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("date"), payAccountFilter.getDate()));
        }

        if (payAccountFilter.getDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("date"), payAccountFilter.getDate()));
        }

        if (payAccountFilter.getDate_exp() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("date"), payAccountFilter.getDate_exp()));
        }

        if (payAccountFilter.getDate_exp() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("date"), payAccountFilter.getDate_exp()));
        }

        if (!StringUtils.isEmpty(payAccountFilter.getName())) {
            predicates.add(builder.like(builder.lower(root.get("client").get("name")), "%" + payAccountFilter.getName().toLowerCase() + "%"));
        }

        if (payAccountFilter.getValue() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("value"), payAccountFilter.getValue()));
        }

        if (payAccountFilter.getValue() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("value"), payAccountFilter.getValue()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
