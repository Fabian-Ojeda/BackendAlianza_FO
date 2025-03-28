package org.alianza.backendalianza_fo.jpa.repository;
import org.alianza.backendalianza_fo.jpa.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing client-related data.
 *
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * for the {@link ClientEntity} entity.
 *
 */

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    /**
     * Retrieves a list of clients whose sharedKey contains the specified substring.
     *
     * @param sharedKey substring to search in sharedKey.
     * @return list of matching ClientEntity.
     */
    List<ClientEntity> findBySharedKeyContaining(String sharedKey);

    /**
     * Retrieves a list of clients whose sharedKey exactly matches the specified value.
     *
     * @param sharedKey value to search.
     * @return list of matching ClientEntity (usually 0 or 1 result).
     */
    List<ClientEntity> findBySharedKey(String sharedKey);

}