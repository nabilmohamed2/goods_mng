package com.projects.management_sys.repository;


import com.projects.management_sys.entity.ItemTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTrackerRepository extends JpaRepository<ItemTracker,Long> {}
