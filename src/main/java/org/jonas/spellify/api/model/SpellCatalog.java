package org.jonas.spellify.api.model;

import java.util.List;

public class SpellCatalog {
    int count;
    List<SpellShort> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SpellShort> getResults() {
        return results;
    }

    public void setResults(List<SpellShort> results) {
        this.results = results;
    }
}
