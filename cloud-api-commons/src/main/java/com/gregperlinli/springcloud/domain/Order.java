package com.gregperlinli.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author gregPerlinLi
 * @since 2022-03-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * Order status: 0 -> Creating, 1 -> Finish
     */
    private Integer status;
}
