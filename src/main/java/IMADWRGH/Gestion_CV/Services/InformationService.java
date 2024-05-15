package IMADWRGH.Gestion_CV.Services;

import IMADWRGH.Gestion_CV.Model.Information;
import IMADWRGH.Gestion_CV.Repository.InformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {

        private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    public List<Information> getAll() {
            return informationRepository.getAll();
        }

        public Optional<Information> getById(int id) {
            return informationRepository.findById(id);
        }

        public int deleteById(int id) {
            return informationRepository.deleteById(id);
        }

        public int save(Information information) {
            return informationRepository.insert(information);
        }

        public int update(Information information) {
            return informationRepository.update(information);
        }
    }
