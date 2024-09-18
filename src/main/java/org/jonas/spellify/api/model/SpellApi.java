package org.jonas.spellify.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpellApi {
//todo Testa att bygga en record istället
        public String index;
        public String name;               // Name of the spell
        @JsonProperty("casting_time")
        public String castingTime;        // Casting time of the spell
        @JsonProperty("desc")
        public List<String> description;  // Description of the spell
        public int level;                 // Level of the spell
        public String range;              // Range of the spell
        public boolean ritual;            // Whether the spell is a ritual
        public String duration;           // Duration of the spell
        public boolean concentration;     // Whether the spell requires concentration


        public String getIndex() {
                return index;
        }

        public void setIndex(String index) {
                this.index = index;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCastingTime() {
                return castingTime;
        }

        public void setCastingTime(String castingTime) {
                this.castingTime = castingTime;
        }

        public List<String> getDescription() {
                return description;
        }

        public void setDescription(List<String> description) {
                this.description = description;
        }

        public int getLevel() {
                return level;
        }

        public void setLevel(int level) {
                this.level = level;
        }

        public String getRange() {
                return range;
        }

        public void setRange(String range) {
                this.range = range;
        }

        public boolean isRitual() {
                return ritual;
        }

        public void setRitual(boolean ritual) {
                this.ritual = ritual;
        }

        public String getDuration() {
                return duration;
        }

        public void setDuration(String duration) {
                this.duration = duration;
        }

        public boolean isConcentration() {
                return concentration;
        }

        public void setConcentration(boolean concentration) {
                this.concentration = concentration;
        }
}