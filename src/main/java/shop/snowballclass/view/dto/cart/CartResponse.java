package shop.snowballclass.view.dto.cart;

import java.util.UUID;

public record CartResponse(
    Long id,
    UUID memberUUID
) {

}
