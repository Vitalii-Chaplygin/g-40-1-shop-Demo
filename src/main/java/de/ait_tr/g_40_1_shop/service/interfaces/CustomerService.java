package de.ait_tr.g_40_1_shop.service.interfaces;

import de.ait_tr.g_40_1_shop.domain.entity.Customer;

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

    Customer save(Customer customer);
    List<Customer> getAllActiveCustomer();
    Customer getActiveCustomerById(Long customerId);
    Customer updateById(Long customerId);
    void deleteById(Long customerId);
    void deleteByName(String name);
    Customer restoreByid(Long customerId);
    int getCountAllActiveCustomer(List<Customer> customers);
    BigDecimal getAlLPriceCardActiveCustomer (Customer customer);
    BigDecimal getAveragePriceActiveCustomerById(Long customerId);
    void addActiveProductToActiveCardById(Long customerId,Long cardId);
    void delProductFromCardById(Long productId,Long cardId);
    void  delAllProductFromCardById(Long customerId);
}
