package br.com.etec.exerc.trabalho.repository.client;

import br.com.etec.exerc.trabalho.model.Client;
import br.com.etec.exerc.trabalho.repository.filter.ClientFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

        return null;
    }

    private Predicate[] createRestrictions(ClientFilter clientFilter, CriteriaBuilder builder, Root<Client> root) {
        return null;
    }

}
