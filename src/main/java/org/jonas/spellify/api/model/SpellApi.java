package org.jonas.spellify.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SpellApi {

        public String index;
        public String name;
        @JsonProperty("casting_time")
        public String castingTime;
        @JsonProperty("desc")
        public List<String> description;
        public int level;
        public String range;
        public boolean ritual;
        public String duration;
        public boolean concentration;


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