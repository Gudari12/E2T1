package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Shift;
import eus.fpsanturtzilh.pag.repository.ShiftRepository;

@Service
public class ShiftService {
	
	private final ShiftRepository repository;
	
	public ShiftService(ShiftRepository repository) {
        this.repository = repository;
    }

	public List<Shift> getAllShifts() {
        return repository.findAll();
    }

	public Shift saveShift(Shift shift) {
		return repository.save(shift);
	}

    public void deleteShift(Integer id) {
        repository.deleteById(id);
    }

    //Type aldatu
    public Shift updateShift(Integer id, Shift updateShift) {
    	Shift existingShift = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shift not found with id " + id));

    	existingShift.setType(updateShift.getType());

        return repository.save(existingShift);
    }

	//Get Shift bat
    public Shift findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}