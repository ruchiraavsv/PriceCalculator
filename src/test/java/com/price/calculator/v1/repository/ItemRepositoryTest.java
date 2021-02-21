package com.price.calculator.v1.repository;

import com.price.calculator.v1.constants.AppConstants;
import com.price.calculator.v1.constants.TestConstants;
import com.price.calculator.v1.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        itemRepository.deleteAll();
        itemRepository.save(new Item(1, AppConstants.PENGUINEAR, TestConstants.penguinEar_carton_size, TestConstants.penguinEar_carton_price));
    }

    @Test
    void testFindItemByName() {
        Item item = itemRepository.findByName(AppConstants.PENGUINEAR).get();
        Assertions.assertEquals(TestConstants.penguinEar_carton_size,item.getCartonSize());
        Assertions.assertEquals(TestConstants.penguinEar_carton_price,item.getCartonPrice());
    }

    @Test
    void tesCreateItem() {
        Item item = new Item(2, AppConstants.HORSESHOE, TestConstants.horseShoe_carton_size, TestConstants.horseShoe_carton_price);
        Item itemSaved = itemRepository.save(item);
        Assertions.assertTrue(itemRepository.existsById(item.getId()));
        Assertions.assertEquals(item.getId(), itemSaved.getId());
        Assertions.assertEquals(item.getCartonSize(), itemSaved.getCartonSize());
        Assertions.assertEquals(item.getCartonPrice(), itemSaved.getCartonPrice());
    }

    @Test
    void tesUpdateItem() {
        Item item = new Item(2, AppConstants.HORSESHOE, TestConstants.horseShoe_carton_size, TestConstants.horseShoe_carton_price);
        itemRepository.save(item);
        item.setCartonPrice(200);
        Item itemUpdated = itemRepository.save(item);
        Assertions.assertEquals(item.getCartonPrice(), 200);
    }

    @Test
    void tesDeleteItem() {
        int id = itemRepository.findByName(AppConstants.PENGUINEAR).get().getId();
        Assertions.assertTrue(itemRepository.existsById(id));
        itemRepository.deleteById(id);
        Assertions.assertFalse(itemRepository.existsById(id));
    }

}
