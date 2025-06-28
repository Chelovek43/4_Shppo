package domain.diet;

import java.util.ArrayList;
import java.util.List;

public class CompositeDietCycle implements DietCycle {
    private List<DietCycle> cycles = new ArrayList<>();

    public void add(DietCycle cycle) {
        cycles.add(cycle);
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("Combo cycle:\n");
        for (DietCycle cycle : cycles) {
            sb.append("  - ").append(cycle.getDescription()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int getDuration() {
        return cycles.stream().mapToInt(DietCycle::getDuration).sum();
    }
}