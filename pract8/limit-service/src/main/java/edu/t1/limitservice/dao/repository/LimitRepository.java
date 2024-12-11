package edu.t1.limitservice.dao.repository;

import edu.t1.limitservice.dao.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LimitRepository extends JpaRepository<Limit, Long> {

    @Modifying
    @Query(value = "update limits set amount_used = 0, modified_at = now()", nativeQuery = true)
    void resetAllAmountUsed();
}
