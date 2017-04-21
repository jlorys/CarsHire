package carshire;

import carshire.domain.Hire;
import java.util.List;

public interface HireService {

    public List<Hire> findAllHires();

    public Hire save(Hire hire);

    public void delete(Hire hire);
}
