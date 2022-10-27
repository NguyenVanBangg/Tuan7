package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.crud.entity.NhanVien;

import javax.transaction.Transactional;
import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    @Modifying
    @Transactional
    @Query(value = "select * from nhanvien", nativeQuery = true)
    public List<NhanVien> findAllNhanVien();

}