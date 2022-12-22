package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sort {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    public static class Entity {
        private int age;

        public Entity(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        @Param({"100", "1000", "10000"})
        public int size;

        public List<Entity> list;
        public Entity[] array;

        @Setup(Level.Invocation)
        public void init() {
            list = IntStream
                    .range(0, size)
                    .mapToObj(Entity::new)
                    .collect(Collectors.toList());
            array = list.toArray(new Entity[0]);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @SuppressWarnings("unused")
    public void streamComparingInt(BenchmarkState state, Blackhole blackhole) {
        blackhole.consume(
                state.list.stream()
                        .sorted(Comparator.comparingInt(Entity::getAge))
                        .collect(Collectors.toList())
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @SuppressWarnings("unused")
    public void streamTernary(BenchmarkState state, Blackhole blackhole) {
        blackhole.consume(
                state.list.stream()
                        .sorted((o1, o2) -> (o1.getAge() < o2.getAge()) ? -1 : ((o1.getAge() == o2.getAge()) ? 0 : 1))
                        .collect(Collectors.toList())
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @SuppressWarnings("unused")
    public void listSortComparingInt(BenchmarkState state, Blackhole blackhole) {
        state.list.sort(Comparator.comparingInt(Entity::getAge));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 3000, timeUnit = TimeUnit.MILLISECONDS)
    @SuppressWarnings("unused")
    public void arraySortComparingInt(BenchmarkState state, Blackhole blackhole) {
        Arrays.sort(state.array, Comparator.comparingInt(Entity::getAge));
    }
}
