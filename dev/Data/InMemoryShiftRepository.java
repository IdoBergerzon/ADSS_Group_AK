package Data;

import Domain.Shift;
import Domain.Request;

import java.util.HashMap;
import java.util.Map;

public class InMemoryShiftRepository {
    private final Map<Pair, Shift> shifts;

    private InMemoryShiftRepository() {
        shifts = new HashMap<>();
    }
}
