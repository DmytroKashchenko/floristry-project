package ua.dmytrokashchenko.floristry.domain.flower;

public class Calla extends Flower {
    private boolean leaves;

    private Calla(CallaBuilder callaBuilder) {
        super(callaBuilder);
        this.leaves = callaBuilder.leaves;
    }

    public boolean isLeaves() {
        return leaves;
    }

    public static class CallaBuilder extends FlowerBuilder<CallaBuilder> {
        private boolean leaves;

        public CallaBuilder() {
        }

        @Override
        public CallaBuilder self() {
            return this;
        }

        public Calla build() {
            return new Calla(self());
        }

        public CallaBuilder withLeaves(boolean leaves) {
            this.leaves = leaves;
            return this;
        }
    }
}
