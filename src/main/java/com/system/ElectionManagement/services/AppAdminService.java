package com.system.ElectionManagement.services;

import com.system.ElectionManagement.repositories.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppAdminService implements AdminServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdminRepository adminRepository;
}
