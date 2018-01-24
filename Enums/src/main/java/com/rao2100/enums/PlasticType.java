package com.rao2100.enums;

import java.util.Arrays;
import java.util.List;

public enum PlasticType {

    PET {
        @Override
        public boolean isSustainable() {
            return true;
        }

        @Override
        public String getDesc() {
            return "Polyethylene Terephthalate";
        }
    },
    HDPE {
        @Override
        public boolean isSustainable() {
            return true;
        }

        @Override
        public String getDesc() {
            return "High-density Polyethylene";
        }
    },
    PVC {
        @Override
        public boolean isSustainable() {
            return false;
        }

        @Override
        public String getDesc() {
            return "Polyvinyl Chloride";
        }
    },
    LDPE {
        @Override
        public boolean isSustainable() {
            return false;
        }

        @Override
        public String getDesc() {
            return "Low-density Polyethylene";
        }
    };

    public abstract boolean isSustainable();

    public abstract String getDesc();

    @Override
    public String toString() {
        return "PlasticType: " + this.name() + " ";

    }

    public static List<PlasticType> getList() {
        return Arrays.asList(PlasticType.values());
    }

}
