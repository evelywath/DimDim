package br.com.fiap.dimdim.cupom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService {

    @Autowired
    CupomRepository repository;

    public List<Cupom> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var cupom = repository.findById(id);
        if(cupom.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Cupom cupom) {
        repository.save(cupom);
    }
    
}