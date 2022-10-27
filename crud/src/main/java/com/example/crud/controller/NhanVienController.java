package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.entity.NhanVien;
import com.example.crud.repository.NhanVienRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class NhanVienController {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("/getAllNhanVien")
    List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAllNhanVien();
    }

   
    @PostMapping("/addNhanVien")
    public ResponseEntity<NhanVien> addNewNhanVien(@RequestBody NhanVien nv) {
        return new ResponseEntity<>(nhanVienRepository.save(nv), HttpStatus.OK);
    }


    @DeleteMapping("/deleteNhanVien/{manv}")
    public ResponseEntity<NhanVien> deleteNhanVienByManv(@PathVariable Integer manv) {
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(manv);
        return nhanVien.map(nv -> {
            nhanVienRepository.deleteById(manv);
            return new ResponseEntity<>(nv, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PutMapping("/updateNhanVien/{manv}")
    public ResponseEntity<NhanVien> updateSanPhamByMaSp(@PathVariable Integer manv, @RequestBody NhanVien nv) {
    	 Optional<NhanVien> nhanVien = nhanVienRepository.findById(manv);
        return nhanVien.map(nv1 -> {
          nv.setMaNV(nv1.getMaNV());
            return new ResponseEntity<>(nhanVienRepository.save(nv), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/findNhanVien/{manv}")
    public ResponseEntity<NhanVien> findNhanVienByMaNv(@PathVariable String manv) {
    	Optional<NhanVien> nhanVien = nhanVienRepository.findById(manv);
        return nhanVien.map(nv -> new ResponseEntity<>(nv, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
