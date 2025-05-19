package mk.ukim.finki.coursehelper.service;

import mk.ukim.finki.coursehelper.model.Source;
import java.util.Optional;

public interface SourceService {
    Optional<Source> getSourceById(Long id);
}
