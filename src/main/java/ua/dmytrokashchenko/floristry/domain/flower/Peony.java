package ua.dmytrokashchenko.floristry.domain.flower;

public class Peony extends Flower {
    public enum PeonyType {HERBACEOUS, WOODY}

    private PeonyType peonyType;

    private Peony(PeonyBuilder peonyBuilder) {
        super(peonyBuilder);
        this.peonyType = peonyBuilder.peonyType;
    }

    public PeonyType getPeonyType() {
        return peonyType;
    }

    public static class PeonyBuilder extends FlowerBuilder<PeonyBuilder> {
        private PeonyType peonyType;

        public PeonyBuilder() {
        }

        @Override
        public PeonyBuilder self() {
            return this;
        }

        public Peony build() {
            return new Peony(self());
        }

        public PeonyBuilder withPeonyType(PeonyType peonyType) {
            this.peonyType = peonyType;
            return this;
        }
    }
}
