package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)

public class Benchmark
{

    @State(Scope.Benchmark)
    public static class FullSet
    {

        Car[] cars;
        BstSet<Car> carSet;

        @Setup(Level.Iteration)
        public void generateElements(BenchmarkParams params)
        {
            cars = Benchmark.generateElements(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarSet(BenchmarkParams params)
        {
            carSet = new BstSet<>();
           addElements(cars, carSet);


        }
    }

    @Param({"10000", "20000", "40000", "80000"})
    public int elementCount;

    Car[] cars;

    @Setup(Level.Iteration)
    public void generateElements()
    {
        cars = generateElements(elementCount);
    }

    static Car[] generateElements(int count)
    {
        return new CarsGenerator().generateShuffle(count, 1.0);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public BstSet<Car> addBstRecursive()
    {
        BstSet<Car> carSet = new BstSet<>(Car.byPrice);
        addElements(cars, carSet);
        return carSet;
    }

    @org.openjdk.jmh.annotations.Benchmark
    public BstSetIterative<Car> addBstIterative()
    {
        BstSetIterative<Car> carSet = new BstSetIterative<>(Car.byPrice);
        addElements(cars, carSet);
        return carSet;
    }

   @org.openjdk.jmh.annotations.Benchmark
    public AvlSet<Car> addAvlRecursive()
    {
        AvlSet<Car> carSet = new AvlSet<>(Car.byPrice);
        addElements(cars, carSet);
        return carSet;
    }

    //remove method call
    @org.openjdk.jmh.annotations.Benchmark //@BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS) @Warmup(time = 1, timeUnit = TimeUnit.SECONDS) @Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
    public void removeBst(FullSet carSet)
    {
        for (Car car : carSet.cars)
        {
            carSet.carSet.remove(car);
        }
    }

    public static void addElements(Car[] carArray, SortedSet<Car> carSet)
    {
        for (Car car : carArray) {
            carSet.add(car);
        }
    }
//   @org.openjdk.jmh.annotations.Benchmark
    public  void Removal(Car[] carArray, SortedSet<Car> carSet)
    {
        for (Car car : carArray) {
            carSet.add(car);
        }
    }

    public static void main(String[] args) throws RunnerException, CloneNotSupportedException {
        Options opt = new OptionsBuilder().include(Benchmark.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();


    }
}
