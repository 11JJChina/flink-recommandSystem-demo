package com.zhang.service.impl;

import com.zhang.dao.ContactDao;
import com.zhang.domain.ContactEntity;
import com.zhang.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public List<ContactEntity> selectByIds(List<String> ids) {
        return contactDao.selectByIds(ids);
    }

    @Override
    public ContactEntity selectById(String id) {
        return contactDao.selectById(Integer.valueOf(id));
    }
}
