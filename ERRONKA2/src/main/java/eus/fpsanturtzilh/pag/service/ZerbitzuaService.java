package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Zerbitzua;
import eus.fpsanturtzilh.pag.repository.ZerbitzuaRepository;

@Service
public class ZerbitzuaService {
	
	private final ZerbitzuaRepository repository;
	
	public ZerbitzuaService(ZerbitzuaRepository repository) {
        this.repository = repository;
    }

	public List<Zerbitzua> getAllZerbitzuak(){
        return repository.findAll();	
	}
	
	public Zerbitzua saveZerbitzua(Zerbitzua zerbitzua) {
		return repository.save(zerbitzua);
	}

    public void deleteZerbitzua(Integer id) {
        repository.deleteById(id);
    }
	
    
    //Aldatu name, price, home_price eta duration
    public Zerbitzua updateZerbitzua(Integer id, Zerbitzua updateZerbitzua) {
    	Zerbitzua existingZerbitzua = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zerbitzua not found with id " + id));

    	existingZerbitzua.setName(updateZerbitzua.getName());
    	existingZerbitzua.setPrice(updateZerbitzua.getPrice());
    	existingZerbitzua.setHome_price(updateZerbitzua.getHome_price());
    	existingZerbitzua.setDuration(updateZerbitzua.getDuration());

        return repository.save(existingZerbitzua);
    }

	//Get Zerbitzua bat
    public Zerbitzua findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
}
