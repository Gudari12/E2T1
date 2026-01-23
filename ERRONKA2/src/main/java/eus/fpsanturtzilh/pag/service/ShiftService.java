package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Shift;
import eus.fpsanturtzilh.pag.repository.ShiftRepository;

/**
 * Shift entitatea kudeatzen duen zerbitzua
 * Shift-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class ShiftService {

    @Autowired
	private final ShiftRepository repository;
	
	/**
	 * ShiftService sortzailea
	 * 
	 * @param repository
	 */
	public ShiftService(ShiftRepository repository) {
        this.repository = repository;
    }

	/**
	 * Shift guztien zerrenda ikusteko
	 * 
	 * @return shift guztien zerrenda
	 */
	public List<Shift> getAllShifts() {
        return repository.findAll();
    }

	/**
	 * Shift berri bat sortzeko
	 * 
	 * @param shift: sortu nahi den appointment
	 * @return gordetako shift identifikatzailearekin
	 */
	public Shift saveShift(Shift shift) {
		return repository.save(shift);
	}

	/**
	 * Shift espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den shift-aren identifikatzailea
	 */
    public void deleteShift(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Shift espezifiko baten mota aldatzeko
     * 
     * @param id: eguneratu nahi den shift-aren identifikatzailea
     * @param updateShift: eguneratzen diren balioak
     * @return eguneratutako shift
     */
    public Shift updateShift(Integer id, Shift updateShift) {
    	Shift existingShift = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shift not found with id " + id));

    	existingShift.setType(updateShift.getType());

        return repository.save(existingShift);
    }

    /**
     * Shift espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den shift-aren identifikatzailea
     * @return aurkitutako shift edo null existitzen ez bada
     */
    public Shift findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}