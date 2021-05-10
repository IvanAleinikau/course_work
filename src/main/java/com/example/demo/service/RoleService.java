package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> findAll(){
        List<Role> roles = new ArrayList<Role>();
        roleDao.findAll().forEach(role -> roles.add(role));
        return roles;
    }

    public Role findById(Integer roleId){
        return roleDao.findById(roleId).get();
    }

    public void create(Role role){
        roleDao.save(role);
    }

    public void delete(Integer roleId){
        roleDao.deleteById(roleId);
    }

    public void update(Role role,Integer roleId){
        roleDao.save(role);
    }
}
