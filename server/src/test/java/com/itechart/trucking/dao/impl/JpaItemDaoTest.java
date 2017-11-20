package com.itechart.trucking.dao.impl;

import com.itechart.trucking.Application;
import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.domain.Item;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@ActiveProfiles("dev_Quontico")
public class JpaItemDaoTest {

    @Autowired
    private EntityManager entityManager;

    private ItemDao itemDao;

    private static final String NAME="item_name";

    private static final Integer PRICE= 15;

    private static final Item.Type TYPE = Item.Type.COUNT;

    @Before
    public void setItemDao() {
        itemDao = new JpaItemDao(entityManager);


}
