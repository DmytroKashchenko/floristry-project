package ua.dmytrokashchenko.floristry.domain.flower;

public class Rose extends Flower {
    private boolean prickles;

    private Rose(RoseBuilder roseBuilder) {
        super(roseBuilder);
        this.prickles = roseBuilder.prickles;
    }

    public boolean isPrickles() {
        return prickles;
    }

    public static class RoseBuilder extends FlowerBuilder<RoseBuilder> {
        private boolean prickles;

        public RoseBuilder() {
        }

        @Override
        public RoseBuilder self() {
            return this;
        }

        public Rose build() {
            return new Rose(self());
        }

        public RoseBuilder withPrickles(boolean prickles) {
            this.prickles = prickles;
            return this;
        }
    }
}
