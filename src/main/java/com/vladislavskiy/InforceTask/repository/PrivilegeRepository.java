package com.vladislavskiy.InforceTask.repository;


import com.vladislavskiy.InforceTask.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByName(String name);
}
