package br.com.etec.exerc.trabalho.repository.client;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.filter.ClientFilter;
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

public class ClientRepositoryImpl implements ClientRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Client> filter(ClientFilter clientFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
        Root<Client> root = criteria.from(Client.class);

        Predicate[] predicates = createRestrictions(clientFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("name")));

        TypedQuery<Client> query = manager.createQuery(criteria);
        addRestrictionsOfPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(clientFilter));
    }

    private Long total(ClientFilter clientFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Client> root = criteria.from(Client.class);

        Predicate[] predicates = createRestrictions(clientFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("name")));

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void addRestrictionsOfPagination(TypedQuery<Client> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordPerPage = pageable.getPageSize();
        int primaryRegisterOfPage = currentPage * totalRecordPerPage;

        query.setFirstResult(primaryRegisterOfPage);
        query.setMaxResults(totalRecordPerPage);
    }

    private Predicate[] createRestrictions(ClientFilter clientFilter, CriteriaBuilder builder, Root<Client> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(clientFilter.getName())) {
            predicates.add(builder.like(builder.lower(root.get("name")), "%" + clientFilter.getName().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
