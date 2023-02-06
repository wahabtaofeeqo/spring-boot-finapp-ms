/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taoltech.finapp.repositories;

import com.taoltech.finapp.models.Role;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findByName(String name);
}
