package ua.dmytrokashchenko.floristry.domain.flower;

public class Hydrangea extends Flower {
    private boolean largeLeaf;

    private Hydrangea(HydrangeaBuilder hydrangeaBuilder) {
        super(hydrangeaBuilder);
        this.largeLeaf = hydrangeaBuilder.largeLeaf;
    }

    public boolean isLargeLeaf() {
        return largeLeaf;
    }

    public static class HydrangeaBuilder extends FlowerBuilder<HydrangeaBuilder> {
        private boolean largeLeaf;

        public HydrangeaBuilder() {
        }

        @Override
        public HydrangeaBuilder self() {
            return this;
        }

        public Hydrangea build() {
            return new Hydrangea(self());
        }

        public HydrangeaBuilder withLeaves(boolean largeLeaf) {
            this.largeLeaf = largeLeaf;
            return this;
        }
    }
}
