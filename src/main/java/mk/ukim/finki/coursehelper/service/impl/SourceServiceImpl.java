package mk.ukim.finki.coursehelper.service.impl;

import mk.ukim.finki.coursehelper.model.Source;
import mk.ukim.finki.coursehelper.repository.SourceRepository;
import mk.ukim.finki.coursehelper.service.SourceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepo;

    public SourceServiceImpl(SourceRepository sourceRepo) {
        this.sourceRepo = sourceRepo;
    }

    @Override
    public Optional<Source> getSourceById(Long id) {
        return sourceRepo.findById(id);
    }
}
