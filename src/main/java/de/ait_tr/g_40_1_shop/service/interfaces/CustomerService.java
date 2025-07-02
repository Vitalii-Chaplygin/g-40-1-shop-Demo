package de.ait_tr.g_40_1_shop.service.interfaces;

import de.ait_tr.g_40_1_shop.domain.dto.CustomerDto;

import java.math.BigDecimal;
import java.util.List;

/*
Функционал сервиса покупателей.
• Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).
• Вернуть всех покупателей из базы данных (активных).
• Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
• Изменить одного покупателя в базе данных по его идентификатору.
• Удалить покупателя из базы данных по его идентификатору.
• Удалить покупателя из базы данных по его имени.
• Восстановить удалённого покупателя в базе данных по его идентификатору.
• Вернуть общее количество покупателей в базе данных (активных).
• Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
• Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)
• Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
• Удалить товар из корзины покупателя по их идентификаторам
• Полностью очистить корзину покупателя по его идентификатору (если он активен

 */
public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    List<CustomerDto> getAllActiveCustomers();
    CustomerDto getActiveCustomerById(Long customerId);
    CustomerDto update(CustomerDto customerDto);
    void deleteById(Long customerId);
    void deleteByName(String name);
    CustomerDto restoreByid(Long customerId);
    long getCountAllActiveCustomer();
    BigDecimal getAlLPriceCardActiveCustomer (Long customerId);
    BigDecimal getAveragePriceActiveCustomerById(Long customerId);
    void addActiveProductToActiveCardById(Long customerId,Long productId);
    void delProductFromCardById(Long productId,Long cartId);
    void  delAllProductFromCardById(Long customerId);
}
