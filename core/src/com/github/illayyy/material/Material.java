package com.github.illayyy.material;

public enum Material {
    AIR {
        @Override
        public Entity getEntity() {
            return new Air();
        }
    },
    SAND {
        @Override
        public Entity getEntity() {
            return new Sand();
        }
    },
    CONCRETE {
        @Override
        public Entity getEntity() {
            return new Concrete();
        }
    };

    public abstract Entity getEntity();

    public Material next() {
        Material[] values = Material.values();

        return values[(this.ordinal() + 1) % values.length];
    }
}
