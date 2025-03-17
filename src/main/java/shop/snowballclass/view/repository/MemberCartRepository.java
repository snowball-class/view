package shop.snowballclass.view.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.snowballclass.view.entity.MemberCart;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberCartRepository extends JpaRepository<MemberCart, Long> {
    Optional<MemberCart> findByMemberUUID(UUID memberUUID);
}
