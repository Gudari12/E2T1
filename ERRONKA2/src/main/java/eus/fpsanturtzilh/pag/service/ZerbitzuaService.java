package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Zerbitzua;
import eus.fpsanturtzilh.pag.repository.ZerbitzuaRepository;

/**
 * Zerbitzua entitatea kudeatzen duen zerbitzua
 * Zerbitzua-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class ZerbitzuaService {

    @Autowired
	private final ZerbitzuaRepository repository;

	/**
	 * ZerbitzuaService sortzailea
	 * 
	 * @param repository
	 */
	public ZerbitzuaService(ZerbitzuaRepository repository) {
        this.repository = repository;
    }

	/**
	 * Zerbitzua guztien zerrenda ikusteko
	 * 
	 * @return zerbitzua guztien zerrenda
	 */
	public List<Zerbitzua> getAllZerbitzuak(){
        return repository.findAll();	
	}

	/**
	 * Zerbitzua berri bat sortzeko
	 * 
	 * @param zerbitzua: sortu nahi den appointment
	 * @return gordetako zerbitzua identifikatzailearekin
	 */
	public Zerbitzua saveZerbitzua(Zerbitzua zerbitzua) {
		return repository.save(zerbitzua);
	}

	/**
	 * Zerbitzua espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den zerbitzua-aren identifikatzailea
	 */
    public void deleteZerbitzua(Integer id) {
        repository.deleteById(id);
    }
	 
    /**
     * Zerbitzua espezifiko baten izena, prezioa, home_prezioa eta iraupena aldatzeko
     * 
     * @param id: eguneratu nahi den zerbitzua-aren identifikatzailea
     * @param updateZerbitzua: eguneratzen diren balioak
     * @return eguneratutako zerbitzua
     */
    public Zerbitzua updateZerbitzua(Integer id, Zerbitzua updateZerbitzua) {
    	Zerbitzua existingZerbitzua = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zerbitzua not found with id " + id));

    	existingZerbitzua.setName(updateZerbitzua.getName());
    	existingZerbitzua.setPrice(updateZerbitzua.getPrice());
    	existingZerbitzua.setHome_price(updateZerbitzua.getHome_price());
    	existingZerbitzua.setDuration(updateZerbitzua.getDuration());

        return repository.save(existingZerbitzua);
    }

    /**
     * Zerbitzua espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den zerbitzua-aren identifikatzailea
     * @return aurkitutako zerbitzua edo null existitzen ez bada
     */
    public Zerbitzua findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
}
