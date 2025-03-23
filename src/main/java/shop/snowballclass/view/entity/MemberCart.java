package shop.snowballclass.view.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class MemberCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    UUID memberUUID;
    @Column(nullable = false, unique = true)
    Long cartId;
    @Column(nullable = false)
    @ColumnDefault("0")
    Long totalPrice;
    @Column(nullable = false)
    @ColumnDefault("0")
    Long totalQuantity;
}
