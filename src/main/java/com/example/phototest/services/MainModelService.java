package com.example.phototest.services;

import com.example.phototest.models.MainModel;
import com.example.phototest.models.MainModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MainModelService {
    @Autowired
    private MainModelRepo mainModelRepo;

    public void save(MultipartFile file1) throws IOException{
        MainModel file = new MainModel();
        file.setData(file1.getBytes());
        file.setContentType(file1.getContentType());
        mainModelRepo.save(file);
    }

    public Optional<MainModel> getFile(Integer id){
        return  mainModelRepo.findById(id);
    }

}
