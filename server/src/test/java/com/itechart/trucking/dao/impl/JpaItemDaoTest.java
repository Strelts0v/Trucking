package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.impl.JpaItemDao;
import com.itechart.trucking.domain.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("test")
public class JpaItemDaoTest {

    @Autowired
    private EntityManager entityManager;

    private Item item;

    private JpaItemDao itemDao;

    private static final String NAME="item_name";

    private static final Integer PRICE= 15;

    private static final Item.Type TYPE = Item.Type.COUNT;

    @Before
    public void setItem() {
        //itemDao = new JpaItemDao(entityManager);
        item = new Item(NAME, PRICE, TYPE);
    }

    @Test
    public void getItemByIdShouldReturnNoItemTest() throws Exception {
        final int invalidItemId = 5;
        Optional<Item> item = itemDao.findItemById(invalidItemId);

        final String errorMessage = "Expected empty optional item object";
        Assert.assertFalse(errorMessage, item.isPresent());
    }

    @Test
    public void getItemByIdShouldReturnCorrespondItem() throws Exception {
        Item expectedItem = itemDao.addItem(item);
        final int addedItemId = expectedItem.getId();
        Optional<Item> actualItem = itemDao.findItemById(addedItemId);

        final String errorMessage = "Expected and actual items are different";
        assertEquals(errorMessage, expectedItem, actualItem.orElse(new Item()));
    }

    @Test
    public void findItemByNameShouldReturnExactItem() throws Exception {
        itemDao.addItem(item);

        Item item1 = new Item();
        item1.setName("name");
        itemDao.addItem(item1);

        final String searchNamePattern = "name";
        Optional<Item> item2 = itemDao.findItemByName(searchNamePattern);

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, item1, item2);
    }

    @Test
    public void getItemsByExistedPageShouldReturnExactItemCount() throws Exception {
        itemDao.addItem(item);

        Item item1 = new Item();
        item1.setName("test name");
        itemDao.addItem(item1);

        final int page = 1;
        final int pageSize = 20;
        List<Item> itemList = itemDao.findAllByPage(page, pageSize);

        final int expectedItemCount = 2;
        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedItemCount, itemList.size());
    }

    @Test
    public void afterAddItemGetItemCountShouldReturnExactItemCount() throws Exception {
        itemDao.addItem(item);
        final int actualItemCount = itemDao.getItemCount();
        final int expectedItemCount = 1;

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedItemCount, actualItemCount);
    }

    @Test
    public void afterUpdateItemGetItemByIdShouldReturnCorrespondItem() throws Exception {
        itemDao.addItem(item);
        item.setName("new name");
        itemDao.editItem(item);

        Optional<Item> actualItem = itemDao.findItemById(item.getId());

        final String errorMessage = "Expected and actual client are different";
        assertEquals(errorMessage, item, actualItem.orElse(new Item()));
    }

    @Test
    public void afterDeleteItemGetItemCountShouldReturnCorrespondItemCount() throws Exception {
        itemDao.addItem(item);
        itemDao.deleteItem(item);
        final int expectedItemCount = 0;
        final int actualItemCount = itemDao.getItemCount();

        final String errorMessage = "Expected and actual client size are different";
        assertEquals(errorMessage, expectedItemCount, actualItemCount);
    }

    @Test
    public void deleteItemWithNoIdShouldNotThrowException() throws Exception {
        itemDao.deleteItem(item);
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteItemWithNotExistedIdShouldThrowException() throws Exception {
        item.setId(1);
        itemDao.deleteItem(item);
    }

    @Ignore
    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void deleteItemWithInvalidIdShouldThrowException() throws Exception {
        item.setId(Integer.MAX_VALUE);
        itemDao.deleteItem(item);
    }
}
